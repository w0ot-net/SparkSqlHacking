package org.bouncycastle.pqc.crypto.gemss;

abstract class Mul_GF2x {
   public abstract void mul_gf2x(Pointer var1, Pointer var2, Pointer var3);

   public abstract void sqr_gf2x(long[] var1, long[] var2, int var3);

   public abstract void mul_gf2x_xor(Pointer var1, Pointer var2, Pointer var3);

   private static long SQR32_NO_SIMD_GF2X(long var0) {
      var0 = (var0 ^ var0 << 16) & 281470681808895L;
      var0 = (var0 ^ var0 << 8) & 71777214294589695L;
      var0 = (var0 ^ var0 << 4) & 1085102592571150095L;
      var0 = (var0 ^ var0 << 2) & 3689348814741910323L;
      return (var0 ^ var0 << 1) & 6148914691236517205L;
   }

   private static long SQR64LOW_NO_SIMD_GF2X(long var0) {
      var0 = (var0 & 4294967295L ^ var0 << 16) & 281470681808895L;
      var0 = (var0 ^ var0 << 8) & 71777214294589695L;
      var0 = (var0 ^ var0 << 4) & 1085102592571150095L;
      var0 = (var0 ^ var0 << 2) & 3689348814741910323L;
      return (var0 ^ var0 << 1) & 6148914691236517205L;
   }

   private static void SQR64_NO_SIMD_GF2X(long[] var0, int var1, long var2) {
      var0[var1 + 1] = SQR32_NO_SIMD_GF2X(var2 >>> 32);
      var0[var1] = SQR64LOW_NO_SIMD_GF2X(var2);
   }

   private static void SQR128_NO_SIMD_GF2X(long[] var0, int var1, long[] var2, int var3) {
      SQR64_NO_SIMD_GF2X(var0, var1 + 2, var2[var3 + 1]);
      SQR64_NO_SIMD_GF2X(var0, var1, var2[var3]);
   }

   private static void SQR256_NO_SIMD_GF2X(long[] var0, int var1, long[] var2, int var3) {
      SQR128_NO_SIMD_GF2X(var0, var1 + 4, var2, var3 + 2);
      SQR128_NO_SIMD_GF2X(var0, var1, var2, var3);
   }

   private static long MUL32_NO_SIMD_GF2X(long var0, long var2) {
      long var4 = -(var2 & 1L) & var0;
      var4 ^= (-(var2 >>> 1 & 1L) & var0) << 1;
      var4 ^= (-(var2 >>> 2 & 1L) & var0) << 2;
      var4 ^= (-(var2 >>> 3 & 1L) & var0) << 3;
      var4 ^= (-(var2 >>> 4 & 1L) & var0) << 4;
      var4 ^= (-(var2 >>> 5 & 1L) & var0) << 5;
      var4 ^= (-(var2 >>> 6 & 1L) & var0) << 6;
      var4 ^= (-(var2 >>> 7 & 1L) & var0) << 7;
      var4 ^= (-(var2 >>> 8 & 1L) & var0) << 8;
      var4 ^= (-(var2 >>> 9 & 1L) & var0) << 9;
      var4 ^= (-(var2 >>> 10 & 1L) & var0) << 10;
      var4 ^= (-(var2 >>> 11 & 1L) & var0) << 11;
      var4 ^= (-(var2 >>> 12 & 1L) & var0) << 12;
      var4 ^= (-(var2 >>> 13 & 1L) & var0) << 13;
      var4 ^= (-(var2 >>> 14 & 1L) & var0) << 14;
      var4 ^= (-(var2 >>> 15 & 1L) & var0) << 15;
      var4 ^= (-(var2 >>> 16 & 1L) & var0) << 16;
      var4 ^= (-(var2 >>> 17 & 1L) & var0) << 17;
      var4 ^= (-(var2 >>> 18 & 1L) & var0) << 18;
      var4 ^= (-(var2 >>> 19 & 1L) & var0) << 19;
      var4 ^= (-(var2 >>> 20 & 1L) & var0) << 20;
      var4 ^= (-(var2 >>> 21 & 1L) & var0) << 21;
      var4 ^= (-(var2 >>> 22 & 1L) & var0) << 22;
      var4 ^= (-(var2 >>> 23 & 1L) & var0) << 23;
      var4 ^= (-(var2 >>> 24 & 1L) & var0) << 24;
      var4 ^= (-(var2 >>> 25 & 1L) & var0) << 25;
      var4 ^= (-(var2 >>> 26 & 1L) & var0) << 26;
      var4 ^= (-(var2 >>> 27 & 1L) & var0) << 27;
      var4 ^= (-(var2 >>> 28 & 1L) & var0) << 28;
      var4 ^= (-(var2 >>> 29 & 1L) & var0) << 29;
      var4 ^= (-(var2 >>> 30 & 1L) & var0) << 30;
      var4 ^= (-(var2 >>> 31 & 1L) & var0) << 31;
      return var4;
   }

   private static void MUL64_NO_SIMD_GF2X(long[] var0, int var1, long var2, long var4) {
      long var6 = -(var4 & 1L) & var2;
      long var10 = -(var4 >>> 63) & var2;
      var6 ^= var10 << 63;
      long var8 = var10 >>> 1;
      var10 = -(var4 >>> 1 & 1L) & var2;
      var6 ^= var10 << 1;
      var8 ^= var10 >>> 63;
      var10 = -(var4 >>> 2 & 1L) & var2;
      var6 ^= var10 << 2;
      var8 ^= var10 >>> 62;
      var10 = -(var4 >>> 3 & 1L) & var2;
      var6 ^= var10 << 3;
      var8 ^= var10 >>> 61;
      var10 = -(var4 >>> 4 & 1L) & var2;
      var6 ^= var10 << 4;
      var8 ^= var10 >>> 60;
      var10 = -(var4 >>> 5 & 1L) & var2;
      var6 ^= var10 << 5;
      var8 ^= var10 >>> 59;
      var10 = -(var4 >>> 6 & 1L) & var2;
      var6 ^= var10 << 6;
      var8 ^= var10 >>> 58;
      var10 = -(var4 >>> 7 & 1L) & var2;
      var6 ^= var10 << 7;
      var8 ^= var10 >>> 57;
      var10 = -(var4 >>> 8 & 1L) & var2;
      var6 ^= var10 << 8;
      var8 ^= var10 >>> 56;
      var10 = -(var4 >>> 9 & 1L) & var2;
      var6 ^= var10 << 9;
      var8 ^= var10 >>> 55;
      var10 = -(var4 >>> 10 & 1L) & var2;
      var6 ^= var10 << 10;
      var8 ^= var10 >>> 54;
      var10 = -(var4 >>> 11 & 1L) & var2;
      var6 ^= var10 << 11;
      var8 ^= var10 >>> 53;
      var10 = -(var4 >>> 12 & 1L) & var2;
      var6 ^= var10 << 12;
      var8 ^= var10 >>> 52;
      var10 = -(var4 >>> 13 & 1L) & var2;
      var6 ^= var10 << 13;
      var8 ^= var10 >>> 51;
      var10 = -(var4 >>> 14 & 1L) & var2;
      var6 ^= var10 << 14;
      var8 ^= var10 >>> 50;
      var10 = -(var4 >>> 15 & 1L) & var2;
      var6 ^= var10 << 15;
      var8 ^= var10 >>> 49;
      var10 = -(var4 >>> 16 & 1L) & var2;
      var6 ^= var10 << 16;
      var8 ^= var10 >>> 48;
      var10 = -(var4 >>> 17 & 1L) & var2;
      var6 ^= var10 << 17;
      var8 ^= var10 >>> 47;
      var10 = -(var4 >>> 18 & 1L) & var2;
      var6 ^= var10 << 18;
      var8 ^= var10 >>> 46;
      var10 = -(var4 >>> 19 & 1L) & var2;
      var6 ^= var10 << 19;
      var8 ^= var10 >>> 45;
      var10 = -(var4 >>> 20 & 1L) & var2;
      var6 ^= var10 << 20;
      var8 ^= var10 >>> 44;
      var10 = -(var4 >>> 21 & 1L) & var2;
      var6 ^= var10 << 21;
      var8 ^= var10 >>> 43;
      var10 = -(var4 >>> 22 & 1L) & var2;
      var6 ^= var10 << 22;
      var8 ^= var10 >>> 42;
      var10 = -(var4 >>> 23 & 1L) & var2;
      var6 ^= var10 << 23;
      var8 ^= var10 >>> 41;
      var10 = -(var4 >>> 24 & 1L) & var2;
      var6 ^= var10 << 24;
      var8 ^= var10 >>> 40;
      var10 = -(var4 >>> 25 & 1L) & var2;
      var6 ^= var10 << 25;
      var8 ^= var10 >>> 39;
      var10 = -(var4 >>> 26 & 1L) & var2;
      var6 ^= var10 << 26;
      var8 ^= var10 >>> 38;
      var10 = -(var4 >>> 27 & 1L) & var2;
      var6 ^= var10 << 27;
      var8 ^= var10 >>> 37;
      var10 = -(var4 >>> 28 & 1L) & var2;
      var6 ^= var10 << 28;
      var8 ^= var10 >>> 36;
      var10 = -(var4 >>> 29 & 1L) & var2;
      var6 ^= var10 << 29;
      var8 ^= var10 >>> 35;
      var10 = -(var4 >>> 30 & 1L) & var2;
      var6 ^= var10 << 30;
      var8 ^= var10 >>> 34;
      var10 = -(var4 >>> 31 & 1L) & var2;
      var6 ^= var10 << 31;
      var8 ^= var10 >>> 33;
      var10 = -(var4 >>> 32 & 1L) & var2;
      var6 ^= var10 << 32;
      var8 ^= var10 >>> 32;
      var10 = -(var4 >>> 33 & 1L) & var2;
      var6 ^= var10 << 33;
      var8 ^= var10 >>> 31;
      var10 = -(var4 >>> 34 & 1L) & var2;
      var6 ^= var10 << 34;
      var8 ^= var10 >>> 30;
      var10 = -(var4 >>> 35 & 1L) & var2;
      var6 ^= var10 << 35;
      var8 ^= var10 >>> 29;
      var10 = -(var4 >>> 36 & 1L) & var2;
      var6 ^= var10 << 36;
      var8 ^= var10 >>> 28;
      var10 = -(var4 >>> 37 & 1L) & var2;
      var6 ^= var10 << 37;
      var8 ^= var10 >>> 27;
      var10 = -(var4 >>> 38 & 1L) & var2;
      var6 ^= var10 << 38;
      var8 ^= var10 >>> 26;
      var10 = -(var4 >>> 39 & 1L) & var2;
      var6 ^= var10 << 39;
      var8 ^= var10 >>> 25;
      var10 = -(var4 >>> 40 & 1L) & var2;
      var6 ^= var10 << 40;
      var8 ^= var10 >>> 24;
      var10 = -(var4 >>> 41 & 1L) & var2;
      var6 ^= var10 << 41;
      var8 ^= var10 >>> 23;
      var10 = -(var4 >>> 42 & 1L) & var2;
      var6 ^= var10 << 42;
      var8 ^= var10 >>> 22;
      var10 = -(var4 >>> 43 & 1L) & var2;
      var6 ^= var10 << 43;
      var8 ^= var10 >>> 21;
      var10 = -(var4 >>> 44 & 1L) & var2;
      var6 ^= var10 << 44;
      var8 ^= var10 >>> 20;
      var10 = -(var4 >>> 45 & 1L) & var2;
      var6 ^= var10 << 45;
      var8 ^= var10 >>> 19;
      var10 = -(var4 >>> 46 & 1L) & var2;
      var6 ^= var10 << 46;
      var8 ^= var10 >>> 18;
      var10 = -(var4 >>> 47 & 1L) & var2;
      var6 ^= var10 << 47;
      var8 ^= var10 >>> 17;
      var10 = -(var4 >>> 48 & 1L) & var2;
      var6 ^= var10 << 48;
      var8 ^= var10 >>> 16;
      var10 = -(var4 >>> 49 & 1L) & var2;
      var6 ^= var10 << 49;
      var8 ^= var10 >>> 15;
      var10 = -(var4 >>> 50 & 1L) & var2;
      var6 ^= var10 << 50;
      var8 ^= var10 >>> 14;
      var10 = -(var4 >>> 51 & 1L) & var2;
      var6 ^= var10 << 51;
      var8 ^= var10 >>> 13;
      var10 = -(var4 >>> 52 & 1L) & var2;
      var6 ^= var10 << 52;
      var8 ^= var10 >>> 12;
      var10 = -(var4 >>> 53 & 1L) & var2;
      var6 ^= var10 << 53;
      var8 ^= var10 >>> 11;
      var10 = -(var4 >>> 54 & 1L) & var2;
      var6 ^= var10 << 54;
      var8 ^= var10 >>> 10;
      var10 = -(var4 >>> 55 & 1L) & var2;
      var6 ^= var10 << 55;
      var8 ^= var10 >>> 9;
      var10 = -(var4 >>> 56 & 1L) & var2;
      var6 ^= var10 << 56;
      var8 ^= var10 >>> 8;
      var10 = -(var4 >>> 57 & 1L) & var2;
      var6 ^= var10 << 57;
      var8 ^= var10 >>> 7;
      var10 = -(var4 >>> 58 & 1L) & var2;
      var6 ^= var10 << 58;
      var8 ^= var10 >>> 6;
      var10 = -(var4 >>> 59 & 1L) & var2;
      var6 ^= var10 << 59;
      var8 ^= var10 >>> 5;
      var10 = -(var4 >>> 60 & 1L) & var2;
      var6 ^= var10 << 60;
      var8 ^= var10 >>> 4;
      var10 = -(var4 >>> 61 & 1L) & var2;
      var6 ^= var10 << 61;
      var8 ^= var10 >>> 3;
      var10 = -(var4 >>> 62 & 1L) & var2;
      var0[var1] = var6 ^ var10 << 62;
      var0[var1 + 1] = var8 ^ var10 >>> 2;
   }

   private static void MUL64_NO_SIMD_GF2X_XOR(long[] var0, int var1, long var2, long var4) {
      long var6 = -(var4 & 1L) & var2;
      long var10 = -(var4 >>> 63) & var2;
      var6 ^= var10 << 63;
      long var8 = var10 >>> 1;
      var10 = -(var4 >>> 1 & 1L) & var2;
      var6 ^= var10 << 1;
      var8 ^= var10 >>> 63;
      var10 = -(var4 >>> 2 & 1L) & var2;
      var6 ^= var10 << 2;
      var8 ^= var10 >>> 62;
      var10 = -(var4 >>> 3 & 1L) & var2;
      var6 ^= var10 << 3;
      var8 ^= var10 >>> 61;
      var10 = -(var4 >>> 4 & 1L) & var2;
      var6 ^= var10 << 4;
      var8 ^= var10 >>> 60;
      var10 = -(var4 >>> 5 & 1L) & var2;
      var6 ^= var10 << 5;
      var8 ^= var10 >>> 59;
      var10 = -(var4 >>> 6 & 1L) & var2;
      var6 ^= var10 << 6;
      var8 ^= var10 >>> 58;
      var10 = -(var4 >>> 7 & 1L) & var2;
      var6 ^= var10 << 7;
      var8 ^= var10 >>> 57;
      var10 = -(var4 >>> 8 & 1L) & var2;
      var6 ^= var10 << 8;
      var8 ^= var10 >>> 56;
      var10 = -(var4 >>> 9 & 1L) & var2;
      var6 ^= var10 << 9;
      var8 ^= var10 >>> 55;
      var10 = -(var4 >>> 10 & 1L) & var2;
      var6 ^= var10 << 10;
      var8 ^= var10 >>> 54;
      var10 = -(var4 >>> 11 & 1L) & var2;
      var6 ^= var10 << 11;
      var8 ^= var10 >>> 53;
      var10 = -(var4 >>> 12 & 1L) & var2;
      var6 ^= var10 << 12;
      var8 ^= var10 >>> 52;
      var10 = -(var4 >>> 13 & 1L) & var2;
      var6 ^= var10 << 13;
      var8 ^= var10 >>> 51;
      var10 = -(var4 >>> 14 & 1L) & var2;
      var6 ^= var10 << 14;
      var8 ^= var10 >>> 50;
      var10 = -(var4 >>> 15 & 1L) & var2;
      var6 ^= var10 << 15;
      var8 ^= var10 >>> 49;
      var10 = -(var4 >>> 16 & 1L) & var2;
      var6 ^= var10 << 16;
      var8 ^= var10 >>> 48;
      var10 = -(var4 >>> 17 & 1L) & var2;
      var6 ^= var10 << 17;
      var8 ^= var10 >>> 47;
      var10 = -(var4 >>> 18 & 1L) & var2;
      var6 ^= var10 << 18;
      var8 ^= var10 >>> 46;
      var10 = -(var4 >>> 19 & 1L) & var2;
      var6 ^= var10 << 19;
      var8 ^= var10 >>> 45;
      var10 = -(var4 >>> 20 & 1L) & var2;
      var6 ^= var10 << 20;
      var8 ^= var10 >>> 44;
      var10 = -(var4 >>> 21 & 1L) & var2;
      var6 ^= var10 << 21;
      var8 ^= var10 >>> 43;
      var10 = -(var4 >>> 22 & 1L) & var2;
      var6 ^= var10 << 22;
      var8 ^= var10 >>> 42;
      var10 = -(var4 >>> 23 & 1L) & var2;
      var6 ^= var10 << 23;
      var8 ^= var10 >>> 41;
      var10 = -(var4 >>> 24 & 1L) & var2;
      var6 ^= var10 << 24;
      var8 ^= var10 >>> 40;
      var10 = -(var4 >>> 25 & 1L) & var2;
      var6 ^= var10 << 25;
      var8 ^= var10 >>> 39;
      var10 = -(var4 >>> 26 & 1L) & var2;
      var6 ^= var10 << 26;
      var8 ^= var10 >>> 38;
      var10 = -(var4 >>> 27 & 1L) & var2;
      var6 ^= var10 << 27;
      var8 ^= var10 >>> 37;
      var10 = -(var4 >>> 28 & 1L) & var2;
      var6 ^= var10 << 28;
      var8 ^= var10 >>> 36;
      var10 = -(var4 >>> 29 & 1L) & var2;
      var6 ^= var10 << 29;
      var8 ^= var10 >>> 35;
      var10 = -(var4 >>> 30 & 1L) & var2;
      var6 ^= var10 << 30;
      var8 ^= var10 >>> 34;
      var10 = -(var4 >>> 31 & 1L) & var2;
      var6 ^= var10 << 31;
      var8 ^= var10 >>> 33;
      var10 = -(var4 >>> 32 & 1L) & var2;
      var6 ^= var10 << 32;
      var8 ^= var10 >>> 32;
      var10 = -(var4 >>> 33 & 1L) & var2;
      var6 ^= var10 << 33;
      var8 ^= var10 >>> 31;
      var10 = -(var4 >>> 34 & 1L) & var2;
      var6 ^= var10 << 34;
      var8 ^= var10 >>> 30;
      var10 = -(var4 >>> 35 & 1L) & var2;
      var6 ^= var10 << 35;
      var8 ^= var10 >>> 29;
      var10 = -(var4 >>> 36 & 1L) & var2;
      var6 ^= var10 << 36;
      var8 ^= var10 >>> 28;
      var10 = -(var4 >>> 37 & 1L) & var2;
      var6 ^= var10 << 37;
      var8 ^= var10 >>> 27;
      var10 = -(var4 >>> 38 & 1L) & var2;
      var6 ^= var10 << 38;
      var8 ^= var10 >>> 26;
      var10 = -(var4 >>> 39 & 1L) & var2;
      var6 ^= var10 << 39;
      var8 ^= var10 >>> 25;
      var10 = -(var4 >>> 40 & 1L) & var2;
      var6 ^= var10 << 40;
      var8 ^= var10 >>> 24;
      var10 = -(var4 >>> 41 & 1L) & var2;
      var6 ^= var10 << 41;
      var8 ^= var10 >>> 23;
      var10 = -(var4 >>> 42 & 1L) & var2;
      var6 ^= var10 << 42;
      var8 ^= var10 >>> 22;
      var10 = -(var4 >>> 43 & 1L) & var2;
      var6 ^= var10 << 43;
      var8 ^= var10 >>> 21;
      var10 = -(var4 >>> 44 & 1L) & var2;
      var6 ^= var10 << 44;
      var8 ^= var10 >>> 20;
      var10 = -(var4 >>> 45 & 1L) & var2;
      var6 ^= var10 << 45;
      var8 ^= var10 >>> 19;
      var10 = -(var4 >>> 46 & 1L) & var2;
      var6 ^= var10 << 46;
      var8 ^= var10 >>> 18;
      var10 = -(var4 >>> 47 & 1L) & var2;
      var6 ^= var10 << 47;
      var8 ^= var10 >>> 17;
      var10 = -(var4 >>> 48 & 1L) & var2;
      var6 ^= var10 << 48;
      var8 ^= var10 >>> 16;
      var10 = -(var4 >>> 49 & 1L) & var2;
      var6 ^= var10 << 49;
      var8 ^= var10 >>> 15;
      var10 = -(var4 >>> 50 & 1L) & var2;
      var6 ^= var10 << 50;
      var8 ^= var10 >>> 14;
      var10 = -(var4 >>> 51 & 1L) & var2;
      var6 ^= var10 << 51;
      var8 ^= var10 >>> 13;
      var10 = -(var4 >>> 52 & 1L) & var2;
      var6 ^= var10 << 52;
      var8 ^= var10 >>> 12;
      var10 = -(var4 >>> 53 & 1L) & var2;
      var6 ^= var10 << 53;
      var8 ^= var10 >>> 11;
      var10 = -(var4 >>> 54 & 1L) & var2;
      var6 ^= var10 << 54;
      var8 ^= var10 >>> 10;
      var10 = -(var4 >>> 55 & 1L) & var2;
      var6 ^= var10 << 55;
      var8 ^= var10 >>> 9;
      var10 = -(var4 >>> 56 & 1L) & var2;
      var6 ^= var10 << 56;
      var8 ^= var10 >>> 8;
      var10 = -(var4 >>> 57 & 1L) & var2;
      var6 ^= var10 << 57;
      var8 ^= var10 >>> 7;
      var10 = -(var4 >>> 58 & 1L) & var2;
      var6 ^= var10 << 58;
      var8 ^= var10 >>> 6;
      var10 = -(var4 >>> 59 & 1L) & var2;
      var6 ^= var10 << 59;
      var8 ^= var10 >>> 5;
      var10 = -(var4 >>> 60 & 1L) & var2;
      var6 ^= var10 << 60;
      var8 ^= var10 >>> 4;
      var10 = -(var4 >>> 61 & 1L) & var2;
      var6 ^= var10 << 61;
      var8 ^= var10 >>> 3;
      var10 = -(var4 >>> 62 & 1L) & var2;
      var0[var1] ^= var6 ^ var10 << 62;
      var0[var1 + 1] ^= var8 ^ var10 >>> 2;
   }

   private static void mul128_no_simd_gf2x(long[] var0, int var1, long[] var2, int var3, long[] var4, int var5) {
      MUL64_NO_SIMD_GF2X(var0, var1, var2[var3], var4[var5]);
      MUL64_NO_SIMD_GF2X(var0, var1 + 2, var2[var3 + 1], var4[var5 + 1]);
      var0[var1 + 2] ^= var0[var1 + 1];
      var0[var1 + 1] = var0[var1] ^ var0[var1 + 2];
      var0[var1 + 2] ^= var0[var1 + 3];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 1, var2[var3] ^ var2[var3 + 1], var4[var5] ^ var4[var5 + 1]);
   }

   private static void mul128_no_simd_gf2x(long[] var0, int var1, long var2, long var4, long var6, long var8) {
      MUL64_NO_SIMD_GF2X(var0, var1, var2, var6);
      MUL64_NO_SIMD_GF2X(var0, var1 + 2, var4, var8);
      var0[var1 + 2] ^= var0[var1 + 1];
      var0[var1 + 1] = var0[var1] ^ var0[var1 + 2];
      var0[var1 + 2] ^= var0[var1 + 3];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 1, var2 ^ var4, var6 ^ var8);
   }

   private static void mul128_no_simd_gf2x_xor(long[] var0, int var1, long var2, long var4, long var6, long var8, long[] var10) {
      MUL64_NO_SIMD_GF2X(var10, 0, var2, var6);
      MUL64_NO_SIMD_GF2X(var10, 2, var4, var8);
      var0[var1] ^= var10[0];
      var10[2] ^= var10[1];
      var0[var1 + 1] ^= var10[0] ^ var10[2];
      var0[var1 + 2] ^= var10[2] ^ var10[3];
      var0[var1 + 3] ^= var10[3];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 1, var2 ^ var4, var6 ^ var8);
   }

   public static void mul192_no_simd_gf2x(long[] var0, int var1, long[] var2, int var3, long[] var4, int var5) {
      MUL64_NO_SIMD_GF2X(var0, var1, var2[var3], var4[var5]);
      MUL64_NO_SIMD_GF2X(var0, var1 + 4, var2[var3 + 2], var4[var5 + 2]);
      MUL64_NO_SIMD_GF2X(var0, var1 + 2, var2[var3 + 1], var4[var5 + 1]);
      var0[var1 + 1] ^= var0[var1 + 2];
      var0[var1 + 3] ^= var0[var1 + 4];
      var0[var1 + 4] = var0[var1 + 3] ^ var0[var1 + 5];
      var0[var1 + 2] = var0[var1 + 3] ^ var0[var1 + 1] ^ var0[var1];
      var0[var1 + 3] = var0[var1 + 1] ^ var0[var1 + 4];
      var0[var1 + 1] ^= var0[var1];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 1, var2[var3] ^ var2[var3 + 1], var4[var5] ^ var4[var5 + 1]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 3, var2[var3 + 1] ^ var2[var3 + 2], var4[var5 + 1] ^ var4[var5 + 2]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 2, var2[var3] ^ var2[var3 + 2], var4[var5] ^ var4[var5 + 2]);
   }

   public static void mul192_no_simd_gf2x_xor(long[] var0, int var1, long[] var2, int var3, long[] var4, int var5, long[] var6) {
      MUL64_NO_SIMD_GF2X(var6, 0, var2[var3], var4[var5]);
      MUL64_NO_SIMD_GF2X(var6, 4, var2[var3 + 2], var4[var5 + 2]);
      MUL64_NO_SIMD_GF2X(var6, 2, var2[var3 + 1], var4[var5 + 1]);
      var0[var1] ^= var6[0];
      var6[1] ^= var6[2];
      var6[3] ^= var6[4];
      var6[4] = var6[3] ^ var6[5];
      var6[0] ^= var6[1];
      var0[var1 + 1] ^= var6[0];
      var0[var1 + 2] ^= var6[3] ^ var6[0];
      var0[var1 + 3] ^= var6[1] ^ var6[4];
      var0[var1 + 4] ^= var6[4];
      var0[var1 + 5] ^= var6[5];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 1, var2[var3] ^ var2[var3 + 1], var4[var5] ^ var4[var5 + 1]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 3, var2[var3 + 1] ^ var2[var3 + 2], var4[var5 + 1] ^ var4[var5 + 2]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 2, var2[var3] ^ var2[var3 + 2], var4[var5] ^ var4[var5 + 2]);
   }

   private static void mul288_no_simd_gf2x(long[] var0, int var1, long[] var2, int var3, long[] var4, int var5, long[] var6) {
      mul128_no_simd_gf2x(var0, var1, var2[var3], var2[var3 + 1], var4[var5], var4[var5 + 1]);
      MUL64_NO_SIMD_GF2X(var0, var1 + 4, var2[var3 + 2], var4[var5 + 2]);
      MUL64_NO_SIMD_GF2X(var0, var1 + 7, var2[var3 + 3], var4[var5 + 3]);
      var0[var1 + 7] ^= var0[var1 + 5];
      var0[var1 + 8] ^= MUL32_NO_SIMD_GF2X(var2[var3 + 4], var4[var5 + 4]);
      var0[var1 + 5] = var0[var1 + 7] ^ var0[var1 + 4];
      var0[var1 + 7] ^= var0[var1 + 8];
      var0[var1 + 6] = var0[var1 + 7] ^ var0[var1 + 4];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 5, var2[var3 + 2] ^ var2[var3 + 3], var4[var5 + 2] ^ var4[var5 + 3]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 7, var2[var3 + 3] ^ var2[var3 + 4], var4[var5 + 3] ^ var4[var5 + 4]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 6, var2[var3 + 2] ^ var2[var3 + 4], var4[var5 + 2] ^ var4[var5 + 4]);
      var0[var1 + 4] ^= var0[var1 + 2];
      var0[var1 + 5] ^= var0[var1 + 3];
      long var7 = var2[var3] ^ var2[var3 + 2];
      long var9 = var2[var3 + 1] ^ var2[var3 + 3];
      long var11 = var4[var5] ^ var4[var5 + 2];
      long var13 = var4[var5 + 1] ^ var4[var5 + 3];
      MUL64_NO_SIMD_GF2X(var6, 0, var7, var11);
      MUL64_NO_SIMD_GF2X(var6, 2, var9, var13);
      var6[2] ^= var6[1];
      var6[3] ^= MUL32_NO_SIMD_GF2X(var2[var3 + 4], var4[var5 + 4]);
      var0[var1 + 2] = var0[var1 + 4] ^ var0[var1] ^ var6[0];
      var0[var1 + 3] = var0[var1 + 5] ^ var0[var1 + 1] ^ var6[2] ^ var6[0];
      var6[2] ^= var6[3];
      var0[var1 + 4] ^= var0[var1 + 6] ^ var6[2] ^ var6[0];
      var0[var1 + 5] ^= var0[var1 + 7] ^ var6[2];
      var0[var1 + 6] ^= var0[var1 + 8] ^ var6[3];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 3, var7 ^ var9, var11 ^ var13);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 5, var9 ^ var2[var3 + 4], var13 ^ var4[var5 + 4]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 4, var7 ^ var2[var3 + 4], var11 ^ var4[var5 + 4]);
   }

   private static void mul288_no_simd_gf2x_xor(long[] var0, int var1, long[] var2, int var3, long[] var4, int var5, long[] var6) {
      mul128_no_simd_gf2x(var6, 0, var2[var3], var2[var3 + 1], var4[var5], var4[var5 + 1]);
      MUL64_NO_SIMD_GF2X(var6, 4, var2[var3 + 2], var4[var5 + 2]);
      MUL64_NO_SIMD_GF2X(var6, 7, var2[var3 + 3], var4[var5 + 3]);
      var6[7] ^= var6[5];
      var6[8] ^= MUL32_NO_SIMD_GF2X(var2[var3 + 4], var4[var5 + 4]);
      var6[5] = var6[7] ^ var6[4];
      var6[7] ^= var6[8];
      var6[6] = var6[7] ^ var6[4];
      var6[4] ^= var6[2];
      var6[5] ^= var6[3];
      var0[var1] ^= var6[0];
      var0[var1 + 1] ^= var6[1];
      var0[var1 + 2] ^= var6[4] ^ var6[0];
      MUL64_NO_SIMD_GF2X_XOR(var6, 5, var2[var3 + 2] ^ var2[var3 + 3], var4[var5 + 2] ^ var4[var5 + 3]);
      MUL64_NO_SIMD_GF2X_XOR(var6, 7, var2[var3 + 3] ^ var2[var3 + 4], var4[var5 + 3] ^ var4[var5 + 4]);
      MUL64_NO_SIMD_GF2X_XOR(var6, 6, var2[var3 + 2] ^ var2[var3 + 4], var4[var5 + 2] ^ var4[var5 + 4]);
      var0[var1 + 3] ^= var6[5] ^ var6[1];
      var0[var1 + 4] ^= var6[4] ^ var6[6];
      var0[var1 + 5] ^= var6[5] ^ var6[7];
      var0[var1 + 6] ^= var6[6] ^ var6[8];
      var0[var1 + 7] ^= var6[7];
      var0[var1 + 8] ^= var6[8];
      long var7 = var2[var3] ^ var2[var3 + 2];
      long var9 = var2[var3 + 1] ^ var2[var3 + 3];
      long var11 = var4[var5] ^ var4[var5 + 2];
      long var13 = var4[var5 + 1] ^ var4[var5 + 3];
      MUL64_NO_SIMD_GF2X(var6, 0, var7, var11);
      MUL64_NO_SIMD_GF2X(var6, 2, var9, var13);
      var6[2] ^= var6[1];
      var6[3] ^= MUL32_NO_SIMD_GF2X(var2[var3 + 4], var4[var5 + 4]);
      var0[var1 + 2] ^= var6[0];
      var0[var1 + 3] ^= var6[2] ^ var6[0];
      var6[2] ^= var6[3];
      var0[var1 + 4] ^= var6[2] ^ var6[0];
      var0[var1 + 5] ^= var6[2];
      var0[var1 + 6] ^= var6[3];
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 3, var7 ^ var9, var11 ^ var13);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 5, var9 ^ var2[var3 + 4], var13 ^ var4[var5 + 4]);
      MUL64_NO_SIMD_GF2X_XOR(var0, var1 + 4, var7 ^ var2[var3 + 4], var11 ^ var4[var5 + 4]);
   }

   private static void mul384_no_simd_gf2x(long[] var0, long[] var1, int var2, long[] var3, int var4, long[] var5) {
      mul192_no_simd_gf2x(var0, 0, var1, var2, var3, var4);
      mul192_no_simd_gf2x(var0, 6, var1, var2 + 3, var3, var4 + 3);
      long var6 = var1[var2] ^ var1[var2 + 3];
      long var8 = var1[var2 + 1] ^ var1[var2 + 4];
      long var10 = var1[var2 + 2] ^ var1[var2 + 5];
      long var12 = var3[var4] ^ var3[var4 + 3];
      long var14 = var3[var4 + 1] ^ var3[var4 + 4];
      long var16 = var3[var4 + 2] ^ var3[var4 + 5];
      var0[6] ^= var0[3];
      var0[7] ^= var0[4];
      var0[8] ^= var0[5];
      MUL64_NO_SIMD_GF2X(var5, 0, var6, var12);
      MUL64_NO_SIMD_GF2X(var5, 4, var10, var16);
      MUL64_NO_SIMD_GF2X(var5, 2, var8, var14);
      var0[3] = var0[6] ^ var0[0] ^ var5[0];
      var5[1] ^= var5[2];
      var5[0] ^= var5[1];
      var5[3] ^= var5[4];
      var5[4] = var5[3] ^ var5[5];
      var0[5] = var0[8] ^ var0[2] ^ var5[3] ^ var5[0];
      var0[6] ^= var0[9] ^ var5[1] ^ var5[4];
      var0[4] = var0[7] ^ var0[1] ^ var5[0];
      var0[7] ^= var0[10] ^ var5[4];
      var0[8] ^= var0[11] ^ var5[5];
      MUL64_NO_SIMD_GF2X_XOR(var0, 4, var6 ^ var8, var12 ^ var14);
      MUL64_NO_SIMD_GF2X_XOR(var0, 6, var8 ^ var10, var14 ^ var16);
      MUL64_NO_SIMD_GF2X_XOR(var0, 5, var6 ^ var10, var12 ^ var16);
   }

   private static void mul384_no_simd_gf2x_xor(long[] var0, long[] var1, int var2, long[] var3, int var4, long[] var5) {
      mul192_no_simd_gf2x(var5, 0, var1, var2, var3, var4);
      mul192_no_simd_gf2x(var5, 6, var1, var2 + 3, var3, var4 + 3);
      long var6 = var1[var2] ^ var1[var2 + 3];
      long var8 = var1[var2 + 1] ^ var1[var2 + 4];
      long var10 = var1[var2 + 2] ^ var1[var2 + 5];
      long var12 = var3[var4] ^ var3[var4 + 3];
      long var14 = var3[var4 + 1] ^ var3[var4 + 4];
      long var16 = var3[var4 + 2] ^ var3[var4 + 5];
      var5[6] ^= var5[3];
      var5[7] ^= var5[4];
      var5[8] ^= var5[5];
      var0[0] ^= var5[0];
      var0[1] ^= var5[1];
      var0[2] ^= var5[2];
      var0[3] ^= var5[6] ^ var5[0];
      var0[5] ^= var5[8] ^ var5[2];
      var0[6] ^= var5[6] ^ var5[9];
      var0[4] ^= var5[7] ^ var5[1];
      var0[7] ^= var5[7] ^ var5[10];
      var0[8] ^= var5[8] ^ var5[11];
      var0[9] ^= var5[9];
      var0[10] ^= var5[10];
      var0[11] ^= var5[11];
      MUL64_NO_SIMD_GF2X(var5, 0, var6, var12);
      MUL64_NO_SIMD_GF2X(var5, 4, var10, var16);
      MUL64_NO_SIMD_GF2X(var5, 2, var8, var14);
      var0[3] ^= var5[0];
      var5[1] ^= var5[2];
      var5[0] ^= var5[1];
      var5[3] ^= var5[4];
      var5[4] = var5[3] ^ var5[5];
      var0[5] ^= var5[3] ^ var5[0];
      var0[6] ^= var5[1] ^ var5[4];
      var0[4] ^= var5[0];
      var0[7] ^= var5[4];
      var0[8] ^= var5[5];
      MUL64_NO_SIMD_GF2X_XOR(var0, 4, var6 ^ var8, var12 ^ var14);
      MUL64_NO_SIMD_GF2X_XOR(var0, 6, var8 ^ var10, var14 ^ var16);
      MUL64_NO_SIMD_GF2X_XOR(var0, 5, var6 ^ var10, var12 ^ var16);
   }

   private static void mul416_no_simd_gf2x(long[] var0, long[] var1, int var2, long[] var3, int var4, long[] var5) {
      mul192_no_simd_gf2x(var0, 0, var1, var2, var3, var4);
      mul128_no_simd_gf2x(var0, 6, var1[var2 + 3], var1[var2 + 4], var3[var4 + 3], var3[var4 + 4]);
      MUL64_NO_SIMD_GF2X(var0, 10, var1[var2 + 5], var3[var4 + 5]);
      var0[12] = MUL32_NO_SIMD_GF2X(var1[var2 + 6], var3[var4 + 6]) ^ var0[11];
      var0[11] = var0[10] ^ var0[12];
      MUL64_NO_SIMD_GF2X_XOR(var0, 11, var1[var2 + 5] ^ var1[var2 + 6], var3[var4 + 5] ^ var3[var4 + 6]);
      var0[8] ^= var0[10];
      var0[11] ^= var0[9];
      var0[10] = var0[8] ^ var0[12];
      var0[8] ^= var0[6];
      var0[9] = var0[11] ^ var0[7];
      mul128_no_simd_gf2x_xor(var0, 8, var1[var2 + 3] ^ var1[var2 + 5], var1[var2 + 4] ^ var1[var2 + 6], var3[var4 + 3] ^ var3[var4 + 5], var3[var4 + 4] ^ var3[var4 + 6], var5);
      long var6 = var1[var2] ^ var1[var2 + 3];
      long var8 = var1[var2 + 1] ^ var1[var2 + 4];
      long var10 = var1[var2 + 2] ^ var1[var2 + 5];
      long var12 = var1[var2 + 6];
      long var14 = var3[var4] ^ var3[var4 + 3];
      long var16 = var3[var4 + 1] ^ var3[var4 + 4];
      long var18 = var3[var4 + 2] ^ var3[var4 + 5];
      long var20 = var3[var4 + 6];
      var0[6] ^= var0[3];
      var0[7] ^= var0[4];
      var0[8] ^= var0[5];
      mul128_no_simd_gf2x(var5, 0, var6, var8, var14, var16);
      MUL64_NO_SIMD_GF2X(var5, 4, var10, var18);
      var5[6] = MUL32_NO_SIMD_GF2X(var12, var20) ^ var5[5];
      var5[5] = var5[4] ^ var5[6];
      MUL64_NO_SIMD_GF2X_XOR(var5, 5, var10 ^ var12, var18 ^ var20);
      var0[3] = var0[6] ^ var0[0] ^ var5[0];
      var0[4] = var0[7] ^ var0[1] ^ var5[1];
      var5[2] ^= var5[4];
      var5[3] ^= var5[5];
      var0[5] = var0[8] ^ var0[2] ^ var5[2] ^ var5[0];
      var0[6] ^= var0[9] ^ var5[3] ^ var5[1];
      var0[7] ^= var0[10] ^ var5[2] ^ var5[6];
      var0[8] ^= var0[11] ^ var5[3];
      var0[9] ^= var0[12] ^ var5[6];
      mul128_no_simd_gf2x_xor(var0, 5, var6 ^ var10, var8 ^ var12, var14 ^ var18, var16 ^ var20, var5);
   }

   private static void mul416_no_simd_gf2x_xor(long[] var0, long[] var1, int var2, long[] var3, int var4, long[] var5, long[] var6) {
      mul192_no_simd_gf2x(var5, 0, var1, var2, var3, var4);
      mul128_no_simd_gf2x(var5, 6, var1[var2 + 3], var1[var2 + 4], var3[var4 + 3], var3[var4 + 4]);
      MUL64_NO_SIMD_GF2X(var5, 10, var1[var2 + 5], var3[var4 + 5]);
      var5[12] = MUL32_NO_SIMD_GF2X(var1[var2 + 6], var3[var4 + 6]) ^ var5[11];
      var5[11] = var5[10] ^ var5[12];
      MUL64_NO_SIMD_GF2X_XOR(var5, 11, var1[var2 + 5] ^ var1[var2 + 6], var3[var4 + 5] ^ var3[var4 + 6]);
      var5[8] ^= var5[10];
      var5[11] ^= var5[9];
      var5[10] = var5[8] ^ var5[12];
      var5[8] ^= var5[6];
      var5[9] = var5[11] ^ var5[7];
      var5[6] ^= var5[3];
      var5[7] ^= var5[4];
      var5[8] ^= var5[5];
      mul128_no_simd_gf2x_xor(var5, 8, var1[var2 + 3] ^ var1[var2 + 5], var1[var2 + 4] ^ var1[var2 + 6], var3[var4 + 3] ^ var3[var4 + 5], var3[var4 + 4] ^ var3[var4 + 6], var6);
      var0[0] ^= var5[0];
      var0[1] ^= var5[1];
      var0[2] ^= var5[2];
      var0[3] ^= var5[6] ^ var5[0];
      var0[4] ^= var5[7] ^ var5[1];
      var0[5] ^= var5[8] ^ var5[2];
      var0[6] ^= var5[6] ^ var5[9];
      var0[7] ^= var5[7] ^ var5[10];
      var0[8] ^= var5[8] ^ var5[11];
      var0[9] ^= var5[9] ^ var5[12];
      var0[10] ^= var5[10];
      var0[11] ^= var5[11];
      var0[12] ^= var5[12];
      long var7 = var1[var2] ^ var1[var2 + 3];
      long var9 = var1[var2 + 1] ^ var1[var2 + 4];
      long var11 = var1[var2 + 2] ^ var1[var2 + 5];
      long var13 = var1[var2 + 6];
      long var15 = var3[var4] ^ var3[var4 + 3];
      long var17 = var3[var4 + 1] ^ var3[var4 + 4];
      long var19 = var3[var4 + 2] ^ var3[var4 + 5];
      long var21 = var3[var4 + 6];
      mul128_no_simd_gf2x(var5, 0, var7, var9, var15, var17);
      MUL64_NO_SIMD_GF2X(var5, 4, var11, var19);
      var5[6] = MUL32_NO_SIMD_GF2X(var13, var21) ^ var5[5];
      var5[5] = var5[4] ^ var5[6];
      MUL64_NO_SIMD_GF2X_XOR(var5, 5, var11 ^ var13, var19 ^ var21);
      var0[3] ^= var5[0];
      var0[4] ^= var5[1];
      var5[2] ^= var5[4];
      var5[3] ^= var5[5];
      var0[5] ^= var5[2] ^ var5[0];
      var0[6] ^= var5[3] ^ var5[1];
      var0[7] ^= var5[2] ^ var5[6];
      var0[8] ^= var5[3];
      var0[9] ^= var5[6];
      mul128_no_simd_gf2x_xor(var0, 5, var7 ^ var11, var9 ^ var13, var15 ^ var19, var17 ^ var21, var5);
   }

   private static void mul544_no_simd_gf2x(long[] var0, long[] var1, int var2, long[] var3, int var4, long[] var5, long[] var6, long[] var7) {
      mul128_no_simd_gf2x(var0, 0, var1[var2], var1[var2 + 1], var3[var4], var3[var4 + 1]);
      mul128_no_simd_gf2x(var0, 4, var1[var2 + 2], var1[var2 + 3], var3[var4 + 2], var3[var4 + 3]);
      var0[4] ^= var0[2];
      var0[5] ^= var0[3];
      var0[2] = var0[4] ^ var0[0];
      var0[3] = var0[5] ^ var0[1];
      var0[4] ^= var0[6];
      var0[5] ^= var0[7];
      mul128_no_simd_gf2x_xor(var0, 2, var1[var2] ^ var1[var2 + 2], var1[var2 + 1] ^ var1[var2 + 3], var3[var4] ^ var3[var4 + 2], var3[var4 + 1] ^ var3[var4 + 3], var7);
      mul288_no_simd_gf2x(var0, 8, var1, var2 + 4, var3, var4 + 4, var7);
      var0[8] ^= var0[4];
      var0[9] ^= var0[5];
      var0[10] ^= var0[6];
      var0[11] ^= var0[7];
      var0[4] = var0[8] ^ var0[0];
      var0[5] = var0[9] ^ var0[1];
      var0[6] = var0[10] ^ var0[2];
      var0[7] = var0[11] ^ var0[3];
      var0[8] ^= var0[12];
      var0[9] ^= var0[13];
      var0[10] ^= var0[14];
      var0[11] ^= var0[15];
      var0[12] ^= var0[16];
      var5[0] = var1[var2] ^ var1[var2 + 4];
      var5[1] = var1[var2 + 1] ^ var1[var2 + 5];
      var5[2] = var1[var2 + 2] ^ var1[var2 + 6];
      var5[3] = var1[var2 + 3] ^ var1[var2 + 7];
      var5[4] = var1[var2 + 8];
      var6[0] = var3[var4] ^ var3[var4 + 4];
      var6[1] = var3[var4 + 1] ^ var3[var4 + 5];
      var6[2] = var3[var4 + 2] ^ var3[var4 + 6];
      var6[3] = var3[var4 + 3] ^ var3[var4 + 7];
      var6[4] = var3[var4 + 8];
      mul288_no_simd_gf2x_xor(var0, 4, var5, 0, var6, 0, var7);
   }

   private static void mul544_no_simd_gf2x_xor(long[] var0, long[] var1, int var2, long[] var3, int var4, long[] var5, long[] var6, long[] var7, long[] var8) {
      mul128_no_simd_gf2x(var7, 0, var1[var2], var1[var2 + 1], var3[var4], var3[var4 + 1]);
      mul128_no_simd_gf2x(var7, 4, var1[var2 + 2], var1[var2 + 3], var3[var4 + 2], var3[var4 + 3]);
      var7[4] ^= var7[2];
      var7[5] ^= var7[3];
      var7[2] = var7[4] ^ var7[0];
      var7[3] = var7[5] ^ var7[1];
      var7[4] ^= var7[6];
      var7[5] ^= var7[7];
      mul128_no_simd_gf2x_xor(var7, 2, var1[var2] ^ var1[var2 + 2], var1[var2 + 1] ^ var1[var2 + 3], var3[var4] ^ var3[var4 + 2], var3[var4 + 1] ^ var3[var4 + 3], var8);
      mul288_no_simd_gf2x(var7, 8, var1, var2 + 4, var3, var4 + 4, var8);
      var7[8] ^= var7[4];
      var7[9] ^= var7[5];
      var7[10] ^= var7[6];
      var7[11] ^= var7[7];
      var0[0] ^= var7[0];
      var0[1] ^= var7[1];
      var0[2] ^= var7[2];
      var0[3] ^= var7[3];
      var0[4] ^= var7[8] ^ var7[0];
      var0[5] ^= var7[9] ^ var7[1];
      var0[6] ^= var7[10] ^ var7[2];
      var0[7] ^= var7[11] ^ var7[3];
      var0[8] ^= var7[8] ^ var7[12];
      var0[9] ^= var7[9] ^ var7[13];
      var0[10] ^= var7[10] ^ var7[14];
      var0[11] ^= var7[11] ^ var7[15];
      var0[12] ^= var7[12] ^ var7[16];
      var0[13] ^= var7[13];
      var0[14] ^= var7[14];
      var0[15] ^= var7[15];
      var0[16] ^= var7[16];
      var5[0] = var1[var2] ^ var1[var2 + 4];
      var5[1] = var1[var2 + 1] ^ var1[var2 + 5];
      var5[2] = var1[var2 + 2] ^ var1[var2 + 6];
      var5[3] = var1[var2 + 3] ^ var1[var2 + 7];
      var5[4] = var1[var2 + 8];
      var6[0] = var3[var4] ^ var3[var4 + 4];
      var6[1] = var3[var4 + 1] ^ var3[var4 + 5];
      var6[2] = var3[var4 + 2] ^ var3[var4 + 6];
      var6[3] = var3[var4 + 3] ^ var3[var4 + 7];
      var6[4] = var3[var4 + 8];
      mul288_no_simd_gf2x_xor(var0, 4, var5, 0, var6, 0, var7);
   }

   public static class Mul12 extends Mul_GF2x {
      private long[] Buffer = new long[12];

      public void mul_gf2x(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul384_no_simd_gf2x(var1.array, var2.array, var2.cp, var3.array, var3.cp, this.Buffer);
      }

      public void sqr_gf2x(long[] var1, long[] var2, int var3) {
         Mul_GF2x.SQR128_NO_SIMD_GF2X(var1, 8, var2, var3 + 4);
         Mul_GF2x.SQR256_NO_SIMD_GF2X(var1, 0, var2, var3);
      }

      public void mul_gf2x_xor(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul384_no_simd_gf2x_xor(var1.array, var2.array, var2.cp, var3.array, var3.cp, this.Buffer);
      }
   }

   public static class Mul13 extends Mul_GF2x {
      private long[] Buffer = new long[13];
      private long[] Buffer2 = new long[4];

      public void mul_gf2x(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul416_no_simd_gf2x(var1.array, var2.array, var2.cp, var3.array, var3.cp, this.Buffer);
      }

      public void sqr_gf2x(long[] var1, long[] var2, int var3) {
         var1[12] = Mul_GF2x.SQR32_NO_SIMD_GF2X(var2[var3 + 6]);
         Mul_GF2x.SQR128_NO_SIMD_GF2X(var1, 8, var2, var3 + 4);
         Mul_GF2x.SQR256_NO_SIMD_GF2X(var1, 0, var2, var3);
      }

      public void mul_gf2x_xor(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul416_no_simd_gf2x_xor(var1.array, var2.array, var2.cp, var3.array, var3.cp, this.Buffer, this.Buffer2);
      }
   }

   public static class Mul17 extends Mul_GF2x {
      private long[] AA = new long[5];
      private long[] BB = new long[5];
      private long[] Buffer1 = new long[17];
      private long[] Buffer2 = new long[4];

      public void mul_gf2x(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul544_no_simd_gf2x(var1.array, var2.array, var2.cp, var3.array, var3.cp, this.AA, this.BB, this.Buffer1);
      }

      public void sqr_gf2x(long[] var1, long[] var2, int var3) {
         var1[16] = Mul_GF2x.SQR32_NO_SIMD_GF2X(var2[var3 + 8]);
         Mul_GF2x.SQR256_NO_SIMD_GF2X(var1, 8, var2, var3 + 4);
         Mul_GF2x.SQR256_NO_SIMD_GF2X(var1, 0, var2, var3);
      }

      public void mul_gf2x_xor(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul544_no_simd_gf2x_xor(var1.array, var2.array, var2.cp, var3.array, var3.cp, this.AA, this.BB, this.Buffer1, this.Buffer2);
      }
   }

   public static class Mul6 extends Mul_GF2x {
      private long[] Buffer = new long[6];

      public void mul_gf2x(Pointer var1, Pointer var2, Pointer var3) {
         mul192_no_simd_gf2x(var1.array, 0, var2.array, var2.cp, var3.array, var3.cp);
      }

      public void sqr_gf2x(long[] var1, long[] var2, int var3) {
         Mul_GF2x.SQR64_NO_SIMD_GF2X(var1, 4, var2[var3 + 2]);
         Mul_GF2x.SQR128_NO_SIMD_GF2X(var1, 0, var2, var3);
      }

      public void mul_gf2x_xor(Pointer var1, Pointer var2, Pointer var3) {
         mul192_no_simd_gf2x_xor(var1.array, var1.cp, var2.array, var2.cp, var3.array, var3.cp, this.Buffer);
      }
   }

   public static class Mul9 extends Mul_GF2x {
      private long[] Buffer = new long[9];

      public void mul_gf2x(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul288_no_simd_gf2x(var1.array, 0, var2.array, var2.cp, var3.array, var3.cp, this.Buffer);
      }

      public void sqr_gf2x(long[] var1, long[] var2, int var3) {
         var1[8] = Mul_GF2x.SQR32_NO_SIMD_GF2X(var2[var3 + 4]);
         Mul_GF2x.SQR256_NO_SIMD_GF2X(var1, 0, var2, var3);
      }

      public void mul_gf2x_xor(Pointer var1, Pointer var2, Pointer var3) {
         Mul_GF2x.mul288_no_simd_gf2x_xor(var1.array, var1.cp, var2.array, var2.cp, var3.array, var3.cp, this.Buffer);
      }
   }
}
