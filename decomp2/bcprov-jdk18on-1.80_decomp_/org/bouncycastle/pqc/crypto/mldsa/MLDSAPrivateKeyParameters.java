package org.bouncycastle.pqc.crypto.mldsa;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

public class MLDSAPrivateKeyParameters extends MLDSAKeyParameters {
   final byte[] rho;
   final byte[] k;
   final byte[] tr;
   final byte[] s1;
   final byte[] s2;
   final byte[] t0;
   private final byte[] t1;
   private final byte[] seed;

   public MLDSAPrivateKeyParameters(MLDSAParameters var1, byte[] var2) {
      this(var1, var2, (MLDSAPublicKeyParameters)null);
   }

   public MLDSAPrivateKeyParameters(MLDSAParameters var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6, byte[] var7, byte[] var8) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, (byte[])null);
   }

   public MLDSAPrivateKeyParameters(MLDSAParameters var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6, byte[] var7, byte[] var8, byte[] var9) {
      super(true, var1);
      this.rho = Arrays.clone(var2);
      this.k = Arrays.clone(var3);
      this.tr = Arrays.clone(var4);
      this.s1 = Arrays.clone(var5);
      this.s2 = Arrays.clone(var6);
      this.t0 = Arrays.clone(var7);
      this.t1 = Arrays.clone(var8);
      this.seed = Arrays.clone(var9);
   }

   public MLDSAPrivateKeyParameters(MLDSAParameters var1, byte[] var2, MLDSAPublicKeyParameters var3) {
      super(true, var1);
      MLDSAEngine var4 = var1.getEngine((SecureRandom)null);
      if (var2.length == 32) {
         byte[][] var5 = var4.generateKeyPairInternal(var2);
         this.rho = var5[0];
         this.k = var5[1];
         this.tr = var5[2];
         this.s1 = var5[3];
         this.s2 = var5[4];
         this.t0 = var5[5];
         this.t1 = var5[6];
         this.seed = var5[7];
      } else {
         int var7 = 0;
         this.rho = Arrays.copyOfRange((byte[])var2, 0, 32);
         var7 += 32;
         this.k = Arrays.copyOfRange(var2, var7, var7 + 32);
         var7 += 32;
         this.tr = Arrays.copyOfRange(var2, var7, var7 + 64);
         var7 += 64;
         int var6 = var4.getDilithiumL() * var4.getDilithiumPolyEtaPackedBytes();
         this.s1 = Arrays.copyOfRange(var2, var7, var7 + var6);
         var7 += var6;
         var6 = var4.getDilithiumK() * var4.getDilithiumPolyEtaPackedBytes();
         this.s2 = Arrays.copyOfRange(var2, var7, var7 + var6);
         var7 += var6;
         var6 = var4.getDilithiumK() * 416;
         this.t0 = Arrays.copyOfRange(var2, var7, var7 + var6);
         int var10000 = var7 + var6;
         this.t1 = var4.deriveT1(this.rho, this.k, this.tr, this.s1, this.s2, this.t0);
         if (var3 != null && !Arrays.constantTimeAreEqual(this.t1, var3.getT1())) {
            throw new IllegalArgumentException("passed in public key does not match private values");
         }

         this.seed = null;
      }

   }

   public byte[] getEncoded() {
      return Arrays.concatenate(new byte[][]{this.rho, this.k, this.tr, this.s1, this.s2, this.t0});
   }

   public byte[] getK() {
      return Arrays.clone(this.k);
   }

   /** @deprecated */
   public byte[] getPrivateKey() {
      return this.getEncoded();
   }

   public byte[] getPublicKey() {
      return MLDSAPublicKeyParameters.getEncoded(this.rho, this.t1);
   }

   public byte[] getSeed() {
      return Arrays.clone(this.seed);
   }

   public MLDSAPublicKeyParameters getPublicKeyParameters() {
      return this.t1 == null ? null : new MLDSAPublicKeyParameters(this.getParameters(), this.rho, this.t1);
   }

   public byte[] getRho() {
      return Arrays.clone(this.rho);
   }

   public byte[] getS1() {
      return Arrays.clone(this.s1);
   }

   public byte[] getS2() {
      return Arrays.clone(this.s2);
   }

   public byte[] getT0() {
      return Arrays.clone(this.t0);
   }

   public byte[] getT1() {
      return Arrays.clone(this.t1);
   }

   public byte[] getTr() {
      return Arrays.clone(this.tr);
   }
}
