package org.apache.ivy.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils {
   public static List toList(Iterator iterator) {
      List<T> list = new ArrayList();

      while(iterator.hasNext()) {
         list.add(iterator.next());
      }

      return list;
   }
}
