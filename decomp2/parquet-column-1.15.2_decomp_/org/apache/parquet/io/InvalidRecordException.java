package org.apache.parquet.io;

import org.apache.parquet.ParquetRuntimeException;

public class InvalidRecordException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public InvalidRecordException() {
   }

   public InvalidRecordException(String message, Throwable cause) {
      super(message, cause);
   }

   public InvalidRecordException(String message) {
      super(message);
   }

   public InvalidRecordException(Throwable cause) {
      super(cause);
   }
}
