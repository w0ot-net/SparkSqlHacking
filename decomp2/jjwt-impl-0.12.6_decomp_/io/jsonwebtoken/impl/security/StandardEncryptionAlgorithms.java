package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.AeadAlgorithm;

public final class StandardEncryptionAlgorithms extends IdRegistry {
   public static final String NAME = "JWE Encryption Algorithm";

   public StandardEncryptionAlgorithms() {
      super("JWE Encryption Algorithm", Collections.of(new AeadAlgorithm[]{new HmacAesAeadAlgorithm(128), new HmacAesAeadAlgorithm(192), new HmacAesAeadAlgorithm(256), new GcmAesAeadAlgorithm(128), new GcmAesAeadAlgorithm(192), new GcmAesAeadAlgorithm(256)}));
   }
}
