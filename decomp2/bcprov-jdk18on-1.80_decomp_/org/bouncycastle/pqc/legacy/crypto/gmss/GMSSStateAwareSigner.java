package org.bouncycastle.pqc.legacy.crypto.gmss;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.util.Memoable;

public class GMSSStateAwareSigner implements StateAwareMessageSigner {
   private final GMSSSigner gmssSigner;
   private GMSSPrivateKeyParameters key;

   public GMSSStateAwareSigner(Digest var1) {
      if (!(var1 instanceof Memoable)) {
         throw new IllegalArgumentException("digest must implement Memoable");
      } else {
         final Memoable var2 = ((Memoable)var1).copy();
         this.gmssSigner = new GMSSSigner(new GMSSDigestProvider() {
            public Digest get() {
               return (Digest)var2.copy();
            }
         });
      }
   }

   public void init(boolean var1, CipherParameters var2) {
      if (var1) {
         if (var2 instanceof ParametersWithRandom) {
            ParametersWithRandom var3 = (ParametersWithRandom)var2;
            this.key = (GMSSPrivateKeyParameters)var3.getParameters();
         } else {
            this.key = (GMSSPrivateKeyParameters)var2;
         }
      }

      this.gmssSigner.init(var1, var2);
   }

   public byte[] generateSignature(byte[] var1) {
      if (this.key == null) {
         throw new IllegalStateException("signing key no longer usable");
      } else {
         byte[] var2 = this.gmssSigner.generateSignature(var1);
         this.key = this.key.nextKey();
         return var2;
      }
   }

   public boolean verifySignature(byte[] var1, byte[] var2) {
      return this.gmssSigner.verifySignature(var1, var2);
   }

   public AsymmetricKeyParameter getUpdatedPrivateKey() {
      GMSSPrivateKeyParameters var1 = this.key;
      this.key = null;
      return var1;
   }
}
