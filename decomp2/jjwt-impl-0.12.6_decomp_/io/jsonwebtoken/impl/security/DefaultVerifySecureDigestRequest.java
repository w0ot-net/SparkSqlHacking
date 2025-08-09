package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import java.io.InputStream;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;

public class DefaultVerifySecureDigestRequest extends DefaultSecureRequest implements VerifySecureDigestRequest {
   private final byte[] digest;

   public DefaultVerifySecureDigestRequest(InputStream payload, Provider provider, SecureRandom secureRandom, Key key, byte[] digest) {
      super(payload, provider, secureRandom, key);
      this.digest = Assert.notEmpty(digest, "Digest byte array cannot be null or empty.");
   }

   public byte[] getDigest() {
      return this.digest;
   }
}
