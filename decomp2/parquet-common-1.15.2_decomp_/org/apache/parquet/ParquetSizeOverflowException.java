package org.apache.parquet;

public class ParquetSizeOverflowException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public ParquetSizeOverflowException() {
   }

   public ParquetSizeOverflowException(String message) {
      super(message);
   }
}
