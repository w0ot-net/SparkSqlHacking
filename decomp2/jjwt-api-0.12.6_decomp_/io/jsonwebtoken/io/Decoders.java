package io.jsonwebtoken.io;

public final class Decoders {
   public static final Decoder BASE64 = new ExceptionPropagatingDecoder(new Base64Decoder());
   public static final Decoder BASE64URL = new ExceptionPropagatingDecoder(new Base64UrlDecoder());

   private Decoders() {
   }
}
