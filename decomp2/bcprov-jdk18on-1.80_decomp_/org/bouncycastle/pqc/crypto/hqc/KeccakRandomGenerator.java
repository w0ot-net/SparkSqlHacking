package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;

class KeccakRandomGenerator {
   private static long[] KeccakRoundConstants = new long[]{1L, 32898L, -9223372036854742902L, -9223372034707259392L, 32907L, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138L, 136L, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778L, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
   protected long[] state;
   protected byte[] dataQueue;
   protected int rate;
   protected int bitsInQueue;
   protected int fixedOutputLength;

   public KeccakRandomGenerator() {
      this(288);
   }

   public KeccakRandomGenerator(int var1) {
      this.state = new long[26];
      this.dataQueue = new byte[192];
      this.init(var1);
   }

   private void init(int var1) {
      switch (var1) {
         case 128:
         case 224:
         case 256:
         case 288:
         case 384:
         case 512:
            this.initSponge(1600 - (var1 << 1));
            return;
         default:
            throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
      }
   }

   private void initSponge(int var1) {
      if (var1 > 0 && var1 < 1600 && var1 % 64 == 0) {
         this.rate = var1;
         Arrays.fill(this.state, 0L);
         Arrays.fill((byte[])this.dataQueue, (byte)0);
         this.bitsInQueue = 0;
         this.fixedOutputLength = (1600 - var1) / 2;
      } else {
         throw new IllegalStateException("invalid rate value");
      }
   }

   private static void keccakPermutation(long[] var0) {
      long var1 = var0[0];
      long var3 = var0[1];
      long var5 = var0[2];
      long var7 = var0[3];
      long var9 = var0[4];
      long var11 = var0[5];
      long var13 = var0[6];
      long var15 = var0[7];
      long var17 = var0[8];
      long var19 = var0[9];
      long var21 = var0[10];
      long var23 = var0[11];
      long var25 = var0[12];
      long var27 = var0[13];
      long var29 = var0[14];
      long var31 = var0[15];
      long var33 = var0[16];
      long var35 = var0[17];
      long var37 = var0[18];
      long var39 = var0[19];
      long var41 = var0[20];
      long var43 = var0[21];
      long var45 = var0[22];
      long var47 = var0[23];
      long var49 = var0[24];

      for(int var51 = 0; var51 < 24; ++var51) {
         long var52 = var1 ^ var11 ^ var21 ^ var31 ^ var41;
         long var54 = var3 ^ var13 ^ var23 ^ var33 ^ var43;
         long var56 = var5 ^ var15 ^ var25 ^ var35 ^ var45;
         long var58 = var7 ^ var17 ^ var27 ^ var37 ^ var47;
         long var60 = var9 ^ var19 ^ var29 ^ var39 ^ var49;
         long var62 = (var54 << 1 | var54 >>> -1) ^ var60;
         long var64 = (var56 << 1 | var56 >>> -1) ^ var52;
         long var66 = (var58 << 1 | var58 >>> -1) ^ var54;
         long var68 = (var60 << 1 | var60 >>> -1) ^ var56;
         long var70 = (var52 << 1 | var52 >>> -1) ^ var58;
         var1 ^= var62;
         var11 ^= var62;
         var21 ^= var62;
         var31 ^= var62;
         var41 ^= var62;
         var3 ^= var64;
         var13 ^= var64;
         var23 ^= var64;
         var33 ^= var64;
         var43 ^= var64;
         var5 ^= var66;
         var15 ^= var66;
         var25 ^= var66;
         var35 ^= var66;
         var45 ^= var66;
         var7 ^= var68;
         var17 ^= var68;
         var27 ^= var68;
         var37 ^= var68;
         var47 ^= var68;
         var9 ^= var70;
         var19 ^= var70;
         var29 ^= var70;
         var39 ^= var70;
         var49 ^= var70;
         var54 = var3 << 1 | var3 >>> 63;
         var3 = var13 << 44 | var13 >>> 20;
         var13 = var19 << 20 | var19 >>> 44;
         var19 = var45 << 61 | var45 >>> 3;
         var45 = var29 << 39 | var29 >>> 25;
         var29 = var41 << 18 | var41 >>> 46;
         var41 = var5 << 62 | var5 >>> 2;
         var5 = var25 << 43 | var25 >>> 21;
         var25 = var27 << 25 | var27 >>> 39;
         var27 = var39 << 8 | var39 >>> 56;
         var39 = var47 << 56 | var47 >>> 8;
         var47 = var31 << 41 | var31 >>> 23;
         var31 = var9 << 27 | var9 >>> 37;
         var9 = var49 << 14 | var49 >>> 50;
         var49 = var43 << 2 | var43 >>> 62;
         var43 = var17 << 55 | var17 >>> 9;
         var17 = var33 << 45 | var33 >>> 19;
         var33 = var11 << 36 | var11 >>> 28;
         var11 = var7 << 28 | var7 >>> 36;
         var7 = var37 << 21 | var37 >>> 43;
         var37 = var35 << 15 | var35 >>> 49;
         var35 = var23 << 10 | var23 >>> 54;
         var23 = var15 << 6 | var15 >>> 58;
         var15 = var21 << 3 | var21 >>> 61;
         var52 = var1 ^ ~var3 & var5;
         long var128 = var3 ^ ~var5 & var7;
         var5 ^= ~var7 & var9;
         var7 ^= ~var9 & var1;
         var9 ^= ~var1 & var3;
         var3 = var128;
         long var123 = var11 ^ ~var13 & var15;
         long var129 = var13 ^ ~var15 & var17;
         var15 ^= ~var17 & var19;
         var17 ^= ~var19 & var11;
         var19 ^= ~var11 & var13;
         var11 = var123;
         var13 = var129;
         long var124 = var54 ^ ~var23 & var25;
         long var130 = var23 ^ ~var25 & var27;
         var25 ^= ~var27 & var29;
         var27 ^= ~var29 & var54;
         var29 ^= ~var54 & var23;
         var21 = var124;
         var23 = var130;
         long var125 = var31 ^ ~var33 & var35;
         var54 = var33 ^ ~var35 & var37;
         var35 ^= ~var37 & var39;
         var37 ^= ~var39 & var31;
         var39 ^= ~var31 & var33;
         var31 = var125;
         var33 = var54;
         long var126 = var41 ^ ~var43 & var45;
         var54 = var43 ^ ~var45 & var47;
         var45 ^= ~var47 & var49;
         var47 ^= ~var49 & var41;
         var49 ^= ~var41 & var43;
         var41 = var126;
         var43 = var54;
         var1 = var52 ^ KeccakRoundConstants[var51];
      }

      var0[0] = var1;
      var0[1] = var3;
      var0[2] = var5;
      var0[3] = var7;
      var0[4] = var9;
      var0[5] = var11;
      var0[6] = var13;
      var0[7] = var15;
      var0[8] = var17;
      var0[9] = var19;
      var0[10] = var21;
      var0[11] = var23;
      var0[12] = var25;
      var0[13] = var27;
      var0[14] = var29;
      var0[15] = var31;
      var0[16] = var33;
      var0[17] = var35;
      var0[18] = var37;
      var0[19] = var39;
      var0[20] = var41;
      var0[21] = var43;
      var0[22] = var45;
      var0[23] = var47;
      var0[24] = var49;
   }

   private void keccakIncAbsorb(byte[] var1, int var2) {
      int var3 = 0;
      int var4 = this.rate >> 3;

      while((long)var2 + this.state[25] >= (long)var4) {
         for(int var5 = 0; (long)var5 < (long)var4 - this.state[25]; ++var5) {
            int var6 = (int)(this.state[25] + (long)var5) >> 3;
            long[] var10000 = this.state;
            var10000[var6] ^= toUnsignedLong(var1[var5 + var3] & 255) << (int)(8L * (this.state[25] + (long)var5 & 7L));
         }

         var2 = (int)((long)var2 - ((long)var4 - this.state[25]));
         var3 = (int)((long)var3 + ((long)var4 - this.state[25]));
         this.state[25] = 0L;
         keccakPermutation(this.state);
      }

      for(int var7 = 0; var7 < var2; ++var7) {
         int var8 = (int)(this.state[25] + (long)var7) >> 3;
         long[] var9 = this.state;
         var9[var8] ^= toUnsignedLong(var1[var7 + var3] & 255) << (int)(8L * (this.state[25] + (long)var7 & 7L));
      }

      long[] var10 = this.state;
      var10[25] += (long)var2;
   }

   private void keccakIncFinalize(int var1) {
      int var2 = this.rate >> 3;
      long[] var10000 = this.state;
      int var10001 = (int)this.state[25] >> 3;
      var10000[var10001] ^= toUnsignedLong(var1) << (int)(8L * (this.state[25] & 7L));
      var10000 = this.state;
      var10000[var2 - 1 >> 3] ^= toUnsignedLong(128) << 8 * (var2 - 1 & 7);
      this.state[25] = 0L;
   }

   private void keccakIncSqueeze(byte[] var1, int var2) {
      int var3 = this.rate >> 3;

      int var4;
      for(var4 = 0; var4 < var2 && (long)var4 < this.state[25]; ++var4) {
         var1[var4] = (byte)((int)(this.state[(int)((long)var3 - this.state[25] + (long)var4 >> 3)] >> (int)(8L * ((long)var3 - this.state[25] + (long)var4 & 7L))));
      }

      int var5 = var4;
      var2 -= var4;
      long[] var10000 = this.state;

      for(var10000[25] -= (long)var4; var2 > 0; this.state[25] = (long)(var3 - var4)) {
         keccakPermutation(this.state);

         for(var4 = 0; var4 < var2 && var4 < var3; ++var4) {
            var1[var5 + var4] = (byte)((int)(this.state[var4 >> 3] >> 8 * (var4 & 7)));
         }

         var5 += var4;
         var2 -= var4;
      }

   }

   public void squeeze(byte[] var1, int var2) {
      this.keccakIncSqueeze(var1, var2);
   }

   public void randomGeneratorInit(byte[] var1, byte[] var2, int var3, int var4) {
      byte[] var5 = new byte[]{1};
      this.keccakIncAbsorb(var1, var3);
      this.keccakIncAbsorb(var2, var4);
      this.keccakIncAbsorb(var5, var5.length);
      this.keccakIncFinalize(31);
   }

   public void seedExpanderInit(byte[] var1, int var2) {
      byte[] var3 = new byte[]{2};
      this.keccakIncAbsorb(var1, var2);
      this.keccakIncAbsorb(var3, 1);
      this.keccakIncFinalize(31);
   }

   public void expandSeed(byte[] var1, int var2) {
      int var3 = var2 & 7;
      this.keccakIncSqueeze(var1, var2 - var3);
      if (var3 != 0) {
         byte[] var4 = new byte[8];
         this.keccakIncSqueeze(var4, 8);
         System.arraycopy(var4, 0, var1, var2 - var3, var3);
      }

   }

   public void SHAKE256_512_ds(byte[] var1, byte[] var2, int var3, byte[] var4) {
      Arrays.fill(this.state, 0L);
      this.keccakIncAbsorb(var2, var3);
      this.keccakIncAbsorb(var4, var4.length);
      this.keccakIncFinalize(31);
      this.keccakIncSqueeze(var1, 64);
   }

   private static long toUnsignedLong(int var0) {
      return (long)var0 & 4294967295L;
   }
}
