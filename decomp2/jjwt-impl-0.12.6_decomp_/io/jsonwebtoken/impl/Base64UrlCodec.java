package io.jsonwebtoken.impl;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;

/** @deprecated */
@Deprecated
public class Base64UrlCodec extends AbstractTextCodec {
   public String encode(byte[] data) {
      return (String)Encoders.BASE64URL.encode(data);
   }

   public byte[] decode(String encoded) {
      return (byte[])Decoders.BASE64URL.decode(encoded);
   }
}
