package org.bouncycastle.pqc.crypto.lms;

import java.io.IOException;
import org.bouncycastle.util.Encodable;

class LMSSignedPubKey implements Encodable {
   private final LMSSignature signature;
   private final LMSPublicKeyParameters publicKey;

   public LMSSignedPubKey(LMSSignature var1, LMSPublicKeyParameters var2) {
      this.signature = var1;
      this.publicKey = var2;
   }

   public LMSSignature getSignature() {
      return this.signature;
   }

   public LMSPublicKeyParameters getPublicKey() {
      return this.publicKey;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         LMSSignedPubKey var2 = (LMSSignedPubKey)var1;
         if (this.signature != null) {
            if (!this.signature.equals(var2.signature)) {
               return false;
            }
         } else if (var2.signature != null) {
            return false;
         }

         return this.publicKey != null ? this.publicKey.equals(var2.publicKey) : var2.publicKey == null;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int var1 = this.signature != null ? this.signature.hashCode() : 0;
      var1 = 31 * var1 + (this.publicKey != null ? this.publicKey.hashCode() : 0);
      return var1;
   }

   public byte[] getEncoded() throws IOException {
      return Composer.compose().bytes(this.signature.getEncoded()).bytes(this.publicKey.getEncoded()).build();
   }
}
