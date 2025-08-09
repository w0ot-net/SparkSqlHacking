package org.sparkproject.jpmml.model.visitors;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FloatInterner extends NumberInterner {
   private static final ConcurrentMap cache = new ConcurrentHashMap();
   public static final ThreadLocal CACHE_PROVIDER = new ThreadLocal() {
      public ConcurrentMap initialValue() {
         return FloatInterner.cache;
      }
   };

   public FloatInterner() {
      super(Float.class, (ConcurrentMap)CACHE_PROVIDER.get());
   }

   public Float canonicalize(Float value) {
      return value;
   }

   public static void clear() {
      cache.clear();
   }
}
