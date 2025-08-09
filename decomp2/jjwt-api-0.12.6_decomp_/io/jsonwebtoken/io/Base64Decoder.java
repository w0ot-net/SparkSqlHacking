package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Assert;

class Base64Decoder extends Base64Support implements Decoder {
   Base64Decoder() {
      super(Base64.DEFAULT);
   }

   Base64Decoder(Base64 base64) {
      super(base64);
   }

   public byte[] decode(CharSequence s) throws DecodingException {
      Assert.notNull(s, "String argument cannot be null");
      return this.base64.decodeFast(s);
   }
}
