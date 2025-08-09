package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.KeySupplier;
import java.security.Key;
import java.security.Provider;

public class ProviderKey implements Key, KeySupplier {
   private final Key key;
   private final Provider provider;

   public static Provider getProvider(Key key, Provider backup) {
      if (key instanceof ProviderKey) {
         ProviderKey<?> pkey = (ProviderKey)key;
         return (Provider)Assert.stateNotNull(pkey.getProvider(), "ProviderKey provider can never be null.");
      } else {
         return backup;
      }
   }

   public static Key getKey(Key key) {
      return key instanceof ProviderKey ? ((ProviderKey)key).getKey() : key;
   }

   ProviderKey(Provider provider, Key key) {
      this.provider = (Provider)Assert.notNull(provider, "Provider cannot be null.");
      this.key = (Key)Assert.notNull(key, "Key argument cannot be null.");
      if (key instanceof ProviderKey) {
         String msg = "Nesting not permitted.";
         throw new IllegalArgumentException(msg);
      }
   }

   public Key getKey() {
      return this.key;
   }

   public String getAlgorithm() {
      return this.key.getAlgorithm();
   }

   public String getFormat() {
      return this.key.getFormat();
   }

   public byte[] getEncoded() {
      return this.key.getEncoded();
   }

   public final Provider getProvider() {
      return this.provider;
   }
}
