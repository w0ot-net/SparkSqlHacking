package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.TypeParameterMatcher;
import java.net.SocketAddress;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAddressResolver implements AddressResolver {
   private final EventExecutor executor;
   private final TypeParameterMatcher matcher;

   protected AbstractAddressResolver(EventExecutor executor) {
      this.executor = (EventExecutor)ObjectUtil.checkNotNull(executor, "executor");
      this.matcher = TypeParameterMatcher.find(this, AbstractAddressResolver.class, "T");
   }

   protected AbstractAddressResolver(EventExecutor executor, Class addressType) {
      this.executor = (EventExecutor)ObjectUtil.checkNotNull(executor, "executor");
      this.matcher = TypeParameterMatcher.get(addressType);
   }

   protected EventExecutor executor() {
      return this.executor;
   }

   public boolean isSupported(SocketAddress address) {
      return this.matcher.match(address);
   }

   public final boolean isResolved(SocketAddress address) {
      if (!this.isSupported(address)) {
         throw new UnsupportedAddressTypeException();
      } else {
         return this.doIsResolved(address);
      }
   }

   protected abstract boolean doIsResolved(SocketAddress var1);

   public final Future resolve(SocketAddress address) {
      if (!this.isSupported((SocketAddress)ObjectUtil.checkNotNull(address, "address"))) {
         return this.executor().newFailedFuture(new UnsupportedAddressTypeException());
      } else if (this.isResolved(address)) {
         return this.executor.newSucceededFuture(address);
      } else {
         try {
            Promise<T> promise = this.executor().newPromise();
            this.doResolve(address, promise);
            return promise;
         } catch (Exception e) {
            return this.executor().newFailedFuture(e);
         }
      }
   }

   public final Future resolve(SocketAddress address, Promise promise) {
      ObjectUtil.checkNotNull(address, "address");
      ObjectUtil.checkNotNull(promise, "promise");
      if (!this.isSupported(address)) {
         return promise.setFailure(new UnsupportedAddressTypeException());
      } else if (this.isResolved(address)) {
         return promise.setSuccess(address);
      } else {
         try {
            this.doResolve(address, promise);
            return promise;
         } catch (Exception e) {
            return promise.setFailure(e);
         }
      }
   }

   public final Future resolveAll(SocketAddress address) {
      if (!this.isSupported((SocketAddress)ObjectUtil.checkNotNull(address, "address"))) {
         return this.executor().newFailedFuture(new UnsupportedAddressTypeException());
      } else if (this.isResolved(address)) {
         return this.executor.newSucceededFuture(Collections.singletonList(address));
      } else {
         try {
            Promise<List<T>> promise = this.executor().newPromise();
            this.doResolveAll(address, promise);
            return promise;
         } catch (Exception e) {
            return this.executor().newFailedFuture(e);
         }
      }
   }

   public final Future resolveAll(SocketAddress address, Promise promise) {
      ObjectUtil.checkNotNull(address, "address");
      ObjectUtil.checkNotNull(promise, "promise");
      if (!this.isSupported(address)) {
         return promise.setFailure(new UnsupportedAddressTypeException());
      } else if (this.isResolved(address)) {
         return promise.setSuccess(Collections.singletonList(address));
      } else {
         try {
            this.doResolveAll(address, promise);
            return promise;
         } catch (Exception e) {
            return promise.setFailure(e);
         }
      }
   }

   protected abstract void doResolve(SocketAddress var1, Promise var2) throws Exception;

   protected abstract void doResolveAll(SocketAddress var1, Promise var2) throws Exception;

   public void close() {
   }
}
