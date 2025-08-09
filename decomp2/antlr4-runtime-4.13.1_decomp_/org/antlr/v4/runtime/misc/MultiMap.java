package org.antlr.v4.runtime.misc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MultiMap extends LinkedHashMap {
   public void map(Object key, Object value) {
      List<V> elementsForKey = (List)this.get(key);
      if (elementsForKey == null) {
         elementsForKey = new ArrayList();
         super.put(key, elementsForKey);
      }

      elementsForKey.add(value);
   }

   public List getPairs() {
      List<Pair<K, V>> pairs = new ArrayList();

      for(Object key : this.keySet()) {
         for(Object value : (List)this.get(key)) {
            pairs.add(new Pair(key, value));
         }
      }

      return pairs;
   }
}
