package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Pack;

public class AsconHash256 extends AsconBaseDigest {
   public AsconHash256() {
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

   public String getAlgorithmName() {
      return "Ascon-Hash256";
   }

   public void reset() {
      super.reset();
      this.x0 = -7269279749984954751L;
      this.x1 = 5459383224871899602L;
      this.x2 = -5880230600644446182L;
      this.x3 = 4359436768738168243L;
      this.x4 = 1899470422303676269L;
   }
}
