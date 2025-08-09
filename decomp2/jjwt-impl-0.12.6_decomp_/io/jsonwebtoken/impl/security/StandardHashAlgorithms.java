package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.HashAlgorithm;

public final class StandardHashAlgorithms extends IdRegistry {
   public StandardHashAlgorithms() {
      super("IANA Hash Algorithm", Collections.of(new HashAlgorithm[]{new DefaultHashAlgorithm("sha-256"), new DefaultHashAlgorithm("sha-384"), new DefaultHashAlgorithm("sha-512"), new DefaultHashAlgorithm("sha3-256"), new DefaultHashAlgorithm("sha3-384"), new DefaultHashAlgorithm("sha3-512")}));
   }
}
