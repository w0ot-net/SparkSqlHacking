package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.impl.pool.Endpoint;
import io.vertx.core.spi.metrics.ClientMetrics;

abstract class ClientHttpEndpointBase extends Endpoint {
   private final ClientMetrics metrics;

   ClientHttpEndpointBase(ClientMetrics metrics, Runnable dispose) {
      super(dispose);
      this.metrics = metrics;
   }

   public final void requestConnection(ContextInternal ctx, long timeout, Handler handler) {
      if (this.metrics != null) {
         Object metric;
         if (this.metrics != null) {
            metric = this.metrics.enqueueRequest();
         } else {
            metric = null;
         }

         handler = (ar) -> {
            if (this.metrics != null) {
               this.metrics.dequeueRequest(metric);
            }

            handler.handle(ar);
         };
      }

      this.requestConnection2(ctx, timeout, handler);
   }

   protected abstract void requestConnection2(ContextInternal var1, long var2, Handler var4);

   abstract void checkExpired();

   protected void dispose() {
      if (this.metrics != null) {
         this.metrics.close();
      }

   }
}
