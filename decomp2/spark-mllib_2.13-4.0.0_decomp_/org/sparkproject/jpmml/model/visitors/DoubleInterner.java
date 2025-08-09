package org.sparkproject.jpmml.model.visitors;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DoubleInterner extends NumberInterner {
   private static final ConcurrentMap cache = new ConcurrentHashMap();
   public static final ThreadLocal CACHE_PROVIDER = new ThreadLocal() {
      public ConcurrentMap initialValue() {
         return DoubleInterner.cache;
      }
   };

   public DoubleInterner() {
      super(Double.class, (ConcurrentMap)CACHE_PROVIDER.get());
   }

   public Double canonicalize(Double value) {
      return value;
   }

   public static void clear() {
      cache.clear();
   }
}
