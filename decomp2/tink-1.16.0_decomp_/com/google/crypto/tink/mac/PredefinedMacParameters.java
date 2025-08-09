package com.google.crypto.tink.mac;

import com.google.crypto.tink.internal.TinkBugException;

public final class PredefinedMacParameters {
   public static final HmacParameters HMAC_SHA256_128BITTAG = (HmacParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> HmacParameters.builder().setKeySizeBytes(32).setTagSizeBytes(16).setVariant(HmacParameters.Variant.TINK).setHashType(HmacParameters.HashType.SHA256).build()));
   public static final HmacParameters HMAC_SHA256_256BITTAG = (HmacParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> HmacParameters.builder().setKeySizeBytes(32).setTagSizeBytes(32).setVariant(HmacParameters.Variant.TINK).setHashType(HmacParameters.HashType.SHA256).build()));
   public static final HmacParameters HMAC_SHA512_256BITTAG = (HmacParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> HmacParameters.builder().setKeySizeBytes(64).setTagSizeBytes(32).setVariant(HmacParameters.Variant.TINK).setHashType(HmacParameters.HashType.SHA512).build()));
   public static final HmacParameters HMAC_SHA512_512BITTAG = (HmacParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> HmacParameters.builder().setKeySizeBytes(64).setTagSizeBytes(64).setVariant(HmacParameters.Variant.TINK).setHashType(HmacParameters.HashType.SHA512).build()));
   public static final AesCmacParameters AES_CMAC = (AesCmacParameters)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> AesCmacParameters.builder().setKeySizeBytes(32).setTagSizeBytes(16).setVariant(AesCmacParameters.Variant.TINK).build()));

   private PredefinedMacParameters() {
   }
}
