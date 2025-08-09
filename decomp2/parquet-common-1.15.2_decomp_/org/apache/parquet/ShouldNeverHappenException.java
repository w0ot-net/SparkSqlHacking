package org.apache.parquet;

public class ShouldNeverHappenException extends ParquetRuntimeException {
   public ShouldNeverHappenException() {
   }

   public ShouldNeverHappenException(String message, Throwable cause) {
      super(message, cause);
   }

   public ShouldNeverHappenException(String message) {
      super(message);
   }

   public ShouldNeverHappenException(Throwable cause) {
      super(cause);
   }
}
