package io.jsonwebtoken;

import java.security.Key;

/** @deprecated */
@Deprecated
public interface SigningKeyResolver {
   Key resolveSigningKey(JwsHeader var1, Claims var2);

   Key resolveSigningKey(JwsHeader var1, byte[] var2);
}
