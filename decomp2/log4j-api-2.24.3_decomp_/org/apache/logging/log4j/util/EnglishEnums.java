package org.apache.logging.log4j.util;

@InternalApi
public final class EnglishEnums {
   private EnglishEnums() {
   }

   public static Enum valueOf(final Class enumType, final String name) {
      return valueOf(enumType, name, (Enum)null);
   }

   public static Enum valueOf(final Class enumType, final String name, final Enum defaultValue) {
      return name == null ? defaultValue : Enum.valueOf(enumType, Strings.toRootUpperCase(name));
   }
}
