package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.SocketAddress;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

@VertxGen
public interface ServerWebSocketHandshake {
   MultiMap headers();

   @Nullable String scheme();

   @Nullable HostAndPort authority();

   String uri();

   String path();

   @Nullable String query();

   Future accept();

   default Future reject() {
      return this.reject(502);
   }

   Future reject(int var1);

   @CacheReturn
   SocketAddress remoteAddress();

   @CacheReturn
   SocketAddress localAddress();

   boolean isSsl();

   @GenIgnore({"permitted-type"})
   SSLSession sslSession();

   @GenIgnore
   List peerCertificates() throws SSLPeerUnverifiedException;
}
