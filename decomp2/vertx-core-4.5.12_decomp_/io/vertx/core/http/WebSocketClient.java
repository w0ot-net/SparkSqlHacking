package io.vertx.core.http;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.SSLOptions;

@VertxGen
public interface WebSocketClient extends Measured {
   ClientWebSocket webSocket();

   default void connect(int port, String host, String requestURI, Handler handler) {
      this.connect((new WebSocketConnectOptions()).setPort(port).setHost(host).setURI(requestURI), handler);
   }

   default Future connect(int port, String host, String requestURI) {
      return this.connect((new WebSocketConnectOptions()).setPort(port).setHost(host).setURI(requestURI));
   }

   default void connect(String host, String requestURI, Handler handler) {
      this.connect((new WebSocketConnectOptions()).setURI(requestURI).setHost(host), handler);
   }

   default Future connect(String host, String requestURI) {
      return this.connect((new WebSocketConnectOptions()).setURI(requestURI).setHost(host));
   }

   default void connect(String requestURI, Handler handler) {
      this.connect((new WebSocketConnectOptions()).setURI(requestURI), handler);
   }

   default Future connect(String requestURI) {
      return this.connect((new WebSocketConnectOptions()).setURI(requestURI));
   }

   void connect(WebSocketConnectOptions var1, Handler var2);

   Future connect(WebSocketConnectOptions var1);

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

   void close(Handler var1);

   Future close();
}
