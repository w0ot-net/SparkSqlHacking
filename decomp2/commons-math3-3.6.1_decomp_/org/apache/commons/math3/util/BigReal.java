package org.apache.commons.math3.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class BigReal implements FieldElement, Comparable, Serializable {
   public static final BigReal ZERO;
   public static final BigReal ONE;
   private static final long serialVersionUID = 4984534880991310382L;
   private final BigDecimal d;
   private RoundingMode roundingMode;
   private int scale;

   public BigReal(BigDecimal val) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = val;
   }

   public BigReal(BigInteger val) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val);
   }

   public BigReal(BigInteger unscaledVal, int scale) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(unscaledVal, scale);
   }

   public BigReal(BigInteger unscaledVal, int scale, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(unscaledVal, scale, mc);
   }

   public BigReal(BigInteger val, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val, mc);
   }

   public BigReal(char[] in) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(in);
   }

   public BigReal(char[] in, int offset, int len) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(in, offset, len);
   }

   public BigReal(char[] in, int offset, int len, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(in, offset, len, mc);
   }

   public BigReal(char[] in, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(in, mc);
   }

   public BigReal(double val) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val);
   }

   public BigReal(double val, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val, mc);
   }

   public BigReal(int val) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val);
   }

   public BigReal(int val, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val, mc);
   }

   public BigReal(long val) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val);
   }

   public BigReal(long val, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val, mc);
   }

   public BigReal(String val) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val);
   }

   public BigReal(String val, MathContext mc) {
      this.roundingMode = RoundingMode.HALF_UP;
      this.scale = 64;
      this.d = new BigDecimal(val, mc);
   }

   public RoundingMode getRoundingMode() {
      return this.roundingMode;
   }

   public void setRoundingMode(RoundingMode roundingMode) {
      this.roundingMode = roundingMode;
   }

   public int getScale() {
      return this.scale;
   }

   public void setScale(int scale) {
      this.scale = scale;
   }

   public BigReal add(BigReal a) {
      return new BigReal(this.d.add(a.d));
   }

   public BigReal subtract(BigReal a) {
      return new BigReal(this.d.subtract(a.d));
   }

   public BigReal negate() {
      return new BigReal(this.d.negate());
   }

   public BigReal divide(BigReal a) throws MathArithmeticException {
      try {
         return new BigReal(this.d.divide(a.d, this.scale, this.roundingMode));
      } catch (ArithmeticException var3) {
         throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
      }
   }

   public BigReal reciprocal() throws MathArithmeticException {
      try {
         return new BigReal(BigDecimal.ONE.divide(this.d, this.scale, this.roundingMode));
      } catch (ArithmeticException var2) {
         throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED, new Object[0]);
      }
   }

   public BigReal multiply(BigReal a) {
      return new BigReal(this.d.multiply(a.d));
   }

   public BigReal multiply(int n) {
      return new BigReal(this.d.multiply(new BigDecimal(n)));
   }

   public int compareTo(BigReal a) {
      return this.d.compareTo(a.d);
   }

   public double doubleValue() {
      return this.d.doubleValue();
   }

   public BigDecimal bigDecimalValue() {
      return this.d;
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else {
         return other instanceof BigReal ? this.d.equals(((BigReal)other).d) : false;
      }
   }

   public int hashCode() {
      return this.d.hashCode();
   }

   public Field getField() {
      return BigRealField.getInstance();
   }

   static {
      ZERO = new BigReal(BigDecimal.ZERO);
      ONE = new BigReal(BigDecimal.ONE);
   }
}
