package io.jsonwebtoken.lang;

import java.util.Collection;
import java.util.Map;

public final class Assert {
   private Assert() {
   }

   public static void isTrue(boolean expression, String message) {
      if (!expression) {
         throw new IllegalArgumentException(message);
      }
   }

   public static void isTrue(boolean expression) {
      isTrue(expression, "[Assertion failed] - this expression must be true");
   }

   public static void isNull(Object object, String message) {
      if (object != null) {
         throw new IllegalArgumentException(message);
      }
   }

   public static void isNull(Object object) {
      isNull(object, "[Assertion failed] - the object argument must be null");
   }

   public static Object notNull(Object object, String message) {
      if (object == null) {
         throw new IllegalArgumentException(message);
      } else {
         return object;
      }
   }

   public static void notNull(Object object) {
      notNull(object, "[Assertion failed] - this argument is required; it must not be null");
   }

   public static void hasLength(String text, String message) {
      if (!Strings.hasLength(text)) {
         throw new IllegalArgumentException(message);
      }
   }

   public static void hasLength(String text) {
      hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
   }

   public static CharSequence hasText(CharSequence text, String message) {
      if (!Strings.hasText(text)) {
         throw new IllegalArgumentException(message);
      } else {
         return text;
      }
   }

   public static void hasText(String text) {
      hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
   }

   public static void doesNotContain(String textToSearch, String substring, String message) {
      if (Strings.hasLength(textToSearch) && Strings.hasLength(substring) && textToSearch.indexOf(substring) != -1) {
         throw new IllegalArgumentException(message);
      }
   }

   public static void doesNotContain(String textToSearch, String substring) {
      doesNotContain(textToSearch, substring, "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
   }

   public static Object[] notEmpty(Object[] array, String message) {
      if (Objects.isEmpty(array)) {
         throw new IllegalArgumentException(message);
      } else {
         return array;
      }
   }

   public static void notEmpty(Object[] array) {
      notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
   }

   public static byte[] notEmpty(byte[] array, String msg) {
      if (Objects.isEmpty(array)) {
         throw new IllegalArgumentException(msg);
      } else {
         return array;
      }
   }

   public static char[] notEmpty(char[] chars, String msg) {
      if (Objects.isEmpty(chars)) {
         throw new IllegalArgumentException(msg);
      } else {
         return chars;
      }
   }

   public static void noNullElements(Object[] array, String message) {
      if (array != null) {
         for(int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
               throw new IllegalArgumentException(message);
            }
         }
      }

   }

   public static void noNullElements(Object[] array) {
      noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
   }

   public static Collection notEmpty(Collection collection, String message) {
      if (Collections.isEmpty(collection)) {
         throw new IllegalArgumentException(message);
      } else {
         return collection;
      }
   }

   public static void notEmpty(Collection collection) {
      notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
   }

   public static Map notEmpty(Map map, String message) {
      if (Collections.isEmpty(map)) {
         throw new IllegalArgumentException(message);
      } else {
         return map;
      }
   }

   public static void notEmpty(Map map) {
      notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
   }

   public static Object isInstanceOf(Class clazz, Object obj) {
      return isInstanceOf(clazz, obj, "");
   }

   public static Object isInstanceOf(Class type, Object obj, String message) {
      notNull(type, "Type to check against must not be null");
      if (!type.isInstance(obj)) {
         throw new IllegalArgumentException(message + "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
      } else {
         return type.cast(obj);
      }
   }

   public static Object stateIsInstance(Class type, Object obj, String message) {
      notNull(type, "Type to check cannot be null.");
      if (!type.isInstance(obj)) {
         String msg = message + "Object of class [" + Objects.nullSafeClassName(obj) + "] must be an instance of " + type;
         throw new IllegalStateException(msg);
      } else {
         return type.cast(obj);
      }
   }

   public static void isAssignable(Class superType, Class subType) {
      isAssignable(superType, subType, "");
   }

   public static void isAssignable(Class superType, Class subType, String message) {
      notNull(superType, "Type to check against must not be null");
      if (subType == null || !superType.isAssignableFrom(subType)) {
         throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
      }
   }

   public static Comparable eq(Comparable value, Comparable requirement, String msg) {
      if (compareTo(value, requirement) != 0) {
         throw new IllegalArgumentException(msg);
      } else {
         return value;
      }
   }

   private static int compareTo(Comparable value, Comparable requirement) {
      notNull(value, "value cannot be null.");
      notNull(requirement, "requirement cannot be null.");
      return value.compareTo(requirement);
   }

   public static Comparable gt(Comparable value, Comparable requirement, String msg) {
      if (compareTo(value, requirement) <= 0) {
         throw new IllegalArgumentException(msg);
      } else {
         return value;
      }
   }

   public static Comparable lte(Comparable value, Comparable requirement, String msg) {
      if (compareTo(value, requirement) > 0) {
         throw new IllegalArgumentException(msg);
      } else {
         return value;
      }
   }

   public static void state(boolean expression, String message) {
      if (!expression) {
         throw new IllegalStateException(message);
      }
   }

   public static void state(boolean expression) {
      state(expression, "[Assertion failed] - this state invariant must be true");
   }

   public static Object stateNotNull(Object value, String msg) throws IllegalStateException {
      if (value == null) {
         throw new IllegalStateException(msg);
      } else {
         return value;
      }
   }
}
