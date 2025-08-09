package io.vertx.core.impl;

import io.netty.channel.EventLoop;

public class EventLoopExecutor implements EventExecutor {
   private final EventLoop eventLoop;

   public EventLoopExecutor(EventLoop eventLoop) {
      this.eventLoop = eventLoop;
   }

   public boolean inThread() {
      return this.eventLoop.inEventLoop();
   }

   public void execute(Runnable command) {
      this.eventLoop.execute(command);
   }
}
