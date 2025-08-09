package io.jsonwebtoken.security;

public interface PrivateJwk extends AsymmetricJwk {
   PublicJwk toPublicJwk();

   KeyPair toKeyPair();
}
