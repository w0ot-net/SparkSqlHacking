package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMPrivateKeyParameters;
import org.bouncycastle.util.Arrays;

public class XWingPrivateKeyParameters extends XWingKeyParameters {
   private final MLKEMPrivateKeyParameters kybPriv;
   private final X25519PrivateKeyParameters xdhPriv;

   XWingPrivateKeyParameters(AsymmetricKeyParameter var1, AsymmetricKeyParameter var2) {
      super(true);
      this.kybPriv = (MLKEMPrivateKeyParameters)var1;
      this.xdhPriv = (X25519PrivateKeyParameters)var2;
   }

   public XWingPrivateKeyParameters(byte[] var1) {
      super(false);
      this.kybPriv = new MLKEMPrivateKeyParameters(MLKEMParameters.ml_kem_768, Arrays.copyOfRange((byte[])var1, 0, var1.length - 32));
      this.xdhPriv = new X25519PrivateKeyParameters(var1, var1.length - 32);
   }

   MLKEMPrivateKeyParameters getKyberPrivateKey() {
      return this.kybPriv;
   }

   X25519PrivateKeyParameters getXDHPrivateKey() {
      return this.xdhPriv;
   }

   public byte[] getEncoded() {
      return Arrays.concatenate(this.kybPriv.getEncoded(), this.xdhPriv.getEncoded());
   }
}
