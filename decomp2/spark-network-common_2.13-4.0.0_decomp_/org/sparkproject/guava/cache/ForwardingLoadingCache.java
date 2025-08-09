package org.sparkproject.guava.cache;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableMap;

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
