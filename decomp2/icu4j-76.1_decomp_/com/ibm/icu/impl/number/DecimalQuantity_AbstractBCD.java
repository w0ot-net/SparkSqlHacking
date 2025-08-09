package com.ibm.icu.impl.number;

import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.text.UFieldPosition;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.FieldPosition;

public abstract class DecimalQuantity_AbstractBCD implements DecimalQuantity {
   protected int scale;
   protected int precision;
   protected byte flags;
   protected static final int NEGATIVE_FLAG = 1;
   protected static final int INFINITY_FLAG = 2;
   protected static final int NAN_FLAG = 4;
   protected double origDouble;
   protected int origDelta;
   protected boolean isApproximate;
   protected int lReqPos = 0;
   protected int rReqPos = 0;
   protected int exponent = 0;
   private static final double[] DOUBLE_MULTIPLIERS = new double[]{(double)1.0F, (double)10.0F, (double)100.0F, (double)1000.0F, (double)10000.0F, (double)100000.0F, (double)1000000.0F, (double)1.0E7F, (double)1.0E8F, (double)1.0E9F, (double)1.0E10F, 1.0E11, 1.0E12, 1.0E13, 1.0E14, 1.0E15, 1.0E16, 1.0E17, 1.0E18, 1.0E19, 1.0E20, 1.0E21};
   /** @deprecated */
   @Deprecated
   public boolean explicitExactDouble = false;
   static final byte[] INT64_BCD = new byte[]{9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 7, 5, 8, 0, 8};
   private static final int SECTION_LOWER_EDGE = -1;
   private static final int SECTION_UPPER_EDGE = -2;

   public void copyFrom(DecimalQuantity _other) {
      this.copyBcdFrom(_other);
      DecimalQuantity_AbstractBCD other = (DecimalQuantity_AbstractBCD)_other;
      this.lReqPos = other.lReqPos;
      this.rReqPos = other.rReqPos;
      this.scale = other.scale;
      this.precision = other.precision;
      this.flags = other.flags;
      this.origDouble = other.origDouble;
      this.origDelta = other.origDelta;
      this.isApproximate = other.isApproximate;
      this.exponent = other.exponent;
   }

   public DecimalQuantity_AbstractBCD clear() {
      this.lReqPos = 0;
      this.rReqPos = 0;
      this.flags = 0;
      this.setBcdToZero();
      return this;
   }

   public void setMinInteger(int minInt) {
      assert minInt >= 0;

      if (minInt < this.lReqPos) {
         minInt = this.lReqPos;
      }

      this.lReqPos = minInt;
   }

   public void setMinFraction(int minFrac) {
      assert minFrac >= 0;

      this.rReqPos = -minFrac;
   }

   public void applyMaxInteger(int maxInt) {
      assert maxInt >= 0;

      if (this.precision != 0) {
         if (maxInt <= this.scale) {
            this.setBcdToZero();
         } else {
            int magnitude = this.getMagnitude();
            if (maxInt <= magnitude) {
               this.popFromLeft(magnitude - maxInt + 1);
               this.compact();
            }

         }
      }
   }

   public long getPositionFingerprint() {
      long fingerprint = 0L;
      fingerprint ^= (long)(this.lReqPos << 16);
      fingerprint ^= (long)this.rReqPos << 32;
      return fingerprint;
   }

   public void roundToIncrement(BigDecimal roundingIncrement, MathContext mathContext) {
      assert roundingIncrement.stripTrailingZeros().precision() != 1 || roundingIncrement.stripTrailingZeros().unscaledValue().intValue() != 5 || roundingIncrement.stripTrailingZeros().unscaledValue().intValue() != 1;

      BigDecimal temp = this.toBigDecimal();
      temp = temp.divide(roundingIncrement, 0, mathContext.getRoundingMode()).multiply(roundingIncrement).round(mathContext);
      if (temp.signum() == 0) {
         this.setBcdToZero();
      } else {
         this.setToBigDecimal(temp);
      }

   }

   public void multiplyBy(BigDecimal multiplicand) {
      if (!this.isZeroish()) {
         BigDecimal temp = this.toBigDecimal();
         temp = temp.multiply(multiplicand);
         this.setToBigDecimal(temp);
      }
   }

   public void negate() {
      this.flags = (byte)(this.flags ^ 1);
   }

   public int getMagnitude() throws ArithmeticException {
      if (this.precision == 0) {
         throw new ArithmeticException("Magnitude is not well-defined for zero");
      } else {
         return this.scale + this.precision - 1;
      }
   }

   public void adjustMagnitude(int delta) {
      if (this.precision != 0) {
         this.scale = Utility.addExact(this.scale, delta);
         this.origDelta = Utility.addExact(this.origDelta, delta);
         Utility.addExact(this.scale, this.precision);
      }

   }

   public int getExponent() {
      return this.exponent;
   }

   public void adjustExponent(int delta) {
      this.exponent += delta;
   }

   public void resetExponent() {
      this.adjustMagnitude(this.exponent);
      this.exponent = 0;
   }

   public boolean isHasIntegerValue() {
      return this.scale >= 0;
   }

   public StandardPlural getStandardPlural(PluralRules rules) {
      if (rules == null) {
         return StandardPlural.OTHER;
      } else {
         String ruleString = rules.select((PluralRules.IFixedDecimal)this);
         return StandardPlural.orOtherFromString(ruleString);
      }
   }

   public double getPluralOperand(PluralRules.Operand operand) {
      assert !this.isApproximate;

      switch (operand) {
         case i:
            return this.isNegative() ? (double)(-this.toLong(true)) : (double)this.toLong(true);
         case f:
            return (double)this.toFractionLong(true);
         case t:
            return (double)this.toFractionLong(false);
         case v:
            return (double)this.fractionCount();
         case w:
            return (double)this.fractionCountWithoutTrailingZeros();
         case e:
            return (double)this.getExponent();
         case c:
            return (double)this.getExponent();
         default:
            return Math.abs(this.toDouble());
      }
   }

   public void populateUFieldPosition(FieldPosition fp) {
      if (fp instanceof UFieldPosition) {
         ((UFieldPosition)fp).setFractionDigits((int)this.getPluralOperand(PluralRules.Operand.v), (long)this.getPluralOperand(PluralRules.Operand.f));
      }

   }

   public int getUpperDisplayMagnitude() {
      assert !this.isApproximate;

      int magnitude = this.scale + this.precision;
      int result = this.lReqPos > magnitude ? this.lReqPos : magnitude;
      return result - 1;
   }

   public int getLowerDisplayMagnitude() {
      assert !this.isApproximate;

      int magnitude = this.scale;
      int result = this.rReqPos < magnitude ? this.rReqPos : magnitude;
      return result;
   }

   public byte getDigit(int magnitude) {
      assert !this.isApproximate;

      return this.getDigitPos(magnitude - this.scale);
   }

   private int fractionCount() {
      return Math.max(0, -this.getLowerDisplayMagnitude() - this.exponent);
   }

   private int fractionCountWithoutTrailingZeros() {
      return Math.max(-this.scale - this.exponent, 0);
   }

   public boolean isNegative() {
      return (this.flags & 1) != 0;
   }

   public Modifier.Signum signum() {
      boolean isZero = this.isZeroish() && !this.isInfinite();
      boolean isNeg = this.isNegative();
      if (isZero && isNeg) {
         return Modifier.Signum.NEG_ZERO;
      } else if (isZero) {
         return Modifier.Signum.POS_ZERO;
      } else {
         return isNeg ? Modifier.Signum.NEG : Modifier.Signum.POS;
      }
   }

   public boolean isInfinite() {
      return (this.flags & 2) != 0;
   }

   public boolean isNaN() {
      return (this.flags & 4) != 0;
   }

   public boolean isZeroish() {
      return this.precision == 0;
   }

   public void setToInt(int n) {
      this.setBcdToZero();
      this.flags = 0;
      if (n < 0) {
         this.flags = (byte)(this.flags | 1);
         n = -n;
      }

      if (n != 0) {
         this._setToInt(n);
         this.compact();
      }

   }

   private void _setToInt(int n) {
      if (n == Integer.MIN_VALUE) {
         this.readLongToBcd(-((long)n));
      } else {
         this.readIntToBcd(n);
      }

   }

   public void setToLong(long n) {
      this.setBcdToZero();
      this.flags = 0;
      if (n < 0L) {
         this.flags = (byte)(this.flags | 1);
         n = -n;
      }

      if (n != 0L) {
         this._setToLong(n);
         this.compact();
      }

   }

   private void _setToLong(long n) {
      if (n == Long.MIN_VALUE) {
         this.readBigIntegerToBcd(BigInteger.valueOf(n).negate());
      } else if (n <= 2147483647L) {
         this.readIntToBcd((int)n);
      } else {
         this.readLongToBcd(n);
      }

   }

   public void setToBigInteger(BigInteger n) {
      this.setBcdToZero();
      this.flags = 0;
      if (n.signum() == -1) {
         this.flags = (byte)(this.flags | 1);
         n = n.negate();
      }

      if (n.signum() != 0) {
         this._setToBigInteger(n);
         this.compact();
      }

   }

   private void _setToBigInteger(BigInteger n) {
      if (n.bitLength() < 32) {
         this.readIntToBcd(n.intValue());
      } else if (n.bitLength() < 64) {
         this.readLongToBcd(n.longValue());
      } else {
         this.readBigIntegerToBcd(n);
      }

   }

   public void setToDouble(double n) {
      this.setBcdToZero();
      this.flags = 0;
      if (Double.doubleToRawLongBits(n) < 0L) {
         this.flags = (byte)(this.flags | 1);
         n = -n;
      }

      if (Double.isNaN(n)) {
         this.flags = (byte)(this.flags | 4);
      } else if (Double.isInfinite(n)) {
         this.flags = (byte)(this.flags | 2);
      } else if (n != (double)0.0F) {
         this._setToDoubleFast(n);
         this.compact();
      }

   }

   private void _setToDoubleFast(double n) {
      this.isApproximate = true;
      this.origDouble = n;
      this.origDelta = 0;
      long ieeeBits = Double.doubleToLongBits(n);
      int exponent = (int)((ieeeBits & 9218868437227405312L) >> 52) - 1023;
      if (exponent <= 52 && (double)((long)n) == n) {
         this._setToLong((long)n);
      } else if (exponent != -1023 && exponent != 1024) {
         int fracLength = (int)((double)(52 - exponent) / 3.321928094887362);
         if (fracLength >= 0) {
            int i;
            for(i = fracLength; i >= 22; i -= 22) {
               n *= 1.0E22;
            }

            n *= DOUBLE_MULTIPLIERS[i];
         } else {
            int i;
            for(i = fracLength; i <= -22; i += 22) {
               n /= 1.0E22;
            }

            n /= DOUBLE_MULTIPLIERS[-i];
         }

         long result = Math.round(n);
         if (result != 0L) {
            this._setToLong(result);
            this.scale -= fracLength;
         }

      } else {
         this.convertToAccurateDouble();
      }
   }

   private void convertToAccurateDouble() {
      double n = this.origDouble;

      assert n != (double)0.0F;

      int delta = this.origDelta;
      this.setBcdToZero();
      String dstr = Double.toString(n);
      if (dstr.indexOf(69) != -1) {
         assert dstr.indexOf(46) == 1;

         int expPos = dstr.indexOf(69);
         this._setToLong(Long.parseLong(dstr.charAt(0) + dstr.substring(2, expPos)));
         this.scale += Integer.parseInt(dstr.substring(expPos + 1)) - (expPos - 1) + 1;
      } else if (dstr.charAt(0) == '0') {
         assert dstr.indexOf(46) == 1;

         this._setToLong(Long.parseLong(dstr.substring(2)));
         this.scale += 2 - dstr.length();
      } else if (dstr.charAt(dstr.length() - 1) == '0') {
         assert dstr.indexOf(46) == dstr.length() - 2;

         assert dstr.length() - 2 <= 18;

         this._setToLong(Long.parseLong(dstr.substring(0, dstr.length() - 2)));
      } else {
         int decimalPos = dstr.indexOf(46);
         this._setToLong(Long.parseLong(dstr.substring(0, decimalPos) + dstr.substring(decimalPos + 1)));
         this.scale += decimalPos - dstr.length() + 1;
      }

      this.scale += delta;
      this.compact();
      this.explicitExactDouble = true;
   }

   public void setToBigDecimal(BigDecimal n) {
      this.setBcdToZero();
      this.flags = 0;
      if (n.signum() == -1) {
         this.flags = (byte)(this.flags | 1);
         n = n.negate();
      }

      if (n.signum() != 0) {
         this._setToBigDecimal(n);
         this.compact();
      }

   }

   private void _setToBigDecimal(BigDecimal n) {
      int fracLength = n.scale();
      n = n.scaleByPowerOfTen(fracLength);
      BigInteger bi = n.toBigInteger();
      this._setToBigInteger(bi);
      this.scale -= fracLength;
   }

   public long toLong(boolean truncateIfOverflow) {
      assert truncateIfOverflow || this.fitsInLong();

      long result = 0L;
      int upperMagnitude = this.exponent + this.scale + this.precision - 1;
      if (truncateIfOverflow) {
         upperMagnitude = Math.min(upperMagnitude, 17);
      }

      for(int magnitude = upperMagnitude; magnitude >= 0; --magnitude) {
         result = result * 10L + (long)this.getDigitPos(magnitude - this.scale - this.exponent);
      }

      if (this.isNegative()) {
         result = -result;
      }

      return result;
   }

   public long toFractionLong(boolean includeTrailingZeros) {
      long result = 0L;
      int magnitude = -1 - this.exponent;
      int lowerMagnitude = this.scale;
      if (includeTrailingZeros) {
         lowerMagnitude = Math.min(lowerMagnitude, this.rReqPos);
      }

      while(magnitude >= lowerMagnitude && (double)result <= 1.0E17) {
         result = result * 10L + (long)this.getDigitPos(magnitude - this.scale);
         --magnitude;
      }

      if (!includeTrailingZeros) {
         while(result > 0L && result % 10L == 0L) {
            result /= 10L;
         }
      }

      return result;
   }

   public boolean fitsInLong() {
      if (!this.isInfinite() && !this.isNaN()) {
         if (this.isZeroish()) {
            return true;
         } else if (this.exponent + this.scale < 0) {
            return false;
         } else {
            int magnitude = this.getMagnitude();
            if (magnitude < 18) {
               return true;
            } else if (magnitude > 18) {
               return false;
            } else {
               for(int p = 0; p < this.precision; ++p) {
                  byte digit = this.getDigit(18 - p);
                  if (digit < INT64_BCD[p]) {
                     return true;
                  }

                  if (digit > INT64_BCD[p]) {
                     return false;
                  }
               }

               return this.isNegative();
            }
         }
      } else {
         return false;
      }
   }

   public double toDouble() {
      assert !this.isApproximate;

      if (this.isNaN()) {
         return Double.NaN;
      } else if (this.isInfinite()) {
         return this.isNegative() ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
      } else {
         StringBuilder sb = new StringBuilder();
         this.toScientificString(sb);
         return Double.parseDouble(sb.toString());
      }
   }

   public BigDecimal toBigDecimal() {
      if (this.isApproximate) {
         this.convertToAccurateDouble();
      }

      return this.bcdToBigDecimal();
   }

   private static int safeSubtract(int a, int b) {
      int diff = a - b;
      if (b < 0 && diff < a) {
         return Integer.MAX_VALUE;
      } else {
         return b > 0 && diff > a ? Integer.MIN_VALUE : diff;
      }
   }

   public void truncate() {
      if (this.scale < 0) {
         this.shiftRight(-this.scale);
         this.scale = 0;
         this.compact();
      }

   }

   public void roundToNickel(int magnitude, MathContext mathContext) {
      this.roundToMagnitude(magnitude, mathContext, true);
   }

   public void roundToMagnitude(int magnitude, MathContext mathContext) {
      this.roundToMagnitude(magnitude, mathContext, false);
   }

   private void roundToMagnitude(int magnitude, MathContext mathContext, boolean nickel) {
      int position = safeSubtract(magnitude, this.scale);
      int _mcPrecision = mathContext.getPrecision();
      if (_mcPrecision > 0 && this.precision - _mcPrecision > position) {
         position = this.precision - _mcPrecision;
      }

      byte trailingDigit = this.getDigitPos(position);
      if ((position > 0 || this.isApproximate || nickel && trailingDigit != 0 && trailingDigit != 5) && this.precision != 0) {
         byte leadingDigit = this.getDigitPos(safeSubtract(position, 1));
         int section;
         if (!this.isApproximate) {
            if (nickel && trailingDigit != 2 && trailingDigit != 7) {
               if (trailingDigit < 2) {
                  section = 1;
               } else if (trailingDigit < 5) {
                  section = 3;
               } else if (trailingDigit < 7) {
                  section = 1;
               } else {
                  section = 3;
               }
            } else if (leadingDigit < 5) {
               section = 1;
            } else if (leadingDigit > 5) {
               section = 3;
            } else {
               section = 2;

               for(int p = safeSubtract(position, 2); p >= 0; --p) {
                  if (this.getDigitPos(p) != 0) {
                     section = 3;
                     break;
                  }
               }
            }
         } else {
            int p = safeSubtract(position, 2);
            int minP = Math.max(0, this.precision - 14);
            if (leadingDigit != 0 || nickel && trailingDigit != 0 && trailingDigit != 5) {
               if (leadingDigit != 4 || nickel && trailingDigit != 2 && trailingDigit != 7) {
                  if (leadingDigit != 5 || nickel && trailingDigit != 2 && trailingDigit != 7) {
                     if (leadingDigit != 9 || nickel && trailingDigit != 4 && trailingDigit != 9) {
                        if (nickel && trailingDigit != 2 && trailingDigit != 7) {
                           if (trailingDigit < 2) {
                              section = 1;
                           } else if (trailingDigit < 5) {
                              section = 3;
                           } else if (trailingDigit < 7) {
                              section = 1;
                           } else {
                              section = 3;
                           }
                        } else if (leadingDigit < 5) {
                           section = 1;
                        } else {
                           section = 3;
                        }
                     } else {
                        for(section = -2; p >= minP; --p) {
                           if (this.getDigitPos(p) != 9) {
                              section = 3;
                              break;
                           }
                        }
                     }
                  } else {
                     for(section = 2; p >= minP; --p) {
                        if (this.getDigitPos(p) != 0) {
                           section = 3;
                           break;
                        }
                     }
                  }
               } else {
                  for(section = 2; p >= minP; --p) {
                     if (this.getDigitPos(p) != 9) {
                        section = 1;
                        break;
                     }
                  }
               }
            } else {
               for(section = -1; p >= minP; --p) {
                  if (this.getDigitPos(p) != 0) {
                     section = 1;
                     break;
                  }
               }
            }

            boolean roundsAtMidpoint = RoundingUtils.roundsAtMidpoint(mathContext.getRoundingMode().ordinal());
            if (safeSubtract(position, 1) < this.precision - 14 || roundsAtMidpoint && section == 2 || !roundsAtMidpoint && section < 0) {
               this.convertToAccurateDouble();
               this.roundToMagnitude(magnitude, mathContext, nickel);
               return;
            }

            this.isApproximate = false;
            this.origDouble = (double)0.0F;
            this.origDelta = 0;
            if (position <= 0 && (!nickel || trailingDigit == 0 || trailingDigit == 5)) {
               return;
            }

            if (section == -1) {
               section = 1;
            }

            if (section == -2) {
               section = 3;
            }
         }

         boolean isEven = nickel ? trailingDigit < 2 || trailingDigit > 7 || trailingDigit == 2 && section != 3 || trailingDigit == 7 && section == 3 : trailingDigit % 2 == 0;
         boolean roundDown = RoundingUtils.getRoundingDirection(isEven, this.isNegative(), section, mathContext.getRoundingMode().ordinal(), this);
         if (position >= this.precision) {
            assert trailingDigit == 0;

            this.setBcdToZero();
            this.scale = magnitude;
         } else {
            this.shiftRight(position);
         }

         if (nickel) {
            if (trailingDigit < 5 && roundDown) {
               this.setDigitPos(0, (byte)0);
               this.compact();
               return;
            }

            if (trailingDigit < 5 || roundDown) {
               this.setDigitPos(0, (byte)5);
               if (this.precision == 0) {
                  this.precision = 1;
               }

               return;
            }

            this.setDigitPos(0, (byte)9);
            trailingDigit = 9;
         }

         if (!roundDown) {
            if (trailingDigit == 9) {
               int bubblePos;
               for(bubblePos = 0; this.getDigitPos(bubblePos) == 9; ++bubblePos) {
               }

               this.shiftRight(bubblePos);
            }

            byte digit0 = this.getDigitPos(0);

            assert digit0 != 9;

            this.setDigitPos(0, (byte)(digit0 + 1));
            ++this.precision;
         }

         this.compact();
      }

   }

   public void roundToInfinity() {
      if (this.isApproximate) {
         this.convertToAccurateDouble();
      }

   }

   /** @deprecated */
   @Deprecated
   public void appendDigit(byte value, int leadingZeros, boolean appendAsInteger) {
      assert leadingZeros >= 0;

      if (value == 0) {
         if (appendAsInteger && this.precision != 0) {
            this.scale += leadingZeros + 1;
         }

      } else {
         if (this.scale > 0) {
            leadingZeros += this.scale;
            if (appendAsInteger) {
               this.scale = 0;
            }
         }

         this.shiftLeft(leadingZeros + 1);
         this.setDigitPos(0, value);
         if (appendAsInteger) {
            this.scale += leadingZeros + 1;
         }

      }
   }

   public String toPlainString() {
      StringBuilder sb = new StringBuilder();
      this.toPlainString(sb);
      return sb.toString();
   }

   public void toPlainString(StringBuilder result) {
      assert !this.isApproximate;

      if (this.isNegative()) {
         result.append('-');
      }

      if (this.precision == 0) {
         result.append('0');
      } else {
         int upper = this.scale + this.precision + this.exponent - 1;
         int lower = this.scale + this.exponent;
         if (upper < this.lReqPos - 1) {
            upper = this.lReqPos - 1;
         }

         if (lower > this.rReqPos) {
            lower = this.rReqPos;
         }

         int p = upper;
         if (upper < 0) {
            result.append('0');
         }

         while(p >= 0) {
            result.append((char)(48 + this.getDigitPos(p - this.scale - this.exponent)));
            --p;
         }

         if (lower < 0) {
            result.append('.');
         }

         while(p >= lower) {
            result.append((char)(48 + this.getDigitPos(p - this.scale - this.exponent)));
            --p;
         }

      }
   }

   public String toScientificString() {
      StringBuilder sb = new StringBuilder();
      this.toScientificString(sb);
      return sb.toString();
   }

   public void toScientificString(StringBuilder result) {
      assert !this.isApproximate;

      if (this.isNegative()) {
         result.append('-');
      }

      if (this.precision == 0) {
         result.append("0E+0");
      } else {
         int upperPos = this.precision - 1;
         int lowerPos = 0;
         result.append((char)(48 + this.getDigitPos(upperPos)));
         int p = upperPos - 1;
         if (p >= lowerPos) {
            result.append('.');

            while(p >= lowerPos) {
               result.append((char)(48 + this.getDigitPos(p)));
               --p;
            }
         }

         result.append('E');
         int _scale = upperPos + this.scale + this.exponent;
         if (_scale == Integer.MIN_VALUE) {
            result.append("-2147483648");
         } else {
            if (_scale < 0) {
               _scale *= -1;
               result.append('-');
            } else {
               result.append('+');
            }

            if (_scale == 0) {
               result.append('0');
            }

            int quot;
            for(int insertIndex = result.length(); _scale > 0; _scale = quot) {
               quot = _scale / 10;
               int rem = _scale % 10;
               result.insert(insertIndex, (char)(48 + rem));
            }

         }
      }
   }

   public String toExponentString() {
      StringBuilder sb = new StringBuilder();
      this.toExponentString(sb);
      return sb.toString();
   }

   private void toExponentString(StringBuilder result) {
      assert !this.isApproximate;

      if (this.isNegative()) {
         result.append('-');
      }

      int upper = this.scale + this.precision - 1;
      int lower = this.scale;
      if (upper < this.lReqPos - 1) {
         upper = this.lReqPos - 1;
      }

      if (lower > this.rReqPos) {
         lower = this.rReqPos;
      }

      int p = upper;
      if (upper < 0) {
         result.append('0');
      }

      while(p >= 0) {
         result.append((char)(48 + this.getDigitPos(p - this.scale)));
         --p;
      }

      if (lower < 0) {
         result.append('.');
      }

      while(p >= lower) {
         result.append((char)(48 + this.getDigitPos(p - this.scale)));
         --p;
      }

      if (this.exponent != 0) {
         result.append('c');
         result.append(this.exponent);
      }

   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else if (!(other instanceof DecimalQuantity_AbstractBCD)) {
         return false;
      } else {
         DecimalQuantity_AbstractBCD _other = (DecimalQuantity_AbstractBCD)other;
         boolean basicEquals = this.scale == _other.scale && this.precision == _other.precision && this.flags == _other.flags && this.lReqPos == _other.lReqPos && this.rReqPos == _other.rReqPos && this.isApproximate == _other.isApproximate;
         if (!basicEquals) {
            return false;
         } else if (this.precision == 0) {
            return true;
         } else if (!this.isApproximate) {
            for(int m = this.getUpperDisplayMagnitude(); m >= this.getLowerDisplayMagnitude(); --m) {
               if (this.getDigit(m) != _other.getDigit(m)) {
                  return false;
               }
            }

            return true;
         } else {
            return this.origDouble == _other.origDouble && this.origDelta == _other.origDelta;
         }
      }
   }

   protected abstract byte getDigitPos(int var1);

   protected abstract void setDigitPos(int var1, byte var2);

   protected abstract void shiftLeft(int var1);

   protected abstract void shiftRight(int var1);

   protected abstract void popFromLeft(int var1);

   protected abstract void setBcdToZero();

   protected abstract void readIntToBcd(int var1);

   protected abstract void readLongToBcd(long var1);

   protected abstract void readBigIntegerToBcd(BigInteger var1);

   protected abstract BigDecimal bcdToBigDecimal();

   protected abstract void copyBcdFrom(DecimalQuantity var1);

   protected abstract void compact();
}
