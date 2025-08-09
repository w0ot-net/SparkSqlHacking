package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.EventLoop;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.DuplexChannel;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.IovArray;
import io.netty.channel.unix.SocketWritableByteChannel;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.Queue;
import java.util.concurrent.Executor;

public abstract class AbstractEpollStreamChannel extends AbstractEpollChannel implements DuplexChannel {
   private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
   private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(DefaultFileRegion.class) + ')';
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(AbstractEpollStreamChannel.class);
   private final Runnable flushTask;
   private volatile Queue spliceQueue;
   private FileDescriptor pipeIn;
   private FileDescriptor pipeOut;
   private WritableByteChannel byteChannel;

   protected AbstractEpollStreamChannel(Channel parent, int fd) {
      this(parent, new LinuxSocket(fd));
   }

   protected AbstractEpollStreamChannel(int fd) {
      this(new LinuxSocket(fd));
   }

   AbstractEpollStreamChannel(LinuxSocket fd) {
      this(fd, isSoErrorZero(fd));
   }

   AbstractEpollStreamChannel(Channel parent, LinuxSocket fd) {
      super(parent, fd, true);
      this.flushTask = new Runnable() {
         public void run() {
            ((AbstractEpollChannel.AbstractEpollUnsafe)AbstractEpollStreamChannel.this.unsafe()).flush0();
         }
      };
      this.flags |= Native.EPOLLRDHUP;
   }

   protected AbstractEpollStreamChannel(Channel parent, LinuxSocket fd, SocketAddress remote) {
      super(parent, fd, remote);
      this.flushTask = new Runnable() {
         public void run() {
            ((AbstractEpollChannel.AbstractEpollUnsafe)AbstractEpollStreamChannel.this.unsafe()).flush0();
         }
      };
      this.flags |= Native.EPOLLRDHUP;
   }

   protected AbstractEpollStreamChannel(LinuxSocket fd, boolean active) {
      super((Channel)null, fd, active);
      this.flushTask = new Runnable() {
         public void run() {
            ((AbstractEpollChannel.AbstractEpollUnsafe)AbstractEpollStreamChannel.this.unsafe()).flush0();
         }
      };
      this.flags |= Native.EPOLLRDHUP;
   }

   protected AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
      return new EpollStreamUnsafe();
   }

   public ChannelMetadata metadata() {
      return METADATA;
   }

   public final ChannelFuture spliceTo(AbstractEpollStreamChannel ch, int len) {
      return this.spliceTo(ch, len, this.newPromise());
   }

   public final ChannelFuture spliceTo(AbstractEpollStreamChannel ch, int len, ChannelPromise promise) {
      if (ch.eventLoop() != this.eventLoop()) {
         throw new IllegalArgumentException("EventLoops are not the same.");
      } else {
         ObjectUtil.checkPositiveOrZero(len, "len");
         if (ch.config().getEpollMode() == EpollMode.LEVEL_TRIGGERED && this.config().getEpollMode() == EpollMode.LEVEL_TRIGGERED) {
            ObjectUtil.checkNotNull(promise, "promise");
            if (!this.isOpen()) {
               promise.tryFailure(new ClosedChannelException());
            } else {
               this.addToSpliceQueue(new SpliceInChannelTask(ch, len, promise));
               this.failSpliceIfClosed(promise);
            }

            return promise;
         } else {
            throw new IllegalStateException("spliceTo() supported only when using " + EpollMode.LEVEL_TRIGGERED);
         }
      }
   }

   public final ChannelFuture spliceTo(FileDescriptor ch, int offset, int len) {
      return this.spliceTo(ch, offset, len, this.newPromise());
   }

   public final ChannelFuture spliceTo(FileDescriptor ch, int offset, int len, ChannelPromise promise) {
      ObjectUtil.checkPositiveOrZero(len, "len");
      ObjectUtil.checkPositiveOrZero(offset, "offset");
      if (this.config().getEpollMode() != EpollMode.LEVEL_TRIGGERED) {
         throw new IllegalStateException("spliceTo() supported only when using " + EpollMode.LEVEL_TRIGGERED);
      } else {
         ObjectUtil.checkNotNull(promise, "promise");
         if (!this.isOpen()) {
            promise.tryFailure(new ClosedChannelException());
         } else {
            this.addToSpliceQueue(new SpliceFdTask(ch, offset, len, promise));
            this.failSpliceIfClosed(promise);
         }

         return promise;
      }
   }

   private void failSpliceIfClosed(ChannelPromise promise) {
      if (!this.isOpen() && !promise.isDone()) {
         final ClosedChannelException ex = new ClosedChannelException();
         if (promise.tryFailure(ex)) {
            this.eventLoop().execute(new Runnable() {
               public void run() {
                  AbstractEpollStreamChannel.this.clearSpliceQueue(ex);
               }
            });
         }
      }

   }

   private int writeBytes(ChannelOutboundBuffer in, ByteBuf buf) throws Exception {
      int readableBytes = buf.readableBytes();
      if (readableBytes == 0) {
         in.remove();
         return 0;
      } else if (!buf.hasMemoryAddress() && buf.nioBufferCount() != 1) {
         ByteBuffer[] nioBuffers = buf.nioBuffers();
         return this.writeBytesMultiple(in, nioBuffers, nioBuffers.length, (long)readableBytes, this.config().getMaxBytesPerGatheringWrite());
      } else {
         return this.doWriteBytes(in, buf);
      }
   }

   private void adjustMaxBytesPerGatheringWrite(long attempted, long written, long oldMaxBytesPerGatheringWrite) {
      if (attempted == written) {
         if (attempted << 1 > oldMaxBytesPerGatheringWrite) {
            this.config().setMaxBytesPerGatheringWrite(attempted << 1);
         }
      } else if (attempted > 4096L && written < attempted >>> 1) {
         this.config().setMaxBytesPerGatheringWrite(attempted >>> 1);
      }

   }

   private int writeBytesMultiple(ChannelOutboundBuffer in, IovArray array) throws IOException {
      long expectedWrittenBytes = array.size();

      assert expectedWrittenBytes != 0L;

      int cnt = array.count();

      assert cnt != 0;

      long localWrittenBytes = this.socket.writevAddresses(array.memoryAddress(0), cnt);
      if (localWrittenBytes > 0L) {
         this.adjustMaxBytesPerGatheringWrite(expectedWrittenBytes, localWrittenBytes, array.maxBytes());
         in.removeBytes(localWrittenBytes);
         return 1;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   private int writeBytesMultiple(ChannelOutboundBuffer in, ByteBuffer[] nioBuffers, int nioBufferCnt, long expectedWrittenBytes, long maxBytesPerGatheringWrite) throws IOException {
      assert expectedWrittenBytes != 0L;

      if (expectedWrittenBytes > maxBytesPerGatheringWrite) {
         expectedWrittenBytes = maxBytesPerGatheringWrite;
      }

      long localWrittenBytes = this.socket.writev(nioBuffers, 0, nioBufferCnt, expectedWrittenBytes);
      if (localWrittenBytes > 0L) {
         this.adjustMaxBytesPerGatheringWrite(expectedWrittenBytes, localWrittenBytes, maxBytesPerGatheringWrite);
         in.removeBytes(localWrittenBytes);
         return 1;
      } else {
         return Integer.MAX_VALUE;
      }
   }

   private int writeDefaultFileRegion(ChannelOutboundBuffer in, DefaultFileRegion region) throws Exception {
      long offset = region.transferred();
      long regionCount = region.count();
      if (offset >= regionCount) {
         in.remove();
         return 0;
      } else {
         long flushedAmount = this.socket.sendFile(region, region.position(), offset, regionCount - offset);
         if (flushedAmount > 0L) {
            in.progress(flushedAmount);
            if (region.transferred() >= regionCount) {
               in.remove();
            }

            return 1;
         } else {
            if (flushedAmount == 0L) {
               this.validateFileRegion(region, offset);
            }

            return Integer.MAX_VALUE;
         }
      }
   }

   private int writeFileRegion(ChannelOutboundBuffer in, FileRegion region) throws Exception {
      if (region.transferred() >= region.count()) {
         in.remove();
         return 0;
      } else {
         if (this.byteChannel == null) {
            this.byteChannel = new EpollSocketWritableByteChannel();
         }

         long flushedAmount = region.transferTo(this.byteChannel, region.transferred());
         if (flushedAmount > 0L) {
            in.progress(flushedAmount);
            if (region.transferred() >= region.count()) {
               in.remove();
            }

            return 1;
         } else {
            return Integer.MAX_VALUE;
         }
      }
   }

   protected void doWrite(ChannelOutboundBuffer in) throws Exception {
      int writeSpinCount = this.config().getWriteSpinCount();

      do {
         int msgCount = in.size();
         if (msgCount > 1 && in.current() instanceof ByteBuf) {
            writeSpinCount -= this.doWriteMultiple(in);
         } else {
            if (msgCount == 0) {
               this.clearFlag(Native.EPOLLOUT);
               return;
            }

            writeSpinCount -= this.doWriteSingle(in);
         }
      } while(writeSpinCount > 0);

      if (writeSpinCount == 0) {
         this.clearFlag(Native.EPOLLOUT);
         this.eventLoop().execute(this.flushTask);
      } else {
         this.setFlag(Native.EPOLLOUT);
      }

   }

   protected int doWriteSingle(ChannelOutboundBuffer in) throws Exception {
      Object msg = in.current();
      if (msg instanceof ByteBuf) {
         return this.writeBytes(in, (ByteBuf)msg);
      } else if (msg instanceof DefaultFileRegion) {
         return this.writeDefaultFileRegion(in, (DefaultFileRegion)msg);
      } else if (msg instanceof FileRegion) {
         return this.writeFileRegion(in, (FileRegion)msg);
      } else if (msg instanceof SpliceOutTask) {
         if (!((SpliceOutTask)msg).spliceOut()) {
            return Integer.MAX_VALUE;
         } else {
            in.remove();
            return 1;
         }
      } else {
         throw new Error();
      }
   }

   private int doWriteMultiple(ChannelOutboundBuffer in) throws Exception {
      long maxBytesPerGatheringWrite = this.config().getMaxBytesPerGatheringWrite();
      IovArray array = ((EpollEventLoop)this.eventLoop()).cleanIovArray();
      array.maxBytes(maxBytesPerGatheringWrite);
      in.forEachFlushedMessage(array);
      if (array.count() >= 1) {
         return this.writeBytesMultiple(in, array);
      } else {
         in.removeBytes(0L);
         return 0;
      }
   }

   protected Object filterOutboundMessage(Object msg) {
      if (msg instanceof ByteBuf) {
         ByteBuf buf = (ByteBuf)msg;
         return UnixChannelUtil.isBufferCopyNeededForWrite(buf) ? this.newDirectBuffer(buf) : buf;
      } else if (!(msg instanceof FileRegion) && !(msg instanceof SpliceOutTask)) {
         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(msg) + EXPECTED_TYPES);
      } else {
         return msg;
      }
   }

   protected final void doShutdownOutput() throws Exception {
      this.socket.shutdown(false, true);
   }

   private void shutdownInput0(ChannelPromise promise) {
      try {
         this.socket.shutdown(true, false);
         promise.setSuccess();
      } catch (Throwable cause) {
         promise.setFailure(cause);
      }

   }

   public boolean isOutputShutdown() {
      return this.socket.isOutputShutdown();
   }

   public boolean isInputShutdown() {
      return this.socket.isInputShutdown();
   }

   public boolean isShutdown() {
      return this.socket.isShutdown();
   }

   public ChannelFuture shutdownOutput() {
      return this.shutdownOutput(this.newPromise());
   }

   public ChannelFuture shutdownOutput(final ChannelPromise promise) {
      EventLoop loop = this.eventLoop();
      if (loop.inEventLoop()) {
         ((AbstractChannel.AbstractUnsafe)this.unsafe()).shutdownOutput(promise);
      } else {
         loop.execute(new Runnable() {
            public void run() {
               ((AbstractChannel.AbstractUnsafe)AbstractEpollStreamChannel.this.unsafe()).shutdownOutput(promise);
            }
         });
      }

      return promise;
   }

   public ChannelFuture shutdownInput() {
      return this.shutdownInput(this.newPromise());
   }

   public ChannelFuture shutdownInput(final ChannelPromise promise) {
      Executor closeExecutor = ((EpollStreamUnsafe)this.unsafe()).prepareToClose();
      if (closeExecutor != null) {
         closeExecutor.execute(new Runnable() {
            public void run() {
               AbstractEpollStreamChannel.this.shutdownInput0(promise);
            }
         });
      } else {
         EventLoop loop = this.eventLoop();
         if (loop.inEventLoop()) {
            this.shutdownInput0(promise);
         } else {
            loop.execute(new Runnable() {
               public void run() {
                  AbstractEpollStreamChannel.this.shutdownInput0(promise);
               }
            });
         }
      }

      return promise;
   }

   public ChannelFuture shutdown() {
      return this.shutdown(this.newPromise());
   }

   public ChannelFuture shutdown(final ChannelPromise promise) {
      ChannelFuture shutdownOutputFuture = this.shutdownOutput();
      if (shutdownOutputFuture.isDone()) {
         this.shutdownOutputDone(shutdownOutputFuture, promise);
      } else {
         shutdownOutputFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture shutdownOutputFuture) throws Exception {
               AbstractEpollStreamChannel.this.shutdownOutputDone(shutdownOutputFuture, promise);
            }
         });
      }

      return promise;
   }

   private void shutdownOutputDone(final ChannelFuture shutdownOutputFuture, final ChannelPromise promise) {
      ChannelFuture shutdownInputFuture = this.shutdownInput();
      if (shutdownInputFuture.isDone()) {
         shutdownDone(shutdownOutputFuture, shutdownInputFuture, promise);
      } else {
         shutdownInputFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture shutdownInputFuture) throws Exception {
               AbstractEpollStreamChannel.shutdownDone(shutdownOutputFuture, shutdownInputFuture, promise);
            }
         });
      }

   }

   private static void shutdownDone(ChannelFuture shutdownOutputFuture, ChannelFuture shutdownInputFuture, ChannelPromise promise) {
      Throwable shutdownOutputCause = shutdownOutputFuture.cause();
      Throwable shutdownInputCause = shutdownInputFuture.cause();
      if (shutdownOutputCause != null) {
         if (shutdownInputCause != null) {
            logger.debug("Exception suppressed because a previous exception occurred.", shutdownInputCause);
         }

         promise.setFailure(shutdownOutputCause);
      } else if (shutdownInputCause != null) {
         promise.setFailure(shutdownInputCause);
      } else {
         promise.setSuccess();
      }

   }

   protected void doClose() throws Exception {
      try {
         super.doClose();
      } finally {
         safeClosePipe(this.pipeIn);
         safeClosePipe(this.pipeOut);
         this.clearSpliceQueue((ClosedChannelException)null);
      }

   }

   private void clearSpliceQueue(ClosedChannelException exception) {
      Queue<SpliceInTask> sQueue = this.spliceQueue;
      if (sQueue != null) {
         while(true) {
            SpliceInTask task = (SpliceInTask)sQueue.poll();
            if (task == null) {
               return;
            }

            if (exception == null) {
               exception = new ClosedChannelException();
            }

            task.promise.tryFailure(exception);
         }
      }
   }

   private static void safeClosePipe(FileDescriptor fd) {
      if (fd != null) {
         try {
            fd.close();
         } catch (IOException e) {
            logger.warn("Error while closing a pipe", e);
         }
      }

   }

   private void addToSpliceQueue(SpliceInTask task) {
      Queue<SpliceInTask> sQueue = this.spliceQueue;
      if (sQueue == null) {
         synchronized(this) {
            sQueue = this.spliceQueue;
            if (sQueue == null) {
               this.spliceQueue = sQueue = PlatformDependent.newMpscQueue();
            }
         }
      }

      sQueue.add(task);
   }

   class EpollStreamUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
      protected Executor prepareToClose() {
         return super.prepareToClose();
      }

      private void handleReadException(ChannelPipeline pipeline, ByteBuf byteBuf, Throwable cause, boolean close, EpollRecvByteAllocatorHandle allocHandle) {
         if (byteBuf != null) {
            if (byteBuf.isReadable()) {
               this.readPending = false;
               pipeline.fireChannelRead(byteBuf);
            } else {
               byteBuf.release();
            }
         }

         allocHandle.readComplete();
         pipeline.fireChannelReadComplete();
         pipeline.fireExceptionCaught(cause);
         if (close || cause instanceof OutOfMemoryError || cause instanceof IOException) {
            this.shutdownInput(false);
         }

      }

      EpollRecvByteAllocatorHandle newEpollHandle(RecvByteBufAllocator.ExtendedHandle handle) {
         return new EpollRecvByteAllocatorStreamingHandle(handle);
      }

      void epollInReady() {
         ChannelConfig config = AbstractEpollStreamChannel.this.config();
         if (AbstractEpollStreamChannel.this.shouldBreakEpollInReady(config)) {
            this.clearEpollIn0();
         } else {
            EpollRecvByteAllocatorHandle allocHandle = this.recvBufAllocHandle();
            allocHandle.edgeTriggered(AbstractEpollStreamChannel.this.isFlagSet(Native.EPOLLET));
            ChannelPipeline pipeline = AbstractEpollStreamChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            allocHandle.reset(config);
            this.epollInBefore();
            ByteBuf byteBuf = null;
            boolean close = false;
            Queue<SpliceInTask> sQueue = null;

            try {
               do {
                  if (sQueue != null || (sQueue = AbstractEpollStreamChannel.this.spliceQueue) != null) {
                     SpliceInTask spliceTask = (SpliceInTask)sQueue.peek();
                     if (spliceTask != null) {
                        boolean spliceInResult = spliceTask.spliceIn(allocHandle);
                        if (allocHandle.isReceivedRdHup()) {
                           this.shutdownInput(true);
                        }

                        if (!spliceInResult) {
                           break;
                        }

                        if (AbstractEpollStreamChannel.this.isActive()) {
                           sQueue.remove();
                        }
                        continue;
                     }
                  }

                  byteBuf = allocHandle.allocate(allocator);
                  allocHandle.lastBytesRead(AbstractEpollStreamChannel.this.doReadBytes(byteBuf));
                  if (allocHandle.lastBytesRead() <= 0) {
                     byteBuf.release();
                     ByteBuf var17 = null;
                     close = allocHandle.lastBytesRead() < 0;
                     if (close) {
                        this.readPending = false;
                     }
                     break;
                  }

                  allocHandle.incMessagesRead(1);
                  this.readPending = false;
                  pipeline.fireChannelRead(byteBuf);
                  ByteBuf var16 = null;
                  if (AbstractEpollStreamChannel.this.shouldBreakEpollInReady(config)) {
                     break;
                  }
               } while(allocHandle.continueReading());

               allocHandle.readComplete();
               pipeline.fireChannelReadComplete();
               if (close) {
                  this.shutdownInput(false);
               }
            } catch (Throwable t) {
               this.handleReadException(pipeline, byteBuf, t, close, allocHandle);
            } finally {
               if (sQueue == null) {
                  this.epollInFinally(config);
               } else if (!config.isAutoRead()) {
                  AbstractEpollStreamChannel.this.clearEpollIn();
               }

            }

         }
      }
   }

   protected abstract class SpliceInTask {
      final ChannelPromise promise;
      int len;

      protected SpliceInTask(int len, ChannelPromise promise) {
         this.promise = promise;
         this.len = len;
      }

      abstract boolean spliceIn(RecvByteBufAllocator.Handle var1);

      protected final int spliceIn(FileDescriptor pipeOut, RecvByteBufAllocator.Handle handle) throws IOException {
         int length = Math.min(handle.guess(), this.len);
         int splicedIn = 0;

         while(true) {
            int localSplicedIn = Native.splice(AbstractEpollStreamChannel.this.socket.intValue(), -1L, pipeOut.intValue(), -1L, (long)length);
            handle.lastBytesRead(localSplicedIn);
            if (localSplicedIn == 0) {
               return splicedIn;
            }

            splicedIn += localSplicedIn;
            length -= localSplicedIn;
         }
      }
   }

   private final class SpliceInChannelTask extends SpliceInTask implements ChannelFutureListener {
      private final AbstractEpollStreamChannel ch;

      SpliceInChannelTask(AbstractEpollStreamChannel ch, int len, ChannelPromise promise) {
         super(len, promise);
         this.ch = ch;
      }

      public void operationComplete(ChannelFuture future) throws Exception {
         if (!future.isSuccess()) {
            this.promise.tryFailure(future.cause());
         }

      }

      public boolean spliceIn(RecvByteBufAllocator.Handle handle) {
         assert this.ch.eventLoop().inEventLoop();

         if (this.len == 0) {
            this.promise.trySuccess();
            return true;
         } else {
            try {
               FileDescriptor pipeOut = this.ch.pipeOut;
               if (pipeOut == null) {
                  FileDescriptor[] pipe = FileDescriptor.pipe();
                  this.ch.pipeIn = pipe[0];
                  pipeOut = this.ch.pipeOut = pipe[1];
               }

               int splicedIn = this.spliceIn(pipeOut, handle);
               if (splicedIn > 0) {
                  if (this.len != Integer.MAX_VALUE) {
                     this.len -= splicedIn;
                  }

                  ChannelPromise splicePromise;
                  if (this.len == 0) {
                     splicePromise = this.promise;
                  } else {
                     splicePromise = this.ch.newPromise().addListener(this);
                  }

                  boolean autoRead = AbstractEpollStreamChannel.this.config().isAutoRead();
                  this.ch.unsafe().write(AbstractEpollStreamChannel.this.new SpliceOutTask(this.ch, splicedIn, autoRead), splicePromise);
                  this.ch.unsafe().flush();
                  if (autoRead && !splicePromise.isDone()) {
                     AbstractEpollStreamChannel.this.config().setAutoRead(false);
                  }
               }

               return this.len == 0;
            } catch (Throwable cause) {
               this.promise.tryFailure(cause);
               return true;
            }
         }
      }
   }

   private final class SpliceOutTask {
      private final AbstractEpollStreamChannel ch;
      private final boolean autoRead;
      private int len;

      SpliceOutTask(AbstractEpollStreamChannel ch, int len, boolean autoRead) {
         this.ch = ch;
         this.len = len;
         this.autoRead = autoRead;
      }

      public boolean spliceOut() throws Exception {
         assert this.ch.eventLoop().inEventLoop();

         try {
            int splicedOut = Native.splice(this.ch.pipeIn.intValue(), -1L, this.ch.socket.intValue(), -1L, (long)this.len);
            this.len -= splicedOut;
            if (this.len == 0) {
               if (this.autoRead) {
                  AbstractEpollStreamChannel.this.config().setAutoRead(true);
               }

               return true;
            } else {
               return false;
            }
         } catch (IOException e) {
            if (this.autoRead) {
               AbstractEpollStreamChannel.this.config().setAutoRead(true);
            }

            throw e;
         }
      }
   }

   private final class SpliceFdTask extends SpliceInTask {
      private final FileDescriptor fd;
      private final ChannelPromise promise;
      private int offset;

      SpliceFdTask(FileDescriptor fd, int offset, int len, ChannelPromise promise) {
         super(len, promise);
         this.fd = fd;
         this.promise = promise;
         this.offset = offset;
      }

      public boolean spliceIn(RecvByteBufAllocator.Handle handle) {
         assert AbstractEpollStreamChannel.this.eventLoop().inEventLoop();

         if (this.len == 0) {
            this.promise.trySuccess();
            return true;
         } else {
            try {
               FileDescriptor[] pipe = FileDescriptor.pipe();
               FileDescriptor pipeIn = pipe[0];
               FileDescriptor pipeOut = pipe[1];

               try {
                  int splicedIn = this.spliceIn(pipeOut, handle);
                  if (splicedIn > 0) {
                     if (this.len != Integer.MAX_VALUE) {
                        this.len -= splicedIn;
                     }

                     do {
                        int splicedOut = Native.splice(pipeIn.intValue(), -1L, this.fd.intValue(), (long)this.offset, (long)splicedIn);
                        this.offset += splicedOut;
                        splicedIn -= splicedOut;
                     } while(splicedIn > 0);

                     if (this.len == 0) {
                        this.promise.trySuccess();
                        boolean var13 = true;
                        return var13;
                     }
                  }

                  boolean var12 = false;
                  return var12;
               } finally {
                  AbstractEpollStreamChannel.safeClosePipe(pipeIn);
                  AbstractEpollStreamChannel.safeClosePipe(pipeOut);
               }
            } catch (Throwable cause) {
               this.promise.tryFailure(cause);
               return true;
            }
         }
      }
   }

   private final class EpollSocketWritableByteChannel extends SocketWritableByteChannel {
      EpollSocketWritableByteChannel() {
         super(AbstractEpollStreamChannel.this.socket);

         assert this.fd == AbstractEpollStreamChannel.this.socket;

      }

      protected int write(ByteBuffer buf, int pos, int limit) throws IOException {
         return AbstractEpollStreamChannel.this.socket.send(buf, pos, limit);
      }

      protected ByteBufAllocator alloc() {
         return AbstractEpollStreamChannel.this.alloc();
      }
   }
}
