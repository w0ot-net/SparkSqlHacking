package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.http.RequestOptions;
import io.vertx.ext.auth.impl.Codec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

public class CacheKey extends CacheVariationsKey {
   private final String variations;

   public CacheKey(RequestOptions request, Vary vary) {
      super(request);
      this.variations = vary.toString();
   }

   public String toString() {
      try {
         MessageDigest digest = MessageDigest.getInstance("SHA-256");
         digest.update(super.toString().getBytes(StandardCharsets.UTF_8));
         digest.update(this.variations.getBytes(StandardCharsets.UTF_8));
         byte[] hashed = digest.digest();
         return Codec.base16Encode(hashed);
      } catch (Exception var3) {
         return super.toString() + "|" + this.variations;
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CacheKey cacheKey = (CacheKey)o;
         return this.port == cacheKey.port && this.host.equals(cacheKey.host) && this.path.equals(cacheKey.path) && this.queryString.equals(cacheKey.queryString) && this.variations.equals(cacheKey.variations);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.host, this.port, this.path, this.queryString, this.variations});
   }
}
