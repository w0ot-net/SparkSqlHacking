package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.DefaultCollectionMutator;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.KeyOperationPolicy;
import io.jsonwebtoken.security.KeyOperationPolicyBuilder;
import io.jsonwebtoken.security.Jwks.OP;

public class DefaultKeyOperationPolicyBuilder extends DefaultCollectionMutator implements KeyOperationPolicyBuilder {
   private boolean unrelated = false;

   public DefaultKeyOperationPolicyBuilder() {
      super(OP.get().values());
   }

   public KeyOperationPolicyBuilder unrelated() {
      this.unrelated = true;
      return this;
   }

   public KeyOperationPolicy build() {
      return new DefaultKeyOperationPolicy(Collections.immutable(this.getCollection()), this.unrelated);
   }
}
