package org.apache.spark.util;

import org.apache.spark.annotation.Private;
import org.sparkproject.guava.base.Joiner;

@Private
public class EnumUtil {
   public static Enum parseIgnoreCase(Class clz, String str) {
      E[] constants = (E[])((Enum[])clz.getEnumConstants());
      if (str == null) {
         return null;
      } else {
         for(Enum e : constants) {
            if (e.name().equalsIgnoreCase(str)) {
               return e;
            }
         }

         throw new IllegalArgumentException(String.format("Illegal type='%s'. Supported type values: %s", str, Joiner.on(", ").join(constants)));
      }
   }
}
