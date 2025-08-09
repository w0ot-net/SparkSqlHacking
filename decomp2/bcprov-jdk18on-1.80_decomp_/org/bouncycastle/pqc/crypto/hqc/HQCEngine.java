package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

class HQCEngine {
   private int n;
   private int n1;
   private int n2;
   private int k;
   private int delta;
   private int w;
   private int wr;
   private int we;
   private int g;
   private int rejectionThreshold;
   private int fft;
   private int mulParam;
   private int SEED_SIZE = 40;
   private byte G_FCT_DOMAIN = 3;
   private byte K_FCT_DOMAIN = 4;
   private int N_BYTE;
   private int n1n2;
   private int N_BYTE_64;
   private int K_BYTE;
   private int K_BYTE_64;
   private int N1_BYTE_64;
   private int N1N2_BYTE_64;
   private int N1N2_BYTE;
   private int N1_BYTE;
   private int GF_POLY_WT = 5;
   private int GF_POLY_M2 = 4;
   private int SALT_SIZE_BYTES = 16;
   private int SALT_SIZE_64 = 2;
   private int[] generatorPoly;
   private int SHA512_BYTES = 64;
   private long RED_MASK;
   private GF2PolynomialCalculator gfCalculator;

   public HQCEngine(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int[] var12) {
      this.n = var1;
      this.k = var4;
      this.delta = var6;
      this.w = var7;
      this.wr = var8;
      this.we = var9;
      this.n1 = var2;
      this.n2 = var3;
      this.n1n2 = var2 * var3;
      this.generatorPoly = var12;
      this.g = var5;
      this.rejectionThreshold = var10;
      this.fft = var11;
      this.mulParam = (int)Math.ceil((double)(var3 / 128));
      this.N_BYTE = Utils.getByteSizeFromBitSize(var1);
      this.K_BYTE = var4;
      this.N_BYTE_64 = Utils.getByte64SizeFromBitSize(var1);
      this.K_BYTE_64 = Utils.getByteSizeFromBitSize(var4);
      this.N1_BYTE_64 = Utils.getByteSizeFromBitSize(var2);
      this.N1N2_BYTE_64 = Utils.getByte64SizeFromBitSize(var2 * var3);
      this.N1N2_BYTE = Utils.getByteSizeFromBitSize(var2 * var3);
      this.N1_BYTE = Utils.getByteSizeFromBitSize(var2);
      this.RED_MASK = (1L << (int)((long)var1 % 64L)) - 1L;
      this.gfCalculator = new GF2PolynomialCalculator(this.N_BYTE_64, var1, this.RED_MASK);
   }

   public void genKeyPair(byte[] var1, byte[] var2, byte[] var3) {
      byte[] var4 = new byte[this.SEED_SIZE];
      byte[] var5 = new byte[this.K_BYTE];
      KeccakRandomGenerator var6 = new KeccakRandomGenerator(256);
      var6.randomGeneratorInit(var3, (byte[])null, var3.length, 0);
      var6.squeeze(var4, 40);
      var6.squeeze(var5, this.K_BYTE);
      KeccakRandomGenerator var7 = new KeccakRandomGenerator(256);
      var7.seedExpanderInit(var4, var4.length);
      long[] var8 = new long[this.N_BYTE_64];
      long[] var9 = new long[this.N_BYTE_64];
      this.generateRandomFixedWeight(var9, var7, this.w);
      this.generateRandomFixedWeight(var8, var7, this.w);
      byte[] var10 = new byte[this.SEED_SIZE];
      var6.squeeze(var10, 40);
      KeccakRandomGenerator var11 = new KeccakRandomGenerator(256);
      var11.seedExpanderInit(var10, var10.length);
      long[] var12 = new long[this.N_BYTE_64];
      this.generatePublicKeyH(var12, var11);
      long[] var13 = new long[this.N_BYTE_64];
      this.gfCalculator.multLongs(var13, var9, var12);
      GF2PolynomialCalculator.addLongs(var13, var13, var8);
      byte[] var14 = new byte[this.N_BYTE];
      Utils.fromLongArrayToByteArray(var14, var13);
      byte[] var15 = Arrays.concatenate(var10, var14);
      byte[] var16 = Arrays.concatenate(var4, var5, var15);
      System.arraycopy(var15, 0, var1, 0, var15.length);
      System.arraycopy(var16, 0, var2, 0, var16.length);
   }

   public void encaps(byte[] var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6) {
      byte[] var7 = new byte[this.K_BYTE];
      byte[] var8 = new byte[this.SEED_SIZE];
      KeccakRandomGenerator var9 = new KeccakRandomGenerator(256);
      var9.randomGeneratorInit(var5, (byte[])null, var5.length, 0);
      var9.squeeze(var8, 40);
      byte[] var10 = new byte[this.K_BYTE];
      var9.squeeze(var10, this.K_BYTE);
      byte[] var11 = new byte[this.SEED_SIZE];
      var9.squeeze(var11, 40);
      var9.squeeze(var7, this.K_BYTE);
      byte[] var12 = new byte[this.SHA512_BYTES];
      byte[] var13 = new byte[this.K_BYTE + this.SALT_SIZE_BYTES * 2 + this.SALT_SIZE_BYTES];
      var9.squeeze(var6, this.SALT_SIZE_BYTES);
      System.arraycopy(var7, 0, var13, 0, var7.length);
      System.arraycopy(var4, 0, var13, this.K_BYTE, this.SALT_SIZE_BYTES * 2);
      System.arraycopy(var6, 0, var13, this.K_BYTE + this.SALT_SIZE_BYTES * 2, this.SALT_SIZE_BYTES);
      KeccakRandomGenerator var14 = new KeccakRandomGenerator(256);
      var14.SHAKE256_512_ds(var12, var13, var13.length, new byte[]{this.G_FCT_DOMAIN});
      long[] var15 = new long[this.N_BYTE_64];
      byte[] var16 = new byte[this.N_BYTE];
      this.extractPublicKeys(var15, var16, var4);
      long[] var17 = new long[this.N1N2_BYTE_64];
      this.encrypt(var1, var17, var15, var16, var7, var12);
      Utils.fromLongArrayToByteArray(var2, var17);
      byte[] var18 = Arrays.concatenate(var7, var1, var2);
      var14.SHAKE256_512_ds(var3, var18, var18.length, new byte[]{this.K_FCT_DOMAIN});
   }

   public int decaps(byte[] var1, byte[] var2, byte[] var3) {
      long[] var4 = new long[this.N_BYTE_64];
      byte[] var5 = new byte[40 + this.N_BYTE];
      byte[] var6 = new byte[this.K_BYTE];
      this.extractKeysFromSecretKeys(var4, var6, var5, var3);
      byte[] var7 = new byte[this.N_BYTE];
      byte[] var8 = new byte[this.N1N2_BYTE];
      byte[] var9 = new byte[this.SALT_SIZE_BYTES];
      this.extractCiphertexts(var7, var8, var9, var2);
      byte[] var10 = new byte[this.k];
      int var11 = this.decrypt(var10, var10, var6, var7, var8, var4);
      byte[] var12 = new byte[this.SHA512_BYTES];
      byte[] var13 = new byte[this.K_BYTE + this.SALT_SIZE_BYTES * 2 + this.SALT_SIZE_BYTES];
      System.arraycopy(var10, 0, var13, 0, var10.length);
      System.arraycopy(var5, 0, var13, this.K_BYTE, this.SALT_SIZE_BYTES * 2);
      System.arraycopy(var9, 0, var13, this.K_BYTE + this.SALT_SIZE_BYTES * 2, this.SALT_SIZE_BYTES);
      KeccakRandomGenerator var14 = new KeccakRandomGenerator(256);
      var14.SHAKE256_512_ds(var12, var13, var13.length, new byte[]{this.G_FCT_DOMAIN});
      long[] var15 = new long[this.N_BYTE_64];
      byte[] var16 = new byte[this.N_BYTE];
      this.extractPublicKeys(var15, var16, var5);
      byte[] var17 = new byte[this.N_BYTE];
      byte[] var18 = new byte[this.N1N2_BYTE];
      long[] var19 = new long[this.N1N2_BYTE_64];
      this.encrypt(var17, var19, var15, var16, var10, var12);
      Utils.fromLongArrayToByteArray(var18, var19);
      byte[] var20 = new byte[this.K_BYTE + this.N_BYTE + this.N1N2_BYTE];
      if (!Arrays.constantTimeAreEqual(var7, var17)) {
         var11 = 1;
      }

      if (!Arrays.constantTimeAreEqual(var8, var18)) {
         var11 = 1;
      }

      --var11;

      for(int var21 = 0; var21 < this.K_BYTE; ++var21) {
         var20[var21] = (byte)((var10[var21] & var11 ^ var6[var21] & ~var11) & 255);
      }

      System.arraycopy(var7, 0, var20, this.K_BYTE, this.N_BYTE);
      System.arraycopy(var8, 0, var20, this.K_BYTE + this.N_BYTE, this.N1N2_BYTE);
      var14.SHAKE256_512_ds(var1, var20, var20.length, new byte[]{this.K_FCT_DOMAIN});
      return -var11;
   }

   int getSessionKeySize() {
      return this.SHA512_BYTES;
   }

   private void encrypt(byte[] var1, long[] var2, long[] var3, byte[] var4, byte[] var5, byte[] var6) {
      KeccakRandomGenerator var7 = new KeccakRandomGenerator(256);
      var7.seedExpanderInit(var6, this.SEED_SIZE);
      long[] var8 = new long[this.N_BYTE_64];
      long[] var9 = new long[this.N_BYTE_64];
      long[] var10 = new long[this.N_BYTE_64];
      this.generateRandomFixedWeight(var10, var7, this.wr);
      this.generateRandomFixedWeight(var8, var7, this.we);
      this.generateRandomFixedWeight(var9, var7, this.wr);
      long[] var11 = new long[this.N_BYTE_64];
      this.gfCalculator.multLongs(var11, var10, var3);
      GF2PolynomialCalculator.addLongs(var11, var11, var9);
      Utils.fromLongArrayToByteArray(var1, var11);
      byte[] var12 = new byte[this.n1];
      long[] var13 = new long[this.N1N2_BYTE_64];
      long[] var14 = new long[this.N_BYTE_64];
      ReedSolomon.encode(var12, var5, this.K_BYTE * 8, this.n1, this.k, this.g, this.generatorPoly);
      ReedMuller.encode(var13, var12, this.n1, this.mulParam);
      System.arraycopy(var13, 0, var14, 0, var13.length);
      long[] var15 = new long[this.N_BYTE_64];
      Utils.fromByteArrayToLongArray(var15, var4);
      long[] var16 = new long[this.N_BYTE_64];
      this.gfCalculator.multLongs(var16, var10, var15);
      GF2PolynomialCalculator.addLongs(var16, var16, var14);
      GF2PolynomialCalculator.addLongs(var16, var16, var8);
      Utils.resizeArray(var2, this.n1n2, var16, this.n, this.N1N2_BYTE_64, this.N1N2_BYTE_64);
   }

   private int decrypt(byte[] var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, long[] var6) {
      long[] var7 = new long[this.N_BYTE_64];
      Utils.fromByteArrayToLongArray(var7, var4);
      long[] var8 = new long[this.N1N2_BYTE_64];
      Utils.fromByteArrayToLongArray(var8, var5);
      long[] var9 = new long[this.N_BYTE_64];
      System.arraycopy(var8, 0, var9, 0, var8.length);
      long[] var10 = new long[this.N_BYTE_64];
      this.gfCalculator.multLongs(var10, var6, var7);
      GF2PolynomialCalculator.addLongs(var10, var10, var9);
      byte[] var11 = new byte[this.n1];
      ReedMuller.decode(var11, var10, this.n1, this.mulParam);
      ReedSolomon.decode(var2, var11, this.n1, this.fft, this.delta, this.k, this.g);
      System.arraycopy(var2, 0, var1, 0, var1.length);
      return 0;
   }

   private void generateRandomFixedWeight(long[] var1, KeccakRandomGenerator var2, int var3) {
      int[] var4 = new int[this.wr];
      byte[] var5 = new byte[this.wr * 4];
      int[] var6 = new int[this.wr];
      int[] var7 = new int[this.wr];
      long[] var8 = new long[this.wr];
      var2.expandSeed(var5, 4 * var3);
      Pack.littleEndianToInt(var5, 0, var4, 0, var4.length);

      for(int var9 = 0; var9 < var3; ++var9) {
         var6[var9] = (int)((long)var9 + ((long)var4[var9] & 4294967295L) % (long)(this.n - var9));
      }

      for(int var17 = var3 - 1; var17 >= 0; --var17) {
         int var10 = 0;

         for(int var11 = var17 + 1; var11 < var3; ++var11) {
            if (var6[var11] == var6[var17]) {
               var10 |= 1;
            }
         }

         int var22 = -var10;
         var6[var17] = var22 & var17 ^ ~var22 & var6[var17];
      }

      for(int var18 = 0; var18 < var3; ++var18) {
         var7[var18] = var6[var18] >>> 6;
         int var21 = var6[var18] & 63;
         var8[var18] = 1L << var21;
      }

      long var19 = 0L;

      for(int var23 = 0; var23 < this.N_BYTE_64; ++var23) {
         var19 = 0L;

         for(int var12 = 0; var12 < var3; ++var12) {
            int var13 = var23 - var7[var12];
            int var14 = 1 ^ (var13 | -var13) >>> 31;
            long var15 = (long)(-var14);
            var19 |= var8[var12] & var15;
         }

         var1[var23] |= var19;
      }

   }

   void generatePublicKeyH(long[] var1, KeccakRandomGenerator var2) {
      byte[] var3 = new byte[this.N_BYTE];
      var2.expandSeed(var3, this.N_BYTE);
      long[] var4 = new long[this.N_BYTE_64];
      Utils.fromByteArrayToLongArray(var4, var3);
      int var10001 = this.N_BYTE_64 - 1;
      var4[var10001] &= Utils.bitMask((long)this.n, 64L);
      System.arraycopy(var4, 0, var1, 0, var1.length);
   }

   private void extractPublicKeys(long[] var1, byte[] var2, byte[] var3) {
      byte[] var4 = new byte[this.SEED_SIZE];
      System.arraycopy(var3, 0, var4, 0, var4.length);
      KeccakRandomGenerator var5 = new KeccakRandomGenerator(256);
      var5.seedExpanderInit(var4, var4.length);
      long[] var6 = new long[this.N_BYTE_64];
      this.generatePublicKeyH(var6, var5);
      System.arraycopy(var6, 0, var1, 0, var1.length);
      System.arraycopy(var3, 40, var2, 0, var2.length);
   }

   private void extractKeysFromSecretKeys(long[] var1, byte[] var2, byte[] var3, byte[] var4) {
      byte[] var5 = new byte[this.SEED_SIZE];
      System.arraycopy(var4, 0, var5, 0, var5.length);
      System.arraycopy(var4, this.SEED_SIZE, var2, 0, this.K_BYTE);
      KeccakRandomGenerator var6 = new KeccakRandomGenerator(256);
      var6.seedExpanderInit(var5, var5.length);
      this.generateRandomFixedWeight(var1, var6, this.w);
      System.arraycopy(var4, this.SEED_SIZE + this.K_BYTE, var3, 0, var3.length);
   }

   private void extractCiphertexts(byte[] var1, byte[] var2, byte[] var3, byte[] var4) {
      System.arraycopy(var4, 0, var1, 0, var1.length);
      System.arraycopy(var4, var1.length, var2, 0, var2.length);
      System.arraycopy(var4, var1.length + var2.length, var3, 0, var3.length);
   }
}
