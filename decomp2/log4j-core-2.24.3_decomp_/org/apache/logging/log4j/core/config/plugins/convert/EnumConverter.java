package org.apache.logging.log4j.core.config.plugins.convert;

import org.apache.logging.log4j.util.EnglishEnums;

public class EnumConverter implements TypeConverter {
   private final Class clazz;

   public EnumConverter(final Class clazz) {
      this.clazz = clazz;
   }

   public Enum convert(final String s) {
      return EnglishEnums.valueOf(this.clazz, s);
   }
}
