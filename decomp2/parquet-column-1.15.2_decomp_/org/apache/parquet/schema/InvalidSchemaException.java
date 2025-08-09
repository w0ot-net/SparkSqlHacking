package org.apache.parquet.schema;

import org.apache.parquet.ParquetRuntimeException;

public class InvalidSchemaException extends ParquetRuntimeException {
   public InvalidSchemaException(String message, Throwable cause) {
      super(message, cause);
   }

   public InvalidSchemaException(String message) {
      super(message);
   }
}
