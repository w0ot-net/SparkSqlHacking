package org.apache.parquet.hadoop.metadata;

import java.util.concurrent.ConcurrentHashMap;

public class Canonicalizer {
   private ConcurrentHashMap canonicals = new ConcurrentHashMap();

   public final Object canonicalize(Object value) {
      T canonical = (T)this.canonicals.get(value);
      if (canonical == null) {
         value = (T)this.toCanonical(value);
         T existing = (T)this.canonicals.putIfAbsent(value, value);
         if (existing == null) {
            canonical = value;
         } else {
            canonical = existing;
         }
      }

      return canonical;
   }

   protected Object toCanonical(Object value) {
      return value;
   }
}
