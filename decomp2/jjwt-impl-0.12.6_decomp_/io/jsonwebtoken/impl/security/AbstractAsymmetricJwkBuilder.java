package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.ParameterMap;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AsymmetricJwk;
import io.jsonwebtoken.security.AsymmetricJwkBuilder;
import io.jsonwebtoken.security.EcPrivateJwkBuilder;
import io.jsonwebtoken.security.EcPublicJwkBuilder;
import io.jsonwebtoken.security.MalformedKeyException;
import io.jsonwebtoken.security.OctetPrivateJwkBuilder;
import io.jsonwebtoken.security.OctetPublicJwkBuilder;
import io.jsonwebtoken.security.PrivateJwkBuilder;
import io.jsonwebtoken.security.PublicJwkBuilder;
import io.jsonwebtoken.security.RsaPrivateJwkBuilder;
import io.jsonwebtoken.security.RsaPublicJwkBuilder;
import java.net.URI;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

abstract class AbstractAsymmetricJwkBuilder extends AbstractJwkBuilder implements AsymmetricJwkBuilder {
   protected Boolean applyX509KeyUse;
   private KeyUseStrategy keyUseStrategy;
   private final X509BuilderSupport x509;

   public AbstractAsymmetricJwkBuilder(JwkContext ctx) {
      super(ctx);
      this.applyX509KeyUse = null;
      this.keyUseStrategy = DefaultKeyUseStrategy.INSTANCE;
      ParameterMap map = (ParameterMap)Assert.isInstanceOf(ParameterMap.class, this.DELEGATE);
      this.x509 = new X509BuilderSupport(map, MalformedKeyException.class);
   }

   AbstractAsymmetricJwkBuilder(AbstractAsymmetricJwkBuilder b, JwkContext ctx) {
      this(ctx);
      this.applyX509KeyUse = b.applyX509KeyUse;
      this.keyUseStrategy = b.keyUseStrategy;
   }

   public AsymmetricJwkBuilder publicKeyUse(String use) {
      Assert.hasText(use, "publicKeyUse cannot be null or empty.");
      ((JwkContext)this.DELEGATE).setPublicKeyUse(use);
      return (AsymmetricJwkBuilder)this.self();
   }

   public AsymmetricJwkBuilder x509Chain(List chain) {
      Assert.notEmpty(chain, "X509Certificate chain cannot be null or empty.");
      this.x509.x509Chain(chain);
      return (AsymmetricJwkBuilder)this.self();
   }

   public AsymmetricJwkBuilder x509Url(URI uri) {
      Assert.notNull(uri, "X509Url cannot be null.");
      this.x509.x509Url(uri);
      return (AsymmetricJwkBuilder)this.self();
   }

   public AsymmetricJwkBuilder x509Sha1Thumbprint(byte[] thumbprint) {
      this.x509.x509Sha1Thumbprint(thumbprint);
      return (AsymmetricJwkBuilder)this.self();
   }

   public AsymmetricJwkBuilder x509Sha256Thumbprint(byte[] thumbprint) {
      this.x509.x509Sha256Thumbprint(thumbprint);
      return (AsymmetricJwkBuilder)this.self();
   }

   public AsymmetricJwkBuilder x509Sha1Thumbprint(boolean enable) {
      this.x509.x509Sha1Thumbprint(enable);
      return (AsymmetricJwkBuilder)this.self();
   }

   public AsymmetricJwkBuilder x509Sha256Thumbprint(boolean enable) {
      this.x509.x509Sha256Thumbprint(enable);
      return (AsymmetricJwkBuilder)this.self();
   }

   public AsymmetricJwk build() {
      this.x509.apply();
      return (AsymmetricJwk)super.build();
   }

   private abstract static class DefaultPublicJwkBuilder extends AbstractAsymmetricJwkBuilder implements PublicJwkBuilder {
      DefaultPublicJwkBuilder(JwkContext ctx) {
         super(ctx);
      }

      public PrivateJwkBuilder privateKey(PrivateKey privateKey) {
         Assert.notNull(privateKey, "PrivateKey argument cannot be null.");
         K publicKey = (K)((PublicKey)Assert.notNull(((JwkContext)this.DELEGATE).getKey(), "PublicKey cannot be null."));
         return this.newPrivateBuilder(this.newContext(privateKey)).publicKey(publicKey);
      }

      protected abstract PrivateJwkBuilder newPrivateBuilder(JwkContext var1);
   }

   private abstract static class DefaultPrivateJwkBuilder extends AbstractAsymmetricJwkBuilder implements PrivateJwkBuilder {
      DefaultPrivateJwkBuilder(JwkContext ctx) {
         super(ctx);
      }

      DefaultPrivateJwkBuilder(DefaultPublicJwkBuilder b, JwkContext ctx) {
         super(b, ctx);
         ((JwkContext)this.DELEGATE).setPublicKey((PublicKey)((JwkContext)b.DELEGATE).getKey());
      }

      public PrivateJwkBuilder publicKey(PublicKey publicKey) {
         ((JwkContext)this.DELEGATE).setPublicKey(publicKey);
         return (PrivateJwkBuilder)this.self();
      }
   }

   static class DefaultRsaPublicJwkBuilder extends DefaultPublicJwkBuilder implements RsaPublicJwkBuilder {
      DefaultRsaPublicJwkBuilder(JwkContext ctx) {
         super(ctx);
      }

      protected RsaPrivateJwkBuilder newPrivateBuilder(JwkContext ctx) {
         return new DefaultRsaPrivateJwkBuilder(this, ctx);
      }
   }

   static class DefaultEcPublicJwkBuilder extends DefaultPublicJwkBuilder implements EcPublicJwkBuilder {
      DefaultEcPublicJwkBuilder(JwkContext src) {
         super(src);
      }

      protected EcPrivateJwkBuilder newPrivateBuilder(JwkContext ctx) {
         return new DefaultEcPrivateJwkBuilder(this, ctx);
      }
   }

   static class DefaultOctetPublicJwkBuilder extends DefaultPublicJwkBuilder implements OctetPublicJwkBuilder {
      DefaultOctetPublicJwkBuilder(JwkContext ctx) {
         super(ctx);
         EdwardsCurve.assertEdwards(ctx.getKey());
      }

      protected OctetPrivateJwkBuilder newPrivateBuilder(JwkContext ctx) {
         return new DefaultOctetPrivateJwkBuilder(this, ctx);
      }
   }

   static class DefaultRsaPrivateJwkBuilder extends DefaultPrivateJwkBuilder implements RsaPrivateJwkBuilder {
      DefaultRsaPrivateJwkBuilder(JwkContext src) {
         super(src);
      }

      DefaultRsaPrivateJwkBuilder(DefaultRsaPublicJwkBuilder b, JwkContext ctx) {
         super(b, ctx);
      }
   }

   static class DefaultEcPrivateJwkBuilder extends DefaultPrivateJwkBuilder implements EcPrivateJwkBuilder {
      DefaultEcPrivateJwkBuilder(JwkContext src) {
         super(src);
      }

      DefaultEcPrivateJwkBuilder(DefaultEcPublicJwkBuilder b, JwkContext ctx) {
         super(b, ctx);
      }
   }

   static class DefaultOctetPrivateJwkBuilder extends DefaultPrivateJwkBuilder implements OctetPrivateJwkBuilder {
      DefaultOctetPrivateJwkBuilder(JwkContext src) {
         super(src);
         EdwardsCurve.assertEdwards(src.getKey());
      }

      DefaultOctetPrivateJwkBuilder(DefaultOctetPublicJwkBuilder b, JwkContext ctx) {
         super(b, ctx);
         EdwardsCurve.assertEdwards(ctx.getKey());
         EdwardsCurve.assertEdwards(ctx.getPublicKey());
      }
   }
}
