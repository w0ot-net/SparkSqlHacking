package io.vertx.core.net;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.impl.Arguments;
import io.vertx.core.net.impl.SocketAddressImpl;
import java.net.InetSocketAddress;

@VertxGen
public interface SocketAddress {
   static SocketAddress sharedRandomPort(int id, String host) {
      if (id < 1) {
         throw new IllegalArgumentException("Shared random port ID " + id + " must be > 0");
      } else {
         return new SocketAddressImpl(-id, host);
      }
   }

   static SocketAddress inetSocketAddress(int port, String host) {
      Arguments.requireInRange(port, 0, 65535, "port p must be in range 0 <= p <= 65535");
      return new SocketAddressImpl(port, host);
   }

   static SocketAddress domainSocketAddress(String path) {
      return new SocketAddressImpl(path);
   }

   @GenIgnore({"permitted-type"})
   static SocketAddress inetSocketAddress(InetSocketAddress address) {
      return new SocketAddressImpl(address);
   }

   @CacheReturn
   String host();

   @CacheReturn
   String hostName();

   @CacheReturn
   String hostAddress();

   @CacheReturn
   int port();

   @CacheReturn
   String path();

   @CacheReturn
   boolean isInetSocket();

   @CacheReturn
   boolean isDomainSocket();
}
