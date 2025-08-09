package com.google.crypto.tink.prf;

import com.google.crypto.tink.internal.TinkBugException;

public final class PredefinedPrfParameters {
   public static final HkdfPrfParameters HKDF_SHA256 = (HkdfPrfParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> HkdfPrfParameters.builder().setKeySizeBytes(32).setHashType(HkdfPrfParameters.HashType.SHA256).build()));
   public static final HmacPrfParameters HMAC_SHA256_PRF = (HmacPrfParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> HmacPrfParameters.builder().setKeySizeBytes(32).setHashType(HmacPrfParameters.HashType.SHA256).build()));
   public static final HmacPrfParameters HMAC_SHA512_PRF = (HmacPrfParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> HmacPrfParameters.builder().setKeySizeBytes(64).setHashType(HmacPrfParameters.HashType.SHA512).build()));
   public static final AesCmacPrfParameters AES_CMAC_PRF = (AesCmacPrfParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> AesCmacPrfParameters.create(32)));

   private PredefinedPrfParameters() {
   }
}
