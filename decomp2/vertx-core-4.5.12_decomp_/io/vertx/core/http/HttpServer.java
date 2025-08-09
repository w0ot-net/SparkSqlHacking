package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.TrafficShapingOptions;
import io.vertx.core.net.impl.SocketAddressImpl;
import io.vertx.core.streams.ReadStream;

@VertxGen
public interface HttpServer extends Measured {
   /** @deprecated */
   @Deprecated
   @CacheReturn
   ReadStream requestStream();

   @Fluent
   HttpServer requestHandler(Handler var1);

   @GenIgnore
   Handler requestHandler();

   HttpServer invalidRequestHandler(Handler var1);

   @Fluent
   HttpServer connectionHandler(Handler var1);

   @Fluent
   HttpServer exceptionHandler(Handler var1);

   /** @deprecated */
   @Deprecated
   @CacheReturn
   ReadStream webSocketStream();

   @Fluent
   HttpServer webSocketHandler(Handler var1);

   @Fluent
   HttpServer webSocketHandshakeHandler(Handler var1);

   @GenIgnore
   Handler webSocketHandler();

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

   void updateTrafficShapingOptions(TrafficShapingOptions var1);

   Future listen();

   default Future listen(int port, String host) {
      return this.listen((SocketAddress)(new SocketAddressImpl(port, host)));
   }

   @Fluent
   default HttpServer listen(int port, String host, Handler listenHandler) {
      Future<HttpServer> fut = this.listen(port, host);
      if (listenHandler != null) {
         fut.onComplete(listenHandler);
      }

      return this;
   }

   @Fluent
   default HttpServer listen(SocketAddress address, Handler listenHandler) {
      Future<HttpServer> fut = this.listen(address);
      if (listenHandler != null) {
         fut.onComplete(listenHandler);
      }

      return this;
   }

   Future listen(SocketAddress var1);

   default Future listen(int port) {
      return this.listen(port, "0.0.0.0");
   }

   @Fluent
   default HttpServer listen(int port, Handler listenHandler) {
      Future<HttpServer> fut = this.listen(port);
      if (listenHandler != null) {
         fut.onComplete(listenHandler);
      }

      return this;
   }

   @Fluent
   default HttpServer listen(Handler listenHandler) {
      Future<HttpServer> fut = this.listen();
      if (listenHandler != null) {
         fut.onComplete(listenHandler);
      }

      return this;
   }

   Future close();

   void close(Handler var1);

   int actualPort();
}
