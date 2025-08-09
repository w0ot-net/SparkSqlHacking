package org.bouncycastle.jcajce;

import javax.crypto.SecretKey;
import org.bouncycastle.util.Arrays;

public final class SecretKeyWithEncapsulation implements SecretKey {
   private final SecretKey secretKey;
   private final byte[] encapsulation;

   public SecretKeyWithEncapsulation(SecretKey var1, byte[] var2) {
      this.secretKey = var1;
      this.encapsulation = Arrays.clone(var2);
   }

   public String getAlgorithm() {
      return this.secretKey.getAlgorithm();
   }

   public String getFormat() {
      return this.secretKey.getFormat();
   }

   public byte[] getEncoded() {
      return this.secretKey.getEncoded();
   }

   public byte[] getEncapsulation() {
      return Arrays.clone(this.encapsulation);
   }

   public boolean equals(Object var1) {
      return this.secretKey.equals(var1);
   }

   public int hashCode() {
      return this.secretKey.hashCode();
   }
}
