package org.bouncycastle.pqc.legacy.crypto.qtesla;

import org.bouncycastle.crypto.digests.CSHAKEDigest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

class HashUtils {
   static final int SECURE_HASH_ALGORITHM_KECCAK_128_RATE = 168;
   static final int SECURE_HASH_ALGORITHM_KECCAK_256_RATE = 136;

   static void secureHashAlgorithmKECCAK128(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5) {
      SHAKEDigest var6 = new SHAKEDigest(128);
      var6.update(var3, var4, var5);
      var6.doFinal(var0, var1, var2);
   }

   static void secureHashAlgorithmKECCAK256(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5) {
      SHAKEDigest var6 = new SHAKEDigest(256);
      var6.update(var3, var4, var5);
      var6.doFinal(var0, var1, var2);
   }

   static void customizableSecureHashAlgorithmKECCAK128Simple(byte[] var0, int var1, int var2, short var3, byte[] var4, int var5, int var6) {
      CSHAKEDigest var7 = new CSHAKEDigest(128, (byte[])null, new byte[]{(byte)var3, (byte)(var3 >> 8)});
      var7.update(var4, var5, var6);
      var7.doFinal(var0, var1, var2);
   }

   static void customizableSecureHashAlgorithmKECCAK256Simple(byte[] var0, int var1, int var2, short var3, byte[] var4, int var5, int var6) {
      CSHAKEDigest var7 = new CSHAKEDigest(256, (byte[])null, new byte[]{(byte)var3, (byte)(var3 >> 8)});
      var7.update(var4, var5, var6);
      var7.doFinal(var0, var1, var2);
   }
}
