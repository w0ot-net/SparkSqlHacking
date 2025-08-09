package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DispatchingJwkFactory implements JwkFactory {
   private static final Collection DEFAULT_FACTORIES = createDefaultFactories();
   static final JwkFactory DEFAULT_INSTANCE = new DispatchingJwkFactory();
   private final Collection factories;

   private static Collection createDefaultFactories() {
      List families = new ArrayList(3);
      families.add(new SecretJwkFactory());
      families.add(new AsymmetricJwkFactory(EcPublicJwkFactory.INSTANCE, new EcPrivateJwkFactory()));
      families.add(new AsymmetricJwkFactory(RsaPublicJwkFactory.INSTANCE, new RsaPrivateJwkFactory()));
      families.add(new AsymmetricJwkFactory(OctetPublicJwkFactory.INSTANCE, new OctetPrivateJwkFactory()));
      return families;
   }

   DispatchingJwkFactory() {
      this(DEFAULT_FACTORIES);
   }

   DispatchingJwkFactory(Collection factories) {
      Assert.notEmpty(factories, "FamilyJwkFactory collection cannot be null or empty.");
      this.factories = new ArrayList(factories.size());

      for(FamilyJwkFactory factory : factories) {
         Assert.hasText(factory.getId(), "FamilyJwkFactory.getFactoryId() cannot return null or empty.");
         this.factories.add(factory);
      }

   }

   public JwkContext newContext(JwkContext src, Key key) {
      Assert.notNull(src, "JwkContext cannot be null.");
      String kty = src.getType();
      assertKeyOrKeyType(key, kty);

      for(FamilyJwkFactory factory : this.factories) {
         if (factory.supports(key) || factory.supports(src)) {
            JwkContext<Key> ctx = factory.newContext(src, key);
            return (JwkContext)Assert.notNull(ctx, "FamilyJwkFactory implementation cannot return null JwkContexts.");
         }
      }

      throw noFamily(key, kty);
   }

   private static void assertKeyOrKeyType(Key key, String kty) {
      if (key == null && !Strings.hasText(kty)) {
         String msg = "Either a Key instance or a " + AbstractJwk.KTY + " value is required to create a JWK.";
         throw new InvalidKeyException(msg);
      }
   }

   public Jwk createJwk(JwkContext ctx) {
      Assert.notNull(ctx, "JwkContext cannot be null.");
      Key key = ctx.getKey();
      String kty = Strings.clean(ctx.getType());
      assertKeyOrKeyType(key, kty);

      for(FamilyJwkFactory factory : this.factories) {
         if (factory.supports(ctx)) {
            String algFamilyId = (String)Assert.hasText(factory.getId(), "factory id cannot be null or empty.");
            if (kty == null) {
               ctx.setType(algFamilyId);
            }

            return factory.createJwk(ctx);
         }
      }

      throw noFamily(key, kty);
   }

   private static UnsupportedKeyException noFamily(Key key, String kty) {
      String reason = key != null ? "key of type " + key.getClass().getName() : "kty value '" + kty + "'";
      String msg = "Unable to create JWK for unrecognized " + reason + ": there is no known JWK Factory capable of creating JWKs for this key type.";
      return new UnsupportedKeyException(msg);
   }
}
