package org.joda.time.field;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

public class FieldUtils {
   private FieldUtils() {
   }

   public static int safeNegate(int var0) {
      if (var0 == Integer.MIN_VALUE) {
         throw new ArithmeticException("Integer.MIN_VALUE cannot be negated");
      } else {
         return -var0;
      }
   }

   public static int safeAdd(int var0, int var1) {
      int var2 = var0 + var1;
      if ((var0 ^ var2) < 0 && (var0 ^ var1) >= 0) {
         throw new ArithmeticException("The calculation caused an overflow: " + var0 + " + " + var1);
      } else {
         return var2;
      }
   }

   public static long safeAdd(long var0, long var2) {
      long var4 = var0 + var2;
      if ((var0 ^ var4) < 0L && (var0 ^ var2) >= 0L) {
         throw new ArithmeticException("The calculation caused an overflow: " + var0 + " + " + var2);
      } else {
         return var4;
      }
   }

   public static long safeSubtract(long var0, long var2) {
      long var4 = var0 - var2;
      if ((var0 ^ var4) < 0L && (var0 ^ var2) < 0L) {
         throw new ArithmeticException("The calculation caused an overflow: " + var0 + " - " + var2);
      } else {
         return var4;
      }
   }

   public static int safeMultiply(int var0, int var1) {
      long var2 = (long)var0 * (long)var1;
      if (var2 >= -2147483648L && var2 <= 2147483647L) {
         return (int)var2;
      } else {
         throw new ArithmeticException("Multiplication overflows an int: " + var0 + " * " + var1);
      }
   }

   public static long safeMultiply(long var0, int var2) {
      switch (var2) {
         case -1:
            if (var0 == Long.MIN_VALUE) {
               throw new ArithmeticException("Multiplication overflows a long: " + var0 + " * " + var2);
            }

            return -var0;
         case 0:
            return 0L;
         case 1:
            return var0;
         default:
            long var3 = var0 * (long)var2;
            if (var3 / (long)var2 != var0) {
               throw new ArithmeticException("Multiplication overflows a long: " + var0 + " * " + var2);
            } else {
               return var3;
            }
      }
   }

   public static long safeMultiply(long var0, long var2) {
      if (var2 == 1L) {
         return var0;
      } else if (var0 == 1L) {
         return var2;
      } else if (var0 != 0L && var2 != 0L) {
         long var4 = var0 * var2;
         if (var4 / var2 == var0 && (var0 != Long.MIN_VALUE || var2 != -1L) && (var2 != Long.MIN_VALUE || var0 != -1L)) {
            return var4;
         } else {
            throw new ArithmeticException("Multiplication overflows a long: " + var0 + " * " + var2);
         }
      } else {
         return 0L;
      }
   }

   public static long safeDivide(long var0, long var2) {
      if (var0 == Long.MIN_VALUE && var2 == -1L) {
         throw new ArithmeticException("Multiplication overflows a long: " + var0 + " / " + var2);
      } else {
         return var0 / var2;
      }
   }

   public static long safeDivide(long var0, long var2, RoundingMode var4) {
      if (var0 == Long.MIN_VALUE && var2 == -1L) {
         throw new ArithmeticException("Multiplication overflows a long: " + var0 + " / " + var2);
      } else {
         BigDecimal var5 = new BigDecimal(var0);
         BigDecimal var6 = new BigDecimal(var2);
         return var5.divide(var6, var4).longValue();
      }
   }

   public static int safeToInt(long var0) {
      if (-2147483648L <= var0 && var0 <= 2147483647L) {
         return (int)var0;
      } else {
         throw new ArithmeticException("Value cannot fit in an int: " + var0);
      }
   }

   public static int safeMultiplyToInt(long var0, long var2) {
      long var4 = safeMultiply(var0, var2);
      return safeToInt(var4);
   }

   public static void verifyValueBounds(DateTimeField var0, int var1, int var2, int var3) {
      if (var1 < var2 || var1 > var3) {
         throw new IllegalFieldValueException(var0.getType(), var1, var2, var3);
      }
   }

   public static void verifyValueBounds(DateTimeFieldType var0, int var1, int var2, int var3) {
      if (var1 < var2 || var1 > var3) {
         throw new IllegalFieldValueException(var0, var1, var2, var3);
      }
   }

   public static void verifyValueBounds(String var0, int var1, int var2, int var3) {
      if (var1 < var2 || var1 > var3) {
         throw new IllegalFieldValueException(var0, var1, var2, var3);
      }
   }

   public static int getWrappedValue(int var0, int var1, int var2, int var3) {
      return getWrappedValue(var0 + var1, var2, var3);
   }

   public static int getWrappedValue(int var0, int var1, int var2) {
      if (var1 >= var2) {
         throw new IllegalArgumentException("MIN > MAX");
      } else {
         int var3 = var2 - var1 + 1;
         var0 -= var1;
         if (var0 >= 0) {
            return var0 % var3 + var1;
         } else {
            int var4 = -var0 % var3;
            return var4 == 0 ? 0 + var1 : var3 - var4 + var1;
         }
      }
   }

   public static boolean equals(Object var0, Object var1) {
      if (var0 == var1) {
         return true;
      } else {
         return var0 != null && var1 != null ? var0.equals(var1) : false;
      }
   }
}
