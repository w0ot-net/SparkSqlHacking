package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.KeyResult;
import javax.crypto.SecretKey;

public class DefaultKeyResult extends DefaultMessage implements KeyResult {
   private final SecretKey key;

   public DefaultKeyResult(SecretKey key) {
      this(key, Bytes.EMPTY);
   }

   public DefaultKeyResult(SecretKey key, byte[] encryptedKey) {
      super(encryptedKey);
      this.key = (SecretKey)Assert.notNull(key, "Content Encryption Key cannot be null.");
   }

   protected void assertBytePayload(byte[] payload) {
      Assert.notNull(payload, "encrypted key bytes cannot be null (but may be empty.");
   }

   public SecretKey getKey() {
      return this.key;
   }
}
