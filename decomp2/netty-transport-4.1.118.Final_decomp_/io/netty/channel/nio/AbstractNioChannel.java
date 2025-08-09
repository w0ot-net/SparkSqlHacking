package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.EventLoop;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.Future;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.TimeUnit;

public abstract class AbstractNioChannel extends AbstractChannel {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(AbstractNioChannel.class);
   private final SelectableChannel ch;
   protected final int readInterestOp;
   volatile SelectionKey selectionKey;
   boolean readPending;
   private final Runnable clearReadPendingRunnable = new Runnable() {
      public void run() {
         AbstractNioChannel.this.clearReadPending0();
      }
   };
   private ChannelPromise connectPromise;
   private Future connectTimeoutFuture;
   private SocketAddress requestedRemoteAddress;

   protected AbstractNioChannel(Channel parent, SelectableChannel ch, int readInterestOp) {
      super(parent);
      this.ch = ch;
      this.readInterestOp = readInterestOp;

      try {
         ch.configureBlocking(false);
      } catch (IOException e) {
         try {
            ch.close();
         } catch (IOException e2) {
            logger.warn("Failed to close a partially initialized socket.", e2);
         }

         throw new ChannelException("Failed to enter non-blocking mode.", e);
      }
   }

   public boolean isOpen() {
      return this.ch.isOpen();
   }

   public NioUnsafe unsafe() {
      return (NioUnsafe)super.unsafe();
   }

   protected SelectableChannel javaChannel() {
      return this.ch;
   }

   public NioEventLoop eventLoop() {
      return (NioEventLoop)super.eventLoop();
   }

   protected SelectionKey selectionKey() {
      assert this.selectionKey != null;

      return this.selectionKey;
   }

   /** @deprecated */
   @Deprecated
   protected boolean isReadPending() {
      return this.readPending;
   }

   /** @deprecated */
   @Deprecated
   protected void setReadPending(final boolean readPending) {
      if (this.isRegistered()) {
         EventLoop eventLoop = this.eventLoop();
         if (eventLoop.inEventLoop()) {
            this.setReadPending0(readPending);
         } else {
            eventLoop.execute(new Runnable() {
               public void run() {
                  AbstractNioChannel.this.setReadPending0(readPending);
               }
            });
         }
      } else {
         this.readPending = readPending;
      }

   }

   protected final void clearReadPending() {
      if (this.isRegistered()) {
         EventLoop eventLoop = this.eventLoop();
         if (eventLoop.inEventLoop()) {
            this.clearReadPending0();
         } else {
            eventLoop.execute(this.clearReadPendingRunnable);
         }
      } else {
         this.readPending = false;
      }

   }

   private void setReadPending0(boolean readPending) {
      this.readPending = readPending;
      if (!readPending) {
         ((AbstractNioUnsafe)this.unsafe()).removeReadOp();
      }

   }

   private void clearReadPending0() {
      this.readPending = false;
      ((AbstractNioUnsafe)this.unsafe()).removeReadOp();
   }

   protected boolean isCompatible(EventLoop loop) {
      return loop instanceof NioEventLoop;
   }

   protected void doRegister() throws Exception {
      boolean selected = false;

      while(true) {
         try {
            this.selectionKey = this.javaChannel().register(this.eventLoop().unwrappedSelector(), 0, this);
            return;
         } catch (CancelledKeyException e) {
            if (selected) {
               throw e;
            }

            this.eventLoop().selectNow();
            selected = true;
         }
      }
   }

   protected void doDeregister() throws Exception {
      this.eventLoop().cancel(this.selectionKey());
   }

   protected void doBeginRead() throws Exception {
      SelectionKey selectionKey = this.selectionKey;
      if (selectionKey.isValid()) {
         this.readPending = true;
         int interestOps = selectionKey.interestOps();
         if ((interestOps & this.readInterestOp) == 0) {
            selectionKey.interestOps(interestOps | this.readInterestOp);
         }

      }
   }

   protected abstract boolean doConnect(SocketAddress var1, SocketAddress var2) throws Exception;

   protected abstract void doFinishConnect() throws Exception;

   protected final ByteBuf newDirectBuffer(ByteBuf buf) {
      int readableBytes = buf.readableBytes();
      if (readableBytes == 0) {
         ReferenceCountUtil.safeRelease(buf);
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBufAllocator alloc = this.alloc();
         if (alloc.isDirectBufferPooled()) {
            ByteBuf directBuf = alloc.directBuffer(readableBytes);
            directBuf.writeBytes(buf, buf.readerIndex(), readableBytes);
            ReferenceCountUtil.safeRelease(buf);
            return directBuf;
         } else {
            ByteBuf directBuf = ByteBufUtil.threadLocalDirectBuffer();
            if (directBuf != null) {
               directBuf.writeBytes(buf, buf.readerIndex(), readableBytes);
               ReferenceCountUtil.safeRelease(buf);
               return directBuf;
            } else {
               return buf;
            }
         }
      }
   }

   protected final ByteBuf newDirectBuffer(ReferenceCounted holder, ByteBuf buf) {
      int readableBytes = buf.readableBytes();
      if (readableBytes == 0) {
         ReferenceCountUtil.safeRelease(holder);
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBufAllocator alloc = this.alloc();
         if (alloc.isDirectBufferPooled()) {
            ByteBuf directBuf = alloc.directBuffer(readableBytes);
            directBuf.writeBytes(buf, buf.readerIndex(), readableBytes);
            ReferenceCountUtil.safeRelease(holder);
            return directBuf;
         } else {
            ByteBuf directBuf = ByteBufUtil.threadLocalDirectBuffer();
            if (directBuf != null) {
               directBuf.writeBytes(buf, buf.readerIndex(), readableBytes);
               ReferenceCountUtil.safeRelease(holder);
               return directBuf;
            } else {
               if (holder != buf) {
                  buf.retain();
                  ReferenceCountUtil.safeRelease(holder);
               }

               return buf;
            }
         }
      }
   }

   protected void doClose() throws Exception {
      ChannelPromise promise = this.connectPromise;
      if (promise != null) {
         promise.tryFailure(new ClosedChannelException());
         this.connectPromise = null;
      }

      Future<?> future = this.connectTimeoutFuture;
      if (future != null) {
         future.cancel(false);
         this.connectTimeoutFuture = null;
      }

   }

   protected abstract class AbstractNioUnsafe extends AbstractChannel.AbstractUnsafe implements NioUnsafe {
      protected final void removeReadOp() {
         SelectionKey key = AbstractNioChannel.this.selectionKey();
         if (key.isValid()) {
            int interestOps = key.interestOps();
            if ((interestOps & AbstractNioChannel.this.readInterestOp) != 0) {
               key.interestOps(interestOps & ~AbstractNioChannel.this.readInterestOp);
            }

         }
      }

      public final SelectableChannel ch() {
         return AbstractNioChannel.this.javaChannel();
      }

      public final void connect(final SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
         if (!promise.isDone() && this.ensureOpen(promise)) {
            try {
               if (AbstractNioChannel.this.connectPromise != null) {
                  throw new ConnectionPendingException();
               }

               boolean wasActive = AbstractNioChannel.this.isActive();
               if (AbstractNioChannel.this.doConnect(remoteAddress, localAddress)) {
                  this.fulfillConnectPromise(promise, wasActive);
               } else {
                  AbstractNioChannel.this.connectPromise = promise;
                  AbstractNioChannel.this.requestedRemoteAddress = remoteAddress;
                  final int connectTimeoutMillis = AbstractNioChannel.this.config().getConnectTimeoutMillis();
                  if (connectTimeoutMillis > 0) {
                     AbstractNioChannel.this.connectTimeoutFuture = AbstractNioChannel.this.eventLoop().schedule(new Runnable() {
                        public void run() {
                           ChannelPromise connectPromise = AbstractNioChannel.this.connectPromise;
                           if (connectPromise != null && !connectPromise.isDone() && connectPromise.tryFailure(new ConnectTimeoutException("connection timed out after " + connectTimeoutMillis + " ms: " + remoteAddress))) {
                              AbstractNioUnsafe.this.close(AbstractNioUnsafe.this.voidPromise());
                           }

                        }
                     }, (long)connectTimeoutMillis, TimeUnit.MILLISECONDS);
                  }

                  promise.addListener(new ChannelFutureListener() {
                     public void operationComplete(ChannelFuture future) {
                        if (future.isCancelled()) {
                           if (AbstractNioChannel.this.connectTimeoutFuture != null) {
                              AbstractNioChannel.this.connectTimeoutFuture.cancel(false);
                           }

                           AbstractNioChannel.this.connectPromise = null;
                           AbstractNioUnsafe.this.close(AbstractNioUnsafe.this.voidPromise());
                        }

                     }
                  });
               }
            } catch (Throwable t) {
               promise.tryFailure(this.annotateConnectException(t, remoteAddress));
               this.closeIfClosed();
            }

         }
      }

      private void fulfillConnectPromise(ChannelPromise promise, boolean wasActive) {
         if (promise != null) {
            boolean active = AbstractNioChannel.this.isActive();
            boolean promiseSet = promise.trySuccess();
            if (!wasActive && active) {
               AbstractNioChannel.this.pipeline().fireChannelActive();
            }

            if (!promiseSet) {
               this.close(this.voidPromise());
            }

         }
      }

      private void fulfillConnectPromise(ChannelPromise promise, Throwable cause) {
         if (promise != null) {
            promise.tryFailure(cause);
            this.closeIfClosed();
         }
      }

      public final void finishConnect() {
         assert AbstractNioChannel.this.eventLoop().inEventLoop();

         try {
            boolean wasActive = AbstractNioChannel.this.isActive();
            AbstractNioChannel.this.doFinishConnect();
            this.fulfillConnectPromise(AbstractNioChannel.this.connectPromise, wasActive);
         } catch (Throwable t) {
            this.fulfillConnectPromise(AbstractNioChannel.this.connectPromise, this.annotateConnectException(t, AbstractNioChannel.this.requestedRemoteAddress));
         } finally {
            if (AbstractNioChannel.this.connectTimeoutFuture != null) {
               AbstractNioChannel.this.connectTimeoutFuture.cancel(false);
            }

            AbstractNioChannel.this.connectPromise = null;
         }

      }

      protected final void flush0() {
         if (!this.isFlushPending()) {
            super.flush0();
         }

      }

      public final void forceFlush() {
         super.flush0();
      }

      private boolean isFlushPending() {
         SelectionKey selectionKey = AbstractNioChannel.this.selectionKey();
         return selectionKey.isValid() && (selectionKey.interestOps() & 4) != 0;
      }
   }

   public interface NioUnsafe extends Channel.Unsafe {
      SelectableChannel ch();

      void finishConnect();

      void read();

      void forceFlush();
   }
}
