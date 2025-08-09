package io.vertx.core.http;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.SSLOptions;
import java.util.List;
import java.util.function.Function;

@VertxGen
public interface HttpClient extends Measured {
   void request(RequestOptions var1, Handler var2);

   Future request(RequestOptions var1);

   void request(HttpMethod var1, int var2, String var3, String var4, Handler var5);

   default Future request(HttpMethod method, int port, String host, String requestURI) {
      return this.request((new RequestOptions()).setMethod(method).setPort(port).setHost(host).setURI(requestURI));
   }

   void request(HttpMethod var1, String var2, String var3, Handler var4);

   default Future request(HttpMethod method, String host, String requestURI) {
      return this.request((new RequestOptions()).setMethod(method).setHost(host).setURI(requestURI));
   }

   void request(HttpMethod var1, String var2, Handler var3);

   default Future request(HttpMethod method, String requestURI) {
      return this.request((new RequestOptions()).setMethod(method).setURI(requestURI));
   }

   /** @deprecated */
   @Deprecated
   void webSocket(int var1, String var2, String var3, Handler var4);

   /** @deprecated */
   @Deprecated
   Future webSocket(int var1, String var2, String var3);

   /** @deprecated */
   @Deprecated
   void webSocket(String var1, String var2, Handler var3);

   /** @deprecated */
   @Deprecated
   Future webSocket(String var1, String var2);

   /** @deprecated */
   @Deprecated
   void webSocket(String var1, Handler var2);

   /** @deprecated */
   @Deprecated
   Future webSocket(String var1);

   /** @deprecated */
   @Deprecated
   void webSocket(WebSocketConnectOptions var1, Handler var2);

   /** @deprecated */
   @Deprecated
   Future webSocket(WebSocketConnectOptions var1);

   /** @deprecated */
   @Deprecated
   void webSocketAbs(String var1, MultiMap var2, WebsocketVersion var3, List var4, Handler var5);

   /** @deprecated */
   @Deprecated
   Future webSocketAbs(String var1, MultiMap var2, WebsocketVersion var3, List var4);

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

   /** @deprecated */
   @Deprecated
   @Fluent
   HttpClient connectionHandler(Handler var1);

   /** @deprecated */
   @Deprecated
   @Fluent
   HttpClient redirectHandler(Function var1);

   /** @deprecated */
   @Deprecated
   @GenIgnore
   Function redirectHandler();

   void close(Handler var1);

   Future close();
}
