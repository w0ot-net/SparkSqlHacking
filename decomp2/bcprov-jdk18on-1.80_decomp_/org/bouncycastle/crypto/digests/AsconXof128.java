package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Pack;

public class AsconXof128 extends AsconBaseDigest implements Xof {
   private boolean m_squeezing = false;

   public AsconXof128() {
      this.reset();
   }

   protected long pad(int var1) {
      return 1L << (var1 << 3);
   }

   protected long loadBytes(byte[] var1, int var2) {
      return Pack.littleEndianToLong(var1, var2);
   }

   protected long loadBytes(byte[] var1, int var2, int var3) {
      return Pack.littleEndianToLong(var1, var2, var3);
   }

   protected void setBytes(long var1, byte[] var3, int var4) {
      Pack.longToLittleEndian(var1, var3, var4);
   }

   protected void setBytes(long var1, byte[] var3, int var4, int var5) {
      Pack.longToLittleEndian(var1, var3, var4, var5);
   }

   protected void padAndAbsorb() {
      this.m_squeezing = true;
      super.padAndAbsorb();
   }

   public String getAlgorithmName() {
      return "Ascon-XOF-128";
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
      this.m_squeezing = false;
      super.reset();
      this.x0 = -2701369817892108309L;
      this.x1 = -3711838248891385495L;
      this.x2 = -1778763697082575311L;
      this.x3 = 1072114354614917324L;
      this.x4 = -2282070310009238562L;
   }
}
