package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.util.Strings;

public class DilithiumParameterSpec implements AlgorithmParameterSpec {
   public static final DilithiumParameterSpec dilithium2;
   public static final DilithiumParameterSpec dilithium3;
   public static final DilithiumParameterSpec dilithium5;
   private static Map parameters;
   private final String name;

   private DilithiumParameterSpec(DilithiumParameters var1) {
      this.name = Strings.toUpperCase(var1.getName());
   }

   public String getName() {
      return this.name;
   }

   public static DilithiumParameterSpec fromName(String var0) {
      return (DilithiumParameterSpec)parameters.get(Strings.toLowerCase(var0));
   }

   static {
      dilithium2 = new DilithiumParameterSpec(DilithiumParameters.dilithium2);
      dilithium3 = new DilithiumParameterSpec(DilithiumParameters.dilithium3);
      dilithium5 = new DilithiumParameterSpec(DilithiumParameters.dilithium5);
      parameters = new HashMap();
      parameters.put("dilithium2", dilithium2);
      parameters.put("dilithium3", dilithium3);
      parameters.put("dilithium5", dilithium5);
   }
}
