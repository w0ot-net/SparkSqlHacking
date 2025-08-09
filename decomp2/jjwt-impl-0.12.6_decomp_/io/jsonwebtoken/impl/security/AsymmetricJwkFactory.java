package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Jwk;
import java.security.Key;

class AsymmetricJwkFactory implements FamilyJwkFactory {
   private final String id;
   private final FamilyJwkFactory publicFactory;
   private final FamilyJwkFactory privateFactory;

   AsymmetricJwkFactory(FamilyJwkFactory publicFactory, FamilyJwkFactory privateFactory) {
      this.publicFactory = (FamilyJwkFactory)Assert.notNull(publicFactory, "publicFactory cannot be null.");
      this.privateFactory = (FamilyJwkFactory)Assert.notNull(privateFactory, "privateFactory cannot be null.");
      this.id = (String)Assert.notNull(publicFactory.getId(), "publicFactory id cannot be null or empty.");
      Assert.isTrue(this.id.equals(privateFactory.getId()), "privateFactory id must equal publicFactory id");
   }

   public String getId() {
      return this.id;
   }

   public boolean supports(JwkContext ctx) {
      return ctx != null && (this.id.equals(ctx.getType()) || this.privateFactory.supports(ctx) || this.publicFactory.supports(ctx));
   }

   public boolean supports(Key key) {
      return key != null && (this.privateFactory.supports(key) || this.publicFactory.supports(key));
   }

   public JwkContext newContext(JwkContext src, Key key) {
      return !this.privateFactory.supports(key) && !this.privateFactory.supports(src) ? this.publicFactory.newContext(src, key) : this.privateFactory.newContext(src, key);
   }

   public Jwk createJwk(JwkContext ctx) {
      return this.privateFactory.supports(ctx) ? this.privateFactory.createJwk(ctx) : this.publicFactory.createJwk(ctx);
   }
}
