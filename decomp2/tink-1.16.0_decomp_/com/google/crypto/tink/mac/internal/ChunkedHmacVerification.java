package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.mac.HmacKey;
import com.google.crypto.tink.util.Bytes;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

final class ChunkedHmacVerification implements ChunkedMacVerification {
   private final Bytes tag;
   private final ChunkedHmacComputation hmacComputation;

   ChunkedHmacVerification(HmacKey key, byte[] tag) throws GeneralSecurityException {
      this.hmacComputation = new ChunkedHmacComputation(key);
      this.tag = Bytes.copyFrom(tag);
   }

   public void update(ByteBuffer data) {
      this.hmacComputation.update(data);
   }

   public void verifyMac() throws GeneralSecurityException {
      byte[] other = this.hmacComputation.computeMac();
      if (!this.tag.equals(Bytes.copyFrom(other))) {
         throw new GeneralSecurityException("invalid MAC");
      }
   }
}
