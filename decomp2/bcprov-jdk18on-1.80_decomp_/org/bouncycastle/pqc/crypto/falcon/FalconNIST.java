package org.bouncycastle.pqc.crypto.falcon;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

class FalconNIST {
   int NONCELEN;
   int LOGN;
   private int N;
   private SecureRandom rand;
   private int CRYPTO_SECRETKEYBYTES;
   private int CRYPTO_PUBLICKEYBYTES;
   int CRYPTO_BYTES;
   private FalconCodec codec = new FalconCodec();

   FalconNIST(int var1, int var2, SecureRandom var3) {
      this.rand = var3;
      this.LOGN = var1;
      this.NONCELEN = var2;
      this.N = 1 << var1;
      this.CRYPTO_PUBLICKEYBYTES = 1 + 14 * this.N / 8;
      if (var1 == 10) {
         this.CRYPTO_SECRETKEYBYTES = 2305;
         this.CRYPTO_BYTES = 1330;
      } else if (var1 != 9 && var1 != 8) {
         if (var1 != 7 && var1 != 6) {
            this.CRYPTO_SECRETKEYBYTES = 1 + this.N * 2 + this.N;
            this.CRYPTO_BYTES = 690;
         } else {
            this.CRYPTO_SECRETKEYBYTES = 1 + 7 * this.N * 2 / 8 + this.N;
            this.CRYPTO_BYTES = 690;
         }
      } else {
         this.CRYPTO_SECRETKEYBYTES = 1 + 6 * this.N * 2 / 8 + this.N;
         this.CRYPTO_BYTES = 690;
      }

   }

   byte[][] crypto_sign_keypair(byte[] var1, int var2, byte[] var3, int var4) {
      byte[] var5 = new byte[this.N];
      byte[] var6 = new byte[this.N];
      byte[] var7 = new byte[this.N];
      short[] var8 = new short[this.N];
      byte[] var9 = new byte[48];
      SHAKE256 var10 = new SHAKE256();
      FalconKeyGen var13 = new FalconKeyGen();
      this.rand.nextBytes(var9);
      var10.inner_shake256_init();
      var10.inner_shake256_inject(var9, 0, var9.length);
      var10.i_shake256_flip();
      var13.keygen(var10, var5, 0, var6, 0, var7, 0, (byte[])null, 0, var8, 0, this.LOGN);
      var3[var4 + 0] = (byte)(80 + this.LOGN);
      int var11 = 1;
      int var12 = this.codec.trim_i8_encode(var3, var4 + var11, this.CRYPTO_SECRETKEYBYTES - var11, var5, 0, this.LOGN, this.codec.max_fg_bits[this.LOGN]);
      if (var12 == 0) {
         throw new IllegalStateException("f encode failed");
      } else {
         byte[] var14 = Arrays.copyOfRange(var3, var4 + var11, var11 + var12);
         var11 += var12;
         var12 = this.codec.trim_i8_encode(var3, var4 + var11, this.CRYPTO_SECRETKEYBYTES - var11, var6, 0, this.LOGN, this.codec.max_fg_bits[this.LOGN]);
         if (var12 == 0) {
            throw new IllegalStateException("g encode failed");
         } else {
            byte[] var15 = Arrays.copyOfRange(var3, var4 + var11, var11 + var12);
            var11 += var12;
            var12 = this.codec.trim_i8_encode(var3, var4 + var11, this.CRYPTO_SECRETKEYBYTES - var11, var7, 0, this.LOGN, this.codec.max_FG_bits[this.LOGN]);
            if (var12 == 0) {
               throw new IllegalStateException("F encode failed");
            } else {
               byte[] var16 = Arrays.copyOfRange(var3, var4 + var11, var11 + var12);
               var11 += var12;
               if (var11 != this.CRYPTO_SECRETKEYBYTES) {
                  throw new IllegalStateException("secret key encoding failed");
               } else {
                  var1[var2 + 0] = (byte)(0 + this.LOGN);
                  var12 = this.codec.modq_encode(var1, var2 + 1, this.CRYPTO_PUBLICKEYBYTES - 1, var8, 0, this.LOGN);
                  if (var12 != this.CRYPTO_PUBLICKEYBYTES - 1) {
                     throw new IllegalStateException("public key encoding failed");
                  } else {
                     return new byte[][]{Arrays.copyOfRange((byte[])var1, 1, var1.length), var14, var15, var16};
                  }
               }
            }
         }
      }
   }

   byte[] crypto_sign(boolean var1, byte[] var2, byte[] var3, int var4, int var5, byte[] var6, int var7) {
      byte[] var8 = new byte[this.N];
      byte[] var9 = new byte[this.N];
      byte[] var10 = new byte[this.N];
      byte[] var11 = new byte[this.N];
      short[] var12 = new short[this.N];
      short[] var13 = new short[this.N];
      byte[] var14 = new byte[48];
      byte[] var15 = new byte[this.NONCELEN];
      SHAKE256 var16 = new SHAKE256();
      FalconSign var20 = new FalconSign();
      FalconVrfy var21 = new FalconVrfy();
      FalconCommon var22 = new FalconCommon();
      int var17 = 0;
      int var18 = this.codec.trim_i8_decode(var8, 0, this.LOGN, this.codec.max_fg_bits[this.LOGN], var6, var7 + var17, this.CRYPTO_SECRETKEYBYTES - var17);
      if (var18 == 0) {
         throw new IllegalStateException("f decode failed");
      } else {
         var17 += var18;
         var18 = this.codec.trim_i8_decode(var9, 0, this.LOGN, this.codec.max_fg_bits[this.LOGN], var6, var7 + var17, this.CRYPTO_SECRETKEYBYTES - var17);
         if (var18 == 0) {
            throw new IllegalStateException("g decode failed");
         } else {
            var17 += var18;
            var18 = this.codec.trim_i8_decode(var10, 0, this.LOGN, this.codec.max_FG_bits[this.LOGN], var6, var7 + var17, this.CRYPTO_SECRETKEYBYTES - var17);
            if (var18 == 0) {
               throw new IllegalArgumentException("F decode failed");
            } else {
               var17 += var18;
               if (var17 != this.CRYPTO_SECRETKEYBYTES - 1) {
                  throw new IllegalStateException("full key not used");
               } else if (!var21.complete_private(var11, 0, var8, 0, var9, 0, var10, 0, this.LOGN, new short[2 * this.N], 0)) {
                  throw new IllegalStateException("complete_private failed");
               } else {
                  this.rand.nextBytes(var15);
                  var16.inner_shake256_init();
                  var16.inner_shake256_inject(var15, 0, this.NONCELEN);
                  var16.inner_shake256_inject(var3, var4, var5);
                  var16.i_shake256_flip();
                  var22.hash_to_point_vartime(var16, var13, 0, this.LOGN);
                  this.rand.nextBytes(var14);
                  var16.inner_shake256_init();
                  var16.inner_shake256_inject(var14, 0, var14.length);
                  var16.i_shake256_flip();
                  var20.sign_dyn(var12, 0, var16, var8, 0, var9, 0, var10, 0, var11, 0, var13, 0, this.LOGN, new FalconFPR[10 * this.N], 0);
                  byte[] var23 = new byte[this.CRYPTO_BYTES - 2 - this.NONCELEN];
                  int var19;
                  if (var1) {
                     var23[0] = (byte)(32 + this.LOGN);
                     var19 = this.codec.comp_encode(var23, 1, var23.length - 1, var12, 0, this.LOGN);
                     if (var19 == 0) {
                        throw new IllegalStateException("signature failed to generate");
                     }

                     ++var19;
                  } else {
                     var19 = this.codec.comp_encode(var23, 0, var23.length, var12, 0, this.LOGN);
                     if (var19 == 0) {
                        throw new IllegalStateException("signature failed to generate");
                     }
                  }

                  var2[0] = (byte)(48 + this.LOGN);
                  System.arraycopy(var15, 0, var2, 1, this.NONCELEN);
                  System.arraycopy(var23, 0, var2, 1 + this.NONCELEN, var19);
                  return Arrays.copyOfRange((byte[])var2, 0, 1 + this.NONCELEN + var19);
               }
            }
         }
      }
   }

   int crypto_sign_open(boolean var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, int var6) {
      short[] var7 = new short[this.N];
      short[] var8 = new short[this.N];
      short[] var9 = new short[this.N];
      SHAKE256 var10 = new SHAKE256();
      FalconVrfy var13 = new FalconVrfy();
      FalconCommon var14 = new FalconCommon();
      if (this.codec.modq_decode(var7, 0, this.LOGN, var5, var6, this.CRYPTO_PUBLICKEYBYTES - 1) != this.CRYPTO_PUBLICKEYBYTES - 1) {
         return -1;
      } else {
         var13.to_ntt_monty(var7, 0, this.LOGN);
         int var11 = var2.length;
         int var12 = var4.length;
         if (var1) {
            if (var11 < 1 || var2[0] != (byte)(32 + this.LOGN)) {
               return -1;
            }

            if (this.codec.comp_decode(var9, 0, this.LOGN, var2, 1, var11 - 1) != var11 - 1) {
               return -1;
            }
         } else if (var11 < 1 || this.codec.comp_decode(var9, 0, this.LOGN, var2, 0, var11) != var11) {
            return -1;
         }

         var10.inner_shake256_init();
         var10.inner_shake256_inject(var3, 0, this.NONCELEN);
         var10.inner_shake256_inject(var4, 0, var12);
         var10.i_shake256_flip();
         var14.hash_to_point_vartime(var10, var8, 0, this.LOGN);
         if (var13.verify_raw(var8, 0, var9, 0, var7, 0, this.LOGN, new short[this.N], 0) == 0) {
            return -1;
         } else {
            return 0;
         }
      }
   }
}
