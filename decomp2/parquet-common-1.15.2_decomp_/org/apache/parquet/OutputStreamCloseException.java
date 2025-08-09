package org.apache.parquet;

public class OutputStreamCloseException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public OutputStreamCloseException() {
   }

   public OutputStreamCloseException(String message, Throwable cause) {
      super(message, cause);
   }

   public OutputStreamCloseException(String message) {
      super(message);
   }

   public OutputStreamCloseException(Throwable cause) {
      super(cause);
   }
}
