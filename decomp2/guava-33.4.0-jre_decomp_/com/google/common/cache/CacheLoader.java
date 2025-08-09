package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Executor;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public abstract class CacheLoader {
   protected CacheLoader() {
   }

   public abstract Object load(Object key) throws Exception;

   @GwtIncompatible
   public ListenableFuture reload(Object key, Object oldValue) throws Exception {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(oldValue);
      return Futures.immediateFuture(this.load(key));
   }

   public Map loadAll(Iterable keys) throws Exception {
      throw new UnsupportedLoadingOperationException();
   }

   public static CacheLoader from(Function function) {
      return new FunctionToCacheLoader(function);
   }

   public static CacheLoader from(Supplier supplier) {
      return new SupplierToCacheLoader(supplier);
   }

   @GwtIncompatible
   public static CacheLoader asyncReloading(final CacheLoader loader, final Executor executor) {
      Preconditions.checkNotNull(loader);
      Preconditions.checkNotNull(executor);
      return new CacheLoader() {
         public Object load(Object key) throws Exception {
            return loader.load(key);
         }

         public ListenableFuture reload(final Object key, final Object oldValue) {
            ListenableFutureTask<V> task = ListenableFutureTask.create(() -> loader.reload(key, oldValue).get());
            executor.execute(task);
            return task;
         }

         public Map loadAll(Iterable keys) throws Exception {
            return loader.loadAll(keys);
         }
      };
   }

   private static final class FunctionToCacheLoader extends CacheLoader implements Serializable {
      private final Function computingFunction;
      private static final long serialVersionUID = 0L;

      public FunctionToCacheLoader(Function computingFunction) {
         this.computingFunction = (Function)Preconditions.checkNotNull(computingFunction);
      }

      public Object load(Object key) {
         return this.computingFunction.apply(Preconditions.checkNotNull(key));
      }
   }

   private static final class SupplierToCacheLoader extends CacheLoader implements Serializable {
      private final Supplier computingSupplier;
      private static final long serialVersionUID = 0L;

      public SupplierToCacheLoader(Supplier computingSupplier) {
         this.computingSupplier = (Supplier)Preconditions.checkNotNull(computingSupplier);
      }

      public Object load(Object key) {
         Preconditions.checkNotNull(key);
         return this.computingSupplier.get();
      }
   }

   public static final class UnsupportedLoadingOperationException extends UnsupportedOperationException {
      UnsupportedLoadingOperationException() {
      }
   }

   public static final class InvalidCacheLoadException extends RuntimeException {
      public InvalidCacheLoadException(String message) {
         super(message);
      }
   }
}
