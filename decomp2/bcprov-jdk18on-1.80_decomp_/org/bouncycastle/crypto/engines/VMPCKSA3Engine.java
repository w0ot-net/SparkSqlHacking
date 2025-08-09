package org.bouncycastle.crypto.engines;

public class VMPCKSA3Engine extends VMPCEngine {
   public String getAlgorithmName() {
      return "VMPC-KSA3";
   }

   protected void initKey(byte[] var1, byte[] var2) {
      this.s = 0;
      this.P = new byte[256];

      for(int var3 = 0; var3 < 256; ++var3) {
         this.P[var3] = (byte)var3;
      }

      for(int var5 = 0; var5 < 768; ++var5) {
         this.s = this.P[this.s + this.P[var5 & 255] + var1[var5 % var1.length] & 255];
         byte var4 = this.P[var5 & 255];
         this.P[var5 & 255] = this.P[this.s & 255];
         this.P[this.s & 255] = var4;
      }

      for(int var6 = 0; var6 < 768; ++var6) {
         this.s = this.P[this.s + this.P[var6 & 255] + var2[var6 % var2.length] & 255];
         byte var8 = this.P[var6 & 255];
         this.P[var6 & 255] = this.P[this.s & 255];
         this.P[this.s & 255] = var8;
      }

      for(int var7 = 0; var7 < 768; ++var7) {
         this.s = this.P[this.s + this.P[var7 & 255] + var1[var7 % var1.length] & 255];
         byte var9 = this.P[var7 & 255];
         this.P[var7 & 255] = this.P[this.s & 255];
         this.P[this.s & 255] = var9;
      }

      this.n = 0;
   }
}
