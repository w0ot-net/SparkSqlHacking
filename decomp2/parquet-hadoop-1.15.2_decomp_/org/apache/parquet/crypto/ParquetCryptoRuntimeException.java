package org.apache.parquet.crypto;

import org.apache.parquet.ParquetRuntimeException;

public class ParquetCryptoRuntimeException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;

   public ParquetCryptoRuntimeException() {
   }

   public ParquetCryptoRuntimeException(String message, Throwable cause) {
      super(message, cause);
   }

   public ParquetCryptoRuntimeException(String message) {
      super(message);
   }

   public ParquetCryptoRuntimeException(Throwable cause) {
      super(cause);
   }
}
