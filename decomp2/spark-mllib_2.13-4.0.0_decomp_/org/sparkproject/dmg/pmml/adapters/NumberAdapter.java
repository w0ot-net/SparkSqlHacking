package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class NumberAdapter extends XmlAdapter {
   public Number unmarshal(String value) {
      Number result = NumberUtil.parseNumber(value);
      if (!isValid(result)) {
         throw new IllegalArgumentException(value);
      } else {
         return result;
      }
   }

   public String marshal(Number value) {
      return value == null ? null : NumberUtil.printNumber(value);
   }

   public static boolean isValid(Number value) {
      if (value instanceof Float) {
         Float floatValue = (Float)value;
         if (floatValue.isNaN() || floatValue.isInfinite()) {
            return false;
         }
      } else if (value instanceof Double) {
         Double doubleValue = (Double)value;
         if (doubleValue.isNaN() || doubleValue.isInfinite()) {
            return false;
         }
      }

      return true;
   }
}
