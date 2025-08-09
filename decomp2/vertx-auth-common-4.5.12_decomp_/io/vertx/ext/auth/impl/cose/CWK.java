package io.vertx.ext.auth.impl.cose;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.jose.JWK;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CWK {
   private static final Map COSE_LABELS = Collections.unmodifiableMap(new HashMap() {
      {
         this.put("1", new NV("kty", new String[]{"1", "OKP", "2", "EC", "3", "RSA"}));
         this.put("2", new NV("kid", new String[0]));
         this.put("3", new NV("alg", new String[]{"-7", "ES256", "-8", "EdDSA", "-35", "ES384", "-36", "ES512", "-37", "PS256", "-38", "PS384", "-39", "PS512", "-47", "ES256K", "-257", "RS256", "-258", "RS384", "-259", "RS512", "-65535", "RS1"}));
         this.put("4", new NV("key_ops", new String[0]));
         this.put("5", new NV("base_iv", new String[0]));
      }
   });
   private static final Map EC_KEY_PARAMS = Collections.unmodifiableMap(new HashMap() {
      {
         this.put("-1", new NV("crv", new String[]{"1", "P-256", "2", "P-384", "3", "P-521", "8", "secp256k1"}));
         this.put("-2", new NV("x", new String[0]));
         this.put("-3", new NV("y", new String[0]));
         this.put("-4", new NV("d", new String[0]));
      }
   });
   private static final Map OKP_KEY_PARAMS = Collections.unmodifiableMap(new HashMap() {
      {
         this.put("-1", new NV("crv", new String[]{"4", "X25519", "5", "X448", "6", "Ed25519", "7", "Ed448"}));
         this.put("-2", new NV("x", new String[0]));
         this.put("-3", new NV("y", new String[0]));
         this.put("-4", new NV("d", new String[0]));
      }
   });
   private static final Map RSA_KEY_PARAMS = Collections.unmodifiableMap(new HashMap() {
      {
         this.put("-1", new NV("n", new String[0]));
         this.put("-2", new NV("e", new String[0]));
         this.put("-3", new NV("d", new String[0]));
         this.put("-4", new NV("p", new String[0]));
         this.put("-5", new NV("q", new String[0]));
         this.put("-6", new NV("dp", new String[0]));
         this.put("-7", new NV("dq", new String[0]));
         this.put("-8", new NV("qi", new String[0]));
         this.put("-9", new NV("other", new String[0]));
         this.put("-10", new NV("r_i", new String[0]));
         this.put("-11", new NV("d_i", new String[0]));
         this.put("-12", new NV("t_i", new String[0]));
      }
   });
   private static final Map KEY_PARAMS = Collections.unmodifiableMap(new HashMap() {
      {
         this.put("OKP", CWK.OKP_KEY_PARAMS);
         this.put("EC", CWK.EC_KEY_PARAMS);
         this.put("RSA", CWK.RSA_KEY_PARAMS);
      }
   });

   public static JWK toJWK(Iterable coseMap) {
      JsonObject retKey = new JsonObject();
      Map<String, String> extraMap = new HashMap();

      for(Map.Entry kv : coseMap) {
         String key = (String)kv.getKey();
         String value = kv.getValue().toString();
         if (!COSE_LABELS.containsKey(key)) {
            extraMap.put(key, value);
         } else {
            String name = ((NV)COSE_LABELS.get(key)).name;
            if (((NV)COSE_LABELS.get(key)).values.containsKey(value)) {
               value = (String)((NV)COSE_LABELS.get(key)).values.get(value);
            }

            retKey.put(name, value);
         }
      }

      Map<String, NV> keyParams = (Map)KEY_PARAMS.get(retKey.getString("kty"));

      for(Map.Entry kv : extraMap.entrySet()) {
         String key = (String)kv.getKey();
         String value = (String)kv.getValue();
         if (!keyParams.containsKey(key)) {
            throw new RuntimeException("unknown COSE key label: " + retKey.getString("kty") + " " + key);
         }

         String name = ((NV)keyParams.get(key)).name;
         if (((NV)keyParams.get(key)).values.containsKey(value)) {
            value = (String)((NV)keyParams.get(key)).values.get(value);
         }

         retKey.put(name, value);
      }

      return new JWK(retKey);
   }

   private static class NV {
      private final String name;
      private final Map values;

      private NV(String name, String... pairs) {
         this.name = name;
         if (pairs != null && pairs.length != 0) {
            if (pairs.length % 2 != 0) {
               throw new IllegalArgumentException("pairs must have even length");
            }

            Map<String, String> tmp = new HashMap();

            for(int i = 0; i < pairs.length; i += 2) {
               tmp.put(pairs[i], pairs[i + 1]);
            }

            this.values = Collections.unmodifiableMap(tmp);
         } else {
            this.values = Collections.emptyMap();
         }

      }
   }
}
