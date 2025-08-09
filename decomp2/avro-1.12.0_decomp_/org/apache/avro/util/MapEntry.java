package org.apache.avro.util;

import java.util.Map;

public class MapEntry implements Map.Entry {
   Object key;
   Object value;

   public MapEntry(Object key, Object value) {
      this.key = key;
      this.value = value;
   }

   public Object getKey() {
      return this.key;
   }

   public Object getValue() {
      return this.value;
   }

   public Object setValue(Object value) {
      V oldValue = (V)this.value;
      this.value = value;
      return oldValue;
   }
}
