package jodd.util;

public class MathUtil {
   public static int parseDigit(char digit) {
      if (digit >= '0' && digit <= '9') {
         return digit - 48;
      } else {
         return CharUtil.isLowercaseAlpha(digit) ? 10 + digit - 97 : 10 + digit - 65;
      }
   }

   public static long randomLong(long min, long max) {
      return min + (long)(Math.random() * (double)(max - min));
   }

   public static int randomInt(int min, int max) {
      return min + (int)(Math.random() * (double)(max - min));
   }

   public static boolean isEven(int x) {
      return x % 2 == 0;
   }

   public static boolean isOdd(int x) {
      return x % 2 != 0;
   }

   public static long factorial(long x) {
      if (x < 0L) {
         return 0L;
      } else {
         long factorial;
         for(factorial = 1L; x > 1L; --x) {
            factorial *= x;
         }

         return factorial;
      }
   }
}
