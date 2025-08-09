package org.apache.spark.util;

import java.util.concurrent.TimeUnit;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import scala.Function1;
import scala.Predef.;
import scala.runtime.BoxedUnit;

public final class NonFateSharingCache$ {
   public static final NonFateSharingCache$ MODULE$ = new NonFateSharingCache$();

   public NonFateSharingCache apply(final Cache cache) {
      if (cache instanceof LoadingCache var4) {
         return this.apply(var4);
      } else {
         return new NonFateSharingCache(cache);
      }
   }

   public NonFateSharingLoadingCache apply(final LoadingCache loadingCache) {
      return new NonFateSharingLoadingCache(loadingCache);
   }

   public NonFateSharingLoadingCache apply(final Function1 loadingFunc, final long maximumSize) {
      .MODULE$.require(loadingFunc != null);
      CacheBuilder builder = CacheBuilder.newBuilder();
      if (maximumSize > 0L) {
         builder.maximumSize(maximumSize);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return new NonFateSharingLoadingCache(builder.build(new CacheLoader(loadingFunc) {
         private final Function1 loadingFunc$1;

         public Object load(final Object k) {
            return this.loadingFunc$1.apply(k);
         }

         public {
            this.loadingFunc$1 = loadingFunc$1;
         }
      }));
   }

   public NonFateSharingCache apply(final long maximumSize, final long expireAfterAccessTime, final TimeUnit expireAfterAccessTimeUnit) {
      CacheBuilder builder = CacheBuilder.newBuilder();
      if (maximumSize > 0L) {
         builder.maximumSize(maximumSize);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (expireAfterAccessTime > 0L) {
         builder.expireAfterAccess(expireAfterAccessTime, expireAfterAccessTimeUnit);
      } else {
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

      return new NonFateSharingCache(builder.build());
   }

   public long apply$default$2() {
      return 0L;
   }

   private NonFateSharingCache$() {
   }
}
