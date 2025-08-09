package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.AbstractParserBuilder;
import io.jsonwebtoken.io.ParserBuilder;
import io.jsonwebtoken.security.KeyOperationPolicied;
import io.jsonwebtoken.security.KeyOperationPolicy;

abstract class AbstractJwkParserBuilder extends AbstractParserBuilder implements KeyOperationPolicied {
   protected KeyOperationPolicy operationPolicy;

   AbstractJwkParserBuilder() {
      this.operationPolicy = AbstractJwkBuilder.DEFAULT_OPERATION_POLICY;
   }

   public ParserBuilder operationPolicy(KeyOperationPolicy policy) throws IllegalArgumentException {
      this.operationPolicy = policy;
      return this.self();
   }
}
