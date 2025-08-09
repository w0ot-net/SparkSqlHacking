package org.supercsv.util;

import java.util.HashMap;
import java.util.Set;

public class TwoDHashMap {
   private final HashMap map;

   public TwoDHashMap() {
      this.map = new HashMap();
   }

   public TwoDHashMap(HashMap map) {
      if (map == null) {
         throw new NullPointerException("map should not be null");
      } else {
         this.map = map;
      }
   }

   public boolean containsKey(Object firstKey, Object secondKey) {
      HashMap<K2, V> innerMap = (HashMap)this.map.get(firstKey);
      return innerMap == null ? false : innerMap.containsKey(secondKey);
   }

   public Object get(Object firstKey, Object secondKey) {
      HashMap<K2, V> innerMap = (HashMap)this.map.get(firstKey);
      return innerMap == null ? null : innerMap.get(secondKey);
   }

   public Object set(Object firstKey, Object secondKey, Object value) {
      HashMap<K2, V> innerMap = (HashMap)this.map.get(firstKey);
      if (innerMap == null) {
         innerMap = new HashMap();
         this.map.put(firstKey, innerMap);
      }

      return innerMap.put(secondKey, value);
   }

   public int size() {
      return this.map.size();
   }

   public int size(Object firstKey) {
      HashMap<K2, V> innerMap = (HashMap)this.map.get(firstKey);
      return innerMap == null ? 0 : innerMap.size();
   }

   public Set keySet() {
      return this.map.keySet();
   }
}
