package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.PrivateJwk;
import io.jsonwebtoken.security.RsaPrivateJwk;
import io.jsonwebtoken.security.RsaPublicJwk;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;
import java.util.Set;

class DefaultRsaPrivateJwk extends AbstractPrivateJwk implements RsaPrivateJwk {
   static final Parameter PRIVATE_EXPONENT = Parameters.secretBigInt("d", "Private Exponent");
   static final Parameter FIRST_PRIME = Parameters.secretBigInt("p", "First Prime Factor");
   static final Parameter SECOND_PRIME = Parameters.secretBigInt("q", "Second Prime Factor");
   static final Parameter FIRST_CRT_EXPONENT = Parameters.secretBigInt("dp", "First Factor CRT Exponent");
   static final Parameter SECOND_CRT_EXPONENT = Parameters.secretBigInt("dq", "Second Factor CRT Exponent");
   static final Parameter FIRST_CRT_COEFFICIENT = Parameters.secretBigInt("qi", "First CRT Coefficient");
   static final Parameter OTHER_PRIMES_INFO;
   static final Set PARAMS;

   DefaultRsaPrivateJwk(JwkContext ctx, RsaPublicJwk pubJwk) {
      super(ctx, DefaultRsaPublicJwk.THUMBPRINT_PARAMS, pubJwk);
   }

   private static boolean equals(RSAOtherPrimeInfo a, RSAOtherPrimeInfo b) {
      if (a == b) {
         return true;
      } else if (a != null && b != null) {
         return Parameters.bytesEquals(a.getPrime(), b.getPrime()) && Parameters.bytesEquals(a.getExponent(), b.getExponent()) && Parameters.bytesEquals(a.getCrtCoefficient(), b.getCrtCoefficient());
      } else {
         return false;
      }
   }

   private static boolean equalsOtherPrimes(ParameterReadable a, ParameterReadable b) {
      List<RSAOtherPrimeInfo> aOthers = (List)a.get(OTHER_PRIMES_INFO);
      List<RSAOtherPrimeInfo> bOthers = (List)b.get(OTHER_PRIMES_INFO);
      int aSize = Collections.size(aOthers);
      int bSize = Collections.size(bOthers);
      if (aSize != bSize) {
         return false;
      } else if (aSize == 0) {
         return true;
      } else {
         RSAOtherPrimeInfo[] aInfos = (RSAOtherPrimeInfo[])aOthers.toArray(new RSAOtherPrimeInfo[0]);
         RSAOtherPrimeInfo[] bInfos = (RSAOtherPrimeInfo[])bOthers.toArray(new RSAOtherPrimeInfo[0]);

         for(int i = 0; i < aSize; ++i) {
            if (!equals(aInfos[i], bInfos[i])) {
               return false;
            }
         }

         return true;
      }
   }

   protected boolean equals(PrivateJwk jwk) {
      return jwk instanceof RsaPrivateJwk && DefaultRsaPublicJwk.equalsPublic(this, jwk) && Parameters.equals((ParameterReadable)this, jwk, PRIVATE_EXPONENT) && Parameters.equals((ParameterReadable)this, jwk, FIRST_PRIME) && Parameters.equals((ParameterReadable)this, jwk, SECOND_PRIME) && Parameters.equals((ParameterReadable)this, jwk, FIRST_CRT_EXPONENT) && Parameters.equals((ParameterReadable)this, jwk, SECOND_CRT_EXPONENT) && Parameters.equals((ParameterReadable)this, jwk, FIRST_CRT_COEFFICIENT) && equalsOtherPrimes(this, (ParameterReadable)jwk);
   }

   static {
      OTHER_PRIMES_INFO = (Parameter)Parameters.builder(RSAOtherPrimeInfo.class).setId("oth").setName("Other Primes Info").setConverter(RSAOtherPrimeInfoConverter.INSTANCE).list().build();
      PARAMS = Collections.concat(DefaultRsaPublicJwk.PARAMS, new Parameter[]{PRIVATE_EXPONENT, FIRST_PRIME, SECOND_PRIME, FIRST_CRT_EXPONENT, SECOND_CRT_EXPONENT, FIRST_CRT_COEFFICIENT, OTHER_PRIMES_INFO});
   }
}
