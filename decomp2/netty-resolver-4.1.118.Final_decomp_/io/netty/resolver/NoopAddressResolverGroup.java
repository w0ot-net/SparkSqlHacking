package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;

public final class NoopAddressResolverGroup extends AddressResolverGroup {
   public static final NoopAddressResolverGroup INSTANCE = new NoopAddressResolverGroup();

   private NoopAddressResolverGroup() {
   }

   protected AddressResolver newResolver(EventExecutor executor) throws Exception {
      return new NoopAddressResolver(executor);
   }
}
