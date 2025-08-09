package org.stringtemplate.v4.misc;

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
}
