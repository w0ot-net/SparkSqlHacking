package org.bouncycastle.pqc.crypto.mldsa;

class Reduce {
   static int montgomeryReduce(long var0) {
      int var2 = (int)(var0 * 58728449L);
      var2 = (int)(var0 - (long)var2 * 8380417L >>> 32);
      return var2;
   }

   static int reduce32(int var0) {
      int var1 = var0 + 4194304 >> 23;
      var1 = var0 - var1 * 8380417;
      return var1;
   }

   static int conditionalAddQ(int var0) {
      var0 += var0 >> 31 & 8380417;
      return var0;
   }
}
