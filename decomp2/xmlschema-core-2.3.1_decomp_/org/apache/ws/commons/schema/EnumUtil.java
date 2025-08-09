package org.apache.ws.commons.schema;

import java.util.Locale;

final class EnumUtil {
   private EnumUtil() {
   }

   static Enum valueOf(Class enumClass, String name) {
      return Enum.valueOf(enumClass, name.toUpperCase(Locale.ENGLISH));
   }
}
