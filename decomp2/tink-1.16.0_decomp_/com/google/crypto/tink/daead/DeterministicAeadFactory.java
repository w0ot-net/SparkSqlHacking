package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.RegistryConfiguration;
import java.security.GeneralSecurityException;

/** @deprecated */
@Deprecated
public final class DeterministicAeadFactory {
   /** @deprecated */
   @Deprecated
   public static DeterministicAead getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
      DeterministicAeadWrapper.register();
      return (DeterministicAead)keysetHandle.getPrimitive(RegistryConfiguration.get(), DeterministicAead.class);
   }

   private DeterministicAeadFactory() {
   }
}
