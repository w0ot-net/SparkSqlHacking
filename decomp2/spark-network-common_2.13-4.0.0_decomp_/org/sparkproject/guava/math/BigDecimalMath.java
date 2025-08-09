package org.sparkproject.guava.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public class BigDecimalMath {
   private BigDecimalMath() {
   }

   public static double roundToDouble(BigDecimal x, RoundingMode mode) {
      return BigDecimalMath.BigDecimalToDoubleRounder.INSTANCE.roundToDouble(x, mode);
   }

   private static class BigDecimalToDoubleRounder extends ToDoubleRounder {
      static final BigDecimalToDoubleRounder INSTANCE = new BigDecimalToDoubleRounder();

      double roundToDoubleArbitrarily(BigDecimal bigDecimal) {
         return bigDecimal.doubleValue();
      }

      int sign(BigDecimal bigDecimal) {
         return bigDecimal.signum();
      }

      BigDecimal toX(double d, RoundingMode mode) {
         return new BigDecimal(d);
      }

      BigDecimal minus(BigDecimal a, BigDecimal b) {
         return a.subtract(b);
      }
   }
}
