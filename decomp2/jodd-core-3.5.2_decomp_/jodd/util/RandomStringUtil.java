package jodd.util;

import java.util.Random;

public class RandomStringUtil {
   protected static final Random rnd = new Random();
   protected static final char[] ALPHA_RANGE = new char[]{'A', 'Z', 'a', 'z'};
   protected static final char[] ALPHA_NUMERIC_RANGE = new char[]{'0', '9', 'A', 'Z', 'a', 'z'};

   public static String random(int count, char[] chars) {
      if (count == 0) {
         return "";
      } else {
         char[] result;
         for(result = new char[count]; count-- > 0; result[count] = chars[rnd.nextInt(chars.length)]) {
         }

         return new String(result);
      }
   }

   public static String random(int count, String chars) {
      return random(count, chars.toCharArray());
   }

   public static String random(int count, char start, char end) {
      if (count == 0) {
         return "";
      } else {
         char[] result = new char[count];

         for(int len = end - start + 1; count-- > 0; result[count] = (char)(rnd.nextInt(len) + start)) {
         }

         return new String(result);
      }
   }

   public static String randomAscii(int count) {
      return random(count, ' ', '~');
   }

   public static String randomNumeric(int count) {
      return random(count, '0', '9');
   }

   public static String randomRanges(int count, char... ranges) {
      if (count == 0) {
         return "";
      } else {
         int i = 0;
         int len = 0;

         int[] lens;
         for(lens = new int[ranges.length]; i < ranges.length; i += 2) {
            int gap = ranges[i + 1] - ranges[i] + 1;
            len += gap;
            lens[i] = len;
         }

         char c;
         char[] result;
         for(result = new char[count]; count-- > 0; result[count] = c) {
            c = 0;
            int r = rnd.nextInt(len);

            for(int var8 = 0; var8 < ranges.length; var8 += 2) {
               if (r < lens[var8]) {
                  r += ranges[var8];
                  if (var8 != 0) {
                     r -= lens[var8 - 2];
                  }

                  c = (char)r;
                  break;
               }
            }
         }

         return new String(result);
      }
   }

   public static String randomAlpha(int count) {
      return randomRanges(count, ALPHA_RANGE);
   }

   public static String randomAlphaNumeric(int count) {
      return randomRanges(count, ALPHA_NUMERIC_RANGE);
   }
}
