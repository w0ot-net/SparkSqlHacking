package io.jsonwebtoken;

import io.jsonwebtoken.security.DigestSupplier;

public interface ProtectedJwt extends Jwt, DigestSupplier {
}
