package org.apache.arrow.vector.util;

public class SchemaChangeRuntimeException extends RuntimeException {
   public SchemaChangeRuntimeException() {
   }

   public SchemaChangeRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public SchemaChangeRuntimeException(String message, Throwable cause) {
      super(message, cause);
   }

   public SchemaChangeRuntimeException(String message) {
      super(message);
   }

   public SchemaChangeRuntimeException(Throwable cause) {
      super(cause);
   }
}
