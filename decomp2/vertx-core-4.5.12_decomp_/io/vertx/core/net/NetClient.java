package io.vertx.core.net;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;

@VertxGen
public interface NetClient extends Measured {
   @Fluent
   NetClient connect(int var1, String var2, Handler var3);

   Future connect(int var1, String var2);

   @Fluent
   NetClient connect(int var1, String var2, String var3, Handler var4);

   Future connect(int var1, String var2, String var3);

   @Fluent
   NetClient connect(SocketAddress var1, Handler var2);

   Future connect(SocketAddress var1);

   @Fluent
   NetClient connect(SocketAddress var1, String var2, Handler var3);

   Future connect(SocketAddress var1, String var2);

   void close(Handler var1);

   Future close();

   default Future updateSSLOptions(SSLOptions options) {
      return this.updateSSLOptions(options, false);
   }

   default void updateSSLOptions(SSLOptions options, Handler handler) {
      Future<Boolean> fut = this.updateSSLOptions(options);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   Future updateSSLOptions(SSLOptions var1, boolean var2);

   default void updateSSLOptions(SSLOptions options, boolean force, Handler handler) {
      Future<Boolean> fut = this.updateSSLOptions(options, force);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }
}
