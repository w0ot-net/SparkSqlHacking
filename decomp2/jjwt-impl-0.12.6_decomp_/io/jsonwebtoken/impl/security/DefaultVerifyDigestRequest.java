package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.VerifyDigestRequest;
import java.io.InputStream;
import java.security.Provider;
import java.security.SecureRandom;

public class DefaultVerifyDigestRequest extends DefaultRequest implements VerifyDigestRequest {
   private final byte[] digest;

   public DefaultVerifyDigestRequest(InputStream payload, Provider provider, SecureRandom secureRandom, byte[] digest) {
      super(payload, provider, secureRandom);
      this.digest = Assert.notEmpty(digest, "Digest byte array cannot be null or empty.");
   }

   public byte[] getDigest() {
      return this.digest;
   }
}
