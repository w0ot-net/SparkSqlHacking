package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.KeyOperation;

public final class StandardKeyOperations extends IdRegistry {
   public StandardKeyOperations() {
      super("JSON Web Key Operation", Collections.of(new KeyOperation[]{DefaultKeyOperation.SIGN, DefaultKeyOperation.VERIFY, DefaultKeyOperation.ENCRYPT, DefaultKeyOperation.DECRYPT, DefaultKeyOperation.WRAP, DefaultKeyOperation.UNWRAP, DefaultKeyOperation.DERIVE_KEY, DefaultKeyOperation.DERIVE_BITS}));
   }
}
