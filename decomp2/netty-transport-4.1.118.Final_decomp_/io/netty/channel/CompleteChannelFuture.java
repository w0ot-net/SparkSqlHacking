package io.netty.channel;

import io.netty.util.concurrent.CompleteFuture;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.ObjectUtil;

abstract class CompleteChannelFuture extends CompleteFuture implements ChannelFuture {
   private final Channel channel;

   protected CompleteChannelFuture(Channel channel, EventExecutor executor) {
      super(executor);
      this.channel = (Channel)ObjectUtil.checkNotNull(channel, "channel");
   }

   protected EventExecutor executor() {
      EventExecutor e = super.executor();
      return (EventExecutor)(e == null ? this.channel().eventLoop() : e);
   }

   public ChannelFuture addListener(GenericFutureListener listener) {
      super.addListener(listener);
      return this;
   }

   public ChannelFuture addListeners(GenericFutureListener... listeners) {
      super.addListeners(listeners);
      return this;
   }

   public ChannelFuture removeListener(GenericFutureListener listener) {
      super.removeListener(listener);
      return this;
   }

   public ChannelFuture removeListeners(GenericFutureListener... listeners) {
      super.removeListeners(listeners);
      return this;
   }

   public ChannelFuture syncUninterruptibly() {
      return this;
   }

   public ChannelFuture sync() throws InterruptedException {
      return this;
   }

   public ChannelFuture await() throws InterruptedException {
      return this;
   }

   public ChannelFuture awaitUninterruptibly() {
      return this;
   }

   public Channel channel() {
      return this.channel;
   }

   public Void getNow() {
      return null;
   }

   public boolean isVoid() {
      return false;
   }
}
