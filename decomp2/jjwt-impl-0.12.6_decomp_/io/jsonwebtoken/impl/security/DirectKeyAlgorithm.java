package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.KeyResult;
import io.jsonwebtoken.security.SecurityException;
import javax.crypto.SecretKey;

public class DirectKeyAlgorithm implements KeyAlgorithm {
   static final String ID = "dir";

   public String getId() {
      return "dir";
   }

   public KeyResult getEncryptionKey(KeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      SecretKey key = (SecretKey)Assert.notNull(request.getPayload(), "Encryption key cannot be null.");
      return new DefaultKeyResult(key);
   }

   public SecretKey getDecryptionKey(DecryptionKeyRequest request) throws SecurityException {
      Assert.notNull(request, "request cannot be null.");
      return (SecretKey)Assert.notNull(request.getKey(), "Decryption key cannot be null.");
   }
}
