package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.security.Password;
import io.jsonwebtoken.security.SecretKeyBuilder;
import javax.crypto.SecretKey;

class ProvidedSecretKeyBuilder extends ProvidedKeyBuilder implements SecretKeyBuilder {
   ProvidedSecretKeyBuilder(SecretKey key) {
      super(key);
   }

   public SecretKey doBuild() {
      if (this.key instanceof Password) {
         return (SecretKey)this.key;
      } else {
         return (SecretKey)(this.provider != null ? new ProviderSecretKey(this.provider, (SecretKey)this.key) : (SecretKey)this.key);
      }
   }
}
