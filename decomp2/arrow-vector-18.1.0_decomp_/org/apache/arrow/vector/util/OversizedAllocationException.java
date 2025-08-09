package org.apache.arrow.vector.util;

public class OversizedAllocationException extends RuntimeException {
   public OversizedAllocationException() {
   }

   public OversizedAllocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public OversizedAllocationException(String message, Throwable cause) {
      super(message, cause);
   }

   public OversizedAllocationException(String message) {
      super(message);
   }

   public OversizedAllocationException(Throwable cause) {
      super(cause);
   }
}
