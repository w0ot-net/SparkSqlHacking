package io.jsonwebtoken;

import io.jsonwebtoken.lang.NestedCollection;
import io.jsonwebtoken.security.PublicJwk;
import io.jsonwebtoken.security.X509Mutator;
import java.net.URI;

public interface ProtectedHeaderMutator extends HeaderMutator, X509Mutator {
   NestedCollection critical();

   ProtectedHeaderMutator jwk(PublicJwk var1);

   ProtectedHeaderMutator jwkSetUrl(URI var1);

   ProtectedHeaderMutator keyId(String var1);

   /** @deprecated */
   @Deprecated
   ProtectedHeaderMutator setKeyId(String var1);

   /** @deprecated */
   @Deprecated
   ProtectedHeaderMutator setAlgorithm(String var1);
}
