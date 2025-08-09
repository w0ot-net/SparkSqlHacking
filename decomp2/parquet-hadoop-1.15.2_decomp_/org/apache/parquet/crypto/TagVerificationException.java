package org.apache.parquet.crypto;

public class TagVerificationException extends ParquetCryptoRuntimeException {
   private static final long serialVersionUID = 1L;

   public TagVerificationException() {
   }

   public TagVerificationException(String message, Throwable cause) {
      super(message, cause);
   }

   public TagVerificationException(String message) {
      super(message);
   }

   public TagVerificationException(Throwable cause) {
      super(cause);
   }
}
