package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.OctetPublicJwk;
import java.security.PublicKey;

public class OctetPublicJwkFactory extends OctetJwkFactory {
   static final OctetPublicJwkFactory INSTANCE = new OctetPublicJwkFactory();

   OctetPublicJwkFactory() {
      super(PublicKey.class, DefaultOctetPublicJwk.PARAMS);
   }

   protected OctetPublicJwk createJwkFromKey(JwkContext ctx) {
      PublicKey key = (PublicKey)Assert.notNull(ctx.getKey(), "PublicKey cannot be null.");
      EdwardsCurve crv = EdwardsCurve.forKey(key);
      byte[] x = crv.getKeyMaterial(key);
      Assert.notEmpty(x, "Edwards PublicKey 'x' value cannot be null or empty.");
      put(ctx, DefaultOctetPublicJwk.CRV, crv.getId());
      put(ctx, DefaultOctetPublicJwk.X, x);
      return new DefaultOctetPublicJwk(ctx);
   }

   protected OctetPublicJwk createJwkFromValues(JwkContext ctx) {
      ParameterReadable reader = new RequiredParameterReader(ctx);
      EdwardsCurve curve = getCurve(reader);
      byte[] x = (byte[])reader.get(DefaultOctetPublicJwk.X);
      PublicKey key = curve.toPublicKey(x, ctx.getProvider());
      ctx.setKey(key);
      return new DefaultOctetPublicJwk(ctx);
   }
}
