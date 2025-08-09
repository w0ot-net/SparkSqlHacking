package org.xerial.snappy.pool;

public final class DefaultPoolFactory {
   public static final String DISABLE_CACHING_PROPERTY = "org.xerial.snappy.pool.disable";
   private static volatile BufferPool defaultPool = "true".equalsIgnoreCase(System.getProperty("org.xerial.snappy.pool.disable")) ? QuiescentBufferPool.getInstance() : CachingBufferPool.getInstance();

   public static BufferPool getDefaultPool() {
      return defaultPool;
   }

   public static void setDefaultPool(BufferPool var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("pool is null");
      } else {
         defaultPool = var0;
      }
   }
}
