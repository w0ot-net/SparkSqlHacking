package org.bouncycastle.pqc.crypto.gemss;

public class GeMSSUtils {
   static long NORBITS_UINT(long var0) {
      var0 |= var0 << 32;
      var0 >>>= 32;
      --var0;
      return var0 >>> 63;
   }

   static long XORBITS_UINT(long var0) {
      var0 ^= var0 << 1;
      var0 ^= var0 << 2;
      return (var0 & -8608480567731124088L) * 1229782938247303441L >>> 63;
   }

   static long ORBITS_UINT(long var0) {
      var0 |= var0 << 32;
      var0 >>>= 32;
      var0 += 4294967295L;
      return var0 >>> 32;
   }

   static long CMP_LT_UINT(long var0, long var2) {
      return (var0 >>> 63 ^ var2 >>> 63) & (var0 >>> 63) - (var2 >>> 63) >>> 63 ^ (var0 >>> 63 ^ var2 >>> 63 ^ 1L) & (var0 & Long.MAX_VALUE) - (var2 & Long.MAX_VALUE) >>> 63;
   }

   static long maskUINT(int var0) {
      return var0 != 0 ? (1L << var0) - 1L : -1L;
   }

   static int Highest_One(int var0) {
      var0 |= var0 >>> 1;
      var0 |= var0 >>> 2;
      var0 |= var0 >>> 4;
      var0 |= var0 >>> 8;
      var0 |= var0 >>> 16;
      return var0 ^ var0 >>> 1;
   }
}
