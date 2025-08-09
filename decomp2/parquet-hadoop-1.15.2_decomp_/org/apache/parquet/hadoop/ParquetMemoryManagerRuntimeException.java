package org.apache.parquet.hadoop;

import org.apache.parquet.ParquetRuntimeException;

public class ParquetMemoryManagerRuntimeException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public ParquetMemoryManagerRuntimeException(String message) {
      super(message);
   }
}
