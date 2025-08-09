package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Supplier;
import io.jsonwebtoken.security.DynamicJwkBuilder;
import io.jsonwebtoken.security.Jwks;
import io.jsonwebtoken.security.KeyOperationPolicy;
import java.security.Provider;

public class JwkBuilderSupplier implements Supplier {
   public static final JwkBuilderSupplier DEFAULT = new JwkBuilderSupplier((Provider)null, (KeyOperationPolicy)null);
   private final Provider provider;
   private final KeyOperationPolicy operationPolicy;

   public JwkBuilderSupplier(Provider provider, KeyOperationPolicy operationPolicy) {
      this.provider = provider;
      this.operationPolicy = operationPolicy;
   }

   public DynamicJwkBuilder get() {
      DynamicJwkBuilder<?, ?> builder = (DynamicJwkBuilder)Jwks.builder().provider(this.provider);
      if (this.operationPolicy != null) {
         builder.operationPolicy(this.operationPolicy);
      }

      return builder;
   }
}
