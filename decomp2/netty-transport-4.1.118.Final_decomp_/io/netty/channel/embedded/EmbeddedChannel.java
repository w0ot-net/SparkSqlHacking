package io.netty.channel.embedded;

import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class EmbeddedChannel extends AbstractChannel {
   private static final SocketAddress LOCAL_ADDRESS = new EmbeddedSocketAddress();
   private static final SocketAddress REMOTE_ADDRESS = new EmbeddedSocketAddress();
   private static final ChannelHandler[] EMPTY_HANDLERS = new ChannelHandler[0];
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(EmbeddedChannel.class);
   private static final ChannelMetadata METADATA_NO_DISCONNECT = new ChannelMetadata(false);
   private static final ChannelMetadata METADATA_DISCONNECT = new ChannelMetadata(true);
   private final EmbeddedEventLoop loop;
   private final ChannelFutureListener recordExceptionListener;
   private final ChannelMetadata metadata;
   private final ChannelConfig config;
   private Queue inboundMessages;
   private Queue outboundMessages;
   private Throwable lastException;
   private State state;
   private int executingStackCnt;
   private boolean cancelRemainingScheduledTasks;

   public EmbeddedChannel() {
      this(EMPTY_HANDLERS);
   }

   public EmbeddedChannel(ChannelId channelId) {
      this(channelId, EMPTY_HANDLERS);
   }

   public EmbeddedChannel(ChannelHandler... handlers) {
      this(EmbeddedChannelId.INSTANCE, handlers);
   }

   public EmbeddedChannel(boolean hasDisconnect, ChannelHandler... handlers) {
      this(EmbeddedChannelId.INSTANCE, hasDisconnect, handlers);
   }

   public EmbeddedChannel(boolean register, boolean hasDisconnect, ChannelHandler... handlers) {
      this(EmbeddedChannelId.INSTANCE, register, hasDisconnect, handlers);
   }

   public EmbeddedChannel(ChannelId channelId, ChannelHandler... handlers) {
      this(channelId, false, handlers);
   }

   public EmbeddedChannel(ChannelId channelId, boolean hasDisconnect, ChannelHandler... handlers) {
      this(channelId, true, hasDisconnect, handlers);
   }

   public EmbeddedChannel(ChannelId channelId, boolean register, boolean hasDisconnect, ChannelHandler... handlers) {
      this((Channel)null, channelId, register, hasDisconnect, handlers);
   }

   public EmbeddedChannel(Channel parent, ChannelId channelId, boolean register, boolean hasDisconnect, ChannelHandler... handlers) {
      super(parent, channelId);
      this.loop = new EmbeddedEventLoop();
      this.recordExceptionListener = new ChannelFutureListener() {
         public void operationComplete(ChannelFuture future) throws Exception {
            EmbeddedChannel.this.recordException(future);
         }
      };
      this.metadata = metadata(hasDisconnect);
      this.config = new DefaultChannelConfig(this);
      this.setup(register, handlers);
   }

   public EmbeddedChannel(ChannelId channelId, boolean hasDisconnect, ChannelConfig config, ChannelHandler... handlers) {
      super((Channel)null, channelId);
      this.loop = new EmbeddedEventLoop();
      this.recordExceptionListener = new ChannelFutureListener() {
         public void operationComplete(ChannelFuture future) throws Exception {
            EmbeddedChannel.this.recordException(future);
         }
      };
      this.metadata = metadata(hasDisconnect);
      this.config = (ChannelConfig)ObjectUtil.checkNotNull(config, "config");
      this.setup(true, handlers);
   }

   private static ChannelMetadata metadata(boolean hasDisconnect) {
      return hasDisconnect ? METADATA_DISCONNECT : METADATA_NO_DISCONNECT;
   }

   private void setup(boolean register, final ChannelHandler... handlers) {
      ObjectUtil.checkNotNull(handlers, "handlers");
      ChannelPipeline p = this.pipeline();
      p.addLast(new ChannelInitializer() {
         protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            for(ChannelHandler h : handlers) {
               if (h == null) {
                  break;
               }

               pipeline.addLast(h);
            }

         }
      });
      if (register) {
         ChannelFuture future = this.loop.register((Channel)this);

         assert future.isDone();
      }

   }

   public void register() throws Exception {
      ChannelFuture future = this.loop.register((Channel)this);

      assert future.isDone();

      Throwable cause = future.cause();
      if (cause != null) {
         PlatformDependent.throwException(cause);
      }

   }

   protected final DefaultChannelPipeline newChannelPipeline() {
      return new EmbeddedChannelPipeline(this);
   }

   public ChannelMetadata metadata() {
      return this.metadata;
   }

   public ChannelConfig config() {
      return this.config;
   }

   public boolean isOpen() {
      return this.state != EmbeddedChannel.State.CLOSED;
   }

   public boolean isActive() {
      return this.state == EmbeddedChannel.State.ACTIVE;
   }

   public Queue inboundMessages() {
      if (this.inboundMessages == null) {
         this.inboundMessages = new ArrayDeque();
      }

      return this.inboundMessages;
   }

   /** @deprecated */
   @Deprecated
   public Queue lastInboundBuffer() {
      return this.inboundMessages();
   }

   public Queue outboundMessages() {
      if (this.outboundMessages == null) {
         this.outboundMessages = new ArrayDeque();
      }

      return this.outboundMessages;
   }

   /** @deprecated */
   @Deprecated
   public Queue lastOutboundBuffer() {
      return this.outboundMessages();
   }

   public Object readInbound() {
      T message = (T)poll(this.inboundMessages);
      if (message != null) {
         ReferenceCountUtil.touch(message, "Caller of readInbound() will handle the message from this point");
      }

      return message;
   }

   public Object readOutbound() {
      T message = (T)poll(this.outboundMessages);
      if (message != null) {
         ReferenceCountUtil.touch(message, "Caller of readOutbound() will handle the message from this point.");
      }

      return message;
   }

   public boolean writeInbound(Object... msgs) {
      this.ensureOpen();
      if (msgs.length == 0) {
         return isNotEmpty(this.inboundMessages);
      } else {
         ++this.executingStackCnt;

         try {
            ChannelPipeline p = this.pipeline();

            for(Object m : msgs) {
               p.fireChannelRead(m);
            }

            this.flushInbound(false, this.voidPromise());
         } finally {
            --this.executingStackCnt;
            this.maybeRunPendingTasks();
         }

         return isNotEmpty(this.inboundMessages);
      }
   }

   public ChannelFuture writeOneInbound(Object msg) {
      return this.writeOneInbound(msg, this.newPromise());
   }

   public ChannelFuture writeOneInbound(Object msg, ChannelPromise promise) {
      ++this.executingStackCnt;

      try {
         if (this.checkOpen(true)) {
            this.pipeline().fireChannelRead(msg);
         }
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return this.checkException(promise);
   }

   public EmbeddedChannel flushInbound() {
      this.flushInbound(true, this.voidPromise());
      return this;
   }

   private ChannelFuture flushInbound(boolean recordException, ChannelPromise promise) {
      ++this.executingStackCnt;

      try {
         if (this.checkOpen(recordException)) {
            this.pipeline().fireChannelReadComplete();
            this.runPendingTasks();
         }
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return this.checkException(promise);
   }

   public boolean writeOutbound(Object... msgs) {
      this.ensureOpen();
      if (msgs.length == 0) {
         return isNotEmpty(this.outboundMessages);
      } else {
         ++this.executingStackCnt;
         RecyclableArrayList futures = RecyclableArrayList.newInstance(msgs.length);

         int size;
         try {
            try {
               for(Object m : msgs) {
                  if (m == null) {
                     break;
                  }

                  futures.add(this.write(m));
               }

               this.flushOutbound0();
               size = futures.size();

               for(int i = 0; i < size; ++i) {
                  ChannelFuture future = (ChannelFuture)futures.get(i);
                  if (future.isDone()) {
                     this.recordException(future);
                  } else {
                     future.addListener(this.recordExceptionListener);
                  }
               }
            } finally {
               --this.executingStackCnt;
               this.maybeRunPendingTasks();
            }

            this.checkException();
            size = isNotEmpty(this.outboundMessages);
         } finally {
            futures.recycle();
         }

         return (boolean)size;
      }
   }

   public ChannelFuture writeOneOutbound(Object msg) {
      return this.writeOneOutbound(msg, this.newPromise());
   }

   public ChannelFuture writeOneOutbound(Object msg, ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture var3;
      try {
         if (!this.checkOpen(true)) {
            return this.checkException(promise);
         }

         var3 = this.write(msg, promise);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var3;
   }

   public EmbeddedChannel flushOutbound() {
      ++this.executingStackCnt;

      try {
         if (this.checkOpen(true)) {
            this.flushOutbound0();
         }
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      this.checkException(this.voidPromise());
      return this;
   }

   private void flushOutbound0() {
      this.runPendingTasks();
      this.flush();
   }

   public boolean finish() {
      return this.finish(false);
   }

   public boolean finishAndReleaseAll() {
      return this.finish(true);
   }

   private boolean finish(boolean releaseAll) {
      ++this.executingStackCnt;

      try {
         this.close();
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      boolean var2;
      try {
         this.checkException();
         var2 = isNotEmpty(this.inboundMessages) || isNotEmpty(this.outboundMessages);
      } finally {
         if (releaseAll) {
            releaseAll(this.inboundMessages);
            releaseAll(this.outboundMessages);
         }

      }

      return var2;
   }

   public boolean releaseInbound() {
      return releaseAll(this.inboundMessages);
   }

   public boolean releaseOutbound() {
      return releaseAll(this.outboundMessages);
   }

   private static boolean releaseAll(Queue queue) {
      if (!isNotEmpty(queue)) {
         return false;
      } else {
         while(true) {
            Object msg = queue.poll();
            if (msg == null) {
               return true;
            }

            ReferenceCountUtil.release(msg);
         }
      }
   }

   public final ChannelFuture close() {
      return this.close(this.newPromise());
   }

   public final ChannelFuture disconnect() {
      return this.disconnect(this.newPromise());
   }

   public final ChannelFuture close(ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture future;
      try {
         this.runPendingTasks();
         future = super.close(promise);
         this.cancelRemainingScheduledTasks = true;
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return future;
   }

   public final ChannelFuture disconnect(ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture future;
      try {
         future = super.disconnect(promise);
         if (!this.metadata.hasDisconnect()) {
            this.cancelRemainingScheduledTasks = true;
         }
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return future;
   }

   public ChannelFuture bind(SocketAddress localAddress) {
      ++this.executingStackCnt;

      ChannelFuture var2;
      try {
         var2 = super.bind(localAddress);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var2;
   }

   public ChannelFuture connect(SocketAddress remoteAddress) {
      ++this.executingStackCnt;

      ChannelFuture var2;
      try {
         var2 = super.connect(remoteAddress);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var2;
   }

   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
      ++this.executingStackCnt;

      ChannelFuture var3;
      try {
         var3 = super.connect(remoteAddress, localAddress);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var3;
   }

   public ChannelFuture deregister() {
      ++this.executingStackCnt;

      ChannelFuture var1;
      try {
         var1 = super.deregister();
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var1;
   }

   public Channel flush() {
      ++this.executingStackCnt;

      Channel var1;
      try {
         var1 = super.flush();
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var1;
   }

   public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture var3;
      try {
         var3 = super.bind(localAddress, promise);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var3;
   }

   public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture var3;
      try {
         var3 = super.connect(remoteAddress, promise);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var3;
   }

   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture var4;
      try {
         var4 = super.connect(remoteAddress, localAddress, promise);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var4;
   }

   public ChannelFuture deregister(ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture var2;
      try {
         var2 = super.deregister(promise);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var2;
   }

   public Channel read() {
      ++this.executingStackCnt;

      Channel var1;
      try {
         var1 = super.read();
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var1;
   }

   public ChannelFuture write(Object msg) {
      ++this.executingStackCnt;

      ChannelFuture var2;
      try {
         var2 = super.write(msg);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var2;
   }

   public ChannelFuture write(Object msg, ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture var3;
      try {
         var3 = super.write(msg, promise);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var3;
   }

   public ChannelFuture writeAndFlush(Object msg) {
      ++this.executingStackCnt;

      ChannelFuture var2;
      try {
         var2 = super.writeAndFlush(msg);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var2;
   }

   public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
      ++this.executingStackCnt;

      ChannelFuture var3;
      try {
         var3 = super.writeAndFlush(msg, promise);
      } finally {
         --this.executingStackCnt;
         this.maybeRunPendingTasks();
      }

      return var3;
   }

   private static boolean isNotEmpty(Queue queue) {
      return queue != null && !queue.isEmpty();
   }

   private static Object poll(Queue queue) {
      return queue != null ? queue.poll() : null;
   }

   private void maybeRunPendingTasks() {
      if (this.executingStackCnt == 0) {
         this.runPendingTasks();
         if (this.cancelRemainingScheduledTasks) {
            this.embeddedEventLoop().cancelScheduledTasks();
         }
      }

   }

   public void runPendingTasks() {
      try {
         this.embeddedEventLoop().runTasks();
      } catch (Exception e) {
         this.recordException((Throwable)e);
      }

      try {
         this.embeddedEventLoop().runScheduledTasks();
      } catch (Exception e) {
         this.recordException((Throwable)e);
      }

   }

   public boolean hasPendingTasks() {
      return this.embeddedEventLoop().hasPendingNormalTasks() || this.embeddedEventLoop().nextScheduledTask() == 0L;
   }

   public long runScheduledPendingTasks() {
      try {
         return this.embeddedEventLoop().runScheduledTasks();
      } catch (Exception e) {
         this.recordException((Throwable)e);
         return this.embeddedEventLoop().nextScheduledTask();
      }
   }

   private void recordException(ChannelFuture future) {
      if (!future.isSuccess()) {
         this.recordException(future.cause());
      }

   }

   private void recordException(Throwable cause) {
      if (this.lastException == null) {
         this.lastException = cause;
      } else {
         logger.warn("More than one exception was raised. Will report only the first one and log others.", cause);
      }

   }

   public void advanceTimeBy(long duration, TimeUnit unit) {
      this.embeddedEventLoop().advanceTimeBy(unit.toNanos(duration));
   }

   public void freezeTime() {
      this.embeddedEventLoop().freezeTime();
   }

   public void unfreezeTime() {
      this.embeddedEventLoop().unfreezeTime();
   }

   private ChannelFuture checkException(ChannelPromise promise) {
      Throwable t = this.lastException;
      if (t != null) {
         this.lastException = null;
         if (promise.isVoid()) {
            PlatformDependent.throwException(t);
         }

         return promise.setFailure(t);
      } else {
         return promise.setSuccess();
      }
   }

   public void checkException() {
      this.checkException(this.voidPromise());
   }

   private boolean checkOpen(boolean recordException) {
      if (!this.isOpen()) {
         if (recordException) {
            this.recordException((Throwable)(new ClosedChannelException()));
         }

         return false;
      } else {
         return true;
      }
   }

   private EmbeddedEventLoop embeddedEventLoop() {
      return this.isRegistered() ? (EmbeddedEventLoop)super.eventLoop() : this.loop;
   }

   protected final void ensureOpen() {
      if (!this.checkOpen(true)) {
         this.checkException();
      }

   }

   protected boolean isCompatible(EventLoop loop) {
      return loop instanceof EmbeddedEventLoop;
   }

   protected SocketAddress localAddress0() {
      return this.isActive() ? LOCAL_ADDRESS : null;
   }

   protected SocketAddress remoteAddress0() {
      return this.isActive() ? REMOTE_ADDRESS : null;
   }

   protected void doRegister() throws Exception {
      this.state = EmbeddedChannel.State.ACTIVE;
   }

   protected void doBind(SocketAddress localAddress) throws Exception {
   }

   protected void doDisconnect() throws Exception {
      if (!this.metadata.hasDisconnect()) {
         this.doClose();
      }

   }

   protected void doClose() throws Exception {
      this.state = EmbeddedChannel.State.CLOSED;
   }

   protected void doBeginRead() throws Exception {
   }

   protected AbstractChannel.AbstractUnsafe newUnsafe() {
      return new EmbeddedUnsafe();
   }

   public Channel.Unsafe unsafe() {
      return ((EmbeddedUnsafe)super.unsafe()).wrapped;
   }

   protected void doWrite(ChannelOutboundBuffer in) throws Exception {
      while(true) {
         Object msg = in.current();
         if (msg == null) {
            return;
         }

         ReferenceCountUtil.retain(msg);
         this.handleOutboundMessage(msg);
         in.remove();
      }
   }

   protected void handleOutboundMessage(Object msg) {
      this.outboundMessages().add(msg);
   }

   protected void handleInboundMessage(Object msg) {
      this.inboundMessages().add(msg);
   }

   private static enum State {
      OPEN,
      ACTIVE,
      CLOSED;
   }

   private final class EmbeddedUnsafe extends AbstractChannel.AbstractUnsafe {
      final Channel.Unsafe wrapped;

      private EmbeddedUnsafe() {
         this.wrapped = new Channel.Unsafe() {
            public RecvByteBufAllocator.Handle recvBufAllocHandle() {
               return EmbeddedUnsafe.this.recvBufAllocHandle();
            }

            public SocketAddress localAddress() {
               return EmbeddedUnsafe.this.localAddress();
            }

            public SocketAddress remoteAddress() {
               return EmbeddedUnsafe.this.remoteAddress();
            }

            public void register(EventLoop eventLoop, ChannelPromise promise) {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.register(eventLoop, promise);
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void bind(SocketAddress localAddress, ChannelPromise promise) {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.bind(localAddress, promise);
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.connect(remoteAddress, localAddress, promise);
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void disconnect(ChannelPromise promise) {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.disconnect(promise);
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void close(ChannelPromise promise) {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.close(promise);
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void closeForcibly() {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.closeForcibly();
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void deregister(ChannelPromise promise) {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.deregister(promise);
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void beginRead() {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.beginRead();
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void write(Object msg, ChannelPromise promise) {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.write(msg, promise);
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public void flush() {
               EmbeddedChannel.this.executingStackCnt++;

               try {
                  EmbeddedUnsafe.this.flush();
               } finally {
                  EmbeddedChannel.this.executingStackCnt--;
                  EmbeddedChannel.this.maybeRunPendingTasks();
               }

            }

            public ChannelPromise voidPromise() {
               return EmbeddedUnsafe.this.voidPromise();
            }

            public ChannelOutboundBuffer outboundBuffer() {
               return EmbeddedUnsafe.this.outboundBuffer();
            }
         };
      }

      public void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
         this.safeSetSuccess(promise);
      }
   }

   private final class EmbeddedChannelPipeline extends DefaultChannelPipeline {
      EmbeddedChannelPipeline(EmbeddedChannel channel) {
         super(channel);
      }

      protected void onUnhandledInboundException(Throwable cause) {
         EmbeddedChannel.this.recordException(cause);
      }

      protected void onUnhandledInboundMessage(ChannelHandlerContext ctx, Object msg) {
         EmbeddedChannel.this.handleInboundMessage(msg);
      }
   }
}
