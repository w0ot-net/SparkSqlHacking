package org.apache.curator.shaded.com.google.common.cache;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

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
