package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.HashAlgorithm;
import io.jsonwebtoken.security.JwkThumbprint;
import java.net.URI;
import java.security.MessageDigest;

class DefaultJwkThumbprint implements JwkThumbprint {
   private static final String URI_PREFIX = "urn:ietf:params:oauth:jwk-thumbprint:";
   private final byte[] digest;
   private final HashAlgorithm alg;
   private final URI uri;
   private final int hashcode;
   private final String sval;

   DefaultJwkThumbprint(byte[] digest, HashAlgorithm alg) {
      this.digest = Assert.notEmpty(digest, "Thumbprint digest byte array cannot be null or empty.");
      this.alg = (HashAlgorithm)Assert.notNull(alg, "Thumbprint HashAlgorithm cannot be null.");
      String id = (String)Assert.hasText(Strings.clean(alg.getId()), "Thumbprint HashAlgorithm id cannot be null or empty.");
      String base64Url = (String)Encoders.BASE64URL.encode(digest);
      String s = "urn:ietf:params:oauth:jwk-thumbprint:" + id + ":" + base64Url;
      this.uri = URI.create(s);
      this.hashcode = Objects.nullSafeHashCode(new Object[]{this.digest, this.alg});
      this.sval = (String)Encoders.BASE64URL.encode(digest);
   }

   public HashAlgorithm getHashAlgorithm() {
      return this.alg;
   }

   public byte[] toByteArray() {
      return (byte[])this.digest.clone();
   }

   public URI toURI() {
      return this.uri;
   }

   public String toString() {
      return this.sval;
   }

   public int hashCode() {
      return this.hashcode;
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof DefaultJwkThumbprint)) {
         return false;
      } else {
         DefaultJwkThumbprint other = (DefaultJwkThumbprint)obj;
         return this.alg.equals(other.alg) && MessageDigest.isEqual(this.digest, other.digest);
      }
   }
}
