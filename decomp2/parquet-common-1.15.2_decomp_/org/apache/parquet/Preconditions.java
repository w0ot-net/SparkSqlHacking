package org.apache.parquet;

public final class Preconditions {
   private Preconditions() {
   }

   /** @deprecated */
   @Deprecated
   public static Object checkNotNull(Object o, String name) throws NullPointerException {
      if (o == null) {
         throw new NullPointerException(name + " should not be null");
      } else {
         return o;
      }
   }

   public static void checkArgument(boolean isValid, String message) throws IllegalArgumentException {
      if (!isValid) {
         throw new IllegalArgumentException(message);
      }
   }

   public static void checkArgument(boolean isValid, String message, Object arg0) throws IllegalArgumentException {
      if (!isValid) {
         throw new IllegalArgumentException(String.format(String.valueOf(message), strings(arg0)));
      }
   }

   public static void checkArgument(boolean isValid, String message, Object arg0, Object arg1) throws IllegalArgumentException {
      if (!isValid) {
         throw new IllegalArgumentException(String.format(String.valueOf(message), strings(arg0, arg1)));
      }
   }

   public static void checkArgument(boolean isValid, String message, Object arg0, Object arg1, Object arg2) throws IllegalArgumentException {
      if (!isValid) {
         throw new IllegalArgumentException(String.format(String.valueOf(message), strings(arg0, arg1, arg2)));
      }
   }

   public static void checkArgument(boolean isValid, String message, Object... args) throws IllegalArgumentException {
      if (!isValid) {
         throw new IllegalArgumentException(String.format(String.valueOf(message), strings(args)));
      }
   }

   public static void checkState(boolean isValid, String message) throws IllegalStateException {
      if (!isValid) {
         throw new IllegalStateException(message);
      }
   }

   public static void checkState(boolean isValid, String message, Object arg0) throws IllegalStateException {
      if (!isValid) {
         throw new IllegalStateException(String.format(String.valueOf(message), strings(arg0)));
      }
   }

   public static void checkState(boolean isValid, String message, Object arg0, Object arg1) throws IllegalStateException {
      if (!isValid) {
         throw new IllegalStateException(String.format(String.valueOf(message), strings(arg0, arg1)));
      }
   }

   public static void checkState(boolean isValid, String message, Object arg0, Object arg1, Object arg2) throws IllegalStateException {
      if (!isValid) {
         throw new IllegalStateException(String.format(String.valueOf(message), strings(arg0, arg1, arg2)));
      }
   }

   public static void checkState(boolean isValid, String message, Object... args) throws IllegalStateException {
      if (!isValid) {
         throw new IllegalStateException(String.format(String.valueOf(message), strings(args)));
      }
   }

   private static String[] strings(Object... objects) {
      String[] strings = new String[objects.length];

      for(int i = 0; i < objects.length; ++i) {
         strings[i] = String.valueOf(objects[i]);
      }

      return strings;
   }
}
