package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyRequest;
import java.security.Provider;
import java.security.SecureRandom;

public class DefaultKeyRequest extends DefaultRequest implements KeyRequest {
   private final JweHeader header;
   private final AeadAlgorithm encryptionAlgorithm;

   public DefaultKeyRequest(Object payload, Provider provider, SecureRandom secureRandom, JweHeader header, AeadAlgorithm encryptionAlgorithm) {
      super(payload, provider, secureRandom);
      this.header = (JweHeader)Assert.notNull(header, "JweHeader/Builder cannot be null.");
      this.encryptionAlgorithm = (AeadAlgorithm)Assert.notNull(encryptionAlgorithm, "AeadAlgorithm argument cannot be null.");
   }

   public JweHeader getHeader() {
      return this.header;
   }

   public AeadAlgorithm getEncryptionAlgorithm() {
      return this.encryptionAlgorithm;
   }
}
