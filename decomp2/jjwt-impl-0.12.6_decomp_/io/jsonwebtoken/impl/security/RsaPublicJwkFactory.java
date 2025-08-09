package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.security.RsaPublicJwk;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

class RsaPublicJwkFactory extends AbstractFamilyJwkFactory {
   static final RsaPublicJwkFactory INSTANCE = new RsaPublicJwkFactory();

   RsaPublicJwkFactory() {
      super("RSA", RSAPublicKey.class, DefaultRsaPublicJwk.PARAMS);
   }

   protected RsaPublicJwk createJwkFromKey(JwkContext ctx) {
      RSAPublicKey key = (RSAPublicKey)ctx.getKey();
      ctx.put(DefaultRsaPublicJwk.MODULUS.getId(), DefaultRsaPublicJwk.MODULUS.applyTo(key.getModulus()));
      ctx.put(DefaultRsaPublicJwk.PUBLIC_EXPONENT.getId(), DefaultRsaPublicJwk.PUBLIC_EXPONENT.applyTo(key.getPublicExponent()));
      return new DefaultRsaPublicJwk(ctx);
   }

   protected RsaPublicJwk createJwkFromValues(JwkContext ctx) {
      ParameterReadable reader = new RequiredParameterReader(ctx);
      BigInteger modulus = (BigInteger)reader.get(DefaultRsaPublicJwk.MODULUS);
      BigInteger publicExponent = (BigInteger)reader.get(DefaultRsaPublicJwk.PUBLIC_EXPONENT);
      final RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
      RSAPublicKey key = (RSAPublicKey)this.generateKey(ctx, new CheckedFunction() {
         public RSAPublicKey apply(KeyFactory keyFactory) throws Exception {
            return (RSAPublicKey)keyFactory.generatePublic(spec);
         }
      });
      ctx.setKey(key);
      return new DefaultRsaPublicJwk(ctx);
   }
}
