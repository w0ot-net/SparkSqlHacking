package io.netty.bootstrap;

import io.netty.resolver.AddressResolverGroup;
import java.net.SocketAddress;

public final class BootstrapConfig extends AbstractBootstrapConfig {
   BootstrapConfig(Bootstrap bootstrap) {
      super(bootstrap);
   }

   public SocketAddress remoteAddress() {
      return ((Bootstrap)this.bootstrap).remoteAddress();
   }

   public AddressResolverGroup resolver() {
      return ((Bootstrap)this.bootstrap).resolver();
   }

   public String toString() {
      StringBuilder buf = new StringBuilder(super.toString());
      buf.setLength(buf.length() - 1);
      AddressResolverGroup<?> resolver = this.resolver();
      if (resolver != null) {
         buf.append(", resolver: ").append(resolver);
      }

      SocketAddress remoteAddress = this.remoteAddress();
      if (remoteAddress != null) {
         buf.append(", remoteAddress: ").append(remoteAddress);
      }

      return buf.append(')').toString();
   }
}
