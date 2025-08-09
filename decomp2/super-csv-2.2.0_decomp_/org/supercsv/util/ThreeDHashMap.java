package org.supercsv.util;

import java.util.HashMap;
import java.util.Set;

public class ThreeDHashMap {
   private final HashMap map = new HashMap();

   public boolean containsKey(Object firstKey, Object secondKey) {
      HashMap<K2, HashMap<K3, V>> innerMap1 = (HashMap)this.map.get(firstKey);
      return innerMap1 == null ? false : innerMap1.containsKey(secondKey);
   }

   public boolean containsKey(Object firstKey, Object secondKey, Object thirdKey) {
      HashMap<K2, HashMap<K3, V>> innerMap1 = (HashMap)this.map.get(firstKey);
      if (innerMap1 == null) {
         return false;
      } else {
         HashMap<K3, V> innerMap2 = (HashMap)innerMap1.get(secondKey);
         return innerMap2 == null ? false : innerMap2.containsKey(thirdKey);
      }
   }

   public HashMap get(Object firstKey) {
      return (HashMap)this.map.get(firstKey);
   }

   public TwoDHashMap getAs2d(Object firstKey) {
      HashMap<K2, HashMap<K3, V>> innerMap1 = (HashMap)this.map.get(firstKey);
      return innerMap1 != null ? new TwoDHashMap(innerMap1) : new TwoDHashMap();
   }

   public HashMap get(Object firstKey, Object secondKey) {
      HashMap<K2, HashMap<K3, V>> innerMap1 = (HashMap)this.map.get(firstKey);
      return innerMap1 == null ? null : (HashMap)innerMap1.get(secondKey);
   }

   public Object get(Object firstKey, Object secondKey, Object thirdKey) {
      HashMap<K2, HashMap<K3, V>> innerMap1 = (HashMap)this.map.get(firstKey);
      if (innerMap1 == null) {
         return null;
      } else {
         HashMap<K3, V> innerMap2 = (HashMap)innerMap1.get(secondKey);
         return innerMap2 == null ? null : innerMap2.get(thirdKey);
      }
   }

   public Object set(Object firstKey, Object secondKey, Object thirdKey, Object value) {
      HashMap<K2, HashMap<K3, V>> innerMap1 = (HashMap)this.map.get(firstKey);
      if (innerMap1 == null) {
         innerMap1 = new HashMap();
         this.map.put(firstKey, innerMap1);
      }

      HashMap<K3, V> innerMap2 = (HashMap)innerMap1.get(secondKey);
      if (innerMap2 == null) {
         innerMap2 = new HashMap();
         innerMap1.put(secondKey, innerMap2);
      }

      return innerMap2.put(thirdKey, value);
   }

   public int size() {
      return this.map.size();
   }

   public int size(Object firstKey) {
      HashMap<K2, HashMap<K3, V>> innerMap = (HashMap)this.map.get(firstKey);
      return innerMap == null ? 0 : innerMap.size();
   }

   public int size(Object firstKey, Object secondKey) {
      HashMap<K2, HashMap<K3, V>> innerMap1 = (HashMap)this.map.get(firstKey);
      if (innerMap1 == null) {
         return 0;
      } else {
         HashMap<K3, V> innerMap2 = (HashMap)innerMap1.get(secondKey);
         return innerMap2 == null ? 0 : innerMap2.size();
      }
   }

   public Set keySet() {
      return this.map.keySet();
   }
}
