package org.apache.curator.shaded.com.google.common.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.CompatibleWith;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

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
