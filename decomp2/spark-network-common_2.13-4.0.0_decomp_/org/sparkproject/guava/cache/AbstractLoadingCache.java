package org.sparkproject.guava.cache;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.collect.ImmutableMap;
import org.sparkproject.guava.collect.Maps;
import org.sparkproject.guava.util.concurrent.UncheckedExecutionException;

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
