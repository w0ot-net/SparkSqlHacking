package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.DatatypeConverter;

public class NumberUtil {
   private NumberUtil() {
   }

   public static Integer parseInteger(String value) {
      return DatatypeConverter.parseInt(value);
   }

   public static String printInteger(Integer value) {
      return DatatypeConverter.printInt(value);
   }

   public static Number parseNumber(String value) {
      try {
         return DatatypeConverter.parseInt(value);
      } catch (NumberFormatException var2) {
         return DatatypeConverter.parseDouble(value);
      }
   }

   public static String printNumber(Number value) {
      if (value instanceof Float) {
         return DatatypeConverter.printFloat(value.floatValue());
      } else {
         return value instanceof Double ? DatatypeConverter.printDouble(value.doubleValue()) : value.toString();
      }
   }
}
