package io.jsonwebtoken.impl.security;

import java.security.Provider;
import javax.crypto.SecretKey;

public final class ProviderSecretKey extends ProviderKey implements SecretKey {
   ProviderSecretKey(Provider provider, SecretKey key) {
      super(provider, key);
   }
}
