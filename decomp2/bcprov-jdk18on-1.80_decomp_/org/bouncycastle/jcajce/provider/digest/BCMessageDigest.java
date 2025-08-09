package org.bouncycastle.jcajce.provider.digest;

import java.security.DigestException;
import java.security.MessageDigest;
import org.bouncycastle.crypto.Digest;

public class BCMessageDigest extends MessageDigest {
   protected Digest digest;
   protected int digestSize;

   protected BCMessageDigest(Digest var1) {
      super(var1.getAlgorithmName());
      this.digest = var1;
      this.digestSize = var1.getDigestSize();
   }

   public void engineReset() {
      this.digest.reset();
   }

   public void engineUpdate(byte var1) {
      this.digest.update(var1);
   }

   public void engineUpdate(byte[] var1, int var2, int var3) {
      this.digest.update(var1, var2, var3);
   }

   public int engineGetDigestLength() {
      return this.digestSize;
   }

   public byte[] engineDigest() {
      byte[] var1 = new byte[this.digestSize];
      this.digest.doFinal(var1, 0);
      return var1;
   }

   public int engineDigest(byte[] var1, int var2, int var3) throws DigestException {
      if (var3 < this.digestSize) {
         throw new DigestException("partial digests not returned");
      } else if (var1.length - var2 < this.digestSize) {
         throw new DigestException("insufficient space in the output buffer to store the digest");
      } else {
         this.digest.doFinal(var1, var2);
         return this.digestSize;
      }
   }
}
