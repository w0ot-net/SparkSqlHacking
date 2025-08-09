package org.bouncycastle.jcajce.provider.asymmetric;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyFactorySpi;

public class SPHINCSPlus {
   private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.sphincsplus.";

   public static class Mappings extends AsymmetricAlgorithmProvider {
      public void configure(ConfigurableProvider var1) {
         var1.addAlgorithm("KeyFactory.SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyFactorySpi");
         var1.addAlgorithm("KeyPairGenerator.SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi");
         var1.addAlgorithm("Alg.Alias.KeyFactory.SPHINCS+", "SPHINCSPLUS");
         var1.addAlgorithm("Alg.Alias.KeyPairGenerator.SPHINCS+", "SPHINCSPLUS");
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHA2-128S", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Sha2_128s", BCObjectIdentifiers.sphincsPlus_sha2_128s);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHA2-128F", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Sha2_128f", BCObjectIdentifiers.sphincsPlus_sha2_128f);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHA2-192S", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Sha2_192s", BCObjectIdentifiers.sphincsPlus_sha2_192s);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHA2-192F", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Sha2_192f", BCObjectIdentifiers.sphincsPlus_sha2_192f);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHA2-256S", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Sha2_256s", BCObjectIdentifiers.sphincsPlus_sha2_256s);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHA2-256F", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Sha2_256f", BCObjectIdentifiers.sphincsPlus_sha2_256f);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHAKE-128S", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Shake_128s", BCObjectIdentifiers.sphincsPlus_shake_128s);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHAKE-128F", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Shake_128f", BCObjectIdentifiers.sphincsPlus_shake_128f);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHAKE-192S", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Shake_192s", BCObjectIdentifiers.sphincsPlus_shake_192s);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHAKE-192F", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Shake_192f", BCObjectIdentifiers.sphincsPlus_shake_192f);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHAKE-256S", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Shake_256s", BCObjectIdentifiers.sphincsPlus_shake_256s);
         this.addKeyPairGeneratorAlgorithm(var1, "SPHINCS+-SHAKE-256F", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi$Shake_256f", BCObjectIdentifiers.sphincsPlus_shake_256f);
         this.addSignatureAlgorithm(var1, "SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SignatureSpi$Direct", BCObjectIdentifiers.sphincsPlus);

         for(int var2 = 1; var2 <= 36; ++var2) {
            var1.addAlgorithm("Alg.Alias.Signature." + BCObjectIdentifiers.sphincsPlus + "." + var2, "SPHINCSPLUS");
            var1.addAlgorithm("Alg.Alias.Signature.OID." + BCObjectIdentifiers.sphincsPlus + "." + var2, "SPHINCSPLUS");
         }

         ASN1ObjectIdentifier[] var4 = new ASN1ObjectIdentifier[]{BCObjectIdentifiers.sphincsPlus_sha2_128s, BCObjectIdentifiers.sphincsPlus_sha2_128f, BCObjectIdentifiers.sphincsPlus_shake_128s, BCObjectIdentifiers.sphincsPlus_shake_128f, BCObjectIdentifiers.sphincsPlus_sha2_192s, BCObjectIdentifiers.sphincsPlus_sha2_192f, BCObjectIdentifiers.sphincsPlus_shake_192s, BCObjectIdentifiers.sphincsPlus_shake_192f, BCObjectIdentifiers.sphincsPlus_sha2_256s, BCObjectIdentifiers.sphincsPlus_sha2_256f, BCObjectIdentifiers.sphincsPlus_shake_256s, BCObjectIdentifiers.sphincsPlus_shake_256f};

         for(int var3 = 0; var3 != var4.length; ++var3) {
            var1.addAlgorithm("Alg.Alias.Signature." + var4[var3], "SPHINCSPLUS");
            var1.addAlgorithm("Alg.Alias.Signature.OID." + var4[var3], "SPHINCSPLUS");
         }

         var1.addAlgorithm("Alg.Alias.Signature.SPHINCS+", "SPHINCSPLUS");
         SPHINCSPlusKeyFactorySpi var5 = new SPHINCSPlusKeyFactorySpi();
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_128s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_128f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_128s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_128f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_192s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_192f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_192s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_192f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_256s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_256f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_256s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_256f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_128s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_128f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_128s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_128f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_192s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_192f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_192s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_192f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_256s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_256f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_256s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_256f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_128s, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_128f, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_192s, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_192f, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_256s, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_sha2_256f, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_128s, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_128f, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_192s, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_192f, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_256s, "SPHINCSPLUS", var5);
         this.registerKeyFactoryOid(var1, BCObjectIdentifiers.sphincsPlus_shake_256f, "SPHINCSPLUS", var5);
         this.registerOidAlgorithmParameters(var1, BCObjectIdentifiers.sphincsPlus, "SPHINCSPLUS");
      }
   }
}
