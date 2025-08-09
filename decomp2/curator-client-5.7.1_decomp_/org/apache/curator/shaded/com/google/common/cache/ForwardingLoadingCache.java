package org.apache.curator.shaded.com.google.common.cache;

import java.util.concurrent.ExecutionException;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class ForwardingLoadingCache extends ForwardingCache implements LoadingCache {
   protected ForwardingLoadingCache() {
   }

   protected abstract LoadingCache delegate();

   @CanIgnoreReturnValue
   public Object get(Object key) throws ExecutionException {
      return this.delegate().get(key);
   }

   @CanIgnoreReturnValue
   public Object getUnchecked(Object key) {
      return this.delegate().getUnchecked(key);
   }

   @CanIgnoreReturnValue
   public ImmutableMap getAll(Iterable keys) throws ExecutionException {
      return this.delegate().getAll(keys);
   }

   public Object apply(Object key) {
      return this.delegate().apply(key);
   }

   public void refresh(Object key) {
      this.delegate().refresh(key);
   }

   public abstract static class SimpleForwardingLoadingCache extends ForwardingLoadingCache {
      private final LoadingCache delegate;

      protected SimpleForwardingLoadingCache(LoadingCache delegate) {
         this.delegate = (LoadingCache)Preconditions.checkNotNull(delegate);
      }

      protected final LoadingCache delegate() {
         return this.delegate;
      }
   }
}
