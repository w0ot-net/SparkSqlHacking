package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.lang.Strings;
import java.util.Map;

class DefaultTokenizedJwt implements TokenizedJwt {
   private final CharSequence protectedHeader;
   private final CharSequence payload;
   private final CharSequence digest;

   DefaultTokenizedJwt(CharSequence protectedHeader, CharSequence payload, CharSequence digest) {
      this.protectedHeader = protectedHeader;
      this.payload = payload;
      this.digest = digest;
   }

   public CharSequence getProtected() {
      return this.protectedHeader;
   }

   public CharSequence getPayload() {
      return this.payload;
   }

   public CharSequence getDigest() {
      return this.digest;
   }

   public Header createHeader(Map m) {
      return (Header)(Strings.hasText(this.getDigest()) ? new DefaultJwsHeader(m) : new DefaultHeader(m));
   }
}
