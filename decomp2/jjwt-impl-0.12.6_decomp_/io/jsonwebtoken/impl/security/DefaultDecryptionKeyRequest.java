package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;

public class DefaultDecryptionKeyRequest extends DefaultKeyRequest implements DecryptionKeyRequest {
   private final Key decryptionKey;

   public DefaultDecryptionKeyRequest(byte[] encryptedCek, Provider provider, SecureRandom secureRandom, JweHeader header, AeadAlgorithm encryptionAlgorithm, Key decryptionKey) {
      super(encryptedCek, provider, secureRandom, header, encryptionAlgorithm);
      this.decryptionKey = (Key)Assert.notNull(decryptionKey, "decryption key cannot be null.");
   }

   protected void assertBytePayload(byte[] payload) {
      Assert.notNull(payload, "encrypted key bytes cannot be null (but may be empty.");
   }

   public Key getKey() {
      return this.decryptionKey;
   }
}
