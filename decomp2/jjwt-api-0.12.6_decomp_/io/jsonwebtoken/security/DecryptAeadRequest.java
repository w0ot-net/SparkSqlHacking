package io.jsonwebtoken.security;

public interface DecryptAeadRequest extends AeadRequest, IvSupplier, DigestSupplier {
}
