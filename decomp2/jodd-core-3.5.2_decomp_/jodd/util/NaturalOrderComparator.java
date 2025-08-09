package jodd.util;

import java.util.Comparator;

public class NaturalOrderComparator implements Comparator {
   protected final boolean ignoreCase;

   public NaturalOrderComparator() {
      this.ignoreCase = false;
   }

   public NaturalOrderComparator(boolean ignoreCase) {
      this.ignoreCase = ignoreCase;
   }

   protected int compareDigits(String str1, int ndx1, String str2, int ndx2) {
      int bias = 0;

      while(true) {
         char char1 = charAt(str1, ndx1);
         char char2 = charAt(str2, ndx2);
         boolean isDigitChar1 = CharUtil.isDigit(char1);
         boolean isDigitChar2 = CharUtil.isDigit(char2);
         if (!isDigitChar1 && !isDigitChar2) {
            return bias;
         }

         if (!isDigitChar1) {
            return -1;
         }

         if (!isDigitChar2) {
            return 1;
         }

         if (char1 < char2) {
            if (bias == 0) {
               bias = -1;
            }
         } else if (char1 > char2) {
            if (bias == 0) {
               bias = 1;
            }
         } else if (char1 == 0 && char2 == 0) {
            return bias;
         }

         ++ndx1;
         ++ndx2;
      }
   }

   public int compare(Object o1, Object o2) {
      String str1 = o1.toString();
      String str2 = o2.toString();
      int ndx1 = 0;
      int ndx2 = 0;

      while(true) {
         int zeroCount2 = 0;
         int zeroCount1 = 0;
         char char1 = charAt(str1, ndx1);

         char char2;
         for(char2 = charAt(str2, ndx2); Character.isSpaceChar(char1) || char1 == '0'; char1 = charAt(str1, ndx1)) {
            if (char1 == '0') {
               ++zeroCount1;
            } else {
               zeroCount1 = 0;
            }

            ++ndx1;
         }

         while(Character.isSpaceChar(char2) || char2 == '0') {
            if (char2 == '0') {
               ++zeroCount2;
            } else {
               zeroCount2 = 0;
            }

            ++ndx2;
            char2 = charAt(str2, ndx2);
         }

         boolean isDigitChar1 = CharUtil.isDigit(char1);
         boolean isDigitChar2 = CharUtil.isDigit(char2);
         if (isDigitChar1 && isDigitChar2) {
            int result = this.compareDigits(str1, ndx1, str2, ndx2);
            if (result != 0) {
               return result;
            }

            if (zeroCount1 != zeroCount2) {
               return zeroCount1 - zeroCount2;
            }
         }

         if (char1 == 0 && char2 == 0) {
            return zeroCount1 - zeroCount2;
         }

         if ((isDigitChar1 || isDigitChar2) && zeroCount1 != zeroCount2) {
            return zeroCount2 - zeroCount1;
         }

         if (zeroCount1 != zeroCount2) {
            return zeroCount1 - zeroCount2;
         }

         if (this.ignoreCase) {
            char1 = Character.toLowerCase(char1);
            char2 = Character.toLowerCase(char2);
         }

         if (char1 < char2) {
            return -1;
         }

         if (char1 > char2) {
            return 1;
         }

         ++ndx1;
         ++ndx2;
      }
   }

   private static char charAt(String s, int i) {
      return i >= s.length() ? '\u0000' : s.charAt(i);
   }
}
