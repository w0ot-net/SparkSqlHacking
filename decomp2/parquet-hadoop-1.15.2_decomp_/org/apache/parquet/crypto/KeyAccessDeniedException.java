package org.apache.parquet.crypto;

public class KeyAccessDeniedException extends ParquetCryptoRuntimeException {
   private static final long serialVersionUID = 1L;

   public KeyAccessDeniedException() {
   }

   public KeyAccessDeniedException(String message, Throwable cause) {
      super(message, cause);
   }

   public KeyAccessDeniedException(String message) {
      super(message);
   }

   public KeyAccessDeniedException(Throwable cause) {
      super(cause);
   }
}
