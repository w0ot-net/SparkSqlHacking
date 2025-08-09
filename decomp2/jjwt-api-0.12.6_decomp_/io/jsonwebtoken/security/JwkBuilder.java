package io.jsonwebtoken.security;

import io.jsonwebtoken.lang.MapMutator;
import io.jsonwebtoken.lang.NestedCollection;

public interface JwkBuilder extends MapMutator, SecurityBuilder, KeyOperationPolicied {
   JwkBuilder algorithm(String var1) throws IllegalArgumentException;

   JwkBuilder id(String var1) throws IllegalArgumentException;

   JwkBuilder idFromThumbprint();

   JwkBuilder idFromThumbprint(HashAlgorithm var1);

   NestedCollection operations();
}
