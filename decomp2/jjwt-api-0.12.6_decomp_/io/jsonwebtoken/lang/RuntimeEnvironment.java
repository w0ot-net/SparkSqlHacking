package io.jsonwebtoken.lang;

import java.security.Provider;
import java.security.Security;
import java.util.concurrent.atomic.AtomicBoolean;

/** @deprecated */
@Deprecated
public final class RuntimeEnvironment {
   private static final String BC_PROVIDER_CLASS_NAME = "org.bouncycastle.jce.provider.BouncyCastleProvider";
   private static final AtomicBoolean bcLoaded = new AtomicBoolean(false);
   /** @deprecated */
   @Deprecated
   public static final boolean BOUNCY_CASTLE_AVAILABLE = Classes.isAvailable("org.bouncycastle.jce.provider.BouncyCastleProvider");

   private RuntimeEnvironment() {
   }

   /** @deprecated */
   @Deprecated
   public static void enableBouncyCastleIfPossible() {
      if (BOUNCY_CASTLE_AVAILABLE && !bcLoaded.get()) {
         try {
            Class<Provider> clazz = Classes.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
            Provider[] providers = Security.getProviders();

            for(Provider provider : providers) {
               if (clazz.isInstance(provider)) {
                  bcLoaded.set(true);
                  return;
               }
            }

            Provider provider = (Provider)Classes.newInstance(clazz);
            Security.addProvider(provider);
            bcLoaded.set(true);
         } catch (UnknownClassException var6) {
         }

      }
   }

   static {
      enableBouncyCastleIfPossible();
   }
}
