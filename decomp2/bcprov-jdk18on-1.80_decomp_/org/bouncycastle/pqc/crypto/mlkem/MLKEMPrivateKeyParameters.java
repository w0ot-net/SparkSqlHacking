package org.bouncycastle.pqc.crypto.mlkem;

import org.bouncycastle.util.Arrays;

public class MLKEMPrivateKeyParameters extends MLKEMKeyParameters {
   final byte[] s;
   final byte[] hpk;
   final byte[] nonce;
   final byte[] t;
   final byte[] rho;
   final byte[] seed;

   public MLKEMPrivateKeyParameters(MLKEMParameters var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6) {
      this(var1, var2, var3, var4, var5, var6, (byte[])null);
   }

   public MLKEMPrivateKeyParameters(MLKEMParameters var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6, byte[] var7) {
      super(true, var1);
      this.s = Arrays.clone(var2);
      this.hpk = Arrays.clone(var3);
      this.nonce = Arrays.clone(var4);
      this.t = Arrays.clone(var5);
      this.rho = Arrays.clone(var6);
      this.seed = Arrays.clone(var7);
   }

   public MLKEMPrivateKeyParameters(MLKEMParameters var1, byte[] var2) {
      super(true, var1);
      MLKEMEngine var3 = var1.getEngine();
      if (var2.length == 64) {
         byte[][] var4 = var3.generateKemKeyPairInternal(Arrays.copyOfRange((byte[])var2, 0, 32), Arrays.copyOfRange((byte[])var2, 32, var2.length));
         this.s = var4[2];
         this.hpk = var4[3];
         this.nonce = var4[4];
         this.t = var4[0];
         this.rho = var4[1];
         this.seed = var4[5];
      } else {
         int var5 = 0;
         this.s = Arrays.copyOfRange((byte[])var2, 0, var3.getKyberIndCpaSecretKeyBytes());
         var5 += var3.getKyberIndCpaSecretKeyBytes();
         this.t = Arrays.copyOfRange(var2, var5, var5 + var3.getKyberIndCpaPublicKeyBytes() - 32);
         var5 += var3.getKyberIndCpaPublicKeyBytes() - 32;
         this.rho = Arrays.copyOfRange(var2, var5, var5 + 32);
         var5 += 32;
         this.hpk = Arrays.copyOfRange(var2, var5, var5 + 32);
         var5 += 32;
         this.nonce = Arrays.copyOfRange(var2, var5, var5 + 32);
         this.seed = null;
      }

   }

   public byte[] getEncoded() {
      return Arrays.concatenate(new byte[][]{this.s, this.t, this.rho, this.hpk, this.nonce});
   }

   public byte[] getHPK() {
      return Arrays.clone(this.hpk);
   }

   public byte[] getNonce() {
      return Arrays.clone(this.nonce);
   }

   public byte[] getPublicKey() {
      return MLKEMPublicKeyParameters.getEncoded(this.t, this.rho);
   }

   public MLKEMPublicKeyParameters getPublicKeyParameters() {
      return new MLKEMPublicKeyParameters(this.getParameters(), this.t, this.rho);
   }

   public byte[] getRho() {
      return Arrays.clone(this.rho);
   }

   public byte[] getS() {
      return Arrays.clone(this.s);
   }

   public byte[] getT() {
      return Arrays.clone(this.t);
   }

   public byte[] getSeed() {
      return Arrays.clone(this.seed);
   }
}
