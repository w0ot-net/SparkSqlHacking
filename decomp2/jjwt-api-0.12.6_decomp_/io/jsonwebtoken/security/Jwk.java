package io.jsonwebtoken.security;

import io.jsonwebtoken.Identifiable;
import java.security.Key;
import java.util.Map;
import java.util.Set;

public interface Jwk extends Identifiable, Map {
   String getAlgorithm();

   Set getOperations();

   String getType();

   JwkThumbprint thumbprint();

   JwkThumbprint thumbprint(HashAlgorithm var1);

   Key toKey();
}
