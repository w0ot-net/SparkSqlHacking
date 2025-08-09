package org.bouncycastle.pqc.crypto.mlkem;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;

public class MLKEMGenerator implements EncapsulatedSecretGenerator {
   private final SecureRandom sr;

   public MLKEMGenerator(SecureRandom var1) {
      this.sr = var1;
   }

   public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter var1) {
      MLKEMPublicKeyParameters var2 = (MLKEMPublicKeyParameters)var1;
      MLKEMEngine var3 = var2.getParameters().getEngine();
      var3.init(this.sr);
      byte[] var4 = new byte[32];
      var3.getRandomBytes(var4);
      byte[][] var5 = var3.kemEncrypt(var2.getEncoded(), var4);
      return new SecretWithEncapsulationImpl(var5[0], var5[1]);
   }

   public SecretWithEncapsulation internalGenerateEncapsulated(AsymmetricKeyParameter var1, byte[] var2) {
      MLKEMPublicKeyParameters var3 = (MLKEMPublicKeyParameters)var1;
      MLKEMEngine var4 = var3.getParameters().getEngine();
      var4.init(this.sr);
      byte[][] var5 = var4.kemEncryptInternal(var3.getEncoded(), var2);
      return new SecretWithEncapsulationImpl(var5[0], var5[1]);
   }
}
