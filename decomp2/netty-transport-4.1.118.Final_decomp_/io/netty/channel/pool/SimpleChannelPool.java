package io.netty.channel.pool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.util.Deque;
import java.util.concurrent.Callable;

public class SimpleChannelPool implements ChannelPool {
   private static final AttributeKey POOL_KEY = AttributeKey.newInstance("io.netty.channel.pool.SimpleChannelPool");
   private final Deque deque;
   private final ChannelPoolHandler handler;
   private final ChannelHealthChecker healthCheck;
   private final Bootstrap bootstrap;
   private final boolean releaseHealthCheck;
   private final boolean lastRecentUsed;

   public SimpleChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler) {
      this(bootstrap, handler, ChannelHealthChecker.ACTIVE);
   }

   public SimpleChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler, ChannelHealthChecker healthCheck) {
      this(bootstrap, handler, healthCheck, true);
   }

   public SimpleChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler, ChannelHealthChecker healthCheck, boolean releaseHealthCheck) {
      this(bootstrap, handler, healthCheck, releaseHealthCheck, true);
   }

   public SimpleChannelPool(Bootstrap bootstrap, final ChannelPoolHandler handler, ChannelHealthChecker healthCheck, boolean releaseHealthCheck, boolean lastRecentUsed) {
      this.deque = PlatformDependent.newConcurrentDeque();
      this.handler = (ChannelPoolHandler)ObjectUtil.checkNotNull(handler, "handler");
      this.healthCheck = (ChannelHealthChecker)ObjectUtil.checkNotNull(healthCheck, "healthCheck");
      this.releaseHealthCheck = releaseHealthCheck;
      this.bootstrap = ((Bootstrap)ObjectUtil.checkNotNull(bootstrap, "bootstrap")).clone();
      this.bootstrap.handler(new ChannelInitializer() {
         protected void initChannel(Channel ch) throws Exception {
            assert ch.eventLoop().inEventLoop();

            handler.channelCreated(ch);
         }
      });
      this.lastRecentUsed = lastRecentUsed;
   }

   protected Bootstrap bootstrap() {
      return this.bootstrap;
   }

   protected ChannelPoolHandler handler() {
      return this.handler;
   }

   protected ChannelHealthChecker healthChecker() {
      return this.healthCheck;
   }

   protected boolean releaseHealthCheck() {
      return this.releaseHealthCheck;
   }

   public final Future acquire() {
      return this.acquire(this.bootstrap.config().group().next().newPromise());
   }

   public Future acquire(Promise promise) {
      return this.acquireHealthyFromPoolOrNew((Promise)ObjectUtil.checkNotNull(promise, "promise"));
   }

   private Future acquireHealthyFromPoolOrNew(final Promise promise) {
      try {
         final Channel ch = this.pollChannel();
         if (ch == null) {
            Bootstrap bs = this.bootstrap.clone();
            bs.attr(POOL_KEY, this);
            ChannelFuture f = this.connectChannel(bs);
            if (f.isDone()) {
               this.notifyConnect(f, promise);
            } else {
               f.addListener(new ChannelFutureListener() {
                  public void operationComplete(ChannelFuture future) throws Exception {
                     SimpleChannelPool.this.notifyConnect(future, promise);
                  }
               });
            }
         } else {
            EventLoop loop = ch.eventLoop();
            if (loop.inEventLoop()) {
               this.doHealthCheck(ch, promise);
            } else {
               loop.execute(new Runnable() {
                  public void run() {
                     SimpleChannelPool.this.doHealthCheck(ch, promise);
                  }
               });
            }
         }
      } catch (Throwable cause) {
         promise.tryFailure(cause);
      }

      return promise;
   }

   private void notifyConnect(ChannelFuture future, Promise promise) {
      Channel channel = null;

      try {
         if (future.isSuccess()) {
            channel = future.channel();
            this.handler.channelAcquired(channel);
            if (!promise.trySuccess(channel)) {
               this.release(channel);
            }
         } else {
            promise.tryFailure(future.cause());
         }
      } catch (Throwable cause) {
         this.closeAndFail(channel, cause, promise);
      }

   }

   private void doHealthCheck(final Channel channel, final Promise promise) {
      try {
         assert channel.eventLoop().inEventLoop();

         Future<Boolean> f = this.healthCheck.isHealthy(channel);
         if (f.isDone()) {
            this.notifyHealthCheck(f, channel, promise);
         } else {
            f.addListener(new FutureListener() {
               public void operationComplete(Future future) {
                  SimpleChannelPool.this.notifyHealthCheck(future, channel, promise);
               }
            });
         }
      } catch (Throwable cause) {
         this.closeAndFail(channel, cause, promise);
      }

   }

   private void notifyHealthCheck(Future future, Channel channel, Promise promise) {
      try {
         assert channel.eventLoop().inEventLoop();

         if (future.isSuccess() && (Boolean)future.getNow()) {
            channel.attr(POOL_KEY).set(this);
            this.handler.channelAcquired(channel);
            promise.setSuccess(channel);
         } else {
            this.closeChannel(channel);
            this.acquireHealthyFromPoolOrNew(promise);
         }
      } catch (Throwable cause) {
         this.closeAndFail(channel, cause, promise);
      }

   }

   protected ChannelFuture connectChannel(Bootstrap bs) {
      return bs.connect();
   }

   public final Future release(Channel channel) {
      return this.release(channel, channel.eventLoop().newPromise());
   }

   public Future release(final Channel channel, final Promise promise) {
      try {
         ObjectUtil.checkNotNull(channel, "channel");
         ObjectUtil.checkNotNull(promise, "promise");
         EventLoop loop = channel.eventLoop();
         if (loop.inEventLoop()) {
            this.doReleaseChannel(channel, promise);
         } else {
            loop.execute(new Runnable() {
               public void run() {
                  SimpleChannelPool.this.doReleaseChannel(channel, promise);
               }
            });
         }
      } catch (Throwable cause) {
         this.closeAndFail(channel, cause, promise);
      }

      return promise;
   }

   private void doReleaseChannel(Channel channel, Promise promise) {
      try {
         assert channel.eventLoop().inEventLoop();

         if (channel.attr(POOL_KEY).getAndSet((Object)null) != this) {
            this.closeAndFail(channel, new IllegalArgumentException("Channel " + channel + " was not acquired from this ChannelPool"), promise);
         } else if (this.releaseHealthCheck) {
            this.doHealthCheckOnRelease(channel, promise);
         } else {
            this.releaseAndOffer(channel, promise);
         }
      } catch (Throwable cause) {
         this.closeAndFail(channel, cause, promise);
      }

   }

   private void doHealthCheckOnRelease(final Channel channel, final Promise promise) throws Exception {
      final Future<Boolean> f = this.healthCheck.isHealthy(channel);
      if (f.isDone()) {
         this.releaseAndOfferIfHealthy(channel, promise, f);
      } else {
         f.addListener(new FutureListener() {
            public void operationComplete(Future future) throws Exception {
               SimpleChannelPool.this.releaseAndOfferIfHealthy(channel, promise, f);
            }
         });
      }

   }

   private void releaseAndOfferIfHealthy(Channel channel, Promise promise, Future future) {
      try {
         if ((Boolean)future.getNow()) {
            this.releaseAndOffer(channel, promise);
         } else {
            this.handler.channelReleased(channel);
            promise.setSuccess((Object)null);
         }
      } catch (Throwable cause) {
         this.closeAndFail(channel, cause, promise);
      }

   }

   private void releaseAndOffer(Channel channel, Promise promise) throws Exception {
      if (this.offerChannel(channel)) {
         this.handler.channelReleased(channel);
         promise.setSuccess((Object)null);
      } else {
         this.closeAndFail(channel, new ChannelPoolFullException(), promise);
      }

   }

   private void closeChannel(Channel channel) throws Exception {
      channel.attr(POOL_KEY).getAndSet((Object)null);
      channel.close();
   }

   private void closeAndFail(Channel channel, Throwable cause, Promise promise) {
      if (channel != null) {
         try {
            this.closeChannel(channel);
         } catch (Throwable t) {
            promise.tryFailure(t);
         }
      }

      promise.tryFailure(cause);
   }

   protected Channel pollChannel() {
      return this.lastRecentUsed ? (Channel)this.deque.pollLast() : (Channel)this.deque.pollFirst();
   }

   protected boolean offerChannel(Channel channel) {
      return this.deque.offer(channel);
   }

   public void close() {
      while(true) {
         Channel channel = this.pollChannel();
         if (channel == null) {
            return;
         }

         channel.close().awaitUninterruptibly();
      }
   }

   public Future closeAsync() {
      return GlobalEventExecutor.INSTANCE.submit(new Callable() {
         public Void call() throws Exception {
            SimpleChannelPool.this.close();
            return null;
         }
      });
   }

   private static final class ChannelPoolFullException extends IllegalStateException {
      private ChannelPoolFullException() {
         super("ChannelPool full");
      }

      public Throwable fillInStackTrace() {
         return this;
      }
   }
}
