package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

public class SegmentConstantPoolArrayCache {
   protected IdentityHashMap knownArrays = new IdentityHashMap(1000);
   protected List lastIndexes;
   protected String[] lastArray;
   protected String lastKey;

   protected boolean arrayIsCached(String[] array) {
      CachedArray cachedArray = (CachedArray)this.knownArrays.get(array);
      return cachedArray != null && cachedArray.lastKnownSize() == array.length;
   }

   protected void cacheArray(String[] array) {
      if (this.arrayIsCached(array)) {
         throw new IllegalArgumentException("Trying to cache an array that already exists");
      } else {
         this.knownArrays.put(array, new CachedArray(array));
         this.lastArray = null;
      }
   }

   public List indexesForArrayKey(String[] array, String key) {
      if (!this.arrayIsCached(array)) {
         this.cacheArray(array);
      }

      if (this.lastArray == array && this.lastKey == key) {
         return this.lastIndexes;
      } else {
         this.lastArray = array;
         this.lastKey = key;
         this.lastIndexes = ((CachedArray)this.knownArrays.get(array)).indexesForKey(key);
         return this.lastIndexes;
      }
   }

   protected class CachedArray {
      String[] primaryArray;
      int lastKnownSize;
      HashMap primaryTable;

      public CachedArray(String[] array) {
         this.primaryArray = array;
         this.lastKnownSize = array.length;
         this.primaryTable = new HashMap(this.lastKnownSize);
         this.cacheIndexes();
      }

      protected void cacheIndexes() {
         for(int index = 0; index < this.primaryArray.length; ++index) {
            String key = this.primaryArray[index];
            ((List)this.primaryTable.computeIfAbsent(key, (k) -> new ArrayList())).add(index);
         }

      }

      public List indexesForKey(String key) {
         List<Integer> list = (List)this.primaryTable.get(key);
         return list != null ? list : Collections.emptyList();
      }

      public int lastKnownSize() {
         return this.lastKnownSize;
      }
   }
}
