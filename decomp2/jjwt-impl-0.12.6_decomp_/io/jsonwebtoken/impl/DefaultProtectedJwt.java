package io.jsonwebtoken.impl;

import io.jsonwebtoken.ProtectedHeader;
import io.jsonwebtoken.ProtectedJwt;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import java.security.MessageDigest;

abstract class DefaultProtectedJwt extends DefaultJwt implements ProtectedJwt {
   protected final byte[] digest;
   private final String digestName;

   protected DefaultProtectedJwt(ProtectedHeader header, Object payload, byte[] digest, String digestName) {
      super(header, payload);
      this.digest = Assert.notEmpty(digest, "Digest byte array cannot be null or empty.");
      this.digestName = (String)Assert.hasText(digestName, "digestName cannot be null or empty.");
   }

   public byte[] getDigest() {
      return (byte[])this.digest.clone();
   }

   protected StringBuilder toStringBuilder() {
      String b64Url = (String)Encoders.BASE64URL.encode(this.digest);
      return super.toStringBuilder().append(',').append(this.digestName).append('=').append(b64Url);
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof DefaultProtectedJwt)) {
         return false;
      } else {
         DefaultProtectedJwt<?, ?> pjwt = (DefaultProtectedJwt)obj;
         return super.equals(pjwt) && MessageDigest.isEqual(this.digest, pjwt.digest);
      }
   }

   public int hashCode() {
      return Objects.nullSafeHashCode(new Object[]{this.getHeader(), this.getPayload(), this.digest});
   }
}
