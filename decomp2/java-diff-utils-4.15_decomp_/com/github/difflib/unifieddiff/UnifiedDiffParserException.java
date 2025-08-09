package com.github.difflib.unifieddiff;

public class UnifiedDiffParserException extends RuntimeException {
   public UnifiedDiffParserException() {
   }

   public UnifiedDiffParserException(String message) {
      super(message);
   }

   public UnifiedDiffParserException(String message, Throwable cause) {
      super(message, cause);
   }

   public UnifiedDiffParserException(Throwable cause) {
      super(cause);
   }

   public UnifiedDiffParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
