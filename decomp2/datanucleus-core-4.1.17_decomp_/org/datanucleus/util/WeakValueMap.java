package org.datanucleus.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

public class WeakValueMap extends ReferenceValueMap {
   public WeakValueMap() {
   }

   public WeakValueMap(int initialCapacity) {
      super(initialCapacity);
   }

   public WeakValueMap(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
   }

   public WeakValueMap(Map m) {
      super(m);
   }

   protected ReferenceValueMap.ValueReference newValueReference(Object key, Object value, ReferenceQueue queue) {
      return new WeakValueReference(key, value, queue);
   }

   private static class WeakValueReference extends WeakReference implements ReferenceValueMap.ValueReference {
      private final Object key;

      WeakValueReference(Object key, Object value, ReferenceQueue q) {
         super(value, q);
         this.key = key;
      }

      public Object getKey() {
         return this.key;
      }
   }
}
