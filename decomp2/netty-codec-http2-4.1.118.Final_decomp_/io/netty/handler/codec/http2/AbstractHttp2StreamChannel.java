package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.VoidChannelPromise;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.ChannelOutputShutdownEvent;
import io.netty.handler.ssl.SslCloseCompletionEvent;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class AbstractHttp2StreamChannel extends DefaultAttributeMap implements Http2StreamChannel {
   static final Http2FrameStreamVisitor WRITABLE_VISITOR = new Http2FrameStreamVisitor() {
      public boolean visit(Http2FrameStream stream) {
         AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
         childChannel.trySetWritable();
         return true;
      }
   };
   static final Http2FrameStreamVisitor CHANNEL_INPUT_SHUTDOWN_READ_COMPLETE_VISITOR;
   static final Http2FrameStreamVisitor CHANNEL_OUTPUT_SHUTDOWN_EVENT_VISITOR;
   static final Http2FrameStreamVisitor SSL_CLOSE_COMPLETION_EVENT_VISITOR;
   private static final InternalLogger logger;
   private static final ChannelMetadata METADATA;
   private static final int MIN_HTTP2_FRAME_SIZE = 9;
   private static final AtomicLongFieldUpdater TOTAL_PENDING_SIZE_UPDATER;
   private static final AtomicIntegerFieldUpdater UNWRITABLE_UPDATER;
   private final ChannelFutureListener windowUpdateFrameWriteListener = new ChannelFutureListener() {
      public void operationComplete(ChannelFuture future) {
         AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(future, AbstractHttp2StreamChannel.this);
      }
   };
   private final Http2StreamChannelConfig config = new Http2StreamChannelConfig(this);
   private final Http2ChannelUnsafe unsafe = new Http2ChannelUnsafe();
   private final ChannelId channelId;
   private final ChannelPipeline pipeline;
   private final Http2FrameCodec.DefaultHttp2FrameStream stream;
   private final ChannelPromise closePromise;
   private volatile boolean registered;
   private volatile long totalPendingSize;
   private volatile int unwritable;
   private Runnable fireChannelWritabilityChangedTask;
   private boolean outboundClosed;
   private int flowControlledBytes;
   private ReadStatus readStatus;
   private Queue inboundBuffer;
   private boolean firstFrameWritten;
   private boolean readCompletePending;

   private static void windowUpdateFrameWriteComplete(ChannelFuture future, Channel streamChannel) {
      Throwable cause = future.cause();
      if (cause != null) {
         Throwable unwrappedCause;
         if (cause instanceof Http2FrameStreamException && (unwrappedCause = cause.getCause()) != null) {
            cause = unwrappedCause;
         }

         streamChannel.pipeline().fireExceptionCaught(cause);
         streamChannel.unsafe().close(streamChannel.unsafe().voidPromise());
      }

   }

   AbstractHttp2StreamChannel(Http2FrameCodec.DefaultHttp2FrameStream stream, int id, ChannelHandler inboundHandler) {
      this.readStatus = AbstractHttp2StreamChannel.ReadStatus.IDLE;
      this.stream = stream;
      stream.attachment = this;
      this.pipeline = new DefaultChannelPipeline(this) {
         protected void incrementPendingOutboundBytes(long size) {
            AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(size, true);
         }

         protected void decrementPendingOutboundBytes(long size) {
            AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(size, true);
         }

         protected void onUnhandledInboundException(Throwable cause) {
            if (cause instanceof Http2FrameStreamException) {
               AbstractHttp2StreamChannel.this.closeWithError(((Http2FrameStreamException)cause).error());
            } else {
               Http2Exception exception = Http2CodecUtil.getEmbeddedHttp2Exception(cause);
               if (exception != null) {
                  AbstractHttp2StreamChannel.this.closeWithError(exception.error());
               } else {
                  super.onUnhandledInboundException(cause);
               }
            }
         }
      };
      this.closePromise = this.pipeline.newPromise();
      this.channelId = new Http2StreamChannelId(this.parent().id(), id);
      if (inboundHandler != null) {
         this.pipeline.addLast(new ChannelHandler[]{inboundHandler});
      }

   }

   private void incrementPendingOutboundBytes(long size, boolean invokeLater) {
      if (size != 0L) {
         long newWriteBufferSize = TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, size);
         if (newWriteBufferSize > (long)this.config().getWriteBufferHighWaterMark()) {
            this.setUnwritable(invokeLater);
         }

      }
   }

   private void decrementPendingOutboundBytes(long size, boolean invokeLater) {
      if (size != 0L) {
         long newWriteBufferSize = TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, -size);
         if (newWriteBufferSize < (long)this.config().getWriteBufferLowWaterMark() && this.parent().isWritable()) {
            this.setWritable(invokeLater);
         }

      }
   }

   final void trySetWritable() {
      if (this.totalPendingSize < (long)this.config().getWriteBufferLowWaterMark()) {
         this.setWritable(false);
      }

   }

   private void setWritable(boolean invokeLater) {
      int oldValue;
      int newValue;
      do {
         oldValue = this.unwritable;
         newValue = oldValue & -2;
      } while(!UNWRITABLE_UPDATER.compareAndSet(this, oldValue, newValue));

      if (oldValue != 0 && newValue == 0) {
         this.fireChannelWritabilityChanged(invokeLater);
      }

   }

   private void setUnwritable(boolean invokeLater) {
      int oldValue;
      int newValue;
      do {
         oldValue = this.unwritable;
         newValue = oldValue | 1;
      } while(!UNWRITABLE_UPDATER.compareAndSet(this, oldValue, newValue));

      if (oldValue == 0) {
         this.fireChannelWritabilityChanged(invokeLater);
      }

   }

   private void fireChannelWritabilityChanged(boolean invokeLater) {
      final ChannelPipeline pipeline = this.pipeline();
      if (invokeLater) {
         Runnable task = this.fireChannelWritabilityChangedTask;
         if (task == null) {
            this.fireChannelWritabilityChangedTask = task = new Runnable() {
               public void run() {
                  pipeline.fireChannelWritabilityChanged();
               }
            };
         }

         this.eventLoop().execute(task);
      } else {
         pipeline.fireChannelWritabilityChanged();
      }

   }

   public Http2FrameStream stream() {
      return this.stream;
   }

   void closeOutbound() {
      this.outboundClosed = true;
   }

   void streamClosed() {
      this.unsafe.readEOS();
      this.unsafe.doBeginRead();
   }

   public ChannelMetadata metadata() {
      return METADATA;
   }

   public ChannelConfig config() {
      return this.config;
   }

   public boolean isOpen() {
      return !this.closePromise.isDone();
   }

   public boolean isActive() {
      return this.isOpen();
   }

   public boolean isWritable() {
      return this.unwritable == 0;
   }

   public ChannelId id() {
      return this.channelId;
   }

   public EventLoop eventLoop() {
      return this.parent().eventLoop();
   }

   public Channel parent() {
      return this.parentContext().channel();
   }

   public boolean isRegistered() {
      return this.registered;
   }

   public SocketAddress localAddress() {
      return this.parent().localAddress();
   }

   public SocketAddress remoteAddress() {
      return this.parent().remoteAddress();
   }

   public ChannelFuture closeFuture() {
      return this.closePromise;
   }

   public long bytesBeforeUnwritable() {
      long bytes = (long)this.config().getWriteBufferHighWaterMark() - this.totalPendingSize + 1L;
      return bytes > 0L && this.isWritable() ? bytes : 0L;
   }

   public long bytesBeforeWritable() {
      long bytes = this.totalPendingSize - (long)this.config().getWriteBufferLowWaterMark() + 1L;
      return bytes > 0L && !this.isWritable() ? bytes : 0L;
   }

   public Channel.Unsafe unsafe() {
      return this.unsafe;
   }

   public ChannelPipeline pipeline() {
      return this.pipeline;
   }

   public ByteBufAllocator alloc() {
      return this.config().getAllocator();
   }

   public Channel read() {
      this.pipeline().read();
      return this;
   }

   public Channel flush() {
      this.pipeline().flush();
      return this;
   }

   public ChannelFuture bind(SocketAddress localAddress) {
      return this.pipeline().bind(localAddress);
   }

   public ChannelFuture connect(SocketAddress remoteAddress) {
      return this.pipeline().connect(remoteAddress);
   }

   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
      return this.pipeline().connect(remoteAddress, localAddress);
   }

   public ChannelFuture disconnect() {
      return this.pipeline().disconnect();
   }

   public ChannelFuture close() {
      return this.pipeline().close();
   }

   public ChannelFuture deregister() {
      return this.pipeline().deregister();
   }

   public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
      return this.pipeline().bind(localAddress, promise);
   }

   public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
      return this.pipeline().connect(remoteAddress, promise);
   }

   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
      return this.pipeline().connect(remoteAddress, localAddress, promise);
   }

   public ChannelFuture disconnect(ChannelPromise promise) {
      return this.pipeline().disconnect(promise);
   }

   public ChannelFuture close(ChannelPromise promise) {
      return this.pipeline().close(promise);
   }

   public ChannelFuture deregister(ChannelPromise promise) {
      return this.pipeline().deregister(promise);
   }

   public ChannelFuture write(Object msg) {
      return this.pipeline().write(msg);
   }

   public ChannelFuture write(Object msg, ChannelPromise promise) {
      return this.pipeline().write(msg, promise);
   }

   public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
      return this.pipeline().writeAndFlush(msg, promise);
   }

   public ChannelFuture writeAndFlush(Object msg) {
      return this.pipeline().writeAndFlush(msg);
   }

   public ChannelPromise newPromise() {
      return this.pipeline().newPromise();
   }

   public ChannelProgressivePromise newProgressivePromise() {
      return this.pipeline().newProgressivePromise();
   }

   public ChannelFuture newSucceededFuture() {
      return this.pipeline().newSucceededFuture();
   }

   public ChannelFuture newFailedFuture(Throwable cause) {
      return this.pipeline().newFailedFuture(cause);
   }

   public ChannelPromise voidPromise() {
      return this.pipeline().voidPromise();
   }

   public int hashCode() {
      return this.id().hashCode();
   }

   public boolean equals(Object o) {
      return this == o;
   }

   public int compareTo(Channel o) {
      return this == o ? 0 : this.id().compareTo(o.id());
   }

   public String toString() {
      return this.parent().toString() + "(H2 - " + this.stream + ')';
   }

   void fireChildRead(Http2Frame frame) {
      assert this.eventLoop().inEventLoop();

      if (!this.isActive()) {
         ReferenceCountUtil.release(frame);
      } else if (this.readStatus != AbstractHttp2StreamChannel.ReadStatus.IDLE) {
         assert this.inboundBuffer == null || this.inboundBuffer.isEmpty();

         RecvByteBufAllocator.Handle allocHandle = this.unsafe.recvBufAllocHandle();
         this.unsafe.doRead0(frame, allocHandle);
         if (allocHandle.continueReading()) {
            this.maybeAddChannelToReadCompletePendingQueue();
         } else {
            this.unsafe.notifyReadComplete(allocHandle, true, false);
         }
      } else {
         if (this.inboundBuffer == null) {
            this.inboundBuffer = new ArrayDeque(4);
         }

         this.inboundBuffer.add(frame);
      }

   }

   void fireChildReadComplete() {
      assert this.eventLoop().inEventLoop();

      assert this.readStatus != AbstractHttp2StreamChannel.ReadStatus.IDLE || !this.readCompletePending;

      this.unsafe.notifyReadComplete(this.unsafe.recvBufAllocHandle(), false, false);
   }

   final void closeWithError(Http2Error error) {
      assert this.eventLoop().inEventLoop();

      this.unsafe.close(this.unsafe.voidPromise(), error);
   }

   private void maybeAddChannelToReadCompletePendingQueue() {
      if (!this.readCompletePending) {
         this.readCompletePending = true;
         this.addChannelToReadCompletePendingQueue();
      }

   }

   protected void flush0(ChannelHandlerContext ctx) {
      ctx.flush();
   }

   protected ChannelFuture write0(ChannelHandlerContext ctx, Object msg) {
      ChannelPromise promise = ctx.newPromise();
      ctx.write(msg, promise);
      return promise;
   }

   protected abstract boolean isParentReadInProgress();

   protected abstract void addChannelToReadCompletePendingQueue();

   protected abstract ChannelHandlerContext parentContext();

   static {
      CHANNEL_INPUT_SHUTDOWN_READ_COMPLETE_VISITOR = new UserEventStreamVisitor(ChannelInputShutdownReadComplete.INSTANCE);
      CHANNEL_OUTPUT_SHUTDOWN_EVENT_VISITOR = new UserEventStreamVisitor(ChannelOutputShutdownEvent.INSTANCE);
      SSL_CLOSE_COMPLETION_EVENT_VISITOR = new UserEventStreamVisitor(SslCloseCompletionEvent.SUCCESS);
      logger = InternalLoggerFactory.getInstance(AbstractHttp2StreamChannel.class);
      METADATA = new ChannelMetadata(false, 16);
      TOTAL_PENDING_SIZE_UPDATER = AtomicLongFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "totalPendingSize");
      UNWRITABLE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "unwritable");
   }

   private static final class UserEventStreamVisitor implements Http2FrameStreamVisitor {
      private final Object event;

      UserEventStreamVisitor(Object event) {
         this.event = ObjectUtil.checkNotNull(event, "event");
      }

      public boolean visit(Http2FrameStream stream) {
         AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
         childChannel.pipeline().fireUserEventTriggered(this.event);
         return true;
      }
   }

   private static final class FlowControlledFrameSizeEstimator implements MessageSizeEstimator {
      static final FlowControlledFrameSizeEstimator INSTANCE = new FlowControlledFrameSizeEstimator();
      private static final MessageSizeEstimator.Handle HANDLE_INSTANCE = new MessageSizeEstimator.Handle() {
         public int size(Object msg) {
            return msg instanceof Http2DataFrame ? (int)Math.min(2147483647L, (long)((Http2DataFrame)msg).initialFlowControlledBytes() + 9L) : 9;
         }
      };

      public MessageSizeEstimator.Handle newHandle() {
         return HANDLE_INSTANCE;
      }
   }

   private static enum ReadStatus {
      IDLE,
      IN_PROGRESS,
      REQUESTED;
   }

   private final class Http2ChannelUnsafe implements Channel.Unsafe {
      private final VoidChannelPromise unsafeVoidPromise;
      private RecvByteBufAllocator.Handle recvHandle;
      private boolean writeDoneAndNoFlush;
      private boolean closeInitiated;
      private boolean readEOS;
      private boolean receivedEndOfStream;
      private boolean sentEndOfStream;

      private Http2ChannelUnsafe() {
         this.unsafeVoidPromise = new VoidChannelPromise(AbstractHttp2StreamChannel.this, false);
      }

      public void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
         if (promise.setUncancellable()) {
            promise.setFailure(new UnsupportedOperationException());
         }
      }

      public RecvByteBufAllocator.Handle recvBufAllocHandle() {
         if (this.recvHandle == null) {
            this.recvHandle = AbstractHttp2StreamChannel.this.config().getRecvByteBufAllocator().newHandle();
            this.recvHandle.reset(AbstractHttp2StreamChannel.this.config());
         }

         return this.recvHandle;
      }

      public SocketAddress localAddress() {
         return AbstractHttp2StreamChannel.this.parent().unsafe().localAddress();
      }

      public SocketAddress remoteAddress() {
         return AbstractHttp2StreamChannel.this.parent().unsafe().remoteAddress();
      }

      public void register(EventLoop eventLoop, ChannelPromise promise) {
         if (promise.setUncancellable()) {
            if (AbstractHttp2StreamChannel.this.registered) {
               promise.setFailure(new UnsupportedOperationException("Re-register is not supported"));
            } else {
               AbstractHttp2StreamChannel.this.registered = true;
               promise.setSuccess();
               AbstractHttp2StreamChannel.this.pipeline().fireChannelRegistered();
               if (AbstractHttp2StreamChannel.this.isActive()) {
                  AbstractHttp2StreamChannel.this.pipeline().fireChannelActive();
               }

            }
         }
      }

      public void bind(SocketAddress localAddress, ChannelPromise promise) {
         if (promise.setUncancellable()) {
            promise.setFailure(new UnsupportedOperationException());
         }
      }

      public void disconnect(ChannelPromise promise) {
         this.close(promise);
      }

      public void close(ChannelPromise promise) {
         this.close(promise, Http2Error.CANCEL);
      }

      void close(final ChannelPromise promise, Http2Error error) {
         if (promise.setUncancellable()) {
            if (this.closeInitiated) {
               if (AbstractHttp2StreamChannel.this.closePromise.isDone()) {
                  promise.setSuccess();
               } else if (!(promise instanceof VoidChannelPromise)) {
                  AbstractHttp2StreamChannel.this.closePromise.addListener(new ChannelFutureListener() {
                     public void operationComplete(ChannelFuture future) {
                        promise.setSuccess();
                     }
                  });
               }

            } else {
               this.closeInitiated = true;
               AbstractHttp2StreamChannel.this.readCompletePending = false;
               boolean wasActive = AbstractHttp2StreamChannel.this.isActive();
               if (AbstractHttp2StreamChannel.this.parent().isActive() && Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream.id()) && !this.readEOS && (!this.receivedEndOfStream || !this.sentEndOfStream)) {
                  Http2StreamFrame resetFrame = (new DefaultHttp2ResetFrame(error)).stream(AbstractHttp2StreamChannel.this.stream());
                  this.write(resetFrame, AbstractHttp2StreamChannel.this.unsafe().voidPromise());
                  this.flush();
               }

               if (AbstractHttp2StreamChannel.this.inboundBuffer != null) {
                  while(true) {
                     Object msg = AbstractHttp2StreamChannel.this.inboundBuffer.poll();
                     if (msg == null) {
                        AbstractHttp2StreamChannel.this.inboundBuffer = null;
                        break;
                     }

                     ReferenceCountUtil.release(msg);
                  }
               }

               AbstractHttp2StreamChannel.this.outboundClosed = true;
               AbstractHttp2StreamChannel.this.closePromise.setSuccess();
               promise.setSuccess();
               this.fireChannelInactiveAndDeregister(this.voidPromise(), wasActive);
            }
         }
      }

      public void closeForcibly() {
         this.close(AbstractHttp2StreamChannel.this.unsafe().voidPromise());
      }

      public void deregister(ChannelPromise promise) {
         this.fireChannelInactiveAndDeregister(promise, false);
      }

      private void fireChannelInactiveAndDeregister(final ChannelPromise promise, final boolean fireChannelInactive) {
         if (promise.setUncancellable()) {
            if (!AbstractHttp2StreamChannel.this.registered) {
               promise.setSuccess();
            } else {
               this.invokeLater(new Runnable() {
                  public void run() {
                     if (fireChannelInactive) {
                        AbstractHttp2StreamChannel.this.pipeline.fireChannelInactive();
                     }

                     if (AbstractHttp2StreamChannel.this.registered) {
                        AbstractHttp2StreamChannel.this.registered = false;
                        AbstractHttp2StreamChannel.this.pipeline.fireChannelUnregistered();
                     }

                     Http2ChannelUnsafe.this.safeSetSuccess(promise);
                  }
               });
            }
         }
      }

      private void safeSetSuccess(ChannelPromise promise) {
         if (!(promise instanceof VoidChannelPromise) && !promise.trySuccess()) {
            AbstractHttp2StreamChannel.logger.warn("Failed to mark a promise as success because it is done already: {}", promise);
         }

      }

      private void invokeLater(Runnable task) {
         try {
            AbstractHttp2StreamChannel.this.eventLoop().execute(task);
         } catch (RejectedExecutionException e) {
            AbstractHttp2StreamChannel.logger.warn("Can't invoke task later as EventLoop rejected it", e);
         }

      }

      public void beginRead() {
         if (AbstractHttp2StreamChannel.this.isActive()) {
            this.updateLocalWindowIfNeeded();
            switch (AbstractHttp2StreamChannel.this.readStatus) {
               case IDLE:
                  AbstractHttp2StreamChannel.this.readStatus = AbstractHttp2StreamChannel.ReadStatus.IN_PROGRESS;
                  this.doBeginRead();
                  break;
               case IN_PROGRESS:
                  AbstractHttp2StreamChannel.this.readStatus = AbstractHttp2StreamChannel.ReadStatus.REQUESTED;
            }

         }
      }

      private Object pollQueuedMessage() {
         return AbstractHttp2StreamChannel.this.inboundBuffer == null ? null : AbstractHttp2StreamChannel.this.inboundBuffer.poll();
      }

      void doBeginRead() {
         if (AbstractHttp2StreamChannel.this.readStatus == AbstractHttp2StreamChannel.ReadStatus.IDLE) {
            if (this.readEOS && (AbstractHttp2StreamChannel.this.inboundBuffer == null || AbstractHttp2StreamChannel.this.inboundBuffer.isEmpty())) {
               this.flush();
               AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
            }
         } else {
            do {
               Object message = this.pollQueuedMessage();
               if (message == null) {
                  this.flush();
                  if (this.readEOS) {
                     AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
                  }
                  break;
               }

               RecvByteBufAllocator.Handle allocHandle = this.recvBufAllocHandle();
               allocHandle.reset(AbstractHttp2StreamChannel.this.config());
               boolean continueReading = false;

               do {
                  this.doRead0((Http2Frame)message, allocHandle);
               } while((this.readEOS || (continueReading = allocHandle.continueReading())) && (message = this.pollQueuedMessage()) != null);

               if (continueReading && AbstractHttp2StreamChannel.this.isParentReadInProgress() && !this.readEOS) {
                  AbstractHttp2StreamChannel.this.maybeAddChannelToReadCompletePendingQueue();
               } else {
                  this.notifyReadComplete(allocHandle, true, true);
                  this.resetReadStatus();
               }
            } while(AbstractHttp2StreamChannel.this.readStatus != AbstractHttp2StreamChannel.ReadStatus.IDLE);
         }

      }

      void readEOS() {
         this.readEOS = true;
      }

      private void updateLocalWindowIfNeeded() {
         if (AbstractHttp2StreamChannel.this.flowControlledBytes != 0 && !AbstractHttp2StreamChannel.this.parentContext().isRemoved()) {
            int bytes = AbstractHttp2StreamChannel.this.flowControlledBytes;
            AbstractHttp2StreamChannel.this.flowControlledBytes = 0;
            ChannelFuture future = AbstractHttp2StreamChannel.this.write0(AbstractHttp2StreamChannel.this.parentContext(), (new DefaultHttp2WindowUpdateFrame(bytes)).stream(AbstractHttp2StreamChannel.this.stream));
            this.writeDoneAndNoFlush = true;
            if (future.isDone()) {
               AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(future, AbstractHttp2StreamChannel.this);
            } else {
               future.addListener(AbstractHttp2StreamChannel.this.windowUpdateFrameWriteListener);
            }
         }

      }

      private void resetReadStatus() {
         AbstractHttp2StreamChannel.this.readStatus = AbstractHttp2StreamChannel.this.readStatus == AbstractHttp2StreamChannel.ReadStatus.REQUESTED ? AbstractHttp2StreamChannel.ReadStatus.IN_PROGRESS : AbstractHttp2StreamChannel.ReadStatus.IDLE;
      }

      void notifyReadComplete(RecvByteBufAllocator.Handle allocHandle, boolean forceReadComplete, boolean inReadLoop) {
         if (AbstractHttp2StreamChannel.this.readCompletePending || forceReadComplete) {
            AbstractHttp2StreamChannel.this.readCompletePending = false;
            if (!inReadLoop) {
               this.resetReadStatus();
            }

            allocHandle.readComplete();
            AbstractHttp2StreamChannel.this.pipeline().fireChannelReadComplete();
            this.flush();
            if (this.readEOS) {
               AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
            }

         }
      }

      void doRead0(Http2Frame frame, RecvByteBufAllocator.Handle allocHandle) {
         int bytes;
         if (frame instanceof Http2DataFrame) {
            bytes = ((Http2DataFrame)frame).initialFlowControlledBytes();
            AbstractHttp2StreamChannel.this.flowControlledBytes = AbstractHttp2StreamChannel.this.flowControlledBytes + bytes;
         } else {
            bytes = 9;
         }

         this.receivedEndOfStream |= this.isEndOfStream(frame);
         allocHandle.attemptedBytesRead(bytes);
         allocHandle.lastBytesRead(bytes);
         allocHandle.incMessagesRead(1);
         AbstractHttp2StreamChannel.this.pipeline().fireChannelRead(frame);
      }

      public void write(Object msg, ChannelPromise promise) {
         if (!promise.setUncancellable()) {
            ReferenceCountUtil.release(msg);
         } else if (AbstractHttp2StreamChannel.this.isActive() && (!AbstractHttp2StreamChannel.this.outboundClosed || !(msg instanceof Http2HeadersFrame) && !(msg instanceof Http2DataFrame))) {
            try {
               if (msg instanceof Http2StreamFrame) {
                  Http2StreamFrame frame = this.validateStreamFrame((Http2StreamFrame)msg).stream(AbstractHttp2StreamChannel.this.stream());
                  this.writeHttp2StreamFrame(frame, promise);
               } else {
                  String msgStr = msg.toString();
                  ReferenceCountUtil.release(msg);
                  promise.setFailure(new IllegalArgumentException("Message must be an " + StringUtil.simpleClassName(Http2StreamFrame.class) + ": " + msgStr));
               }
            } catch (Throwable t) {
               promise.tryFailure(t);
            }

         } else {
            ReferenceCountUtil.release(msg);
            promise.setFailure(new ClosedChannelException());
         }
      }

      private boolean isEndOfStream(Http2Frame frame) {
         if (frame instanceof Http2HeadersFrame) {
            return ((Http2HeadersFrame)frame).isEndStream();
         } else {
            return frame instanceof Http2DataFrame ? ((Http2DataFrame)frame).isEndStream() : false;
         }
      }

      private void writeHttp2StreamFrame(Http2StreamFrame frame, final ChannelPromise promise) {
         if (!AbstractHttp2StreamChannel.this.firstFrameWritten && !Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream().id()) && !(frame instanceof Http2HeadersFrame)) {
            ReferenceCountUtil.release(frame);
            promise.setFailure(new IllegalArgumentException("The first frame must be a headers frame. Was: " + frame.name()));
         } else {
            final boolean firstWrite;
            if (AbstractHttp2StreamChannel.this.firstFrameWritten) {
               firstWrite = false;
            } else {
               firstWrite = AbstractHttp2StreamChannel.this.firstFrameWritten = true;
            }

            this.sentEndOfStream |= this.isEndOfStream(frame);
            ChannelFuture f = AbstractHttp2StreamChannel.this.write0(AbstractHttp2StreamChannel.this.parentContext(), frame);
            if (f.isDone()) {
               if (firstWrite) {
                  this.firstWriteComplete(f, promise);
               } else {
                  this.writeComplete(f, promise);
               }
            } else {
               final long bytes = (long)AbstractHttp2StreamChannel.FlowControlledFrameSizeEstimator.HANDLE_INSTANCE.size(frame);
               AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(bytes, false);
               f.addListener(new ChannelFutureListener() {
                  public void operationComplete(ChannelFuture future) {
                     if (firstWrite) {
                        Http2ChannelUnsafe.this.firstWriteComplete(future, promise);
                     } else {
                        Http2ChannelUnsafe.this.writeComplete(future, promise);
                     }

                     AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(bytes, false);
                  }
               });
               this.writeDoneAndNoFlush = true;
            }

         }
      }

      private void firstWriteComplete(ChannelFuture future, ChannelPromise promise) {
         Throwable cause = future.cause();
         if (cause == null) {
            promise.setSuccess();
         } else {
            this.closeForcibly();
            promise.setFailure(this.wrapStreamClosedError(cause));
         }

      }

      private void writeComplete(ChannelFuture future, ChannelPromise promise) {
         Throwable cause = future.cause();
         if (cause == null) {
            promise.setSuccess();
         } else {
            Throwable error = this.wrapStreamClosedError(cause);
            if (error instanceof IOException) {
               if (AbstractHttp2StreamChannel.this.config.isAutoClose()) {
                  this.closeForcibly();
               } else {
                  AbstractHttp2StreamChannel.this.outboundClosed = true;
               }
            }

            promise.setFailure(error);
         }

      }

      private Throwable wrapStreamClosedError(Throwable cause) {
         return cause instanceof Http2Exception && ((Http2Exception)cause).error() == Http2Error.STREAM_CLOSED ? (new ClosedChannelException()).initCause(cause) : cause;
      }

      private Http2StreamFrame validateStreamFrame(Http2StreamFrame frame) {
         if (frame.stream() != null && frame.stream() != AbstractHttp2StreamChannel.this.stream) {
            String msgString = frame.toString();
            ReferenceCountUtil.release(frame);
            throw new IllegalArgumentException("Stream " + frame.stream() + " must not be set on the frame: " + msgString);
         } else {
            return frame;
         }
      }

      public void flush() {
         if (this.writeDoneAndNoFlush && !AbstractHttp2StreamChannel.this.isParentReadInProgress()) {
            this.writeDoneAndNoFlush = false;
            AbstractHttp2StreamChannel.this.flush0(AbstractHttp2StreamChannel.this.parentContext());
         }
      }

      public ChannelPromise voidPromise() {
         return this.unsafeVoidPromise;
      }

      public ChannelOutboundBuffer outboundBuffer() {
         return null;
      }
   }

   private static final class Http2StreamChannelConfig extends DefaultChannelConfig {
      Http2StreamChannelConfig(Channel channel) {
         super(channel);
      }

      public MessageSizeEstimator getMessageSizeEstimator() {
         return AbstractHttp2StreamChannel.FlowControlledFrameSizeEstimator.INSTANCE;
      }

      public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator estimator) {
         throw new UnsupportedOperationException();
      }

      public ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator allocator) {
         if (!(allocator.newHandle() instanceof RecvByteBufAllocator.ExtendedHandle)) {
            throw new IllegalArgumentException("allocator.newHandle() must return an object of type: " + RecvByteBufAllocator.ExtendedHandle.class);
         } else {
            super.setRecvByteBufAllocator(allocator);
            return this;
         }
      }
   }
}
