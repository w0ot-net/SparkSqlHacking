package io.jsonwebtoken.security;

import java.net.URI;
import java.util.List;

public interface X509Accessor {
   URI getX509Url();

   List getX509Chain();

   byte[] getX509Sha1Thumbprint();

   byte[] getX509Sha256Thumbprint();
}
