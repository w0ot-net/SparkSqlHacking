package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.RegistryConfiguration;
import java.security.GeneralSecurityException;

/** @deprecated */
@Deprecated
public final class MacFactory {
   /** @deprecated */
   @Deprecated
   public static Mac getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
      MacWrapper.register();
      return (Mac)keysetHandle.getPrimitive(RegistryConfiguration.get(), Mac.class);
   }

   private MacFactory() {
   }
}
