package org.apache.commons.text.numbers;

import java.text.DecimalFormatSymbols;
import java.util.Objects;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public enum DoubleFormat {
   PLAIN(PlainDoubleFormat::new),
   SCIENTIFIC(ScientificDoubleFormat::new),
   ENGINEERING(EngineeringDoubleFormat::new),
   MIXED(MixedDoubleFormat::new);

   private final Function factory;

   private DoubleFormat(Function factory) {
      this.factory = factory;
   }

   public Builder builder() {
      return new Builder(this.factory);
   }

   // $FF: synthetic method
   private static DoubleFormat[] $values() {
      return new DoubleFormat[]{PLAIN, SCIENTIFIC, ENGINEERING, MIXED};
   }

   private abstract static class AbstractDoubleFormat implements DoubleFunction, ParsedDecimal.FormatOptions {
      private final int maxPrecision;
      private final int minDecimalExponent;
      private final String positiveInfinity;
      private final String negativeInfinity;
      private final String nan;
      private final boolean fractionPlaceholder;
      private final boolean signedZero;
      private final char[] digits;
      private final char decimalSeparator;
      private final char groupingSeparator;
      private final boolean groupThousands;
      private final char minusSign;
      private final char[] exponentSeparatorChars;
      private final boolean alwaysIncludeExponent;

      AbstractDoubleFormat(Builder builder) {
         this.maxPrecision = builder.maxPrecision;
         this.minDecimalExponent = builder.minDecimalExponent;
         this.positiveInfinity = builder.infinity;
         this.negativeInfinity = builder.minusSign + builder.infinity;
         this.nan = builder.nan;
         this.fractionPlaceholder = builder.fractionPlaceholder;
         this.signedZero = builder.signedZero;
         this.digits = builder.digits.toCharArray();
         this.decimalSeparator = builder.decimalSeparator;
         this.groupingSeparator = builder.groupingSeparator;
         this.groupThousands = builder.groupThousands;
         this.minusSign = builder.minusSign;
         this.exponentSeparatorChars = builder.exponentSeparator.toCharArray();
         this.alwaysIncludeExponent = builder.alwaysIncludeExponent;
      }

      public String apply(double d) {
         if (Double.isFinite(d)) {
            return this.applyFinite(d);
         } else if (Double.isInfinite(d)) {
            return d > (double)0.0F ? this.positiveInfinity : this.negativeInfinity;
         } else {
            return this.nan;
         }
      }

      private String applyFinite(double d) {
         ParsedDecimal n = ParsedDecimal.from(d);
         int roundExponent = Math.max(n.getExponent(), this.minDecimalExponent);
         if (this.maxPrecision > 0) {
            roundExponent = Math.max(n.getScientificExponent() - this.maxPrecision + 1, roundExponent);
         }

         n.round(roundExponent);
         return this.applyFiniteInternal(n);
      }

      protected abstract String applyFiniteInternal(ParsedDecimal var1);

      public char getDecimalSeparator() {
         return this.decimalSeparator;
      }

      public char[] getDigits() {
         return this.digits;
      }

      public char[] getExponentSeparatorChars() {
         return this.exponentSeparatorChars;
      }

      public char getGroupingSeparator() {
         return this.groupingSeparator;
      }

      public char getMinusSign() {
         return this.minusSign;
      }

      public boolean isAlwaysIncludeExponent() {
         return this.alwaysIncludeExponent;
      }

      public boolean isGroupThousands() {
         return this.groupThousands;
      }

      public boolean isIncludeFractionPlaceholder() {
         return this.fractionPlaceholder;
      }

      public boolean isSignedZero() {
         return this.signedZero;
      }
   }

   public static final class Builder implements Supplier {
      private static final int DEFAULT_PLAIN_FORMAT_MAX_DECIMAL_EXPONENT = 6;
      private static final int DEFAULT_PLAIN_FORMAT_MIN_DECIMAL_EXPONENT = -3;
      private static final String DEFAULT_DECIMAL_DIGITS = "0123456789";
      private final Function factory;
      private int maxPrecision;
      private int minDecimalExponent;
      private int plainFormatMaxDecimalExponent;
      private int plainFormatMinDecimalExponent;
      private String infinity;
      private String nan;
      private boolean fractionPlaceholder;
      private boolean signedZero;
      private String digits;
      private char decimalSeparator;
      private char groupingSeparator;
      private boolean groupThousands;
      private char minusSign;
      private String exponentSeparator;
      private boolean alwaysIncludeExponent;

      private static String getDigitString(DecimalFormatSymbols symbols) {
         int zeroDelta = symbols.getZeroDigit() - "0123456789".charAt(0);
         char[] digitChars = new char["0123456789".length()];

         for(int i = 0; i < "0123456789".length(); ++i) {
            digitChars[i] = (char)("0123456789".charAt(i) + zeroDelta);
         }

         return String.valueOf(digitChars);
      }

      private Builder(Function factory) {
         this.minDecimalExponent = Integer.MIN_VALUE;
         this.plainFormatMaxDecimalExponent = 6;
         this.plainFormatMinDecimalExponent = -3;
         this.infinity = "Infinity";
         this.nan = "NaN";
         this.fractionPlaceholder = true;
         this.signedZero = true;
         this.digits = "0123456789";
         this.decimalSeparator = '.';
         this.groupingSeparator = ',';
         this.minusSign = '-';
         this.exponentSeparator = "E";
         this.factory = factory;
      }

      public Builder allowSignedZero(boolean signedZero) {
         this.signedZero = signedZero;
         return this;
      }

      public Builder alwaysIncludeExponent(boolean alwaysIncludeExponent) {
         this.alwaysIncludeExponent = alwaysIncludeExponent;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public DoubleFunction build() {
         return this.get();
      }

      public Builder decimalSeparator(char decimalSeparator) {
         this.decimalSeparator = decimalSeparator;
         return this;
      }

      public Builder digits(String digits) {
         Objects.requireNonNull(digits, "digits");
         if (digits.length() != "0123456789".length()) {
            throw new IllegalArgumentException("Digits string must contain exactly " + "0123456789".length() + " characters.");
         } else {
            this.digits = digits;
            return this;
         }
      }

      public Builder exponentSeparator(String exponentSeparator) {
         this.exponentSeparator = (String)Objects.requireNonNull(exponentSeparator, "exponentSeparator");
         return this;
      }

      public Builder formatSymbols(DecimalFormatSymbols symbols) {
         Objects.requireNonNull(symbols, "symbols");
         return this.digits(getDigitString(symbols)).decimalSeparator(symbols.getDecimalSeparator()).groupingSeparator(symbols.getGroupingSeparator()).minusSign(symbols.getMinusSign()).exponentSeparator(symbols.getExponentSeparator()).infinity(symbols.getInfinity()).nan(symbols.getNaN());
      }

      public DoubleFunction get() {
         return (DoubleFunction)this.factory.apply(this);
      }

      public Builder groupingSeparator(char groupingSeparator) {
         this.groupingSeparator = groupingSeparator;
         return this;
      }

      public Builder groupThousands(boolean groupThousands) {
         this.groupThousands = groupThousands;
         return this;
      }

      public Builder includeFractionPlaceholder(boolean fractionPlaceholder) {
         this.fractionPlaceholder = fractionPlaceholder;
         return this;
      }

      public Builder infinity(String infinity) {
         this.infinity = (String)Objects.requireNonNull(infinity, "infinity");
         return this;
      }

      public Builder maxPrecision(int maxPrecision) {
         this.maxPrecision = maxPrecision;
         return this;
      }

      public Builder minDecimalExponent(int minDecimalExponent) {
         this.minDecimalExponent = minDecimalExponent;
         return this;
      }

      public Builder minusSign(char minusSign) {
         this.minusSign = minusSign;
         return this;
      }

      public Builder nan(String nan) {
         this.nan = (String)Objects.requireNonNull(nan, "nan");
         return this;
      }

      public Builder plainFormatMaxDecimalExponent(int plainFormatMaxDecimalExponent) {
         this.plainFormatMaxDecimalExponent = plainFormatMaxDecimalExponent;
         return this;
      }

      public Builder plainFormatMinDecimalExponent(int plainFormatMinDecimalExponent) {
         this.plainFormatMinDecimalExponent = plainFormatMinDecimalExponent;
         return this;
      }
   }

   private static final class EngineeringDoubleFormat extends AbstractDoubleFormat {
      EngineeringDoubleFormat(Builder builder) {
         super(builder);
      }

      public String applyFiniteInternal(ParsedDecimal val) {
         return val.toEngineeringString(this);
      }
   }

   private static final class MixedDoubleFormat extends AbstractDoubleFormat {
      private final int plainMaxExponent;
      private final int plainMinExponent;

      MixedDoubleFormat(Builder builder) {
         super(builder);
         this.plainMaxExponent = builder.plainFormatMaxDecimalExponent;
         this.plainMinExponent = builder.plainFormatMinDecimalExponent;
      }

      protected String applyFiniteInternal(ParsedDecimal val) {
         int sciExp = val.getScientificExponent();
         return sciExp <= this.plainMaxExponent && sciExp >= this.plainMinExponent ? val.toPlainString(this) : val.toScientificString(this);
      }
   }

   private static final class PlainDoubleFormat extends AbstractDoubleFormat {
      PlainDoubleFormat(Builder builder) {
         super(builder);
      }

      protected String applyFiniteInternal(ParsedDecimal val) {
         return val.toPlainString(this);
      }
   }

   private static final class ScientificDoubleFormat extends AbstractDoubleFormat {
      ScientificDoubleFormat(Builder builder) {
         super(builder);
      }

      public String applyFiniteInternal(ParsedDecimal val) {
         return val.toScientificString(this);
      }
   }
}
