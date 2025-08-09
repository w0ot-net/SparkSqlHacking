package io.netty.resolver;

import [Lio.netty.resolver.NameResolver;;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import java.util.Arrays;
import java.util.List;

public final class CompositeNameResolver extends SimpleNameResolver {
   private final NameResolver[] resolvers;

   public CompositeNameResolver(EventExecutor executor, NameResolver... resolvers) {
      super(executor);
      ObjectUtil.checkNotNull(resolvers, "resolvers");

      for(int i = 0; i < resolvers.length; ++i) {
         ObjectUtil.checkNotNull(resolvers[i], "resolvers[" + i + ']');
      }

      if (resolvers.length < 2) {
         throw new IllegalArgumentException("resolvers: " + Arrays.asList(resolvers) + " (expected: at least 2 resolvers)");
      } else {
         this.resolvers = (NameResolver[])((NameResolver;)resolvers).clone();
      }
   }

   protected void doResolve(String inetHost, Promise promise) throws Exception {
      this.doResolveRec(inetHost, promise, 0, (Throwable)null);
   }

   private void doResolveRec(final String inetHost, final Promise promise, final int resolverIndex, Throwable lastFailure) throws Exception {
      if (resolverIndex >= this.resolvers.length) {
         promise.setFailure(lastFailure);
      } else {
         NameResolver<T> resolver = this.resolvers[resolverIndex];
         resolver.resolve(inetHost).addListener(new FutureListener() {
            public void operationComplete(Future future) throws Exception {
               if (future.isSuccess()) {
                  promise.setSuccess(future.getNow());
               } else {
                  CompositeNameResolver.this.doResolveRec(inetHost, promise, resolverIndex + 1, future.cause());
               }

            }
         });
      }

   }

   protected void doResolveAll(String inetHost, Promise promise) throws Exception {
      this.doResolveAllRec(inetHost, promise, 0, (Throwable)null);
   }

   private void doResolveAllRec(final String inetHost, final Promise promise, final int resolverIndex, Throwable lastFailure) throws Exception {
      if (resolverIndex >= this.resolvers.length) {
         promise.setFailure(lastFailure);
      } else {
         NameResolver<T> resolver = this.resolvers[resolverIndex];
         resolver.resolveAll(inetHost).addListener(new FutureListener() {
            public void operationComplete(Future future) throws Exception {
               if (future.isSuccess()) {
                  promise.setSuccess(future.getNow());
               } else {
                  CompositeNameResolver.this.doResolveAllRec(inetHost, promise, resolverIndex + 1, future.cause());
               }

            }
         });
      }

   }
}
