package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.security.AeadRequest;
import io.jsonwebtoken.security.IvSupplier;
import java.io.InputStream;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.SecretKey;

public class DefaultAeadRequest extends DefaultSecureRequest implements AeadRequest, IvSupplier {
   private final byte[] IV;
   private final InputStream AAD;

   DefaultAeadRequest(InputStream payload, Provider provider, SecureRandom secureRandom, SecretKey key, InputStream aad, byte[] iv) {
      super(payload, provider, secureRandom, key);
      this.AAD = aad;
      this.IV = iv;
   }

   public DefaultAeadRequest(InputStream payload, Provider provider, SecureRandom secureRandom, SecretKey key, InputStream aad) {
      this(payload, provider, secureRandom, key, aad, (byte[])null);
   }

   public InputStream getAssociatedData() {
      return this.AAD;
   }

   public byte[] getIv() {
      return this.IV;
   }
}
