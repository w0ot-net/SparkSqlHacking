package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.spi.metrics.ClientMetrics;
import java.util.ArrayDeque;
import java.util.Deque;

class WebSocketEndpoint extends ClientHttpEndpointBase {
   private final int maxPoolSize;
   private final HttpChannelConnector connector;
   private final Deque waiters;
   private int inflightConnections;

   WebSocketEndpoint(ClientMetrics metrics, int maxPoolSize, HttpChannelConnector connector, Runnable dispose) {
      super(metrics, dispose);
      this.maxPoolSize = maxPoolSize;
      this.connector = connector;
      this.waiters = new ArrayDeque();
   }

   private void connect(ContextInternal ctx, final Handler handler) {
      ContextInternal eventLoopContext;
      if (ctx.isEventLoopContext()) {
         eventLoopContext = ctx;
      } else {
         eventLoopContext = ctx.owner().createEventLoopContext(ctx.nettyEventLoop(), ctx.workerPool(), ctx.classLoader());
      }

      class Listener implements Handler {
         public void handle(AsyncResult ar) {
            if (ar.succeeded()) {
               HttpClientConnection c = (HttpClientConnection)ar.result();
               if (WebSocketEndpoint.this.incRefCount()) {
                  c.evictionHandler((v) -> {
                     WebSocketEndpoint.this.decRefCount();
                     WebSocketEndpoint.this.release();
                  });
                  handler.handle(Future.succeededFuture(c));
               } else {
                  c.close();
                  handler.handle(Future.failedFuture("Connection closed"));
               }
            } else {
               WebSocketEndpoint.this.release();
               handler.handle(Future.failedFuture(ar.cause()));
            }

         }
      }

      this.connector.httpConnect(eventLoopContext, new Listener());
   }

   private void release() {
      Waiter h;
      synchronized(this) {
         if (--this.inflightConnections > this.maxPoolSize || this.waiters.isEmpty()) {
            return;
         }

         h = (Waiter)this.waiters.poll();
      }

      this.connect(h.context, h.handler);
   }

   private boolean tryAcquire(ContextInternal ctx, Handler handler) {
      synchronized(this) {
         if (this.inflightConnections >= this.maxPoolSize) {
            this.waiters.add(new Waiter(handler, ctx));
            return false;
         } else {
            ++this.inflightConnections;
            return true;
         }
      }
   }

   public void requestConnection2(ContextInternal ctx, long timeout, Handler handler) {
      if (this.tryAcquire(ctx, handler)) {
         this.connect(ctx, handler);
      }

   }

   void checkExpired() {
   }

   public void close() {
      super.close();
      synchronized(this) {
         this.waiters.forEach((waiter) -> waiter.context.runOnContext((v) -> waiter.handler.handle(Future.failedFuture("Closed"))));
         this.waiters.clear();
      }
   }

   private static class Waiter {
      final Handler handler;
      final ContextInternal context;

      Waiter(Handler handler, ContextInternal context) {
         this.handler = handler;
         this.context = context;
      }
   }
}
