package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.Jwk;
import java.security.Key;
import java.security.KeyFactory;
import java.util.Set;

abstract class AbstractFamilyJwkFactory implements FamilyJwkFactory {
   private final String ktyValue;
   private final Class keyType;
   private final Set params;

   protected static void put(JwkContext ctx, Parameter param, Object value) {
      ctx.put(param.getId(), param.applyTo(value));
   }

   AbstractFamilyJwkFactory(String ktyValue, Class keyType, Set params) {
      this.ktyValue = (String)Assert.hasText(ktyValue, "keyType argument cannot be null or empty.");
      this.keyType = (Class)Assert.notNull(keyType, "keyType class cannot be null.");
      this.params = (Set)Assert.notEmpty(params, "Parameters collection cannot be null or empty.");
   }

   public String getId() {
      return this.ktyValue;
   }

   public boolean supports(Key key) {
      return this.keyType.isInstance(key);
   }

   public JwkContext newContext(JwkContext src, Key key) {
      Assert.notNull(src, "Source JwkContext cannot be null.");
      return key != null ? new DefaultJwkContext(this.params, src, key) : new DefaultJwkContext(this.params, src, false);
   }

   public boolean supports(JwkContext ctx) {
      return this.supports(ctx.getKey()) || this.supportsKeyValues(ctx);
   }

   protected boolean supportsKeyValues(JwkContext ctx) {
      return this.ktyValue.equals(ctx.getType());
   }

   protected Key generateKey(JwkContext ctx, CheckedFunction fn) {
      return this.generateKey(ctx, this.keyType, fn);
   }

   protected String getKeyFactoryJcaName(JwkContext ctx) {
      String jcaName = KeysBridge.findAlgorithm(ctx.getKey());
      return Strings.hasText(jcaName) ? jcaName : this.getId();
   }

   protected Key generateKey(JwkContext ctx, Class type, CheckedFunction fn) {
      // $FF: Couldn't be decompiled
   }

   public final Jwk createJwk(JwkContext ctx) {
      Assert.notNull(ctx, "JwkContext argument cannot be null.");
      if (!this.supports(ctx)) {
         String msg = "Unsupported JwkContext.";
         throw new IllegalArgumentException(msg);
      } else {
         K key = (K)ctx.getKey();
         if (key != null) {
            ctx.setType(this.ktyValue);
            return this.createJwkFromKey(ctx);
         } else {
            return this.createJwkFromValues(ctx);
         }
      }
   }

   protected abstract Jwk createJwkFromKey(JwkContext var1);

   protected abstract Jwk createJwkFromValues(JwkContext var1);
}
