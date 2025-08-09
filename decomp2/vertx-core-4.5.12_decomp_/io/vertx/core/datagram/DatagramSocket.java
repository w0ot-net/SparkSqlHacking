package io.vertx.core.datagram;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

@VertxGen
public interface DatagramSocket extends ReadStream, Measured {
   @Fluent
   DatagramSocket send(Buffer var1, int var2, String var3, Handler var4);

   Future send(Buffer var1, int var2, String var3);

   WriteStream sender(int var1, String var2);

   @Fluent
   DatagramSocket send(String var1, int var2, String var3, Handler var4);

   Future send(String var1, int var2, String var3);

   @Fluent
   DatagramSocket send(String var1, String var2, int var3, String var4, Handler var5);

   Future send(String var1, String var2, int var3, String var4);

   void close(Handler var1);

   Future close();

   @CacheReturn
   SocketAddress localAddress();

   @Fluent
   DatagramSocket listenMulticastGroup(String var1, Handler var2);

   Future listenMulticastGroup(String var1);

   @Fluent
   DatagramSocket listenMulticastGroup(String var1, String var2, @Nullable String var3, Handler var4);

   Future listenMulticastGroup(String var1, String var2, @Nullable String var3);

   @Fluent
   DatagramSocket unlistenMulticastGroup(String var1, Handler var2);

   Future unlistenMulticastGroup(String var1);

   @Fluent
   DatagramSocket unlistenMulticastGroup(String var1, String var2, @Nullable String var3, Handler var4);

   Future unlistenMulticastGroup(String var1, String var2, @Nullable String var3);

   @Fluent
   DatagramSocket blockMulticastGroup(String var1, String var2, Handler var3);

   Future blockMulticastGroup(String var1, String var2);

   @Fluent
   DatagramSocket blockMulticastGroup(String var1, String var2, String var3, Handler var4);

   Future blockMulticastGroup(String var1, String var2, String var3);

   @Fluent
   DatagramSocket listen(int var1, String var2, Handler var3);

   Future listen(int var1, String var2);

   /** @deprecated */
   @Deprecated
   DatagramSocket pause();

   /** @deprecated */
   @Deprecated
   DatagramSocket resume();

   /** @deprecated */
   @Deprecated
   DatagramSocket fetch(long var1);

   /** @deprecated */
   @Deprecated
   DatagramSocket endHandler(Handler var1);

   DatagramSocket handler(Handler var1);

   DatagramSocket exceptionHandler(Handler var1);
}
