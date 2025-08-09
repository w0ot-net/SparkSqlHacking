package org.bouncycastle.pqc.crypto.falcon;

class FalconRNG {
   byte[] bd = new byte[512];
   long bdummy_u64 = 0L;
   int ptr = 0;
   byte[] sd = new byte[256];
   long sdummy_u64 = 0L;
   int type = 0;
   FalconConversions convertor = new FalconConversions();

   void prng_init(SHAKE256 var1) {
      byte[] var2 = new byte[56];
      var1.inner_shake256_extract(var2, 0, 56);

      for(int var7 = 0; var7 < 14; ++var7) {
         int var8 = var2[(var7 << 2) + 0] & 255 | (var2[(var7 << 2) + 1] & 255) << 8 | (var2[(var7 << 2) + 2] & 255) << 16 | (var2[(var7 << 2) + 3] & 255) << 24;
         System.arraycopy(this.convertor.int_to_bytes(var8), 0, this.sd, var7 << 2, 4);
      }

      long var5 = (long)this.convertor.bytes_to_int(this.sd, 48) & 4294967295L;
      long var3 = (long)this.convertor.bytes_to_int(this.sd, 52) & 4294967295L;
      System.arraycopy(this.convertor.long_to_bytes(var5 + (var3 << 32)), 0, this.sd, 48, 8);
      this.prng_refill();
   }

   void prng_refill() {
      int[] var1 = new int[]{1634760805, 857760878, 2036477234, 1797285236};
      long var2 = this.convertor.bytes_to_long(this.sd, 48);

      for(int var4 = 0; var4 < 8; ++var4) {
         int[] var5 = new int[16];
         System.arraycopy(var1, 0, var5, 0, var1.length);
         System.arraycopy(this.convertor.bytes_to_int_array(this.sd, 0, 12), 0, var5, 4, 12);
         var5[14] ^= (int)var2;
         var5[15] ^= (int)(var2 >>> 32);

         for(int var7 = 0; var7 < 10; ++var7) {
            this.QROUND(0, 4, 8, 12, var5);
            this.QROUND(1, 5, 9, 13, var5);
            this.QROUND(2, 6, 10, 14, var5);
            this.QROUND(3, 7, 11, 15, var5);
            this.QROUND(0, 5, 10, 15, var5);
            this.QROUND(1, 6, 11, 12, var5);
            this.QROUND(2, 7, 8, 13, var5);
            this.QROUND(3, 4, 9, 14, var5);
         }

         for(int var6 = 0; var6 < 4; ++var6) {
            var5[var6] += var1[var6];
         }

         for(int var8 = 4; var8 < 14; ++var8) {
            var5[var8] += this.convertor.bytes_to_int(this.sd, 4 * var8 - 16);
         }

         var5[14] += this.convertor.bytes_to_int(this.sd, 40) ^ (int)var2;
         var5[15] += this.convertor.bytes_to_int(this.sd, 44) ^ (int)(var2 >>> 32);
         ++var2;

         for(int var9 = 0; var9 < 16; ++var9) {
            this.bd[(var4 << 2) + (var9 << 5) + 0] = (byte)var5[var9];
            this.bd[(var4 << 2) + (var9 << 5) + 1] = (byte)(var5[var9] >>> 8);
            this.bd[(var4 << 2) + (var9 << 5) + 2] = (byte)(var5[var9] >>> 16);
            this.bd[(var4 << 2) + (var9 << 5) + 3] = (byte)(var5[var9] >>> 24);
         }
      }

      System.arraycopy(this.convertor.long_to_bytes(var2), 0, this.sd, 48, 8);
      this.ptr = 0;
   }

   void prng_get_bytes(byte[] var1, int var2, int var3) {
      int var4 = var2;

      while(var3 > 0) {
         int var5 = this.bd.length - this.ptr;
         if (var5 > var3) {
            var5 = var3;
         }

         System.arraycopy(this.bd, 0, var1, var4, var5);
         var4 += var5;
         var3 -= var5;
         this.ptr += var5;
         if (this.ptr == this.bd.length) {
            this.prng_refill();
         }
      }

   }

   private void QROUND(int var1, int var2, int var3, int var4, int[] var5) {
      var5[var1] += var5[var2];
      var5[var4] ^= var5[var1];
      var5[var4] = var5[var4] << 16 | var5[var4] >>> 16;
      var5[var3] += var5[var4];
      var5[var2] ^= var5[var3];
      var5[var2] = var5[var2] << 12 | var5[var2] >>> 20;
      var5[var1] += var5[var2];
      var5[var4] ^= var5[var1];
      var5[var4] = var5[var4] << 8 | var5[var4] >>> 24;
      var5[var3] += var5[var4];
      var5[var2] ^= var5[var3];
      var5[var2] = var5[var2] << 7 | var5[var2] >>> 25;
   }

   long prng_get_u64() {
      int var1 = this.ptr;
      if (var1 >= this.bd.length - 9) {
         this.prng_refill();
         var1 = 0;
      }

      this.ptr = var1 + 8;
      return (long)this.bd[var1 + 0] & 255L | ((long)this.bd[var1 + 1] & 255L) << 8 | ((long)this.bd[var1 + 2] & 255L) << 16 | ((long)this.bd[var1 + 3] & 255L) << 24 | ((long)this.bd[var1 + 4] & 255L) << 32 | ((long)this.bd[var1 + 5] & 255L) << 40 | ((long)this.bd[var1 + 6] & 255L) << 48 | ((long)this.bd[var1 + 7] & 255L) << 56;
   }

   byte prng_get_u8() {
      byte var1 = this.bd[this.ptr++];
      if (this.ptr == this.bd.length) {
         this.prng_refill();
      }

      return var1;
   }
}
