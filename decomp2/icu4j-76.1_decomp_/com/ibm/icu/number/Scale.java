package com.ibm.icu.number;

import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.RoundingUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Scale {
   private static final Scale DEFAULT = new Scale(0, (BigDecimal)null);
   private static final Scale HUNDRED = new Scale(2, (BigDecimal)null);
   private static final Scale THOUSAND = new Scale(3, (BigDecimal)null);
   private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100L);
   private static final BigDecimal BIG_DECIMAL_1000 = BigDecimal.valueOf(1000L);
   final int magnitude;
   final BigDecimal arbitrary;
   final BigDecimal reciprocal;
   final MathContext mc;

   private Scale(int magnitude, BigDecimal arbitrary) {
      this(magnitude, arbitrary, RoundingUtils.DEFAULT_MATH_CONTEXT_34_DIGITS);
   }

   private Scale(int magnitude, BigDecimal arbitrary, MathContext mc) {
      if (arbitrary != null) {
         arbitrary = arbitrary.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : arbitrary.stripTrailingZeros();
         if (arbitrary.precision() == 1 && arbitrary.unscaledValue().equals(BigInteger.ONE)) {
            magnitude -= arbitrary.scale();
            arbitrary = null;
         }
      }

      this.magnitude = magnitude;
      this.arbitrary = arbitrary;
      this.mc = mc;
      if (arbitrary != null && BigDecimal.ZERO.compareTo(arbitrary) != 0) {
         this.reciprocal = BigDecimal.ONE.divide(arbitrary, mc);
      } else {
         this.reciprocal = null;
      }

   }

   public static Scale none() {
      return DEFAULT;
   }

   public static Scale powerOfTen(int power) {
      if (power == 0) {
         return DEFAULT;
      } else if (power == 2) {
         return HUNDRED;
      } else {
         return power == 3 ? THOUSAND : new Scale(power, (BigDecimal)null);
      }
   }

   public static Scale byBigDecimal(BigDecimal multiplicand) {
      if (multiplicand.compareTo(BigDecimal.ONE) == 0) {
         return DEFAULT;
      } else if (multiplicand.compareTo(BIG_DECIMAL_100) == 0) {
         return HUNDRED;
      } else {
         return multiplicand.compareTo(BIG_DECIMAL_1000) == 0 ? THOUSAND : new Scale(0, multiplicand);
      }
   }

   public static Scale byDouble(double multiplicand) {
      if (multiplicand == (double)1.0F) {
         return DEFAULT;
      } else if (multiplicand == (double)100.0F) {
         return HUNDRED;
      } else {
         return multiplicand == (double)1000.0F ? THOUSAND : new Scale(0, BigDecimal.valueOf(multiplicand));
      }
   }

   public static Scale byDoubleAndPowerOfTen(double multiplicand, int power) {
      return new Scale(power, BigDecimal.valueOf(multiplicand));
   }

   boolean isValid() {
      return this.magnitude != 0 || this.arbitrary != null;
   }

   /** @deprecated */
   @Deprecated
   public Scale withMathContext(MathContext mc) {
      return this.mc.equals(mc) ? this : new Scale(this.magnitude, this.arbitrary, mc);
   }

   /** @deprecated */
   @Deprecated
   public void applyTo(DecimalQuantity quantity) {
      quantity.adjustMagnitude(this.magnitude);
      if (this.arbitrary != null) {
         quantity.multiplyBy(this.arbitrary);
      }

   }

   /** @deprecated */
   @Deprecated
   public void applyReciprocalTo(DecimalQuantity quantity) {
      quantity.adjustMagnitude(-this.magnitude);
      if (this.reciprocal != null) {
         quantity.multiplyBy(this.reciprocal);
         quantity.roundToMagnitude(quantity.getMagnitude() - this.mc.getPrecision(), this.mc);
      }

   }
}
