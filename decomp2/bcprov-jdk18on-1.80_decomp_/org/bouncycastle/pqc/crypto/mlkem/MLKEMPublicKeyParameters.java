package org.bouncycastle.pqc.crypto.mlkem;

import org.bouncycastle.util.Arrays;

public class MLKEMPublicKeyParameters extends MLKEMKeyParameters {
   final byte[] t;
   final byte[] rho;

   static byte[] getEncoded(byte[] var0, byte[] var1) {
      return Arrays.concatenate(var0, var1);
   }

   public MLKEMPublicKeyParameters(MLKEMParameters var1, byte[] var2, byte[] var3) {
      super(false, var1);
      this.t = Arrays.clone(var2);
      this.rho = Arrays.clone(var3);
   }

   public MLKEMPublicKeyParameters(MLKEMParameters var1, byte[] var2) {
      super(false, var1);
      this.t = Arrays.copyOfRange((byte[])var2, 0, var2.length - 32);
      this.rho = Arrays.copyOfRange(var2, var2.length - 32, var2.length);
   }

   public byte[] getEncoded() {
      return getEncoded(this.t, this.rho);
   }

   public byte[] getRho() {
      return Arrays.clone(this.rho);
   }

   public byte[] getT() {
      return Arrays.clone(this.t);
   }
}
