package org.sparkproject.jpmml.model.visitors;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IntegerInterner extends NumberInterner {
   private static final ConcurrentMap cache = new ConcurrentHashMap();
   public static final ThreadLocal CACHE_PROVIDER = new ThreadLocal() {
      public ConcurrentMap initialValue() {
         return IntegerInterner.cache;
      }
   };

   public IntegerInterner() {
      super(Integer.class, (ConcurrentMap)CACHE_PROVIDER.get());
   }

   public Integer canonicalize(Integer value) {
      return value;
   }

   public static void clear() {
      cache.clear();
   }
}
