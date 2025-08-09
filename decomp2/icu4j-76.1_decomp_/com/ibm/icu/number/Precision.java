package com.ibm.icu.number;

import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.MultiplierProducer;
import com.ibm.icu.impl.number.RoundingUtils;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.Currency;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public abstract class Precision {
   MathContext mathContext;
   NumberFormatter.TrailingZeroDisplay trailingZeroDisplay;
   /** @deprecated */
   @Deprecated
   public static final BogusRounder BOGUS_PRECISION = new BogusRounder();
   static final InfiniteRounderImpl NONE = new InfiniteRounderImpl();
   static final FractionRounderImpl FIXED_FRAC_0 = new FractionRounderImpl(0, 0);
   static final FractionRounderImpl FIXED_FRAC_2 = new FractionRounderImpl(2, 2);
   static final FractionRounderImpl DEFAULT_MAX_FRAC_6 = new FractionRounderImpl(0, 6);
   static final SignificantRounderImpl FIXED_SIG_2 = new SignificantRounderImpl(2, 2);
   static final SignificantRounderImpl FIXED_SIG_3 = new SignificantRounderImpl(3, 3);
   static final SignificantRounderImpl RANGE_SIG_2_3 = new SignificantRounderImpl(2, 3);
   static final FracSigRounderImpl COMPACT_STRATEGY;
   static final IncrementFiveRounderImpl NICKEL;
   static final CurrencyRounderImpl MONETARY_STANDARD;
   static final CurrencyRounderImpl MONETARY_CASH;

   Precision() {
      this.mathContext = RoundingUtils.DEFAULT_MATH_CONTEXT_UNLIMITED;
   }

   public static Precision unlimited() {
      return constructInfinite();
   }

   public static FractionPrecision integer() {
      return constructFraction(0, 0);
   }

   public static FractionPrecision fixedFraction(int minMaxFractionPlaces) {
      if (minMaxFractionPlaces >= 0 && minMaxFractionPlaces <= 999) {
         return constructFraction(minMaxFractionPlaces, minMaxFractionPlaces);
      } else {
         throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
      }
   }

   public static FractionPrecision minFraction(int minFractionPlaces) {
      if (minFractionPlaces >= 0 && minFractionPlaces <= 999) {
         return constructFraction(minFractionPlaces, -1);
      } else {
         throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
      }
   }

   public static FractionPrecision maxFraction(int maxFractionPlaces) {
      if (maxFractionPlaces >= 0 && maxFractionPlaces <= 999) {
         return constructFraction(0, maxFractionPlaces);
      } else {
         throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
      }
   }

   public static FractionPrecision minMaxFraction(int minFractionPlaces, int maxFractionPlaces) {
      if (minFractionPlaces >= 0 && maxFractionPlaces <= 999 && minFractionPlaces <= maxFractionPlaces) {
         return constructFraction(minFractionPlaces, maxFractionPlaces);
      } else {
         throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
      }
   }

   public static Precision fixedSignificantDigits(int minMaxSignificantDigits) {
      if (minMaxSignificantDigits >= 1 && minMaxSignificantDigits <= 999) {
         return constructSignificant(minMaxSignificantDigits, minMaxSignificantDigits);
      } else {
         throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
      }
   }

   public static Precision minSignificantDigits(int minSignificantDigits) {
      if (minSignificantDigits >= 1 && minSignificantDigits <= 999) {
         return constructSignificant(minSignificantDigits, -1);
      } else {
         throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
      }
   }

   public static Precision maxSignificantDigits(int maxSignificantDigits) {
      if (maxSignificantDigits >= 1 && maxSignificantDigits <= 999) {
         return constructSignificant(1, maxSignificantDigits);
      } else {
         throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
      }
   }

   public static Precision minMaxSignificantDigits(int minSignificantDigits, int maxSignificantDigits) {
      if (minSignificantDigits >= 1 && maxSignificantDigits <= 999 && minSignificantDigits <= maxSignificantDigits) {
         return constructSignificant(minSignificantDigits, maxSignificantDigits);
      } else {
         throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
      }
   }

   public static Precision increment(BigDecimal roundingIncrement) {
      if (roundingIncrement != null && roundingIncrement.compareTo(BigDecimal.ZERO) > 0) {
         return constructIncrement(roundingIncrement);
      } else {
         throw new IllegalArgumentException("Rounding increment must be positive and non-null");
      }
   }

   public static CurrencyPrecision currency(Currency.CurrencyUsage currencyUsage) {
      if (currencyUsage != null) {
         return constructCurrency(currencyUsage);
      } else {
         throw new IllegalArgumentException("CurrencyUsage must be non-null");
      }
   }

   public Precision trailingZeroDisplay(NumberFormatter.TrailingZeroDisplay trailingZeroDisplay) {
      Precision result = this.createCopy();
      result.trailingZeroDisplay = trailingZeroDisplay;
      return result;
   }

   /** @deprecated */
   @Deprecated
   public Precision withMode(MathContext mathContext) {
      if (this.mathContext.equals(mathContext)) {
         return this;
      } else {
         Precision other = this.createCopy();
         other.mathContext = mathContext;
         return other;
      }
   }

   abstract Precision createCopy();

   void createCopyHelper(Precision copy) {
      copy.mathContext = this.mathContext;
      copy.trailingZeroDisplay = this.trailingZeroDisplay;
   }

   /** @deprecated */
   @Deprecated
   public abstract void apply(DecimalQuantity var1);

   static Precision constructInfinite() {
      return NONE;
   }

   static FractionPrecision constructFraction(int minFrac, int maxFrac) {
      if (minFrac == 0 && maxFrac == 0) {
         return FIXED_FRAC_0;
      } else if (minFrac == 2 && maxFrac == 2) {
         return FIXED_FRAC_2;
      } else {
         return minFrac == 0 && maxFrac == 6 ? DEFAULT_MAX_FRAC_6 : new FractionRounderImpl(minFrac, maxFrac);
      }
   }

   static Precision constructSignificant(int minSig, int maxSig) {
      if (minSig == 2 && maxSig == 2) {
         return FIXED_SIG_2;
      } else if (minSig == 3 && maxSig == 3) {
         return FIXED_SIG_3;
      } else {
         return minSig == 2 && maxSig == 3 ? RANGE_SIG_2_3 : new SignificantRounderImpl(minSig, maxSig);
      }
   }

   static Precision constructFractionSignificant(FractionPrecision base_, int minSig, int maxSig, NumberFormatter.RoundingPriority priority, boolean retain) {
      assert base_ instanceof FractionRounderImpl;

      FractionRounderImpl base = (FractionRounderImpl)base_;
      Precision returnValue;
      if (base.minFrac == 0 && base.maxFrac == 0 && minSig == 1 && maxSig == 2 && priority == NumberFormatter.RoundingPriority.RELAXED && !retain) {
         returnValue = COMPACT_STRATEGY;
      } else {
         returnValue = new FracSigRounderImpl(base.minFrac, base.maxFrac, minSig, maxSig, priority, retain);
      }

      return returnValue.withMode(base.mathContext);
   }

   static Precision constructIncrement(BigDecimal increment) {
      if (increment.equals(NICKEL.increment)) {
         return NICKEL;
      } else {
         BigDecimal reduced = increment.stripTrailingZeros();
         if (reduced.precision() == 1) {
            int minFrac = increment.scale();
            int maxFrac = reduced.scale();
            BigInteger digit = reduced.unscaledValue();
            if (digit.intValue() == 1) {
               return new IncrementOneRounderImpl(increment, minFrac, maxFrac);
            }

            if (digit.intValue() == 5) {
               return new IncrementFiveRounderImpl(increment, minFrac, maxFrac);
            }
         }

         return new IncrementRounderImpl(increment);
      }
   }

   static CurrencyPrecision constructCurrency(Currency.CurrencyUsage usage) {
      if (usage == Currency.CurrencyUsage.STANDARD) {
         return MONETARY_STANDARD;
      } else if (usage == Currency.CurrencyUsage.CASH) {
         return MONETARY_CASH;
      } else {
         throw new AssertionError();
      }
   }

   static Precision constructFromCurrency(CurrencyPrecision base_, Currency currency) {
      assert base_ instanceof CurrencyRounderImpl;

      CurrencyRounderImpl base = (CurrencyRounderImpl)base_;
      double incrementDouble = currency.getRoundingIncrement(base.usage);
      Precision returnValue;
      if (incrementDouble != (double)0.0F) {
         BigDecimal increment = BigDecimal.valueOf(incrementDouble);
         returnValue = constructIncrement(increment);
      } else {
         int minMaxFrac = currency.getDefaultFractionDigits(base.usage);
         returnValue = constructFraction(minMaxFrac, minMaxFrac);
      }

      return returnValue.withMode(base.mathContext);
   }

   Precision withLocaleData(Currency currency) {
      return this instanceof CurrencyPrecision ? ((CurrencyPrecision)this).withCurrency(currency) : this;
   }

   int chooseMultiplierAndApply(DecimalQuantity input, MultiplierProducer producer) {
      assert !input.isZeroish();

      int magnitude = input.getMagnitude();
      int multiplier = producer.getMultiplier(magnitude);
      input.adjustMagnitude(multiplier);
      this.apply(input);
      if (input.isZeroish()) {
         return multiplier;
      } else if (input.getMagnitude() == magnitude + multiplier) {
         return multiplier;
      } else {
         int _multiplier = producer.getMultiplier(magnitude + 1);
         if (multiplier == _multiplier) {
            return multiplier;
         } else {
            input.adjustMagnitude(_multiplier - multiplier);
            this.apply(input);
            return _multiplier;
         }
      }
   }

   private static int getRoundingMagnitudeFraction(int maxFrac) {
      return maxFrac == -1 ? Integer.MIN_VALUE : -maxFrac;
   }

   private static int getRoundingMagnitudeSignificant(DecimalQuantity value, int maxSig) {
      if (maxSig == -1) {
         return Integer.MIN_VALUE;
      } else {
         int magnitude = value.isZeroish() ? 0 : value.getMagnitude();
         return magnitude - maxSig + 1;
      }
   }

   private static int getDisplayMagnitudeFraction(int minFrac) {
      return minFrac == 0 ? Integer.MAX_VALUE : -minFrac;
   }

   void setResolvedMinFraction(DecimalQuantity value, int resolvedMinFraction) {
      if (this.trailingZeroDisplay == null || this.trailingZeroDisplay == NumberFormatter.TrailingZeroDisplay.AUTO || value.getPluralOperand(PluralRules.Operand.t) != (double)0.0F) {
         value.setMinFraction(resolvedMinFraction);
      }

   }

   private static int getDisplayMagnitudeSignificant(DecimalQuantity value, int minSig) {
      int magnitude = value.isZeroish() ? 0 : value.getMagnitude();
      return magnitude - minSig + 1;
   }

   static {
      COMPACT_STRATEGY = new FracSigRounderImpl(0, 0, 1, 2, NumberFormatter.RoundingPriority.RELAXED, false);
      NICKEL = new IncrementFiveRounderImpl(new BigDecimal("0.05"), 2, 2);
      MONETARY_STANDARD = new CurrencyRounderImpl(Currency.CurrencyUsage.STANDARD);
      MONETARY_CASH = new CurrencyRounderImpl(Currency.CurrencyUsage.CASH);
   }

   /** @deprecated */
   @Deprecated
   public static class BogusRounder extends Precision {
      /** @deprecated */
      @Deprecated
      public void apply(DecimalQuantity value) {
         throw new AssertionError("BogusRounder must not be applied");
      }

      BogusRounder createCopy() {
         BogusRounder copy = new BogusRounder();
         this.createCopyHelper(copy);
         return copy;
      }

      /** @deprecated */
      @Deprecated
      public Precision into(Precision precision) {
         Precision copy = precision.createCopy();
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class InfiniteRounderImpl extends Precision {
      public InfiniteRounderImpl() {
      }

      public void apply(DecimalQuantity value) {
         value.roundToInfinity();
         this.setResolvedMinFraction(value, 0);
      }

      InfiniteRounderImpl createCopy() {
         InfiniteRounderImpl copy = new InfiniteRounderImpl();
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class FractionRounderImpl extends FractionPrecision {
      final int minFrac;
      final int maxFrac;

      public FractionRounderImpl(int minFrac, int maxFrac) {
         this.minFrac = minFrac;
         this.maxFrac = maxFrac;
      }

      public void apply(DecimalQuantity value) {
         value.roundToMagnitude(Precision.getRoundingMagnitudeFraction(this.maxFrac), this.mathContext);
         this.setResolvedMinFraction(value, Math.max(0, -Precision.getDisplayMagnitudeFraction(this.minFrac)));
      }

      FractionRounderImpl createCopy() {
         FractionRounderImpl copy = new FractionRounderImpl(this.minFrac, this.maxFrac);
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class SignificantRounderImpl extends Precision {
      final int minSig;
      final int maxSig;

      public SignificantRounderImpl(int minSig, int maxSig) {
         this.minSig = minSig;
         this.maxSig = maxSig;
      }

      public void apply(DecimalQuantity value) {
         value.roundToMagnitude(Precision.getRoundingMagnitudeSignificant(value, this.maxSig), this.mathContext);
         this.setResolvedMinFraction(value, Math.max(0, -Precision.getDisplayMagnitudeSignificant(value, this.minSig)));
         if (value.isZeroish() && this.minSig > 0) {
            value.setMinInteger(1);
         }

      }

      public void apply(DecimalQuantity quantity, int minInt) {
         assert quantity.isZeroish();

         this.setResolvedMinFraction(quantity, this.minSig - minInt);
      }

      SignificantRounderImpl createCopy() {
         SignificantRounderImpl copy = new SignificantRounderImpl(this.minSig, this.maxSig);
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class FracSigRounderImpl extends Precision {
      final int minFrac;
      final int maxFrac;
      final int minSig;
      final int maxSig;
      final NumberFormatter.RoundingPriority priority;
      final boolean retain;

      public FracSigRounderImpl(int minFrac, int maxFrac, int minSig, int maxSig, NumberFormatter.RoundingPriority priority, boolean retain) {
         this.minFrac = minFrac;
         this.maxFrac = maxFrac;
         this.minSig = minSig;
         this.maxSig = maxSig;
         this.priority = priority;
         this.retain = retain;
      }

      public void apply(DecimalQuantity value) {
         int roundingMag1 = Precision.getRoundingMagnitudeFraction(this.maxFrac);
         int roundingMag2 = Precision.getRoundingMagnitudeSignificant(value, this.maxSig);
         int roundingMag;
         if (this.priority == NumberFormatter.RoundingPriority.RELAXED) {
            roundingMag = Math.min(roundingMag1, roundingMag2);
         } else {
            roundingMag = Math.max(roundingMag1, roundingMag2);
         }

         if (!value.isZeroish()) {
            int upperMag = value.getMagnitude();
            value.roundToMagnitude(roundingMag, this.mathContext);
            if (!value.isZeroish() && value.getMagnitude() != upperMag && roundingMag1 == roundingMag2) {
               ++roundingMag2;
            }
         }

         int displayMag1 = Precision.getDisplayMagnitudeFraction(this.minFrac);
         int displayMag2 = Precision.getDisplayMagnitudeSignificant(value, this.minSig);
         int displayMag;
         if (this.retain) {
            displayMag = Math.min(displayMag1, displayMag2);
         } else if (this.priority == NumberFormatter.RoundingPriority.RELAXED) {
            if (roundingMag2 <= roundingMag1) {
               displayMag = displayMag2;
            } else {
               displayMag = displayMag1;
            }
         } else {
            assert this.priority == NumberFormatter.RoundingPriority.STRICT;

            if (roundingMag2 <= roundingMag1) {
               displayMag = displayMag1;
            } else {
               displayMag = displayMag2;
            }
         }

         this.setResolvedMinFraction(value, Math.max(0, -displayMag));
      }

      FracSigRounderImpl createCopy() {
         FracSigRounderImpl copy = new FracSigRounderImpl(this.minFrac, this.maxFrac, this.minSig, this.maxSig, this.priority, this.retain);
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class IncrementRounderImpl extends Precision {
      final BigDecimal increment;

      public IncrementRounderImpl(BigDecimal increment) {
         this.increment = increment;
      }

      public void apply(DecimalQuantity value) {
         value.roundToIncrement(this.increment, this.mathContext);
         this.setResolvedMinFraction(value, Math.max(0, this.increment.scale()));
      }

      IncrementRounderImpl createCopy() {
         IncrementRounderImpl copy = new IncrementRounderImpl(this.increment);
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class IncrementOneRounderImpl extends IncrementRounderImpl {
      final int minFrac;
      final int maxFrac;

      public IncrementOneRounderImpl(BigDecimal increment, int minFrac, int maxFrac) {
         super(increment);
         this.minFrac = minFrac;
         this.maxFrac = maxFrac;
      }

      public void apply(DecimalQuantity value) {
         value.roundToMagnitude(-this.maxFrac, this.mathContext);
         this.setResolvedMinFraction(value, this.minFrac);
      }

      IncrementOneRounderImpl createCopy() {
         IncrementOneRounderImpl copy = new IncrementOneRounderImpl(this.increment, this.minFrac, this.maxFrac);
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class IncrementFiveRounderImpl extends IncrementRounderImpl {
      final int minFrac;
      final int maxFrac;

      public IncrementFiveRounderImpl(BigDecimal increment, int minFrac, int maxFrac) {
         super(increment);
         this.minFrac = minFrac;
         this.maxFrac = maxFrac;
      }

      public void apply(DecimalQuantity value) {
         value.roundToNickel(-this.maxFrac, this.mathContext);
         this.setResolvedMinFraction(value, this.minFrac);
      }

      IncrementFiveRounderImpl createCopy() {
         IncrementFiveRounderImpl copy = new IncrementFiveRounderImpl(this.increment, this.minFrac, this.maxFrac);
         this.createCopyHelper(copy);
         return copy;
      }
   }

   static class CurrencyRounderImpl extends CurrencyPrecision {
      final Currency.CurrencyUsage usage;

      public CurrencyRounderImpl(Currency.CurrencyUsage usage) {
         this.usage = usage;
      }

      public void apply(DecimalQuantity value) {
         throw new AssertionError();
      }

      CurrencyRounderImpl createCopy() {
         CurrencyRounderImpl copy = new CurrencyRounderImpl(this.usage);
         this.createCopyHelper(copy);
         return copy;
      }
   }
}
