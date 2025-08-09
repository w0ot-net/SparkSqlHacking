package org.sparkproject.guava.cache;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.collect.ImmutableMap;

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
