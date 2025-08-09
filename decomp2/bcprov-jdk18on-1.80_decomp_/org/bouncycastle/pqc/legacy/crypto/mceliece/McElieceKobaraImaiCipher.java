package org.bouncycastle.pqc.legacy.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.pqc.crypto.MessageEncryptor;
import org.bouncycastle.pqc.legacy.math.linearalgebra.ByteUtils;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.legacy.math.linearalgebra.IntegerFunctions;

public class McElieceKobaraImaiCipher implements MessageEncryptor {
   public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.3";
   private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
   public static final byte[] PUBLIC_CONSTANT = "a predetermined public constant".getBytes();
   private Digest messDigest;
   private SecureRandom sr;
   McElieceCCA2KeyParameters key;
   private int n;
   private int k;
   private int t;
   private boolean forEncryption;

   public void init(boolean var1, CipherParameters var2) {
      this.forEncryption = var1;
      if (var1) {
         if (var2 instanceof ParametersWithRandom) {
            ParametersWithRandom var3 = (ParametersWithRandom)var2;
            this.sr = var3.getRandom();
            this.key = (McElieceCCA2PublicKeyParameters)var3.getParameters();
            this.initCipherEncrypt((McElieceCCA2PublicKeyParameters)this.key);
         } else {
            this.sr = CryptoServicesRegistrar.getSecureRandom();
            this.key = (McElieceCCA2PublicKeyParameters)var2;
            this.initCipherEncrypt((McElieceCCA2PublicKeyParameters)this.key);
         }
      } else {
         this.key = (McElieceCCA2PrivateKeyParameters)var2;
         this.initCipherDecrypt((McElieceCCA2PrivateKeyParameters)this.key);
      }

   }

   public int getKeySize(McElieceCCA2KeyParameters var1) {
      if (var1 instanceof McElieceCCA2PublicKeyParameters) {
         return ((McElieceCCA2PublicKeyParameters)var1).getN();
      } else if (var1 instanceof McElieceCCA2PrivateKeyParameters) {
         return ((McElieceCCA2PrivateKeyParameters)var1).getN();
      } else {
         throw new IllegalArgumentException("unsupported type");
      }
   }

   private void initCipherEncrypt(McElieceCCA2PublicKeyParameters var1) {
      this.messDigest = Utils.getDigest(var1.getDigest());
      this.n = var1.getN();
      this.k = var1.getK();
      this.t = var1.getT();
   }

   private void initCipherDecrypt(McElieceCCA2PrivateKeyParameters var1) {
      this.messDigest = Utils.getDigest(var1.getDigest());
      this.n = var1.getN();
      this.k = var1.getK();
      this.t = var1.getT();
   }

   public byte[] messageEncrypt(byte[] var1) {
      if (!this.forEncryption) {
         throw new IllegalStateException("cipher initialised for decryption");
      } else {
         int var2 = this.messDigest.getDigestSize();
         int var3 = this.k >> 3;
         int var4 = IntegerFunctions.binomial(this.n, this.t).bitLength() - 1 >> 3;
         int var5 = var3 + var4 - var2 - PUBLIC_CONSTANT.length;
         if (var1.length > var5) {
            var5 = var1.length;
         }

         int var6 = var5 + PUBLIC_CONSTANT.length;
         int var7 = var6 + var2 - var3 - var4;
         byte[] var8 = new byte[var6];
         System.arraycopy(var1, 0, var8, 0, var1.length);
         System.arraycopy(PUBLIC_CONSTANT, 0, var8, var5, PUBLIC_CONSTANT.length);
         byte[] var9 = new byte[var2];
         this.sr.nextBytes(var9);
         DigestRandomGenerator var10 = new DigestRandomGenerator(new SHA1Digest());
         var10.addSeedMaterial(var9);
         byte[] var11 = new byte[var6];
         var10.nextBytes(var11);

         for(int var12 = var6 - 1; var12 >= 0; --var12) {
            var11[var12] ^= var8[var12];
         }

         byte[] var20 = new byte[this.messDigest.getDigestSize()];
         this.messDigest.update(var11, 0, var11.length);
         this.messDigest.doFinal(var20, 0);

         for(int var13 = var2 - 1; var13 >= 0; --var13) {
            var20[var13] ^= var9[var13];
         }

         byte[] var21 = ByteUtils.concatenate(var20, var11);
         byte[] var14 = new byte[0];
         if (var7 > 0) {
            var14 = new byte[var7];
            System.arraycopy(var21, 0, var14, 0, var7);
         }

         byte[] var15 = new byte[var4];
         System.arraycopy(var21, var7, var15, 0, var4);
         byte[] var16 = new byte[var3];
         System.arraycopy(var21, var7 + var4, var16, 0, var3);
         GF2Vector var17 = GF2Vector.OS2VP(this.k, var16);
         GF2Vector var18 = Conversions.encode(this.n, this.t, var15);
         byte[] var19 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters)this.key, var17, var18).getEncoded();
         return var7 > 0 ? ByteUtils.concatenate(var14, var19) : var19;
      }
   }

   public byte[] messageDecrypt(byte[] var1) throws InvalidCipherTextException {
      if (this.forEncryption) {
         throw new IllegalStateException("cipher initialised for decryption");
      } else {
         int var2 = this.n >> 3;
         if (var1.length < var2) {
            throw new InvalidCipherTextException("Bad Padding: Ciphertext too short.");
         } else {
            int var3 = this.messDigest.getDigestSize();
            int var4 = this.k >> 3;
            int var5 = IntegerFunctions.binomial(this.n, this.t).bitLength() - 1 >> 3;
            int var6 = var1.length - var2;
            byte[] var7;
            byte[] var8;
            if (var6 > 0) {
               byte[][] var9 = ByteUtils.split(var1, var6);
               var7 = var9[0];
               var8 = var9[1];
            } else {
               var7 = new byte[0];
               var8 = var1;
            }

            GF2Vector var25 = GF2Vector.OS2VP(this.n, var8);
            GF2Vector[] var10 = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters)this.key, var25);
            byte[] var11 = var10[0].getEncoded();
            GF2Vector var12 = var10[1];
            if (var11.length > var4) {
               var11 = ByteUtils.subArray(var11, 0, var4);
            }

            byte[] var13 = Conversions.decode(this.n, this.t, var12);
            if (var13.length < var5) {
               byte[] var14 = new byte[var5];
               System.arraycopy(var13, 0, var14, var5 - var13.length, var13.length);
               var13 = var14;
            }

            byte[] var26 = ByteUtils.concatenate(var7, var13);
            var26 = ByteUtils.concatenate(var26, var11);
            int var15 = var26.length - var3;
            byte[][] var16 = ByteUtils.split(var26, var3);
            byte[] var17 = var16[0];
            byte[] var18 = var16[1];
            byte[] var19 = new byte[this.messDigest.getDigestSize()];
            this.messDigest.update(var18, 0, var18.length);
            this.messDigest.doFinal(var19, 0);

            for(int var20 = var3 - 1; var20 >= 0; --var20) {
               var19[var20] ^= var17[var20];
            }

            DigestRandomGenerator var28 = new DigestRandomGenerator(new SHA1Digest());
            var28.addSeedMaterial(var19);
            byte[] var21 = new byte[var15];
            var28.nextBytes(var21);

            for(int var22 = var15 - 1; var22 >= 0; --var22) {
               var21[var22] ^= var18[var22];
            }

            if (var21.length < var15) {
               throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
            } else {
               byte[][] var29 = ByteUtils.split(var21, var15 - PUBLIC_CONSTANT.length);
               byte[] var23 = var29[0];
               byte[] var24 = var29[1];
               if (!ByteUtils.equals(var24, PUBLIC_CONSTANT)) {
                  throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
               } else {
                  return var23;
               }
            }
         }
      }
   }
}
