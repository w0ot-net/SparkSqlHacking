package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.ChunkedMac;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public final class ChunkedAesCmacImpl implements ChunkedMac {
   private static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private final AesCmacKey key;

   public ChunkedAesCmacImpl(AesCmacKey key) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
      } else {
         this.key = key;
      }
   }

   public ChunkedMacComputation createComputation() throws GeneralSecurityException {
      return new ChunkedAesCmacComputation(this.key);
   }

   public ChunkedMacVerification createVerification(final byte[] tag) throws GeneralSecurityException {
      if (tag.length < this.key.getOutputPrefix().size()) {
         throw new GeneralSecurityException("Tag too short");
      } else if (!this.key.getOutputPrefix().equals(Bytes.copyFrom(tag, 0, this.key.getOutputPrefix().size()))) {
         throw new GeneralSecurityException("Wrong tag prefix");
      } else {
         return new ChunkedAesCmacVerification(this.key, tag);
      }
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
   }
}
