package io.vertx.core.net.impl.pool;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ConnectionManager {
   private final Map endpointMap = new ConcurrentHashMap();

   public void forEach(Consumer consumer) {
      this.endpointMap.values().forEach(consumer);
   }

   public void getConnection(ContextInternal ctx, Object key, EndpointProvider provider, Handler handler) {
      this.getConnection(ctx, key, provider, 0L, handler);
   }

   public void getConnection(ContextInternal ctx, Object key, EndpointProvider provider, long timeout, Handler handler) {
      Runnable dispose = () -> {
         Endpoint var10000 = (Endpoint)this.endpointMap.remove(key);
      };

      Endpoint<C> endpoint;
      do {
         endpoint = (Endpoint)this.endpointMap.computeIfAbsent(key, (k) -> provider.create(ctx, dispose));
      } while(!endpoint.getConnection(ctx, timeout, handler));

   }

   public void close() {
      for(Endpoint conn : this.endpointMap.values()) {
         conn.close();
      }

   }
}
