package io.jsonwebtoken.io;

public class CodecException extends IOException {
   public CodecException(String message) {
      super(message);
   }

   public CodecException(String message, Throwable cause) {
      super(message, cause);
   }
}
