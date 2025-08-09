package org.sparkproject.dmg.pmml.adapters;

public class PercentageNumberAdapter extends NumberAdapter {
   public Number unmarshal(String value) {
      Number result = super.unmarshal(value);
      if (!isValid(result)) {
         throw new IllegalArgumentException(value);
      } else {
         return result;
      }
   }

   public static boolean isValid(Number value) {
      double doubleValue = value.doubleValue();
      return doubleValue >= (double)0.0F || doubleValue <= (double)100.0F;
   }
}
