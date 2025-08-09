package com.univocity.parsers.common;

public class DataValidationException extends DataProcessingException {
   private static final long serialVersionUID = 3110975527111918123L;

   public DataValidationException(String message) {
      super(message, -1, (Object[])null, (Throwable)null);
   }

   public DataValidationException(String message, Throwable cause) {
      super(message, -1, (Object[])null, cause);
   }

   public DataValidationException(String message, Object[] row) {
      super(message, -1, row, (Throwable)null);
   }

   public DataValidationException(String message, Object[] row, Throwable cause) {
      super(message, -1, row, cause);
   }

   public DataValidationException(String message, int columnIndex) {
      super(message, columnIndex, (Object[])null, (Throwable)null);
   }

   protected String getErrorDescription() {
      return "Error validating parsed input";
   }
}
