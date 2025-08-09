package org.sparkproject.guava.cache;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.collect.ImmutableMap;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface LoadingCache extends Cache, Function {
   @CanIgnoreReturnValue
   Object get(Object key) throws ExecutionException;

   @CanIgnoreReturnValue
   Object getUnchecked(Object key);

   @CanIgnoreReturnValue
   ImmutableMap getAll(Iterable keys) throws ExecutionException;

   /** @deprecated */
   @Deprecated
   Object apply(Object key);

   void refresh(Object key);

   ConcurrentMap asMap();
}
