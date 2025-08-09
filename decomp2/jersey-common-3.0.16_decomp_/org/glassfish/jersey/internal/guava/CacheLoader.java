package org.glassfish.jersey.internal.guava;

public abstract class CacheLoader {
   protected CacheLoader() {
   }

   public abstract Object load(Object var1) throws Exception;

   public ListenableFuture reload(Object key, Object oldValue) throws Exception {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(oldValue);
      return Futures.immediateFuture(this.load(key));
   }

   public static final class InvalidCacheLoadException extends RuntimeException {
      public InvalidCacheLoadException(String message) {
         super(message);
      }
   }
}
