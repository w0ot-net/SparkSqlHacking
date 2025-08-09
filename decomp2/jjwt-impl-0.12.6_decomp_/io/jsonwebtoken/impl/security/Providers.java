package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Classes;
import java.security.Provider;
import java.security.Security;
import java.util.concurrent.atomic.AtomicReference;

final class Providers {
   private static final String BC_PROVIDER_CLASS_NAME = "org.bouncycastle.jce.provider.BouncyCastleProvider";
   static final boolean BOUNCY_CASTLE_AVAILABLE = Classes.isAvailable("org.bouncycastle.jce.provider.BouncyCastleProvider");
   private static final AtomicReference BC_PROVIDER = new AtomicReference();

   private Providers() {
   }

   public static Provider findBouncyCastle() {
      if (!BOUNCY_CASTLE_AVAILABLE) {
         return null;
      } else {
         Provider provider = (Provider)BC_PROVIDER.get();
         if (provider == null) {
            Class<Provider> clazz = Classes.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
            Provider[] providers = Security.getProviders();

            for(Provider aProvider : providers) {
               if (clazz.isInstance(aProvider)) {
                  BC_PROVIDER.set(aProvider);
                  return aProvider;
               }
            }

            provider = (Provider)Classes.newInstance(clazz);
            BC_PROVIDER.set(provider);
         }

         return provider;
      }
   }
}
