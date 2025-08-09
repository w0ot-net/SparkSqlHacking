package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMExtractor;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class XWingKEMExtractor implements EncapsulatedSecretExtractor {
   private final XWingPrivateKeyParameters key;
   private final MLKEMExtractor kemExtractor;

   public XWingKEMExtractor(XWingPrivateKeyParameters var1) {
      this.key = var1;
      this.kemExtractor = new MLKEMExtractor(this.key.getKyberPrivateKey());
   }

   public byte[] extractSecret(byte[] var1) {
      byte[] var2 = this.kemExtractor.extractSecret(Arrays.copyOfRange((byte[])var1, 0, var1.length - 32));
      X25519Agreement var3 = new X25519Agreement();
      byte[] var4 = new byte[var2.length + var3.getAgreementSize()];
      System.arraycopy(var2, 0, var4, 0, var2.length);
      Arrays.clear(var2);
      var3.init(this.key.getXDHPrivateKey());
      X25519PublicKeyParameters var5 = new X25519PublicKeyParameters(Arrays.copyOfRange(var1, var1.length - 32, var1.length));
      var3.calculateAgreement(var5, var4, var2.length);
      SHA3Digest var6 = new SHA3Digest(256);
      var6.update(Strings.toByteArray("\\.//^\\"), 0, 6);
      var6.update(var4, 0, var4.length);
      var6.update(var5.getEncoded(), 0, 32);
      var6.update(this.key.getXDHPrivateKey().generatePublicKey().getEncoded(), 0, 32);
      byte[] var7 = new byte[32];
      var6.doFinal(var7, 0);
      return var7;
   }

   public int getEncapsulationLength() {
      return this.kemExtractor.getEncapsulationLength() + 32;
   }
}
