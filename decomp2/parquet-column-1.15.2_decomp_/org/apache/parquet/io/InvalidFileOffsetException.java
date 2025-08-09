package org.apache.parquet.io;

import org.apache.parquet.ParquetRuntimeException;

public class InvalidFileOffsetException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public InvalidFileOffsetException(String message) {
      super(message);
   }
}
