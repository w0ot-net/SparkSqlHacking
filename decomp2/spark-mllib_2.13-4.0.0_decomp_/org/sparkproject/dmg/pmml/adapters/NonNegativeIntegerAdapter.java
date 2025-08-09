package org.sparkproject.dmg.pmml.adapters;

public class NonNegativeIntegerAdapter extends IntegerAdapter {
   public Integer unmarshal(String value) {
      Integer result = super.unmarshal(value);
      if (!isValid(result)) {
         throw new IllegalArgumentException(value);
      } else {
         return result;
      }
   }

   public static boolean isValid(Integer value) {
      return value >= 0;
   }
}
