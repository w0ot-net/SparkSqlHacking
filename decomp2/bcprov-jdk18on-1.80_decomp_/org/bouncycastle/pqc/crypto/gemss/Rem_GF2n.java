package org.bouncycastle.pqc.crypto.gemss;

abstract class Rem_GF2n {
   protected long mask;
   protected int ki;
   protected int ki64;

   public abstract void rem_gf2n(long[] var1, int var2, long[] var3);

   public abstract void rem_gf2n_xor(long[] var1, int var2, long[] var3);

   public static class REM192_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
      private final int k3;
      private final int k364;
      private final int ki_k3;

      REM192_SPECIALIZED_TRINOMIAL_GF2X(int var1, int var2, int var3, int var4, long var5) {
         this.k3 = var1;
         this.ki = var2;
         this.ki64 = var3;
         this.k364 = var4;
         this.mask = var5;
         this.ki_k3 = var2 - var1;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[2] >>> this.ki ^ var3[3] << this.ki64;
         long var6 = var3[3] >>> this.ki ^ var3[4] << this.ki64;
         long var8 = var3[4] >>> this.ki ^ var3[5] << this.ki64;
         var1[var2 + 1] = var3[1] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         var1[var2 + 2] = (var3[2] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3) & this.mask;
         var4 ^= var8 >>> this.ki_k3;
         var1[var2] = var3[0] ^ var4 ^ var4 << this.k3;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[2] >>> this.ki ^ var3[3] << this.ki64;
         long var6 = var3[3] >>> this.ki ^ var3[4] << this.ki64;
         long var8 = var3[4] >>> this.ki ^ var3[5] << this.ki64;
         var1[var2 + 1] ^= var3[1] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         var1[var2 + 2] ^= (var3[2] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3) & this.mask;
         var4 ^= var8 >>> this.ki_k3;
         var1[var2] ^= var3[0] ^ var4 ^ var4 << this.k3;
      }
   }

   public static class REM288_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
      private final int k3;
      private final int k364;
      private final int k364ki;
      private final int k3_ki;

      public REM288_SPECIALIZED_TRINOMIAL_GF2X(int var1, int var2, int var3, int var4, long var5) {
         this.k3 = var1;
         this.ki = var2;
         this.ki64 = var3;
         this.k364 = var4;
         this.mask = var5;
         this.k364ki = var4 + var2;
         this.k3_ki = var1 - var2;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[5] >>> this.ki ^ var3[6] << this.ki64;
         long var6 = var3[6] >>> this.ki ^ var3[7] << this.ki64;
         var1[var2 + 2] = var3[2] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         long var8 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         var1[var2 + 3] = var3[3] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3;
         long var10 = var3[8] >>> this.ki;
         var6 = var3[4] >>> this.ki ^ var3[5] << this.ki64 ^ var8 >>> this.k364ki ^ var10 << this.k3_ki;
         var1[var2 + 4] = (var3[4] ^ var10 ^ var8 >>> this.k364 ^ var10 << this.k3) & this.mask;
         var1[var2] = var3[0] ^ var6 ^ var6 << this.k3;
         var1[var2 + 1] = var3[1] ^ var4 ^ var4 << this.k3 ^ var6 >>> this.k364;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[5] >>> this.ki ^ var3[6] << this.ki64;
         long var6 = var3[6] >>> this.ki ^ var3[7] << this.ki64;
         var1[var2 + 2] ^= var3[2] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         long var8 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         var1[var2 + 3] ^= var3[3] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 = var3[8] >>> this.ki;
         var1[var2 + 4] ^= (var3[4] ^ var6 ^ var8 >>> this.k364 ^ var6 << this.k3) & this.mask;
         var8 = var3[4] >>> this.ki ^ var3[5] << this.ki64 ^ var8 >>> this.k364ki ^ var6 << this.k3_ki;
         var1[var2] ^= var3[0] ^ var8 ^ var8 << this.k3;
         var1[var2 + 1] ^= var3[1] ^ var4 ^ var4 << this.k3 ^ var8 >>> this.k364;
      }
   }

   public static class REM384_SPECIALIZED358_TRINOMIAL_GF2X extends Rem_GF2n {
      private final int k3;
      private final int k364;
      private final int k364ki;
      private final int k3_ki;

      public REM384_SPECIALIZED358_TRINOMIAL_GF2X(int var1, int var2, int var3, int var4, long var5) {
         this.k3 = var1;
         this.ki = var2;
         this.ki64 = var3;
         this.k364 = var4;
         this.mask = var5;
         this.k364ki = var4 + var2;
         this.k3_ki = var1 - var2;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[6] >>> this.ki ^ var3[7] << this.ki64;
         long var6 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         var1[var2 + 2] = var3[2] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         long var8 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         var1[var2 + 3] = var3[3] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         var1[var2 + 4] = var3[4] ^ var6 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var10 = var3[5] >>> this.ki ^ var3[6] << this.ki64 ^ var6 >>> this.k364ki ^ var8 << this.k3_ki;
         var1[var2 + 5] = (var3[5] ^ var8 ^ var6 >>> this.k364) & this.mask;
         var1[var2] = var3[0] ^ var10 ^ var10 << this.k3;
         var1[var2 + 1] = var3[1] ^ var4 ^ var10 >>> this.k364 ^ var4 << this.k3;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[6] >>> this.ki ^ var3[7] << this.ki64;
         long var6 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         var1[var2 + 2] ^= var3[2] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         long var8 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         var1[var2 + 3] ^= var3[3] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         var1[var2 + 4] ^= var3[4] ^ var6 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         var1[var2 + 5] ^= (var3[5] ^ var8 ^ var6 >>> this.k364) & this.mask;
         var6 = var3[5] >>> this.ki ^ var3[6] << this.ki64 ^ var6 >>> this.k364ki ^ var8 << this.k3_ki;
         var1[var2] ^= var3[0] ^ var6 ^ var6 << this.k3;
         var1[var2 + 1] ^= var3[1] ^ var4 ^ var6 >>> this.k364 ^ var4 << this.k3;
      }
   }

   public static class REM384_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
      private final int k3;
      private final int k364;
      private final int k364ki;
      private final int k3_ki;

      public REM384_SPECIALIZED_TRINOMIAL_GF2X(int var1, int var2, int var3, int var4, long var5) {
         this.k3 = var1;
         this.ki = var2;
         this.ki64 = var3;
         this.k364 = var4;
         this.mask = var5;
         this.k364ki = var4 + var2;
         this.k3_ki = var1 - var2;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         long var6 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         long var8 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         long var10 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var12 = var3[5] >>> this.ki ^ var3[6] << this.ki64 ^ var6 >>> this.k364ki ^ var8 << this.k3_ki;
         long var14 = var3[6] >>> this.ki ^ var3[7] << this.ki64 ^ var8 >>> this.k364ki ^ var10 << this.k3_ki;
         var1[var2] = var3[0] ^ var12;
         var1[var2 + 1] = var3[1] ^ var14 ^ var12 << this.k3;
         var1[var2 + 2] = var3[2] ^ var4 ^ var12 >>> this.k364 ^ var14 << this.k3;
         var1[var2 + 3] = var3[3] ^ var6 ^ var14 >>> this.k364 ^ var4 << this.k3;
         var1[var2 + 4] = var3[4] ^ var8 ^ var4 >>> this.k364 ^ var6 << this.k3;
         var1[var2 + 5] = (var3[5] ^ var10 ^ var6 >>> this.k364) & this.mask;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         long var6 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         long var8 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         long var10 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var12 = var3[5] >>> this.ki ^ var3[6] << this.ki64 ^ var6 >>> this.k364ki ^ var8 << this.k3_ki;
         long var14 = var3[6] >>> this.ki ^ var3[7] << this.ki64 ^ var8 >>> this.k364ki ^ var10 << this.k3_ki;
         var1[var2] ^= var3[0] ^ var12;
         var1[var2 + 1] ^= var3[1] ^ var14 ^ var12 << this.k3;
         var1[var2 + 2] ^= var3[2] ^ var4 ^ var12 >>> this.k364 ^ var14 << this.k3;
         var1[var2 + 3] ^= var3[3] ^ var6 ^ var14 >>> this.k364 ^ var4 << this.k3;
         var1[var2 + 4] ^= var3[4] ^ var8 ^ var4 >>> this.k364 ^ var6 << this.k3;
         var1[var2 + 5] ^= (var3[5] ^ var10 ^ var6 >>> this.k364) & this.mask;
      }
   }

   public static class REM384_TRINOMIAL_GF2X extends Rem_GF2n {
      private final int k3;
      private final int k364;
      private final int ki_k3;

      public REM384_TRINOMIAL_GF2X(int var1, int var2, int var3, int var4, long var5) {
         this.k3 = var1;
         this.ki = var2;
         this.ki64 = var3;
         this.k364 = var4;
         this.mask = var5;
         this.ki_k3 = var2 - var1;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[5] >>> this.ki ^ var3[6] << this.ki64;
         long var6 = var3[6] >>> this.ki ^ var3[7] << this.ki64;
         long var8 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         long var10 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         long var12 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         long var14 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var16 = var4 ^ var14 >>> this.ki_k3;
         var1[var2] = var3[0] ^ var16 ^ var16 << this.k3;
         var1[var2 + 1] = var3[1] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         var1[var2 + 2] = var3[2] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var1[var2 + 3] = var3[3] ^ var10 ^ var8 >>> this.k364 ^ var10 << this.k3;
         var1[var2 + 4] = var3[4] ^ var12 ^ var10 >>> this.k364 ^ var12 << this.k3;
         var1[var2 + 5] = (var3[5] ^ var14 ^ var12 >>> this.k364 ^ var14 << this.k3) & this.mask;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[5] >>> this.ki ^ var3[6] << this.ki64;
         long var6 = var3[6] >>> this.ki ^ var3[7] << this.ki64;
         long var8 = var3[7] >>> this.ki ^ var3[8] << this.ki64;
         long var10 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         long var12 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         long var14 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var16 = var4 ^ var14 >>> this.ki_k3;
         var1[var2] ^= var3[0] ^ var16 ^ var16 << this.k3;
         var1[var2 + 1] ^= var3[1] ^ var6 ^ var4 >>> this.k364 ^ var6 << this.k3;
         var1[var2 + 2] ^= var3[2] ^ var8 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var1[var2 + 3] ^= var3[3] ^ var10 ^ var8 >>> this.k364 ^ var10 << this.k3;
         var1[var2 + 4] ^= var3[4] ^ var12 ^ var10 >>> this.k364 ^ var12 << this.k3;
         var1[var2 + 5] ^= (var3[5] ^ var14 ^ var12 >>> this.k364 ^ var14 << this.k3) & this.mask;
      }
   }

   public static class REM402_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
      private final int k3;
      private final int k364;

      public REM402_SPECIALIZED_TRINOMIAL_GF2X(int var1, int var2, int var3, int var4, long var5) {
         this.k3 = var1;
         this.ki = var2;
         this.ki64 = var3;
         this.k364 = var4;
         this.mask = var5;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         long var6 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var8 = var3[11] >>> this.ki ^ var3[12] << this.ki64;
         long var10 = var3[12] >>> this.ki;
         long var12 = var4 >>> 39 ^ var6 << 25 ^ var3[6] >>> this.ki ^ var3[7] << this.ki64;
         long var14 = var6 >>> 39 ^ var8 << 25 ^ var3[7] >>> this.ki ^ var3[8] << this.ki64;
         long var16 = var8 >>> 39 ^ var10 << 25 ^ var3[8] >>> this.ki ^ var3[9] << this.ki64;
         var1[var2] = var3[0] ^ var12;
         var1[var2 + 1] = var3[1] ^ var14;
         var1[var2 + 2] = var3[2] ^ var16 ^ var12 << this.k3;
         var1[var2 + 3] = var3[3] ^ var4 ^ var12 >>> this.k364 ^ var14 << this.k3;
         var1[var2 + 4] = var3[4] ^ var6 ^ var14 >>> this.k364 ^ var16 << this.k3;
         var1[var2 + 5] = var3[5] ^ var8 ^ var16 >>> this.k364 ^ var4 << this.k3;
         var1[var2 + 6] = (var3[6] ^ var10 ^ var4 >>> this.k364) & this.mask;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         long var6 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var8 = var3[11] >>> this.ki ^ var3[12] << this.ki64;
         long var10 = var3[12] >>> this.ki;
         long var12 = var4 >>> 39 ^ var6 << 25 ^ var3[6] >>> this.ki ^ var3[7] << this.ki64;
         long var14 = var6 >>> 39 ^ var8 << 25 ^ var3[7] >>> this.ki ^ var3[8] << this.ki64;
         long var16 = var8 >>> 39 ^ var10 << 25 ^ var3[8] >>> this.ki ^ var3[9] << this.ki64;
         var1[var2] ^= var3[0] ^ var12;
         var1[var2 + 1] ^= var3[1] ^ var14;
         var1[var2 + 2] ^= var3[2] ^ var16 ^ var12 << this.k3;
         var1[var2 + 3] ^= var3[3] ^ var4 ^ var12 >>> this.k364 ^ var14 << this.k3;
         var1[var2 + 4] ^= var3[4] ^ var6 ^ var14 >>> this.k364 ^ var16 << this.k3;
         var1[var2 + 5] ^= var3[5] ^ var8 ^ var16 >>> this.k364 ^ var4 << this.k3;
         var1[var2 + 6] ^= (var3[6] ^ var10 ^ var4 >>> this.k364) & this.mask;
      }
   }

   public static class REM544_PENTANOMIAL_GF2X extends Rem_GF2n {
      private final int k1;
      private final int k2;
      private final int k3;
      private final int k164;
      private final int k264;
      private final int k364;
      private final int ki_k3;
      private final int ki_k2;
      private final int ki_k1;

      public REM544_PENTANOMIAL_GF2X(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, long var9) {
         this.k1 = var1;
         this.k2 = var2;
         this.k3 = var3;
         this.ki = var4;
         this.ki64 = var5;
         this.k164 = var6;
         this.k264 = var7;
         this.k364 = var8;
         this.mask = var9;
         this.ki_k3 = var4 - var3;
         this.ki_k2 = var4 - var2;
         this.ki_k1 = var4 - var1;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[16] >>> this.ki;
         long var6 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         long var8 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         var1[var2 + 1] = var3[1] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 ^= var4 >>> this.ki_k3 ^ var4 >>> this.ki_k2 ^ var4 >>> this.ki_k1;
         var1[var2] = var3[0] ^ var6 ^ var6 << this.k1 ^ var6 << this.k2 ^ var6 << this.k3;
         var6 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         var1[var2 + 2] = var3[2] ^ var6 ^ var8 >>> this.k164 ^ var6 << this.k1 ^ var8 >>> this.k264 ^ var6 << this.k2 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[11] >>> this.ki ^ var3[12] << this.ki64;
         var1[var2 + 3] = var3[3] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 = var3[12] >>> this.ki ^ var3[13] << this.ki64;
         var1[var2 + 4] = var3[4] ^ var6 ^ var8 >>> this.k164 ^ var6 << this.k1 ^ var8 >>> this.k264 ^ var6 << this.k2 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[13] >>> this.ki ^ var3[14] << this.ki64;
         var1[var2 + 5] = var3[5] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 = var3[14] >>> this.ki ^ var3[15] << this.ki64;
         var1[var2 + 6] = var3[6] ^ var6 ^ var8 >>> this.k164 ^ var6 << this.k1 ^ var8 >>> this.k264 ^ var6 << this.k2 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[15] >>> this.ki ^ var3[16] << this.ki64;
         var1[var2 + 7] = var3[7] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var1[var2 + 8] = (var3[8] ^ var4 ^ var8 >>> this.k164 ^ var4 << this.k1 ^ var8 >>> this.k264 ^ var4 << this.k2 ^ var8 >>> this.k364 ^ var4 << this.k3) & this.mask;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[16] >>> this.ki;
         long var6 = var3[8] >>> this.ki ^ var3[9] << this.ki64;
         long var8 = var3[9] >>> this.ki ^ var3[10] << this.ki64;
         var1[var2 + 1] ^= var3[1] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 ^= var4 >>> this.ki_k3 ^ var4 >>> this.ki_k2 ^ var4 >>> this.ki_k1;
         var1[var2] ^= var3[0] ^ var6 ^ var6 << this.k1 ^ var6 << this.k2 ^ var6 << this.k3;
         var6 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         var1[var2 + 2] ^= var3[2] ^ var6 ^ var8 >>> this.k164 ^ var6 << this.k1 ^ var8 >>> this.k264 ^ var6 << this.k2 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[11] >>> this.ki ^ var3[12] << this.ki64;
         var1[var2 + 3] ^= var3[3] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 = var3[12] >>> this.ki ^ var3[13] << this.ki64;
         var1[var2 + 4] ^= var3[4] ^ var6 ^ var8 >>> this.k164 ^ var6 << this.k1 ^ var8 >>> this.k264 ^ var6 << this.k2 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[13] >>> this.ki ^ var3[14] << this.ki64;
         var1[var2 + 5] ^= var3[5] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var6 = var3[14] >>> this.ki ^ var3[15] << this.ki64;
         var1[var2 + 6] ^= var3[6] ^ var6 ^ var8 >>> this.k164 ^ var6 << this.k1 ^ var8 >>> this.k264 ^ var6 << this.k2 ^ var8 >>> this.k364 ^ var6 << this.k3;
         var8 = var3[15] >>> this.ki ^ var3[16] << this.ki64;
         var1[var2 + 7] ^= var3[7] ^ var8 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2 ^ var6 >>> this.k364 ^ var8 << this.k3;
         var1[var2 + 8] ^= (var3[8] ^ var4 ^ var8 >>> this.k164 ^ var4 << this.k1 ^ var8 >>> this.k264 ^ var4 << this.k2 ^ var8 >>> this.k364 ^ var4 << this.k3) & this.mask;
      }
   }

   public static class REM544_PENTANOMIAL_K3_IS_128_GF2X extends Rem_GF2n {
      private final int k1;
      private final int k2;
      private final int k164;
      private final int k264;

      public REM544_PENTANOMIAL_K3_IS_128_GF2X(int var1, int var2, int var3, int var4, int var5, int var6, long var7) {
         this.k1 = var1;
         this.k2 = var2;
         this.ki = var3;
         this.ki64 = var4;
         this.k164 = var5;
         this.k264 = var6;
         this.mask = var7;
      }

      public void rem_gf2n(long[] var1, int var2, long[] var3) {
         long var4 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var6 = var3[11] >>> this.ki ^ var3[12] << this.ki64;
         long var8 = var3[12] >>> this.ki ^ var3[13] << this.ki64;
         var1[var2 + 4] = var3[4] ^ var8 ^ var4 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2;
         long var10 = var3[13] >>> this.ki ^ var3[14] << this.ki64;
         var1[var2 + 5] = var3[5] ^ var10 ^ var6 ^ var8 >>> this.k164 ^ var10 << this.k1 ^ var8 >>> this.k264 ^ var10 << this.k2;
         long var12 = var3[14] >>> this.ki ^ var3[15] << this.ki64;
         var1[var2 + 6] = var3[6] ^ var12 ^ var8 ^ var10 >>> this.k164 ^ var12 << this.k1 ^ var10 >>> this.k264 ^ var12 << this.k2;
         var8 = var3[15] >>> this.ki ^ var3[16] << this.ki64;
         var1[var2 + 7] = var3[7] ^ var8 ^ var10 ^ var12 >>> this.k164 ^ var8 << this.k1 ^ var12 >>> this.k264 ^ var8 << this.k2;
         var10 = var3[16] >>> this.ki;
         var1[var2 + 8] = (var3[8] ^ var10 ^ var12 ^ var8 >>> this.k164 ^ var10 << this.k1 ^ var8 >>> this.k264 ^ var10 << this.k2) & this.mask;
         var12 = (var3[8] ^ var12) >>> this.ki ^ (var3[9] ^ var8) << this.ki64 ^ var3[16] >>> this.k264;
         var8 = (var3[9] ^ var8) >>> this.ki ^ (var3[10] ^ var10) << this.ki64;
         var1[var2] = var3[0] ^ var12 ^ var12 << this.k1 ^ var12 << this.k2;
         var1[var2 + 1] = var3[1] ^ var8 ^ var12 >>> this.k164 ^ var8 << this.k1 ^ var12 >>> this.k264 ^ var8 << this.k2;
         var1[var2 + 2] = var3[2] ^ var4 ^ var12 ^ var8 >>> this.k164 ^ var4 << this.k1 ^ var8 >>> this.k264 ^ var4 << this.k2;
         var1[var2 + 3] = var3[3] ^ var6 ^ var8 ^ var4 >>> this.k164 ^ var6 << this.k1 ^ var4 >>> this.k264 ^ var6 << this.k2;
      }

      public void rem_gf2n_xor(long[] var1, int var2, long[] var3) {
         long var4 = var3[10] >>> this.ki ^ var3[11] << this.ki64;
         long var6 = var3[11] >>> this.ki ^ var3[12] << this.ki64;
         long var8 = var3[12] >>> this.ki ^ var3[13] << this.ki64;
         var1[var2 + 4] ^= var3[4] ^ var8 ^ var4 ^ var6 >>> this.k164 ^ var8 << this.k1 ^ var6 >>> this.k264 ^ var8 << this.k2;
         long var10 = var3[13] >>> this.ki ^ var3[14] << this.ki64;
         var1[var2 + 5] ^= var3[5] ^ var10 ^ var6 ^ var8 >>> this.k164 ^ var10 << this.k1 ^ var8 >>> this.k264 ^ var10 << this.k2;
         long var12 = var3[14] >>> this.ki ^ var3[15] << this.ki64;
         var1[var2 + 6] ^= var3[6] ^ var12 ^ var8 ^ var10 >>> this.k164 ^ var12 << this.k1 ^ var10 >>> this.k264 ^ var12 << this.k2;
         var8 = var3[15] >>> this.ki ^ var3[16] << this.ki64;
         var1[var2 + 7] ^= var3[7] ^ var8 ^ var10 ^ var12 >>> this.k164 ^ var8 << this.k1 ^ var12 >>> this.k264 ^ var8 << this.k2;
         var10 = var3[16] >>> this.ki;
         var1[var2 + 8] ^= (var3[8] ^ var10 ^ var12 ^ var8 >>> this.k164 ^ var10 << this.k1 ^ var8 >>> this.k264 ^ var10 << this.k2) & this.mask;
         var12 = (var3[8] ^ var12) >>> this.ki ^ (var3[9] ^ var8) << this.ki64 ^ var3[16] >>> this.k264;
         var8 = (var3[9] ^ var8) >>> this.ki ^ (var3[10] ^ var10) << this.ki64;
         var1[var2] ^= var3[0] ^ var12 ^ var12 << this.k1 ^ var12 << this.k2;
         var1[var2 + 1] ^= var3[1] ^ var8 ^ var12 >>> this.k164 ^ var8 << this.k1 ^ var12 >>> this.k264 ^ var8 << this.k2;
         var1[var2 + 2] ^= var3[2] ^ var4 ^ var12 ^ var8 >>> this.k164 ^ var4 << this.k1 ^ var8 >>> this.k264 ^ var4 << this.k2;
         var1[var2 + 3] ^= var3[3] ^ var6 ^ var8 ^ var4 >>> this.k164 ^ var6 << this.k1 ^ var4 >>> this.k264 ^ var6 << this.k2;
      }
   }
}
