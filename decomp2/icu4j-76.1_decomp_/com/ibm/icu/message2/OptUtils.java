package com.ibm.icu.message2;

import java.util.Map;

class OptUtils {
   private OptUtils() {
   }

   static Number asNumber(Object value) {
      if (value instanceof Number) {
         return (Number)value;
      } else {
         if (value instanceof CharSequence) {
            try {
               return Double.parseDouble(value.toString());
            } catch (NumberFormatException var2) {
            }
         }

         return null;
      }
   }

   static Integer getInteger(Map options, String key) {
      Object value = options.get(key);
      if (value == null) {
         return null;
      } else {
         Number nrValue = asNumber(value);
         return nrValue != null ? nrValue.intValue() : null;
      }
   }

   static String getString(Map options, String key, String defaultVal) {
      Object value = options.get(key);
      return value instanceof CharSequence ? value.toString() : defaultVal;
   }

   static String getString(Map options, String key) {
      return getString(options, key, (String)null);
   }
}
