package io.jsonwebtoken.security;

import java.security.PublicKey;

public interface PrivateKeyBuilder extends KeyBuilder {
   PrivateKeyBuilder publicKey(PublicKey var1);
}
