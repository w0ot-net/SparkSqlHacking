package org.apache.commons.math3.exception.util;

import java.util.ArrayList;
import java.util.List;

public class ArgUtils {
   private ArgUtils() {
   }

   public static Object[] flatten(Object[] array) {
      List<Object> list = new ArrayList();
      if (array != null) {
         for(Object o : array) {
            if (o instanceof Object[]) {
               for(Object oR : flatten(o)) {
                  list.add(oR);
               }
            } else {
               list.add(o);
            }
         }
      }

      return list.toArray();
   }
}
