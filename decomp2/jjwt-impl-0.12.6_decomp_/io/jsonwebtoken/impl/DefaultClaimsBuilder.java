package io.jsonwebtoken.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;

public final class DefaultClaimsBuilder extends DelegatingClaimsMutator implements ClaimsBuilder {
   public Claims build() {
      return new DefaultClaims((ParameterMap)this.DELEGATE);
   }
}
