package org.apache.hadoop.hive.common.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.IntBuffer;
import org.apache.hive.common.util.Decimal128FastBuffer;

public final class Decimal128 extends Number implements Comparable {
   public static final short MAX_SCALE = 38;
   public static final short MIN_SCALE = 0;
   public static final Decimal128 ONE = (new Decimal128()).update(1L);
   public static final Decimal128 MAX_VALUE;
   public static final Decimal128 MIN_VALUE;
   private static final long serialVersionUID = 1L;
   private UnsignedInt128 unscaledValue;
   private short scale;
   private byte signum;
   private int[] tmpArray;

   public static int getIntsPerElement(int precision) {
      return UnsignedInt128.getIntsPerElement(precision) + 1;
   }

   public Decimal128() {
      this.tmpArray = new int[2];
      this.unscaledValue = new UnsignedInt128();
      this.scale = 0;
      this.signum = 0;
   }

   public Decimal128(Decimal128 o) {
      this.tmpArray = new int[2];
      this.unscaledValue = new UnsignedInt128(o.unscaledValue);
      this.scale = o.scale;
      this.signum = o.signum;
   }

   public Decimal128(double val, short scale) {
      this();
      this.update(val, scale);
   }

   public Decimal128(UnsignedInt128 unscaledVal, short scale, boolean negative) {
      this.tmpArray = new int[2];
      checkScaleRange(scale);
      this.unscaledValue = new UnsignedInt128(unscaledVal);
      this.scale = scale;
      if (this.unscaledValue.isZero()) {
         this.signum = 0;
      } else {
         this.signum = (byte)(negative ? -1 : 1);
      }

      this.unscaledValue.throwIfExceedsTenToThirtyEight();
   }

   public Decimal128(long val) {
      this(val, (short)0);
   }

   public Decimal128(long val, short scale) {
      this();
      this.update(val, scale);
   }

   public Decimal128(String str, short scale) {
      this();
      this.update(str, scale);
   }

   public Decimal128(char[] str, int offset, int length, short scale) {
      this();
      this.update(str, offset, length, scale);
   }

   public Decimal128 zeroClear() {
      this.unscaledValue.zeroClear();
      this.signum = 0;
      return this;
   }

   public boolean isZero() {
      assert this.signum == 0 && this.unscaledValue.isZero() || this.signum != 0 && !this.unscaledValue.isZero();

      return this.signum == 0;
   }

   public Decimal128 update(Decimal128 o) {
      this.unscaledValue.update(o.unscaledValue);
      this.scale = o.scale;
      this.signum = o.signum;
      return this;
   }

   public Decimal128 update(Decimal128 o, short scale) {
      this.update(o);
      this.changeScaleDestructive(scale);
      return this;
   }

   public Decimal128 update(long val) {
      return this.update(val, (short)0);
   }

   public Decimal128 update(long val, short scale) {
      this.scale = 0;
      if (val < 0L) {
         this.unscaledValue.update(-val);
         this.signum = -1;
      } else if (val == 0L) {
         this.zeroClear();
      } else {
         this.unscaledValue.update(val);
         this.signum = 1;
      }

      if (scale != 0) {
         this.changeScaleDestructive(scale);
      }

      return this;
   }

   public Decimal128 update(double val, short scale) {
      if (!Double.isInfinite(val) && !Double.isNaN(val)) {
         checkScaleRange(scale);
         this.scale = scale;
         long valBits = Double.doubleToLongBits(val);
         byte sign = (byte)(valBits >> 63 == 0L ? 1 : -1);
         short exponent = (short)((int)(valBits >> 52 & 2047L));
         long significand = exponent == 0 ? (valBits & 4503599627370495L) << 1 : valBits & 4503599627370495L | 4503599627370496L;
         exponent = (short)(exponent - 1075);
         if (significand == 0L) {
            this.zeroClear();
            return this;
         } else {
            for(this.signum = sign; (significand & 1L) == 0L; ++exponent) {
               significand >>= 1;
            }

            this.unscaledValue.update(significand);
            if (exponent >= 0) {
               this.unscaledValue.shiftLeftDestructiveCheckOverflow(exponent);
               this.unscaledValue.scaleUpTenDestructive(scale);
            } else {
               short twoScaleDown = (short)(-exponent);
               if (scale >= twoScaleDown) {
                  this.unscaledValue.shiftLeftDestructiveCheckOverflow(scale - twoScaleDown);
                  this.unscaledValue.scaleUpFiveDestructive(scale);
               } else {
                  this.unscaledValue.multiplyShiftDestructive(SqlMathUtil.POWER_FIVES_INT128[scale], (short)(twoScaleDown - scale));
               }
            }

            return this;
         }
      } else {
         throw new NumberFormatException("Infinite or NaN");
      }
   }

   public Decimal128 update(IntBuffer buf, int precision) {
      int scaleAndSignum = buf.get();
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update(buf, precision);

      assert this.signum == 0 == this.unscaledValue.isZero();

      return this;
   }

   public Decimal128 update128(IntBuffer buf) {
      int scaleAndSignum = buf.get();
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update128(buf);

      assert this.signum == 0 == this.unscaledValue.isZero();

      return this;
   }

   public Decimal128 update96(IntBuffer buf) {
      int scaleAndSignum = buf.get();
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update96(buf);

      assert this.signum == 0 == this.unscaledValue.isZero();

      return this;
   }

   public Decimal128 update64(IntBuffer buf) {
      int scaleAndSignum = buf.get();
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update64(buf);

      assert this.signum == 0 == this.unscaledValue.isZero();

      return this;
   }

   public Decimal128 update32(IntBuffer buf) {
      int scaleAndSignum = buf.get();
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update32(buf);

      assert this.signum == 0 == this.unscaledValue.isZero();

      return this;
   }

   public Decimal128 update(int[] array, int offset, int precision) {
      int scaleAndSignum = array[offset];
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update(array, offset + 1, precision);
      return this;
   }

   public Decimal128 update128(int[] array, int offset) {
      int scaleAndSignum = array[offset];
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update128(array, offset + 1);
      return this;
   }

   public Decimal128 update96(int[] array, int offset) {
      int scaleAndSignum = array[offset];
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update96(array, offset + 1);
      return this;
   }

   public Decimal128 update64(int[] array, int offset) {
      int scaleAndSignum = array[offset];
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update64(array, offset + 1);
      return this;
   }

   public Decimal128 update32(int[] array, int offset) {
      int scaleAndSignum = array[offset];
      this.scale = (short)(scaleAndSignum >> 16);
      this.signum = (byte)(scaleAndSignum & 255);
      this.unscaledValue.update32(array, offset + 1);
      return this;
   }

   public Decimal128 update(BigDecimal bigDecimal) {
      return this.update(bigDecimal.unscaledValue(), (short)bigDecimal.scale());
   }

   public Decimal128 update(BigInteger bigInt, short scale) {
      this.scale = scale;
      this.signum = (byte)bigInt.compareTo(BigInteger.ZERO);
      if (this.signum == 0) {
         this.update(0L);
      } else if (this.signum < 0) {
         this.unscaledValue.update(bigInt.negate());
      } else {
         this.unscaledValue.update(bigInt);
      }

      return this;
   }

   public Decimal128 update(String str, short scale) {
      return this.update(str.toCharArray(), 0, str.length(), scale);
   }

   public Decimal128 update(char[] str, int offset, int length, short scale) {
      int end = offset + length;

      assert end <= str.length;

      int cursor = offset;
      boolean negative = false;
      if (str[offset] == '+') {
         cursor = offset + 1;
      } else if (str[offset] == '-') {
         negative = true;
         cursor = offset + 1;
      }

      while(cursor < end && str[cursor] == '0') {
         ++cursor;
      }

      this.scale = scale;
      this.zeroClear();
      if (cursor == end) {
         return this;
      } else {
         int accumulated = 0;
         int accumulatedCount = 0;
         boolean fractional = false;
         int fractionalDigits = 0;

         int exponent;
         for(exponent = 0; cursor < end; ++cursor) {
            if (str[cursor] == '.') {
               if (fractional) {
                  throw new NumberFormatException("Invalid string:" + new String(str, offset, length));
               }

               fractional = true;
            } else if (str[cursor] >= '0' && str[cursor] <= '9') {
               if (accumulatedCount == 9) {
                  this.unscaledValue.scaleUpTenDestructive((short)accumulatedCount);
                  this.unscaledValue.addDestructive(accumulated);
                  accumulated = 0;
                  accumulatedCount = 0;
               }

               int digit = str[cursor] - 48;
               accumulated = accumulated * 10 + digit;
               ++accumulatedCount;
               if (fractional) {
                  ++fractionalDigits;
               }
            } else {
               if (str[cursor] != 'e' && str[cursor] != 'E') {
                  throw new NumberFormatException("Invalid string:" + new String(str, offset, length));
               }

               ++cursor;
               boolean exponentNagative = false;
               if (str[cursor] == '+') {
                  ++cursor;
               } else if (str[cursor] == '-') {
                  exponentNagative = true;
                  ++cursor;
               }

               for(; cursor < end; ++cursor) {
                  if (str[cursor] >= '0' && str[cursor] <= '9') {
                     int exponentDigit = str[cursor] - 48;
                     exponent *= 10;
                     exponent += exponentDigit;
                  }
               }

               if (exponentNagative) {
                  exponent = -exponent;
               }
            }
         }

         if (accumulatedCount > 0) {
            this.unscaledValue.scaleUpTenDestructive((short)accumulatedCount);
            this.unscaledValue.addDestructive(accumulated);
         }

         int scaleAdjust = scale - fractionalDigits + exponent;
         if (scaleAdjust > 0) {
            this.unscaledValue.scaleUpTenDestructive((short)scaleAdjust);
         } else if (scaleAdjust < 0) {
            this.unscaledValue.scaleDownTenDestructive((short)(-scaleAdjust));
         }

         this.signum = (byte)(this.unscaledValue.isZero() ? 0 : (negative ? -1 : 1));
         return this;
      }
   }

   public int fastSerializeForHiveDecimal(Decimal128FastBuffer scratch) {
      return this.unscaledValue.fastSerializeForHiveDecimal(scratch, this.signum);
   }

   public void serializeTo(int[] array, int offset, int precision) {
      array[offset] = this.scale << 16 | this.signum & 255;
      this.unscaledValue.serializeTo(array, offset + 1, precision);
   }

   public void serializeTo128(int[] array, int offset) {
      array[offset] = this.scale << 16 | this.signum & 255;
      this.unscaledValue.serializeTo128(array, offset + 1);
   }

   public void serializeTo96(int[] array, int offset) {
      array[offset] = this.scale << 16 | this.signum & 255;
      this.unscaledValue.serializeTo96(array, offset + 1);
   }

   public void serializeTo64(int[] array, int offset) {
      array[offset] = this.scale << 16 | this.signum & 255;
      this.unscaledValue.serializeTo64(array, offset + 1);
   }

   public void serializeTo32(int[] array, int offset) {
      array[offset] = this.scale << 16 | this.signum & 255;
      this.unscaledValue.serializeTo32(array, offset + 1);
   }

   public void serializeTo(IntBuffer buf, int precision) {
      buf.put(this.scale << 16 | this.signum & 255);
      this.unscaledValue.serializeTo(buf, precision);
   }

   public void serializeTo128(IntBuffer buf) {
      buf.put(this.scale << 16 | this.signum & 255);
      this.unscaledValue.serializeTo128(buf);
   }

   public void serializeTo96(IntBuffer buf) {
      buf.put(this.scale << 16 | this.signum & 255);
      this.unscaledValue.serializeTo96(buf);
   }

   public void serializeTo64(IntBuffer buf) {
      buf.put(this.scale << 16 | this.signum & 255);
      this.unscaledValue.serializeTo64(buf);
   }

   public void serializeTo32(IntBuffer buf) {
      buf.put(this.scale << 16 | this.signum & 255);
      this.unscaledValue.serializeTo32(buf);
   }

   public void changeScaleDestructive(short scale) {
      if (scale != this.scale) {
         checkScaleRange(scale);
         short scaleDown = (short)(this.scale - scale);
         if (scaleDown > 0) {
            this.unscaledValue.scaleDownTenDestructive(scaleDown);
            if (this.unscaledValue.isZero()) {
               this.signum = 0;
            }
         } else if (scaleDown < 0) {
            this.unscaledValue.scaleUpTenDestructive((short)(-scaleDown));
         }

         this.scale = scale;
         this.unscaledValue.throwIfExceedsTenToThirtyEight();
      }
   }

   public static void add(Decimal128 left, Decimal128 right, Decimal128 result, short scale) {
      result.update(left);
      result.addDestructive(right, scale);
   }

   public Decimal128 addDestructive(Decimal128 right, short scale) {
      this.changeScaleDestructive(scale);
      if (right.signum == 0) {
         return this;
      } else if (this.signum == 0) {
         this.update(right);
         this.changeScaleDestructive(scale);
         return this;
      } else {
         short rightScaleTen = (short)(scale - right.scale);
         if (this.signum == right.signum) {
            this.unscaledValue.addDestructiveScaleTen(right.unscaledValue, rightScaleTen);
         } else {
            byte cmp = UnsignedInt128.differenceScaleTen(this.unscaledValue, right.unscaledValue, this.unscaledValue, rightScaleTen);
            if (cmp == 0) {
               this.signum = 0;
            } else if (cmp < 0) {
               this.signum = right.signum;
            }
         }

         this.unscaledValue.throwIfExceedsTenToThirtyEight();
         return this;
      }
   }

   public static void subtract(Decimal128 left, Decimal128 right, Decimal128 result, short scale) {
      result.update(left);
      result.subtractDestructive(right, scale);
   }

   public Decimal128 subtractDestructive(Decimal128 right, short scale) {
      this.changeScaleDestructive(scale);
      if (right.signum == 0) {
         return this;
      } else if (this.signum == 0) {
         this.update(right);
         this.changeScaleDestructive(scale);
         this.negateDestructive();
         return this;
      } else {
         short rightScaleTen = (short)(scale - right.scale);
         if (this.signum != right.signum) {
            this.unscaledValue.addDestructiveScaleTen(right.unscaledValue, rightScaleTen);
         } else {
            byte cmp = UnsignedInt128.differenceScaleTen(this.unscaledValue, right.unscaledValue, this.unscaledValue, rightScaleTen);
            if (cmp == 0) {
               this.signum = 0;
            } else if (cmp < 0) {
               this.signum = (byte)(-right.signum);
            }
         }

         this.unscaledValue.throwIfExceedsTenToThirtyEight();
         return this;
      }
   }

   public static void multiply(Decimal128 left, Decimal128 right, Decimal128 result, short scale) {
      if (result != left && result != right) {
         result.update(left);
         result.multiplyDestructive(right, scale);
      } else {
         throw new IllegalArgumentException("result object cannot be left or right operand");
      }
   }

   public void multiplyDestructiveNativeDecimal128(Decimal128 right, short newScale) {
      if (this.signum != 0 && right.signum != 0) {
         short currentTotalScale = (short)(this.scale + right.scale);
         short scaleBack = (short)(currentTotalScale - newScale);
         if (scaleBack > 0) {
            this.unscaledValue.multiplyScaleDownTenDestructive(right.unscaledValue, scaleBack);
         } else {
            this.unscaledValue.multiplyDestructive(right.unscaledValue);
            this.unscaledValue.scaleUpTenDestructive((short)(-scaleBack));
         }

         this.scale = newScale;
         this.signum *= right.signum;
         if (this.unscaledValue.isZero()) {
            this.signum = 0;
         }

         this.unscaledValue.throwIfExceedsTenToThirtyEight();
      } else {
         this.zeroClear();
         this.scale = newScale;
      }
   }

   public void multiplyDestructive(Decimal128 right, short newScale) {
      HiveDecimal rightHD = HiveDecimal.create(right.toBigDecimal());
      HiveDecimal thisHD = HiveDecimal.create(this.toBigDecimal());
      HiveDecimal result = thisHD.multiply(rightHD);
      if (result == null) {
         throw new ArithmeticException("null multiply result");
      } else {
         this.update(result.bigDecimalValue().toPlainString(), newScale);
         this.unscaledValue.throwIfExceedsTenToThirtyEight();
      }
   }

   public static void divide(Decimal128 left, Decimal128 right, Decimal128 quotient, short scale) {
      if (quotient != left && quotient != right) {
         quotient.update(left);
         quotient.divideDestructive(right, scale);
      } else {
         throw new IllegalArgumentException("result object cannot be left or right operand");
      }
   }

   public void divideDestructiveNativeDecimal128(Decimal128 right, short newScale, Decimal128 remainder) {
      if (right.signum == 0) {
         SqlMathUtil.throwZeroDivisionException();
      }

      if (this.signum == 0) {
         this.scale = newScale;
         remainder.update(this);
      } else {
         short scaleBack = (short)(this.scale - right.scale - newScale);
         if (scaleBack >= 0) {
            this.unscaledValue.divideDestructive(right.unscaledValue, remainder.unscaledValue);
            this.unscaledValue.scaleDownTenDestructive(scaleBack);
            remainder.unscaledValue.scaleDownTenDestructive(scaleBack);
         } else {
            this.unscaledValue.divideScaleUpTenDestructive(right.unscaledValue, (short)(-scaleBack), remainder.unscaledValue);
         }

         this.scale = newScale;
         this.signum = (byte)(this.unscaledValue.isZero() ? 0 : this.signum * right.signum);
         remainder.scale = this.scale;
         remainder.signum = (byte)(remainder.unscaledValue.isZero() ? 0 : 1);
         this.unscaledValue.throwIfExceedsTenToThirtyEight();
      }
   }

   public void divideDestructive(Decimal128 right, short newScale) {
      HiveDecimal rightHD = HiveDecimal.create(right.toBigDecimal());
      HiveDecimal thisHD = HiveDecimal.create(this.toBigDecimal());
      HiveDecimal result = thisHD.divide(rightHD);
      if (result == null) {
         throw new ArithmeticException("null divide result");
      } else {
         this.update(result.bigDecimalValue().toPlainString(), newScale);
         this.unscaledValue.throwIfExceedsTenToThirtyEight();
      }
   }

   public static void modulo(Decimal128 left, Decimal128 right, Decimal128 result, short scale) {
      divide(left, right, result, scale);
      result.zeroFractionPart();
      result.multiplyDestructive(right, scale);
      result.negateDestructive();
      result.addDestructive(left, scale);
   }

   public void absDestructive() {
      if (this.signum < 0) {
         this.signum = 1;
      }

   }

   public void negateDestructive() {
      this.signum = (byte)(-this.signum);
   }

   public double sqrtAsDouble() {
      if (this.signum == 0) {
         return (double)0.0F;
      } else if (this.signum < 0) {
         throw new ArithmeticException("sqrt will not be a real number");
      } else {
         double val = this.doubleValue();
         return Math.sqrt(val);
      }
   }

   public double powAsDouble(double n) {
      if (this.signum == 0) {
         return (double)0.0F;
      } else {
         double val = this.doubleValue();
         double result = Math.pow(val, n);
         if (Double.isInfinite(result) || Double.isNaN(result)) {
            SqlMathUtil.throwOverflowException();
         }

         return result;
      }
   }

   public byte getSignum() {
      return this.signum;
   }

   public short getScale() {
      return this.scale;
   }

   public UnsignedInt128 getUnscaledValue() {
      return this.unscaledValue;
   }

   public int compareTo(Decimal128 val) {
      if (val == this) {
         return 0;
      } else if (this.signum != val.signum) {
         return this.signum - val.signum;
      } else {
         int cmp;
         if (this.scale >= val.scale) {
            cmp = this.unscaledValue.compareToScaleTen(val.unscaledValue, (short)(this.scale - val.scale));
         } else {
            cmp = -val.unscaledValue.compareToScaleTen(this.unscaledValue, (short)(val.scale - this.scale));
         }

         return cmp * this.signum;
      }
   }

   public boolean equals(Object x) {
      if (x == this) {
         return true;
      } else if (!(x instanceof Decimal128)) {
         return false;
      } else {
         Decimal128 xDec = (Decimal128)x;
         if (this.scale != xDec.scale) {
            return false;
         } else {
            return this.signum != xDec.signum ? false : this.unscaledValue.equals(xDec.unscaledValue);
         }
      }
   }

   public int hashCode() {
      return this.signum == 0 ? 0 : this.signum * (this.scale * 31 + this.unscaledValue.hashCode());
   }

   public long longValue() {
      if (this.signum == 0) {
         return 0L;
      } else if (this.scale == 0) {
         long ret = (long)this.unscaledValue.getV1();
         ret <<= 32;
         ret |= 4294967295L & (long)this.unscaledValue.getV0();
         return this.signum >= 0 ? ret : -ret;
      } else {
         HiveDecimal hd = HiveDecimal.create(this.toBigDecimal());
         return hd.longValue();
      }
   }

   public int intValue() {
      if (this.signum == 0) {
         return 0;
      } else {
         int ret;
         if (this.scale == 0) {
            ret = this.unscaledValue.getV0();
         } else {
            UnsignedInt128 tmp = new UnsignedInt128(this.unscaledValue);
            tmp.scaleDownTenDestructive(this.scale);
            ret = tmp.getV0();
         }

         return SqlMathUtil.setSignBitInt(ret, this.signum > 0);
      }
   }

   public float floatValue() {
      return Float.parseFloat(this.toFormalString());
   }

   public double doubleValue() {
      return Double.parseDouble(this.toFormalString());
   }

   public BigDecimal toBigDecimal() {
      return new BigDecimal(this.toFormalString());
   }

   public void checkPrecisionOverflow(int precision) {
      if (precision > 0 && precision <= 38) {
         if (this.unscaledValue.compareTo(SqlMathUtil.POWER_TENS_INT128[precision]) >= 0) {
            SqlMathUtil.throwOverflowException();
         }

      } else {
         throw new IllegalArgumentException("Invalid precision " + precision);
      }
   }

   private static void checkScaleRange(short scale) {
      if (scale < 0) {
         throw new ArithmeticException("Decimal128 does not support negative scaling");
      } else if (scale > 38) {
         throw new ArithmeticException("Beyond possible Decimal128 scaling");
      }
   }

   public String getHiveDecimalString() {
      if (this.signum == 0) {
         return "0";
      } else {
         StringBuilder buf = new StringBuilder(50);
         if (this.signum < 0) {
            buf.append('-');
         }

         char[] unscaled = this.unscaledValue.getDigitsArray(this.tmpArray);
         int unscaledLength = this.tmpArray[0];
         int trailingZeros = this.tmpArray[1];
         int numIntegerDigits = unscaledLength - this.scale;
         if (numIntegerDigits > 0) {
            for(int i = 0; i < numIntegerDigits; ++i) {
               buf.append(unscaled[i]);
            }

            if (this.scale > trailingZeros) {
               buf.append('.');

               for(int i = numIntegerDigits; i < unscaledLength - trailingZeros; ++i) {
                  buf.append(unscaled[i]);
               }
            }
         } else {
            buf.append('0');
            if (this.scale > trailingZeros) {
               buf.append('.');

               for(int i = unscaledLength; i < this.scale; ++i) {
                  buf.append('0');
               }

               for(int i = 0; i < unscaledLength - trailingZeros; ++i) {
                  buf.append(unscaled[i]);
               }
            }
         }

         return new String(buf);
      }
   }

   public String toFormalString() {
      if (this.signum == 0) {
         return "0";
      } else {
         StringBuilder buf = new StringBuilder(50);
         if (this.signum < 0) {
            buf.append('-');
         }

         String unscaled = this.unscaledValue.toFormalString();
         if (unscaled.length() > this.scale) {
            buf.append(unscaled, 0, unscaled.length() - this.scale);
            if (this.scale > 0) {
               buf.append('.');
               buf.append(unscaled, unscaled.length() - this.scale, unscaled.length());
            }
         } else {
            buf.append('0');
            if (this.scale > 0) {
               buf.append('.');

               for(int i = unscaled.length(); i < this.scale; ++i) {
                  buf.append('0');
               }

               buf.append(unscaled);
            }
         }

         return new String(buf);
      }
   }

   public String toString() {
      return this.toFormalString() + "(Decimal128: scale=" + this.scale + ", signum=" + this.signum + ", BigDecimal.toString=" + this.toBigDecimal().toString() + ", unscaledValue=[" + this.unscaledValue.toString() + "])";
   }

   public void setNullDataValue() {
      this.unscaledValue.update(1, 0, 0, 0);
   }

   public void updateFixedPoint(long val, short scale) {
      this.scale = scale;
      if (val < 0L) {
         this.unscaledValue.update(-val);
         this.signum = -1;
      } else if (val == 0L) {
         this.zeroClear();
      } else {
         this.unscaledValue.update(val);
         this.signum = 1;
      }

   }

   public void zeroFractionPart(UnsignedInt128 scratch) {
      short placesToRemove = this.getScale();
      if (placesToRemove != 0) {
         UnsignedInt128 powerTenDivisor = SqlMathUtil.POWER_TENS_INT128[placesToRemove];
         this.getUnscaledValue().divideDestructive(powerTenDivisor, scratch);
         this.getUnscaledValue().scaleUpTenDestructive(placesToRemove);
         if (this.unscaledValue.isZero()) {
            this.signum = 0;
         }

      }
   }

   public void zeroFractionPart() {
      UnsignedInt128 scratch = new UnsignedInt128();
      this.zeroFractionPart(scratch);
   }

   public Decimal128 squareDestructive() {
      this.multiplyDestructive(this, this.getScale());
      return this;
   }

   public Decimal128 updateVarianceDestructive(Decimal128 scratch, Decimal128 value, Decimal128 sum, long count) {
      scratch.update(count);
      scratch.multiplyDestructive(value, value.getScale());
      scratch.subtractDestructive(sum, sum.getScale());
      scratch.squareDestructive();
      scratch.unscaledValue.divideDestructive(count * (count - 1L));
      this.addDestructive(scratch, this.getScale());
      return this;
   }

   public Decimal128 fastUpdateFromInternalStorage(byte[] internalStorage, short scale) {
      this.scale = scale;
      this.signum = this.unscaledValue.fastUpdateFromInternalStorage(internalStorage);
      return this;
   }

   public void setUnscaledValue(UnsignedInt128 unscaledValue) {
      this.unscaledValue = unscaledValue;
   }

   public void setScale(short scale) {
      this.scale = scale;
   }

   public void setSignum(byte signum) {
      this.signum = signum;
   }

   static {
      MAX_VALUE = (new Decimal128(UnsignedInt128.TEN_TO_THIRTYEIGHT, (short)0, false)).subtractDestructive(ONE, (short)0);
      MIN_VALUE = (new Decimal128(UnsignedInt128.TEN_TO_THIRTYEIGHT, (short)0, true)).addDestructive(ONE, (short)0);
   }
}
