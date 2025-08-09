package io.vertx.core.net.impl.pool;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;

public class PoolWaiter {
   static final Listener NULL_LISTENER = new Listener() {
   };
   final Listener listener;
   final ContextInternal context;
   final int capacity;
   final Handler handler;
   PoolWaiter prev;
   PoolWaiter next;
   boolean disposed;
   boolean queued;

   PoolWaiter(Listener listener, ContextInternal context, int capacity, Handler handler) {
      this.listener = listener;
      this.context = context;
      this.capacity = capacity;
      this.handler = handler;
   }

   public ContextInternal context() {
      return this.context;
   }

   public interface Listener {
      default void onEnqueue(PoolWaiter waiter) {
      }

      default void onConnect(PoolWaiter waiter) {
      }
   }
}
