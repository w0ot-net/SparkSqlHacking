package io.jsonwebtoken.security;

public interface AsymmetricJwkBuilder extends JwkBuilder, X509Builder {
   AsymmetricJwkBuilder publicKeyUse(String var1) throws IllegalArgumentException;
}
