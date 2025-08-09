package org.bouncycastle.pqc.crypto.sphincsplus;

class HarakaSXof extends HarakaSBase {
   public String getAlgorithmName() {
      return "Haraka-S";
   }

   public HarakaSXof(byte[] var1) {
      byte[] var2 = new byte[640];
      this.update(var1, 0, var1.length);
      this.doFinal(var2, 0, var2.length);
      this.haraka512_rc = new long[10][8];
      this.haraka256_rc = new int[10][8];

      for(int var3 = 0; var3 < 10; ++var3) {
         this.interleaveConstant32(this.haraka256_rc[var3], var2, var3 << 5);
         this.interleaveConstant(this.haraka512_rc[var3], var2, var3 << 6);
      }

   }

   public void update(byte[] var1, int var2, int var3) {
      int var4 = var2;
      int var6 = var3 + this.off >> 5;

      for(int var5 = 0; var5 < var6; ++var5) {
         while(this.off < 32) {
            byte[] var10000 = this.buffer;
            int var10003 = this.off++;
            var10000[var10003] ^= var1[var4++];
         }

         this.haraka512Perm(this.buffer);
         this.off = 0;
      }

      while(var4 < var2 + var3) {
         byte[] var7 = this.buffer;
         int var9 = this.off++;
         var7[var9] ^= var1[var4++];
      }

   }

   public void update(byte var1) {
      byte[] var10000 = this.buffer;
      int var10003 = this.off++;
      var10000[var10003] ^= var1;
      if (this.off == 32) {
         this.haraka512Perm(this.buffer);
         this.off = 0;
      }

   }

   public int doFinal(byte[] var1, int var2, int var3) {
      int var4 = var3;
      byte[] var10000 = this.buffer;
      int var10001 = this.off;
      var10000[var10001] = (byte)(var10000[var10001] ^ 31);
      var10000 = this.buffer;

      for(var10000[31] = (byte)(var10000[31] ^ 128); var3 >= 32; var3 -= 32) {
         this.haraka512Perm(this.buffer);
         System.arraycopy(this.buffer, 0, var1, var2, 32);
         var2 += 32;
      }

      if (var3 > 0) {
         this.haraka512Perm(this.buffer);
         System.arraycopy(this.buffer, 0, var1, var2, var3);
      }

      this.reset();
      return var4;
   }
}
