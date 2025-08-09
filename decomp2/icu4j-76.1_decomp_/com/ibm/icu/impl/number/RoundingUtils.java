package com.ibm.icu.impl.number;

import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.number.Precision;
import com.ibm.icu.number.Scale;
import com.ibm.icu.text.PluralRules;
import java.math.MathContext;
import java.math.RoundingMode;

public class RoundingUtils {
   public static final int SECTION_LOWER = 1;
   public static final int SECTION_MIDPOINT = 2;
   public static final int SECTION_UPPER = 3;
   public static final RoundingMode DEFAULT_ROUNDING_MODE;
   public static final int MAX_INT_FRAC_SIG = 999;
   private static final MathContext[] MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED;
   private static final MathContext[] MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS;
   public static final MathContext DEFAULT_MATH_CONTEXT_UNLIMITED;
   public static final MathContext DEFAULT_MATH_CONTEXT_34_DIGITS;

   public static boolean getRoundingDirection(boolean isEven, boolean isNegative, int section, int roundingMode, Object reference) {
      switch (roundingMode) {
         case 0:
            return false;
         case 1:
            return true;
         case 2:
            return isNegative;
         case 3:
            return !isNegative;
         case 4:
            switch (section) {
               case 1:
                  return true;
               case 2:
                  return false;
               case 3:
                  return false;
               default:
                  throw new ArithmeticException("Rounding is required on " + reference.toString());
            }
         case 5:
            switch (section) {
               case 1:
                  return true;
               case 2:
                  return true;
               case 3:
                  return false;
               default:
                  throw new ArithmeticException("Rounding is required on " + reference.toString());
            }
         case 6:
            switch (section) {
               case 1:
                  return true;
               case 2:
                  return isEven;
               case 3:
                  return false;
            }
      }

      throw new ArithmeticException("Rounding is required on " + reference.toString());
   }

   public static boolean roundsAtMidpoint(int roundingMode) {
      switch (roundingMode) {
         case 0:
         case 1:
         case 2:
         case 3:
            return false;
         default:
            return true;
      }
   }

   public static MathContext getMathContextOrUnlimited(DecimalFormatProperties properties) {
      MathContext mathContext = properties.getMathContext();
      if (mathContext == null) {
         RoundingMode roundingMode = properties.getRoundingMode();
         if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_EVEN;
         }

         mathContext = MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[roundingMode.ordinal()];
      }

      return mathContext;
   }

   public static MathContext getMathContextOr34Digits(DecimalFormatProperties properties) {
      MathContext mathContext = properties.getMathContext();
      if (mathContext == null) {
         RoundingMode roundingMode = properties.getRoundingMode();
         if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_EVEN;
         }

         mathContext = MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS[roundingMode.ordinal()];
      }

      return mathContext;
   }

   public static MathContext mathContextUnlimited(RoundingMode roundingMode) {
      return MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[roundingMode.ordinal()];
   }

   public static Scale scaleFromProperties(DecimalFormatProperties properties) {
      MathContext mc = getMathContextOr34Digits(properties);
      if (properties.getMagnitudeMultiplier() != 0) {
         return Scale.powerOfTen(properties.getMagnitudeMultiplier()).withMathContext(mc);
      } else {
         return properties.getMultiplier() != null ? Scale.byBigDecimal(properties.getMultiplier()).withMathContext(mc) : null;
      }
   }

   public static StandardPlural getPluralSafe(Precision rounder, PluralRules rules, DecimalQuantity dq) {
      if (rounder == null) {
         return dq.getStandardPlural(rules);
      } else {
         DecimalQuantity copy = dq.createCopy();
         rounder.apply(copy);
         return copy.getStandardPlural(rules);
      }
   }

   static {
      DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;
      MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED = new MathContext[RoundingMode.values().length];
      MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS = new MathContext[RoundingMode.values().length];

      for(int i = 0; i < MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS.length; ++i) {
         MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[i] = new MathContext(0, RoundingMode.valueOf(i));
         MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS[i] = new MathContext(34);
      }

      DEFAULT_MATH_CONTEXT_UNLIMITED = MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[DEFAULT_ROUNDING_MODE.ordinal()];
      DEFAULT_MATH_CONTEXT_34_DIGITS = MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS[DEFAULT_ROUNDING_MODE.ordinal()];
   }
}
