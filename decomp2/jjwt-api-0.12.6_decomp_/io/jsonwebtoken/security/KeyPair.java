package io.jsonwebtoken.security;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyPair {
   PublicKey getPublic();

   PrivateKey getPrivate();

   java.security.KeyPair toJavaKeyPair();
}
