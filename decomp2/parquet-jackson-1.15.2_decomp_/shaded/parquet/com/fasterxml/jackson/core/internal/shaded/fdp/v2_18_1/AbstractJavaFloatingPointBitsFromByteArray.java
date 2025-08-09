package shaded.parquet.com.fasterxml.jackson.core.internal.shaded.fdp.v2_18_1;

abstract class AbstractJavaFloatingPointBitsFromByteArray extends AbstractFloatValueParser {
   private static int skipWhitespace(byte[] str, int index, int endIndex) {
      while(index < endIndex && (str[index] & 255) <= 32) {
         ++index;
      }

      return index;
   }

   abstract long nan();

   abstract long negativeInfinity();

   private long parseDecFloatLiteral(byte[] str, int index, int startIndex, int endIndex, boolean isNegative, boolean hasLeadingZero) {
      long significand = 0L;
      int significandStartIndex = index;
      int virtualIndexOfPoint = -1;
      boolean illegal = false;

      byte ch;
      for(ch = 0; index < endIndex; ++index) {
         ch = str[index];
         char digit = (char)(ch - 48);
         if (digit < '\n') {
            significand = 10L * significand + (long)digit;
         } else {
            if (ch != 46) {
               break;
            }

            illegal |= virtualIndexOfPoint >= 0;

            for(virtualIndexOfPoint = index; index < endIndex - 4; index += 4) {
               int digits = FastDoubleSwar.tryToParseFourDigits(str, index + 1);
               if (digits < 0) {
                  break;
               }

               significand = 10000L * significand + (long)digits;
            }
         }
      }

      int significandEndIndex = index;
      int exponent;
      int digitCount;
      if (virtualIndexOfPoint < 0) {
         digitCount = index - significandStartIndex;
         virtualIndexOfPoint = index;
         exponent = 0;
      } else {
         digitCount = index - significandStartIndex - 1;
         exponent = virtualIndexOfPoint - index + 1;
      }

      int expNumber = 0;
      if ((ch | 32) == 101) {
         ++index;
         ch = charAt(str, index, endIndex);
         boolean isExponentNegative = ch == 45;
         if (isExponentNegative || ch == 43) {
            ++index;
            ch = charAt(str, index, endIndex);
         }

         int digit = (char)(ch - 48);
         illegal |= digit >= 10;

         do {
            if (expNumber < 1024) {
               expNumber = 10 * expNumber + digit;
            }

            ++index;
            ch = charAt(str, index, endIndex);
            digit = (char)(ch - 48);
         } while(digit < 10);

         if (isExponentNegative) {
            expNumber = -expNumber;
         }

         exponent += expNumber;
      }

      if ((ch | 34) == 102) {
         ++index;
      }

      index = skipWhitespace(str, index, endIndex);
      if (!illegal && index >= endIndex && (hasLeadingZero || digitCount != 0)) {
         int skipCountInTruncatedDigits = 0;
         int exponentOfTruncatedSignificand;
         boolean isSignificandTruncated;
         if (digitCount > 19) {
            significand = 0L;

            for(index = significandStartIndex; index < significandEndIndex; ++index) {
               ch = str[index];
               if (ch == 46) {
                  ++skipCountInTruncatedDigits;
               } else {
                  if (Long.compareUnsigned(significand, 1000000000000000000L) >= 0) {
                     break;
                  }

                  significand = 10L * significand + (long)ch - 48L;
               }
            }

            isSignificandTruncated = index < significandEndIndex;
            exponentOfTruncatedSignificand = virtualIndexOfPoint - index + skipCountInTruncatedDigits + expNumber;
         } else {
            isSignificandTruncated = false;
            exponentOfTruncatedSignificand = 0;
         }

         return this.valueOfFloatLiteral(str, startIndex, endIndex, isNegative, significand, exponent, isSignificandTruncated, exponentOfTruncatedSignificand);
      } else {
         throw new NumberFormatException("illegal syntax");
      }
   }

   public long parseFloatingPointLiteral(byte[] str, int offset, int length) {
      int endIndex = checkBounds(str.length, offset, length);
      int index = skipWhitespace(str, offset, endIndex);
      if (index == endIndex) {
         throw new NumberFormatException("illegal syntax");
      } else {
         byte ch = str[index];
         boolean isNegative = ch == 45;
         if (isNegative || ch == 43) {
            ++index;
            ch = charAt(str, index, endIndex);
            if (ch == 0) {
               throw new NumberFormatException("illegal syntax");
            }
         }

         if (ch >= 73) {
            return this.parseNaNOrInfinity(str, index, endIndex, isNegative);
         } else {
            boolean hasLeadingZero = ch == 48;
            if (hasLeadingZero) {
               ++index;
               ch = charAt(str, index, endIndex);
               if ((ch | 32) == 120) {
                  return this.parseHexFloatingPointLiteral(str, index + 1, offset, endIndex, isNegative);
               }
            }

            return this.parseDecFloatLiteral(str, index, offset, endIndex, isNegative, hasLeadingZero);
         }
      }
   }

   private long parseHexFloatingPointLiteral(byte[] str, int index, int startIndex, int endIndex, boolean isNegative) {
      long significand = 0L;
      int exponent = 0;
      int significandStartIndex = index;
      int virtualIndexOfPoint = -1;
      boolean illegal = false;

      byte ch;
      for(ch = 0; index < endIndex; ++index) {
         ch = str[index];
         int hexValue = lookupHex(ch);
         if (hexValue >= 0) {
            significand = significand << 4 | (long)hexValue;
         } else {
            if (hexValue != -4) {
               break;
            }

            illegal |= virtualIndexOfPoint >= 0;
            virtualIndexOfPoint = index;
         }
      }

      int significandEndIndex = index;
      int digitCount;
      if (virtualIndexOfPoint < 0) {
         digitCount = index - significandStartIndex;
         virtualIndexOfPoint = index;
      } else {
         digitCount = index - significandStartIndex - 1;
         exponent = Math.min(virtualIndexOfPoint - index + 1, 1024) * 4;
      }

      int expNumber = 0;
      boolean hasExponent = (ch | 32) == 112;
      if (hasExponent) {
         ++index;
         ch = charAt(str, index, endIndex);
         boolean isExponentNegative = ch == 45;
         if (isExponentNegative || ch == 43) {
            ++index;
            ch = charAt(str, index, endIndex);
         }

         int digit = (char)(ch - 48);
         illegal |= digit >= 10;

         do {
            if (expNumber < 1024) {
               expNumber = 10 * expNumber + digit;
            }

            ++index;
            ch = charAt(str, index, endIndex);
            digit = (char)(ch - 48);
         } while(digit < 10);

         if (isExponentNegative) {
            expNumber = -expNumber;
         }

         exponent += expNumber;
      }

      if ((ch | 34) == 102) {
         ++index;
      }

      index = skipWhitespace(str, index, endIndex);
      if (!illegal && index >= endIndex && digitCount != 0 && hasExponent) {
         int skipCountInTruncatedDigits = 0;
         boolean isSignificandTruncated;
         if (digitCount > 16) {
            significand = 0L;

            for(index = significandStartIndex; index < significandEndIndex; ++index) {
               ch = str[index];
               int hexValue = lookupHex(ch);
               if (hexValue >= 0) {
                  if (Long.compareUnsigned(significand, 1000000000000000000L) >= 0) {
                     break;
                  }

                  significand = significand << 4 | (long)hexValue;
               } else {
                  ++skipCountInTruncatedDigits;
               }
            }

            isSignificandTruncated = index < significandEndIndex;
         } else {
            isSignificandTruncated = false;
         }

         return this.valueOfHexLiteral(str, startIndex, endIndex, isNegative, significand, exponent, isSignificandTruncated, (virtualIndexOfPoint - index + skipCountInTruncatedDigits) * 4 + expNumber);
      } else {
         throw new NumberFormatException("illegal syntax");
      }
   }

   private long parseNaNOrInfinity(byte[] str, int index, int endIndex, boolean isNegative) {
      if (str[index] == 78) {
         if (index + 2 < endIndex && str[index + 1] == 97 && str[index + 2] == 78) {
            index = skipWhitespace(str, index + 3, endIndex);
            if (index == endIndex) {
               return this.nan();
            }
         }
      } else if (index + 7 < endIndex && FastDoubleSwar.readLongLE(str, index) == 8751735898823355977L) {
         index = skipWhitespace(str, index + 8, endIndex);
         if (index == endIndex) {
            return isNegative ? this.negativeInfinity() : this.positiveInfinity();
         }
      }

      throw new NumberFormatException("illegal syntax");
   }

   abstract long positiveInfinity();

   abstract long valueOfFloatLiteral(byte[] var1, int var2, int var3, boolean var4, long var5, int var7, boolean var8, int var9);

   abstract long valueOfHexLiteral(byte[] var1, int var2, int var3, boolean var4, long var5, int var7, boolean var8, int var9);
}
