package io.jsonwebtoken.impl;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;

/** @deprecated */
@Deprecated
public class Base64Codec extends AbstractTextCodec {
   public String encode(byte[] data) {
      return (String)Encoders.BASE64.encode(data);
   }

   public byte[] decode(String encoded) {
      return (byte[])Decoders.BASE64.decode(encoded);
   }
}
