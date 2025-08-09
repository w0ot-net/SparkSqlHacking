package io.jsonwebtoken.impl;

import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.JwtVisitor;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import java.security.MessageDigest;

public class DefaultJwe extends DefaultProtectedJwt implements Jwe {
   private static final String DIGEST_NAME = "tag";
   private final byte[] iv;

   public DefaultJwe(JweHeader header, Object payload, byte[] iv, byte[] aadTag) {
      super(header, payload, aadTag, "tag");
      this.iv = Assert.notEmpty(iv, "Initialization vector cannot be null or empty.");
   }

   public byte[] getInitializationVector() {
      return (byte[])this.iv.clone();
   }

   protected StringBuilder toStringBuilder() {
      return super.toStringBuilder().append(",iv=").append((String)Encoders.BASE64URL.encode(this.iv));
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Jwe)) {
         return false;
      } else {
         Jwe<?> jwe = (Jwe)obj;
         return super.equals(jwe) && MessageDigest.isEqual(this.iv, jwe.getInitializationVector());
      }
   }

   public int hashCode() {
      return Objects.nullSafeHashCode(new Object[]{this.getHeader(), this.getPayload(), this.iv, this.digest});
   }

   public Object accept(JwtVisitor v) {
      return v.visit(this);
   }
}
