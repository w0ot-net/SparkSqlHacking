package io.jsonwebtoken;

import io.jsonwebtoken.security.PublicJwk;
import io.jsonwebtoken.security.X509Accessor;
import java.net.URI;
import java.util.Set;

public interface ProtectedHeader extends Header, X509Accessor {
   URI getJwkSetUrl();

   PublicJwk getJwk();

   String getKeyId();

   Set getCritical();
}
