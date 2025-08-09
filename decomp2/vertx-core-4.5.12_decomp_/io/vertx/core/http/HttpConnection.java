package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.SocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

@VertxGen
public interface HttpConnection {
   default int getWindowSize() {
      return -1;
   }

   @Fluent
   default HttpConnection setWindowSize(int windowSize) {
      return this;
   }

   @Fluent
   default HttpConnection goAway(long errorCode) {
      return this.goAway(errorCode, -1);
   }

   @Fluent
   default HttpConnection goAway(long errorCode, int lastStreamId) {
      return this.goAway(errorCode, lastStreamId, (Buffer)null);
   }

   @Fluent
   HttpConnection goAway(long var1, int var3, Buffer var4);

   @Fluent
   HttpConnection goAwayHandler(@Nullable Handler var1);

   @Fluent
   HttpConnection shutdownHandler(@Nullable Handler var1);

   default void shutdown(Handler handler) {
      this.shutdown(30L, TimeUnit.SECONDS, handler);
   }

   default Future shutdown() {
      return this.shutdown(30L, TimeUnit.SECONDS);
   }

   /** @deprecated */
   @Deprecated
   default void shutdown(long timeout, Handler handler) {
      this.shutdown(timeout, TimeUnit.MILLISECONDS, handler);
   }

   /** @deprecated */
   @Deprecated
   default Future shutdown(long timeoutMs) {
      return this.shutdown(timeoutMs, TimeUnit.MILLISECONDS);
   }

   default void shutdown(long timeout, TimeUnit unit, Handler handler) {
      Future<Void> fut = this.shutdown(timeout, unit);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   Future shutdown(long var1, TimeUnit var3);

   @Fluent
   HttpConnection closeHandler(Handler var1);

   Future close();

   default void close(Handler handler) {
      this.close().onComplete(handler);
   }

   Http2Settings settings();

   Future updateSettings(Http2Settings var1);

   @Fluent
   HttpConnection updateSettings(Http2Settings var1, Handler var2);

   Http2Settings remoteSettings();

   @Fluent
   HttpConnection remoteSettingsHandler(Handler var1);

   @Fluent
   HttpConnection ping(Buffer var1, Handler var2);

   Future ping(Buffer var1);

   @Fluent
   HttpConnection pingHandler(@Nullable Handler var1);

   @Fluent
   HttpConnection exceptionHandler(Handler var1);

   @CacheReturn
   SocketAddress remoteAddress();

   SocketAddress remoteAddress(boolean var1);

   @CacheReturn
   SocketAddress localAddress();

   SocketAddress localAddress(boolean var1);

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
}
