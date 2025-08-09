package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.RsaPrivateJwk;
import io.jsonwebtoken.security.RsaPublicJwk;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.RSAMultiPrimePrivateCrtKeySpec;
import java.security.spec.RSAOtherPrimeInfo;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.List;
import java.util.Set;

class RsaPrivateJwkFactory extends AbstractFamilyJwkFactory {
   private static final Set OPTIONAL_PRIVATE_PARAMS;
   private static final String PUBKEY_ERR_MSG;
   private static final String PUB_EXPONENT_EX_MSG;

   RsaPrivateJwkFactory() {
      super("RSA", RSAPrivateKey.class, DefaultRsaPrivateJwk.PARAMS);
   }

   protected boolean supportsKeyValues(JwkContext ctx) {
      return super.supportsKeyValues(ctx) && ctx.containsKey(DefaultRsaPrivateJwk.PRIVATE_EXPONENT.getId());
   }

   private static BigInteger getPublicExponent(RSAPrivateKey key) {
      if (key instanceof RSAPrivateCrtKey) {
         return ((RSAPrivateCrtKey)key).getPublicExponent();
      } else if (key instanceof RSAMultiPrimePrivateCrtKey) {
         return ((RSAMultiPrimePrivateCrtKey)key).getPublicExponent();
      } else {
         String msg = String.format(PUB_EXPONENT_EX_MSG, KeysBridge.toString(key));
         throw new UnsupportedKeyException(msg);
      }
   }

   private RSAPublicKey derivePublic(final JwkContext ctx) {
      RSAPrivateKey key = (RSAPrivateKey)ctx.getKey();
      BigInteger modulus = key.getModulus();
      BigInteger publicExponent = getPublicExponent(key);
      final RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
      return (RSAPublicKey)this.generateKey(ctx, RSAPublicKey.class, new CheckedFunction() {
         public RSAPublicKey apply(KeyFactory kf) {
            try {
               return (RSAPublicKey)kf.generatePublic(spec);
            } catch (Exception e) {
               String msg = "Unable to derive RSAPublicKey from RSAPrivateKey " + ctx + ". Cause: " + e.getMessage();
               throw new InvalidKeyException(msg);
            }
         }
      });
   }

   protected RsaPrivateJwk createJwkFromKey(JwkContext ctx) {
      RSAPrivateKey key = (RSAPrivateKey)ctx.getKey();
      PublicKey publicKey = ctx.getPublicKey();
      RSAPublicKey rsaPublicKey;
      if (publicKey != null) {
         rsaPublicKey = (RSAPublicKey)Assert.isInstanceOf(RSAPublicKey.class, publicKey, PUBKEY_ERR_MSG);
      } else {
         rsaPublicKey = this.derivePublic(ctx);
      }

      boolean copyId = !Strings.hasText(ctx.getId()) && ctx.getIdThumbprintAlgorithm() != null;
      JwkContext<RSAPublicKey> pubCtx = RsaPublicJwkFactory.INSTANCE.newContext(ctx, rsaPublicKey);
      RsaPublicJwk pubJwk = (RsaPublicJwk)RsaPublicJwkFactory.INSTANCE.createJwk(pubCtx);
      ctx.putAll(pubJwk);
      if (copyId) {
         ctx.setId(pubJwk.getId());
      }

      put(ctx, DefaultRsaPrivateJwk.PRIVATE_EXPONENT, key.getPrivateExponent());
      if (key instanceof RSAPrivateCrtKey) {
         RSAPrivateCrtKey ckey = (RSAPrivateCrtKey)key;
         put(ctx, DefaultRsaPrivateJwk.FIRST_PRIME, ckey.getPrimeP());
         put(ctx, DefaultRsaPrivateJwk.SECOND_PRIME, ckey.getPrimeQ());
         put(ctx, DefaultRsaPrivateJwk.FIRST_CRT_EXPONENT, ckey.getPrimeExponentP());
         put(ctx, DefaultRsaPrivateJwk.SECOND_CRT_EXPONENT, ckey.getPrimeExponentQ());
         put(ctx, DefaultRsaPrivateJwk.FIRST_CRT_COEFFICIENT, ckey.getCrtCoefficient());
      } else if (key instanceof RSAMultiPrimePrivateCrtKey) {
         RSAMultiPrimePrivateCrtKey ckey = (RSAMultiPrimePrivateCrtKey)key;
         put(ctx, DefaultRsaPrivateJwk.FIRST_PRIME, ckey.getPrimeP());
         put(ctx, DefaultRsaPrivateJwk.SECOND_PRIME, ckey.getPrimeQ());
         put(ctx, DefaultRsaPrivateJwk.FIRST_CRT_EXPONENT, ckey.getPrimeExponentP());
         put(ctx, DefaultRsaPrivateJwk.SECOND_CRT_EXPONENT, ckey.getPrimeExponentQ());
         put(ctx, DefaultRsaPrivateJwk.FIRST_CRT_COEFFICIENT, ckey.getCrtCoefficient());
         List<RSAOtherPrimeInfo> infos = Arrays.asList(ckey.getOtherPrimeInfo());
         if (!Collections.isEmpty(infos)) {
            put(ctx, DefaultRsaPrivateJwk.OTHER_PRIMES_INFO, infos);
         }
      }

      return new DefaultRsaPrivateJwk(ctx, pubJwk);
   }

   protected RsaPrivateJwk createJwkFromValues(JwkContext ctx) {
      ParameterReadable reader = new RequiredParameterReader(ctx);
      BigInteger privateExponent = (BigInteger)reader.get(DefaultRsaPrivateJwk.PRIVATE_EXPONENT);
      JwkContext<RSAPublicKey> pubCtx = new DefaultJwkContext(DefaultRsaPublicJwk.PARAMS, ctx);
      RsaPublicJwk pubJwk = RsaPublicJwkFactory.INSTANCE.createJwkFromValues(pubCtx);
      RSAPublicKey pubKey = (RSAPublicKey)pubJwk.toKey();
      BigInteger modulus = pubKey.getModulus();
      BigInteger publicExponent = pubKey.getPublicExponent();
      boolean containsOptional = false;

      for(Parameter param : OPTIONAL_PRIVATE_PARAMS) {
         if (ctx.containsKey(param.getId())) {
            containsOptional = true;
            break;
         }
      }

      KeySpec spec;
      if (containsOptional) {
         BigInteger firstPrime = (BigInteger)reader.get(DefaultRsaPrivateJwk.FIRST_PRIME);
         BigInteger secondPrime = (BigInteger)reader.get(DefaultRsaPrivateJwk.SECOND_PRIME);
         BigInteger firstCrtExponent = (BigInteger)reader.get(DefaultRsaPrivateJwk.FIRST_CRT_EXPONENT);
         BigInteger secondCrtExponent = (BigInteger)reader.get(DefaultRsaPrivateJwk.SECOND_CRT_EXPONENT);
         BigInteger firstCrtCoefficient = (BigInteger)reader.get(DefaultRsaPrivateJwk.FIRST_CRT_COEFFICIENT);
         if (ctx.containsKey(DefaultRsaPrivateJwk.OTHER_PRIMES_INFO.getId())) {
            List<RSAOtherPrimeInfo> otherPrimes = (List)reader.get(DefaultRsaPrivateJwk.OTHER_PRIMES_INFO);
            RSAOtherPrimeInfo[] arr = new RSAOtherPrimeInfo[Collections.size(otherPrimes)];
            arr = (RSAOtherPrimeInfo[])otherPrimes.toArray(arr);
            spec = new RSAMultiPrimePrivateCrtKeySpec(modulus, publicExponent, privateExponent, firstPrime, secondPrime, firstCrtExponent, secondCrtExponent, firstCrtCoefficient, arr);
         } else {
            spec = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, firstPrime, secondPrime, firstCrtExponent, secondCrtExponent, firstCrtCoefficient);
         }
      } else {
         spec = new RSAPrivateKeySpec(modulus, privateExponent);
      }

      RSAPrivateKey key = this.generateFromSpec(ctx, spec);
      ctx.setKey(key);
      return new DefaultRsaPrivateJwk(ctx, pubJwk);
   }

   protected RSAPrivateKey generateFromSpec(JwkContext ctx, final KeySpec keySpec) {
      return (RSAPrivateKey)this.generateKey(ctx, new CheckedFunction() {
         public RSAPrivateKey apply(KeyFactory kf) throws Exception {
            return (RSAPrivateKey)kf.generatePrivate(keySpec);
         }
      });
   }

   static {
      OPTIONAL_PRIVATE_PARAMS = Collections.setOf(new Parameter[]{DefaultRsaPrivateJwk.FIRST_PRIME, DefaultRsaPrivateJwk.SECOND_PRIME, DefaultRsaPrivateJwk.FIRST_CRT_EXPONENT, DefaultRsaPrivateJwk.SECOND_CRT_EXPONENT, DefaultRsaPrivateJwk.FIRST_CRT_COEFFICIENT});
      PUBKEY_ERR_MSG = "JwkContext publicKey must be an " + RSAPublicKey.class.getName() + " instance.";
      PUB_EXPONENT_EX_MSG = "Unable to derive RSAPublicKey from RSAPrivateKey [%s]. Supported keys implement the " + RSAPrivateCrtKey.class.getName() + " or " + RSAMultiPrimePrivateCrtKey.class.getName() + " interfaces.  If the specified RSAPrivateKey cannot be one of these two, you must explicitly " + "provide an RSAPublicKey in addition to the RSAPrivateKey, as the " + "[JWA RFC, Section 6.3.2](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2) " + "requires public values to be present in private RSA JWKs.";
   }
}
