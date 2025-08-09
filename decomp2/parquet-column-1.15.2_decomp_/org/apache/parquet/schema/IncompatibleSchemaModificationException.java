package org.apache.parquet.schema;

import org.apache.parquet.ParquetRuntimeException;

public class IncompatibleSchemaModificationException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public IncompatibleSchemaModificationException() {
   }

   public IncompatibleSchemaModificationException(String message, Throwable cause) {
      super(message, cause);
   }

   public IncompatibleSchemaModificationException(String message) {
      super(message);
   }

   public IncompatibleSchemaModificationException(Throwable cause) {
      super(cause);
   }
}
