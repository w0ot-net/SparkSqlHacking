package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.security.Jwk;
import java.security.Key;

public interface JwkFactory {
   JwkContext newContext(JwkContext var1, Key var2);

   Jwk createJwk(JwkContext var1);
}
