package io.netty.resolver.dns;

import io.netty.resolver.NameResolver;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

final class InflightNameResolver implements NameResolver {
   private final EventExecutor executor;
   private final NameResolver delegate;
   private final ConcurrentMap resolvesInProgress;
   private final ConcurrentMap resolveAllsInProgress;

   InflightNameResolver(EventExecutor executor, NameResolver delegate, ConcurrentMap resolvesInProgress, ConcurrentMap resolveAllsInProgress) {
      this.executor = (EventExecutor)ObjectUtil.checkNotNull(executor, "executor");
      this.delegate = (NameResolver)ObjectUtil.checkNotNull(delegate, "delegate");
      this.resolvesInProgress = (ConcurrentMap)ObjectUtil.checkNotNull(resolvesInProgress, "resolvesInProgress");
      this.resolveAllsInProgress = (ConcurrentMap)ObjectUtil.checkNotNull(resolveAllsInProgress, "resolveAllsInProgress");
   }

   public Future resolve(String inetHost) {
      return this.resolve(inetHost, this.executor.newPromise());
   }

   public Future resolveAll(String inetHost) {
      return this.resolveAll(inetHost, this.executor.newPromise());
   }

   public void close() {
      this.delegate.close();
   }

   public Promise resolve(String inetHost, Promise promise) {
      return this.resolve(this.resolvesInProgress, inetHost, promise, false);
   }

   public Promise resolveAll(String inetHost, Promise promise) {
      return this.resolve(this.resolveAllsInProgress, inetHost, promise, true);
   }

   private Promise resolve(final ConcurrentMap resolveMap, final String inetHost, final Promise promise, boolean resolveAll) {
      Promise<U> earlyPromise = (Promise)resolveMap.putIfAbsent(inetHost, promise);
      if (earlyPromise != null) {
         if (earlyPromise.isDone()) {
            transferResult(earlyPromise, promise);
         } else {
            earlyPromise.addListener(new FutureListener() {
               public void operationComplete(Future f) throws Exception {
                  InflightNameResolver.transferResult(f, promise);
               }
            });
         }
      } else {
         try {
            if (resolveAll) {
               this.delegate.resolveAll(inetHost, promise);
            } else {
               this.delegate.resolve(inetHost, promise);
            }
         } finally {
            if (promise.isDone()) {
               resolveMap.remove(inetHost);
            } else {
               promise.addListener(new FutureListener() {
                  public void operationComplete(Future f) throws Exception {
                     resolveMap.remove(inetHost);
                  }
               });
            }

         }
      }

      return promise;
   }

   private static void transferResult(Future src, Promise dst) {
      if (src.isSuccess()) {
         dst.trySuccess(src.getNow());
      } else {
         dst.tryFailure(src.cause());
      }

   }

   public String toString() {
      return StringUtil.simpleClassName(this) + '(' + this.delegate + ')';
   }
}
