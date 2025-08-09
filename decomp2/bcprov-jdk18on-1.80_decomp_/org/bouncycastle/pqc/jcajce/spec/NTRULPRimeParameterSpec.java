package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimeParameters;
import org.bouncycastle.util.Strings;

public class NTRULPRimeParameterSpec implements AlgorithmParameterSpec {
   public static final NTRULPRimeParameterSpec ntrulpr653;
   public static final NTRULPRimeParameterSpec ntrulpr761;
   public static final NTRULPRimeParameterSpec ntrulpr857;
   public static final NTRULPRimeParameterSpec ntrulpr953;
   public static final NTRULPRimeParameterSpec ntrulpr1013;
   public static final NTRULPRimeParameterSpec ntrulpr1277;
   private static Map parameters;
   private final String name;

   private NTRULPRimeParameterSpec(NTRULPRimeParameters var1) {
      this.name = var1.getName();
   }

   public String getName() {
      return this.name;
   }

   public static NTRULPRimeParameterSpec fromName(String var0) {
      return (NTRULPRimeParameterSpec)parameters.get(Strings.toLowerCase(var0));
   }

   static {
      ntrulpr653 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr653);
      ntrulpr761 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr761);
      ntrulpr857 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr857);
      ntrulpr953 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr953);
      ntrulpr1013 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr1013);
      ntrulpr1277 = new NTRULPRimeParameterSpec(NTRULPRimeParameters.ntrulpr1277);
      parameters = new HashMap();
      parameters.put("ntrulpr653", ntrulpr653);
      parameters.put("ntrulpr761", ntrulpr761);
      parameters.put("ntrulpr857", ntrulpr857);
      parameters.put("ntrulpr953", ntrulpr953);
      parameters.put("ntrulpr1013", ntrulpr1013);
      parameters.put("ntrulpr1277", ntrulpr1277);
   }
}
