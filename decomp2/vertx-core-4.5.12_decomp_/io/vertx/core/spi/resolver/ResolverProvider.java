package io.vertx.core.spi.resolver;

import io.netty.resolver.AddressResolverGroup;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.impl.resolver.DefaultResolverProvider;
import io.vertx.core.impl.resolver.DnsResolverProvider;

public interface ResolverProvider {
   String DISABLE_DNS_RESOLVER_PROP_NAME = "vertx.disableDnsResolver";

   static ResolverProvider factory(Vertx vertx, AddressResolverOptions options) {
      try {
         if (!Boolean.getBoolean("vertx.disableDnsResolver")) {
            return DnsResolverProvider.create((VertxInternal)vertx, options);
         }
      } catch (Throwable e) {
         if (e instanceof VertxException) {
            throw e;
         }

         Logger logger = LoggerFactory.getLogger(ResolverProvider.class);
         logger.info("Using the default address resolver as the dns resolver could not be loaded");
      }

      return new DefaultResolverProvider();
   }

   AddressResolverGroup resolver(AddressResolverOptions var1);

   void close(Handler var1);
}
