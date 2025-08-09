package org.bouncycastle.pqc.crypto.xwing;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMGenerator;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class XWingKEMGenerator implements EncapsulatedSecretGenerator {
   private final SecureRandom sr;

   public XWingKEMGenerator(SecureRandom var1) {
      this.sr = var1;
   }

   public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter var1) {
      XWingPublicKeyParameters var2 = (XWingPublicKeyParameters)var1;
      MLKEMGenerator var3 = new MLKEMGenerator(this.sr);
      SecretWithEncapsulation var4 = var3.generateEncapsulated(var2.getKyberPublicKey());
      X25519Agreement var5 = new X25519Agreement();
      byte[] var6 = var4.getSecret();
      byte[] var7 = new byte[var6.length + var5.getAgreementSize()];
      System.arraycopy(var6, 0, var7, 0, var6.length);
      Arrays.clear(var6);
      X25519KeyPairGenerator var8 = new X25519KeyPairGenerator();
      var8.init(new X25519KeyGenerationParameters(this.sr));
      AsymmetricCipherKeyPair var9 = var8.generateKeyPair();
      var5.init(var9.getPrivate());
      var5.calculateAgreement(var2.getXDHPublicKey(), var7, var6.length);
      X25519PublicKeyParameters var10 = (X25519PublicKeyParameters)var9.getPublic();
      SHA3Digest var11 = new SHA3Digest(256);
      var11.update(Strings.toByteArray("\\.//^\\"), 0, 6);
      var11.update(var7, 0, var7.length);
      var11.update(var10.getEncoded(), 0, 32);
      var11.update(var2.getXDHPublicKey().getEncoded(), 0, 32);
      byte[] var12 = new byte[32];
      var11.doFinal(var12, 0);
      return new SecretWithEncapsulationImpl(var12, Arrays.concatenate(var4.getEncapsulation(), var10.getEncoded()));
   }
}
