package io.vertx.core.net;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.http.impl.HttpUtils;
import io.vertx.core.net.impl.HostAndPortImpl;

@VertxGen
public interface HostAndPort {
   static HostAndPort create(String host, int port) {
      return new HostAndPortImpl(host, port);
   }

   static HostAndPort parseAuthority(String string, int schemePort) {
      return HostAndPortImpl.parseAuthority(string, schemePort);
   }

   static HostAndPort authority(String host, int port) {
      if (!HttpUtils.isValidHostAuthority(host)) {
         throw new IllegalArgumentException("Invalid authority host portion: " + host);
      } else {
         return new HostAndPortImpl(host, port);
      }
   }

   static HostAndPort authority(String host) {
      return authority(host, -1);
   }

   String host();

   int port();
}
