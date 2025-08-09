package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.util.Bytes;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

final class ChunkedAesCmacVerification implements ChunkedMacVerification {
   private final Bytes tag;
   private final ChunkedAesCmacComputation aesCmacComputation;

   ChunkedAesCmacVerification(AesCmacKey key, byte[] tag) throws GeneralSecurityException {
      this.aesCmacComputation = new ChunkedAesCmacComputation(key);
      this.tag = Bytes.copyFrom(tag);
   }

   public void update(ByteBuffer data) throws GeneralSecurityException {
      this.aesCmacComputation.update(data);
   }

   public void verifyMac() throws GeneralSecurityException {
      byte[] other = this.aesCmacComputation.computeMac();
      if (!this.tag.equals(Bytes.copyFrom(other))) {
         throw new GeneralSecurityException("invalid MAC");
      }
   }
}
