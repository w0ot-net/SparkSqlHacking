package io.vertx.core.impl;

public class Arguments {
   public static void require(boolean condition, String message) {
      if (!condition) {
         throw new IllegalArgumentException(message);
      }
   }

   public static void requireInRange(int number, int min, int max, String message) {
      if (number < min || number > max) {
         throw new IllegalArgumentException(message);
      }
   }
}
