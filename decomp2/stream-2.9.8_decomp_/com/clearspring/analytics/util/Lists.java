package com.clearspring.analytics.util;

import java.util.ArrayList;
import java.util.List;

public class Lists {
   public static List newArrayList(Iterable source) {
      List<T> r = new ArrayList();

      for(Object x : source) {
         r.add(x);
      }

      return r;
   }

   public static List newArrayList() {
      return new ArrayList();
   }
}
