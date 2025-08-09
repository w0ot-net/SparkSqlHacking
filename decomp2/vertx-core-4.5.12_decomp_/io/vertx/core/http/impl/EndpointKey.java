package io.vertx.core.http.impl;

import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SocketAddress;
import java.util.Objects;

final class EndpointKey {
   final boolean ssl;
   final SocketAddress serverAddr;
   final SocketAddress peerAddr;
   final ProxyOptions proxyOptions;

   EndpointKey(boolean ssl, ProxyOptions proxyOptions, SocketAddress serverAddr, SocketAddress peerAddr) {
      if (serverAddr == null) {
         throw new NullPointerException("No null server address");
      } else if (peerAddr == null) {
         throw new NullPointerException("No null peer address");
      } else {
         this.ssl = ssl;
         this.proxyOptions = proxyOptions;
         this.peerAddr = peerAddr;
         this.serverAddr = serverAddr;
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         EndpointKey that = (EndpointKey)o;
         return this.ssl == that.ssl && this.serverAddr.equals(that.serverAddr) && this.peerAddr.equals(that.peerAddr) && equals(this.proxyOptions, that.proxyOptions);
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.ssl ? 1 : 0;
      result = 31 * result + this.peerAddr.hashCode();
      result = 31 * result + this.serverAddr.hashCode();
      if (this.proxyOptions != null) {
         result = 31 * result + hashCode(this.proxyOptions);
      }

      return result;
   }

   private static boolean equals(ProxyOptions options1, ProxyOptions options2) {
      if (options1 == options2) {
         return true;
      } else if (options1 != null && options2 != null) {
         return Objects.equals(options1.getHost(), options2.getHost()) && options1.getPort() == options2.getPort() && Objects.equals(options1.getUsername(), options2.getUsername()) && Objects.equals(options1.getPassword(), options2.getPassword());
      } else {
         return false;
      }
   }

   private static int hashCode(ProxyOptions options) {
      return options.getUsername() != null && options.getPassword() != null ? Objects.hash(new Object[]{options.getHost(), options.getPort(), options.getType(), options.getUsername(), options.getPassword()}) : Objects.hash(new Object[]{options.getHost(), options.getPort(), options.getType()});
   }
}
