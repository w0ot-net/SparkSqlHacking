package io.jsonwebtoken.impl;

/** @deprecated */
@Deprecated
public interface TextCodec {
   /** @deprecated */
   @Deprecated
   TextCodec BASE64 = new Base64Codec();
   /** @deprecated */
   @Deprecated
   TextCodec BASE64URL = new Base64UrlCodec();

   String encode(String var1);

   String encode(byte[] var1);

   byte[] decode(String var1);

   String decodeToString(String var1);
}
