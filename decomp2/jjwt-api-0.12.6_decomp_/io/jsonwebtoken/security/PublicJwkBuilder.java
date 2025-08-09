package io.jsonwebtoken.security;

import java.security.PrivateKey;

public interface PublicJwkBuilder extends AsymmetricJwkBuilder {
   PrivateJwkBuilder privateKey(PrivateKey var1);
}
