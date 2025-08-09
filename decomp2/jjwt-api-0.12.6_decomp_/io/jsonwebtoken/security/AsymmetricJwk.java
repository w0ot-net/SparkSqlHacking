package io.jsonwebtoken.security;

public interface AsymmetricJwk extends Jwk, X509Accessor {
   String getPublicKeyUse();
}
