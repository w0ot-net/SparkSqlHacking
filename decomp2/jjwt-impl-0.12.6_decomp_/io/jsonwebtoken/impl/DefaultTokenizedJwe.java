package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import java.util.Map;

class DefaultTokenizedJwe extends DefaultTokenizedJwt implements TokenizedJwe {
   private final CharSequence encryptedKey;
   private final CharSequence iv;

   DefaultTokenizedJwe(CharSequence protectedHeader, CharSequence body, CharSequence digest, CharSequence encryptedKey, CharSequence iv) {
      super(protectedHeader, body, digest);
      this.encryptedKey = encryptedKey;
      this.iv = iv;
   }

   public CharSequence getEncryptedKey() {
      return this.encryptedKey;
   }

   public CharSequence getIv() {
      return this.iv;
   }

   public Header createHeader(Map m) {
      return new DefaultJweHeader(m);
   }
}
