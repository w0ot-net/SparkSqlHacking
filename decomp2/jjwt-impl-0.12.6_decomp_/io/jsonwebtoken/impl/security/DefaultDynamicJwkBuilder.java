package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.DynamicJwkBuilder;
import io.jsonwebtoken.security.EcPrivateJwkBuilder;
import io.jsonwebtoken.security.EcPublicJwkBuilder;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.OctetPrivateJwkBuilder;
import io.jsonwebtoken.security.OctetPublicJwkBuilder;
import io.jsonwebtoken.security.PrivateJwkBuilder;
import io.jsonwebtoken.security.PublicJwkBuilder;
import io.jsonwebtoken.security.RsaPrivateJwkBuilder;
import io.jsonwebtoken.security.RsaPublicJwkBuilder;
import io.jsonwebtoken.security.SecretJwkBuilder;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import javax.crypto.SecretKey;

public class DefaultDynamicJwkBuilder extends AbstractJwkBuilder implements DynamicJwkBuilder {
   public DefaultDynamicJwkBuilder() {
      this(new DefaultJwkContext());
   }

   public DefaultDynamicJwkBuilder(JwkContext ctx) {
      super(ctx);
   }

   public SecretJwkBuilder key(SecretKey key) {
      return new AbstractJwkBuilder.DefaultSecretJwkBuilder(this.newContext(key));
   }

   public RsaPublicJwkBuilder key(RSAPublicKey key) {
      return new AbstractAsymmetricJwkBuilder.DefaultRsaPublicJwkBuilder(this.newContext(key));
   }

   public RsaPrivateJwkBuilder key(RSAPrivateKey key) {
      return new AbstractAsymmetricJwkBuilder.DefaultRsaPrivateJwkBuilder(this.newContext(key));
   }

   public EcPublicJwkBuilder key(ECPublicKey key) {
      return new AbstractAsymmetricJwkBuilder.DefaultEcPublicJwkBuilder(this.newContext(key));
   }

   public EcPrivateJwkBuilder key(ECPrivateKey key) {
      return new AbstractAsymmetricJwkBuilder.DefaultEcPrivateJwkBuilder(this.newContext(key));
   }

   private static UnsupportedKeyException unsupportedKey(Key key, Exception e) {
      String msg = "There is no builder that supports specified key [" + KeysBridge.toString(key) + "].";
      return new UnsupportedKeyException(msg, e);
   }

   public PublicJwkBuilder key(PublicKey key) {
      if (key instanceof RSAPublicKey) {
         return this.key((RSAPublicKey)key);
      } else if (key instanceof ECPublicKey) {
         return this.key((ECPublicKey)key);
      } else {
         try {
            return this.octetKey(key);
         } catch (Exception e) {
            throw unsupportedKey(key, e);
         }
      }
   }

   public PrivateJwkBuilder key(PrivateKey key) {
      Assert.notNull(key, "Key cannot be null.");
      if (key instanceof RSAPrivateKey) {
         return this.key((RSAPrivateKey)key);
      } else if (key instanceof ECPrivateKey) {
         return this.key((ECPrivateKey)key);
      } else {
         try {
            return this.octetKey(key);
         } catch (Exception e) {
            throw unsupportedKey(key, e);
         }
      }
   }

   public OctetPublicJwkBuilder octetKey(PublicKey key) {
      return new AbstractAsymmetricJwkBuilder.DefaultOctetPublicJwkBuilder(this.newContext(key));
   }

   public OctetPrivateJwkBuilder octetKey(PrivateKey key) {
      return new AbstractAsymmetricJwkBuilder.DefaultOctetPrivateJwkBuilder(this.newContext(key));
   }

   public PublicJwkBuilder chain(List chain) throws UnsupportedKeyException {
      Assert.notEmpty(chain, "chain cannot be null or empty.");
      X509Certificate cert = (X509Certificate)Assert.notNull(chain.get(0), "The first X509Certificate cannot be null.");
      PublicKey key = (PublicKey)Assert.notNull(cert.getPublicKey(), "The first X509Certificate's PublicKey cannot be null.");
      return (PublicJwkBuilder)this.key(key).x509Chain(chain);
   }

   public RsaPublicJwkBuilder rsaChain(List chain) {
      Assert.notEmpty(chain, "X509Certificate chain cannot be empty.");
      X509Certificate cert = (X509Certificate)chain.get(0);
      PublicKey key = cert.getPublicKey();
      RSAPublicKey pubKey = (RSAPublicKey)KeyPairs.assertKey(key, RSAPublicKey.class, "The first X509Certificate's ");
      return (RsaPublicJwkBuilder)this.key(pubKey).x509Chain(chain);
   }

   public EcPublicJwkBuilder ecChain(List chain) {
      Assert.notEmpty(chain, "X509Certificate chain cannot be empty.");
      X509Certificate cert = (X509Certificate)chain.get(0);
      PublicKey key = cert.getPublicKey();
      ECPublicKey pubKey = (ECPublicKey)KeyPairs.assertKey(key, ECPublicKey.class, "The first X509Certificate's ");
      return (EcPublicJwkBuilder)this.key(pubKey).x509Chain(chain);
   }

   public OctetPrivateJwkBuilder octetKeyPair(KeyPair pair) {
      PublicKey pub = (PublicKey)KeyPairs.getKey(pair, PublicKey.class);
      PrivateKey priv = (PrivateKey)KeyPairs.getKey(pair, PrivateKey.class);
      EdwardsCurve.assertEdwards(pub);
      EdwardsCurve.assertEdwards(priv);
      return (OctetPrivateJwkBuilder)this.octetKey(priv).publicKey(pub);
   }

   public OctetPublicJwkBuilder octetChain(List chain) {
      Assert.notEmpty(chain, "X509Certificate chain cannot be empty.");
      X509Certificate cert = (X509Certificate)chain.get(0);
      PublicKey key = cert.getPublicKey();
      Assert.notNull(key, "The first X509Certificate's PublicKey cannot be null.");
      EdwardsCurve.assertEdwards(key);
      return (OctetPublicJwkBuilder)this.octetKey(key).x509Chain(chain);
   }

   public RsaPrivateJwkBuilder rsaKeyPair(KeyPair pair) {
      RSAPublicKey pub = (RSAPublicKey)KeyPairs.getKey(pair, RSAPublicKey.class);
      RSAPrivateKey priv = (RSAPrivateKey)KeyPairs.getKey(pair, RSAPrivateKey.class);
      return (RsaPrivateJwkBuilder)this.key(priv).publicKey(pub);
   }

   public EcPrivateJwkBuilder ecKeyPair(KeyPair pair) {
      ECPublicKey pub = (ECPublicKey)KeyPairs.getKey(pair, ECPublicKey.class);
      ECPrivateKey priv = (ECPrivateKey)KeyPairs.getKey(pair, ECPrivateKey.class);
      return (EcPrivateJwkBuilder)this.key(priv).publicKey(pub);
   }

   public PrivateJwkBuilder keyPair(KeyPair keyPair) throws UnsupportedKeyException {
      A pub = (A)((PublicKey)KeyPairs.getKey(keyPair, PublicKey.class));
      B priv = (B)((PrivateKey)KeyPairs.getKey(keyPair, PrivateKey.class));
      return this.key(priv).publicKey(pub);
   }

   public Jwk build() {
      if (Strings.hasText((String)((JwkContext)this.DELEGATE).get(AbstractJwk.KTY))) {
         this.setDelegate(this.jwkFactory.newContext((JwkContext)this.DELEGATE, ((JwkContext)this.DELEGATE).getKey()));
      }

      return super.build();
   }
}
