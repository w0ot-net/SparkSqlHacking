package io.vertx.core.net.impl;

import io.netty.util.NetUtil;
import io.vertx.core.VertxException;
import io.vertx.core.impl.Arguments;
import io.vertx.core.net.SocketAddress;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class SocketAddressImpl implements SocketAddress {
   private final String host;
   private final String hostName;
   private final InetAddress ipAddress;
   private final int port;
   private final String path;

   public SocketAddressImpl(InetSocketAddress address) {
      Arguments.requireInRange(address.getPort(), 0, 65535, "port p must be in range 0 <= p <= 65535");
      this.path = null;
      this.port = address.getPort();
      this.host = address.getHostString();
      if (address.isUnresolved()) {
         this.hostName = address.getHostName();
         this.ipAddress = null;
      } else {
         String host = address.getHostString();
         if (NetUtil.isValidIpV4Address(host) || NetUtil.isValidIpV6Address(host)) {
            host = null;
         }

         this.hostName = host;
         this.ipAddress = address.getAddress();
      }

   }

   public SocketAddressImpl(int port, String host) {
      if (port > 65535) {
         throw new IllegalArgumentException("port p must be < 65535");
      } else {
         this.path = null;
         this.port = port;
         if (NetUtil.isValidIpV4Address(host)) {
            InetAddress ip;
            try {
               ip = InetAddress.getByAddress(NetUtil.createByteArrayFromIpAddressString(host));
            } catch (UnknownHostException e) {
               throw new VertxException(e);
            }

            this.host = host;
            this.hostName = null;
            this.ipAddress = ip;
         } else if (NetUtil.isValidIpV6Address(host)) {
            Inet6Address ip = NetUtil.getByName(host);
            this.host = host;
            this.hostName = null;
            this.ipAddress = ip;
         } else {
            Arguments.require(!host.isEmpty(), "host name must not be empty");
            this.host = host;
            this.hostName = host;
            this.ipAddress = null;
         }

      }
   }

   public SocketAddressImpl(String path) {
      Objects.requireNonNull(path, "domain socket path must be non null");
      this.port = -1;
      this.host = null;
      this.ipAddress = null;
      this.hostName = null;
      this.path = path;
   }

   public String path() {
      return this.path;
   }

   public String host() {
      return this.host;
   }

   public String hostName() {
      return this.hostName;
   }

   public String hostAddress() {
      return this.ipAddress == null ? null : this.ipAddress.getHostAddress();
   }

   public int port() {
      return this.port;
   }

   public boolean isInetSocket() {
      return this.path == null;
   }

   public boolean isDomainSocket() {
      return this.path != null;
   }

   public String toString() {
      return this.path == null ? this.host + ":" + this.port : this.path;
   }

   public InetAddress ipAddress() {
      return this.ipAddress;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         SocketAddressImpl that = (SocketAddressImpl)o;
         return this.port == that.port && Objects.equals(this.host, that.host) && Objects.equals(this.path, that.path);
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = Objects.hashCode(this.host);
      result = 31 * result + Objects.hashCode(this.path);
      result = 31 * result + this.port;
      return result;
   }
}
