package com.fasterxml.jackson.core.internal.shaded.fdp.v2_18_2;

import java.math.BigInteger;
import java.util.Map;

class ParseDigitsTaskCharArray {
   private ParseDigitsTaskCharArray() {
   }

   static BigInteger parseDigitsIterative(char[] str, int from, int to) {
      assert str != null : "str==null";

      int numDigits = to - from;
      BigSignificand bigSignificand = new BigSignificand(FastIntegerMath.estimateNumBits((long)numDigits));
      int preroll = from + (numDigits & 7);
      int value = FastDoubleSwar.tryToParseUpTo7Digits(str, from, preroll);
      boolean success = value >= 0;
      bigSignificand.add(value);

      for(int var9 = preroll; var9 < to; var9 += 8) {
         int addend = FastDoubleSwar.tryToParseEightDigits(str, var9);
         success &= addend >= 0;
         bigSignificand.fma(100000000, addend);
      }

      if (!success) {
         throw new NumberFormatException("illegal syntax");
      } else {
         return bigSignificand.toBigInteger();
      }
   }

   static BigInteger parseDigitsRecursive(char[] str, int from, int to, Map powersOfTen, int recursionThreshold) {
      assert str != null : "str==null";

      assert powersOfTen != null : "powersOfTen==null";

      int numDigits = to - from;
      if (numDigits <= recursionThreshold) {
         return parseDigitsIterative(str, from, to);
      } else {
         int mid = FastIntegerMath.splitFloor16(from, to);
         BigInteger high = parseDigitsRecursive(str, from, mid, powersOfTen, recursionThreshold);
         BigInteger low = parseDigitsRecursive(str, mid, to, powersOfTen, recursionThreshold);
         high = FftMultiplier.multiply(high, (BigInteger)powersOfTen.get(to - mid));
         return low.add(high);
      }
   }
}
