package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Assert;

class Base64Encoder extends Base64Support implements Encoder {
   Base64Encoder() {
      this(Base64.DEFAULT);
   }

   Base64Encoder(Base64 base64) {
      super(base64);
   }

   public String encode(byte[] bytes) throws EncodingException {
      Assert.notNull(bytes, "byte array argument cannot be null");
      return this.base64.encodeToString(bytes, false);
   }
}
