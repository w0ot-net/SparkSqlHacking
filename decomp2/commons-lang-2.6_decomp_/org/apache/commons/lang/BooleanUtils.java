package org.apache.commons.lang;

public class BooleanUtils {
   public static Boolean negate(Boolean bool) {
      if (bool == null) {
         return null;
      } else {
         return bool ? Boolean.FALSE : Boolean.TRUE;
      }
   }

   public static boolean isTrue(Boolean bool) {
      if (bool == null) {
         return false;
      } else {
         return bool;
      }
   }

   public static boolean isNotTrue(Boolean bool) {
      return !isTrue(bool);
   }

   public static boolean isFalse(Boolean bool) {
      if (bool == null) {
         return false;
      } else {
         return !bool;
      }
   }

   public static boolean isNotFalse(Boolean bool) {
      return !isFalse(bool);
   }

   public static Boolean toBooleanObject(boolean bool) {
      return bool ? Boolean.TRUE : Boolean.FALSE;
   }

   public static boolean toBoolean(Boolean bool) {
      if (bool == null) {
         return false;
      } else {
         return bool;
      }
   }

   public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull) {
      if (bool == null) {
         return valueIfNull;
      } else {
         return bool;
      }
   }

   public static boolean toBoolean(int value) {
      return value != 0;
   }

   public static Boolean toBooleanObject(int value) {
      return value == 0 ? Boolean.FALSE : Boolean.TRUE;
   }

   public static Boolean toBooleanObject(Integer value) {
      if (value == null) {
         return null;
      } else {
         return value == 0 ? Boolean.FALSE : Boolean.TRUE;
      }
   }

   public static boolean toBoolean(int value, int trueValue, int falseValue) {
      if (value == trueValue) {
         return true;
      } else if (value == falseValue) {
         return false;
      } else {
         throw new IllegalArgumentException("The Integer did not match either specified value");
      }
   }

   public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue) {
      if (value == null) {
         if (trueValue == null) {
            return true;
         }

         if (falseValue == null) {
            return false;
         }
      } else {
         if (value.equals(trueValue)) {
            return true;
         }

         if (value.equals(falseValue)) {
            return false;
         }
      }

      throw new IllegalArgumentException("The Integer did not match either specified value");
   }

   public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue) {
      if (value == trueValue) {
         return Boolean.TRUE;
      } else if (value == falseValue) {
         return Boolean.FALSE;
      } else if (value == nullValue) {
         return null;
      } else {
         throw new IllegalArgumentException("The Integer did not match any specified value");
      }
   }

   public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue) {
      if (value == null) {
         if (trueValue == null) {
            return Boolean.TRUE;
         }

         if (falseValue == null) {
            return Boolean.FALSE;
         }

         if (nullValue == null) {
            return null;
         }
      } else {
         if (value.equals(trueValue)) {
            return Boolean.TRUE;
         }

         if (value.equals(falseValue)) {
            return Boolean.FALSE;
         }

         if (value.equals(nullValue)) {
            return null;
         }
      }

      throw new IllegalArgumentException("The Integer did not match any specified value");
   }

   public static int toInteger(boolean bool) {
      return bool ? 1 : 0;
   }

   public static Integer toIntegerObject(boolean bool) {
      return bool ? org.apache.commons.lang.math.NumberUtils.INTEGER_ONE : org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;
   }

   public static Integer toIntegerObject(Boolean bool) {
      if (bool == null) {
         return null;
      } else {
         return bool ? org.apache.commons.lang.math.NumberUtils.INTEGER_ONE : org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;
      }
   }

   public static int toInteger(boolean bool, int trueValue, int falseValue) {
      return bool ? trueValue : falseValue;
   }

   public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue) {
      if (bool == null) {
         return nullValue;
      } else {
         return bool ? trueValue : falseValue;
      }
   }

   public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue) {
      return bool ? trueValue : falseValue;
   }

   public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue) {
      if (bool == null) {
         return nullValue;
      } else {
         return bool ? trueValue : falseValue;
      }
   }

   public static Boolean toBooleanObject(String str) {
      if (str == "true") {
         return Boolean.TRUE;
      } else if (str == null) {
         return null;
      } else {
         switch (str.length()) {
            case 1:
               char ch0 = str.charAt(0);
               if (ch0 == 'y' || ch0 == 'Y' || ch0 == 't' || ch0 == 'T') {
                  return Boolean.TRUE;
               }

               if (ch0 == 'n' || ch0 == 'N' || ch0 == 'f' || ch0 == 'F') {
                  return Boolean.FALSE;
               }
               break;
            case 2:
               char ch0 = str.charAt(0);
               char ch1 = str.charAt(1);
               if ((ch0 == 'o' || ch0 == 'O') && (ch1 == 'n' || ch1 == 'N')) {
                  return Boolean.TRUE;
               }

               if ((ch0 == 'n' || ch0 == 'N') && (ch1 == 'o' || ch1 == 'O')) {
                  return Boolean.FALSE;
               }
               break;
            case 3:
               char ch0 = str.charAt(0);
               char ch1 = str.charAt(1);
               char ch2 = str.charAt(2);
               if ((ch0 == 'y' || ch0 == 'Y') && (ch1 == 'e' || ch1 == 'E') && (ch2 == 's' || ch2 == 'S')) {
                  return Boolean.TRUE;
               }

               if ((ch0 == 'o' || ch0 == 'O') && (ch1 == 'f' || ch1 == 'F') && (ch2 == 'f' || ch2 == 'F')) {
                  return Boolean.FALSE;
               }
               break;
            case 4:
               char ch0 = str.charAt(0);
               char ch1 = str.charAt(1);
               char ch2 = str.charAt(2);
               char ch3 = str.charAt(3);
               if ((ch0 == 't' || ch0 == 'T') && (ch1 == 'r' || ch1 == 'R') && (ch2 == 'u' || ch2 == 'U') && (ch3 == 'e' || ch3 == 'E')) {
                  return Boolean.TRUE;
               }
               break;
            case 5:
               char ch0 = str.charAt(0);
               char ch1 = str.charAt(1);
               char ch2 = str.charAt(2);
               char ch3 = str.charAt(3);
               char ch4 = str.charAt(4);
               if ((ch0 == 'f' || ch0 == 'F') && (ch1 == 'a' || ch1 == 'A') && (ch2 == 'l' || ch2 == 'L') && (ch3 == 's' || ch3 == 'S') && (ch4 == 'e' || ch4 == 'E')) {
                  return Boolean.FALSE;
               }
         }

         return null;
      }
   }

   public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString) {
      if (str == null) {
         if (trueString == null) {
            return Boolean.TRUE;
         }

         if (falseString == null) {
            return Boolean.FALSE;
         }

         if (nullString == null) {
            return null;
         }
      } else {
         if (str.equals(trueString)) {
            return Boolean.TRUE;
         }

         if (str.equals(falseString)) {
            return Boolean.FALSE;
         }

         if (str.equals(nullString)) {
            return null;
         }
      }

      throw new IllegalArgumentException("The String did not match any specified value");
   }

   public static boolean toBoolean(String str) {
      return toBoolean(toBooleanObject(str));
   }

   public static boolean toBoolean(String str, String trueString, String falseString) {
      if (str == null) {
         if (trueString == null) {
            return true;
         }

         if (falseString == null) {
            return false;
         }
      } else {
         if (str.equals(trueString)) {
            return true;
         }

         if (str.equals(falseString)) {
            return false;
         }
      }

      throw new IllegalArgumentException("The String did not match either specified value");
   }

   public static String toStringTrueFalse(Boolean bool) {
      return toString(bool, "true", "false", (String)null);
   }

   public static String toStringOnOff(Boolean bool) {
      return toString(bool, "on", "off", (String)null);
   }

   public static String toStringYesNo(Boolean bool) {
      return toString(bool, "yes", "no", (String)null);
   }

   public static String toString(Boolean bool, String trueString, String falseString, String nullString) {
      if (bool == null) {
         return nullString;
      } else {
         return bool ? trueString : falseString;
      }
   }

   public static String toStringTrueFalse(boolean bool) {
      return toString(bool, "true", "false");
   }

   public static String toStringOnOff(boolean bool) {
      return toString(bool, "on", "off");
   }

   public static String toStringYesNo(boolean bool) {
      return toString(bool, "yes", "no");
   }

   public static String toString(boolean bool, String trueString, String falseString) {
      return bool ? trueString : falseString;
   }

   public static boolean xor(boolean[] array) {
      if (array == null) {
         throw new IllegalArgumentException("The Array must not be null");
      } else if (array.length == 0) {
         throw new IllegalArgumentException("Array is empty");
      } else {
         int trueCount = 0;

         for(int i = 0; i < array.length; ++i) {
            if (array[i]) {
               if (trueCount >= 1) {
                  return false;
               }

               ++trueCount;
            }
         }

         return trueCount == 1;
      }
   }

   public static Boolean xor(Boolean[] array) {
      if (array == null) {
         throw new IllegalArgumentException("The Array must not be null");
      } else if (array.length == 0) {
         throw new IllegalArgumentException("Array is empty");
      } else {
         boolean[] primitive = null;

         try {
            primitive = ArrayUtils.toPrimitive(array);
         } catch (NullPointerException var3) {
            throw new IllegalArgumentException("The array must not contain any null elements");
         }

         return xor(primitive) ? Boolean.TRUE : Boolean.FALSE;
      }
   }
}
