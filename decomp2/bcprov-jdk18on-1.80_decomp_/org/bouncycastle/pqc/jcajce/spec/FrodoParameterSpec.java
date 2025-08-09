package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.util.Strings;

public class FrodoParameterSpec implements AlgorithmParameterSpec {
   public static final FrodoParameterSpec frodokem640aes;
   public static final FrodoParameterSpec frodokem640shake;
   public static final FrodoParameterSpec frodokem976aes;
   public static final FrodoParameterSpec frodokem976shake;
   public static final FrodoParameterSpec frodokem1344aes;
   public static final FrodoParameterSpec frodokem1344shake;
   private static Map parameters;
   private final String name;

   private FrodoParameterSpec(FrodoParameters var1) {
      this.name = var1.getName();
   }

   public String getName() {
      return this.name;
   }

   public static FrodoParameterSpec fromName(String var0) {
      return (FrodoParameterSpec)parameters.get(Strings.toLowerCase(var0));
   }

   static {
      frodokem640aes = new FrodoParameterSpec(FrodoParameters.frodokem640aes);
      frodokem640shake = new FrodoParameterSpec(FrodoParameters.frodokem640shake);
      frodokem976aes = new FrodoParameterSpec(FrodoParameters.frodokem976aes);
      frodokem976shake = new FrodoParameterSpec(FrodoParameters.frodokem976shake);
      frodokem1344aes = new FrodoParameterSpec(FrodoParameters.frodokem1344aes);
      frodokem1344shake = new FrodoParameterSpec(FrodoParameters.frodokem1344shake);
      parameters = new HashMap();
      parameters.put("frodokem19888r3", frodokem640aes);
      parameters.put("frodokem19888shaker3", frodokem640shake);
      parameters.put("frodokem31296r3", frodokem976aes);
      parameters.put("frodokem31296shaker3", frodokem976shake);
      parameters.put("frodokem43088r3", frodokem1344aes);
      parameters.put("frodokem43088shaker3", frodokem1344shake);
      parameters.put("frodokem640aes", frodokem640aes);
      parameters.put("frodokem640shake", frodokem640shake);
      parameters.put("frodokem976aes", frodokem976aes);
      parameters.put("frodokem976shake", frodokem976shake);
      parameters.put("frodokem1344aes", frodokem1344aes);
      parameters.put("frodokem1344shake", frodokem1344shake);
   }
}
