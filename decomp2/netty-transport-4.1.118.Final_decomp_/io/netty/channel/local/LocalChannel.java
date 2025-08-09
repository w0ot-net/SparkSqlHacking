package io.netty.channel.local;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.channel.PreferHeapByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class LocalChannel extends AbstractChannel {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(LocalChannel.class);
   private static final AtomicReferenceFieldUpdater FINISH_READ_FUTURE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(LocalChannel.class, Future.class, "finishReadFuture");
   private static final ChannelMetadata METADATA = new ChannelMetadata(false);
   private static final int MAX_READER_STACK_DEPTH = 8;
   private final ChannelConfig config = new DefaultChannelConfig(this);
   final Queue inboundBuffer = PlatformDependent.newSpscQueue();
   private final Runnable readTask = new Runnable() {
      public void run() {
         if (!LocalChannel.this.inboundBuffer.isEmpty()) {
            LocalChannel.this.readInbound();
         }

      }
   };
   private final Runnable shutdownHook = new Runnable() {
      public void run() {
         LocalChannel.this.unsafe().close(LocalChannel.this.unsafe().voidPromise());
      }
   };
   private volatile State state;
   private volatile LocalChannel peer;
   private volatile LocalAddress localAddress;
   private volatile LocalAddress remoteAddress;
   private volatile ChannelPromise connectPromise;
   private volatile boolean readInProgress;
   private volatile boolean writeInProgress;
   private volatile Future finishReadFuture;

   public LocalChannel() {
      super((Channel)null);
      this.config().setAllocator(new PreferHeapByteBufAllocator(this.config.getAllocator()));
   }

   protected LocalChannel(LocalServerChannel parent, LocalChannel peer) {
      super(parent);
      this.config().setAllocator(new PreferHeapByteBufAllocator(this.config.getAllocator()));
      this.peer = peer;
      this.localAddress = parent.localAddress();
      this.remoteAddress = peer.localAddress();
   }

   public ChannelMetadata metadata() {
      return METADATA;
   }

   public ChannelConfig config() {
      return this.config;
   }

   public LocalServerChannel parent() {
      return (LocalServerChannel)super.parent();
   }

   public LocalAddress localAddress() {
      return (LocalAddress)super.localAddress();
   }

   public LocalAddress remoteAddress() {
      return (LocalAddress)super.remoteAddress();
   }

   public boolean isOpen() {
      return this.state != LocalChannel.State.CLOSED;
   }

   public boolean isActive() {
      return this.state == LocalChannel.State.CONNECTED;
   }

   protected AbstractChannel.AbstractUnsafe newUnsafe() {
      return new LocalUnsafe();
   }

   protected boolean isCompatible(EventLoop loop) {
      return loop instanceof SingleThreadEventLoop;
   }

   protected SocketAddress localAddress0() {
      return this.localAddress;
   }

   protected SocketAddress remoteAddress0() {
      return this.remoteAddress;
   }

   protected void doRegister() throws Exception {
      if (this.peer != null && this.parent() != null) {
         final LocalChannel peer = this.peer;
         this.state = LocalChannel.State.CONNECTED;
         peer.remoteAddress = this.parent() == null ? null : this.parent().localAddress();
         peer.state = LocalChannel.State.CONNECTED;
         peer.eventLoop().execute(new Runnable() {
            public void run() {
               ChannelPromise promise = peer.connectPromise;
               if (promise != null && promise.trySuccess()) {
                  peer.pipeline().fireChannelActive();
               }

            }
         });
      }

      ((SingleThreadEventExecutor)this.eventLoop()).addShutdownHook(this.shutdownHook);
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
      this.localAddress = LocalChannelRegistry.register(this, this.localAddress, localAddress);
      this.state = LocalChannel.State.BOUND;
   }

   protected void doDisconnect() throws Exception {
      this.doClose();
   }

   protected void doClose() throws Exception {
      final LocalChannel peer = this.peer;
      State oldState = this.state;

      try {
         if (oldState != LocalChannel.State.CLOSED) {
            if (this.localAddress != null) {
               if (this.parent() == null) {
                  LocalChannelRegistry.unregister(this.localAddress);
               }

               this.localAddress = null;
            }

            this.state = LocalChannel.State.CLOSED;
            if (this.writeInProgress && peer != null) {
               this.finishPeerRead(peer);
            }

            ChannelPromise promise = this.connectPromise;
            if (promise != null) {
               promise.tryFailure(new ClosedChannelException());
               this.connectPromise = null;
            }
         }

         if (peer != null) {
            this.peer = null;
            EventLoop peerEventLoop = peer.eventLoop();
            final boolean peerIsActive = peer.isActive();

            try {
               peerEventLoop.execute(new Runnable() {
                  public void run() {
                     peer.tryClose(peerIsActive);
                  }
               });
            } catch (Throwable cause) {
               logger.warn("Releasing Inbound Queues for channels {}-{} because exception occurred!", new Object[]{this, peer, cause});
               if (peerEventLoop.inEventLoop()) {
                  peer.releaseInboundBuffers();
               } else {
                  peer.close();
               }

               PlatformDependent.throwException(cause);
            }
         }
      } finally {
         if (oldState != null && oldState != LocalChannel.State.CLOSED) {
            this.releaseInboundBuffers();
         }

      }

   }

   private void tryClose(boolean isActive) {
      if (isActive) {
         this.unsafe().close(this.unsafe().voidPromise());
      } else {
         this.releaseInboundBuffers();
      }

   }

   protected void doDeregister() throws Exception {
      ((SingleThreadEventExecutor)this.eventLoop()).removeShutdownHook(this.shutdownHook);
   }

   private void readInbound() {
      RecvByteBufAllocator.Handle handle = this.unsafe().recvBufAllocHandle();
      handle.reset(this.config());
      ChannelPipeline pipeline = this.pipeline();

      do {
         Object received = this.inboundBuffer.poll();
         if (received == null) {
            break;
         }

         if (received instanceof ByteBuf && this.inboundBuffer.peek() instanceof ByteBuf) {
            ByteBuf msg = (ByteBuf)received;
            ByteBuf output = handle.allocate(this.alloc());
            if (msg.readableBytes() < output.writableBytes()) {
               output.writeBytes(msg, msg.readerIndex(), msg.readableBytes());
               msg.release();

               while((received = this.inboundBuffer.peek()) instanceof ByteBuf && ((ByteBuf)received).readableBytes() < output.writableBytes()) {
                  this.inboundBuffer.poll();
                  msg = (ByteBuf)received;
                  output.writeBytes(msg, msg.readerIndex(), msg.readableBytes());
                  msg.release();
               }

               handle.lastBytesRead(output.readableBytes());
               received = output;
            } else {
               handle.lastBytesRead(output.capacity());
               output.release();
            }
         }

         handle.incMessagesRead(1);
         pipeline.fireChannelRead(received);
      } while(handle.continueReading());

      handle.readComplete();
      pipeline.fireChannelReadComplete();
   }

   protected void doBeginRead() throws Exception {
      if (!this.readInProgress) {
         Queue<Object> inboundBuffer = this.inboundBuffer;
         if (inboundBuffer.isEmpty()) {
            this.readInProgress = true;
         } else {
            InternalThreadLocalMap threadLocals = InternalThreadLocalMap.get();
            int stackDepth = threadLocals.localChannelReaderStackDepth();
            if (stackDepth < 8) {
               threadLocals.setLocalChannelReaderStackDepth(stackDepth + 1);

               try {
                  this.readInbound();
               } finally {
                  threadLocals.setLocalChannelReaderStackDepth(stackDepth);
               }
            } else {
               try {
                  this.eventLoop().execute(this.readTask);
               } catch (Throwable cause) {
                  logger.warn("Closing Local channels {}-{} because exception occurred!", new Object[]{this, this.peer, cause});
                  this.close();
                  this.peer.close();
                  PlatformDependent.throwException(cause);
               }
            }

         }
      }
   }

   protected void doWrite(ChannelOutboundBuffer in) throws Exception {
      switch (this.state) {
         case OPEN:
         case BOUND:
            throw new NotYetConnectedException();
         case CLOSED:
            throw new ClosedChannelException();
         case CONNECTED:
         default:
            LocalChannel peer = this.peer;
            this.writeInProgress = true;

            try {
               ClosedChannelException exception = null;

               while(true) {
                  Object msg = in.current();
                  if (msg == null) {
                     break;
                  }

                  try {
                     if (peer.state == LocalChannel.State.CONNECTED) {
                        peer.inboundBuffer.add(ReferenceCountUtil.retain(msg));
                        in.remove();
                     } else {
                        if (exception == null) {
                           exception = new ClosedChannelException();
                        }

                        in.remove(exception);
                     }
                  } catch (Throwable cause) {
                     in.remove(cause);
                  }
               }
            } finally {
               this.writeInProgress = false;
            }

            this.finishPeerRead(peer);
      }
   }

   private void finishPeerRead(LocalChannel peer) {
      if (peer.eventLoop() == this.eventLoop() && !peer.writeInProgress) {
         this.finishPeerRead0(peer);
      } else {
         this.runFinishPeerReadTask(peer);
      }

   }

   private void runFinishPeerReadTask(final LocalChannel peer) {
      Runnable finishPeerReadTask = new Runnable() {
         public void run() {
            LocalChannel.this.finishPeerRead0(peer);
         }
      };

      try {
         if (peer.writeInProgress) {
            peer.finishReadFuture = peer.eventLoop().submit(finishPeerReadTask);
         } else {
            peer.eventLoop().execute(finishPeerReadTask);
         }
      } catch (Throwable cause) {
         logger.warn("Closing Local channels {}-{} because exception occurred!", new Object[]{this, peer, cause});
         this.close();
         peer.close();
         PlatformDependent.throwException(cause);
      }

   }

   private void releaseInboundBuffers() {
      assert this.eventLoop() == null || this.eventLoop().inEventLoop();

      this.readInProgress = false;
      Queue<Object> inboundBuffer = this.inboundBuffer;

      Object msg;
      while((msg = inboundBuffer.poll()) != null) {
         ReferenceCountUtil.release(msg);
      }

   }

   private void finishPeerRead0(LocalChannel peer) {
      Future<?> peerFinishReadFuture = peer.finishReadFuture;
      if (peerFinishReadFuture != null) {
         if (!peerFinishReadFuture.isDone()) {
            this.runFinishPeerReadTask(peer);
            return;
         }

         FINISH_READ_FUTURE_UPDATER.compareAndSet(peer, peerFinishReadFuture, (Object)null);
      }

      if (peer.readInProgress && !peer.inboundBuffer.isEmpty()) {
         peer.readInProgress = false;
         peer.readInbound();
      }

   }

   private static enum State {
      OPEN,
      BOUND,
      CONNECTED,
      CLOSED;
   }

   private class LocalUnsafe extends AbstractChannel.AbstractUnsafe {
      private LocalUnsafe() {
      }

      public void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
         if (promise.setUncancellable() && this.ensureOpen(promise)) {
            if (LocalChannel.this.state == LocalChannel.State.CONNECTED) {
               Exception cause = new AlreadyConnectedException();
               this.safeSetFailure(promise, cause);
               LocalChannel.this.pipeline().fireExceptionCaught(cause);
            } else if (LocalChannel.this.connectPromise != null) {
               throw new ConnectionPendingException();
            } else {
               LocalChannel.this.connectPromise = promise;
               if (LocalChannel.this.state != LocalChannel.State.BOUND && localAddress == null) {
                  localAddress = new LocalAddress(LocalChannel.this);
               }

               if (localAddress != null) {
                  try {
                     LocalChannel.this.doBind(localAddress);
                  } catch (Throwable t) {
                     this.safeSetFailure(promise, t);
                     this.close(this.voidPromise());
                     return;
                  }
               }

               Channel boundChannel = LocalChannelRegistry.get(remoteAddress);
               if (!(boundChannel instanceof LocalServerChannel)) {
                  Exception cause = new ConnectException("connection refused: " + remoteAddress);
                  this.safeSetFailure(promise, cause);
                  this.close(this.voidPromise());
               } else {
                  LocalServerChannel serverChannel = (LocalServerChannel)boundChannel;
                  LocalChannel.this.peer = serverChannel.serve(LocalChannel.this);
               }
            }
         }
      }
   }
}
