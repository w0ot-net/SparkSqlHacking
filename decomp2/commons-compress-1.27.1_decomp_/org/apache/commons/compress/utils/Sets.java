package org.apache.commons.compress.utils;

import java.util.Collections;
import java.util.HashSet;

public class Sets {
   @SafeVarargs
   public static HashSet newHashSet(Object... elements) {
      HashSet<E> set = new HashSet(elements != null ? elements.length : 0);
      if (elements != null) {
         Collections.addAll(set, elements);
      }

      return set;
   }

   private Sets() {
   }
}
