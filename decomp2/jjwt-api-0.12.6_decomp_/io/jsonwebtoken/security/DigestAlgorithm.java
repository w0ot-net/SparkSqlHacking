package io.jsonwebtoken.security;

import io.jsonwebtoken.Identifiable;

public interface DigestAlgorithm extends Identifiable {
   byte[] digest(Request var1) throws SecurityException;

   boolean verify(VerifyDigestRequest var1) throws SecurityException;
}
