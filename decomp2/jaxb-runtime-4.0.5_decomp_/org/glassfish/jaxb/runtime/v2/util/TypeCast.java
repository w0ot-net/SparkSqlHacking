package org.glassfish.jaxb.runtime.v2.util;

import java.util.Map;

public class TypeCast {
   private TypeCast() {
   }

   public static Map checkedCast(Map m, Class keyType, Class valueType) {
      if (m == null) {
         return null;
      } else {
         for(Map.Entry e : m.entrySet()) {
            if (!keyType.isInstance(e.getKey())) {
               throw new ClassCastException(e.getKey().getClass().toString());
            }

            if (!valueType.isInstance(e.getValue())) {
               throw new ClassCastException(e.getValue().getClass().toString());
            }
         }

         return m;
      }
   }
}
