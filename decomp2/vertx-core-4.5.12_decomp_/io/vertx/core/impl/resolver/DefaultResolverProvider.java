package io.vertx.core.impl.resolver;

import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.DefaultAddressResolverGroup;
import io.vertx.core.Handler;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.spi.resolver.ResolverProvider;

public class DefaultResolverProvider implements ResolverProvider {
   public AddressResolverGroup resolver(AddressResolverOptions options) {
      return DefaultAddressResolverGroup.INSTANCE;
   }

   public void close(Handler doneHandler) {
      doneHandler.handle((Object)null);
   }
}
