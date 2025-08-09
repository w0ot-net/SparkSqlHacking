package com.google.crypto.tink.signature;

import java.security.GeneralSecurityException;

/** @deprecated */
@Deprecated
public final class PublicKeySignConfig {
   /** @deprecated */
   @Deprecated
   public static void registerStandardKeyTypes() throws GeneralSecurityException {
      SignatureConfig.register();
   }

   private PublicKeySignConfig() {
   }
}
