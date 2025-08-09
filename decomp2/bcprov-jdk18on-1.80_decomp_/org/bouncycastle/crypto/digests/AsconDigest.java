package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Pack;

/** @deprecated */
public class AsconDigest extends AsconBaseDigest {
   AsconParameters asconParameters;
   private final String algorithmName;

   public AsconDigest(AsconParameters var1) {
      this.asconParameters = var1;
      switch (var1.ordinal()) {
         case 0:
            this.ASCON_PB_ROUNDS = 12;
            this.algorithmName = "Ascon-Hash";
            break;
         case 1:
            this.ASCON_PB_ROUNDS = 8;
            this.algorithmName = "Ascon-HashA";
            break;
         default:
            throw new IllegalArgumentException("Invalid parameter settings for Ascon Hash");
      }

      this.reset();
   }

   protected long pad(int var1) {
      return 128L << 56 - (var1 << 3);
   }

   protected long loadBytes(byte[] var1, int var2) {
      return Pack.bigEndianToLong(var1, var2);
   }

   protected long loadBytes(byte[] var1, int var2, int var3) {
      return Pack.bigEndianToLong(var1, var2, var3);
   }

   protected void setBytes(long var1, byte[] var3, int var4) {
      Pack.longToBigEndian(var1, var3, var4);
   }

   protected void setBytes(long var1, byte[] var3, int var4, int var5) {
      Pack.longToBigEndian(var1, var3, var4, var5);
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public void reset() {
      super.reset();
      switch (this.asconParameters.ordinal()) {
         case 0:
            this.x0 = -1255492011513352131L;
            this.x1 = -8380609354527731710L;
            this.x2 = -5437372128236807582L;
            this.x3 = 4834782570098516968L;
            this.x4 = 3787428097924915520L;
            break;
         case 1:
            this.x0 = 92044056785660070L;
            this.x1 = 8326807761760157607L;
            this.x2 = 3371194088139667532L;
            this.x3 = -2956994353054992515L;
            this.x4 = -6828509670848688761L;
      }

   }

   public static enum AsconParameters {
      AsconHash,
      AsconHashA;

      // $FF: synthetic method
      private static AsconParameters[] $values() {
         return new AsconParameters[]{AsconHash, AsconHashA};
      }
   }
}
