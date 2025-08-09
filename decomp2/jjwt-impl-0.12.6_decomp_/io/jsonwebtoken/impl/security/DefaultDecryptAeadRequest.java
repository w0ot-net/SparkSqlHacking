package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.DecryptAeadRequest;
import java.io.InputStream;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.SecretKey;

public class DefaultDecryptAeadRequest extends DefaultAeadRequest implements DecryptAeadRequest {
   private final byte[] TAG;

   public DefaultDecryptAeadRequest(InputStream payload, SecretKey key, InputStream aad, byte[] iv, byte[] tag) {
      super(payload, (Provider)null, (SecureRandom)null, key, aad, Assert.notEmpty(iv, "Initialization Vector cannot be null or empty."));
      this.TAG = Assert.notEmpty(tag, "AAD Authentication Tag cannot be null or empty.");
   }

   public byte[] getDigest() {
      return this.TAG;
   }
}
