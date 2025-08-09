package org.apache.hadoop.hive.common.type;

import java.util.Arrays;

public final class SqlMathUtil {
   public static final long NEGATIVE_LONG_MASK = Long.MIN_VALUE;
   public static final long FULLBITS_63 = Long.MAX_VALUE;
   public static final int NEGATIVE_INT_MASK = Integer.MIN_VALUE;
   public static final long LONG_MASK = 4294967295L;
   public static final int FULLBITS_31 = Integer.MAX_VALUE;
   public static final int FULLBITS_32 = -1;
   public static final int MAX_POWER_FIVE_INT31 = 13;
   public static final int[] POWER_FIVES_INT31 = new int[14];
   public static final int MAX_POWER_FIVE_INT63 = 27;
   public static final long[] POWER_FIVES_INT63 = new long[28];
   public static final int MAX_POWER_FIVE_INT128 = 55;
   public static final UnsignedInt128[] POWER_FIVES_INT128 = new UnsignedInt128[56];
   public static final UnsignedInt128[] INVERSE_POWER_FIVES_INT128 = new UnsignedInt128[56];
   public static final int MAX_POWER_TEN_INT31 = 9;
   public static final int[] POWER_TENS_INT31 = new int[10];
   public static final int[] ROUND_POWER_TENS_INT31 = new int[10];
   public static final int MAX_POWER_TEN_INT128 = 38;
   public static final UnsignedInt128[] POWER_TENS_INT128 = new UnsignedInt128[39];
   public static final UnsignedInt128[] ROUND_POWER_TENS_INT128 = new UnsignedInt128[39];
   public static final UnsignedInt128[] INVERSE_POWER_TENS_INT128 = new UnsignedInt128[39];
   public static final int[] INVERSE_POWER_TENS_INT128_WORD_SHIFTS = new int[39];
   private static final byte[] BIT_LENGTH = new byte[256];
   private static final long BASE = 4294967296L;

   public static int setSignBitInt(int val, boolean positive) {
      return positive ? val & Integer.MAX_VALUE : val | Integer.MIN_VALUE;
   }

   public static long setSignBitLong(long val, boolean positive) {
      return positive ? val & Long.MAX_VALUE : val | Long.MIN_VALUE;
   }

   public static short bitLengthInWord(int word) {
      if (word < 0) {
         return 32;
      } else if (word < 65536) {
         return word < 256 ? (short)BIT_LENGTH[word] : (short)(BIT_LENGTH[word >>> 8] + 8);
      } else {
         return word < 16777216 ? (short)(BIT_LENGTH[word >>> 16] + 16) : (short)(BIT_LENGTH[word >>> 24] + 24);
      }
   }

   public static short bitLength(int v0, int v1, int v2, int v3) {
      if (v3 != 0) {
         return (short)(bitLengthInWord(v3) + 96);
      } else if (v2 != 0) {
         return (short)(bitLengthInWord(v2) + 64);
      } else {
         return v1 != 0 ? (short)(bitLengthInWord(v1) + 32) : bitLengthInWord(v0);
      }
   }

   public static int compareUnsignedInt(int x, int y) {
      if (x == y) {
         return 0;
      } else {
         return x + Integer.MIN_VALUE < y + Integer.MIN_VALUE ? -1 : 1;
      }
   }

   public static int compareUnsignedLong(long x, long y) {
      if (x == y) {
         return 0;
      } else {
         return x + Long.MIN_VALUE < y + Long.MIN_VALUE ? -1 : 1;
      }
   }

   public static long divideUnsignedLong(long dividend, long divisor) {
      if (divisor < 0L) {
         return compareUnsignedLong(dividend, divisor) < 0 ? 0L : 1L;
      } else if (dividend >= 0L) {
         return dividend / divisor;
      } else {
         long quotient = (dividend >>> 1) / divisor << 1;
         long remainder = dividend - quotient * divisor;
         return compareUnsignedLong(remainder, divisor) >= 0 ? quotient + 1L : quotient;
      }
   }

   public static long remainderUnsignedLong(long dividend, long divisor) {
      if (divisor < 0L) {
         return compareUnsignedLong(dividend, divisor) < 0 ? dividend : dividend - divisor;
      } else if (dividend >= 0L) {
         return dividend % divisor;
      } else {
         long quotient = (dividend >>> 1) / divisor << 1;
         long remainder = dividend - quotient * divisor;
         return compareUnsignedLong(remainder, divisor) >= 0 ? remainder - divisor : remainder;
      }
   }

   public static long combineInts(int lo, int hi) {
      return ((long)hi & 4294967295L) << 32 | (long)lo & 4294967295L;
   }

   public static int extractHiInt(long val) {
      return (int)(val >> 32);
   }

   public static int extractLowInt(long val) {
      return (int)val;
   }

   static void throwOverflowException() {
      throw new ArithmeticException("Overflow");
   }

   static void throwZeroDivisionException() {
      throw new ArithmeticException("Divide by zero");
   }

   private static void multiplyMultiPrecision(int[] inOut, int multiplier) {
      long multiplierUnsigned = (long)multiplier & 4294967295L;
      long product = 0L;

      for(int i = 0; i < inOut.length; ++i) {
         product = ((long)inOut[i] & 4294967295L) * multiplierUnsigned + (product >>> 32);
         inOut[i] = (int)product;
      }

      if (product >> 32 != 0L) {
         throwOverflowException();
      }

   }

   private static int divideMultiPrecision(int[] inOut, int divisor) {
      long divisorUnsigned = (long)divisor & 4294967295L;
      long remainder = 0L;

      for(int i = inOut.length - 1; i >= 0; --i) {
         remainder = ((long)inOut[i] & 4294967295L) + (remainder << 32);
         long quotient = remainder / divisorUnsigned;
         inOut[i] = (int)quotient;
         remainder %= divisorUnsigned;
      }

      return (int)remainder;
   }

   private static int arrayValidLength(int[] array) {
      int len;
      for(len = array.length; len > 0 && array[len - 1] == 0; --len) {
      }

      return len <= 0 ? 0 : len;
   }

   public static int[] divideMultiPrecision(int[] dividend, int[] divisor, int[] quotient) {
      int dividendLength = arrayValidLength(dividend);
      int divisorLength = arrayValidLength(divisor);
      Arrays.fill(quotient, 0);
      int[] remainder = new int[dividend.length + 1];
      System.arraycopy(dividend, 0, remainder, 0, dividend.length);
      remainder[remainder.length - 1] = 0;
      if (divisorLength == 0) {
         throwZeroDivisionException();
      }

      if (dividendLength < divisorLength) {
         return remainder;
      } else if (divisorLength == 1) {
         int rem = divideMultiPrecision(remainder, divisor[0]);
         System.arraycopy(remainder, 0, quotient, 0, quotient.length);
         Arrays.fill(remainder, 0);
         remainder[0] = rem;
         return remainder;
      } else {
         int d1 = (int)(4294967296L / (((long)divisor[divisorLength - 1] & 4294967295L) + 1L));
         if (d1 > 1) {
            int[] newDivisor = new int[divisorLength];
            System.arraycopy(divisor, 0, newDivisor, 0, divisorLength);
            multiplyMultiPrecision(newDivisor, d1);
            divisor = newDivisor;
            multiplyMultiPrecision(remainder, d1);
         }

         long dHigh = (long)divisor[divisorLength - 1] & 4294967295L;
         long dLow = (long)divisor[divisorLength - 2] & 4294967295L;

         for(int rIndex = remainder.length - 1; rIndex >= divisorLength; --rIndex) {
            long accum = combineInts(remainder[rIndex - 1], remainder[rIndex]);
            int qhat;
            if (dHigh == ((long)remainder[rIndex] & 4294967295L)) {
               qhat = -1;
            } else {
               qhat = (int)divideUnsignedLong(accum, dHigh);
            }

            for(int rhat = (int)(accum - ((long)qhat & 4294967295L) * dHigh); compareUnsignedLong(dLow * ((long)qhat & 4294967295L), combineInts(remainder[rIndex - 2], rhat)) > 0; rhat = (int)((long)rhat + dHigh)) {
               --qhat;
               if (((long)rhat & 4294967295L) >= (long)(-((int)dHigh))) {
                  break;
               }
            }

            long dwlMulAccum = 0L;
            accum = 4294967296L;
            int iulRwork = rIndex - divisorLength;

            for(int dIndex = 0; dIndex < divisorLength; ++iulRwork) {
               dwlMulAccum += ((long)qhat & 4294967295L) * ((long)divisor[dIndex] & 4294967295L);
               accum += ((long)remainder[iulRwork] & 4294967295L) - ((long)extractLowInt(dwlMulAccum) & 4294967295L);
               dwlMulAccum = (long)extractHiInt(dwlMulAccum) & 4294967295L;
               remainder[iulRwork] = extractLowInt(accum);
               accum = ((long)extractHiInt(accum) & 4294967295L) + 4294967296L - 1L;
               ++dIndex;
            }

            accum += ((long)remainder[iulRwork] & 4294967295L) - dwlMulAccum;
            remainder[iulRwork] = extractLowInt(accum);
            quotient[rIndex - divisorLength] = qhat;
            if (extractHiInt(accum) == 0) {
               quotient[rIndex - divisorLength] = qhat - 1;
               int carry = 0;
               int dIndex = 0;

               for(iulRwork = rIndex - divisorLength; dIndex < divisorLength; ++iulRwork) {
                  long accum2 = ((long)divisor[dIndex] & 4294967295L) + ((long)remainder[iulRwork] & 4294967295L) + ((long)carry & 4294967295L);
                  carry = extractHiInt(accum2);
                  remainder[iulRwork] = extractLowInt(accum2);
                  ++dIndex;
               }

               remainder[iulRwork] += carry;
            }
         }

         if (d1 > 1) {
            divideMultiPrecision(remainder, d1);
         }

         return remainder;
      }
   }

   private SqlMathUtil() {
   }

   static {
      BIT_LENGTH[0] = 0;

      for(int i = 1; i < 8; ++i) {
         for(int j = 1 << i - 1; j < 1 << i; ++j) {
            BIT_LENGTH[j] = (byte)i;
         }
      }

      POWER_FIVES_INT31[0] = 1;

      for(int i = 1; i < POWER_FIVES_INT31.length; ++i) {
         POWER_FIVES_INT31[i] = POWER_FIVES_INT31[i - 1] * 5;

         assert POWER_FIVES_INT31[i] > 0;
      }

      POWER_FIVES_INT63[0] = 1L;

      for(int i = 1; i < POWER_FIVES_INT63.length; ++i) {
         POWER_FIVES_INT63[i] = POWER_FIVES_INT63[i - 1] * 5L;

         assert POWER_FIVES_INT63[i] > 0L;
      }

      POWER_TENS_INT31[0] = 1;
      ROUND_POWER_TENS_INT31[0] = 0;

      for(int i = 1; i < POWER_TENS_INT31.length; ++i) {
         POWER_TENS_INT31[i] = POWER_TENS_INT31[i - 1] * 10;

         assert POWER_TENS_INT31[i] > 0;

         ROUND_POWER_TENS_INT31[i] = POWER_TENS_INT31[i] >> 1;
      }

      POWER_FIVES_INT128[0] = new UnsignedInt128(1L);
      INVERSE_POWER_FIVES_INT128[0] = new UnsignedInt128(-1, -1, -1, -1);

      for(int i = 1; i < POWER_FIVES_INT128.length; ++i) {
         POWER_FIVES_INT128[i] = new UnsignedInt128(POWER_FIVES_INT128[i - 1]);
         POWER_FIVES_INT128[i].multiplyDestructive(5);
         INVERSE_POWER_FIVES_INT128[i] = new UnsignedInt128(INVERSE_POWER_FIVES_INT128[i - 1]);
         INVERSE_POWER_FIVES_INT128[i].divideDestructive(5);
      }

      POWER_TENS_INT128[0] = new UnsignedInt128(1L);
      ROUND_POWER_TENS_INT128[0] = new UnsignedInt128(0L);
      INVERSE_POWER_TENS_INT128[0] = new UnsignedInt128(-1, -1, -1, -1);
      INVERSE_POWER_TENS_INT128_WORD_SHIFTS[0] = 0;
      int[] inverseTens = new int[8];
      Arrays.fill(inverseTens, -1);

      for(int i = 1; i < POWER_TENS_INT128.length; ++i) {
         int divisor = 10;
         POWER_TENS_INT128[i] = new UnsignedInt128(POWER_TENS_INT128[i - 1]);
         POWER_TENS_INT128[i].multiplyDestructive(10);
         ROUND_POWER_TENS_INT128[i] = POWER_TENS_INT128[i].shiftRightConstructive(1, false);
         long remainder = 0L;

         for(int j = inverseTens.length - 1; j >= 0; --j) {
            long quotient = (((long)inverseTens[j] & 4294967295L) + (remainder << 32)) / 10L;
            remainder = (((long)inverseTens[j] & 4294967295L) + (remainder << 32)) % 10L;
            inverseTens[j] = (int)quotient;
         }

         int wordShifts = 0;

         for(int j = inverseTens.length - 1; j >= 4 && inverseTens[j] == 0; --j) {
            ++wordShifts;
         }

         INVERSE_POWER_TENS_INT128_WORD_SHIFTS[i] = wordShifts;
         INVERSE_POWER_TENS_INT128[i] = new UnsignedInt128(inverseTens[inverseTens.length - 4 - wordShifts], inverseTens[inverseTens.length - 3 - wordShifts], inverseTens[inverseTens.length - 2 - wordShifts], inverseTens[inverseTens.length - 1 - wordShifts]);
      }

   }
}
