package org.apache.commons.compress.harmony.pack200;

public class CPDouble extends CPConstant {
   private final double theDouble;

   public CPDouble(double theDouble) {
      this.theDouble = theDouble;
   }

   public int compareTo(CPDouble obj) {
      return Double.compare(this.theDouble, obj.theDouble);
   }

   public double getDouble() {
      return this.theDouble;
   }
}
