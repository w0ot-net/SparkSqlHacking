package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;

public class NoopAddressResolver extends AbstractAddressResolver {
   public NoopAddressResolver(EventExecutor executor) {
      super(executor);
   }

   protected boolean doIsResolved(SocketAddress address) {
      return true;
   }

   protected void doResolve(SocketAddress unresolvedAddress, Promise promise) throws Exception {
      promise.setSuccess(unresolvedAddress);
   }

   protected void doResolveAll(SocketAddress unresolvedAddress, Promise promise) throws Exception {
      promise.setSuccess(Collections.singletonList(unresolvedAddress));
   }
}
