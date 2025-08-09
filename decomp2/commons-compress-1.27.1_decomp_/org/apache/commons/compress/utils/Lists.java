package org.apache.commons.compress.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class Lists {
   public static ArrayList newArrayList() {
      return new ArrayList();
   }

   public static ArrayList newArrayList(Iterator iterator) {
      ArrayList<E> list = newArrayList();
      Iterators.addAll(list, iterator);
      return list;
   }

   private Lists() {
   }
}
