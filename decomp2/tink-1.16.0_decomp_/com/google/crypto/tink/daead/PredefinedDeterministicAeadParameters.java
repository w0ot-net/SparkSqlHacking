package com.google.crypto.tink.daead;

import com.google.crypto.tink.internal.TinkBugException;

public final class PredefinedDeterministicAeadParameters {
   public static final AesSivParameters AES256_SIV = (AesSivParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> AesSivParameters.builder().setKeySizeBytes(64).setVariant(AesSivParameters.Variant.TINK).build()));

   private PredefinedDeterministicAeadParameters() {
   }
}
