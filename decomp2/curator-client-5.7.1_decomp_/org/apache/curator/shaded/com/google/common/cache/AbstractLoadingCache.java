package org.apache.curator.shaded.com.google.common.cache;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.util.concurrent.UncheckedExecutionException;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class AbstractLoadingCache extends AbstractCache implements LoadingCache {
   protected AbstractLoadingCache() {
   }

   @CanIgnoreReturnValue
   public Object getUnchecked(Object key) {
      try {
         return this.get(key);
      } catch (ExecutionException e) {
         throw new UncheckedExecutionException(e.getCause());
      }
   }

   public ImmutableMap getAll(Iterable keys) throws ExecutionException {
      Map<K, V> result = Maps.newLinkedHashMap();

      for(Object key : keys) {
         if (!result.containsKey(key)) {
            result.put(key, this.get(key));
         }
      }

      return ImmutableMap.copyOf(result);
   }

   public final Object apply(Object key) {
      return this.getUnchecked(key);
   }

   public void refresh(Object key) {
      throw new UnsupportedOperationException();
   }
}
