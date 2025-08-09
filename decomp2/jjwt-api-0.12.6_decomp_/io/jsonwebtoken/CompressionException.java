package io.jsonwebtoken;

import io.jsonwebtoken.io.IOException;

public class CompressionException extends IOException {
   public CompressionException(String message) {
      super(message);
   }

   public CompressionException(String message, Throwable cause) {
      super(message, cause);
   }
}
