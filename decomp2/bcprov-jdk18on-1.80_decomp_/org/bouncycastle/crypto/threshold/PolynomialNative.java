package org.bouncycastle.crypto.threshold;

class PolynomialNative extends Polynomial {
   private final int IRREDUCIBLE;

   public PolynomialNative(ShamirSecretSplitter.Algorithm var1) {
      switch (var1) {
         case AES:
            this.IRREDUCIBLE = 283;
            break;
         case RSA:
            this.IRREDUCIBLE = 285;
            break;
         default:
            throw new IllegalArgumentException("The algorithm is not correct");
      }

   }

   protected byte gfMul(int var1, int var2) {
      int var3;
      for(var3 = 0; var2 > 0; var2 >>= 1) {
         if ((var2 & 1) != 0) {
            var3 ^= var1;
         }

         var1 <<= 1;
         if ((var1 & 256) != 0) {
            var1 ^= this.IRREDUCIBLE;
         }
      }

      for(; var3 >= 256; var3 <<= 1) {
         if ((var3 & 256) != 0) {
            var3 ^= this.IRREDUCIBLE;
         }
      }

      return (byte)(var3 & 255);
   }

   protected byte gfDiv(int var1, int var2) {
      return this.gfMul(var1, this.gfPow((byte)var2, (byte)-2) & 255);
   }
}
