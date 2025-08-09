package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

public class PBEPBKDF1 {
   private PBEPBKDF1() {
   }

   public static class AlgParams extends BaseAlgorithmParameters {
      PBEParameter params;

      protected byte[] engineGetEncoded() {
         try {
            return this.params.getEncoded("DER");
         } catch (IOException var2) {
            throw new RuntimeException("Oooops! " + var2.toString());
         }
      }

      protected byte[] engineGetEncoded(String var1) {
         return this.isASN1FormatString(var1) ? this.engineGetEncoded() : null;
      }

      protected AlgorithmParameterSpec localEngineGetParameterSpec(Class var1) throws InvalidParameterSpecException {
         if (var1 != PBEParameterSpec.class && var1 != AlgorithmParameterSpec.class) {
            throw new InvalidParameterSpecException("unknown parameter spec passed to PBKDF1 PBE parameters object.");
         } else {
            return new PBEParameterSpec(this.params.getSalt(), this.params.getIterationCount().intValue());
         }
      }

      protected void engineInit(AlgorithmParameterSpec var1) throws InvalidParameterSpecException {
         if (!(var1 instanceof PBEParameterSpec)) {
            throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PBKDF1 PBE parameters algorithm parameters object");
         } else {
            PBEParameterSpec var2 = (PBEParameterSpec)var1;
            this.params = new PBEParameter(var2.getSalt(), var2.getIterationCount());
         }
      }

      protected void engineInit(byte[] var1) throws IOException {
         this.params = PBEParameter.getInstance(var1);
      }

      protected void engineInit(byte[] var1, String var2) throws IOException {
         if (this.isASN1FormatString(var2)) {
            this.engineInit(var1);
         } else {
            throw new IOException("Unknown parameters format in PBKDF2 parameters object");
         }
      }

      protected String engineToString() {
         return "PBKDF1 Parameters";
      }
   }

   public static class Mappings extends AlgorithmProvider {
      private static final String PREFIX = PBEPBKDF1.class.getName();

      public void configure(ConfigurableProvider var1) {
         var1.addAlgorithm("AlgorithmParameters.PBKDF1", PREFIX + "$AlgParams");
         var1.addAlgorithm("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, "PBKDF1");
         var1.addAlgorithm("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, "PBKDF1");
         var1.addAlgorithm("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC, "PBKDF1");
         var1.addAlgorithm("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, "PBKDF1");
         var1.addAlgorithm("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC, "PBKDF1");
      }
   }
}
