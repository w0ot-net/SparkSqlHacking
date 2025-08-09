package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.Jwts.ENC;
import io.jsonwebtoken.Jwts.KEY;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.MalformedKeyException;
import io.jsonwebtoken.security.SecretJwk;
import io.jsonwebtoken.security.SecretKeyAlgorithm;
import io.jsonwebtoken.security.WeakKeyException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class SecretJwkFactory extends AbstractFamilyJwkFactory {
   SecretJwkFactory() {
      super("oct", SecretKey.class, DefaultSecretJwk.PARAMS);
   }

   protected SecretJwk createJwkFromKey(JwkContext ctx) {
      SecretKey key = (SecretKey)Assert.notNull(ctx.getKey(), "JwkContext key cannot be null.");
      byte[] encoded = null;

      String k;
      try {
         encoded = KeysBridge.getEncoded(key);
         k = (String)Encoders.BASE64URL.encode(encoded);
         Assert.hasText(k, "k value cannot be null or empty.");
      } catch (Throwable t) {
         String msg = "Unable to encode SecretKey to JWK: " + t.getMessage();
         throw new InvalidKeyException(msg, t);
      } finally {
         Bytes.clear(encoded);
      }

      MacAlgorithm mac = DefaultMacAlgorithm.findByKey(key);
      if (mac != null) {
         ctx.put(AbstractJwk.ALG.getId(), mac.getId());
      }

      ctx.put(DefaultSecretJwk.K.getId(), k);
      return this.createJwkFromValues(ctx);
   }

   private static void assertKeyBitLength(byte[] bytes, MacAlgorithm alg) {
      long bitLen = Bytes.bitLength(bytes);
      long requiredBitLen = (long)alg.getKeyBitLength();
      if (bitLen < requiredBitLen) {
         String msg = "Secret JWK " + AbstractJwk.ALG + " value is '" + alg.getId() + "', but the " + DefaultSecretJwk.K + " length is smaller than the " + alg.getId() + " minimum length of " + Bytes.bitsMsg(requiredBitLen) + " required by " + "[JWA RFC 7518, Section 3.2](https://www.rfc-editor.org/rfc/rfc7518.html#section-3.2), " + "2nd paragraph: 'A key of the same size as the hash output or larger MUST be used with this " + "algorithm.'";
         throw new WeakKeyException(msg);
      }
   }

   private static void assertSymmetric(Identifiable alg) {
      if (!(alg instanceof MacAlgorithm) && !(alg instanceof SecretKeyAlgorithm) && !(alg instanceof AeadAlgorithm)) {
         String msg = "Invalid Secret JWK " + AbstractJwk.ALG + " value '" + alg.getId() + "'. Secret JWKs " + "may only be used with symmetric (secret) key algorithms.";
         throw new MalformedKeyException(msg);
      }
   }

   protected SecretJwk createJwkFromValues(JwkContext ctx) {
      ParameterReadable reader = new RequiredParameterReader(ctx);
      byte[] bytes = (byte[])reader.get(DefaultSecretJwk.K);
      String algId = ctx.getAlgorithm();
      if (Strings.hasText(algId)) {
         Identifiable alg = (Identifiable)SIG.get().get(algId);
         if (alg == null) {
            alg = (Identifiable)KEY.get().get(algId);
         }

         if (alg == null) {
            alg = (Identifiable)ENC.get().get(algId);
         }

         if (alg != null) {
            assertSymmetric(alg);
         }

         SecretKey key;
         if (alg instanceof MacAlgorithm) {
            assertKeyBitLength(bytes, (MacAlgorithm)alg);
            String jcaName = ((CryptoAlgorithm)alg).getJcaName();
            Assert.hasText(jcaName, "Algorithm jcaName cannot be null or empty.");
            key = new SecretKeySpec(bytes, jcaName);
         } else {
            key = AesAlgorithm.keyFor(bytes);
         }

         ctx.setKey(key);
         return new DefaultSecretJwk(ctx);
      } else {
         int kBitLen = (int)Bytes.bitLength(bytes);
         SecretKey key;
         if (!ctx.isSigUse() && kBitLen <= SIG.HS256.getKeyBitLength()) {
            key = AesAlgorithm.keyFor(bytes);
         } else {
            key = Keys.hmacShaKeyFor(bytes);
         }

         ctx.setKey(key);
         return new DefaultSecretJwk(ctx);
      }
   }
}
