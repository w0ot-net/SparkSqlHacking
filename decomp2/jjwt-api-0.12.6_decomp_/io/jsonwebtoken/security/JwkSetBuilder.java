package io.jsonwebtoken.security;

import io.jsonwebtoken.lang.MapMutator;
import java.util.Collection;

public interface JwkSetBuilder extends MapMutator, SecurityBuilder, KeyOperationPolicied {
   JwkSetBuilder add(Jwk var1);

   JwkSetBuilder add(Collection var1);

   JwkSetBuilder keys(Collection var1);
}
