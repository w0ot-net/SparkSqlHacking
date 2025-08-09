package io.jsonwebtoken.security;

public interface X509Builder extends X509Mutator {
   X509Builder x509Sha1Thumbprint(boolean var1);

   X509Builder x509Sha256Thumbprint(boolean var1);
}
