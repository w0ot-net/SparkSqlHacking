package io.jsonwebtoken.security;

import java.security.PublicKey;

public interface PrivateJwkBuilder extends AsymmetricJwkBuilder {
   PrivateJwkBuilder publicKey(PublicKey var1);
}
