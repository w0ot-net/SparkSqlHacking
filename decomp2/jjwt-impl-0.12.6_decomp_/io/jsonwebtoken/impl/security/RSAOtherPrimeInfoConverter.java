package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.MalformedKeyException;
import java.math.BigInteger;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class RSAOtherPrimeInfoConverter implements Converter {
   static final RSAOtherPrimeInfoConverter INSTANCE = new RSAOtherPrimeInfoConverter();
   static final Parameter PRIME_FACTOR = Parameters.secretBigInt("r", "Prime Factor");
   static final Parameter FACTOR_CRT_EXPONENT = Parameters.secretBigInt("d", "Factor CRT Exponent");
   static final Parameter FACTOR_CRT_COEFFICIENT = Parameters.secretBigInt("t", "Factor CRT Coefficient");
   static final Set PARAMS;

   public Object applyTo(RSAOtherPrimeInfo info) {
      Map<String, Object> m = new LinkedHashMap(3);
      m.put(PRIME_FACTOR.getId(), PRIME_FACTOR.applyTo(info.getPrime()));
      m.put(FACTOR_CRT_EXPONENT.getId(), FACTOR_CRT_EXPONENT.applyTo(info.getExponent()));
      m.put(FACTOR_CRT_COEFFICIENT.getId(), FACTOR_CRT_COEFFICIENT.applyTo(info.getCrtCoefficient()));
      return m;
   }

   public RSAOtherPrimeInfo applyFrom(Object o) {
      if (o == null) {
         throw new MalformedKeyException("RSA JWK 'oth' (Other Prime Info) element cannot be null.");
      } else if (!(o instanceof Map)) {
         String msg = "RSA JWK 'oth' (Other Prime Info) must contain map elements of name/value pairs. Element type found: " + o.getClass().getName();
         throw new MalformedKeyException(msg);
      } else {
         Map<?, ?> m = (Map)o;
         if (Collections.isEmpty(m)) {
            throw new MalformedKeyException("RSA JWK 'oth' (Other Prime Info) element map cannot be empty.");
         } else {
            JwkContext<?> ctx = new DefaultJwkContext(PARAMS);

            try {
               for(Map.Entry entry : m.entrySet()) {
                  String name = String.valueOf(entry.getKey());
                  ctx.put(name, entry.getValue());
               }
            } catch (Exception e) {
               throw new MalformedKeyException(e.getMessage(), e);
            }

            ParameterReadable reader = new RequiredParameterReader(ctx);
            BigInteger prime = (BigInteger)reader.get(PRIME_FACTOR);
            BigInteger primeExponent = (BigInteger)reader.get(FACTOR_CRT_EXPONENT);
            BigInteger crtCoefficient = (BigInteger)reader.get(FACTOR_CRT_COEFFICIENT);
            return new RSAOtherPrimeInfo(prime, primeExponent, crtCoefficient);
         }
      }
   }

   static {
      PARAMS = Collections.setOf(new Parameter[]{PRIME_FACTOR, FACTOR_CRT_EXPONENT, FACTOR_CRT_COEFFICIENT});
   }
}
