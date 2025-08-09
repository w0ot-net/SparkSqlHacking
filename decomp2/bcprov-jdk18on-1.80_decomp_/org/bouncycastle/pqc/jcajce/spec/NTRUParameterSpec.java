package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.util.Strings;

public class NTRUParameterSpec implements AlgorithmParameterSpec {
   public static final NTRUParameterSpec ntruhps2048509;
   public static final NTRUParameterSpec ntruhps2048677;
   public static final NTRUParameterSpec ntruhps4096821;
   public static final NTRUParameterSpec ntruhps40961229;
   public static final NTRUParameterSpec ntruhrss701;
   public static final NTRUParameterSpec ntruhrss1373;
   private static Map parameters;
   private final String name;

   private NTRUParameterSpec(NTRUParameters var1) {
      this.name = var1.getName();
   }

   public String getName() {
      return this.name;
   }

   public static NTRUParameterSpec fromName(String var0) {
      return (NTRUParameterSpec)parameters.get(Strings.toLowerCase(var0));
   }

   static {
      ntruhps2048509 = new NTRUParameterSpec(NTRUParameters.ntruhps2048509);
      ntruhps2048677 = new NTRUParameterSpec(NTRUParameters.ntruhps2048677);
      ntruhps4096821 = new NTRUParameterSpec(NTRUParameters.ntruhps4096821);
      ntruhps40961229 = new NTRUParameterSpec(NTRUParameters.ntruhps40961229);
      ntruhrss701 = new NTRUParameterSpec(NTRUParameters.ntruhrss701);
      ntruhrss1373 = new NTRUParameterSpec(NTRUParameters.ntruhrss1373);
      parameters = new HashMap();
      parameters.put("ntruhps2048509", ntruhps2048509);
      parameters.put("ntruhps2048677", ntruhps2048677);
      parameters.put("ntruhps4096821", ntruhps4096821);
      parameters.put("ntruhps40961229", ntruhps40961229);
      parameters.put("ntruhrss701", ntruhrss701);
      parameters.put("ntruhrss1373", ntruhrss1373);
   }
}
