package io.netty.util.internal;

import java.util.Collection;
import java.util.Map;

public final class ObjectUtil {
   private static final float FLOAT_ZERO = 0.0F;
   private static final double DOUBLE_ZERO = (double)0.0F;
   private static final long LONG_ZERO = 0L;
   private static final int INT_ZERO = 0;

   private ObjectUtil() {
   }

   public static Object checkNotNull(Object arg, String text) {
      if (arg == null) {
         throw new NullPointerException(text);
      } else {
         return arg;
      }
   }

   public static Object[] deepCheckNotNull(String text, Object... varargs) {
      if (varargs == null) {
         throw new NullPointerException(text);
      } else {
         for(Object element : varargs) {
            if (element == null) {
               throw new NullPointerException(text);
            }
         }

         return varargs;
      }
   }

   public static Object checkNotNullWithIAE(Object arg, String paramName) throws IllegalArgumentException {
      if (arg == null) {
         throw new IllegalArgumentException("Param '" + paramName + "' must not be null");
      } else {
         return arg;
      }
   }

   public static Object checkNotNullArrayParam(Object value, int index, String name) throws IllegalArgumentException {
      if (value == null) {
         throw new IllegalArgumentException("Array index " + index + " of parameter '" + name + "' must not be null");
      } else {
         return value;
      }
   }

   public static int checkPositive(int i, String name) {
      if (i <= 0) {
         throw new IllegalArgumentException(name + " : " + i + " (expected: > 0)");
      } else {
         return i;
      }
   }

   public static long checkPositive(long l, String name) {
      if (l <= 0L) {
         throw new IllegalArgumentException(name + " : " + l + " (expected: > 0)");
      } else {
         return l;
      }
   }

   public static double checkPositive(double d, String name) {
      if (d <= (double)0.0F) {
         throw new IllegalArgumentException(name + " : " + d + " (expected: > 0)");
      } else {
         return d;
      }
   }

   public static float checkPositive(float f, String name) {
      if (f <= 0.0F) {
         throw new IllegalArgumentException(name + " : " + f + " (expected: > 0)");
      } else {
         return f;
      }
   }

   public static int checkPositiveOrZero(int i, String name) {
      if (i < 0) {
         throw new IllegalArgumentException(name + " : " + i + " (expected: >= 0)");
      } else {
         return i;
      }
   }

   public static long checkPositiveOrZero(long l, String name) {
      if (l < 0L) {
         throw new IllegalArgumentException(name + " : " + l + " (expected: >= 0)");
      } else {
         return l;
      }
   }

   public static double checkPositiveOrZero(double d, String name) {
      if (d < (double)0.0F) {
         throw new IllegalArgumentException(name + " : " + d + " (expected: >= 0)");
      } else {
         return d;
      }
   }

   public static float checkPositiveOrZero(float f, String name) {
      if (f < 0.0F) {
         throw new IllegalArgumentException(name + " : " + f + " (expected: >= 0)");
      } else {
         return f;
      }
   }

   public static int checkInRange(int i, int start, int end, String name) {
      if (i >= start && i <= end) {
         return i;
      } else {
         throw new IllegalArgumentException(name + ": " + i + " (expected: " + start + "-" + end + ")");
      }
   }

   public static long checkInRange(long l, long start, long end, String name) {
      if (l >= start && l <= end) {
         return l;
      } else {
         throw new IllegalArgumentException(name + ": " + l + " (expected: " + start + "-" + end + ")");
      }
   }

   public static Object[] checkNonEmpty(Object[] array, String name) {
      if (((Object[])checkNotNull(array, name)).length == 0) {
         throw new IllegalArgumentException("Param '" + name + "' must not be empty");
      } else {
         return array;
      }
   }

   public static byte[] checkNonEmpty(byte[] array, String name) {
      if (((byte[])checkNotNull(array, name)).length == 0) {
         throw new IllegalArgumentException("Param '" + name + "' must not be empty");
      } else {
         return array;
      }
   }

   public static char[] checkNonEmpty(char[] array, String name) {
      if (((char[])checkNotNull(array, name)).length == 0) {
         throw new IllegalArgumentException("Param '" + name + "' must not be empty");
      } else {
         return array;
      }
   }

   public static Collection checkNonEmpty(Collection collection, String name) {
      if (((Collection)checkNotNull(collection, name)).isEmpty()) {
         throw new IllegalArgumentException("Param '" + name + "' must not be empty");
      } else {
         return collection;
      }
   }

   public static String checkNonEmpty(String value, String name) {
      if (((String)checkNotNull(value, name)).isEmpty()) {
         throw new IllegalArgumentException("Param '" + name + "' must not be empty");
      } else {
         return value;
      }
   }

   public static Map checkNonEmpty(Map value, String name) {
      if (((Map)checkNotNull(value, name)).isEmpty()) {
         throw new IllegalArgumentException("Param '" + name + "' must not be empty");
      } else {
         return value;
      }
   }

   public static CharSequence checkNonEmpty(CharSequence value, String name) {
      if (((CharSequence)checkNotNull(value, name)).length() == 0) {
         throw new IllegalArgumentException("Param '" + name + "' must not be empty");
      } else {
         return value;
      }
   }

   public static String checkNonEmptyAfterTrim(String value, String name) {
      String trimmed = ((String)checkNotNull(value, name)).trim();
      return checkNonEmpty(trimmed, name);
   }

   public static int intValue(Integer wrapper, int defaultValue) {
      return wrapper != null ? wrapper : defaultValue;
   }

   public static long longValue(Long wrapper, long defaultValue) {
      return wrapper != null ? wrapper : defaultValue;
   }
}
