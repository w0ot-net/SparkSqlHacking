package io.jsonwebtoken.io;

public class DecodingException extends CodecException {
   public DecodingException(String message) {
      super(message);
   }

   public DecodingException(String message, Throwable cause) {
      super(message, cause);
   }
}
