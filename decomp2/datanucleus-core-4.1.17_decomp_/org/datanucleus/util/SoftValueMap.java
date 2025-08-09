package org.datanucleus.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;

public class SoftValueMap extends ReferenceValueMap {
   public SoftValueMap() {
   }

   public SoftValueMap(int initialCapacity) {
      super(initialCapacity);
   }

   public SoftValueMap(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
   }

   public SoftValueMap(Map m) {
      super(m);
   }

   protected ReferenceValueMap.ValueReference newValueReference(Object key, Object value, ReferenceQueue queue) {
      return new SoftValueReference(key, value, queue);
   }

   private static class SoftValueReference extends SoftReference implements ReferenceValueMap.ValueReference {
      private final Object key;

      SoftValueReference(Object key, Object value, ReferenceQueue q) {
         super(value, q);
         this.key = key;
      }

      public Object getKey() {
         return this.key;
      }
   }
}
