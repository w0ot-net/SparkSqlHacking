package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.EcPrivateJwk;
import io.jsonwebtoken.security.EcPublicJwk;
import io.jsonwebtoken.security.InvalidKeyException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;

class EcPrivateJwkFactory extends AbstractEcJwkFactory {
   private static final String ECPUBKEY_ERR_MSG = "JwkContext publicKey must be an " + ECPublicKey.class.getName() + " instance.";
   private static final EcPublicJwkFactory PUB_FACTORY;

   EcPrivateJwkFactory() {
      super(ECPrivateKey.class, DefaultEcPrivateJwk.PARAMS);
   }

   protected boolean supportsKeyValues(JwkContext ctx) {
      return super.supportsKeyValues(ctx) && ctx.containsKey(DefaultEcPrivateJwk.D.getId());
   }

   protected ECPublicKey derivePublic(KeyFactory keyFactory, ECPublicKeySpec spec) throws InvalidKeySpecException {
      return (ECPublicKey)keyFactory.generatePublic(spec);
   }

   protected ECPublicKey derivePublic(JwkContext ctx) {
      final ECPrivateKey key = (ECPrivateKey)ctx.getKey();
      return (ECPublicKey)this.generateKey(ctx, ECPublicKey.class, new CheckedFunction() {
         public ECPublicKey apply(KeyFactory kf) {
            try {
               ECPublicKeySpec spec = ECCurve.publicKeySpec(key);
               return EcPrivateJwkFactory.this.derivePublic(kf, spec);
            } catch (Exception e) {
               String msg = "Unable to derive ECPublicKey from ECPrivateKey: " + e.getMessage();
               throw new InvalidKeyException(msg, e);
            }
         }
      });
   }

   protected EcPrivateJwk createJwkFromKey(JwkContext ctx) {
      ECPrivateKey key = (ECPrivateKey)ctx.getKey();
      PublicKey publicKey = ctx.getPublicKey();
      ECPublicKey ecPublicKey;
      if (publicKey != null) {
         ecPublicKey = (ECPublicKey)Assert.isInstanceOf(ECPublicKey.class, publicKey, ECPUBKEY_ERR_MSG);
      } else {
         ecPublicKey = this.derivePublic(ctx);
      }

      boolean copyId = !Strings.hasText(ctx.getId()) && ctx.getIdThumbprintAlgorithm() != null;
      JwkContext<ECPublicKey> pubCtx = PUB_FACTORY.newContext(ctx, ecPublicKey);
      EcPublicJwk pubJwk = (EcPublicJwk)PUB_FACTORY.createJwk(pubCtx);
      ctx.putAll(pubJwk);
      if (copyId) {
         ctx.setId(pubJwk.getId());
      }

      String d = toOctetString(key.getParams().getCurve(), key.getS());
      ctx.put(DefaultEcPrivateJwk.D.getId(), d);
      return new DefaultEcPrivateJwk(ctx, pubJwk);
   }

   protected EcPrivateJwk createJwkFromValues(JwkContext ctx) {
      ParameterReadable reader = new RequiredParameterReader(ctx);
      String curveId = (String)reader.get(DefaultEcPublicJwk.CRV);
      BigInteger d = (BigInteger)reader.get(DefaultEcPrivateJwk.D);
      JwkContext<ECPublicKey> pubCtx = new DefaultJwkContext(DefaultEcPublicJwk.PARAMS, ctx);
      EcPublicJwk pubJwk = (EcPublicJwk)EcPublicJwkFactory.INSTANCE.createJwk(pubCtx);
      ECCurve curve = getCurveByJwaId(curveId);
      final ECPrivateKeySpec privateSpec = new ECPrivateKeySpec(d, curve.toParameterSpec());
      ECPrivateKey key = (ECPrivateKey)this.generateKey(ctx, new CheckedFunction() {
         public ECPrivateKey apply(KeyFactory kf) throws Exception {
            return (ECPrivateKey)kf.generatePrivate(privateSpec);
         }
      });
      ctx.setKey(key);
      return new DefaultEcPrivateJwk(ctx, pubJwk);
   }

   static {
      PUB_FACTORY = EcPublicJwkFactory.INSTANCE;
   }
}
