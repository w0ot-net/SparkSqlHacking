package org.apache.orc.protobuf;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class MapFieldLite extends LinkedHashMap {
   private boolean isMutable = true;
   private static final MapFieldLite EMPTY_MAP_FIELD = new MapFieldLite();

   private MapFieldLite() {
   }

   private MapFieldLite(Map mapData) {
      super(mapData);
   }

   public static MapFieldLite emptyMapField() {
      return EMPTY_MAP_FIELD;
   }

   public void mergeFrom(MapFieldLite other) {
      this.ensureMutable();
      if (!other.isEmpty()) {
         this.putAll(other);
      }

   }

   public Set entrySet() {
      return this.isEmpty() ? Collections.emptySet() : super.entrySet();
   }

   public void clear() {
      this.ensureMutable();
      super.clear();
   }

   public Object put(Object key, Object value) {
      this.ensureMutable();
      Internal.checkNotNull(key);
      Internal.checkNotNull(value);
      return super.put(key, value);
   }

   public Object put(Map.Entry entry) {
      return this.put(entry.getKey(), entry.getValue());
   }

   public void putAll(Map m) {
      this.ensureMutable();
      checkForNullKeysAndValues(m);
      super.putAll(m);
   }

   public Object remove(Object key) {
      this.ensureMutable();
      return super.remove(key);
   }

   private static void checkForNullKeysAndValues(Map m) {
      for(Object key : m.keySet()) {
         Internal.checkNotNull(key);
         Internal.checkNotNull(m.get(key));
      }

   }

   private static boolean equals(Object a, Object b) {
      return a instanceof byte[] && b instanceof byte[] ? Arrays.equals((byte[])a, (byte[])b) : a.equals(b);
   }

   static boolean equals(Map a, Map b) {
      if (a == b) {
         return true;
      } else if (a.size() != b.size()) {
         return false;
      } else {
         for(Map.Entry entry : a.entrySet()) {
            if (!b.containsKey(entry.getKey())) {
               return false;
            }

            if (!equals(entry.getValue(), b.get(entry.getKey()))) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean equals(Object object) {
      return object instanceof Map && equals((Map)this, (Map)((Map)object));
   }

   private static int calculateHashCodeForObject(Object a) {
      if (a instanceof byte[]) {
         return Internal.hashCode((byte[])a);
      } else if (a instanceof Internal.EnumLite) {
         throw new UnsupportedOperationException();
      } else {
         return a.hashCode();
      }
   }

   static int calculateHashCodeForMap(Map a) {
      int result = 0;

      for(Map.Entry entry : a.entrySet()) {
         result += calculateHashCodeForObject(entry.getKey()) ^ calculateHashCodeForObject(entry.getValue());
      }

      return result;
   }

   public int hashCode() {
      return calculateHashCodeForMap(this);
   }

   private static Object copy(Object object) {
      if (object instanceof byte[]) {
         byte[] data = (byte[])object;
         return Arrays.copyOf(data, data.length);
      } else {
         return object;
      }
   }

   static Map copy(Map map) {
      Map<K, V> result = new LinkedHashMap(map.size() * 4 / 3 + 1);

      for(Map.Entry entry : map.entrySet()) {
         result.put(entry.getKey(), copy(entry.getValue()));
      }

      return result;
   }

   public MapFieldLite mutableCopy() {
      return this.isEmpty() ? new MapFieldLite() : new MapFieldLite(this);
   }

   public void makeImmutable() {
      this.isMutable = false;
   }

   public boolean isMutable() {
      return this.isMutable;
   }

   private void ensureMutable() {
      if (!this.isMutable()) {
         throw new UnsupportedOperationException();
      }
   }

   static {
      EMPTY_MAP_FIELD.makeImmutable();
   }
}
