package io.jsonwebtoken.security;

import java.net.URI;

public interface JwkThumbprint {
   HashAlgorithm getHashAlgorithm();

   byte[] toByteArray();

   URI toURI();

   String toString();
}
