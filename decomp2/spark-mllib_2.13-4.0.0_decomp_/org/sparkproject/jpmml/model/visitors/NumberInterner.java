package org.sparkproject.jpmml.model.visitors;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public abstract class NumberInterner extends AttributeInterner {
   private ConcurrentMap cache = null;

   protected NumberInterner(Class type, ConcurrentMap cache) {
      super(type);
      this.setCache(cache);
   }

   public abstract Number canonicalize(Number var1);

   public Number intern(Number value) {
      ConcurrentMap<V, V> cache = this.getCache();
      if (value == null) {
         return null;
      } else {
         V canonicalValue = (V)((Number)cache.get(value));
         if (canonicalValue == null) {
            canonicalValue = (V)this.canonicalize(value);
            cache.putIfAbsent(value, canonicalValue);
         }

         return canonicalValue;
      }
   }

   public ConcurrentMap getCache() {
      return this.cache;
   }

   private void setCache(ConcurrentMap cache) {
      this.cache = (ConcurrentMap)Objects.requireNonNull(cache);
   }
}
