package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Pack;

/** @deprecated */
public class AsconXof extends AsconBaseDigest implements Xof {
   AsconParameters asconParameters;
   private final String algorithmName;
   private boolean m_squeezing = false;

   public AsconXof(AsconParameters var1) {
      this.asconParameters = var1;
      switch (var1.ordinal()) {
         case 0:
            this.ASCON_PB_ROUNDS = 12;
            this.algorithmName = "Ascon-Xof";
            break;
         case 1:
            this.ASCON_PB_ROUNDS = 8;
            this.algorithmName = "Ascon-XofA";
            break;
         default:
            throw new IllegalArgumentException("Invalid parameter settings for Ascon Hash");
      }

      this.reset();
   }

   public void update(byte var1) {
      if (this.m_squeezing) {
         throw new IllegalArgumentException("attempt to absorb while squeezing");
      } else {
         super.update(var1);
      }
   }

   public void update(byte[] var1, int var2, int var3) {
      if (this.m_squeezing) {
         throw new IllegalArgumentException("attempt to absorb while squeezing");
      } else {
         super.update(var1, var2, var3);
      }
   }

   protected void padAndAbsorb() {
      this.m_squeezing = true;
      super.padAndAbsorb();
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

   public int doOutput(byte[] var1, int var2, int var3) {
      return this.hash(var1, var2, var3);
   }

   public int doFinal(byte[] var1, int var2, int var3) {
      int var4 = this.doOutput(var1, var2, var3);
      this.reset();
      return var4;
   }

   public int getByteLength() {
      return 8;
   }

   public void reset() {
      super.reset();
      this.m_squeezing = false;
      switch (this.asconParameters.ordinal()) {
         case 0:
            this.x0 = -5368810569253202922L;
            this.x1 = 3121280575360345120L;
            this.x2 = 7395939140700676632L;
            this.x3 = 6533890155656471820L;
            this.x4 = 5710016986865767350L;
            break;
         case 1:
            this.x0 = 4940560291654768690L;
            this.x1 = -3635129828240960206L;
            this.x2 = -597534922722107095L;
            this.x3 = 2623493988082852443L;
            this.x4 = -6283826724160825537L;
      }

   }

   public static enum AsconParameters {
      AsconXof,
      AsconXofA;

      // $FF: synthetic method
      private static AsconParameters[] $values() {
         return new AsconParameters[]{AsconXof, AsconXofA};
      }
   }
}
