package org.bouncycastle.pqc.crypto.falcon;

class SHAKE256 {
   long[] A = new long[25];
   byte[] dbuf = new byte[200];
   long dptr = 0L;
   private long[] RC = new long[]{1L, 32898L, -9223372036854742902L, -9223372034707259392L, 32907L, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138L, 136L, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778L, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};

   void process_block(long[] var1) {
      var1[1] = ~var1[1];
      var1[2] = ~var1[2];
      var1[8] = ~var1[8];
      var1[12] = ~var1[12];
      var1[17] = ~var1[17];
      var1[20] = ~var1[20];

      for(int var36 = 0; var36 < 24; var36 += 2) {
         long var12 = var1[1] ^ var1[6];
         long var14 = var1[11] ^ var1[16];
         var12 ^= var1[21] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         long var16 = var1[4] ^ var1[9];
         long var18 = var1[14] ^ var1[19];
         var12 ^= var1[24];
         var16 ^= var18;
         long var2 = var12 ^ var16;
         var12 = var1[2] ^ var1[7];
         var14 = var1[12] ^ var1[17];
         var12 ^= var1[22] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[0] ^ var1[5];
         var18 = var1[10] ^ var1[15];
         var12 ^= var1[20];
         var16 ^= var18;
         long var4 = var12 ^ var16;
         var12 = var1[3] ^ var1[8];
         var14 = var1[13] ^ var1[18];
         var12 ^= var1[23] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[1] ^ var1[6];
         var18 = var1[11] ^ var1[16];
         var12 ^= var1[21];
         var16 ^= var18;
         long var6 = var12 ^ var16;
         var12 = var1[4] ^ var1[9];
         var14 = var1[14] ^ var1[19];
         var12 ^= var1[24] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[2] ^ var1[7];
         var18 = var1[12] ^ var1[17];
         var12 ^= var1[22];
         var16 ^= var18;
         long var8 = var12 ^ var16;
         var12 = var1[0] ^ var1[5];
         var14 = var1[10] ^ var1[15];
         var12 ^= var1[20] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[3] ^ var1[8];
         var18 = var1[13] ^ var1[18];
         var12 ^= var1[23];
         var16 ^= var18;
         long var10 = var12 ^ var16;
         var1[0] ^= var2;
         var1[5] ^= var2;
         var1[10] ^= var2;
         var1[15] ^= var2;
         var1[20] ^= var2;
         var1[1] ^= var4;
         var1[6] ^= var4;
         var1[11] ^= var4;
         var1[16] ^= var4;
         var1[21] ^= var4;
         var1[2] ^= var6;
         var1[7] ^= var6;
         var1[12] ^= var6;
         var1[17] ^= var6;
         var1[22] ^= var6;
         var1[3] ^= var8;
         var1[8] ^= var8;
         var1[13] ^= var8;
         var1[18] ^= var8;
         var1[23] ^= var8;
         var1[4] ^= var10;
         var1[9] ^= var10;
         var1[14] ^= var10;
         var1[19] ^= var10;
         var1[24] ^= var10;
         var1[5] = var1[5] << 36 | var1[5] >>> 28;
         var1[10] = var1[10] << 3 | var1[10] >>> 61;
         var1[15] = var1[15] << 41 | var1[15] >>> 23;
         var1[20] = var1[20] << 18 | var1[20] >>> 46;
         var1[1] = var1[1] << 1 | var1[1] >>> 63;
         var1[6] = var1[6] << 44 | var1[6] >>> 20;
         var1[11] = var1[11] << 10 | var1[11] >>> 54;
         var1[16] = var1[16] << 45 | var1[16] >>> 19;
         var1[21] = var1[21] << 2 | var1[21] >>> 62;
         var1[2] = var1[2] << 62 | var1[2] >>> 2;
         var1[7] = var1[7] << 6 | var1[7] >>> 58;
         var1[12] = var1[12] << 43 | var1[12] >>> 21;
         var1[17] = var1[17] << 15 | var1[17] >>> 49;
         var1[22] = var1[22] << 61 | var1[22] >>> 3;
         var1[3] = var1[3] << 28 | var1[3] >>> 36;
         var1[8] = var1[8] << 55 | var1[8] >>> 9;
         var1[13] = var1[13] << 25 | var1[13] >>> 39;
         var1[18] = var1[18] << 21 | var1[18] >>> 43;
         var1[23] = var1[23] << 56 | var1[23] >>> 8;
         var1[4] = var1[4] << 27 | var1[4] >>> 37;
         var1[9] = var1[9] << 20 | var1[9] >>> 44;
         var1[14] = var1[14] << 39 | var1[14] >>> 25;
         var1[19] = var1[19] << 8 | var1[19] >>> 56;
         var1[24] = var1[24] << 14 | var1[24] >>> 50;
         long var34 = ~var1[12];
         long var22 = var1[6] | var1[12];
         long var24 = var1[0] ^ var22;
         var22 = var34 | var1[18];
         long var26 = var1[6] ^ var22;
         var22 = var1[18] & var1[24];
         long var28 = var1[12] ^ var22;
         var22 = var1[24] | var1[0];
         long var30 = var1[18] ^ var22;
         var22 = var1[0] & var1[6];
         long var32 = var1[24] ^ var22;
         var1[0] = var24;
         var1[6] = var26;
         var1[12] = var28;
         var1[18] = var30;
         var1[24] = var32;
         var34 = ~var1[22];
         var22 = var1[9] | var1[10];
         var24 = var1[3] ^ var22;
         var22 = var1[10] & var1[16];
         var26 = var1[9] ^ var22;
         var22 = var1[16] | var34;
         var28 = var1[10] ^ var22;
         var22 = var1[22] | var1[3];
         var30 = var1[16] ^ var22;
         var22 = var1[3] & var1[9];
         var32 = var1[22] ^ var22;
         var1[3] = var24;
         var1[9] = var26;
         var1[10] = var28;
         var1[16] = var30;
         var1[22] = var32;
         var34 = ~var1[19];
         var22 = var1[7] | var1[13];
         var24 = var1[1] ^ var22;
         var22 = var1[13] & var1[19];
         var26 = var1[7] ^ var22;
         var22 = var34 & var1[20];
         var28 = var1[13] ^ var22;
         var22 = var1[20] | var1[1];
         var30 = var34 ^ var22;
         var22 = var1[1] & var1[7];
         var32 = var1[20] ^ var22;
         var1[1] = var24;
         var1[7] = var26;
         var1[13] = var28;
         var1[19] = var30;
         var1[20] = var32;
         var34 = ~var1[17];
         var22 = var1[5] & var1[11];
         var24 = var1[4] ^ var22;
         var22 = var1[11] | var1[17];
         var26 = var1[5] ^ var22;
         var22 = var34 | var1[23];
         var28 = var1[11] ^ var22;
         var22 = var1[23] & var1[4];
         var30 = var34 ^ var22;
         var22 = var1[4] | var1[5];
         var32 = var1[23] ^ var22;
         var1[4] = var24;
         var1[5] = var26;
         var1[11] = var28;
         var1[17] = var30;
         var1[23] = var32;
         var34 = ~var1[8];
         var22 = var34 & var1[14];
         var24 = var1[2] ^ var22;
         var22 = var1[14] | var1[15];
         var26 = var34 ^ var22;
         var22 = var1[15] & var1[21];
         var28 = var1[14] ^ var22;
         var22 = var1[21] | var1[2];
         var30 = var1[15] ^ var22;
         var22 = var1[2] & var1[8];
         var32 = var1[21] ^ var22;
         var1[2] = var24;
         var1[8] = var26;
         var1[14] = var28;
         var1[15] = var30;
         var1[21] = var32;
         var1[0] ^= this.RC[var36 + 0];
         var12 = var1[6] ^ var1[9];
         var14 = var1[7] ^ var1[5];
         var12 ^= var1[8] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[24] ^ var1[22];
         var18 = var1[20] ^ var1[23];
         var12 ^= var1[21];
         var16 ^= var18;
         var2 = var12 ^ var16;
         var12 = var1[12] ^ var1[10];
         var14 = var1[13] ^ var1[11];
         var12 ^= var1[14] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[0] ^ var1[3];
         var18 = var1[1] ^ var1[4];
         var12 ^= var1[2];
         var16 ^= var18;
         var4 = var12 ^ var16;
         var12 = var1[18] ^ var1[16];
         var14 = var1[19] ^ var1[17];
         var12 ^= var1[15] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[6] ^ var1[9];
         var18 = var1[7] ^ var1[5];
         var12 ^= var1[8];
         var16 ^= var18;
         var6 = var12 ^ var16;
         var12 = var1[24] ^ var1[22];
         var14 = var1[20] ^ var1[23];
         var12 ^= var1[21] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[12] ^ var1[10];
         var18 = var1[13] ^ var1[11];
         var12 ^= var1[14];
         var16 ^= var18;
         var8 = var12 ^ var16;
         var12 = var1[0] ^ var1[3];
         var14 = var1[1] ^ var1[4];
         var12 ^= var1[2] ^ var14;
         var12 = var12 << 1 | var12 >>> 63;
         var16 = var1[18] ^ var1[16];
         var18 = var1[19] ^ var1[17];
         var12 ^= var1[15];
         var16 ^= var18;
         var10 = var12 ^ var16;
         var1[0] ^= var2;
         var1[3] ^= var2;
         var1[1] ^= var2;
         var1[4] ^= var2;
         var1[2] ^= var2;
         var1[6] ^= var4;
         var1[9] ^= var4;
         var1[7] ^= var4;
         var1[5] ^= var4;
         var1[8] ^= var4;
         var1[12] ^= var6;
         var1[10] ^= var6;
         var1[13] ^= var6;
         var1[11] ^= var6;
         var1[14] ^= var6;
         var1[18] ^= var8;
         var1[16] ^= var8;
         var1[19] ^= var8;
         var1[17] ^= var8;
         var1[15] ^= var8;
         var1[24] ^= var10;
         var1[22] ^= var10;
         var1[20] ^= var10;
         var1[23] ^= var10;
         var1[21] ^= var10;
         var1[3] = var1[3] << 36 | var1[3] >>> 28;
         var1[1] = var1[1] << 3 | var1[1] >>> 61;
         var1[4] = var1[4] << 41 | var1[4] >>> 23;
         var1[2] = var1[2] << 18 | var1[2] >>> 46;
         var1[6] = var1[6] << 1 | var1[6] >>> 63;
         var1[9] = var1[9] << 44 | var1[9] >>> 20;
         var1[7] = var1[7] << 10 | var1[7] >>> 54;
         var1[5] = var1[5] << 45 | var1[5] >>> 19;
         var1[8] = var1[8] << 2 | var1[8] >>> 62;
         var1[12] = var1[12] << 62 | var1[12] >>> 2;
         var1[10] = var1[10] << 6 | var1[10] >>> 58;
         var1[13] = var1[13] << 43 | var1[13] >>> 21;
         var1[11] = var1[11] << 15 | var1[11] >>> 49;
         var1[14] = var1[14] << 61 | var1[14] >>> 3;
         var1[18] = var1[18] << 28 | var1[18] >>> 36;
         var1[16] = var1[16] << 55 | var1[16] >>> 9;
         var1[19] = var1[19] << 25 | var1[19] >>> 39;
         var1[17] = var1[17] << 21 | var1[17] >>> 43;
         var1[15] = var1[15] << 56 | var1[15] >>> 8;
         var1[24] = var1[24] << 27 | var1[24] >>> 37;
         var1[22] = var1[22] << 20 | var1[22] >>> 44;
         var1[20] = var1[20] << 39 | var1[20] >>> 25;
         var1[23] = var1[23] << 8 | var1[23] >>> 56;
         var1[21] = var1[21] << 14 | var1[21] >>> 50;
         var34 = ~var1[13];
         var22 = var1[9] | var1[13];
         var24 = var1[0] ^ var22;
         var22 = var34 | var1[17];
         var26 = var1[9] ^ var22;
         var22 = var1[17] & var1[21];
         var28 = var1[13] ^ var22;
         var22 = var1[21] | var1[0];
         var30 = var1[17] ^ var22;
         var22 = var1[0] & var1[9];
         var32 = var1[21] ^ var22;
         var1[0] = var24;
         var1[9] = var26;
         var1[13] = var28;
         var1[17] = var30;
         var1[21] = var32;
         var34 = ~var1[14];
         var22 = var1[22] | var1[1];
         var24 = var1[18] ^ var22;
         var22 = var1[1] & var1[5];
         var26 = var1[22] ^ var22;
         var22 = var1[5] | var34;
         var28 = var1[1] ^ var22;
         var22 = var1[14] | var1[18];
         var30 = var1[5] ^ var22;
         var22 = var1[18] & var1[22];
         var32 = var1[14] ^ var22;
         var1[18] = var24;
         var1[22] = var26;
         var1[1] = var28;
         var1[5] = var30;
         var1[14] = var32;
         var34 = ~var1[23];
         var22 = var1[10] | var1[19];
         var24 = var1[6] ^ var22;
         var22 = var1[19] & var1[23];
         var26 = var1[10] ^ var22;
         var22 = var34 & var1[2];
         var28 = var1[19] ^ var22;
         var22 = var1[2] | var1[6];
         var30 = var34 ^ var22;
         var22 = var1[6] & var1[10];
         var32 = var1[2] ^ var22;
         var1[6] = var24;
         var1[10] = var26;
         var1[19] = var28;
         var1[23] = var30;
         var1[2] = var32;
         var34 = ~var1[11];
         var22 = var1[3] & var1[7];
         var24 = var1[24] ^ var22;
         var22 = var1[7] | var1[11];
         var26 = var1[3] ^ var22;
         var22 = var34 | var1[15];
         var28 = var1[7] ^ var22;
         var22 = var1[15] & var1[24];
         var30 = var34 ^ var22;
         var22 = var1[24] | var1[3];
         var32 = var1[15] ^ var22;
         var1[24] = var24;
         var1[3] = var26;
         var1[7] = var28;
         var1[11] = var30;
         var1[15] = var32;
         var34 = ~var1[16];
         var22 = var34 & var1[20];
         var24 = var1[12] ^ var22;
         var22 = var1[20] | var1[4];
         var26 = var34 ^ var22;
         var22 = var1[4] & var1[8];
         var28 = var1[20] ^ var22;
         var22 = var1[8] | var1[12];
         var30 = var1[4] ^ var22;
         var22 = var1[12] & var1[16];
         var32 = var1[8] ^ var22;
         var1[12] = var24;
         var1[16] = var26;
         var1[20] = var28;
         var1[4] = var30;
         var1[8] = var32;
         var1[0] ^= this.RC[var36 + 1];
         long var20 = var1[5];
         var1[5] = var1[18];
         var1[18] = var1[11];
         var1[11] = var1[10];
         var1[10] = var1[6];
         var1[6] = var1[22];
         var1[22] = var1[20];
         var1[20] = var1[12];
         var1[12] = var1[19];
         var1[19] = var1[15];
         var1[15] = var1[24];
         var1[24] = var1[8];
         var1[8] = var20;
         var20 = var1[1];
         var1[1] = var1[9];
         var1[9] = var1[14];
         var1[14] = var1[2];
         var1[2] = var1[13];
         var1[13] = var1[23];
         var1[23] = var1[4];
         var1[4] = var1[21];
         var1[21] = var1[16];
         var1[16] = var1[3];
         var1[3] = var1[17];
         var1[17] = var1[7];
         var1[7] = var20;
      }

      var1[1] = ~var1[1];
      var1[2] = ~var1[2];
      var1[8] = ~var1[8];
      var1[12] = ~var1[12];
      var1[17] = ~var1[17];
      var1[20] = ~var1[20];
   }

   void inner_shake256_init() {
      this.dptr = 0L;

      for(int var1 = 0; var1 < this.A.length; ++var1) {
         this.A[var1] = 0L;
      }

   }

   void inner_shake256_inject(byte[] var1, int var2, int var3) {
      long var4 = this.dptr;

      while(var3 > 0) {
         long var6 = 136L - var4;
         if (var6 > (long)var3) {
            var6 = (long)var3;
         }

         for(long var8 = 0L; var8 < var6; ++var8) {
            long var10 = var8 + var4;
            long[] var10000 = this.A;
            var10000[(int)(var10 >> 3)] ^= ((long)var1[var2 + (int)var8] & 255L) << (int)((var10 & 7L) << 3);
         }

         var4 += var6;
         var2 = (int)((long)var2 + var6);
         var3 = (int)((long)var3 - var6);
         if (var4 == 136L) {
            this.process_block(this.A);
            var4 = 0L;
         }
      }

      this.dptr = var4;
   }

   void i_shake256_flip() {
      int var1 = (int)this.dptr;
      long[] var10000 = this.A;
      var10000[var1 >> 3] ^= 31L << ((var1 & 7) << 3);
      var10000 = this.A;
      var10000[16] ^= Long.MIN_VALUE;
      this.dptr = 136L;
   }

   void inner_shake256_extract(byte[] var1, int var2, int var3) {
      int var5 = var2;
      int var4 = (int)this.dptr;

      while(var3 > 0) {
         if (var4 == 136) {
            this.process_block(this.A);
            var4 = 0;
         }

         int var6 = 136 - var4;
         if (var6 > var3) {
            var6 = var3;
         }

         for(var3 -= var6; var6-- > 0; ++var4) {
            var1[var5++] = (byte)((int)(this.A[var4 >> 3] >>> ((var4 & 7) << 3)));
         }
      }

      this.dptr = (long)var4;
   }
}
