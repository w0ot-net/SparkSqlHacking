package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.KeyOperation;
import java.util.Set;

final class DefaultKeyOperation implements KeyOperation {
   private static final String CUSTOM_DESCRIPTION = "Custom key operation";
   static final KeyOperation SIGN = of("sign", "Compute digital signature or MAC", "verify");
   static final KeyOperation VERIFY = of("verify", "Verify digital signature or MAC", "sign");
   static final KeyOperation ENCRYPT = of("encrypt", "Encrypt content", "decrypt");
   static final KeyOperation DECRYPT = of("decrypt", "Decrypt content and validate decryption, if applicable", "encrypt");
   static final KeyOperation WRAP = of("wrapKey", "Encrypt key", "unwrapKey");
   static final KeyOperation UNWRAP = of("unwrapKey", "Decrypt key and validate decryption, if applicable", "wrapKey");
   static final KeyOperation DERIVE_KEY = of("deriveKey", "Derive key", (String)null);
   static final KeyOperation DERIVE_BITS = of("deriveBits", "Derive bits not to be used as a key", (String)null);
   final String id;
   final String description;
   final Set related;

   static KeyOperation of(String id, String description, String related) {
      return new DefaultKeyOperation(id, description, Collections.setOf(new String[]{related}));
   }

   DefaultKeyOperation(String id) {
      this(id, (String)null, (Set)null);
   }

   DefaultKeyOperation(String id, String description, Set related) {
      this.id = (String)Assert.hasText(id, "id cannot be null or empty.");
      this.description = Strings.hasText(description) ? description : "Custom key operation";
      this.related = related != null ? Collections.immutable(related) : Collections.emptySet();
   }

   public String getId() {
      return this.id;
   }

   public String getDescription() {
      return this.description;
   }

   public boolean isRelated(KeyOperation operation) {
      return this.equals(operation) || operation != null && this.related.contains(operation.getId());
   }

   public int hashCode() {
      return this.id.hashCode();
   }

   public boolean equals(Object obj) {
      return obj == this || obj instanceof KeyOperation && this.id.equals(((KeyOperation)obj).getId());
   }

   public String toString() {
      return "'" + this.id + "' (" + this.description + ")";
   }
}
