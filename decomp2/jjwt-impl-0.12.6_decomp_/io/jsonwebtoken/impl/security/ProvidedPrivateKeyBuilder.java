package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.PrivateKeyBuilder;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECKey;

public class ProvidedPrivateKeyBuilder extends ProvidedKeyBuilder implements PrivateKeyBuilder {
   private PublicKey publicKey;

   ProvidedPrivateKeyBuilder(PrivateKey key) {
      super(key);
   }

   public PrivateKeyBuilder publicKey(PublicKey publicKey) {
      this.publicKey = publicKey;
      return this;
   }

   public PrivateKey doBuild() {
      PrivateKey key = (PrivateKey)this.key;
      String privAlg = Strings.clean(((PrivateKey)this.key).getAlgorithm());
      if (!(key instanceof ECKey) && ("EC".equalsIgnoreCase(privAlg) || "ECDSA".equalsIgnoreCase(privAlg)) && this.publicKey instanceof ECKey) {
         key = new PrivateECKey(key, ((ECKey)this.publicKey).getParams());
      }

      return (PrivateKey)(this.provider != null ? new ProviderPrivateKey(this.provider, key) : key);
   }
}
