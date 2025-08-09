package org.bouncycastle.pqc.jcajce.provider.rainbow;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyPairGenerator;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.RainbowParameterSpec;
import org.bouncycastle.util.Strings;

public class RainbowKeyPairGeneratorSpi extends KeyPairGenerator {
   private static Map parameters = new HashMap();
   private final RainbowParameters rainbowParameters;
   RainbowKeyGenerationParameters param;
   RainbowKeyPairGenerator engine = new RainbowKeyPairGenerator();
   SecureRandom random = CryptoServicesRegistrar.getSecureRandom();
   boolean initialised = false;

   public RainbowKeyPairGeneratorSpi() {
      super("RAINBOW");
      this.rainbowParameters = null;
   }

   protected RainbowKeyPairGeneratorSpi(RainbowParameters var1) {
      super(var1.getName());
      this.rainbowParameters = var1;
   }

   public void initialize(int var1, SecureRandom var2) {
      throw new IllegalArgumentException("use AlgorithmParameterSpec");
   }

   public void initialize(AlgorithmParameterSpec var1, SecureRandom var2) throws InvalidAlgorithmParameterException {
      String var3 = getNameFromParams(var1);
      if (var3 != null && parameters.containsKey(var3)) {
         RainbowParameters var4 = (RainbowParameters)parameters.get(var3);
         this.param = new RainbowKeyGenerationParameters(var2, var4);
         if (this.rainbowParameters != null && !var4.getName().equals(this.rainbowParameters.getName())) {
            throw new InvalidAlgorithmParameterException("key pair generator locked to " + Strings.toUpperCase(this.rainbowParameters.getName()));
         } else {
            this.engine.init(this.param);
            this.initialised = true;
         }
      } else {
         throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + var1);
      }
   }

   private static String getNameFromParams(AlgorithmParameterSpec var0) {
      if (var0 instanceof RainbowParameterSpec) {
         RainbowParameterSpec var1 = (RainbowParameterSpec)var0;
         return var1.getName();
      } else {
         return Strings.toLowerCase(SpecUtil.getNameFrom(var0));
      }
   }

   public KeyPair generateKeyPair() {
      if (!this.initialised) {
         if (this.rainbowParameters != null) {
            this.param = new RainbowKeyGenerationParameters(this.random, this.rainbowParameters);
         } else {
            this.param = new RainbowKeyGenerationParameters(this.random, RainbowParameters.rainbowIIIclassic);
         }

         this.engine.init(this.param);
         this.initialised = true;
      }

      AsymmetricCipherKeyPair var1 = this.engine.generateKeyPair();
      RainbowPublicKeyParameters var2 = (RainbowPublicKeyParameters)var1.getPublic();
      RainbowPrivateKeyParameters var3 = (RainbowPrivateKeyParameters)var1.getPrivate();
      return new KeyPair(new BCRainbowPublicKey(var2), new BCRainbowPrivateKey(var3));
   }

   static {
      parameters.put(RainbowParameterSpec.rainbowIIIclassic.getName(), RainbowParameters.rainbowIIIclassic);
      parameters.put(RainbowParameterSpec.rainbowIIIcircumzenithal.getName(), RainbowParameters.rainbowIIIcircumzenithal);
      parameters.put(RainbowParameterSpec.rainbowIIIcompressed.getName(), RainbowParameters.rainbowIIIcompressed);
      parameters.put(RainbowParameterSpec.rainbowVclassic.getName(), RainbowParameters.rainbowVclassic);
      parameters.put(RainbowParameterSpec.rainbowVcircumzenithal.getName(), RainbowParameters.rainbowVcircumzenithal);
      parameters.put(RainbowParameterSpec.rainbowVcompressed.getName(), RainbowParameters.rainbowVcompressed);
   }

   public static class RainbowIIIcircum extends RainbowKeyPairGeneratorSpi {
      public RainbowIIIcircum() {
         super(RainbowParameters.rainbowIIIcircumzenithal);
      }
   }

   public static class RainbowIIIclassic extends RainbowKeyPairGeneratorSpi {
      public RainbowIIIclassic() {
         super(RainbowParameters.rainbowIIIclassic);
      }
   }

   public static class RainbowIIIcomp extends RainbowKeyPairGeneratorSpi {
      public RainbowIIIcomp() {
         super(RainbowParameters.rainbowIIIcompressed);
      }
   }

   public static class RainbowVcircum extends RainbowKeyPairGeneratorSpi {
      public RainbowVcircum() {
         super(RainbowParameters.rainbowVcircumzenithal);
      }
   }

   public static class RainbowVclassic extends RainbowKeyPairGeneratorSpi {
      public RainbowVclassic() {
         super(RainbowParameters.rainbowVclassic);
      }
   }

   public static class RainbowVcomp extends RainbowKeyPairGeneratorSpi {
      public RainbowVcomp() {
         super(RainbowParameters.rainbowVcompressed);
      }
   }
}
