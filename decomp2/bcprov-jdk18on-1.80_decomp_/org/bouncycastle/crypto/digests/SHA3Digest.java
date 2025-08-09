package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServicePurpose;

public class SHA3Digest extends KeccakDigest {
   private static int checkBitLength(int var0) {
      switch (var0) {
         case 224:
         case 256:
         case 384:
         case 512:
            return var0;
         default:
            throw new IllegalArgumentException("'bitLength' " + var0 + " not supported for SHA-3");
      }
   }

   public SHA3Digest() {
      this(256, CryptoServicePurpose.ANY);
   }

   public SHA3Digest(CryptoServicePurpose var1) {
      this(256, var1);
   }

   public SHA3Digest(int var1) {
      super(checkBitLength(var1), CryptoServicePurpose.ANY);
   }

   public SHA3Digest(int var1, CryptoServicePurpose var2) {
      super(checkBitLength(var1), var2);
   }

   public SHA3Digest(SHA3Digest var1) {
      super((KeccakDigest)var1);
   }

   public String getAlgorithmName() {
      return "SHA3-" + this.fixedOutputLength;
   }

   public int doFinal(byte[] var1, int var2) {
      this.absorbBits(2, 2);
      return super.doFinal(var1, var2);
   }

   protected int doFinal(byte[] var1, int var2, byte var3, int var4) {
      if (var4 >= 0 && var4 <= 7) {
         int var5 = var3 & (1 << var4) - 1 | 2 << var4;
         int var6 = var4 + 2;
         if (var6 >= 8) {
            this.absorb((byte)var5);
            var6 -= 8;
            var5 >>>= 8;
         }

         return super.doFinal(var1, var2, (byte)var5, var6);
      } else {
         throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
      }
   }
}
