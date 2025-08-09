package org.apache.arrow.vector.validate;

public class ValidateUtil {
   private ValidateUtil() {
   }

   public static void validateOrThrow(boolean expression, String errorMessage) {
      if (!expression) {
         throw new ValidateException(errorMessage);
      }
   }

   public static void validateOrThrow(boolean expression, String errorMessage, Object... args) {
      if (!expression) {
         throw new ValidateException(String.format(errorMessage, args));
      }
   }

   public static class ValidateException extends RuntimeException {
      public ValidateException(String message) {
         super(message);
      }
   }
}
