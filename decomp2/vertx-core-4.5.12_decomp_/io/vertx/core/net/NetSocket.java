package io.vertx.core.net;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen
public interface NetSocket extends ReadStream, WriteStream {
   NetSocket exceptionHandler(Handler var1);

   NetSocket handler(Handler var1);

   NetSocket pause();

   NetSocket resume();

   NetSocket fetch(long var1);

   NetSocket endHandler(Handler var1);

   NetSocket setWriteQueueMaxSize(int var1);

   NetSocket drainHandler(Handler var1);

   String writeHandlerID();

   void write(String var1, Handler var2);

   Future write(String var1);

   void write(String var1, String var2, Handler var3);

   Future write(String var1, String var2);

   void write(Buffer var1, Handler var2);

   default Future sendFile(String filename) {
      return this.sendFile(filename, 0L, Long.MAX_VALUE);
   }

   default Future sendFile(String filename, long offset) {
      return this.sendFile(filename, offset, Long.MAX_VALUE);
   }

   Future sendFile(String var1, long var2, long var4);

   @Fluent
   default NetSocket sendFile(String filename, Handler resultHandler) {
      return this.sendFile(filename, 0L, Long.MAX_VALUE, resultHandler);
   }

   @Fluent
   default NetSocket sendFile(String filename, long offset, Handler resultHandler) {
      return this.sendFile(filename, offset, Long.MAX_VALUE, resultHandler);
   }

   @Fluent
   NetSocket sendFile(String var1, long var2, long var4, Handler var6);

   @CacheReturn
   SocketAddress remoteAddress();

   SocketAddress remoteAddress(boolean var1);

   @CacheReturn
   SocketAddress localAddress();

   SocketAddress localAddress(boolean var1);

   Future end();

   void end(Handler var1);

   Future close();

   void close(Handler var1);

   @Fluent
   NetSocket closeHandler(@Nullable Handler var1);

   @Fluent
   NetSocket upgradeToSsl(Handler var1);

   Future upgradeToSsl();

   @Fluent
   NetSocket upgradeToSsl(String var1, Handler var2);

   Future upgradeToSsl(String var1);

   boolean isSsl();

   @GenIgnore({"permitted-type"})
   SSLSession sslSession();

   /** @deprecated */
   @Deprecated
   @GenIgnore
   X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException;

   @GenIgnore
   List peerCertificates() throws SSLPeerUnverifiedException;

   String indicatedServerName();

   String applicationLayerProtocol();
}
