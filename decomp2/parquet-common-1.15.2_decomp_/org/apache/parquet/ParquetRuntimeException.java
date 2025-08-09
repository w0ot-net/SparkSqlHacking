package org.apache.parquet;

public abstract class ParquetRuntimeException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public ParquetRuntimeException() {
   }

   public ParquetRuntimeException(String message, Throwable cause) {
      super(message, cause);
   }

   public ParquetRuntimeException(String message) {
      super(message);
   }

   public ParquetRuntimeException(Throwable cause) {
      super(cause);
   }
}
