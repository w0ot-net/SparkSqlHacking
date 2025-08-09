package org.apache.parquet.hadoop;

import org.apache.parquet.ParquetRuntimeException;

public class ParquetEmptyBlockException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public ParquetEmptyBlockException() {
   }

   public ParquetEmptyBlockException(String message, Throwable cause) {
      super(message, cause);
   }

   public ParquetEmptyBlockException(String message) {
      super(message);
   }

   public ParquetEmptyBlockException(Throwable cause) {
      super(cause);
   }
}
