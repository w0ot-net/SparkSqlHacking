package io.vertx.core.net;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.impl.SocketAddressImpl;
import io.vertx.core.streams.ReadStream;

@VertxGen
public interface NetServer extends Measured {
   /** @deprecated */
   @Deprecated
   ReadStream connectStream();

   NetServer connectHandler(@Nullable Handler var1);

   @GenIgnore
   Handler connectHandler();

   Future listen();

   @Fluent
   default NetServer listen(Handler listenHandler) {
      Future<NetServer> fut = this.listen();
      if (listenHandler != null) {
         fut.onComplete(listenHandler);
      }

      return this;
   }

   default Future listen(int port, String host) {
      return this.listen((SocketAddress)(new SocketAddressImpl(port, host)));
   }

   @Fluent
   default NetServer listen(int port, String host, Handler listenHandler) {
      Future<NetServer> fut = this.listen(port, host);
      if (listenHandler != null) {
         fut.onComplete(listenHandler);
      }

      return this;
   }

   default Future listen(int port) {
      return this.listen(port, "0.0.0.0");
   }

   @Fluent
   default NetServer listen(int port, Handler listenHandler) {
      return this.listen(port, "0.0.0.0", listenHandler);
   }

   Future listen(SocketAddress var1);

   @Fluent
   default NetServer listen(SocketAddress localAddress, Handler listenHandler) {
      Future<NetServer> fut = this.listen(localAddress);
      if (listenHandler != null) {
         fut.onComplete(listenHandler);
      }

      return this;
   }

   @GenIgnore
   @Fluent
   NetServer exceptionHandler(Handler var1);

   Future close();

   void close(Handler var1);

   int actualPort();

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
}
