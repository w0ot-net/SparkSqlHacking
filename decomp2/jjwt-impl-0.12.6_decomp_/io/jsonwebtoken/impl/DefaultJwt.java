package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtVisitor;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;

public class DefaultJwt implements Jwt {
   private final Header header;
   private final Object payload;

   public DefaultJwt(Header header, Object payload) {
      this.header = (Header)Assert.notNull(header, "header cannot be null.");
      this.payload = Assert.notNull(payload, "payload cannot be null.");
   }

   public Header getHeader() {
      return this.header;
   }

   public Object getBody() {
      return this.getPayload();
   }

   public Object getPayload() {
      return this.payload;
   }

   protected StringBuilder toStringBuilder() {
      StringBuilder sb = new StringBuilder(100);
      sb.append("header=").append(this.header).append(",payload=");
      if (this.payload instanceof byte[]) {
         String encoded = (String)Encoders.BASE64URL.encode((byte[])this.payload);
         sb.append(encoded);
      } else {
         sb.append(this.payload);
      }

      return sb;
   }

   public final String toString() {
      return this.toStringBuilder().toString();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Jwt)) {
         return false;
      } else {
         Jwt<?, ?> jwt = (Jwt)obj;
         return Objects.nullSafeEquals(this.header, jwt.getHeader()) && Objects.nullSafeEquals(this.payload, jwt.getPayload());
      }
   }

   public int hashCode() {
      return Objects.nullSafeHashCode(new Object[]{this.header, this.payload});
   }

   public Object accept(JwtVisitor v) {
      return v.visit(this);
   }
}
