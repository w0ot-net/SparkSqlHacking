package org.bouncycastle.pqc.crypto.mlkem;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

class MLKEMEngine {
   private SecureRandom random;
   private MLKEMIndCpa indCpa;
   public static final int KyberN = 256;
   public static final int KyberQ = 3329;
   public static final int KyberQinv = 62209;
   public static final int KyberSymBytes = 32;
   private static final int KyberSharedSecretBytes = 32;
   public static final int KyberPolyBytes = 384;
   private static final int KyberEta2 = 2;
   private static final int KyberIndCpaMsgBytes = 32;
   private final int KyberK;
   private final int KyberPolyVecBytes;
   private final int KyberPolyCompressedBytes;
   private final int KyberPolyVecCompressedBytes;
   private final int KyberEta1;
   private final int KyberIndCpaPublicKeyBytes;
   private final int KyberIndCpaSecretKeyBytes;
   private final int KyberIndCpaBytes;
   private final int KyberPublicKeyBytes;
   private final int KyberSecretKeyBytes;
   private final int KyberCipherTextBytes;
   private final int CryptoBytes;
   private final int CryptoSecretKeyBytes;
   private final int CryptoPublicKeyBytes;
   private final int CryptoCipherTextBytes;
   private final int sessionKeyLength;
   private final Symmetric symmetric;

   public Symmetric getSymmetric() {
      return this.symmetric;
   }

   public static int getKyberEta2() {
      return 2;
   }

   public static int getKyberIndCpaMsgBytes() {
      return 32;
   }

   public int getCryptoCipherTextBytes() {
      return this.CryptoCipherTextBytes;
   }

   public int getCryptoPublicKeyBytes() {
      return this.CryptoPublicKeyBytes;
   }

   public int getCryptoSecretKeyBytes() {
      return this.CryptoSecretKeyBytes;
   }

   public int getCryptoBytes() {
      return this.CryptoBytes;
   }

   public int getKyberCipherTextBytes() {
      return this.KyberCipherTextBytes;
   }

   public int getKyberSecretKeyBytes() {
      return this.KyberSecretKeyBytes;
   }

   public int getKyberIndCpaPublicKeyBytes() {
      return this.KyberIndCpaPublicKeyBytes;
   }

   public int getKyberIndCpaSecretKeyBytes() {
      return this.KyberIndCpaSecretKeyBytes;
   }

   public int getKyberIndCpaBytes() {
      return this.KyberIndCpaBytes;
   }

   public int getKyberPublicKeyBytes() {
      return this.KyberPublicKeyBytes;
   }

   public int getKyberPolyCompressedBytes() {
      return this.KyberPolyCompressedBytes;
   }

   public int getKyberK() {
      return this.KyberK;
   }

   public int getKyberPolyVecBytes() {
      return this.KyberPolyVecBytes;
   }

   public int getKyberPolyVecCompressedBytes() {
      return this.KyberPolyVecCompressedBytes;
   }

   public int getKyberEta1() {
      return this.KyberEta1;
   }

   public MLKEMEngine(int var1) {
      this.KyberK = var1;
      switch (var1) {
         case 2:
            this.KyberEta1 = 3;
            this.KyberPolyCompressedBytes = 128;
            this.KyberPolyVecCompressedBytes = var1 * 320;
            this.sessionKeyLength = 32;
            break;
         case 3:
            this.KyberEta1 = 2;
            this.KyberPolyCompressedBytes = 128;
            this.KyberPolyVecCompressedBytes = var1 * 320;
            this.sessionKeyLength = 32;
            break;
         case 4:
            this.KyberEta1 = 2;
            this.KyberPolyCompressedBytes = 160;
            this.KyberPolyVecCompressedBytes = var1 * 352;
            this.sessionKeyLength = 32;
            break;
         default:
            throw new IllegalArgumentException("K: " + var1 + " is not supported for Crystals Kyber");
      }

      this.KyberPolyVecBytes = var1 * 384;
      this.KyberIndCpaPublicKeyBytes = this.KyberPolyVecBytes + 32;
      this.KyberIndCpaSecretKeyBytes = this.KyberPolyVecBytes;
      this.KyberIndCpaBytes = this.KyberPolyVecCompressedBytes + this.KyberPolyCompressedBytes;
      this.KyberPublicKeyBytes = this.KyberIndCpaPublicKeyBytes;
      this.KyberSecretKeyBytes = this.KyberIndCpaSecretKeyBytes + this.KyberIndCpaPublicKeyBytes + 64;
      this.KyberCipherTextBytes = this.KyberIndCpaBytes;
      this.CryptoBytes = 32;
      this.CryptoSecretKeyBytes = this.KyberSecretKeyBytes;
      this.CryptoPublicKeyBytes = this.KyberPublicKeyBytes;
      this.CryptoCipherTextBytes = this.KyberCipherTextBytes;
      this.symmetric = new Symmetric.ShakeSymmetric();
      this.indCpa = new MLKEMIndCpa(this);
   }

   public void init(SecureRandom var1) {
      this.random = var1;
   }

   public byte[][] generateKemKeyPair() {
      byte[] var1 = new byte[32];
      byte[] var2 = new byte[32];
      this.random.nextBytes(var1);
      this.random.nextBytes(var2);
      return this.generateKemKeyPairInternal(var1, var2);
   }

   public byte[][] generateKemKeyPairInternal(byte[] var1, byte[] var2) {
      byte[][] var3 = this.indCpa.generateKeyPair(var1);
      byte[] var4 = new byte[this.KyberIndCpaSecretKeyBytes];
      System.arraycopy(var3[1], 0, var4, 0, this.KyberIndCpaSecretKeyBytes);
      byte[] var5 = new byte[32];
      this.symmetric.hash_h(var5, var3[0], 0);
      byte[] var6 = new byte[this.KyberIndCpaPublicKeyBytes];
      System.arraycopy(var3[0], 0, var6, 0, this.KyberIndCpaPublicKeyBytes);
      return new byte[][]{Arrays.copyOfRange((byte[])var6, 0, var6.length - 32), Arrays.copyOfRange(var6, var6.length - 32, var6.length), var4, var5, var2, Arrays.concatenate(var1, var2)};
   }

   public byte[][] kemEncryptInternal(byte[] var1, byte[] var2) {
      byte[] var4 = new byte[64];
      byte[] var5 = new byte[64];
      System.arraycopy(var2, 0, var4, 0, 32);
      this.symmetric.hash_h(var4, var1, 32);
      this.symmetric.hash_g(var5, var4);
      byte[] var3 = this.indCpa.encrypt(var1, Arrays.copyOfRange((byte[])var4, 0, 32), Arrays.copyOfRange((byte[])var5, 32, var5.length));
      byte[] var6 = new byte[this.sessionKeyLength];
      System.arraycopy(var5, 0, var6, 0, var6.length);
      byte[][] var7 = new byte[][]{var6, var3};
      return var7;
   }

   public byte[] kemDecryptInternal(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[64];
      byte[] var4 = new byte[64];
      byte[] var5 = Arrays.copyOfRange(var1, this.KyberIndCpaSecretKeyBytes, var1.length);
      System.arraycopy(this.indCpa.decrypt(var1, var2), 0, var3, 0, 32);
      System.arraycopy(var1, this.KyberSecretKeyBytes - 64, var3, 32, 32);
      this.symmetric.hash_g(var4, var3);
      byte[] var6 = new byte[32 + this.KyberCipherTextBytes];
      System.arraycopy(var1, this.KyberSecretKeyBytes - 32, var6, 0, 32);
      System.arraycopy(var2, 0, var6, 32, this.KyberCipherTextBytes);
      this.symmetric.kdf(var6, var6);
      byte[] var7 = this.indCpa.encrypt(var5, Arrays.copyOfRange((byte[])var3, 0, 32), Arrays.copyOfRange((byte[])var4, 32, var4.length));
      boolean var8 = !Arrays.constantTimeAreEqual(var2, var7);
      this.cmov(var4, var6, 32, var8);
      return Arrays.copyOfRange((byte[])var4, 0, this.sessionKeyLength);
   }

   public byte[][] kemEncrypt(byte[] var1, byte[] var2) {
      if (var1.length != this.KyberIndCpaPublicKeyBytes) {
         throw new IllegalArgumentException("Input validation Error: Type check failed for ml-kem encapsulation");
      } else {
         PolyVec var3 = new PolyVec(this);
         byte[] var4 = this.indCpa.unpackPublicKey(var3, var1);
         byte[] var5 = this.indCpa.packPublicKey(var3, var4);
         if (!Arrays.areEqual(var5, var1)) {
            throw new IllegalArgumentException("Input validation: Modulus check failed for ml-kem encapsulation");
         } else {
            return this.kemEncryptInternal(var1, var2);
         }
      }
   }

   public byte[] kemDecrypt(byte[] var1, byte[] var2) {
      return this.kemDecryptInternal(var1, var2);
   }

   private void cmov(byte[] var1, byte[] var2, int var3, boolean var4) {
      if (var4) {
         System.arraycopy(var2, 0, var1, 0, var3);
      } else {
         System.arraycopy(var1, 0, var1, 0, var3);
      }

   }

   public void getRandomBytes(byte[] var1) {
      this.random.nextBytes(var1);
   }
}
