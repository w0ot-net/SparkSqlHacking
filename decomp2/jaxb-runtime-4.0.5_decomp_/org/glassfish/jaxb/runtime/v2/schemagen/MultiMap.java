package org.glassfish.jaxb.runtime.v2.schemagen;

import java.util.Map;
import java.util.TreeMap;

final class MultiMap extends TreeMap {
   private static final long serialVersionUID = 236563410947519673L;
   private final Object many;

   public MultiMap(Object many) {
      this.many = many;
   }

   public Object put(Comparable key, Object value) {
      V old = (V)super.put(key, value);
      if (old != null && !old.equals(value)) {
         super.put(key, this.many);
      }

      return old;
   }

   public void putAll(Map map) {
      throw new UnsupportedOperationException();
   }
}
