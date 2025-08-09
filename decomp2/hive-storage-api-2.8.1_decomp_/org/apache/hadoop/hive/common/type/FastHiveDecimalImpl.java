package org.apache.hadoop.hive.common.type;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FastHiveDecimalImpl extends FastHiveDecimal {
   private static final long[] powerOfTenTable = new long[]{1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
   public static final int MAX_DECIMAL_DIGITS = 38;
   private static final int INTWORD_DECIMAL_DIGITS = 8;
   private static final int MULTIPLER_INTWORD_DECIMAL;
   private static final int LONGWORD_DECIMAL_DIGITS = 16;
   private static final long MAX_LONGWORD_DECIMAL;
   private static final long MULTIPLER_LONGWORD_DECIMAL;
   public static final int DECIMAL64_DECIMAL_DIGITS = 18;
   public static final long MAX_ABS_DECIMAL64 = 999999999999999999L;
   private static final int TWO_X_LONGWORD_DECIMAL_DIGITS = 32;
   private static final int THREE_X_LONGWORD_DECIMAL_DIGITS = 48;
   private static final int FOUR_X_LONGWORD_DECIMAL_DIGITS = 64;
   private static final int HIGHWORD_DECIMAL_DIGITS = 6;
   private static final long MAX_HIGHWORD_DECIMAL;
   private static final long FULL_MAX_HIGHWORD_DECIMAL;
   private static final BigInteger BIG_INTEGER_TWO;
   private static final BigInteger BIG_INTEGER_FIVE;
   private static final BigInteger BIG_INTEGER_TEN;
   public static final BigInteger BIG_INTEGER_MAX_DECIMAL;
   private static final BigInteger BIG_INTEGER_MAX_LONGWORD_DECIMAL;
   private static final BigInteger BIG_INTEGER_LONGWORD_MULTIPLIER;
   private static final BigInteger BIG_INTEGER_LONGWORD_MULTIPLIER_2X;
   private static final BigInteger BIG_INTEGER_MAX_HIGHWORD_DECIMAL;
   private static final BigInteger BIG_INTEGER_HIGHWORD_MULTIPLIER;
   private static final byte BYTE_BLANK = 32;
   private static final byte BYTE_DIGIT_ZERO = 48;
   private static final byte BYTE_DIGIT_NINE = 57;
   private static final byte BYTE_DOT = 46;
   private static final byte BYTE_MINUS = 45;
   private static final byte BYTE_PLUS = 43;
   private static final byte BYTE_EXPONENT_LOWER = 101;
   private static final byte BYTE_EXPONENT_UPPER = 69;
   private static final FastHiveDecimal FAST_HIVE_DECIMAL_TWO_POWER_62;
   private static final FastHiveDecimal FAST_HIVE_DECIMAL_TWO_POWER_63;
   private static final FastHiveDecimal FAST_HIVE_DECIMAL_TWO_POWER_125;
   private static final FastHiveDecimal FAST_HIVE_DECIMAL_TWO_POWER_63_INVERSE;
   private static final int SERIALIZATION_UTILS_WRITE_QUOTIENT_INTEGER_WORD_NUM = 3;
   private static final int SERIALIZATION_UTILS_WRITE_QUOTIENT_INTEGER_DIGIT_NUM = 15;
   private static final FastHiveDecimal FAST_HIVE_DECIMAL_TWO_POWER_56;
   private static final FastHiveDecimal FAST_HIVE_DECIMAL_TWO_POWER_112;
   private static final FastHiveDecimal FAST_HIVE_DECIMAL_TWO_POWER_56_INVERSE;
   private static final int BIG_INTEGER_BYTES_QUOTIENT_INTEGER_WORD_NUM = 3;
   private static final int BIG_INTEGER_BYTES_QUOTIENT_INTEGER_DIGIT_NUM = 8;
   private static final int INITIAL_SHIFT = 48;
   private static final long LONG_56_BIT_MASK = 72057594037927935L;
   private static final long LONG_TWO_TO_56_POWER = 72057594037927936L;
   private static final long LONG_BYTE_MASK = 255L;
   private static final long LONG_BYTE_HIGH_BIT_MASK = 128L;
   private static final byte BYTE_ALL_BITS = -1;
   private static final int MAX_BYTE_DIGITS = 3;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MIN_BYTE_VALUE_MINUS_ONE;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MAX_BYTE_VALUE_PLUS_ONE;
   private static final int MAX_SHORT_DIGITS = 5;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MIN_SHORT_VALUE_MINUS_ONE;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MAX_SHORT_VALUE_PLUS_ONE;
   private static final int MAX_INT_DIGITS = 10;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MIN_INT_VALUE_MINUS_ONE;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MAX_INT_VALUE_PLUS_ONE;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MIN_LONG_VALUE;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MAX_LONG_VALUE;
   private static final int MAX_LONG_DIGITS;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MIN_LONG_VALUE_MINUS_ONE;
   private static final FastHiveDecimal FASTHIVEDECIMAL_MAX_LONG_VALUE_PLUS_ONE;
   private static final BigInteger BIG_INTEGER_UNSIGNED_BYTE_MAX_VALUE;
   private static final BigInteger BIG_INTEGER_UNSIGNED_SHORT_MAX_VALUE;
   private static final BigInteger BIG_INTEGER_UNSIGNED_INT_MAX_VALUE;
   private static final BigInteger BIG_INTEGER_UNSIGNED_LONG_MAX_VALUE;
   private static final int ZERO_NEW_FASTER_HASH_CODE;
   static final int STACK_LENGTH_LIMIT = 20;

   private static void doRaiseSetFromBytesInvalid(byte[] bytes, int offset, int length, FastHiveDecimal fastResult) {
      int end = offset + length;
      throw new RuntimeException("Invalid fast decimal \"" + new String(bytes, offset, end, StandardCharsets.UTF_8) + "\" fastSignum " + fastResult.fastSignum + " fast0 " + fastResult.fast0 + " fast1 " + fastResult.fast1 + " fast2 " + fastResult.fast2 + " fastIntegerDigitCount " + fastResult.fastIntegerDigitCount + " fastScale " + fastResult.fastScale + " stack trace: " + getStackTraceAsSingleLine(Thread.currentThread().getStackTrace()));
   }

   public static boolean fastSetFromBytes(byte[] bytes, int offset, int length, boolean trimBlanks, FastHiveDecimal fastResult) {
      int bytesLength = bytes.length;
      if (offset >= 0 && offset < bytesLength) {
         int end = offset + length;
         if (end > offset && end <= bytesLength) {
            int index = offset;
            if (trimBlanks) {
               while(bytes[index] == 32) {
                  ++index;
                  if (index >= end) {
                     return false;
                  }
               }
            }

            boolean isNegative = false;
            if (bytes[index] == 45) {
               isNegative = true;
               ++index;
               if (index >= end) {
                  return false;
               }
            } else if (bytes[index] == 43) {
               ++index;
               if (index >= end) {
                  return false;
               }
            }

            int precision = 0;
            int longWordIndex = 0;
            int digitNum = 6;
            long multiplier = powerOfTenTable[5];
            int digitValue = 0;
            long longWord = 0L;
            long fast0 = 0L;
            long fast1 = 0L;
            long fast2 = 0L;
            boolean haveInteger = false;

            while(true) {
               byte work = bytes[index];
               if (work < 48 || work > 57) {
                  break;
               }

               haveInteger = true;
               if (precision == 0 && work == 48) {
                  ++index;
                  if (index >= end) {
                     break;
                  }
               } else {
                  digitValue = work - 48;
                  if (digitNum == 0) {
                     if (longWordIndex == 0) {
                        fast2 = longWord;
                     } else if (longWordIndex == 1) {
                        fast1 = longWord;
                     } else if (longWordIndex == 2) {
                        return false;
                     }

                     ++longWordIndex;
                     digitNum = 16;
                     multiplier = powerOfTenTable[15];
                     longWord = 0L;
                  }

                  longWord += (long)digitValue * multiplier;
                  multiplier /= 10L;
                  --digitNum;
                  ++precision;
                  ++index;
                  if (index >= end) {
                     break;
                  }
               }
            }

            boolean sawDot = false;
            if (index < end && bytes[index] == 46) {
               sawDot = true;
               ++index;
            }

            if (trimBlanks && index < end && bytes[index] == 32) {
               ++index;

               while(index < end && bytes[index] == 32) {
                  ++index;
               }

               if (index < end) {
                  return false;
               }
            }

            if (index >= end) {
               if (!haveInteger) {
                  return false;
               } else if (precision == 0) {
                  return true;
               } else {
                  if (longWordIndex == 0) {
                     fast2 = longWord;
                  } else if (longWordIndex == 1) {
                     fast1 = longWord;
                  } else {
                     fast0 = longWord;
                  }

                  fastResult.fastSignum = isNegative ? -1 : 1;
                  fastResult.fastIntegerDigitCount = precision;
                  fastResult.fastScale = 0;
                  int scaleDown = 38 - precision;
                  if (scaleDown > 0) {
                     doFastScaleDown(fast0, fast1, fast2, scaleDown, fastResult);
                  } else {
                     fastResult.fast0 = fast0;
                     fastResult.fast1 = fast1;
                     fastResult.fast2 = fast2;
                  }

                  return true;
               }
            } else if (!haveInteger && !sawDot) {
               return false;
            } else {
               int integerDigitCount = precision;
               int nonTrailingZeroScale = 0;
               boolean roundingNecessary = false;
               if (sawDot) {
                  label304:
                  do {
                     byte var39 = bytes[index];
                     if (var39 < 48 || var39 > 57) {
                        if (!haveInteger) {
                           return false;
                        }
                        break;
                     }

                     digitValue = var39 - 48;
                     if (digitNum == 0) {
                        if (longWordIndex == 0) {
                           fast2 = longWord;
                        } else if (longWordIndex == 1) {
                           fast1 = longWord;
                        } else if (longWordIndex == 2) {
                           if (digitValue >= 5) {
                              roundingNecessary = true;
                           }

                           while(true) {
                              ++index;
                              if (index >= end) {
                                 break label304;
                              }

                              var39 = bytes[index];
                              if (var39 < 48 || var39 > 57) {
                                 break label304;
                              }
                           }
                        }

                        ++longWordIndex;
                        digitNum = 16;
                        multiplier = powerOfTenTable[digitNum - 1];
                        longWord = 0L;
                     }

                     longWord += (long)digitValue * multiplier;
                     multiplier /= 10L;
                     --digitNum;
                     ++precision;
                     if (digitValue != 0) {
                        nonTrailingZeroScale = precision - integerDigitCount;
                     }

                     ++index;
                  } while(index < end);
               }

               boolean haveExponent = false;
               if (index < end && (bytes[index] == 69 || bytes[index] == 101)) {
                  haveExponent = true;
                  ++index;
                  if (index >= end) {
                     return false;
                  }
               }

               if (longWordIndex == 0) {
                  fast2 = longWord;
               } else if (longWordIndex == 1) {
                  fast1 = longWord;
               } else {
                  fast0 = longWord;
               }

               int trailingZeroesScale = precision - integerDigitCount;
               if (integerDigitCount != 0 || nonTrailingZeroScale != 0) {
                  fastResult.fastSignum = isNegative ? -1 : 1;
                  fastResult.fastIntegerDigitCount = integerDigitCount;
                  fastResult.fastScale = nonTrailingZeroScale;
                  int trailingZeroCount = trailingZeroesScale - fastResult.fastScale;
                  int scaleDown = 38 - precision + trailingZeroCount;
                  if (scaleDown > 0) {
                     doFastScaleDown(fast0, fast1, fast2, scaleDown, fastResult);
                  } else {
                     fastResult.fast0 = fast0;
                     fastResult.fast1 = fast1;
                     fastResult.fast2 = fast2;
                  }
               }

               if (roundingNecessary) {
                  if (fastResult.fastSignum == 0) {
                     fastResult.fastSignum = isNegative ? -1 : 1;
                     fastResult.fast0 = 1L;
                     fastResult.fastIntegerDigitCount = 0;
                     fastResult.fastScale = 38;
                  } else if (!fastAdd(fastResult.fastSignum, fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale, fastResult.fastSignum, 1L, 0L, 0L, 0, trailingZeroesScale, fastResult)) {
                     return false;
                  }
               }

               if (!haveExponent) {
                  if (trimBlanks && index < end && bytes[index] == 32) {
                     ++index;

                     while(index < end && bytes[index] == 32) {
                        ++index;
                     }
                  }

                  return index >= end;
               } else {
                  boolean isExponentNegative = false;
                  if (bytes[index] == 45) {
                     isExponentNegative = true;
                     ++index;
                     if (index >= end) {
                        return false;
                     }
                  } else if (bytes[index] == 43) {
                     ++index;
                     if (index >= end) {
                        return false;
                     }
                  }

                  long exponent = 0L;
                  multiplier = 1L;

                  do {
                     byte var41 = bytes[index];
                     if (var41 < 48 || var41 > 57) {
                        break;
                     }

                     if (multiplier > 10L) {
                        return false;
                     }

                     digitValue = var41 - 48;
                     if (digitValue != 0 || exponent != 0L) {
                        exponent = exponent * 10L + (long)digitValue;
                        multiplier *= 10L;
                     }

                     ++index;
                  } while(index < end);

                  if (isExponentNegative) {
                     exponent = -exponent;
                  }

                  if (trimBlanks && index < end && bytes[index] == 32) {
                     ++index;

                     while(index < end && bytes[index] == 32) {
                        ++index;
                     }
                  }

                  if (index < end) {
                     return false;
                  } else if (integerDigitCount == 0 && nonTrailingZeroScale == 0) {
                     return true;
                  } else if (exponent != 0L && !fastScaleByPowerOfTen(fastResult, (int)exponent, fastResult)) {
                     return false;
                  } else {
                     int trailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
                     if (trailingZeroCount > 0) {
                        doFastScaleDown(fastResult, trailingZeroCount, fastResult);
                        fastResult.fastScale -= trailingZeroCount;
                     }

                     return true;
                  }
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean fastSetFromDigitsOnlyBytesAndScale(boolean isNegative, byte[] bytes, int offset, int length, int scale, FastHiveDecimal fastResult) {
      int bytesLength = bytes.length;
      if (offset >= 0 && offset < bytesLength) {
         int end = offset + length;
         if (end > offset && end <= bytesLength) {
            int index = offset;
            int precision = 0;
            int longWordIndex = 0;
            int digitNum = 6;
            long multiplier = powerOfTenTable[5];
            long longWord = 0L;
            long fast0 = 0L;
            long fast1 = 0L;
            long fast2 = 0L;
            boolean haveInteger = false;

            while(true) {
               byte work = bytes[index];
               if (work < 48 || work > 57) {
                  if (!haveInteger) {
                     return false;
                  }
                  break;
               }

               haveInteger = true;
               if (precision == 0 && work == 48) {
                  ++index;
                  if (index >= end) {
                     break;
                  }
               } else {
                  int digitValue = work - 48;
                  if (digitNum == 0) {
                     if (longWordIndex == 0) {
                        fast2 = longWord;
                     } else if (longWordIndex == 1) {
                        fast1 = longWord;
                     } else if (longWordIndex == 2) {
                        return false;
                     }

                     ++longWordIndex;
                     digitNum = 16;
                     multiplier = powerOfTenTable[15];
                     longWord = 0L;
                  }

                  longWord += (long)digitValue * multiplier;
                  multiplier /= 10L;
                  --digitNum;
                  ++precision;
                  ++index;
                  if (index >= end) {
                     break;
                  }
               }
            }

            if (index < end) {
               return false;
            } else if (precision == 0) {
               return true;
            } else {
               if (longWordIndex == 0) {
                  fast2 = longWord;
               } else if (longWordIndex == 1) {
                  fast1 = longWord;
               } else {
                  fast0 = longWord;
               }

               fastResult.fastSignum = isNegative ? -1 : 1;
               fastResult.fastIntegerDigitCount = Math.max(0, precision - scale);
               fastResult.fastScale = scale;
               int scaleDown = 38 - precision;
               if (scaleDown > 0) {
                  doFastScaleDown(fast0, fast1, fast2, scaleDown, fastResult);
               } else {
                  fastResult.fast0 = fast0;
                  fastResult.fast1 = fast1;
                  fastResult.fast2 = fast2;
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static BigInteger doBigIntegerScaleDown(BigInteger unscaledValue, int scaleDown) {
      BigInteger[] quotientAndRemainder = unscaledValue.divideAndRemainder(BigInteger.TEN.pow(scaleDown));
      BigInteger quotient = quotientAndRemainder[0];
      BigInteger round = quotientAndRemainder[1].divide(BigInteger.TEN.pow(scaleDown - 1));
      if (round.compareTo(BIG_INTEGER_FIVE) >= 0) {
         quotient = quotient.add(BigInteger.ONE);
      }

      return quotient;
   }

   public static boolean fastSetFromBigDecimal(BigDecimal bigDecimal, boolean allowRounding, FastHiveDecimal fastResult) {
      if (bigDecimal.signum() == 0 && bigDecimal.scale() != 0) {
         bigDecimal = BigDecimal.ZERO;
      }

      if (!allowRounding) {
         if (bigDecimal.signum() != 0) {
            BigDecimal bigDecimalStripped = bigDecimal.stripTrailingZeros();
            int stripTrailingZerosScale = bigDecimalStripped.scale();
            if (stripTrailingZerosScale < 0) {
               bigDecimal = bigDecimal.setScale(0);
            } else {
               bigDecimal = bigDecimalStripped;
            }
         }

         int scale = bigDecimal.scale();
         if (scale >= 0 && scale <= 38) {
            if (!fastSetFromBigInteger(bigDecimal.unscaledValue(), fastResult)) {
               return false;
            } else {
               if (fastResult.fastSignum != 0) {
                  fastResult.fastIntegerDigitCount = Math.max(0, fastResult.fastIntegerDigitCount - scale);
                  fastResult.fastScale = scale;
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return fastSetFromBigInteger(bigDecimal.unscaledValue(), bigDecimal.scale(), bigDecimal.precision(), fastResult);
      }
   }

   public static boolean fastSetFromString(String string, boolean trimBlanks, FastHiveDecimal result) {
      byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
      return fastSetFromBytes(bytes, 0, bytes.length, trimBlanks, result);
   }

   public static void fastSetFromInt(int intValue, FastHiveDecimal fastResult) {
      if (intValue != 0) {
         if (intValue > 0) {
            fastResult.fastSignum = 1;
         } else {
            fastResult.fastSignum = -1;
            intValue = Math.abs(intValue);
         }

         fastResult.fast0 = (long)intValue & 4294967295L;
         fastResult.fastIntegerDigitCount = fastLongWordPrecision(fastResult.fast0);
      }
   }

   public static void fastSetFromLong(long longValue, FastHiveDecimal fastResult) {
      if (longValue != 0L) {
         if (longValue == Long.MIN_VALUE) {
            fastResult.fastSignum = -1;
            fastResult.fast1 = 922L;
            fastResult.fast0 = 3372036854775808L;
            fastResult.fastIntegerDigitCount = 19;
         } else {
            if (longValue > 0L) {
               fastResult.fastSignum = 1;
            } else {
               fastResult.fastSignum = -1;
               longValue = Math.abs(longValue);
            }

            fastResult.fast1 = longValue / MULTIPLER_LONGWORD_DECIMAL;
            fastResult.fast0 = longValue % MULTIPLER_LONGWORD_DECIMAL;
            if (fastResult.fast1 != 0L) {
               fastResult.fastIntegerDigitCount = 16 + fastLongWordPrecision(fastResult.fast1);
            } else {
               fastResult.fastIntegerDigitCount = fastLongWordPrecision(fastResult.fast0);
            }
         }

      }
   }

   public static boolean fastSetFromLongAndScale(long longValue, int scale, FastHiveDecimal fastResult) {
      if (scale >= 0 && scale <= 38) {
         fastSetFromLong(longValue, fastResult);
         if (scale == 0) {
            return true;
         } else {
            return fastScaleByPowerOfTen(fastResult, -scale, fastResult);
         }
      } else {
         return false;
      }
   }

   public static boolean fastSetFromFloat(float floatValue, FastHiveDecimal fastResult) {
      String floatString = Float.toString(floatValue);
      return fastSetFromString(floatString, false, fastResult);
   }

   public static boolean fastSetFromDouble(double doubleValue, FastHiveDecimal fastResult) {
      String doubleString = Double.toString(doubleValue);
      return fastSetFromString(doubleString, false, fastResult);
   }

   public static boolean fastSetFromBigInteger(BigInteger bigInteger, FastHiveDecimal fastResult) {
      int signum = bigInteger.signum();
      if (signum == 0) {
         return true;
      } else {
         fastResult.fastSignum = signum;
         if (signum == -1) {
            bigInteger = bigInteger.negate();
         }

         if (bigInteger.compareTo(BIG_INTEGER_LONGWORD_MULTIPLIER) < 0) {
            fastResult.fast0 = bigInteger.longValue();
            if (fastResult.fast0 == 0L) {
               fastResult.fastSignum = 0;
            } else {
               fastResult.fastIntegerDigitCount = fastLongWordPrecision(fastResult.fast0);
            }

            return true;
         } else {
            BigInteger[] quotientAndRemainder = bigInteger.divideAndRemainder(BIG_INTEGER_LONGWORD_MULTIPLIER);
            fastResult.fast0 = quotientAndRemainder[1].longValue();
            BigInteger quotient = quotientAndRemainder[0];
            if (quotient.compareTo(BIG_INTEGER_LONGWORD_MULTIPLIER) < 0) {
               fastResult.fast1 = quotient.longValue();
               if (fastResult.fast0 == 0L && fastResult.fast1 == 0L) {
                  throw new RuntimeException("Unexpected");
               } else {
                  fastResult.fastIntegerDigitCount = 16 + fastLongWordPrecision(fastResult.fast1);
                  return true;
               }
            } else {
               quotientAndRemainder = quotient.divideAndRemainder(BIG_INTEGER_LONGWORD_MULTIPLIER);
               fastResult.fast1 = quotientAndRemainder[1].longValue();
               quotient = quotientAndRemainder[0];
               if (quotient.compareTo(BIG_INTEGER_HIGHWORD_MULTIPLIER) >= 0) {
                  return false;
               } else {
                  fastResult.fast2 = quotient.longValue();
                  if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
                     fastResult.fastSignum = 0;
                  } else {
                     fastResult.fastIntegerDigitCount = 32 + fastHighWordPrecision(fastResult.fast2);
                  }

                  return true;
               }
            }
         }
      }
   }

   public static boolean fastSetFromBigInteger(BigInteger bigInteger, int scale, FastHiveDecimal fastResult) {
      return fastSetFromBigInteger(bigInteger, scale, -1, fastResult);
   }

   public static boolean fastSetFromBigInteger(BigInteger bigInteger, int scale, int precision, FastHiveDecimal fastResult) {
      if (scale < 0) {
         bigInteger = bigInteger.multiply(BIG_INTEGER_TEN.pow(-scale));
         scale = 0;
      }

      int signum = bigInteger.signum();
      if (signum == 0) {
         return true;
      } else {
         if (signum == -1) {
            bigInteger = bigInteger.negate();
         }

         if (precision < 0) {
            precision = bigInteger.toString().length();
         }

         int integerDigitCount = precision - scale;
         int maxScale;
         if (integerDigitCount >= 0) {
            if (integerDigitCount > 38) {
               return false;
            }

            maxScale = 38 - integerDigitCount;
         } else {
            maxScale = 38;
         }

         if (scale > maxScale) {
            int trimAwayCount = scale - maxScale;
            if (trimAwayCount > 1) {
               BigInteger bigIntegerThrowAwayBelowRoundDigitDivisor = BIG_INTEGER_TEN.pow(trimAwayCount - 1);
               bigInteger = bigInteger.divide(bigIntegerThrowAwayBelowRoundDigitDivisor);
            }

            BigInteger[] quotientAndRemainder = bigInteger.divideAndRemainder(BIG_INTEGER_TEN);
            BigInteger quotient = quotientAndRemainder[0];
            if (quotientAndRemainder[1].intValue() >= 5) {
               if (quotient.equals(BIG_INTEGER_MAX_DECIMAL)) {
                  if (maxScale == 0) {
                     return false;
                  }

                  bigInteger = BIG_INTEGER_TEN.pow(integerDigitCount);
                  maxScale = 0;
               } else {
                  bigInteger = quotient.add(BigInteger.ONE);
               }
            } else {
               bigInteger = quotient;
            }

            scale = maxScale;
         }

         if (!fastSetFromBigInteger(bigInteger, fastResult)) {
            return false;
         } else {
            if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
               fastResult.fastSignum = 0;
            } else {
               fastResult.fastSignum = signum;
               fastResult.fastIntegerDigitCount = Math.max(0, fastResult.fastIntegerDigitCount - scale);
               fastResult.fastScale = scale;
               int trailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, scale);
               if (trailingZeroCount > 0) {
                  doFastScaleDown(fastResult, trailingZeroCount, fastResult);
                  fastResult.fastScale -= trailingZeroCount;
               }
            }

            return true;
         }
      }
   }

   public static void fastFractionPortion(int fastSignum, long fast0, long fast1, long fast2, int fastScale, FastHiveDecimal fastResult) {
      if (fastSignum != 0 && fastScale != 0) {
         long result0;
         long result1;
         long result2;
         if (fastScale < 16) {
            long clearFactor = powerOfTenTable[fastScale];
            result0 = fast0 % clearFactor;
            result1 = 0L;
            result2 = 0L;
         } else if (fastScale < 32) {
            int adjustedScaleDown = fastScale - 16;
            long clearFactor = powerOfTenTable[adjustedScaleDown];
            result0 = fast0;
            result1 = fast1 % clearFactor;
            result2 = 0L;
         } else {
            int adjustedScaleDown = fastScale - 32;
            long clearFactor = powerOfTenTable[adjustedScaleDown];
            result0 = fast0;
            result1 = fast1;
            result2 = fast2 % clearFactor;
         }

         if (result0 == 0L && result1 == 0L && result2 == 0L) {
            fastResult.fastReset();
         } else {
            fastResult.fastSet(fastSignum, result0, result1, result2, 0, fastScale);
         }

      } else {
         fastResult.fastReset();
      }
   }

   public static void fastIntegerPortion(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, FastHiveDecimal fastResult) {
      if (fastSignum == 0) {
         fastResult.fastReset();
      } else {
         if (fastScale == 0) {
            fastResult.fastSet(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
         }

         fastResult.fastSignum = fastSignum;
         doFastScaleDown(fast0, fast1, fast2, fastScale, fastResult);
         fastResult.fastIntegerDigitCount = fastIntegerDigitCount;
         fastResult.fastScale = 0;
      }
   }

   public static boolean doBinaryToDecimalConversion(long lowerWord, long middleWord, long highWord, FastHiveDecimal middleWordMultiplier, FastHiveDecimal highWordMultiplier, FastHiveDecimal fastResult) {
      long result0 = lowerWord % MULTIPLER_LONGWORD_DECIMAL;
      long result1 = lowerWord / MULTIPLER_LONGWORD_DECIMAL;
      long result2 = 0L;
      if (middleWord != 0L || highWord != 0L) {
         if (highWord == 0L) {
            if (!fastMultiply5x5HalfWords(middleWord % MULTIPLER_LONGWORD_DECIMAL, middleWord / MULTIPLER_LONGWORD_DECIMAL, 0L, middleWordMultiplier.fast0, middleWordMultiplier.fast1, middleWordMultiplier.fast2, fastResult)) {
               return false;
            }

            long calc0 = result0 + fastResult.fast0;
            result0 = calc0 % MULTIPLER_LONGWORD_DECIMAL;
            long calc1 = calc0 / MULTIPLER_LONGWORD_DECIMAL + result1 + fastResult.fast1;
            result1 = calc1 % MULTIPLER_LONGWORD_DECIMAL;
            result2 = calc1 / MULTIPLER_LONGWORD_DECIMAL + fastResult.fast2;
         } else if (middleWord == 0L) {
            if (!fastMultiply5x5HalfWords(highWord % MULTIPLER_LONGWORD_DECIMAL, highWord / MULTIPLER_LONGWORD_DECIMAL, 0L, highWordMultiplier.fast0, highWordMultiplier.fast1, highWordMultiplier.fast2, fastResult)) {
               return false;
            }

            long calc0 = result0 + fastResult.fast0;
            result0 = calc0 % MULTIPLER_LONGWORD_DECIMAL;
            long calc1 = calc0 / MULTIPLER_LONGWORD_DECIMAL + result1 + fastResult.fast1;
            result1 = calc1 % MULTIPLER_LONGWORD_DECIMAL;
            result2 = calc1 / MULTIPLER_LONGWORD_DECIMAL + fastResult.fast2;
         } else {
            if (!fastMultiply5x5HalfWords(middleWord % MULTIPLER_LONGWORD_DECIMAL, middleWord / MULTIPLER_LONGWORD_DECIMAL, 0L, middleWordMultiplier.fast0, middleWordMultiplier.fast1, middleWordMultiplier.fast2, fastResult)) {
               return false;
            }

            long middleResult0 = fastResult.fast0;
            long middleResult1 = fastResult.fast1;
            long middleResult2 = fastResult.fast2;
            if (!fastMultiply5x5HalfWords(highWord % MULTIPLER_LONGWORD_DECIMAL, highWord / MULTIPLER_LONGWORD_DECIMAL, 0L, highWordMultiplier.fast0, highWordMultiplier.fast1, highWordMultiplier.fast2, fastResult)) {
               return false;
            }

            long calc0 = result0 + middleResult0 + fastResult.fast0;
            result0 = calc0 % MULTIPLER_LONGWORD_DECIMAL;
            long calc1 = calc0 / MULTIPLER_LONGWORD_DECIMAL + result1 + middleResult1 + fastResult.fast1;
            result1 = calc1 % MULTIPLER_LONGWORD_DECIMAL;
            result2 = calc1 / MULTIPLER_LONGWORD_DECIMAL + middleResult2 + fastResult.fast2;
         }
      }

      if (result2 != 0L) {
         fastResult.fastIntegerDigitCount = 32 + fastHighWordPrecision(result2);
         fastResult.fastSignum = 1;
      } else if (result1 != 0L) {
         fastResult.fastIntegerDigitCount = 16 + fastHighWordPrecision(result1);
         fastResult.fastSignum = 1;
      } else if (result0 != 0L) {
         fastResult.fastIntegerDigitCount = fastHighWordPrecision(result0);
         fastResult.fastSignum = 1;
      } else {
         fastResult.fastIntegerDigitCount = 0;
         fastResult.fastSignum = 0;
      }

      fastResult.fast0 = result0;
      fastResult.fast1 = result1;
      fastResult.fast2 = result2;
      return true;
   }

   public static boolean doDecimalToBinaryDivisionRemainder(long dividendFast0, long dividendFast1, long dividendFast2, FastHiveDecimal fastInverseConst, int quotientIntegerWordNum, int quotientIntegerDigitNum, FastHiveDecimal fastMultiplierConst, long[] scratchLongs) {
      if (!fastMultiply5x6HalfWords(dividendFast0, dividendFast1, dividendFast2, fastInverseConst.fast0, fastInverseConst.fast1, fastInverseConst.fast2, scratchLongs)) {
         return false;
      } else {
         long divideFactor = powerOfTenTable[quotientIntegerDigitNum];
         long multiplyFactor = powerOfTenTable[16 - quotientIntegerDigitNum];
         long quotientFast0 = scratchLongs[quotientIntegerWordNum] / divideFactor + scratchLongs[quotientIntegerWordNum + 1] % divideFactor * multiplyFactor;
         long quotientFast1 = scratchLongs[quotientIntegerWordNum + 1] / divideFactor + scratchLongs[quotientIntegerWordNum + 2] % divideFactor * multiplyFactor;
         long quotientFast2 = scratchLongs[quotientIntegerWordNum + 2] / divideFactor;
         if (!fastMultiply5x6HalfWords(quotientFast0, quotientFast1, quotientFast2, fastMultiplierConst.fast0, fastMultiplierConst.fast1, fastMultiplierConst.fast2, scratchLongs)) {
            return false;
         } else {
            long quotientMultiplied0 = scratchLongs[0];
            long quotientMultiplied1 = scratchLongs[1];
            long quotientMultiplied2 = scratchLongs[2];
            if (!doSubtractSameScaleNoUnderflow(dividendFast0, dividendFast1, dividendFast2, quotientMultiplied0, quotientMultiplied1, quotientMultiplied2, scratchLongs)) {
               return false;
            } else {
               long remainderBinaryWord = scratchLongs[1] * MULTIPLER_LONGWORD_DECIMAL + scratchLongs[0];
               scratchLongs[0] = quotientFast0;
               scratchLongs[1] = quotientFast1;
               scratchLongs[2] = quotientFast2;
               scratchLongs[3] = remainderBinaryWord;
               return true;
            }
         }
      }
   }

   private static boolean doDecimalToBinaryConversion(long fast0, long fast1, long fast2, FastHiveDecimal fastInverseConst, int quotientIntegerWordNum, int quotientIntegerDigitNum, FastHiveDecimal fastMultiplierConst, long[] scratchLongs) {
      long middleBinaryWord = 0L;
      long highBinaryWord = 0L;
      long lowerBinaryWord;
      if (fastCompareTo(1, fast0, fast1, fast2, 0, 1, fastMultiplierConst.fast0, fastMultiplierConst.fast1, fastMultiplierConst.fast2, 0) < 0) {
         lowerBinaryWord = fast1 * MULTIPLER_LONGWORD_DECIMAL + fast0;
      } else {
         if (!doDecimalToBinaryDivisionRemainder(fast0, fast1, fast2, fastInverseConst, quotientIntegerWordNum, quotientIntegerDigitNum, fastMultiplierConst, scratchLongs)) {
            return false;
         }

         long quotientFast0 = scratchLongs[0];
         long quotientFast1 = scratchLongs[1];
         long quotientFast2 = scratchLongs[2];
         lowerBinaryWord = scratchLongs[3];
         if (fastCompareTo(1, quotientFast0, quotientFast1, quotientFast2, 0, 1, fastMultiplierConst.fast0, fastMultiplierConst.fast1, fastMultiplierConst.fast2, 0) < 0) {
            middleBinaryWord = quotientFast1 * MULTIPLER_LONGWORD_DECIMAL + quotientFast0;
         } else {
            if (!doDecimalToBinaryDivisionRemainder(quotientFast0, quotientFast1, quotientFast2, fastInverseConst, quotientIntegerWordNum, quotientIntegerDigitNum, fastMultiplierConst, scratchLongs)) {
               return false;
            }

            highBinaryWord = scratchLongs[1] * MULTIPLER_LONGWORD_DECIMAL + scratchLongs[0];
            middleBinaryWord = scratchLongs[3];
         }
      }

      scratchLongs[0] = lowerBinaryWord;
      scratchLongs[1] = middleBinaryWord;
      scratchLongs[2] = highBinaryWord;
      return true;
   }

   public static boolean fastSerializationUtilsRead(InputStream inputStream, int scale, byte[] scratchBytes, FastHiveDecimal fastResult) throws IOException {
      int readCount = 0;

      int input;
      do {
         input = inputStream.read();
         if (input == -1) {
            throw new EOFException("Reading BigInteger past EOF from " + inputStream);
         }

         scratchBytes[readCount++] = (byte)input;
      } while(input >= 128);

      long lowerWord63 = 0L;
      long middleWord63 = 0L;
      long highWord63 = 0L;
      long work = 0L;
      int offset = 0;
      int readIndex = 0;

      do {
         long b = (long)scratchBytes[readIndex++];
         work |= (127L & b) << offset % 63;
         offset += 7;
         if (offset == 63) {
            lowerWord63 = work;
            work = 0L;
         } else if (offset % 63 == 0) {
            if (offset == 126) {
               middleWord63 = work;
            } else {
               if (offset != 189) {
                  throw new EOFException("Reading more than 3 words of BigInteger");
               }

               highWord63 = work;
            }

            work = 0L;
         }
      } while(readIndex < readCount);

      if (work != 0L) {
         if (offset < 63) {
            lowerWord63 = work;
         } else if (offset < 126) {
            middleWord63 = work;
         } else {
            if (offset >= 189) {
               throw new EOFException("Reading more than 3 words of BigInteger");
            }

            highWord63 = work;
         }
      }

      boolean isNegative = (lowerWord63 & 1L) != 0L;
      lowerWord63 >>= 1;
      if (!doBinaryToDecimalConversion(lowerWord63, middleWord63, highWord63, FAST_HIVE_DECIMAL_TWO_POWER_62, FAST_HIVE_DECIMAL_TWO_POWER_125, fastResult)) {
         return false;
      } else if (isNegative && !doAddSameScaleSameSign(1, fastResult.fast0, fastResult.fast1, fastResult.fast2, 1L, 0L, 0L, fastResult)) {
         return false;
      } else {
         if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
            fastResult.fastSignum = 0;
         } else {
            fastResult.fastSignum = isNegative ? -1 : 1;
            int rawPrecision = fastRawPrecision(fastResult);
            fastResult.fastIntegerDigitCount = Math.max(0, rawPrecision - scale);
            fastResult.fastScale = scale;
            int resultTrailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
            if (resultTrailingZeroCount > 0) {
               doFastScaleDown(fastResult, resultTrailingZeroCount, fastResult);
               fastResult.fastScale -= resultTrailingZeroCount;
            }
         }

         return true;
      }
   }

   public static boolean fastSerializationUtilsWrite(OutputStream outputStream, int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, long[] scratchLongs) throws IOException {
      // $FF: Couldn't be decompiled
   }

   public static long getDecimal64AbsMax(int precision) {
      return powerOfTenTable[precision] - 1L;
   }

   public static void fastDeserialize64(long inputDecimal64Long, int inputScale, FastHiveDecimal fastResult) {
      if (inputDecimal64Long == 0L) {
         fastResult.fastReset();
      } else {
         long decimal64Long;
         if (inputDecimal64Long > 0L) {
            fastResult.fastSignum = 1;
            decimal64Long = inputDecimal64Long;
         } else {
            fastResult.fastSignum = -1;
            decimal64Long = -inputDecimal64Long;
         }

         int trimScale;
         for(trimScale = inputScale; trimScale > 0 && decimal64Long % 10L == 0L; --trimScale) {
            decimal64Long /= 10L;
         }

         fastResult.fast2 = 0L;
         fastResult.fast1 = decimal64Long / MULTIPLER_LONGWORD_DECIMAL;
         fastResult.fast0 = decimal64Long % MULTIPLER_LONGWORD_DECIMAL;
         fastResult.fastScale = trimScale;
         fastResult.fastIntegerDigitCount = Math.max(0, fastRawPrecision(fastResult) - fastResult.fastScale);
      }
   }

   public static long fastSerialize64(int scale, int fastSignum, long fast1, long fast0, int fastScale) {
      if (fastSignum == 0) {
         return 0L;
      } else {
         return fastSignum == 1 ? (fast1 * MULTIPLER_LONGWORD_DECIMAL + fast0) * powerOfTenTable[scale - fastScale] : -(fast1 * MULTIPLER_LONGWORD_DECIMAL + fast0) * powerOfTenTable[scale - fastScale];
      }
   }

   public static boolean fastSetFromBigIntegerBytesAndScale(byte[] bytes, int offset, int length, int scale, FastHiveDecimal fastResult) {
      int bytesLength = bytes.length;
      if (offset >= 0 && offset < bytesLength) {
         int end = offset + length;
         if (end > offset && end <= bytesLength) {
            int startOffset = offset;
            boolean isNegative = bytes[offset] < 0;
            if (isNegative) {
               while(offset < end && bytes[offset] == -1) {
                  ++offset;
               }

               if (offset > end) {
                  return false;
               }
            } else {
               while(offset < end && bytes[offset] == 0) {
                  ++offset;
               }

               if (offset >= end) {
                  return true;
               }
            }

            long lowerWord56 = 0L;
            long middleWord56 = 0L;
            long highWord56 = 0L;
            int reverseIndex = end;
            int lowestCount = Math.min(end - offset, 7);
            int shift = 0;

            for(int i = 0; i < lowestCount; ++i) {
               --reverseIndex;
               long work = (long)(bytes[reverseIndex] & 255);
               lowerWord56 |= work << shift;
               shift += 8;
            }

            if (reverseIndex <= offset) {
               if (isNegative) {
                  lowerWord56 = ~lowerWord56 & (1L << shift) - 1L;
               }
            } else {
               int middleCount = Math.min(reverseIndex - offset, 7);
               shift = 0;

               for(int i = 0; i < middleCount; ++i) {
                  --reverseIndex;
                  long work = (long)(bytes[reverseIndex] & 255);
                  middleWord56 |= work << shift;
                  shift += 8;
               }

               if (reverseIndex <= offset) {
                  if (isNegative) {
                     lowerWord56 = ~lowerWord56 & 72057594037927935L;
                     middleWord56 = ~middleWord56 & (1L << shift) - 1L;
                  }
               } else {
                  int highCount = Math.min(reverseIndex - offset, 7);
                  shift = 0;

                  for(int i = 0; i < highCount; ++i) {
                     --reverseIndex;
                     long work = (long)(bytes[reverseIndex] & 255);
                     highWord56 |= work << shift;
                     shift += 8;
                  }

                  if (isNegative) {
                     lowerWord56 = ~lowerWord56 & 72057594037927935L;
                     middleWord56 = ~middleWord56 & 72057594037927935L;
                     highWord56 = ~highWord56 & (1L << shift) - 1L;
                  }
               }
            }

            if (!doBinaryToDecimalConversion(lowerWord56, middleWord56, highWord56, FAST_HIVE_DECIMAL_TWO_POWER_56, FAST_HIVE_DECIMAL_TWO_POWER_112, fastResult)) {
               return doAlternateSetFromBigIntegerBytesAndScale(bytes, startOffset, length, scale, fastResult);
            } else if (isNegative && !doAddSameScaleSameSign(1, fastResult.fast0, fastResult.fast1, fastResult.fast2, 1L, 0L, 0L, fastResult)) {
               return doAlternateSetFromBigIntegerBytesAndScale(bytes, startOffset, length, scale, fastResult);
            } else {
               if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
                  fastResult.fastSignum = 0;
               } else {
                  fastResult.fastSignum = isNegative ? -1 : 1;
                  fastResult.fastScale = scale;
                  int rawPrecision = fastRawPrecision(fastResult);
                  fastResult.fastIntegerDigitCount = Math.max(0, rawPrecision - scale);
                  int resultTrailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
                  if (resultTrailingZeroCount > 0) {
                     doFastScaleDown(fastResult, resultTrailingZeroCount, fastResult);
                     fastResult.fastScale -= resultTrailingZeroCount;
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean doAlternateSetFromBigIntegerBytesAndScale(byte[] bytes, int offset, int length, int scale, FastHiveDecimal fastResult) {
      byte[] byteArray = Arrays.copyOfRange(bytes, offset, offset + length);
      BigInteger bigInteger = new BigInteger(byteArray);
      BigDecimal bigDecimal = new BigDecimal(bigInteger, scale);
      fastResult.fastReset();
      return fastSetFromBigDecimal(bigDecimal, true, fastResult);
   }

   public static int fastBigIntegerBytes(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastSerializeScale, long[] scratchLongs, byte[] buffer) {
      return fastSerializeScale != -1 ? fastBigIntegerBytesScaled(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, fastSerializeScale, scratchLongs, buffer) : fastBigIntegerBytesUnscaled(fastSignum, fast0, fast1, fast2, scratchLongs, buffer);
   }

   public static int fastBigIntegerBytesUnscaled(int fastSignum, long fast0, long fast1, long fast2, long[] scratchLongs, byte[] buffer) {
      if (fastSignum == 0) {
         buffer[0] = 0;
         return 1;
      } else {
         boolean isNegative = fastSignum == -1;
         if (!doDecimalToBinaryConversion(fast0, fast1, fast2, FAST_HIVE_DECIMAL_TWO_POWER_56_INVERSE, 3, 8, FAST_HIVE_DECIMAL_TWO_POWER_56, scratchLongs)) {
            return 0;
         } else {
            int byteIndex = 0;
            long word0 = scratchLongs[0];
            long word1 = scratchLongs[1];
            long word2 = scratchLongs[2];
            if (!isNegative) {
               long longWork = 0L;
               int shift = 48;
               if (word2 != 0L) {
                  label219:
                  while(true) {
                     longWork = word2 >> shift & 255L;
                     if (longWork != 0L) {
                        if ((longWork & 128L) != 0L) {
                           buffer[byteIndex++] = 0;
                        }

                        while(true) {
                           buffer[byteIndex++] = (byte)((int)longWork);
                           if (shift == 0) {
                              shift = 48;
                              break label219;
                           }

                           shift -= 8;
                           longWork = word2 >> shift & 255L;
                        }
                     }

                     if (shift == 0) {
                        throw new RuntimeException("Unexpected #1");
                     }

                     shift -= 8;
                  }
               }

               if (byteIndex != 0 || word1 != 0L) {
                  if (byteIndex != 0) {
                     longWork = word1 >> shift & 255L;
                  } else {
                     while(true) {
                        longWork = word1 >> shift & 255L;
                        if (longWork != 0L) {
                           if ((longWork & 128L) != 0L) {
                              buffer[byteIndex++] = 0;
                           }
                           break;
                        }

                        if (shift == 0) {
                           throw new RuntimeException("Unexpected #2");
                        }

                        shift -= 8;
                     }
                  }

                  while(true) {
                     buffer[byteIndex++] = (byte)((int)longWork);
                     if (shift == 0) {
                        shift = 48;
                        break;
                     }

                     shift -= 8;
                     longWork = word1 >> shift & 255L;
                  }
               }

               if (byteIndex != 0) {
                  longWork = word0 >> shift & 255L;
               } else {
                  while(true) {
                     longWork = word0 >> shift & 255L;
                     if (longWork != 0L) {
                        if ((longWork & 128L) != 0L) {
                           buffer[byteIndex++] = 0;
                        }
                        break;
                     }

                     if (shift == 0) {
                        throw new RuntimeException("Unexpected #3");
                     }

                     shift -= 8;
                  }
               }

               while(true) {
                  buffer[byteIndex++] = (byte)((int)longWork);
                  if (shift == 0) {
                     break;
                  }

                  shift -= 8;
                  longWork = word0 >> shift & 255L;
               }
            } else {
               --word0;
               if (word0 < 0L) {
                  word0 += 72057594037927936L;
                  --word1;
                  if (word1 < 0L) {
                     word1 += 72057594037927936L;
                     --word2;
                     if (word2 < 0L) {
                        return 0;
                     }
                  }
               }

               long longWork = 0L;
               int shift = 48;
               if (word2 != 0L) {
                  label171:
                  while(true) {
                     longWork = word2 >> shift & 255L;
                     if (longWork != 0L) {
                        longWork = ~longWork & 255L;
                        if ((longWork & 128L) == 0L) {
                           buffer[byteIndex++] = -1;
                        }

                        word2 = ~word2;
                        word1 = ~word1;
                        word0 = ~word0;

                        while(true) {
                           buffer[byteIndex++] = (byte)((int)longWork);
                           if (shift == 0) {
                              shift = 48;
                              break label171;
                           }

                           shift -= 8;
                           longWork = word2 >> shift & 255L;
                        }
                     }

                     if (shift == 0) {
                        throw new RuntimeException("Unexpected #1");
                     }

                     shift -= 8;
                  }
               }

               if (byteIndex != 0 || word1 != 0L) {
                  if (byteIndex != 0) {
                     longWork = word1 >> shift & 255L;
                  } else {
                     while(true) {
                        longWork = word1 >> shift & 255L;
                        if (longWork != 0L) {
                           longWork = ~longWork & 255L;
                           if ((longWork & 128L) == 0L) {
                              buffer[byteIndex++] = -1;
                           }

                           word1 = ~word1;
                           word0 = ~word0;
                           break;
                        }

                        if (shift == 0) {
                           throw new RuntimeException("Unexpected #2");
                        }

                        shift -= 8;
                     }
                  }

                  while(true) {
                     buffer[byteIndex++] = (byte)((int)longWork);
                     if (shift == 0) {
                        shift = 48;
                        break;
                     }

                     shift -= 8;
                     longWork = word1 >> shift & 255L;
                  }
               }

               if (byteIndex != 0) {
                  longWork = word0 >> shift & 255L;
               } else {
                  while(true) {
                     longWork = word0 >> shift & 255L;
                     if (longWork != 0L) {
                        longWork = ~longWork & 255L;
                        if ((longWork & 128L) == 0L) {
                           buffer[byteIndex++] = -1;
                        }

                        word0 = ~word0;
                        break;
                     }

                     if (shift == 0) {
                        buffer[0] = -1;
                        return 1;
                     }

                     shift -= 8;
                  }
               }

               while(true) {
                  buffer[byteIndex++] = (byte)((int)longWork);
                  if (shift == 0) {
                     break;
                  }

                  shift -= 8;
                  longWork = word0 >> shift & 255L;
               }
            }

            return byteIndex;
         }
      }
   }

   public static int fastBigIntegerBytesScaled(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int serializeScale, long[] scratchLongs, byte[] buffer) {
      if (fastSignum != 0 && serializeScale != fastScale) {
         if (serializeScale > fastScale) {
            int scaleUp = serializeScale - fastScale;
            int maxScale = 38 - fastIntegerDigitCount;
            if (serializeScale > maxScale) {
               BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, fast0, fast1, fast2);
               BigInteger bigIntegerScaled = bigInteger.multiply(BIG_INTEGER_TEN.pow(scaleUp));
               byte[] bigIntegerBytesScaled = bigIntegerScaled.toByteArray();
               int length = bigIntegerBytesScaled.length;
               System.arraycopy(bigIntegerBytesScaled, 0, buffer, 0, length);
               return length;
            } else {
               FastHiveDecimal fastTemp = new FastHiveDecimal();
               if (!fastScaleUp(fast0, fast1, fast2, scaleUp, fastTemp)) {
                  throw new RuntimeException("Unexpected");
               } else {
                  return fastBigIntegerBytesUnscaled(fastSignum, fastTemp.fast0, fastTemp.fast1, fastTemp.fast2, scratchLongs, buffer);
               }
            }
         } else {
            FastHiveDecimal fastTemp = new FastHiveDecimal();
            return !fastRound(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, serializeScale, 4, fastTemp) ? 0 : fastBigIntegerBytesUnscaled(fastSignum, fastTemp.fast0, fastTemp.fast1, fastTemp.fast2, scratchLongs, buffer);
         }
      } else {
         return fastBigIntegerBytesUnscaled(fastSignum, fast0, fast1, fast2, scratchLongs, buffer);
      }
   }

   public static boolean fastIsByte(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastIntegerDigitCount < 3) {
         return true;
      } else if (fastIntegerDigitCount > 3) {
         return false;
      } else if (fastScale == 0) {
         if (fast1 == 0L && fast2 == 0L) {
            if (fastSignum == 1) {
               return fast0 <= 127L;
            } else {
               return -fast0 >= -128L;
            }
         } else {
            return false;
         }
      } else if (fastSignum == 1) {
         return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MAX_BYTE_VALUE_PLUS_ONE) < 0;
      } else {
         return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MIN_BYTE_VALUE_MINUS_ONE) > 0;
      }
   }

   public static byte fastByteValueClip(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastScale == 0) {
         if (fast1 == 0L && fast2 == 0L) {
            if (fastSignum == 1) {
               if (fast0 <= 127L) {
                  return (byte)((int)fast0);
               }
            } else if (-fast0 >= -128L) {
               return (byte)((int)(-fast0));
            }
         }

         BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, fast0, fast1, fast2);
         return bigInteger.remainder(BIG_INTEGER_UNSIGNED_BYTE_MAX_VALUE).byteValue();
      } else {
         long result0;
         long result1;
         long result2;
         if (fastScale < 16) {
            long divideFactor = powerOfTenTable[fastScale];
            long multiplyFactor = powerOfTenTable[16 - fastScale];
            result0 = fast0 / divideFactor + fast1 % divideFactor * multiplyFactor;
            result1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result2 = fast2 / divideFactor;
         } else if (fastScale < 32) {
            int adjustedScaleDown = fastScale - 16;
            long divideFactor = powerOfTenTable[adjustedScaleDown];
            long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
            result0 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result1 = fast2 / divideFactor;
            result2 = 0L;
         } else {
            int adjustedScaleDown = fastScale - 32;
            result0 = fast2 / powerOfTenTable[adjustedScaleDown];
            result1 = 0L;
            result2 = 0L;
         }

         if (result1 == 0L && result2 == 0L) {
            if (fastSignum == 1) {
               if (result0 <= 127L) {
                  return (byte)((int)result0);
               }
            } else if (-result0 >= -128L) {
               return (byte)((int)(-result0));
            }
         }

         BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, result0, result1, result2);
         return bigInteger.remainder(BIG_INTEGER_UNSIGNED_BYTE_MAX_VALUE).byteValue();
      }
   }

   public static boolean fastIsShort(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastIntegerDigitCount < 5) {
         return true;
      } else if (fastIntegerDigitCount > 5) {
         return false;
      } else if (fastScale == 0) {
         if (fast1 == 0L && fast2 == 0L) {
            if (fastSignum == 1) {
               return fast0 <= 32767L;
            } else {
               return -fast0 >= -32768L;
            }
         } else {
            return false;
         }
      } else if (fastSignum == 1) {
         return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MAX_SHORT_VALUE_PLUS_ONE) < 0;
      } else {
         return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MIN_SHORT_VALUE_MINUS_ONE) > 0;
      }
   }

   public static short fastShortValueClip(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastScale == 0) {
         if (fast1 == 0L && fast2 == 0L) {
            if (fastSignum == 1) {
               if (fast0 <= 32767L) {
                  return (short)((int)fast0);
               }
            } else if (-fast0 >= -32768L) {
               return (short)((int)(-fast0));
            }
         }

         BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, fast0, fast1, fast2);
         return bigInteger.remainder(BIG_INTEGER_UNSIGNED_SHORT_MAX_VALUE).shortValue();
      } else {
         long result0;
         long result1;
         long result2;
         if (fastScale < 16) {
            long divideFactor = powerOfTenTable[fastScale];
            long multiplyFactor = powerOfTenTable[16 - fastScale];
            result0 = fast0 / divideFactor + fast1 % divideFactor * multiplyFactor;
            result1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result2 = fast2 / divideFactor;
         } else if (fastScale < 32) {
            int adjustedScaleDown = fastScale - 16;
            long divideFactor = powerOfTenTable[adjustedScaleDown];
            long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
            result0 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result1 = fast2 / divideFactor;
            result2 = 0L;
         } else {
            int adjustedScaleDown = fastScale - 32;
            result0 = fast2 / powerOfTenTable[adjustedScaleDown];
            result1 = 0L;
            result2 = 0L;
         }

         if (result1 == 0L && result2 == 0L) {
            if (fastSignum == 1) {
               if (result0 <= 32767L) {
                  return (short)((int)result0);
               }
            } else if (-result0 >= -32768L) {
               return (short)((int)(-result0));
            }
         }

         BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, result0, result1, result2);
         return bigInteger.remainder(BIG_INTEGER_UNSIGNED_SHORT_MAX_VALUE).shortValue();
      }
   }

   public static boolean fastIsInt(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastIntegerDigitCount < 10) {
         return true;
      } else if (fastIntegerDigitCount > 10) {
         return false;
      } else if (fastScale == 0) {
         if (fast1 == 0L && fast2 == 0L) {
            if (fastSignum == 1) {
               return fast0 <= 2147483647L;
            } else {
               return -fast0 >= -2147483648L;
            }
         } else {
            return false;
         }
      } else if (fastSignum == 1) {
         return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MAX_INT_VALUE_PLUS_ONE) < 0;
      } else {
         return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MIN_INT_VALUE_MINUS_ONE) > 0;
      }
   }

   public static int fastIntValueClip(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastScale == 0) {
         if (fast1 == 0L && fast2 == 0L) {
            if (fastSignum == 1) {
               if (fast0 <= 2147483647L) {
                  return (int)fast0;
               }
            } else if (-fast0 >= -2147483648L) {
               return (int)(-fast0);
            }
         }

         BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, fast0, fast1, fast2);
         return bigInteger.remainder(BIG_INTEGER_UNSIGNED_INT_MAX_VALUE).intValue();
      } else {
         long result0;
         long result1;
         long result2;
         if (fastScale < 16) {
            long divideFactor = powerOfTenTable[fastScale];
            long multiplyFactor = powerOfTenTable[16 - fastScale];
            result0 = fast0 / divideFactor + fast1 % divideFactor * multiplyFactor;
            result1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result2 = fast2 / divideFactor;
         } else if (fastScale < 32) {
            int adjustedScaleDown = fastScale - 16;
            long divideFactor = powerOfTenTable[adjustedScaleDown];
            long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
            result0 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result1 = fast2 / divideFactor;
            result2 = 0L;
         } else {
            int adjustedScaleDown = fastScale - 32;
            result0 = fast2 / powerOfTenTable[adjustedScaleDown];
            result1 = 0L;
            result2 = 0L;
         }

         if (result1 == 0L && result2 == 0L) {
            if (fastSignum == 1) {
               if (result0 <= 2147483647L) {
                  return (int)result0;
               }
            } else if (-result0 >= -2147483648L) {
               return (int)(-result0);
            }
         }

         BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, result0, result1, result2);
         return bigInteger.remainder(BIG_INTEGER_UNSIGNED_INT_MAX_VALUE).intValue();
      }
   }

   public static boolean fastIsLong(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastIntegerDigitCount < MAX_LONG_DIGITS) {
         return true;
      } else if (fastIntegerDigitCount > MAX_LONG_DIGITS) {
         return false;
      } else if (fastScale != 0) {
         if (fastSignum == 1) {
            return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MAX_LONG_VALUE_PLUS_ONE) < 0;
         } else {
            return fastCompareTo(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MIN_LONG_VALUE_MINUS_ONE) > 0;
         }
      } else if (fastSignum == 1) {
         FastHiveDecimal max = FASTHIVEDECIMAL_MAX_LONG_VALUE;
         return fast1 <= max.fast1 && (fast1 != max.fast1 || fast0 <= max.fast0);
      } else {
         FastHiveDecimal min = FASTHIVEDECIMAL_MIN_LONG_VALUE;
         return fast1 <= min.fast1 && (fast1 != min.fast1 || fast0 <= min.fast0);
      }
   }

   public static long fastLongValueClip(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastSignum == 0) {
         return 0L;
      } else if (fastScale == 0) {
         if (fastCompareTo(1, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MAX_LONG_VALUE) <= 0) {
            return fastSignum == 1 ? fast1 * MULTIPLER_LONGWORD_DECIMAL + fast0 : -(fast1 * MULTIPLER_LONGWORD_DECIMAL + fast0);
         } else if (fastEquals(fastSignum, fast0, fast1, fast2, fastScale, FASTHIVEDECIMAL_MIN_LONG_VALUE)) {
            return Long.MIN_VALUE;
         } else {
            BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, fast0, fast1, fast2);
            return bigInteger.remainder(BIG_INTEGER_UNSIGNED_LONG_MAX_VALUE).longValue();
         }
      } else {
         long result0;
         long result1;
         long result2;
         if (fastScale < 16) {
            long divideFactor = powerOfTenTable[fastScale];
            long multiplyFactor = powerOfTenTable[16 - fastScale];
            result0 = fast0 / divideFactor + fast1 % divideFactor * multiplyFactor;
            result1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result2 = fast2 / divideFactor;
         } else if (fastScale < 32) {
            int adjustedScaleDown = fastScale - 16;
            long divideFactor = powerOfTenTable[adjustedScaleDown];
            long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
            result0 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result1 = fast2 / divideFactor;
            result2 = 0L;
         } else {
            int adjustedScaleDown = fastScale - 32;
            result0 = fast2 / powerOfTenTable[adjustedScaleDown];
            result1 = 0L;
            result2 = 0L;
         }

         if (fastCompareTo(1, result0, result1, result2, 0, FASTHIVEDECIMAL_MAX_LONG_VALUE) <= 0) {
            return fastSignum == 1 ? result1 * MULTIPLER_LONGWORD_DECIMAL + result0 : -(result1 * MULTIPLER_LONGWORD_DECIMAL + result0);
         } else if (fastEquals(fastSignum, result0, result1, result2, 0, FASTHIVEDECIMAL_MIN_LONG_VALUE)) {
            return Long.MIN_VALUE;
         } else {
            BigInteger bigInteger = fastBigIntegerValueUnscaled(fastSignum, result0, result1, result2);
            return bigInteger.remainder(BIG_INTEGER_UNSIGNED_LONG_MAX_VALUE).longValue();
         }
      }
   }

   public static float fastFloatValue(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastSignum == 0) {
         return 0.0F;
      } else {
         BigDecimal bigDecimal = fastBigDecimalValue(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
         return bigDecimal.floatValue();
      }
   }

   public static double fastDoubleValue(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastSignum == 0) {
         return (double)0.0F;
      } else {
         BigDecimal bigDecimal = fastBigDecimalValue(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
         return bigDecimal.doubleValue();
      }
   }

   public static BigInteger fastBigIntegerValue(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastSerializationScale) {
      return fastSerializationScale != -1 ? fastBigIntegerValueScaled(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, fastSerializationScale) : fastBigIntegerValueUnscaled(fastSignum, fast0, fast1, fast2);
   }

   public static BigInteger fastBigIntegerValueUnscaled(int fastSignum, long fast0, long fast1, long fast2) {
      if (fastSignum == 0) {
         return BigInteger.ZERO;
      } else {
         BigInteger result;
         if (fast2 == 0L) {
            if (fast1 == 0L) {
               result = BigInteger.valueOf(fast0);
            } else {
               result = BigInteger.valueOf(fast0).add(BigInteger.valueOf(fast1).multiply(BIG_INTEGER_LONGWORD_MULTIPLIER));
            }
         } else {
            result = BigInteger.valueOf(fast0).add(BigInteger.valueOf(fast1).multiply(BIG_INTEGER_LONGWORD_MULTIPLIER)).add(BigInteger.valueOf(fast2).multiply(BIG_INTEGER_LONGWORD_MULTIPLIER_2X));
         }

         return fastSignum == 1 ? result : result.negate();
      }
   }

   public static BigInteger fastBigIntegerValueScaled(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastSerializationScale) {
      BigDecimal bigDecimal = fastBigDecimalValue(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
      bigDecimal = bigDecimal.setScale(fastSerializationScale, RoundingMode.HALF_UP);
      return bigDecimal.unscaledValue();
   }

   public static BigDecimal fastBigDecimalValue(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      BigInteger unscaledValue = fastBigIntegerValueUnscaled(fastSignum, fast0, fast1, fast2);
      return new BigDecimal(unscaledValue, fastScale);
   }

   public static int fastCompareTo(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftScale, FastHiveDecimal fastRight) {
      return fastCompareTo(leftSignum, leftFast0, leftFast1, leftFast2, leftScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastScale);
   }

   private static int doCompareToSameScale(int signum, long leftFast0, long leftFast1, long leftFast2, long rightFast0, long rightFast1, long rightFast2) {
      if (leftFast0 == rightFast0 && leftFast1 == rightFast1 && leftFast2 == rightFast2) {
         return 0;
      } else if (leftFast2 < rightFast2) {
         return -signum;
      } else if (leftFast2 > rightFast2) {
         return signum;
      } else if (leftFast1 < rightFast1) {
         return -signum;
      } else if (leftFast1 > rightFast1) {
         return signum;
      } else {
         return leftFast0 < rightFast0 ? -signum : signum;
      }
   }

   public static int fastCompareTo(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightScale) {
      if (leftSignum == 0 && rightSignum == 0) {
         return 0;
      } else {
         int signDiff = leftSignum - rightSignum;
         if (signDiff != 0) {
            return signDiff > 0 ? 1 : -1;
         } else if (leftScale == rightScale) {
            return doCompareToSameScale(leftSignum, leftFast0, leftFast1, leftFast2, rightFast0, rightFast1, rightFast2);
         } else if (leftScale < rightScale) {
            int scaleDown = rightScale - leftScale;
            long compare0;
            long compare1;
            long compare2;
            if (scaleDown < 16) {
               long divideFactor = powerOfTenTable[scaleDown];
               long multiplyFactor = powerOfTenTable[16 - scaleDown];
               compare0 = rightFast0 / divideFactor + rightFast1 % divideFactor * multiplyFactor;
               compare1 = rightFast1 / divideFactor + rightFast2 % divideFactor * multiplyFactor;
               compare2 = rightFast2 / divideFactor;
            } else if (scaleDown < 32) {
               int adjustedScaleDown = scaleDown - 16;
               long divideFactor = powerOfTenTable[adjustedScaleDown];
               long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
               compare0 = rightFast1 / divideFactor + rightFast2 % divideFactor * multiplyFactor;
               compare1 = rightFast2 / divideFactor;
               compare2 = 0L;
            } else {
               int adjustedScaleDown = scaleDown - 32;
               compare0 = rightFast2 / powerOfTenTable[adjustedScaleDown];
               compare1 = 0L;
               compare2 = 0L;
            }

            if (leftFast0 == compare0 && leftFast1 == compare1 && leftFast2 == compare2) {
               return -leftSignum;
            } else if (leftFast2 < compare2) {
               return -leftSignum;
            } else if (leftFast2 > compare2) {
               return leftSignum;
            } else if (leftFast1 < compare1) {
               return -leftSignum;
            } else if (leftFast1 > compare1) {
               return leftSignum;
            } else {
               return leftFast0 < compare0 ? -leftSignum : leftSignum;
            }
         } else {
            int scaleDown = leftScale - rightScale;
            long compare0;
            long compare1;
            long compare2;
            if (scaleDown < 16) {
               long divideFactor = powerOfTenTable[scaleDown];
               long multiplyFactor = powerOfTenTable[16 - scaleDown];
               compare1 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor;
               compare0 = leftFast0 / divideFactor + leftFast1 % divideFactor * multiplyFactor;
               compare2 = leftFast2 / divideFactor;
            } else if (scaleDown < 32) {
               int adjustedScaleDown = scaleDown - 16;
               long divideFactor = powerOfTenTable[adjustedScaleDown];
               long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
               compare0 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor;
               compare1 = leftFast2 / divideFactor;
               compare2 = 0L;
            } else {
               int adjustedScaleDown = scaleDown - 32;
               compare0 = leftFast2 / powerOfTenTable[adjustedScaleDown];
               compare1 = 0L;
               compare2 = 0L;
            }

            if (compare0 == rightFast0 && compare1 == rightFast1 && compare2 == rightFast2) {
               return leftSignum;
            } else if (compare2 < rightFast2) {
               return -leftSignum;
            } else if (compare2 > rightFast2) {
               return leftSignum;
            } else if (compare1 < rightFast1) {
               return -leftSignum;
            } else if (compare1 > rightFast1) {
               return leftSignum;
            } else {
               return compare0 < rightFast0 ? -leftSignum : leftSignum;
            }
         }
      }
   }

   public static boolean fastEquals(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftScale, FastHiveDecimal fastRight) {
      if (leftSignum == 0) {
         return fastRight.fastSignum == 0;
      } else if (leftSignum != fastRight.fastSignum) {
         return false;
      } else if (leftScale != fastRight.fastScale) {
         return false;
      } else {
         return leftFast0 == fastRight.fast0 && leftFast1 == fastRight.fast1 && leftFast2 == fastRight.fast2;
      }
   }

   public static boolean fastEquals(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightScale) {
      if (leftSignum == 0) {
         return rightSignum == 0;
      } else if (leftSignum != rightSignum) {
         return false;
      } else if (leftScale != rightScale) {
         return false;
      } else {
         return leftFast0 == rightFast0 && leftFast1 == rightFast1 && leftFast2 == rightFast2;
      }
   }

   private static int doCalculateNewFasterHashCode(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      long key = ~fast0 + (fast0 << 21);
      key ^= key >>> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >>> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >>> 28;
      key += key << 31;
      long longHashCode = key;
      key = ~fast1 + (fast1 << 21);
      key ^= key >>> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >>> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >>> 28;
      key += key << 31;
      longHashCode ^= key;
      key = ~fast2 + (fast2 << 21);
      key ^= key >>> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >>> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >>> 28;
      key += key << 31;
      longHashCode ^= key;
      key = (long)fastSignum;
      key = ~key + (key << 21);
      key ^= key >>> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >>> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >>> 28;
      key += key << 31;
      longHashCode ^= key;
      key = (long)fastIntegerDigitCount;
      key = ~key + (key << 21);
      key ^= key >>> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >>> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >>> 28;
      key += key << 31;
      longHashCode ^= key;
      key = (long)fastScale;
      key = ~key + (key << 21);
      key ^= key >>> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >>> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >>> 28;
      key += key << 31;
      longHashCode ^= key;
      return (int)longHashCode;
   }

   public static int fastNewFasterHashCode(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastSignum == 0) {
         return ZERO_NEW_FASTER_HASH_CODE;
      } else {
         int hashCode = doCalculateNewFasterHashCode(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
         return hashCode;
      }
   }

   public static int fastHashCode(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      BigDecimal bigDecimal = fastBigDecimalValue(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
      return bigDecimal.hashCode();
   }

   public static boolean fastScaleByPowerOfTen(FastHiveDecimal fastDec, int power, FastHiveDecimal fastResult) {
      return fastScaleByPowerOfTen(fastDec.fastSignum, fastDec.fast0, fastDec.fast1, fastDec.fast2, fastDec.fastIntegerDigitCount, fastDec.fastScale, power, fastResult);
   }

   public static boolean fastScaleByPowerOfTen(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int power, FastHiveDecimal fastResult) {
      if (fastSignum == 0) {
         fastResult.fastReset();
         return true;
      } else if (power == 0) {
         fastResult.fastSet(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
         return true;
      } else {
         int absPower = Math.abs(power);
         if (power > 0) {
            if (fastIntegerDigitCount > 0) {
               int integerRoom = 38 - fastIntegerDigitCount;
               if (integerRoom < power) {
                  return false;
               }

               fastResult.fastSignum = fastSignum;
               if (fastScale <= power) {
                  int scaleUp = power - fastScale;
                  if (scaleUp > 0) {
                     if (!fastScaleUp(fast0, fast1, fast2, scaleUp, fastResult)) {
                        throw new RuntimeException("Unexpected");
                     }
                  } else {
                     fastResult.fast0 = fast0;
                     fastResult.fast1 = fast1;
                     fastResult.fast2 = fast2;
                  }

                  fastResult.fastIntegerDigitCount = fastIntegerDigitCount + fastScale + scaleUp;
                  fastResult.fastScale = 0;
               } else {
                  fastResult.fastSet(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount + power, fastScale - power);
               }
            } else {
               int rawPrecision = fastRawPrecision(fastSignum, fast0, fast1, fast2);
               int zeroesBelowDot = fastScale - rawPrecision;
               if (power > 38 + zeroesBelowDot) {
                  return false;
               }

               int newIntegerDigitCount = Math.max(0, power - zeroesBelowDot);
               if (newIntegerDigitCount > rawPrecision) {
                  fastResult.fastSignum = fastSignum;
                  int scaleUp = newIntegerDigitCount - rawPrecision;
                  if (!fastScaleUp(fast0, fast1, fast2, scaleUp, fastResult)) {
                     throw new RuntimeException("Unexpected");
                  }

                  fastResult.fastIntegerDigitCount = newIntegerDigitCount;
                  fastResult.fastScale = 0;
               } else {
                  int newScale = Math.max(0, fastScale - power);
                  fastResult.fastSet(fastSignum, fast0, fast1, fast2, newIntegerDigitCount, newScale);
               }
            }
         } else if (fastScale + absPower <= 38) {
            int newScale = fastScale + absPower;
            int newIntegerDigitCount = Math.max(0, fastIntegerDigitCount - absPower);
            int trailingZeroCount = fastTrailingDecimalZeroCount(fast0, fast1, fast2, newIntegerDigitCount, newScale);
            if (trailingZeroCount > 0) {
               fastResult.fastSignum = fastSignum;
               doFastScaleDown(fast0, fast1, fast2, trailingZeroCount, fastResult);
               fastResult.fastScale = newScale - trailingZeroCount;
               fastResult.fastIntegerDigitCount = newIntegerDigitCount;
            } else {
               fastResult.fastSet(fastSignum, fast0, fast1, fast2, newIntegerDigitCount, newScale);
            }
         } else {
            int scaleDown = fastScale + absPower - 38;
            if (scaleDown < 38) {
               if (!fastRoundFractionalHalfUp(fastSignum, fast0, fast1, fast2, scaleDown, fastResult)) {
                  return false;
               }

               if (fastResult.fastSignum != 0) {
                  fastResult.fastScale = 38;
                  fastResult.fastIntegerDigitCount = Math.max(0, fastRawPrecision(fastResult) - fastResult.fastScale);
                  int trailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
                  if (trailingZeroCount > 0) {
                     doFastScaleDown(fastResult, trailingZeroCount, fastResult);
                     fastResult.fastScale -= trailingZeroCount;
                  }
               }
            } else {
               fastResult.fastReset();
            }
         }

         return true;
      }
   }

   public static boolean doFastRound(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int roundPower, int roundingMode, FastHiveDecimal fastResult) {
      if (fastSignum == 0) {
         fastResult.fastReset();
         return true;
      } else if (fastScale == roundPower) {
         fastResult.fastSet(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
         return true;
      } else {
         if (roundPower > fastScale) {
            fastResult.fastSet(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
         } else {
            if (roundPower < 0) {
               switch (roundingMode) {
                  case 0:
                     if (!fastRoundIntegerUp(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                        return false;
                     }
                     break;
                  case 1:
                     if (!fastRoundIntegerDown(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                        return false;
                     }
                     break;
                  case 2:
                     if (fastSignum == 1) {
                        if (!fastRoundIntegerUp(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                           return false;
                        }

                        if (fastResult.fast2 > MAX_HIGHWORD_DECIMAL) {
                           return false;
                        }
                     } else if (!fastRoundIntegerDown(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                        return false;
                     }
                     break;
                  case 3:
                     if (fastSignum == 1) {
                        if (!fastRoundIntegerDown(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                           return false;
                        }
                     } else {
                        if (!fastRoundIntegerUp(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                           return false;
                        }

                        if (fastResult.fast2 > MAX_HIGHWORD_DECIMAL) {
                           return false;
                        }
                     }
                     break;
                  case 4:
                     if (!fastRoundIntegerHalfUp(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                        return false;
                     }

                     if (fastResult.fast2 > MAX_HIGHWORD_DECIMAL) {
                        return false;
                     }
                     break;
                  case 5:
                  default:
                     throw new RuntimeException("Unsupported rounding mode " + roundingMode);
                  case 6:
                     if (!fastRoundIntegerHalfEven(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, roundPower, fastResult)) {
                        return false;
                     }

                     if (fastResult.fast2 > MAX_HIGHWORD_DECIMAL) {
                        return false;
                     }
               }

               return true;
            }

            int scaleDown = fastScale - roundPower;
            switch (roundingMode) {
               case 0:
                  if (!fastRoundFractionalUp(fastSignum, fast0, fast1, fast2, scaleDown, fastResult)) {
                     return false;
                  }
                  break;
               case 1:
                  fastRoundFractionalDown(fastSignum, fast0, fast1, fast2, scaleDown, fastResult);
                  break;
               case 2:
                  if (fastSignum == 1) {
                     if (!fastRoundFractionalUp(fastSignum, fast0, fast1, fast2, scaleDown, fastResult)) {
                        return false;
                     }
                  } else {
                     fastRoundFractionalDown(fastSignum, fast0, fast1, fast2, scaleDown, fastResult);
                  }
                  break;
               case 3:
                  if (fastSignum == 1) {
                     fastRoundFractionalDown(fastSignum, fast0, fast1, fast2, scaleDown, fastResult);
                  } else if (!fastRoundFractionalUp(fastSignum, fast0, fast1, fast2, scaleDown, fastResult)) {
                     return false;
                  }
                  break;
               case 4:
                  if (!fastRoundFractionalHalfUp(fastSignum, fast0, fast1, fast2, scaleDown, fastResult)) {
                     return false;
                  }
                  break;
               case 5:
               default:
                  throw new RuntimeException("Unsupported rounding mode " + roundingMode);
               case 6:
                  if (!fastRoundFractionalHalfEven(fastSignum, fast0, fast1, fast2, scaleDown, fastResult)) {
                     return false;
                  }
            }

            if (fastResult.fastSignum == 0) {
               fastResult.fastScale = 0;
            } else {
               int rawPrecision = fastRawPrecision(fastResult);
               fastResult.fastIntegerDigitCount = Math.max(0, rawPrecision - roundPower);
               fastResult.fastScale = roundPower;
               int trailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
               if (trailingZeroCount > 0) {
                  doFastScaleDown(fastResult, trailingZeroCount, fastResult);
                  fastResult.fastScale -= trailingZeroCount;
               }
            }
         }

         return true;
      }
   }

   public static boolean fastRound(FastHiveDecimal fastDec, int newScale, int roundingMode, FastHiveDecimal fastResult) {
      return fastRound(fastDec.fastSignum, fastDec.fast0, fastDec.fast1, fastDec.fast2, fastDec.fastIntegerDigitCount, fastDec.fastScale, newScale, roundingMode, fastResult);
   }

   public static boolean fastRound(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int newScale, int roundingMode, FastHiveDecimal fastResult) {
      return doFastRound(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, newScale, roundingMode, fastResult);
   }

   private static boolean isRoundPortionAllZeroes(long fast0, long fast1, long fast2, int roundingPoint) {
      boolean isRoundPortionAllZeroes;
      if (roundingPoint < 16) {
         long roundPointFactor = powerOfTenTable[roundingPoint];
         isRoundPortionAllZeroes = fast0 % roundPointFactor == 0L;
      } else if (roundingPoint < 32) {
         int adjustedRoundingPoint = roundingPoint - 16;
         if (adjustedRoundingPoint == 0) {
            isRoundPortionAllZeroes = fast0 == 0L;
         } else {
            long roundPointFactor = powerOfTenTable[adjustedRoundingPoint];
            long roundPortion = fast1 % roundPointFactor;
            isRoundPortionAllZeroes = fast0 == 0L && roundPortion == 0L;
         }
      } else {
         int adjustedRoundingPoint = roundingPoint - 32;
         if (adjustedRoundingPoint == 0) {
            isRoundPortionAllZeroes = fast0 == 0L && fast1 == 0L;
         } else {
            long roundPointFactor = powerOfTenTable[adjustedRoundingPoint];
            long roundPortion = fast2 % roundPointFactor;
            isRoundPortionAllZeroes = fast0 == 0L && fast1 == 0L && roundPortion == 0L;
         }
      }

      return isRoundPortionAllZeroes;
   }

   private static boolean isRoundPortionHalfUp(long fast0, long fast1, long fast2, int roundingPoint) {
      boolean isRoundPortionHalfUp;
      if (roundingPoint < 16) {
         long withRoundDigit = fast0 / powerOfTenTable[roundingPoint - 1];
         long roundDigit = withRoundDigit % 10L;
         isRoundPortionHalfUp = roundDigit >= 5L;
      } else if (roundingPoint < 32) {
         int adjustedRoundingPoint = roundingPoint - 16;
         long roundDigit;
         if (adjustedRoundingPoint == 0) {
            roundDigit = fast0 / (MULTIPLER_LONGWORD_DECIMAL / 10L);
         } else {
            long withRoundDigit = fast1 / powerOfTenTable[adjustedRoundingPoint - 1];
            roundDigit = withRoundDigit % 10L;
         }

         isRoundPortionHalfUp = roundDigit >= 5L;
      } else {
         int adjustedRoundingPoint = roundingPoint - 32;
         long roundDigit;
         if (adjustedRoundingPoint == 0) {
            roundDigit = fast1 / (MULTIPLER_LONGWORD_DECIMAL / 10L);
         } else {
            long withRoundDigit = fast2 / powerOfTenTable[adjustedRoundingPoint - 1];
            roundDigit = withRoundDigit % 10L;
         }

         isRoundPortionHalfUp = roundDigit >= 5L;
      }

      return isRoundPortionHalfUp;
   }

   private static boolean isRoundPortionHalfEven(long fast0, long fast1, long fast2, int roundingPoint) {
      boolean isRoundPortionHalfEven;
      if (roundingPoint < 16) {
         long roundDivisor = powerOfTenTable[roundingPoint - 1];
         long withRoundDigit = fast0 / roundDivisor;
         long roundDigit = withRoundDigit % 10L;
         long fast0Scaled = withRoundDigit / 10L;
         if (roundDigit > 5L) {
            isRoundPortionHalfEven = true;
         } else if (roundDigit == 5L) {
            boolean exactlyOneHalf;
            if (roundingPoint - 1 == 0) {
               exactlyOneHalf = true;
            } else {
               exactlyOneHalf = fast0 % roundDivisor == 0L;
            }

            if (exactlyOneHalf) {
               isRoundPortionHalfEven = fast0Scaled % 2L == 1L;
            } else {
               isRoundPortionHalfEven = true;
            }
         } else {
            isRoundPortionHalfEven = false;
         }
      } else if (roundingPoint < 32) {
         int adjustedRoundingPoint = roundingPoint - 16;
         if (adjustedRoundingPoint == 0) {
            long roundDivisor = MULTIPLER_LONGWORD_DECIMAL / 10L;
            long roundDigit = fast0 / roundDivisor;
            if (roundDigit > 5L) {
               isRoundPortionHalfEven = true;
            } else if (roundDigit == 5L) {
               boolean exactlyOneHalf = fast0 % roundDivisor == 0L;
               if (exactlyOneHalf) {
                  isRoundPortionHalfEven = fast1 % 2L == 1L;
               } else {
                  isRoundPortionHalfEven = true;
               }
            } else {
               isRoundPortionHalfEven = false;
            }
         } else {
            long roundDivisor = powerOfTenTable[adjustedRoundingPoint - 1];
            long withRoundDigit = fast1 / roundDivisor;
            long roundDigit = withRoundDigit % 10L;
            long fast1Scaled = withRoundDigit / 10L;
            if (roundDigit > 5L) {
               isRoundPortionHalfEven = true;
            } else if (roundDigit == 5L) {
               boolean exactlyOneHalf;
               if (adjustedRoundingPoint - 1 == 0) {
                  exactlyOneHalf = fast0 == 0L;
               } else {
                  exactlyOneHalf = fast0 == 0L && fast1 % roundDivisor == 0L;
               }

               if (exactlyOneHalf) {
                  isRoundPortionHalfEven = fast1Scaled % 2L == 1L;
               } else {
                  isRoundPortionHalfEven = true;
               }
            } else {
               isRoundPortionHalfEven = false;
            }
         }
      } else {
         int adjustedRoundingPoint = roundingPoint - 32;
         if (adjustedRoundingPoint == 0) {
            long roundDivisor = MULTIPLER_LONGWORD_DECIMAL / 10L;
            long roundDigit = fast1 / roundDivisor;
            if (roundDigit > 5L) {
               isRoundPortionHalfEven = true;
            } else if (roundDigit == 5L) {
               boolean exactlyOneHalf = fast1 % roundDivisor == 0L && fast0 == 0L;
               if (exactlyOneHalf) {
                  isRoundPortionHalfEven = fast2 % 2L == 1L;
               } else {
                  isRoundPortionHalfEven = true;
               }
            } else {
               isRoundPortionHalfEven = false;
            }
         } else {
            long roundDivisor = powerOfTenTable[adjustedRoundingPoint - 1];
            long withRoundDigit = fast2 / roundDivisor;
            long roundDigit = withRoundDigit % 10L;
            long fast2Scaled = withRoundDigit / 10L;
            if (roundDigit > 5L) {
               isRoundPortionHalfEven = true;
            } else if (roundDigit == 5L) {
               boolean exactlyOneHalf;
               if (adjustedRoundingPoint - 1 == 0) {
                  exactlyOneHalf = fast1 == 0L && fast0 == 0L;
               } else {
                  exactlyOneHalf = fast2 % roundDivisor == 0L && fast1 == 0L && fast0 == 0L;
               }

               if (exactlyOneHalf) {
                  isRoundPortionHalfEven = fast2Scaled % 2L == 1L;
               } else {
                  isRoundPortionHalfEven = true;
               }
            } else {
               isRoundPortionHalfEven = false;
            }
         }
      }

      return isRoundPortionHalfEven;
   }

   private static void doClearRoundIntegerPortionAndAddOne(long fast0, long fast1, long fast2, int absRoundPower, FastHiveDecimal fastResult) {
      long result0;
      long result1;
      long result2;
      if (absRoundPower < 16) {
         long roundFactor = powerOfTenTable[absRoundPower];
         long r0 = fast0 / roundFactor * roundFactor + roundFactor;
         result0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
         long r1 = fast1 + r0 / MULTIPLER_LONGWORD_DECIMAL;
         result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
         result2 = fast2 + r1 / MULTIPLER_LONGWORD_DECIMAL;
      } else if (absRoundPower < 32) {
         int adjustedAbsPower = absRoundPower - 16;
         long roundFactor = powerOfTenTable[adjustedAbsPower];
         result0 = 0L;
         long r1 = fast1 / roundFactor * roundFactor + roundFactor;
         result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
         result2 = fast2 + r1 / MULTIPLER_LONGWORD_DECIMAL;
      } else {
         int adjustedAbsPower = absRoundPower - 32;
         long roundFactor = powerOfTenTable[adjustedAbsPower];
         result0 = 0L;
         result1 = 0L;
         result2 = fast2 / roundFactor * roundFactor + roundFactor;
      }

      fastResult.fast0 = result0;
      fastResult.fast1 = result1;
      fastResult.fast2 = result2;
   }

   private static void doClearRoundIntegerPortion(long fast0, long fast1, long fast2, int absRoundPower, FastHiveDecimal fastResult) {
      long result0;
      long result1;
      long result2;
      if (absRoundPower < 16) {
         long roundFactor = powerOfTenTable[absRoundPower];
         result0 = fast0 / roundFactor * roundFactor;
         result1 = fast1;
         result2 = fast2;
      } else if (absRoundPower < 32) {
         int adjustedAbsPower = absRoundPower - 16;
         long roundFactor = powerOfTenTable[adjustedAbsPower];
         result0 = 0L;
         result1 = fast1 / roundFactor * roundFactor;
         result2 = fast2;
      } else {
         int adjustedAbsPower = absRoundPower - 32;
         long roundFactor = powerOfTenTable[adjustedAbsPower];
         result0 = 0L;
         result1 = 0L;
         result2 = fast2 / roundFactor * roundFactor;
      }

      fastResult.fast0 = result0;
      fastResult.fast1 = result1;
      fastResult.fast2 = result2;
   }

   public static boolean fastRoundIntegerUp(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int roundPower, FastHiveDecimal fastResult) {
      if (roundPower >= 0) {
         throw new IllegalArgumentException("Expecting roundPower < 0 (roundPower " + roundPower + ")");
      } else {
         int absRoundPower = -roundPower;
         if (fastIntegerDigitCount < absRoundPower) {
            return false;
         } else {
            int roundingPoint = absRoundPower + fastScale;
            if (roundingPoint > 38) {
               return false;
            } else {
               boolean isRoundPortionAllZeroes = isRoundPortionAllZeroes(fast0, fast1, fast2, roundingPoint);
               if (fastScale == 0) {
                  fastResult.fast0 = fast0;
                  fastResult.fast1 = fast1;
                  fastResult.fast2 = fast2;
               } else {
                  doFastScaleDown(fast0, fast1, fast2, fastScale, fastResult);
               }

               if (!isRoundPortionAllZeroes) {
                  doClearRoundIntegerPortionAndAddOne(fastResult.fast0, fastResult.fast1, fastResult.fast2, absRoundPower, fastResult);
               }

               if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
                  fastResult.fastSignum = 0;
                  fastResult.fastIntegerDigitCount = 0;
                  fastResult.fastScale = 0;
               } else {
                  fastResult.fastSignum = fastSignum;
                  fastResult.fastIntegerDigitCount = fastRawPrecision(fastResult);
                  fastResult.fastScale = 0;
               }

               return true;
            }
         }
      }
   }

   public static boolean fastRoundIntegerDown(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int roundPower, FastHiveDecimal fastResult) {
      if (roundPower >= 0) {
         throw new IllegalArgumentException("Expecting roundPower < 0 (roundPower " + roundPower + ")");
      } else {
         int absRoundPower = -roundPower;
         if (fastIntegerDigitCount < absRoundPower) {
            fastResult.fastReset();
            return true;
         } else {
            int roundingPoint = absRoundPower + fastScale;
            if (roundingPoint > 38) {
               fastResult.fastReset();
               return true;
            } else {
               if (fastScale == 0) {
                  fastResult.fast0 = fast0;
                  fastResult.fast1 = fast1;
                  fastResult.fast2 = fast2;
               } else {
                  doFastScaleDown(fast0, fast1, fast2, fastScale, fastResult);
               }

               doClearRoundIntegerPortion(fastResult.fast0, fastResult.fast1, fastResult.fast2, absRoundPower, fastResult);
               if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
                  fastResult.fastIntegerDigitCount = 0;
                  fastResult.fastScale = 0;
               } else {
                  fastResult.fastSignum = fastSignum;
                  fastResult.fastIntegerDigitCount = fastRawPrecision(fastResult);
                  fastResult.fastScale = 0;
               }

               return true;
            }
         }
      }
   }

   public static boolean fastRoundIntegerHalfUp(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int roundPower, FastHiveDecimal fastResult) {
      if (roundPower >= 0) {
         throw new IllegalArgumentException("Expecting roundPower < 0 (roundPower " + roundPower + ")");
      } else {
         int absRoundPower = -roundPower;
         if (fastIntegerDigitCount < absRoundPower) {
            fastResult.fastReset();
            return true;
         } else {
            int roundingPoint = absRoundPower + fastScale;
            if (roundingPoint > 38) {
               fastResult.fastReset();
               return true;
            } else {
               boolean isRoundPortionHalfUp = isRoundPortionHalfUp(fast0, fast1, fast2, roundingPoint);
               if (fastScale == 0) {
                  fastResult.fast0 = fast0;
                  fastResult.fast1 = fast1;
                  fastResult.fast2 = fast2;
               } else {
                  doFastScaleDown(fast0, fast1, fast2, fastScale, fastResult);
               }

               if (isRoundPortionHalfUp) {
                  doClearRoundIntegerPortionAndAddOne(fastResult.fast0, fastResult.fast1, fastResult.fast2, absRoundPower, fastResult);
               } else {
                  doClearRoundIntegerPortion(fastResult.fast0, fastResult.fast1, fastResult.fast2, absRoundPower, fastResult);
               }

               if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
                  fastResult.fastSignum = 0;
                  fastResult.fastIntegerDigitCount = 0;
                  fastResult.fastScale = 0;
               } else {
                  fastResult.fastSignum = fastSignum;
                  fastResult.fastIntegerDigitCount = fastRawPrecision(fastResult);
                  fastResult.fastScale = 0;
               }

               return true;
            }
         }
      }
   }

   public static boolean fastRoundIntegerHalfEven(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int roundPower, FastHiveDecimal fastResult) {
      if (roundPower >= 0) {
         throw new IllegalArgumentException("Expecting roundPower < 0 (roundPower " + roundPower + ")");
      } else {
         int absRoundPower = -roundPower;
         if (fastIntegerDigitCount < absRoundPower) {
            fastResult.fastReset();
         }

         int roundingPoint = absRoundPower + fastScale;
         if (roundingPoint > 38) {
            fastResult.fastReset();
            return true;
         } else {
            boolean isRoundPortionHalfEven = isRoundPortionHalfEven(fast0, fast1, fast2, roundingPoint);
            if (fastScale == 0) {
               fastResult.fast0 = fast0;
               fastResult.fast1 = fast1;
               fastResult.fast2 = fast2;
            } else {
               doFastScaleDown(fast0, fast1, fast2, fastScale, fastResult);
            }

            if (isRoundPortionHalfEven) {
               doClearRoundIntegerPortionAndAddOne(fastResult.fast0, fastResult.fast1, fastResult.fast2, absRoundPower, fastResult);
            } else {
               doClearRoundIntegerPortion(fastResult.fast0, fastResult.fast1, fastResult.fast2, absRoundPower, fastResult);
            }

            if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
               fastResult.fastSignum = 0;
               fastResult.fastIntegerDigitCount = 0;
               fastResult.fastScale = 0;
            } else {
               fastResult.fastSignum = fastSignum;
               fastResult.fastIntegerDigitCount = fastRawPrecision(fastResult);
               fastResult.fastScale = 0;
            }

            return true;
         }
      }
   }

   public static boolean fastScaleDownNoRound(int fastSignum, long fast0, long fast1, long fast2, int scaleDown, FastHiveDecimal fastResult) {
      if (scaleDown >= 1 && scaleDown < 47) {
         long result0;
         long result1;
         long result2;
         if (scaleDown < 16) {
            long divideFactor = powerOfTenTable[scaleDown];
            long multiplyFactor = powerOfTenTable[16 - scaleDown];
            long throwAwayFraction = fast0 % divideFactor;
            if (throwAwayFraction != 0L) {
               return false;
            }

            result0 = fast0 / divideFactor + fast1 % divideFactor * multiplyFactor;
            result1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result2 = fast2 / divideFactor;
         } else if (scaleDown < 32) {
            int adjustedScaleDown = scaleDown - 16;
            long divideFactor = powerOfTenTable[adjustedScaleDown];
            long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
            boolean isThrowAwayFractionZero;
            if (adjustedScaleDown == 0) {
               isThrowAwayFractionZero = fast0 == 0L;
            } else {
               long throwAwayFraction = fast1 % divideFactor;
               isThrowAwayFractionZero = throwAwayFraction == 0L && fast0 == 0L;
            }

            if (!isThrowAwayFractionZero) {
               return false;
            }

            result0 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result1 = fast2 / divideFactor;
            result2 = 0L;
         } else {
            int adjustedScaleDown = scaleDown - 32;
            long divideFactor = powerOfTenTable[adjustedScaleDown];
            boolean isThrowAwayFractionZero;
            if (adjustedScaleDown == 0) {
               isThrowAwayFractionZero = fast0 == 0L && fast1 == 0L;
            } else {
               long throwAwayFraction = fast2 % divideFactor;
               isThrowAwayFractionZero = throwAwayFraction == 0L && fast0 == 0L && fast1 == 0L;
            }

            if (!isThrowAwayFractionZero) {
               return false;
            }

            result0 = fast2 / divideFactor;
            result1 = 0L;
            result2 = 0L;
         }

         if (result0 == 0L && result1 == 0L && result2 == 0L) {
            fastResult.fastReset();
         } else {
            fastResult.fastSignum = fastSignum;
            fastResult.fast0 = result0;
            fastResult.fast1 = result1;
            fastResult.fast2 = result2;
         }

         return true;
      } else {
         throw new IllegalArgumentException("Expecting scaleDown > 0 and scaleDown < 3*16 - 1 (scaleDown " + scaleDown + ")");
      }
   }

   public static boolean fastRoundFractionalUp(int fastSignum, long fast0, long fast1, long fast2, int scaleDown, FastHiveDecimal fastResult) {
      if (scaleDown >= 1 && scaleDown <= 38) {
         if (scaleDown == 38) {
            if (fast0 == 0L && fast1 == 0L && fast2 == 0L) {
               fastResult.fastReset();
            } else {
               fastResult.fastSet(fastSignum, 1L, 0L, 0L, 1, 0);
            }

            return true;
         } else {
            boolean isRoundPortionAllZeroes = isRoundPortionAllZeroes(fast0, fast1, fast2, scaleDown);
            doFastScaleDown(fast0, fast1, fast2, scaleDown, fastResult);
            if (!isRoundPortionAllZeroes) {
               long r0 = fastResult.fast0 + 1L;
               fastResult.fast0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
               long r1 = fastResult.fast1 + r0 / MULTIPLER_LONGWORD_DECIMAL;
               fastResult.fast1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
               fastResult.fast2 += r1 / MULTIPLER_LONGWORD_DECIMAL;
            }

            if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
               fastResult.fastSignum = 0;
               fastResult.fastIntegerDigitCount = 0;
               fastResult.fastScale = 0;
            } else {
               fastResult.fastSignum = fastSignum;
            }

            return fastResult.fast2 <= MAX_HIGHWORD_DECIMAL;
         }
      } else {
         throw new IllegalArgumentException("Expecting scaleDown > 0 and scaleDown < 38 (scaleDown " + scaleDown + ")");
      }
   }

   public static void fastRoundFractionalDown(int fastSignum, long fast0, long fast1, long fast2, int scaleDown, FastHiveDecimal fastResult) {
      if (scaleDown >= 1 && scaleDown <= 38) {
         if (scaleDown == 38) {
            fastResult.fastReset();
         } else {
            doFastScaleDown(fast0, fast1, fast2, scaleDown, fastResult);
            if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
               fastResult.fastSignum = 0;
               fastResult.fastIntegerDigitCount = 0;
               fastResult.fastScale = 0;
            } else {
               fastResult.fastSignum = fastSignum;
            }

         }
      } else {
         throw new IllegalArgumentException("Expecting scaleDown > 0 and scaleDown < 38 (scaleDown " + scaleDown + ")");
      }
   }

   public static boolean fastRoundFractionalHalfUp(int fastSignum, long fast0, long fast1, long fast2, int scaleDown, FastHiveDecimal fastResult) {
      if (fastSignum == 0) {
         throw new IllegalArgumentException("Unexpected zero value");
      } else if (scaleDown >= 1 && scaleDown <= 38) {
         if (scaleDown == 38) {
            long roundDigit = fast2 / powerOfTenTable[5];
            if (roundDigit < 5L) {
               fastResult.fastReset();
            } else {
               fastResult.fastSet(fastSignum, 1L, 0L, 0L, 1, 0);
            }

            return true;
         } else {
            boolean isRoundPortionHalfUp = isRoundPortionHalfUp(fast0, fast1, fast2, scaleDown);
            doFastScaleDown(fast0, fast1, fast2, scaleDown, fastResult);
            if (isRoundPortionHalfUp) {
               long r0 = fastResult.fast0 + 1L;
               fastResult.fast0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
               long r1 = fastResult.fast1 + r0 / MULTIPLER_LONGWORD_DECIMAL;
               fastResult.fast1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
               fastResult.fast2 += r1 / MULTIPLER_LONGWORD_DECIMAL;
            }

            if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
               fastResult.fastSignum = 0;
               fastResult.fastIntegerDigitCount = 0;
               fastResult.fastScale = 0;
            } else {
               fastResult.fastSignum = fastSignum;
            }

            return fastResult.fast2 <= MAX_HIGHWORD_DECIMAL;
         }
      } else {
         throw new IllegalArgumentException("Expecting scaleDown > 0 and scaleDown < 38 (scaleDown " + scaleDown + ")");
      }
   }

   public static boolean fastRoundFractionalHalfUp5Words(int fastSignum, long fast0, long fast1, long fast2, long fast3, long fast4, int scaleDown, FastHiveDecimal fastResult) {
      long result0;
      long result1;
      long result2;
      long result3;
      long result4;
      if (scaleDown < 16) {
         long withRoundDigit = fast0 / powerOfTenTable[scaleDown - 1];
         long roundDigit = withRoundDigit % 10L;
         long divideFactor = powerOfTenTable[scaleDown];
         long multiplyFactor = powerOfTenTable[16 - scaleDown];
         if (roundDigit < 5L) {
            result0 = withRoundDigit / 10L + fast1 % divideFactor * multiplyFactor;
            result1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
            result2 = fast2 / divideFactor + fast3 % divideFactor * multiplyFactor;
            result3 = fast3 / divideFactor + fast4 % divideFactor * multiplyFactor;
            result4 = fast4 / divideFactor;
         } else {
            long r0 = withRoundDigit / 10L + fast1 % divideFactor * multiplyFactor + 1L;
            result0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
            long r1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor + r0 / MULTIPLER_LONGWORD_DECIMAL;
            result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
            long r2 = fast2 / divideFactor + fast3 % divideFactor * multiplyFactor + r1 / MULTIPLER_LONGWORD_DECIMAL;
            result2 = r2 % MULTIPLER_LONGWORD_DECIMAL;
            long r3 = fast3 / divideFactor + fast4 % divideFactor * multiplyFactor + r2 / MULTIPLER_LONGWORD_DECIMAL;
            result3 = r3 % MULTIPLER_LONGWORD_DECIMAL;
            result4 = fast4 / divideFactor + r3 % MULTIPLER_LONGWORD_DECIMAL;
         }
      } else if (scaleDown < 32) {
         int adjustedScaleDown = scaleDown - 16;
         long roundDigit;
         long fast1Scaled;
         if (adjustedScaleDown == 0) {
            roundDigit = fast0 / (MULTIPLER_LONGWORD_DECIMAL / 10L);
            fast1Scaled = fast1;
         } else {
            long withRoundDigit = fast1 / powerOfTenTable[adjustedScaleDown - 1];
            roundDigit = withRoundDigit % 10L;
            fast1Scaled = withRoundDigit / 10L;
         }

         long divideFactor = powerOfTenTable[adjustedScaleDown];
         long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
         if (roundDigit < 5L) {
            result0 = fast1Scaled + fast2 % divideFactor * multiplyFactor;
            result1 = fast2 / divideFactor + fast3 % divideFactor * multiplyFactor;
            result2 = fast3 / divideFactor + fast4 % divideFactor * multiplyFactor;
            result3 = fast4 / divideFactor;
         } else {
            long r0 = fast1Scaled + fast2 % divideFactor * multiplyFactor + 1L;
            result0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
            long r1 = fast2 / divideFactor + fast3 % divideFactor * multiplyFactor + r0 / MULTIPLER_LONGWORD_DECIMAL;
            result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
            long r2 = fast3 / divideFactor + fast4 % divideFactor * multiplyFactor + r1 / MULTIPLER_LONGWORD_DECIMAL;
            result2 = r2 % MULTIPLER_LONGWORD_DECIMAL;
            result3 = fast4 / divideFactor + r2 / MULTIPLER_LONGWORD_DECIMAL;
         }

         result4 = 0L;
      } else {
         int adjustedScaleDown = scaleDown - 32;
         long roundDigit;
         long fast2Scaled;
         if (adjustedScaleDown == 0) {
            roundDigit = fast1 / (MULTIPLER_LONGWORD_DECIMAL / 10L);
            fast2Scaled = fast2;
         } else {
            long withRoundDigit = fast2 / powerOfTenTable[adjustedScaleDown - 1];
            roundDigit = withRoundDigit % 10L;
            fast2Scaled = withRoundDigit / 10L;
         }

         long divideFactor = powerOfTenTable[adjustedScaleDown];
         long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
         if (roundDigit < 5L) {
            result0 = fast2Scaled + fast3 % divideFactor * multiplyFactor;
            result1 = fast3 / divideFactor + fast4 % divideFactor * multiplyFactor;
            result2 = fast4 / divideFactor;
         } else {
            long r0 = fast2Scaled + fast3 % divideFactor * multiplyFactor + 1L;
            result0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
            long r1 = fast3 / divideFactor + fast4 % divideFactor * multiplyFactor + r0 / MULTIPLER_LONGWORD_DECIMAL;
            result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
            result2 = fast4 / divideFactor + r1 / MULTIPLER_LONGWORD_DECIMAL;
         }

         result3 = 0L;
         result4 = 0L;
      }

      if (result4 == 0L && result3 == 0L) {
         if (result0 == 0L && result1 == 0L && result2 == 0L) {
            fastResult.fastReset();
         }

         fastResult.fastSignum = fastSignum;
         fastResult.fast0 = result0;
         fastResult.fast1 = result1;
         fastResult.fast2 = result2;
         return result2 <= MAX_HIGHWORD_DECIMAL;
      } else {
         throw new RuntimeException("Unexpected overflow into result3 or result4");
      }
   }

   public static boolean fastRoundFractionalHalfEven(int fastSignum, long fast0, long fast1, long fast2, int scaleDown, FastHiveDecimal fastResult) {
      if (scaleDown >= 1 && scaleDown <= 38) {
         if (scaleDown == 38) {
            long roundDivisor = powerOfTenTable[5];
            long withRoundDigit = fast2 / roundDivisor;
            long roundDigit = withRoundDigit % 10L;
            long fast2Scaled = withRoundDigit / 10L;
            boolean shouldRound;
            if (roundDigit > 5L) {
               shouldRound = true;
            } else if (roundDigit == 5L) {
               boolean exactlyOneHalf = fast2Scaled == 0L && fast1 == 0L && fast0 == 0L;
               if (exactlyOneHalf) {
                  shouldRound = false;
               } else {
                  shouldRound = true;
               }
            } else {
               shouldRound = false;
            }

            if (!shouldRound) {
               fastResult.fastReset();
            } else {
               fastResult.fastSet(fastSignum, 1L, 0L, 0L, 1, 0);
            }

            return true;
         } else {
            boolean isRoundPortionHalfEven = isRoundPortionHalfEven(fast0, fast1, fast2, scaleDown);
            doFastScaleDown(fast0, fast1, fast2, scaleDown, fastResult);
            if (isRoundPortionHalfEven) {
               long r0 = fastResult.fast0 + 1L;
               fastResult.fast0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
               long r1 = fastResult.fast1 + r0 / MULTIPLER_LONGWORD_DECIMAL;
               fastResult.fast1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
               fastResult.fast2 += r1 / MULTIPLER_LONGWORD_DECIMAL;
            }

            if (fastResult.fast0 == 0L && fastResult.fast1 == 0L && fastResult.fast2 == 0L) {
               fastResult.fastSignum = 0;
               fastResult.fastIntegerDigitCount = 0;
               fastResult.fastScale = 0;
            } else {
               fastResult.fastSignum = fastSignum;
            }

            return fastResult.fast2 <= MAX_HIGHWORD_DECIMAL;
         }
      } else {
         throw new IllegalArgumentException("Expecting scaleDown > 0 and scaleDown < 38 (scaleDown " + scaleDown + ")");
      }
   }

   public static void doFastScaleDown(FastHiveDecimal fastDec, int scaleDown, FastHiveDecimal fastResult) {
      doFastScaleDown(fastDec.fast0, fastDec.fast1, fastDec.fast2, scaleDown, fastResult);
   }

   public static void doFastScaleDown(long fast0, long fast1, long fast2, int scaleDown, FastHiveDecimal fastResult) {
      long result0;
      long result1;
      long result2;
      if (scaleDown < 16) {
         long divideFactor = powerOfTenTable[scaleDown];
         long multiplyFactor = powerOfTenTable[16 - scaleDown];
         result0 = fast0 / divideFactor + fast1 % divideFactor * multiplyFactor;
         result1 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
         result2 = fast2 / divideFactor;
      } else if (scaleDown < 32) {
         int adjustedScaleDown = scaleDown - 16;
         long divideFactor = powerOfTenTable[adjustedScaleDown];
         long multiplyFactor = powerOfTenTable[16 - adjustedScaleDown];
         result0 = fast1 / divideFactor + fast2 % divideFactor * multiplyFactor;
         result1 = fast2 / divideFactor;
         result2 = 0L;
      } else {
         int adjustedScaleDown = scaleDown - 32;
         result0 = fast2 / powerOfTenTable[adjustedScaleDown];
         result1 = 0L;
         result2 = 0L;
      }

      if (result0 == 0L && result1 == 0L && result2 == 0L) {
         fastResult.fastSignum = 0;
      }

      fastResult.fast0 = result0;
      fastResult.fast1 = result1;
      fastResult.fast2 = result2;
   }

   public static boolean fastScaleUp(FastHiveDecimal fastDec, int scaleUp, FastHiveDecimal fastResult) {
      return fastScaleUp(fastDec.fast0, fastDec.fast1, fastDec.fast2, scaleUp, fastResult);
   }

   public static boolean fastScaleUp(long fast0, long fast1, long fast2, int scaleUp, FastHiveDecimal fastResult) {
      if (scaleUp >= 1 && scaleUp < 38) {
         long result0;
         long result1;
         long result2;
         if (scaleUp < 6) {
            long overflowFactor = powerOfTenTable[6 - scaleUp];
            if (fast2 / overflowFactor != 0L) {
               return false;
            }

            long divideFactor = powerOfTenTable[16 - scaleUp];
            long multiplyFactor = powerOfTenTable[scaleUp];
            result2 = fast2 * multiplyFactor + fast1 / divideFactor;
            result1 = fast1 % divideFactor * multiplyFactor + fast0 / divideFactor;
            result0 = fast0 % divideFactor * multiplyFactor;
         } else if (scaleUp < 22) {
            if (fast2 != 0L) {
               return false;
            }

            int adjustedScaleUp = scaleUp - 6;
            int middleDigits = 16 - adjustedScaleUp;
            long overflowFactor = powerOfTenTable[middleDigits];
            if (fast1 / overflowFactor != 0L) {
               return false;
            }

            if (middleDigits < 6) {
               int highWordMoreDigits = 6 - middleDigits;
               long multiplyFactor = powerOfTenTable[highWordMoreDigits];
               long divideFactor = powerOfTenTable[16 - highWordMoreDigits];
               result2 = fast1 * multiplyFactor + fast0 / divideFactor;
               result1 = fast0 % divideFactor * multiplyFactor;
               result0 = 0L;
            } else if (middleDigits == 6) {
               result2 = fast1;
               result1 = fast0;
               result0 = 0L;
            } else {
               int keepMiddleDigits = middleDigits - 6;
               long divideFactor = powerOfTenTable[keepMiddleDigits];
               long multiplyFactor = powerOfTenTable[16 - keepMiddleDigits];
               result2 = fast1 / divideFactor;
               result1 = fast1 % divideFactor * multiplyFactor + fast0 / divideFactor;
               result0 = fast0 % divideFactor * multiplyFactor;
            }
         } else {
            if (fast2 != 0L || fast1 != 0L) {
               return false;
            }

            int adjustedScaleUp = scaleUp - 6 - 16;
            int lowerDigits = 16 - adjustedScaleUp;
            long overflowFactor = powerOfTenTable[lowerDigits];
            if (fast0 / overflowFactor != 0L) {
               return false;
            }

            if (lowerDigits < 6) {
               int highWordMoreDigits = 6 - lowerDigits;
               long multiplyFactor = powerOfTenTable[highWordMoreDigits];
               long var10000 = powerOfTenTable[16 - highWordMoreDigits];
               result2 = fast0 * multiplyFactor;
               result1 = 0L;
               result0 = 0L;
            } else if (lowerDigits == 6) {
               result2 = fast0;
               result1 = 0L;
               result0 = 0L;
            } else {
               int keepLowerDigits = lowerDigits - 6;
               long keepLowerDivideFactor = powerOfTenTable[keepLowerDigits];
               long keepLowerMultiplyFactor = powerOfTenTable[16 - keepLowerDigits];
               result2 = fast0 / keepLowerDivideFactor;
               result1 = fast0 % keepLowerDivideFactor * keepLowerMultiplyFactor;
               result0 = 0L;
            }
         }

         if (result0 == 0L && result1 == 0L && result2 == 0L) {
            fastResult.fastSignum = 0;
         }

         fastResult.fast0 = result0;
         fastResult.fast1 = result1;
         fastResult.fast2 = result2;
         return true;
      } else {
         throw new IllegalArgumentException("Expecting scaleUp > 0 and scaleUp < 38");
      }
   }

   public static int fastLongWordTrailingZeroCount(long longWord) {
      if (longWord == 0L) {
         return 16;
      } else {
         long factor = 10L;

         for(int i = 0; i < 16; ++i) {
            if (longWord % factor != 0L) {
               return i;
            }

            factor *= 10L;
         }

         return 0;
      }
   }

   public static int fastHighWordTrailingZeroCount(long longWord) {
      if (longWord == 0L) {
         return 6;
      } else {
         long factor = 10L;

         for(int i = 0; i < 6; ++i) {
            if (longWord % factor != 0L) {
               return i;
            }

            factor *= 10L;
         }

         return 0;
      }
   }

   public static int fastLongWordPrecision(long longWord) {
      if (longWord == 0L) {
         return 0;
      } else if (longWord > 99999999L) {
         if (longWord > 999999999999L) {
            if (longWord > 99999999999999L) {
               return longWord > 999999999999999L ? 16 : 15;
            } else {
               return longWord > 9999999999999L ? 14 : 13;
            }
         } else if (longWord > 9999999999L) {
            return longWord > 99999999999L ? 12 : 11;
         } else {
            return longWord > 999999999L ? 10 : 9;
         }
      } else if (longWord > 9999L) {
         if (longWord > 999999L) {
            return longWord > 9999999L ? 8 : 7;
         } else {
            return longWord > 99999L ? 6 : 5;
         }
      } else if (longWord > 99L) {
         return longWord > 999L ? 4 : 3;
      } else {
         return longWord > 9L ? 2 : 1;
      }
   }

   public static int fastHighWordPrecision(long longWord) {
      if (longWord == 0L) {
         return 0;
      } else if (longWord > 999L) {
         if (longWord > 9999L) {
            return longWord > 99999L ? 6 : 5;
         } else {
            return 4;
         }
      } else if (longWord > 99L) {
         return 3;
      } else {
         return longWord > 9L ? 2 : 1;
      }
   }

   public static int fastSqlPrecision(FastHiveDecimal fastDec) {
      return fastSqlPrecision(fastDec.fastSignum, fastDec.fast0, fastDec.fast1, fastDec.fast2, fastDec.fastIntegerDigitCount, fastDec.fastScale);
   }

   public static int fastSqlPrecision(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastSignum == 0) {
         return 1;
      } else {
         int rawPrecision = fastRawPrecision(fastSignum, fast0, fast1, fast2);
         return rawPrecision < fastScale ? fastScale : rawPrecision;
      }
   }

   public static int fastRawPrecision(FastHiveDecimal fastDec) {
      return fastRawPrecision(fastDec.fastSignum, fastDec.fast0, fastDec.fast1, fastDec.fast2);
   }

   public static int fastRawPrecision(int fastSignum, long fast0, long fast1, long fast2) {
      if (fastSignum == 0) {
         return 0;
      } else {
         int precision;
         if (fast2 != 0L) {
            precision = 32 + fastHighWordPrecision(fast2);
         } else if (fast1 != 0L) {
            precision = 16 + fastLongWordPrecision(fast1);
         } else {
            precision = fastLongWordPrecision(fast0);
         }

         return precision;
      }
   }

   public static boolean isAllZeroesBelow(int fastSignum, long fast0, long fast1, long fast2, int power) {
      if (power >= 0 && power <= 38) {
         if (fastSignum == 0) {
            return true;
         } else if (power >= 32) {
            if (fast0 == 0L && fast1 == 0L) {
               int adjustedPower = power - 32;
               if (adjustedPower == 0) {
                  return true;
               } else {
                  long remainder = fast2 % powerOfTenTable[adjustedPower];
                  return remainder == 0L;
               }
            } else {
               return false;
            }
         } else if (power >= 16) {
            if (fast0 != 0L) {
               return false;
            } else {
               int adjustedPower = power - 16;
               if (adjustedPower == 0) {
                  return true;
               } else {
                  long remainder = fast1 % powerOfTenTable[adjustedPower];
                  return remainder == 0L;
               }
            }
         } else if (power == 0) {
            return true;
         } else {
            long remainder = fast0 % powerOfTenTable[power];
            return remainder == 0L;
         }
      } else {
         throw new IllegalArgumentException("Expecting power >= 0 and power <= 38");
      }
   }

   public static boolean fastExceedsPrecision(long fast0, long fast1, long fast2, int precision) {
      if (precision <= 0) {
         return true;
      } else if (precision >= 38) {
         return false;
      } else {
         int precisionLessOne = precision - 1;
         int wordNum = precisionLessOne / 16;
         int digitInWord = precisionLessOne % 16;
         long overLimitInWord = powerOfTenTable[digitInWord + 1] - 1L;
         if (wordNum == 0) {
            if (digitInWord < 15 && fast0 > overLimitInWord) {
               return true;
            } else {
               return fast1 != 0L || fast2 != 0L;
            }
         } else if (wordNum == 1) {
            if (digitInWord < 15 && fast1 > overLimitInWord) {
               return true;
            } else {
               return fast2 != 0L;
            }
         } else {
            return fast2 > overLimitInWord;
         }
      }
   }

   public static int fastTrailingDecimalZeroCount(long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      if (fastScale >= 0 && fastScale <= 38) {
         if (fastScale == 0) {
            return 0;
         } else {
            int lowerLongwordDigits = Math.min(fastScale, 16);
            if (lowerLongwordDigits < 16 || fast0 != 0L) {
               long factor = 10L;

               for(int i = 0; i < lowerLongwordDigits; ++i) {
                  if (fast0 % factor != 0L) {
                     return i;
                  }

                  factor *= 10L;
               }

               if (lowerLongwordDigits < 16) {
                  return fastScale;
               }
            }

            if (fastScale == 16) {
               return fastScale;
            } else {
               int middleLongwordDigits = Math.min(fastScale - 16, 16);
               if (middleLongwordDigits < 16 || fast1 != 0L) {
                  long factor = 10L;

                  for(int i = 0; i < middleLongwordDigits; ++i) {
                     if (fast1 % factor != 0L) {
                        return 16 + i;
                     }

                     factor *= 10L;
                  }

                  if (middleLongwordDigits < 16) {
                     return fastScale;
                  }
               }

               if (fastScale == 32) {
                  return fastScale;
               } else {
                  int highLongwordDigits = fastScale - 32;
                  if (highLongwordDigits < 6 || fast2 != 0L) {
                     long factor = 10L;

                     for(int i = 0; i < highLongwordDigits; ++i) {
                        if (fast2 % factor != 0L) {
                           return 32 + i;
                        }

                        factor *= 10L;
                     }
                  }

                  return fastScale;
               }
            }
         }
      } else {
         throw new IllegalArgumentException("Expecting scale >= 0 and scale <= 38");
      }
   }

   public static FastHiveDecimal.FastCheckPrecisionScaleStatus fastCheckPrecisionScale(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int maxPrecision, int maxScale) {
      if (fastSignum == 0) {
         return FastHiveDecimal.FastCheckPrecisionScaleStatus.NO_CHANGE;
      } else {
         int maxIntegerDigitCount = maxPrecision - maxScale;
         if (fastIntegerDigitCount > maxIntegerDigitCount) {
            return FastHiveDecimal.FastCheckPrecisionScaleStatus.OVERFLOW;
         } else {
            return fastScale > maxScale ? FastHiveDecimal.FastCheckPrecisionScaleStatus.UPDATE_SCALE_DOWN : FastHiveDecimal.FastCheckPrecisionScaleStatus.NO_CHANGE;
         }
      }
   }

   public static boolean fastUpdatePrecisionScale(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int maxPrecision, int maxScale, FastHiveDecimal.FastCheckPrecisionScaleStatus status, FastHiveDecimal fastResult) {
      switch (status) {
         case UPDATE_SCALE_DOWN:
            fastResult.fastSignum = fastSignum;
            if (!fastRoundFractionalHalfUp(fastSignum, fast0, fast1, fast2, fastScale - maxScale, fastResult)) {
               return false;
            } else {
               fastResult.fastScale = maxScale;
               fastResult.fastIntegerDigitCount = Math.max(0, fastRawPrecision(fastResult) - fastResult.fastScale);
               int maxIntegerDigitCount = maxPrecision - maxScale;
               if (fastResult.fastIntegerDigitCount > maxIntegerDigitCount) {
                  return false;
               }

               int trailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
               if (trailingZeroCount > 0) {
                  doFastScaleDown(fastResult, trailingZeroCount, fastResult);
                  fastResult.fastScale -= trailingZeroCount;
               }

               return true;
            }
         default:
            throw new RuntimeException("Unexpected fast check precision scale status " + status);
      }
   }

   public static boolean doAddSameScaleSameSign(FastHiveDecimal fastLeft, FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return doAddSameScaleSameSign(fastLeft.fastSignum, fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastResult);
   }

   public static boolean doAddSameScaleSameSign(int resultSignum, long left0, long left1, long left2, long right0, long right1, long right2, FastHiveDecimal fastResult) {
      long r0 = left0 + right0;
      long result0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
      long r1 = left1 + right1 + r0 / MULTIPLER_LONGWORD_DECIMAL;
      long result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
      long result2 = left2 + right2 + r1 / MULTIPLER_LONGWORD_DECIMAL;
      if (result0 == 0L && result1 == 0L && result2 == 0L) {
         fastResult.fastReset();
      } else {
         fastResult.fastSignum = resultSignum;
         fastResult.fast0 = result0;
         fastResult.fast1 = result1;
         fastResult.fast2 = result2;
      }

      return result2 <= MAX_HIGHWORD_DECIMAL;
   }

   public static boolean doSubtractSameScaleNoUnderflow(int resultSignum, FastHiveDecimal fastLeft, FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return doSubtractSameScaleNoUnderflow(resultSignum, fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastResult);
   }

   public static boolean doSubtractSameScaleNoUnderflow(int resultSignum, long left0, long left1, long left2, long right0, long right1, long right2, FastHiveDecimal fastResult) {
      long r0 = left0 - right0;
      long result0;
      long r1;
      if (r0 < 0L) {
         result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
         r1 = left1 - right1 - 1L;
      } else {
         result0 = r0;
         r1 = left1 - right1;
      }

      long result1;
      long result2;
      if (r1 < 0L) {
         result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
         result2 = left2 - right2 - 1L;
      } else {
         result1 = r1;
         result2 = left2 - right2;
      }

      if (result2 < 0L) {
         return false;
      } else {
         if (result0 == 0L && result1 == 0L && result2 == 0L) {
            fastResult.fastReset();
         } else {
            fastResult.fastSignum = resultSignum;
            fastResult.fast0 = result0;
            fastResult.fast1 = result1;
            fastResult.fast2 = result2;
         }

         return true;
      }
   }

   public static boolean doSubtractSameScaleNoUnderflow(long left0, long left1, long left2, long right0, long right1, long right2, long[] result) {
      long r0 = left0 - right0;
      long result0;
      long r1;
      if (r0 < 0L) {
         result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
         r1 = left1 - right1 - 1L;
      } else {
         result0 = r0;
         r1 = left1 - right1;
      }

      long result1;
      long result2;
      if (r1 < 0L) {
         result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
         result2 = left2 - right2 - 1L;
      } else {
         result1 = r1;
         result2 = left2 - right2;
      }

      if (result2 < 0L) {
         return false;
      } else {
         result[0] = result0;
         result[1] = result1;
         result[2] = result2;
         return true;
      }
   }

   private static boolean doAddSameScale(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int scale, FastHiveDecimal fastResult) {
      if (leftSignum == rightSignum) {
         if (!doAddSameScaleSameSign(leftSignum, leftFast0, leftFast1, leftFast2, rightFast0, rightFast1, rightFast2, fastResult)) {
            if (scale <= 0) {
               return false;
            }

            if (!fastRoundFractionalHalfUp(fastResult.fastSignum, fastResult.fast0, fastResult.fast1, fastResult.fast2, 1, fastResult)) {
               return false;
            }

            --scale;
         }

         fastResult.fastScale = scale;
      } else {
         int compareTo = fastCompareTo(1, leftFast0, leftFast1, leftFast2, 0, 1, rightFast0, rightFast1, rightFast2, 0);
         if (compareTo == 0) {
            fastResult.fastReset();
            return true;
         }

         if (compareTo == 1) {
            if (!doSubtractSameScaleNoUnderflow(leftSignum, leftFast0, leftFast1, leftFast2, rightFast0, rightFast1, rightFast2, fastResult)) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else if (!doSubtractSameScaleNoUnderflow(rightSignum, rightFast0, rightFast1, rightFast2, leftFast0, leftFast1, leftFast2, fastResult)) {
            throw new RuntimeException("Unexpected underflow");
         }

         fastResult.fastScale = scale;
      }

      if (fastResult.fastSignum != 0) {
         int precision = fastRawPrecision(fastResult);
         fastResult.fastIntegerDigitCount = Math.max(0, precision - fastResult.fastScale);
      }

      int resultTrailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
      if (resultTrailingZeroCount > 0) {
         doFastScaleDown(fastResult, resultTrailingZeroCount, fastResult);
         if (fastResult.fastSignum == 0) {
            fastResult.fastScale = 0;
         } else {
            fastResult.fastScale -= resultTrailingZeroCount;
         }
      }

      return true;
   }

   private static boolean doFinishAddSubtractDifferentScale(long result0, long result1, long result2, long result3, long result4, int resultScale, FastHiveDecimal fastResult) {
      int precision;
      if (result4 != 0L) {
         precision = 64 + fastLongWordPrecision(result4);
      } else if (result3 != 0L) {
         precision = 48 + fastLongWordPrecision(result3);
      } else if (result2 != 0L) {
         precision = 32 + fastLongWordPrecision(result2);
      } else if (result1 != 0L) {
         precision = 16 + fastLongWordPrecision(result1);
      } else {
         precision = fastLongWordPrecision(result0);
      }

      if (precision > 38) {
         int scaleDown = precision - 38;
         resultScale -= scaleDown;
         if (resultScale < 0) {
            return false;
         }

         if (!fastRoundFractionalHalfUp5Words(1, result0, result1, result2, result3, result4, scaleDown, fastResult)) {
            if (resultScale <= 0) {
               return false;
            }

            if (!fastRoundFractionalHalfUp(fastResult.fastSignum, fastResult.fast0, fastResult.fast1, fastResult.fast2, 1, fastResult)) {
               throw new RuntimeException("Unexpected overflow");
            }

            if (fastResult.fastSignum == 0) {
               return true;
            }

            --resultScale;
         }

         precision = fastRawPrecision(1, fastResult.fast0, fastResult.fast1, fastResult.fast2);
         result0 = fastResult.fast0;
         result1 = fastResult.fast1;
         result2 = fastResult.fast2;
      }

      fastResult.fastSignum = 1;
      fastResult.fast0 = result0;
      fastResult.fast1 = result1;
      fastResult.fast2 = result2;
      fastResult.fastIntegerDigitCount = Math.max(0, precision - resultScale);
      fastResult.fastScale = resultScale;
      int resultTrailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
      if (resultTrailingZeroCount > 0) {
         doFastScaleDown(fastResult, resultTrailingZeroCount, fastResult);
         if (fastResult.fastSignum == 0) {
            fastResult.fastScale = 0;
         } else {
            fastResult.fastScale -= resultTrailingZeroCount;
         }
      }

      return true;
   }

   private static boolean fastSubtractDifferentScale(long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      long result0 = 0L;
      long result1 = 0L;
      long result2 = 0L;
      long result3 = 0L;
      long result4 = 0L;
      int resultScale;
      if (leftScale > rightScale) {
         int diffScale = leftScale - rightScale;
         resultScale = leftScale;
         if (diffScale < 16) {
            long divideFactor = powerOfTenTable[16 - diffScale];
            long multiplyFactor = powerOfTenTable[diffScale];
            long r0 = leftFast0 - rightFast0 % divideFactor * multiplyFactor;
            long r1;
            if (r0 < 0L) {
               result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
               r1 = leftFast1 - rightFast0 / divideFactor - rightFast1 % divideFactor * multiplyFactor - 1L;
            } else {
               result0 = r0;
               r1 = leftFast1 - rightFast0 / divideFactor - rightFast1 % divideFactor * multiplyFactor;
            }

            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast2 - rightFast1 / divideFactor - rightFast2 % divideFactor * multiplyFactor - 1L;
            } else {
               result1 = r1;
               r2 = leftFast2 - rightFast1 / divideFactor - rightFast2 % divideFactor * multiplyFactor;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = -(rightFast2 / divideFactor) - 1L;
            } else {
               result2 = r2;
               r3 = -(rightFast2 / divideFactor);
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = -1L;
            } else {
               result3 = r3;
               r4 = 0L;
            }

            if (r4 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else if (diffScale == 16) {
            result0 = leftFast0;
            long r1 = leftFast1 - rightFast0;
            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast2 - rightFast1 - 1L;
            } else {
               result1 = r1;
               r2 = leftFast2 - rightFast1;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = -rightFast2 - 1L;
            } else {
               result2 = r2;
               r3 = -rightFast2;
            }

            if (r3 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else if (diffScale < 32) {
            long divideFactor = powerOfTenTable[32 - diffScale];
            long multiplyFactor = powerOfTenTable[diffScale - 16];
            result0 = leftFast0;
            long r1 = leftFast1 - rightFast0 % divideFactor * multiplyFactor;
            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast2 - rightFast0 / divideFactor - rightFast1 % divideFactor * multiplyFactor - 1L;
            } else {
               result1 = r1;
               r2 = leftFast2 - rightFast0 / divideFactor - rightFast1 % divideFactor * multiplyFactor;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = -rightFast1 / divideFactor - rightFast2 % divideFactor * multiplyFactor - 1L;
            } else {
               result2 = r2;
               r3 = -rightFast1 / divideFactor - rightFast2 % divideFactor * multiplyFactor;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = -rightFast2 / divideFactor - 1L;
            } else {
               result3 = r3;
               r4 = -rightFast2 / divideFactor;
            }

            long r5;
            if (r4 < 0L) {
               result4 = r4 + MULTIPLER_LONGWORD_DECIMAL;
               r5 = -1L;
            } else {
               result4 = r4;
               r5 = 0L;
            }

            if (r5 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else if (diffScale == 32) {
            result0 = leftFast0;
            result1 = leftFast1;
            long r2 = leftFast2 - rightFast0;
            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = -rightFast1 - 1L;
            } else {
               result2 = r2;
               r3 = -rightFast1;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = -rightFast2 - 1L;
            } else {
               result3 = r3;
               r4 = -rightFast2;
            }

            long r5;
            if (r4 < 0L) {
               result4 = r4 + MULTIPLER_LONGWORD_DECIMAL;
               r5 = -1L;
            } else {
               result4 = r4;
               r5 = 0L;
            }

            if (r5 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else {
            long divideFactor = powerOfTenTable[48 - diffScale];
            long multiplyFactor = powerOfTenTable[diffScale - 32];
            result0 = leftFast0;
            result1 = leftFast1;
            long r2 = leftFast2 - rightFast0 % divideFactor * multiplyFactor;
            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = -(rightFast0 / divideFactor) - rightFast1 % divideFactor * multiplyFactor - 1L;
            } else {
               result2 = r2;
               r3 = -(rightFast0 / divideFactor) - rightFast1 % divideFactor * multiplyFactor;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = -(rightFast1 / divideFactor) - rightFast2 % divideFactor * multiplyFactor - 1L;
            } else {
               result3 = r3;
               r4 = -(rightFast1 / divideFactor) - rightFast2 % divideFactor * multiplyFactor;
            }

            long r5;
            if (r4 < 0L) {
               result4 = r4 + MULTIPLER_LONGWORD_DECIMAL;
               r5 = -(rightFast2 / divideFactor) - 1L;
            } else {
               result4 = r4;
               r5 = -(rightFast2 / divideFactor);
            }

            if (r5 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         }
      } else {
         int diffScale = rightScale - leftScale;
         resultScale = rightScale;
         if (diffScale < 16) {
            long divideFactor = powerOfTenTable[16 - diffScale];
            long multiplyFactor = powerOfTenTable[diffScale];
            long r0 = leftFast0 % divideFactor * multiplyFactor - rightFast0;
            long r1;
            if (r0 < 0L) {
               result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
               r1 = leftFast0 / divideFactor + leftFast1 % divideFactor * multiplyFactor - rightFast1 - 1L;
            } else {
               result0 = r0;
               r1 = leftFast0 / divideFactor + leftFast1 % divideFactor * multiplyFactor - rightFast1;
            }

            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor - rightFast2 - 1L;
            } else {
               result1 = r1;
               r2 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor - rightFast2;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = leftFast2 / divideFactor - 1L;
            } else {
               result2 = r2;
               r3 = leftFast2 / divideFactor;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = -1L;
            } else {
               result3 = r3;
               r4 = 0L;
            }

            if (r4 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else if (diffScale == 16) {
            long r0 = -rightFast0;
            long r1;
            if (r0 < 0L) {
               result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
               r1 = leftFast0 - rightFast1 - 1L;
            } else {
               result0 = r0;
               r1 = leftFast0 - rightFast1;
            }

            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast1 - rightFast2 - 1L;
            } else {
               result1 = r1;
               r2 = leftFast1 - rightFast2;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = leftFast2 - 1L;
            } else {
               result2 = r2;
               r3 = leftFast2;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = -1L;
            } else {
               result3 = r3;
               r4 = 0L;
            }

            if (r4 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else if (diffScale < 32) {
            long divideFactor = powerOfTenTable[32 - diffScale];
            long multiplyFactor = powerOfTenTable[diffScale - 16];
            long r0 = -rightFast0;
            long r1;
            if (r0 < 0L) {
               result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
               r1 = leftFast0 % divideFactor * multiplyFactor - rightFast1 - 1L;
            } else {
               result0 = r0;
               r1 = leftFast0 % divideFactor * multiplyFactor - rightFast1;
            }

            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast0 / divideFactor + leftFast1 % divideFactor * multiplyFactor - rightFast2 - 1L;
            } else {
               result1 = r1;
               r2 = leftFast0 / divideFactor + leftFast1 % divideFactor * multiplyFactor - rightFast2;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor - 1L;
            } else {
               result2 = r2;
               r3 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = leftFast2 / divideFactor - 1L;
            } else {
               result3 = r3;
               r4 = leftFast2 / divideFactor;
            }

            if (r4 < 0L) {
               result4 = r4 + MULTIPLER_LONGWORD_DECIMAL;
            } else {
               result4 = r4;
            }
         } else if (diffScale == 32) {
            long r0 = -rightFast0;
            long r1;
            if (r0 < 0L) {
               result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
               r1 = -rightFast1 - 1L;
            } else {
               result0 = r0;
               r1 = -rightFast1;
            }

            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast0 - rightFast2 - 1L;
            } else {
               result1 = r1;
               r2 = leftFast0 - rightFast2;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = leftFast1 - 1L;
            } else {
               result2 = r2;
               r3 = leftFast1;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = leftFast2 - 1L;
            } else {
               result3 = r3;
               r4 = leftFast2;
            }

            long r5;
            if (r4 < 0L) {
               result4 = r4 + MULTIPLER_LONGWORD_DECIMAL;
               r5 = -1L;
            } else {
               result4 = r4;
               r5 = 0L;
            }

            if (r5 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         } else {
            long divideFactor = powerOfTenTable[48 - diffScale];
            long multiplyFactor = powerOfTenTable[diffScale - 32];
            long r0 = -rightFast0;
            long r1;
            if (r0 < 0L) {
               result0 = r0 + MULTIPLER_LONGWORD_DECIMAL;
               r1 = -rightFast1 - 1L;
            } else {
               result0 = r0;
               r1 = -rightFast1;
            }

            long r2;
            if (r1 < 0L) {
               result1 = r1 + MULTIPLER_LONGWORD_DECIMAL;
               r2 = leftFast0 % divideFactor * multiplyFactor - rightFast2 - 1L;
            } else {
               result1 = r1;
               r2 = leftFast0 % divideFactor * multiplyFactor - rightFast2;
            }

            long r3;
            if (r2 < 0L) {
               result2 = r2 + MULTIPLER_LONGWORD_DECIMAL;
               r3 = leftFast0 / divideFactor + leftFast1 % divideFactor * multiplyFactor - 1L;
            } else {
               result2 = r2;
               r3 = leftFast0 / divideFactor + leftFast1 % divideFactor * multiplyFactor;
            }

            long r4;
            if (r3 < 0L) {
               result3 = r3 + MULTIPLER_LONGWORD_DECIMAL;
               r4 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor - 1L;
            } else {
               result3 = r3;
               r4 = leftFast1 / divideFactor + leftFast2 % divideFactor * multiplyFactor;
            }

            long r5;
            if (r4 < 0L) {
               result4 = r4 + MULTIPLER_LONGWORD_DECIMAL;
               r5 = leftFast2 / divideFactor - 1L;
            } else {
               result4 = r4;
               r5 = leftFast2 / divideFactor;
            }

            if (r5 != 0L) {
               throw new RuntimeException("Unexpected underflow");
            }
         }
      }

      return doFinishAddSubtractDifferentScale(result0, result1, result2, result3, result4, resultScale, fastResult);
   }

   private static boolean fastAddDifferentScale(long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      long result0;
      long result1;
      long result2;
      long shift0;
      long shift1;
      long shift2;
      int diffScale;
      int resultScale;
      if (leftScale > rightScale) {
         result0 = leftFast0;
         result1 = leftFast1;
         result2 = leftFast2;
         shift0 = rightFast0;
         shift1 = rightFast1;
         shift2 = rightFast2;
         diffScale = leftScale - rightScale;
         resultScale = leftScale;
      } else {
         result0 = rightFast0;
         result1 = rightFast1;
         result2 = rightFast2;
         shift0 = leftFast0;
         shift1 = leftFast1;
         shift2 = leftFast2;
         diffScale = rightScale - leftScale;
         resultScale = rightScale;
      }

      long result3 = 0L;
      long result4 = 0L;
      if (diffScale < 16) {
         long divideFactor = powerOfTenTable[16 - diffScale];
         long multiplyFactor = powerOfTenTable[diffScale];
         long r0 = result0 + shift0 % divideFactor * multiplyFactor;
         result0 = r0 % MULTIPLER_LONGWORD_DECIMAL;
         long r1 = result1 + shift0 / divideFactor + shift1 % divideFactor * multiplyFactor + r0 / MULTIPLER_LONGWORD_DECIMAL;
         result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
         long r2 = result2 + shift1 / divideFactor + shift2 % divideFactor * multiplyFactor + r1 / MULTIPLER_LONGWORD_DECIMAL;
         result2 = r2 % MULTIPLER_LONGWORD_DECIMAL;
         long r3 = shift2 / divideFactor + r2 / MULTIPLER_LONGWORD_DECIMAL;
         result3 = r3 % MULTIPLER_LONGWORD_DECIMAL;
      } else if (diffScale == 16) {
         long r1 = result1 + shift0;
         result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
         long r2 = result2 + shift1 + r1 / MULTIPLER_LONGWORD_DECIMAL;
         result2 = r2 % MULTIPLER_LONGWORD_DECIMAL;
         long r3 = shift2 + r2 / MULTIPLER_LONGWORD_DECIMAL;
         result3 = r3 % MULTIPLER_LONGWORD_DECIMAL;
         result4 = r3 / MULTIPLER_LONGWORD_DECIMAL;
      } else if (diffScale < 32) {
         long divideFactor = powerOfTenTable[32 - diffScale];
         long multiplyFactor = powerOfTenTable[diffScale - 16];
         long r1 = result1 + shift0 % divideFactor * multiplyFactor;
         result1 = r1 % MULTIPLER_LONGWORD_DECIMAL;
         long r2 = result2 + shift0 / divideFactor + shift1 % divideFactor * multiplyFactor + r1 / MULTIPLER_LONGWORD_DECIMAL;
         result2 = r2 % MULTIPLER_LONGWORD_DECIMAL;
         long r3 = shift1 / divideFactor + shift2 % divideFactor * multiplyFactor + r2 / MULTIPLER_LONGWORD_DECIMAL;
         result3 = r3 % MULTIPLER_LONGWORD_DECIMAL;
         long r4 = shift2 / divideFactor + r3 / MULTIPLER_LONGWORD_DECIMAL;
         result4 = r4 % MULTIPLER_LONGWORD_DECIMAL;
      } else if (diffScale == 32) {
         long r2 = result2 + shift0;
         result2 = r2 % MULTIPLER_LONGWORD_DECIMAL;
         long r3 = shift1 + r2 / MULTIPLER_LONGWORD_DECIMAL;
         result3 = r3 % MULTIPLER_LONGWORD_DECIMAL;
         long r4 = shift2 + r3 / MULTIPLER_LONGWORD_DECIMAL;
         result4 = r4 % MULTIPLER_LONGWORD_DECIMAL;
      } else {
         long divideFactor = powerOfTenTable[48 - diffScale];
         long multiplyFactor = powerOfTenTable[diffScale - 32];
         long r2 = result2 + shift0 % divideFactor * multiplyFactor;
         result2 = r2 % MULTIPLER_LONGWORD_DECIMAL;
         long r3 = shift0 / divideFactor + shift1 % divideFactor * multiplyFactor + r2 / MULTIPLER_LONGWORD_DECIMAL;
         result3 = r3 % MULTIPLER_LONGWORD_DECIMAL;
         long r4 = shift1 / divideFactor + shift2 % divideFactor * multiplyFactor + r3 / MULTIPLER_LONGWORD_DECIMAL;
         result4 = r4 % MULTIPLER_LONGWORD_DECIMAL;
         if (shift2 / divideFactor != 0L) {
            throw new RuntimeException("Unexpected overflow");
         }
      }

      return doFinishAddSubtractDifferentScale(result0, result1, result2, result3, result4, resultScale, fastResult);
   }

   private static boolean doAddDifferentScale(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      if (leftSignum == rightSignum) {
         if (!fastAddDifferentScale(leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale, fastResult)) {
            return false;
         }

         fastResult.fastSignum = leftSignum;
      } else {
         int compareTo = fastCompareTo(1, leftFast0, leftFast1, leftFast2, leftScale, 1, rightFast0, rightFast1, rightFast2, rightScale);
         if (compareTo == 0) {
            fastResult.fastSignum = 0;
            fastResult.fast0 = 0L;
            fastResult.fast1 = 0L;
            fastResult.fast2 = 0L;
            fastResult.fastScale = 0;
            return true;
         }

         if (compareTo == 1) {
            if (!fastSubtractDifferentScale(leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale, fastResult)) {
               throw new RuntimeException("Unexpected overflow");
            }

            fastResult.fastSignum = leftSignum;
         } else {
            if (!fastSubtractDifferentScale(rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale, fastResult)) {
               throw new RuntimeException("Unexpected overflow");
            }

            fastResult.fastSignum = rightSignum;
         }
      }

      int resultTrailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
      if (resultTrailingZeroCount > 0) {
         doFastScaleDown(fastResult, resultTrailingZeroCount, fastResult);
         if (fastResult.fastSignum == 0) {
            fastResult.fastScale = 0;
         } else {
            fastResult.fastScale -= resultTrailingZeroCount;
         }
      }

      return true;
   }

   public static boolean fastAdd(FastHiveDecimal fastLeft, FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return fastAdd(fastLeft.fastSignum, fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastLeft.fastIntegerDigitCount, fastLeft.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   public static boolean fastAdd(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      if (rightSignum == 0) {
         fastResult.fastSet(leftSignum, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale);
         return true;
      } else if (leftSignum == 0) {
         fastResult.fastSet(rightSignum, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale);
         return true;
      } else {
         return leftScale == rightScale ? doAddSameScale(leftSignum, leftFast0, leftFast1, leftFast2, rightSignum, rightFast0, rightFast1, rightFast2, leftScale, fastResult) : doAddDifferentScale(leftSignum, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale, rightSignum, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale, fastResult);
      }
   }

   public static boolean fastSubtract(FastHiveDecimal fastLeft, FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return fastSubtract(fastLeft.fastSignum, fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastLeft.fastIntegerDigitCount, fastLeft.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   public static boolean fastSubtract(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      if (rightSignum == 0) {
         fastResult.fastSet(leftSignum, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale);
         return true;
      } else {
         int flippedDecSignum = rightSignum == 1 ? -1 : 1;
         if (leftSignum == 0) {
            fastResult.fastSet(flippedDecSignum, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale);
            return true;
         } else {
            return leftScale == rightScale ? doAddSameScale(leftSignum, leftFast0, leftFast1, leftFast2, flippedDecSignum, rightFast0, rightFast1, rightFast2, leftScale, fastResult) : doAddDifferentScale(leftSignum, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale, flippedDecSignum, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale, fastResult);
         }
      }
   }

   private static boolean doMultiply(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      fastResult.fastSignum = leftSignum == rightSignum ? 1 : -1;
      int resultScale = leftScale + rightScale;
      if (leftScale == 0) {
         int leftTrailingZeroCount = fastTrailingDecimalZeroCount(leftFast0, leftFast1, leftFast2, 0, leftIntegerDigitCount);
         if (leftTrailingZeroCount > 0) {
            doFastScaleDown(leftFast0, leftFast1, leftFast2, leftTrailingZeroCount, fastResult);
            resultScale -= leftTrailingZeroCount;
            leftFast0 = fastResult.fast0;
            leftFast1 = fastResult.fast1;
            leftFast2 = fastResult.fast2;
         }
      }

      if (rightScale == 0) {
         int rightTrailingZeroCount = fastTrailingDecimalZeroCount(rightFast0, rightFast1, rightFast2, 0, rightIntegerDigitCount);
         if (rightTrailingZeroCount > 0) {
            doFastScaleDown(rightFast0, rightFast1, rightFast2, rightTrailingZeroCount, fastResult);
            resultScale -= rightTrailingZeroCount;
            rightFast0 = fastResult.fast0;
            rightFast1 = fastResult.fast1;
            rightFast2 = fastResult.fast2;
         }
      }

      boolean largeOverflow = !fastMultiply5x5HalfWords(leftFast0, leftFast1, leftFast2, rightFast0, rightFast1, rightFast2, fastResult);
      if (largeOverflow) {
         return false;
      } else if (fastResult.fastSignum == 0) {
         fastResult.fastScale = 0;
         return true;
      } else {
         if (resultScale < 0) {
            if (-resultScale >= 38) {
               return false;
            }

            if (!fastScaleUp(fastResult.fast0, fastResult.fast1, fastResult.fast2, -resultScale, fastResult)) {
               return false;
            }

            resultScale = 0;
         }

         int precision;
         if (fastResult.fast2 != 0L) {
            precision = 32 + fastLongWordPrecision(fastResult.fast2);
         } else if (fastResult.fast1 != 0L) {
            precision = 16 + fastLongWordPrecision(fastResult.fast1);
         } else {
            precision = fastLongWordPrecision(fastResult.fast0);
         }

         int integerDigitCount = Math.max(0, precision - resultScale);
         if (integerDigitCount > 38) {
            return false;
         } else {
            if (precision > 38 || resultScale > 38) {
               int maxScale = 38 - integerDigitCount;
               int scaleDown = resultScale - maxScale;
               if (!fastScaleDownNoRound(fastResult.fastSignum, fastResult.fast0, fastResult.fast1, fastResult.fast2, scaleDown, fastResult)) {
                  return false;
               }

               resultScale -= scaleDown;
            }

            fastResult.fastScale = resultScale;
            fastResult.fastIntegerDigitCount = integerDigitCount;
            if (fastResult.fastScale > 38) {
               return false;
            } else {
               int resultTrailingZeroCount = fastTrailingDecimalZeroCount(fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale);
               if (resultTrailingZeroCount > 0) {
                  doFastScaleDown(fastResult, resultTrailingZeroCount, fastResult);
                  if (fastResult.fastSignum == 0) {
                     fastResult.fastScale = 0;
                  } else {
                     fastResult.fastScale -= resultTrailingZeroCount;
                  }
               }

               return true;
            }
         }
      }
   }

   public static boolean fastMultiply5x5HalfWords(FastHiveDecimal fastLeft, FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return fastMultiply5x5HalfWords(fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastResult);
   }

   public static boolean fastMultiply5x5HalfWords(long left0, long left1, long left2, long right0, long right1, long right2, FastHiveDecimal fastResult) {
      long halfRight0 = right0 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight1 = right0 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight2 = right1 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight3 = right1 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight4 = right2 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft0 = left0 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft1 = left0 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft2 = left1 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft3 = left1 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft4 = left2 % (long)MULTIPLER_INTWORD_DECIMAL;
      long product = halfRight0 * halfLeft0;
      int z0 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft1 + halfRight1 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      int z1 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft2 + halfRight1 * halfLeft1 + halfRight2 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      int z2 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft3 + halfRight1 * halfLeft2 + halfRight2 * halfLeft1 + halfRight3 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      int z3 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft4 + halfRight1 * halfLeft3 + halfRight2 * halfLeft2 + halfRight3 * halfLeft1 + halfRight4 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      if ((halfRight4 == 0L || halfLeft4 == 0L && halfLeft3 == 0L && halfLeft2 == 0L && halfLeft1 == 0L) && (halfRight3 == 0L || halfLeft4 == 0L && halfLeft3 == 0L && halfLeft2 == 0L) && (halfRight2 == 0L || halfLeft4 == 0L && halfLeft3 == 0L) && (halfRight1 == 0L || halfLeft4 == 0L)) {
         long result0 = (long)z1 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z0;
         long result1 = (long)z3 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z2;
         if (result0 == 0L && result1 == 0L && product == 0L) {
            fastResult.fastSignum = 0;
         }

         fastResult.fast0 = result0;
         fastResult.fast1 = result1;
         fastResult.fast2 = product;
         return true;
      } else {
         return false;
      }
   }

   public static boolean fastMultiplyFullInternal(FastHiveDecimal fastLeft, FastHiveDecimal fastRight, long[] result) {
      return fastMultiplyFullInternal(fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastRight.fast0, fastRight.fast1, fastRight.fast2, result);
   }

   public static boolean fastMultiply5x5HalfWords(long left0, long left1, long left2, long right0, long right1, long right2, long[] result) {
      long halfRight0 = right0 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight1 = right0 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight2 = right1 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight3 = right1 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfRight4 = right2 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft0 = left0 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft1 = left0 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft2 = left1 % (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft3 = left1 / (long)MULTIPLER_INTWORD_DECIMAL;
      long halfLeft4 = left2 % (long)MULTIPLER_INTWORD_DECIMAL;
      long product = halfRight0 * halfLeft0;
      int z0 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft1 + halfRight1 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      int z1 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft2 + halfRight1 * halfLeft1 + halfRight2 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      int z2 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft3 + halfRight1 * halfLeft2 + halfRight2 * halfLeft1 + halfRight3 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      int z3 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
      product = halfRight0 * halfLeft4 + halfRight1 * halfLeft3 + halfRight2 * halfLeft2 + halfRight3 * halfLeft1 + halfRight4 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
      if ((halfRight4 == 0L || halfLeft4 == 0L && halfLeft3 == 0L && halfLeft2 == 0L && halfLeft1 == 0L) && (halfRight3 == 0L || halfLeft4 == 0L && halfLeft3 == 0L && halfLeft2 == 0L) && (halfRight2 == 0L || halfLeft4 == 0L && halfLeft3 == 0L) && (halfRight1 == 0L || halfLeft4 == 0L)) {
         result[0] = (long)z1 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z0;
         result[1] = (long)z3 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z2;
         result[2] = product;
         return true;
      } else {
         return false;
      }
   }

   public static boolean fastMultiplyFullInternal(long left0, long left1, long left2, long right0, long right1, long right2, long[] result) {
      if (result.length != 5) {
         throw new IllegalArgumentException("Expecting result array length = 5");
      } else {
         long halfRight0 = right0 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight1 = right0 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight2 = right1 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight3 = right1 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight4 = right2 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft0 = left0 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft1 = left0 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft2 = left1 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft3 = left1 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft4 = left2 % (long)MULTIPLER_INTWORD_DECIMAL;
         long product = halfRight0 * halfLeft0;
         int z0 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft1 + halfRight1 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z1 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft2 + halfRight1 * halfLeft1 + halfRight2 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z2 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft3 + halfRight1 * halfLeft2 + halfRight2 * halfLeft1 + halfRight3 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z3 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft4 + halfRight1 * halfLeft3 + halfRight2 * halfLeft2 + halfRight3 * halfLeft1 + halfRight4 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z4 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight1 * halfLeft4 + halfRight2 * halfLeft3 + halfRight3 * halfLeft2 + halfRight4 * halfLeft1 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z5 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight2 * halfLeft4 + halfRight3 * halfLeft3 + halfRight4 * halfLeft2 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z6 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight3 * halfLeft4 + halfRight4 * halfLeft3 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z7 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight4 * halfLeft4 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z8 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product /= (long)MULTIPLER_INTWORD_DECIMAL;
         if (product > FULL_MAX_HIGHWORD_DECIMAL) {
            return false;
         } else {
            result[0] = (long)z1 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z0;
            result[1] = (long)z3 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z2;
            result[2] = (long)z5 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z4;
            result[3] = (long)z7 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z6;
            result[4] = product * (long)MULTIPLER_INTWORD_DECIMAL + (long)z8;
            return true;
         }
      }
   }

   public static boolean fastMultiply5x6HalfWords(long left0, long left1, long left2, long right0, long right1, long right2, long[] result) {
      if (result.length != 6) {
         throw new RuntimeException("Expecting result array length = 6");
      } else {
         long halfRight0 = right0 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight1 = right0 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight2 = right1 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight3 = right1 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight4 = right2 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfRight5 = right2 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft0 = left0 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft1 = left0 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft2 = left1 % (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft3 = left1 / (long)MULTIPLER_INTWORD_DECIMAL;
         long halfLeft4 = left2 % (long)MULTIPLER_INTWORD_DECIMAL;
         long product = halfRight0 * halfLeft0;
         int z0 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft1 + halfRight1 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z1 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft2 + halfRight1 * halfLeft1 + halfRight2 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z2 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft3 + halfRight1 * halfLeft2 + halfRight2 * halfLeft1 + halfRight3 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z3 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight0 * halfLeft4 + halfRight1 * halfLeft3 + halfRight2 * halfLeft2 + halfRight3 * halfLeft1 + halfRight4 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z4 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight1 * halfLeft4 + halfRight2 * halfLeft3 + halfRight3 * halfLeft2 + halfRight4 * halfLeft1 + halfRight5 * halfLeft0 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z5 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight2 * halfLeft4 + halfRight3 * halfLeft3 + halfRight4 * halfLeft2 + halfRight5 * halfLeft1 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z6 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight3 * halfLeft4 + halfRight4 * halfLeft3 + halfRight5 * halfLeft2 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z7 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight4 * halfLeft4 + halfRight5 * halfLeft3 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z8 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product = halfRight5 * halfLeft4 + product / (long)MULTIPLER_INTWORD_DECIMAL;
         int z9 = (int)(product % (long)MULTIPLER_INTWORD_DECIMAL);
         product /= (long)MULTIPLER_INTWORD_DECIMAL;
         if (product > (long)MULTIPLER_INTWORD_DECIMAL) {
            return false;
         } else {
            result[0] = (long)z1 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z0;
            result[1] = (long)z3 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z2;
            result[2] = (long)z5 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z4;
            result[3] = (long)z7 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z6;
            result[4] = (long)z9 * (long)MULTIPLER_INTWORD_DECIMAL + (long)z8;
            result[5] = product;
            return true;
         }
      }
   }

   public static boolean fastMultiply(FastHiveDecimal fastLeft, FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return fastMultiply(fastLeft.fastSignum, fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastLeft.fastIntegerDigitCount, fastLeft.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   public static boolean fastMultiply(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      if (leftSignum != 0 && rightSignum != 0) {
         return doMultiply(leftSignum, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale, rightSignum, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale, fastResult);
      } else {
         fastResult.fastReset();
         return true;
      }
   }

   private static long doSingleWordQuotient(long leftFast0, long leftFast1, long leftFast2, long rightFast0, FastHiveDecimal fastResult) {
      long quotient2;
      long quotient1;
      long quotient0;
      long remainderSubexpr2;
      if (leftFast2 == 0L && leftFast1 == 0L) {
         quotient2 = 0L;
         quotient1 = 0L;
         quotient0 = leftFast0 / rightFast0;
         long k0 = leftFast0 - quotient0 * rightFast0;
         remainderSubexpr2 = k0 * MULTIPLER_LONGWORD_DECIMAL;
      } else if (leftFast2 == 0L) {
         quotient2 = 0L;
         quotient1 = leftFast1 / rightFast0;
         long k1 = leftFast1 - quotient1 * rightFast0;
         long quotientSubexpr0 = k1 * MULTIPLER_LONGWORD_DECIMAL + leftFast0;
         quotient0 = quotientSubexpr0 / rightFast0;
         long k0 = quotientSubexpr0 - quotient0 * rightFast0;
         remainderSubexpr2 = k0 * MULTIPLER_LONGWORD_DECIMAL;
      } else if (leftFast1 == 0L) {
         quotient2 = leftFast2 / rightFast0;
         quotient1 = 0L;
         quotient0 = leftFast0 / rightFast0;
         long k0 = leftFast0 - quotient0 * rightFast0;
         remainderSubexpr2 = k0 * MULTIPLER_LONGWORD_DECIMAL;
      } else {
         quotient2 = leftFast2 / rightFast0;
         long k2 = leftFast2 - quotient2 * rightFast0;
         long quotientSubexpr1 = k2 * MULTIPLER_LONGWORD_DECIMAL + leftFast1;
         quotient1 = quotientSubexpr1 / rightFast0;
         long k1 = quotientSubexpr1 - quotient1 * rightFast0;
         long quotientSubexpr0 = k1 * MULTIPLER_LONGWORD_DECIMAL;
         quotient0 = quotientSubexpr0 / rightFast0;
         long k0 = quotientSubexpr0 - quotient0 * rightFast0;
         remainderSubexpr2 = k0 * MULTIPLER_LONGWORD_DECIMAL;
      }

      fastResult.fast0 = quotient0;
      fastResult.fast1 = quotient1;
      fastResult.fast2 = quotient2;
      return remainderSubexpr2;
   }

   private static int doSingleWordRemainder(long leftFast0, long leftFast1, long leftFast2, long rightFast0, long remainderSubexpr2, FastHiveDecimal fastResult) {
      int remainderDigitCount;
      long remainder2;
      long remainder1;
      long remainder0;
      if (remainderSubexpr2 == 0L) {
         remainder2 = 0L;
         remainder1 = 0L;
         remainder0 = 0L;
         remainderDigitCount = 0;
      } else {
         remainder2 = remainderSubexpr2 / rightFast0;
         long k2 = remainderSubexpr2 - remainder2 * rightFast0;
         if (k2 == 0L) {
            remainder1 = 0L;
            remainder0 = 0L;
            remainderDigitCount = 16 - fastLongWordTrailingZeroCount(remainder2);
         } else {
            long remainderSubexpr1 = k2 * MULTIPLER_LONGWORD_DECIMAL;
            remainder1 = remainderSubexpr1 / rightFast0;
            long k1 = remainderSubexpr1 - remainder1 * rightFast0;
            if (k1 == 0L) {
               remainder0 = 0L;
               remainderDigitCount = 32 - fastLongWordTrailingZeroCount(remainder1);
            } else {
               long remainderSubexpr0 = k2 * MULTIPLER_LONGWORD_DECIMAL;
               remainder0 = remainderSubexpr0 / rightFast0;
               remainderDigitCount = 48 - fastLongWordTrailingZeroCount(remainder0);
            }
         }
      }

      fastResult.fast0 = remainder0;
      fastResult.fast1 = remainder1;
      fastResult.fast2 = remainder2;
      return remainderDigitCount;
   }

   private static boolean fastSingleWordDivision(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftScale, int rightSignum, long rightFast0, int rightScale, FastHiveDecimal fastResult) {
      long remainderSubexpr2 = doSingleWordQuotient(leftFast0, leftFast1, leftFast2, rightFast0, fastResult);
      long quotient0 = fastResult.fast0;
      long quotient1 = fastResult.fast1;
      long quotient2 = fastResult.fast2;
      int quotientDigitCount;
      if (quotient2 != 0L) {
         quotientDigitCount = fastLongWordPrecision(quotient2);
      } else if (quotient1 != 0L) {
         quotientDigitCount = fastLongWordPrecision(quotient1);
      } else {
         quotientDigitCount = fastLongWordPrecision(quotient0);
      }

      int remainderDigitCount = doSingleWordRemainder(leftFast0, leftFast1, leftFast2, rightFast0, remainderSubexpr2, fastResult);
      long remainder0 = fastResult.fast0;
      long remainder1 = fastResult.fast1;
      long remainder2 = fastResult.fast2;
      fastResult.fast0 = quotient0;
      fastResult.fast1 = quotient1;
      fastResult.fast2 = quotient2;
      int quotientScale = leftScale + rightScale;
      if (remainderDigitCount == 0) {
         fastResult.fastScale = quotientScale;
      } else {
         int resultScale = quotientScale + remainderDigitCount;
         int adjustedQuotientDigitCount;
         if (quotientScale > 0) {
            adjustedQuotientDigitCount = Math.max(0, quotientDigitCount - quotientScale);
         } else {
            adjustedQuotientDigitCount = quotientDigitCount;
         }

         int maxScale = 38 - adjustedQuotientDigitCount;
         int scale = Math.min(resultScale, maxScale);
         int remainderScale = Math.min(remainderDigitCount, maxScale - quotientScale);
         if (remainderScale > 0) {
            if (quotientDigitCount > 0) {
               fastScaleUp(fastResult, remainderScale, fastResult);
            }

            if (remainderScale < 16) {
               long remainderDivisor2 = powerOfTenTable[16 - remainderScale];
               fastResult.fast0 += remainder2 / remainderDivisor2;
            } else if (remainderScale == 16) {
               fastResult.fast0 = remainder2;
            } else if (remainderScale < 32) {
               long remainderDivisor2 = powerOfTenTable[remainderScale - 16];
               fastResult.fast1 += remainder2 / remainderDivisor2;
               fastResult.fast0 = remainder1;
            } else if (remainderScale == 32) {
               fastResult.fast1 = remainder2;
               fastResult.fast0 = remainder1;
            }
         }

         fastResult.fastScale = scale;
      }

      return true;
   }

   public static boolean fastDivide(FastHiveDecimal fastLeft, FastHiveDecimal fastRight, FastHiveDecimal fastResult) {
      return fastDivide(fastLeft.fastSignum, fastLeft.fast0, fastLeft.fast1, fastLeft.fast2, fastLeft.fastIntegerDigitCount, fastLeft.fastScale, fastRight.fastSignum, fastRight.fast0, fastRight.fast1, fastRight.fast2, fastRight.fastIntegerDigitCount, fastRight.fastScale, fastResult);
   }

   public static boolean fastDivide(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      fastResult.fastReset();
      if (rightSignum == 0) {
         return false;
      } else if (leftSignum == 0) {
         return true;
      } else {
         BigDecimal denominator = fastBigDecimalValue(leftSignum, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale);
         BigDecimal divisor = fastBigDecimalValue(rightSignum, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale);
         BigDecimal quotient = denominator.divide(divisor, 38, 4);
         return fastSetFromBigDecimal(quotient, true, fastResult);
      }
   }

   public static boolean fastRemainder(int leftSignum, long leftFast0, long leftFast1, long leftFast2, int leftIntegerDigitCount, int leftScale, int rightSignum, long rightFast0, long rightFast1, long rightFast2, int rightIntegerDigitCount, int rightScale, FastHiveDecimal fastResult) {
      fastResult.fastReset();
      if (rightSignum == 0) {
         return false;
      } else if (leftSignum == 0) {
         return true;
      } else {
         BigDecimal denominator = fastBigDecimalValue(leftSignum, leftFast0, leftFast1, leftFast2, leftIntegerDigitCount, leftScale);
         BigDecimal divisor = fastBigDecimalValue(rightSignum, rightFast0, rightFast1, rightFast2, rightIntegerDigitCount, rightScale);
         BigDecimal remainder = denominator.remainder(divisor);
         fastResult.fastReset();
         return fastSetFromBigDecimal(remainder, true, fastResult);
      }
   }

   public static boolean fastPow(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int exponent, FastHiveDecimal fastResult) {
      fastResult.fastSet(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
      if (exponent < 0) {
         return false;
      } else {
         for(int e = 1; e < exponent; ++e) {
            if (!doMultiply(fastResult.fastSignum, fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale, fastResult.fastSignum, fastResult.fast0, fastResult.fast1, fastResult.fast2, fastResult.fastIntegerDigitCount, fastResult.fastScale, fastResult)) {
               return false;
            }
         }

         return true;
      }
   }

   public static String fastToFormatString(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int formatScale) {
      byte[] scratchBuffer = new byte[79];
      int index = doFastToFormatBytes(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, formatScale, scratchBuffer);
      return new String(scratchBuffer, index, 79 - index, StandardCharsets.UTF_8);
   }

   public static String fastToFormatString(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int formatScale, byte[] scratchBuffer) {
      int index = doFastToBytes(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, formatScale, scratchBuffer);
      return new String(scratchBuffer, index, scratchBuffer.length - index, StandardCharsets.UTF_8);
   }

   public static int fastToFormatBytes(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int formatScale, byte[] scratchBuffer) {
      return doFastToFormatBytes(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, formatScale, scratchBuffer);
   }

   public static int doFastToFormatBytes(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int formatScale, byte[] scratchBuffer) {
      if (formatScale >= fastScale) {
         return doFastToBytes(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, formatScale, scratchBuffer);
      } else {
         FastHiveDecimal fastTemp = new FastHiveDecimal();
         return !fastRound(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, formatScale, 4, fastTemp) ? 0 : doFastToBytes(fastTemp.fastSignum, fastTemp.fast0, fastTemp.fast1, fastTemp.fast2, fastTemp.fastIntegerDigitCount, fastTemp.fastScale, formatScale, scratchBuffer);
      }
   }

   public static String fastToString(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastTrailingZeroesScale) {
      return doFastToString(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, fastTrailingZeroesScale);
   }

   public static String fastToString(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastTrailingZeroesScale, byte[] scratchBuffer) {
      return doFastToString(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, fastTrailingZeroesScale, scratchBuffer);
   }

   public static String fastToDigitsOnlyString(long fast0, long fast1, long fast2, int fastIntegerDigitCount) {
      byte[] scratchBuffer = new byte[79];
      int index = doFastToDigitsOnlyBytes(fast0, fast1, fast2, fastIntegerDigitCount, scratchBuffer);
      return new String(scratchBuffer, index, 79 - index, StandardCharsets.UTF_8);
   }

   public static int fastToBytes(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastTrailingZeroesScale, byte[] scratchBuffer) {
      return doFastToBytes(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, fastTrailingZeroesScale, scratchBuffer);
   }

   private static String doFastToString(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastTrailingZeroesScale) {
      byte[] scratchBuffer = new byte[79];
      int index = doFastToBytes(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, fastTrailingZeroesScale, scratchBuffer);
      return new String(scratchBuffer, index, 79 - index, StandardCharsets.UTF_8);
   }

   private static String doFastToString(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastTrailingZeroesScale, byte[] scratchBuffer) {
      int index = doFastToBytes(fastSignum, fast0, fast1, fast2, fastIntegerDigitCount, fastScale, fastTrailingZeroesScale, scratchBuffer);
      return new String(scratchBuffer, index, scratchBuffer.length - index, StandardCharsets.UTF_8);
   }

   private static int doFastToBytes(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale, int fastTrailingZeroesScale, byte[] scratchBuffer) {
      int index = scratchBuffer.length - 1;
      int trailingZeroCount = fastTrailingZeroesScale != -1 ? fastTrailingZeroesScale - fastScale : 0;
      if (trailingZeroCount > 0) {
         for(int i = 0; i < trailingZeroCount; ++i) {
            scratchBuffer[index--] = 48;
         }
      }

      boolean isZeroFast1AndFast2 = fast1 == 0L && fast2 == 0L;
      boolean isZeroFast2 = fast2 == 0L;
      int lowerLongwordScale = 0;
      int middleLongwordScale = 0;
      int highLongwordScale = 0;
      long longWord = fast0;
      if (fastScale > 0) {
         lowerLongwordScale = Math.min(fastScale, 16);

         for(int i = 0; i < lowerLongwordScale; ++i) {
            scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
            longWord /= 10L;
         }

         if (lowerLongwordScale == 16) {
            longWord = fast1;
         }

         if (fastScale > 16) {
            middleLongwordScale = Math.min(fastScale - 16, 16);

            for(int i = 0; i < middleLongwordScale; ++i) {
               scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
               longWord /= 10L;
            }

            if (middleLongwordScale == 16) {
               longWord = fast2;
            }

            if (fastScale > 32) {
               highLongwordScale = fastScale - 32;

               for(int i = 0; i < highLongwordScale; ++i) {
                  scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
                  longWord /= 10L;
               }
            }
         }

         scratchBuffer[index--] = 46;
      } else if (trailingZeroCount > 0) {
         scratchBuffer[index--] = 46;
      }

      boolean atLeastOneIntegerDigit = false;
      if (fastScale <= 16) {
         int remainingLowerLongwordDigits = 16 - lowerLongwordScale;

         for(int i = 0; i < remainingLowerLongwordDigits; ++i) {
            scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
            atLeastOneIntegerDigit = true;
            longWord /= 10L;
            if (longWord == 0L && isZeroFast1AndFast2) {
               break;
            }
         }

         if (isZeroFast1AndFast2) {
            if (!atLeastOneIntegerDigit) {
               scratchBuffer[index--] = 48;
            }

            if (fastSignum == -1) {
               scratchBuffer[index--] = 45;
            }

            return index + 1;
         }

         longWord = fast1;
      }

      if (fastScale <= 32) {
         int remainingMiddleLongwordDigits = 16 - middleLongwordScale;

         for(int i = 0; i < remainingMiddleLongwordDigits; ++i) {
            scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
            atLeastOneIntegerDigit = true;
            longWord /= 10L;
            if (longWord == 0L && isZeroFast2) {
               break;
            }
         }

         if (isZeroFast2) {
            if (!atLeastOneIntegerDigit) {
               scratchBuffer[index--] = 48;
            }

            if (fastSignum == -1) {
               scratchBuffer[index--] = 45;
            }

            return index + 1;
         }

         longWord = fast2;
      }

      int remainingHighwordDigits = 6 - highLongwordScale;

      for(int i = 0; i < remainingHighwordDigits; ++i) {
         scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
         atLeastOneIntegerDigit = true;
         longWord /= 10L;
         if (longWord == 0L) {
            break;
         }
      }

      if (!atLeastOneIntegerDigit) {
         scratchBuffer[index--] = 48;
      }

      if (fastSignum == -1) {
         scratchBuffer[index--] = 45;
      }

      return index + 1;
   }

   public static int fastToDigitsOnlyBytes(long fast0, long fast1, long fast2, int fastIntegerDigitCount, byte[] scratchBuffer) {
      return doFastToDigitsOnlyBytes(fast0, fast1, fast2, fastIntegerDigitCount, scratchBuffer);
   }

   private static int doFastToDigitsOnlyBytes(long fast0, long fast1, long fast2, int fastIntegerDigitCount, byte[] scratchBuffer) {
      int index = scratchBuffer.length - 1;
      boolean isZeroFast1AndFast2 = fast1 == 0L && fast2 == 0L;
      boolean isZeroFast2 = fast2 == 0L;
      boolean atLeastOneIntegerDigit = false;
      long longWord = fast0;

      for(int i = 0; i < 16; ++i) {
         scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
         atLeastOneIntegerDigit = true;
         longWord /= 10L;
         if (longWord == 0L && isZeroFast1AndFast2) {
            break;
         }
      }

      if (isZeroFast1AndFast2) {
         if (!atLeastOneIntegerDigit) {
            scratchBuffer[index--] = 48;
         }

         return index + 1;
      } else {
         longWord = fast1;

         for(int i = 0; i < 16; ++i) {
            scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
            atLeastOneIntegerDigit = true;
            longWord /= 10L;
            if (longWord == 0L && isZeroFast2) {
               break;
            }
         }

         if (isZeroFast2) {
            if (!atLeastOneIntegerDigit) {
               scratchBuffer[index--] = 48;
            }

            return index + 1;
         } else {
            longWord = fast2;

            for(int i = 0; i < 6; ++i) {
               scratchBuffer[index--] = (byte)((int)(48L + longWord % 10L));
               atLeastOneIntegerDigit = true;
               longWord /= 10L;
               if (longWord == 0L) {
                  break;
               }
            }

            if (!atLeastOneIntegerDigit) {
               scratchBuffer[index--] = 48;
            }

            return index + 1;
         }
      }
   }

   public static boolean fastIsValid(FastHiveDecimal fastDec) {
      return fastIsValid(fastDec.fastSignum, fastDec.fast0, fastDec.fast1, fastDec.fast2, fastDec.fastIntegerDigitCount, fastDec.fastScale);
   }

   public static boolean fastIsValid(int fastSignum, long fast0, long fast1, long fast2, int fastIntegerDigitCount, int fastScale) {
      boolean isValid;
      if (fastSignum == 0) {
         isValid = fast0 == 0L && fast1 == 0L && fast2 == 0L && fastIntegerDigitCount == 0 && fastScale == 0;
         if (!isValid) {
            System.out.println("FAST_IS_VALID signum 0 but other fields not");
         }
      } else {
         isValid = fast0 >= 0L && fast0 <= MAX_LONGWORD_DECIMAL && fast1 >= 0L && fast1 <= MAX_LONGWORD_DECIMAL && fast2 >= 0L && fast2 <= MAX_HIGHWORD_DECIMAL;
         if (!isValid) {
            System.out.println("FAST_IS_VALID fast0 .. fast2 out of range");
         } else if (fastScale >= 0 && fastScale <= 38) {
            if (fastIntegerDigitCount >= 0 && fastIntegerDigitCount <= 38) {
               if (fastIntegerDigitCount + fastScale > 38) {
                  System.out.println("FAST_IS_VALID exceeds max precision: fastIntegerDigitCount " + fastIntegerDigitCount + " and fastScale " + fastScale);
                  isValid = false;
               } else {
                  int rawPrecision = fastRawPrecision(fastSignum, fast0, fast1, fast2);
                  if (fastIntegerDigitCount > 0) {
                     if (rawPrecision != fastIntegerDigitCount + fastScale) {
                        System.out.println("FAST_IS_VALID integer case: rawPrecision " + rawPrecision + " fastIntegerDigitCount " + fastIntegerDigitCount + " fastScale " + fastScale);
                        isValid = false;
                     }
                  } else if (rawPrecision > fastScale) {
                     System.out.println("FAST_IS_VALID fraction only case: rawPrecision " + rawPrecision + " fastIntegerDigitCount " + fastIntegerDigitCount + " fastScale " + fastScale);
                     isValid = false;
                  }

                  if (isValid) {
                     int trailingZeroCount = fastTrailingDecimalZeroCount(fast0, fast1, fast2, fastIntegerDigitCount, fastScale);
                     if (trailingZeroCount != 0) {
                        System.out.println("FAST_IS_VALID exceeds max precision: trailingZeroCount != 0");
                        isValid = false;
                     }
                  }
               }
            } else {
               System.out.println("FAST_IS_VALID fastIntegerDigitCount " + fastIntegerDigitCount + " out of range");
               isValid = false;
            }
         } else {
            System.out.println("FAST_IS_VALID fastScale " + fastScale + " out of range");
            isValid = false;
         }
      }

      if (!isValid) {
         System.out.println("FAST_IS_VALID fast0 " + fast0);
         System.out.println("FAST_IS_VALID fast1 " + fast1);
         System.out.println("FAST_IS_VALID fast2 " + fast2);
         System.out.println("FAST_IS_VALID fastIntegerDigitCount " + fastIntegerDigitCount);
         System.out.println("FAST_IS_VALID fastScale " + fastScale);
      }

      return isValid;
   }

   public static void fastRaiseInvalidException(FastHiveDecimal fastResult) {
      throw new RuntimeException("Invalid fast decimal  fastSignum " + fastResult.fastSignum + " fast0 " + fastResult.fast0 + " fast1 " + fastResult.fast1 + " fast2 " + fastResult.fast2 + " fastIntegerDigitCount " + fastResult.fastIntegerDigitCount + " fastScale " + fastResult.fastScale + " stack trace: " + getStackTraceAsSingleLine(Thread.currentThread().getStackTrace()));
   }

   public static void fastRaiseInvalidException(FastHiveDecimal fastResult, String parameters) {
      throw new RuntimeException("Parameters: " + parameters + " --> Invalid fast decimal  fastSignum " + fastResult.fastSignum + " fast0 " + fastResult.fast0 + " fast1 " + fastResult.fast1 + " fast2 " + fastResult.fast2 + " fastIntegerDigitCount " + fastResult.fastIntegerDigitCount + " fastScale " + fastResult.fastScale + " stack trace: " + getStackTraceAsSingleLine(Thread.currentThread().getStackTrace()));
   }

   public static String getStackTraceAsSingleLine(StackTraceElement[] stackTrace) {
      StringBuilder sb = new StringBuilder();
      sb.append("Stack trace: ");
      int length = stackTrace.length;
      boolean isTruncated = false;
      if (length > 20) {
         length = 20;
         isTruncated = true;
      }

      for(int i = 0; i < length; ++i) {
         if (i > 0) {
            sb.append(", ");
         }

         sb.append(stackTrace[i]);
      }

      if (isTruncated) {
         sb.append(", ...");
      }

      return sb.toString();
   }

   public static String displayBytes(byte[] bytes, int start, int length) {
      StringBuilder sb = new StringBuilder();

      for(int i = start; i < start + length; ++i) {
         sb.append(String.format("\\%03d", bytes[i] & 255));
      }

      return sb.toString();
   }

   static {
      MULTIPLER_INTWORD_DECIMAL = (int)powerOfTenTable[8];
      MAX_LONGWORD_DECIMAL = powerOfTenTable[16] - 1L;
      MULTIPLER_LONGWORD_DECIMAL = powerOfTenTable[16];
      MAX_HIGHWORD_DECIMAL = powerOfTenTable[6] - 1L;
      FULL_MAX_HIGHWORD_DECIMAL = powerOfTenTable[4] - 1L;
      BIG_INTEGER_TWO = BigInteger.valueOf(2L);
      BIG_INTEGER_FIVE = BigInteger.valueOf(5L);
      BIG_INTEGER_TEN = BigInteger.valueOf(10L);
      BIG_INTEGER_MAX_DECIMAL = BIG_INTEGER_TEN.pow(38).subtract(BigInteger.ONE);
      BIG_INTEGER_MAX_LONGWORD_DECIMAL = BigInteger.valueOf(MAX_LONGWORD_DECIMAL);
      BIG_INTEGER_LONGWORD_MULTIPLIER = BigInteger.ONE.add(BIG_INTEGER_MAX_LONGWORD_DECIMAL);
      BIG_INTEGER_LONGWORD_MULTIPLIER_2X = BIG_INTEGER_LONGWORD_MULTIPLIER.multiply(BIG_INTEGER_LONGWORD_MULTIPLIER);
      BIG_INTEGER_MAX_HIGHWORD_DECIMAL = BigInteger.valueOf(MAX_HIGHWORD_DECIMAL);
      BIG_INTEGER_HIGHWORD_MULTIPLIER = BigInteger.ONE.add(BIG_INTEGER_MAX_HIGHWORD_DECIMAL);
      FAST_HIVE_DECIMAL_TWO_POWER_62 = new FastHiveDecimal(1, 1686018427387904L, 461L, 0L, 19, 0);
      FAST_HIVE_DECIMAL_TWO_POWER_63 = new FastHiveDecimal(1, 3372036854775808L, 922L, 0L, 19, 0);
      FAST_HIVE_DECIMAL_TWO_POWER_125 = new FastHiveDecimal(1, 1825928971026432L, 9586511730793292L, 425352L, 38, 0);
      FAST_HIVE_DECIMAL_TWO_POWER_63_INVERSE = new FastHiveDecimal(1, 6994171142578125L, 5044340074528008L, 1084202172485L, 45, 0);
      FAST_HIVE_DECIMAL_TWO_POWER_56 = new FastHiveDecimal(1, 2057594037927936L, 7L, 0L, 17, 0);
      FAST_HIVE_DECIMAL_TWO_POWER_112 = new FastHiveDecimal(1, 8530496329220096L, 9229685853482762L, 51L, 34, 0);
      FAST_HIVE_DECIMAL_TWO_POWER_56_INVERSE = new FastHiveDecimal(1, 9585113525390625L, 8078144567552953L, 13877787L, 40, 0);
      FASTHIVEDECIMAL_MIN_BYTE_VALUE_MINUS_ONE = new FastHiveDecimal(-129L);
      FASTHIVEDECIMAL_MAX_BYTE_VALUE_PLUS_ONE = new FastHiveDecimal(128L);
      FASTHIVEDECIMAL_MIN_SHORT_VALUE_MINUS_ONE = new FastHiveDecimal(-32769L);
      FASTHIVEDECIMAL_MAX_SHORT_VALUE_PLUS_ONE = new FastHiveDecimal(32768L);
      FASTHIVEDECIMAL_MIN_INT_VALUE_MINUS_ONE = new FastHiveDecimal(-2147483649L);
      FASTHIVEDECIMAL_MAX_INT_VALUE_PLUS_ONE = new FastHiveDecimal(2147483648L);
      FASTHIVEDECIMAL_MIN_LONG_VALUE = new FastHiveDecimal(Long.MIN_VALUE);
      FASTHIVEDECIMAL_MAX_LONG_VALUE = new FastHiveDecimal(Long.MAX_VALUE);
      MAX_LONG_DIGITS = FASTHIVEDECIMAL_MAX_LONG_VALUE.fastIntegerDigitCount;
      FASTHIVEDECIMAL_MIN_LONG_VALUE_MINUS_ONE = new FastHiveDecimal("-9223372036854775809");
      FASTHIVEDECIMAL_MAX_LONG_VALUE_PLUS_ONE = new FastHiveDecimal("9223372036854775808");
      BIG_INTEGER_UNSIGNED_BYTE_MAX_VALUE = BIG_INTEGER_TWO.pow(8).subtract(BigInteger.ONE);
      BIG_INTEGER_UNSIGNED_SHORT_MAX_VALUE = BIG_INTEGER_TWO.pow(16).subtract(BigInteger.ONE);
      BIG_INTEGER_UNSIGNED_INT_MAX_VALUE = BIG_INTEGER_TWO.pow(32).subtract(BigInteger.ONE);
      BIG_INTEGER_UNSIGNED_LONG_MAX_VALUE = BIG_INTEGER_TWO.pow(64).subtract(BigInteger.ONE);
      ZERO_NEW_FASTER_HASH_CODE = doCalculateNewFasterHashCode(0, 0L, 0L, 0L, 0, 0);
   }
}
