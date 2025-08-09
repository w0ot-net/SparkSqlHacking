package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Config;
import java.security.GeneralSecurityException;

/** @deprecated */
@Deprecated
public final class HybridDecryptConfig {
   /** @deprecated */
   @Deprecated
   public static void registerStandardKeyTypes() throws GeneralSecurityException {
      Config.register(HybridConfig.TINK_1_0_0);
   }

   private HybridDecryptConfig() {
   }
}
