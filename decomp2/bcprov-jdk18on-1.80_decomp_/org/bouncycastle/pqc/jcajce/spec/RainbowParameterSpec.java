package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.util.Strings;

public class RainbowParameterSpec implements AlgorithmParameterSpec {
   public static final RainbowParameterSpec rainbowIIIclassic;
   public static final RainbowParameterSpec rainbowIIIcircumzenithal;
   public static final RainbowParameterSpec rainbowIIIcompressed;
   public static final RainbowParameterSpec rainbowVclassic;
   public static final RainbowParameterSpec rainbowVcircumzenithal;
   public static final RainbowParameterSpec rainbowVcompressed;
   private static Map parameters;
   private final String name;

   private RainbowParameterSpec(RainbowParameters var1) {
      this.name = Strings.toUpperCase(var1.getName());
   }

   public String getName() {
      return this.name;
   }

   public static RainbowParameterSpec fromName(String var0) {
      return (RainbowParameterSpec)parameters.get(Strings.toLowerCase(var0));
   }

   static {
      rainbowIIIclassic = new RainbowParameterSpec(RainbowParameters.rainbowIIIclassic);
      rainbowIIIcircumzenithal = new RainbowParameterSpec(RainbowParameters.rainbowIIIcircumzenithal);
      rainbowIIIcompressed = new RainbowParameterSpec(RainbowParameters.rainbowIIIcompressed);
      rainbowVclassic = new RainbowParameterSpec(RainbowParameters.rainbowVclassic);
      rainbowVcircumzenithal = new RainbowParameterSpec(RainbowParameters.rainbowVcircumzenithal);
      rainbowVcompressed = new RainbowParameterSpec(RainbowParameters.rainbowVcompressed);
      parameters = new HashMap();
      parameters.put("rainbow-iii-classic", rainbowIIIclassic);
      parameters.put("rainbow-iii-circumzenithal", rainbowIIIcircumzenithal);
      parameters.put("rainbow-iii-compressed", rainbowIIIcompressed);
      parameters.put("rainbow-v-classic", rainbowVclassic);
      parameters.put("rainbow-v-circumzenithal", rainbowVcircumzenithal);
      parameters.put("rainbow-v-compressed", rainbowVcompressed);
   }
}
