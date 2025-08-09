package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;

@DoNotMock("Use CacheBuilder.newBuilder().build()")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Cache {
   @CheckForNull
   @CanIgnoreReturnValue
   Object getIfPresent(@CompatibleWith("K") Object key);

   @CanIgnoreReturnValue
   Object get(Object key, Callable loader) throws ExecutionException;

   ImmutableMap getAllPresent(Iterable keys);

   void put(Object key, Object value);

   void putAll(Map m);

   void invalidate(@CompatibleWith("K") Object key);

   void invalidateAll(Iterable keys);

   void invalidateAll();

   long size();

   CacheStats stats();

   ConcurrentMap asMap();

   void cleanUp();
}
