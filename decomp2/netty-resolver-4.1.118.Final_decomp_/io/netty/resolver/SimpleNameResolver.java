package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

public abstract class SimpleNameResolver implements NameResolver {
   private final EventExecutor executor;

   protected SimpleNameResolver(EventExecutor executor) {
      this.executor = (EventExecutor)ObjectUtil.checkNotNull(executor, "executor");
   }

   protected EventExecutor executor() {
      return this.executor;
   }

   public final Future resolve(String inetHost) {
      Promise<T> promise = this.executor().newPromise();
      return this.resolve(inetHost, promise);
   }

   public Future resolve(String inetHost, Promise promise) {
      ObjectUtil.checkNotNull(promise, "promise");

      try {
         this.doResolve(inetHost, promise);
         return promise;
      } catch (Exception e) {
         return promise.setFailure(e);
      }
   }

   public final Future resolveAll(String inetHost) {
      Promise<List<T>> promise = this.executor().newPromise();
      return this.resolveAll(inetHost, promise);
   }

   public Future resolveAll(String inetHost, Promise promise) {
      ObjectUtil.checkNotNull(promise, "promise");

      try {
         this.doResolveAll(inetHost, promise);
         return promise;
      } catch (Exception e) {
         return promise.setFailure(e);
      }
   }

   protected abstract void doResolve(String var1, Promise var2) throws Exception;

   protected abstract void doResolveAll(String var1, Promise var2) throws Exception;

   public void close() {
   }
}
