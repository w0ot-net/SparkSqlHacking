package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/** @deprecated */
public class KEMParameterSpec extends KTSParameterSpec {
   public KEMParameterSpec(String var1) {
      this(var1, 256);
   }

   public KEMParameterSpec(String var1, int var2) {
      super(var1, var2, (AlgorithmParameterSpec)null, (AlgorithmIdentifier)null, (byte[])null);
   }

   public int getKeySizeInBits() {
      return this.getKeySize();
   }
}
