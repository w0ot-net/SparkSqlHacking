package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.saber.SABERParameters;
import org.bouncycastle.util.Strings;

public class SABERParameterSpec implements AlgorithmParameterSpec {
   public static final SABERParameterSpec lightsaberkem128r3;
   public static final SABERParameterSpec saberkem128r3;
   public static final SABERParameterSpec firesaberkem128r3;
   public static final SABERParameterSpec lightsaberkem192r3;
   public static final SABERParameterSpec saberkem192r3;
   public static final SABERParameterSpec firesaberkem192r3;
   public static final SABERParameterSpec lightsaberkem256r3;
   public static final SABERParameterSpec saberkem256r3;
   public static final SABERParameterSpec firesaberkem256r3;
   private static Map parameters;
   private final String name;

   private SABERParameterSpec(SABERParameters var1) {
      this.name = var1.getName();
   }

   public String getName() {
      return this.name;
   }

   public static SABERParameterSpec fromName(String var0) {
      return (SABERParameterSpec)parameters.get(Strings.toLowerCase(var0));
   }

   static {
      lightsaberkem128r3 = new SABERParameterSpec(SABERParameters.lightsaberkem128r3);
      saberkem128r3 = new SABERParameterSpec(SABERParameters.saberkem128r3);
      firesaberkem128r3 = new SABERParameterSpec(SABERParameters.firesaberkem128r3);
      lightsaberkem192r3 = new SABERParameterSpec(SABERParameters.lightsaberkem192r3);
      saberkem192r3 = new SABERParameterSpec(SABERParameters.saberkem192r3);
      firesaberkem192r3 = new SABERParameterSpec(SABERParameters.firesaberkem192r3);
      lightsaberkem256r3 = new SABERParameterSpec(SABERParameters.lightsaberkem256r3);
      saberkem256r3 = new SABERParameterSpec(SABERParameters.saberkem256r3);
      firesaberkem256r3 = new SABERParameterSpec(SABERParameters.firesaberkem256r3);
      parameters = new HashMap();
   }
}
