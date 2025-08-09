package io.jsonwebtoken.impl.security;

import java.security.PrivateKey;
import java.security.Provider;

public final class ProviderPrivateKey extends ProviderKey implements PrivateKey {
   ProviderPrivateKey(Provider provider, PrivateKey key) {
      super(provider, key);
   }
}
