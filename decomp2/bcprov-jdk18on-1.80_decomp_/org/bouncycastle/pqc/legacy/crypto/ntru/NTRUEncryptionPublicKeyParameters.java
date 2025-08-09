package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;

public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {
   public IntegerPolynomial h;

   public NTRUEncryptionPublicKeyParameters(IntegerPolynomial var1, NTRUEncryptionParameters var2) {
      super(false, var2);
      this.h = var1;
   }

   public NTRUEncryptionPublicKeyParameters(byte[] var1, NTRUEncryptionParameters var2) {
      super(false, var2);
      this.h = IntegerPolynomial.fromBinary(var1, var2.N, var2.q);
   }

   public NTRUEncryptionPublicKeyParameters(InputStream var1, NTRUEncryptionParameters var2) throws IOException {
      super(false, var2);
      this.h = IntegerPolynomial.fromBinary(var1, var2.N, var2.q);
   }

   public byte[] getEncoded() {
      return this.h.toBinary(this.params.q);
   }

   public void writeTo(OutputStream var1) throws IOException {
      var1.write(this.getEncoded());
   }

   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.h == null ? 0 : this.h.hashCode());
      var1 = 31 * var1 + (this.params == null ? 0 : this.params.hashCode());
      return var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!(var1 instanceof NTRUEncryptionPublicKeyParameters)) {
         return false;
      } else {
         NTRUEncryptionPublicKeyParameters var2 = (NTRUEncryptionPublicKeyParameters)var1;
         if (this.h == null) {
            if (var2.h != null) {
               return false;
            }
         } else if (!this.h.equals(var2.h)) {
            return false;
         }

         if (this.params == null) {
            if (var2.params != null) {
               return false;
            }
         } else if (!this.params.equals(var2.params)) {
            return false;
         }

         return true;
      }
   }
}
