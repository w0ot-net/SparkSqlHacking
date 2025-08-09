package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi;

public class Rainbow {
   private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.rainbow.";

   public static class Mappings extends AsymmetricAlgorithmProvider {
      public void configure(ConfigurableProvider var1) {
         var1.addAlgorithm("KeyFactory.RAINBOW", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi");
         var1.addAlgorithm("KeyPairGenerator.RAINBOW", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi");
         this.addKeyPairGeneratorAlgorithm(var1, "RAINBOW-III-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowIIIclassic", BCObjectIdentifiers.rainbow_III_classic);
         this.addKeyPairGeneratorAlgorithm(var1, "RAINBOW-III-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowIIIcircum", BCObjectIdentifiers.rainbow_III_circumzenithal);
         this.addKeyPairGeneratorAlgorithm(var1, "RAINBOW-III-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowIIIcomp", BCObjectIdentifiers.rainbow_III_compressed);
         this.addKeyPairGeneratorAlgorithm(var1, "RAINBOW-V-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowVclassic", BCObjectIdentifiers.rainbow_V_classic);
         this.addKeyPairGeneratorAlgorithm(var1, "RAINBOW-V-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowVcircum", BCObjectIdentifiers.rainbow_V_circumzenithal);
         this.addKeyPairGeneratorAlgorithm(var1, "RAINBOW-V-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowVcomp", BCObjectIdentifiers.rainbow_V_compressed);
         this.addSignatureAlgorithm(var1, "RAINBOW", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$Base", BCObjectIdentifiers.rainbow);
         this.addSignatureAlgorithm(var1, "RAINBOW-III-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowIIIclassic", BCObjectIdentifiers.rainbow_III_classic);
         this.addSignatureAlgorithm(var1, "RAINBOW-III-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowIIIcircum", BCObjectIdentifiers.rainbow_III_circumzenithal);
         this.addSignatureAlgorithm(var1, "RAINBOW-III-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowIIIcomp", BCObjectIdentifiers.rainbow_III_compressed);
         this.addSignatureAlgorithm(var1, "RAINBOW-V-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowVclassic", BCObjectIdentifiers.rainbow_V_classic);
         this.addSignatureAlgorithm(var1, "RAINBOW-V-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowVcircum", BCObjectIdentifiers.rainbow_V_circumzenithal);
         this.addSignatureAlgorithm(var1, "RAINBOW-v-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowVcomp", BCObjectIdentifiers.rainbow_V_compressed);
         RainbowKeyFactorySpi var2 = new RainbowKeyFactorySpi();
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.rainbow_III_classic, "RAINBOW", var2);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.rainbow_III_circumzenithal, "RAINBOW", var2);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.rainbow_III_compressed, "RAINBOW", var2);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.rainbow_V_classic, "RAINBOW", var2);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.rainbow_V_circumzenithal, "RAINBOW", var2);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.rainbow_V_compressed, "RAINBOW", var2);
      }
   }
}
