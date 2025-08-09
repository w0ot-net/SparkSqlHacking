package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.KeyBuilder;
import java.security.Key;

abstract class ProvidedKeyBuilder extends AbstractSecurityBuilder implements KeyBuilder {
   protected final Key key;

   ProvidedKeyBuilder(Key key) {
      this.key = (Key)Assert.notNull(key, "Key cannot be null.");
   }

   public final Key build() {
      return this.key instanceof ProviderKey ? this.key : this.doBuild();
   }

   abstract Key doBuild();
}
