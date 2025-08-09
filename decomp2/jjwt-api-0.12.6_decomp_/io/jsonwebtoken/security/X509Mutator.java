package io.jsonwebtoken.security;

import java.net.URI;
import java.util.List;

public interface X509Mutator {
   X509Mutator x509Url(URI var1);

   X509Mutator x509Chain(List var1);

   X509Mutator x509Sha1Thumbprint(byte[] var1);

   X509Mutator x509Sha256Thumbprint(byte[] var1);
}
