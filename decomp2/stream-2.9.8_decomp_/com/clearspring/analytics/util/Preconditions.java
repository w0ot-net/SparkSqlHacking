package com.clearspring.analytics.util;

public class Preconditions {
   public static void checkState(boolean condition, String msg) {
      if (!condition) {
         throw new IllegalStateException(msg);
      }
   }

   public static void checkArgument(boolean condition) {
      if (!condition) {
         throw new IllegalArgumentException();
      }
   }

   public static void checkState(boolean condition) {
      if (!condition) {
         throw new IllegalStateException();
      }
   }

   public static void checkArgument(boolean condition, String format, Object... args) {
      if (!condition) {
         throw new IllegalArgumentException(String.format(format, args));
      }
   }

   public static void checkState(boolean condition, String format, Object... args) {
      if (!condition) {
         throw new IllegalStateException(String.format(format, args));
      }
   }
}
