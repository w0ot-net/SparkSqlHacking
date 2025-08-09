package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.OctetPrivateJwk;
import io.jsonwebtoken.security.OctetPublicJwk;
import java.security.PrivateKey;
import java.security.PublicKey;

public class OctetPrivateJwkFactory extends OctetJwkFactory {
   public OctetPrivateJwkFactory() {
      super(PrivateKey.class, DefaultOctetPrivateJwk.PARAMS);
   }

   protected boolean supportsKeyValues(JwkContext ctx) {
      return super.supportsKeyValues(ctx) && ctx.containsKey(DefaultOctetPrivateJwk.D.getId());
   }

   protected OctetPrivateJwk createJwkFromKey(JwkContext ctx) {
      PrivateKey key = (PrivateKey)Assert.notNull(ctx.getKey(), "PrivateKey cannot be null.");
      EdwardsCurve crv = EdwardsCurve.forKey(key);
      PublicKey pub = ctx.getPublicKey();
      if (pub != null) {
         if (!crv.equals(EdwardsCurve.forKey(pub))) {
            String msg = "Specified Edwards Curve PublicKey does not match the specified PrivateKey's curve.";
            throw new InvalidKeyException(msg);
         }
      } else {
         pub = EdwardsCurve.derivePublic(key);
      }

      boolean copyId = !Strings.hasText(ctx.getId()) && ctx.getIdThumbprintAlgorithm() != null;
      JwkContext<PublicKey> pubCtx = OctetPublicJwkFactory.INSTANCE.newContext(ctx, pub);
      OctetPublicJwk<PublicKey> pubJwk = (OctetPublicJwk)OctetPublicJwkFactory.INSTANCE.createJwk(pubCtx);
      ctx.putAll(pubJwk);
      if (copyId) {
         ctx.setId(pubJwk.getId());
      }

      byte[] d = crv.getKeyMaterial(key);
      Assert.notEmpty(d, "Edwards PrivateKey 'd' value cannot be null or empty.");
      put(ctx, DefaultOctetPrivateJwk.D, d);
      return new DefaultOctetPrivateJwk(ctx, pubJwk);
   }

   protected OctetPrivateJwk createJwkFromValues(JwkContext ctx) {
      ParameterReadable reader = new RequiredParameterReader(ctx);
      EdwardsCurve curve = getCurve(reader);
      JwkContext<PublicKey> pubCtx = new DefaultJwkContext(DefaultOctetPublicJwk.PARAMS, ctx);
      OctetPublicJwk<PublicKey> pubJwk = OctetPublicJwkFactory.INSTANCE.createJwkFromValues(pubCtx);
      byte[] d = (byte[])reader.get(DefaultOctetPrivateJwk.D);
      PrivateKey key = curve.toPrivateKey(d, ctx.getProvider());
      ctx.setKey(key);
      return new DefaultOctetPrivateJwk(ctx, pubJwk);
   }
}
