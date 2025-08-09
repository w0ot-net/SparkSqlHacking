package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

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
