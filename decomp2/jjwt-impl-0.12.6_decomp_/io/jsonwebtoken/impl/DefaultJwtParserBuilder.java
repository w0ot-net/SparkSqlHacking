package io.jsonwebtoken.impl;

import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.CompressionCodecResolver;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.Jwts.ENC;
import io.jsonwebtoken.Jwts.KEY;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.Jwts.ZIP;
import io.jsonwebtoken.impl.io.DelegateStringDecoder;
import io.jsonwebtoken.impl.lang.DefaultNestedCollection;
import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.impl.lang.Services;
import io.jsonwebtoken.impl.security.ConstantKeyLocator;
import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.NestedCollection;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;

public class DefaultJwtParserBuilder implements JwtParserBuilder {
   private static final int MILLISECONDS_PER_SECOND = 1000;
   static final long MAX_CLOCK_SKEW_MILLIS = 9223372036854775L;
   static final String MAX_CLOCK_SKEW_ILLEGAL_MSG = "Illegal allowedClockSkewMillis value: multiplying this value by 1000 to obtain the number of milliseconds would cause a numeric overflow.";
   private Provider provider;
   private boolean unsecured = false;
   private boolean unsecuredDecompression = false;
   private Locator keyLocator;
   private SigningKeyResolver signingKeyResolver = null;
   private Registry encAlgs = ENC.get();
   private Registry keyAlgs = KEY.get();
   private Registry sigAlgs = SIG.get();
   private Registry zipAlgs = ZIP.get();
   private CompressionCodecResolver compressionCodecResolver;
   private Decoder decoder;
   private Deserializer deserializer;
   private final ClaimsBuilder expectedClaims;
   private Clock clock;
   private Set critical;
   private long allowedClockSkewMillis;
   private Key signatureVerificationKey;
   private Key decryptionKey;

   public DefaultJwtParserBuilder() {
      this.decoder = new DelegateStringDecoder(Decoders.BASE64URL);
      this.expectedClaims = Jwts.claims();
      this.clock = DefaultClock.INSTANCE;
      this.critical = Collections.emptySet();
      this.allowedClockSkewMillis = 0L;
   }

   public JwtParserBuilder unsecured() {
      this.unsecured = true;
      return this;
   }

   public JwtParserBuilder unsecuredDecompression() {
      this.unsecuredDecompression = true;
      return this;
   }

   public JwtParserBuilder provider(Provider provider) {
      this.provider = provider;
      return this;
   }

   public JwtParserBuilder deserializeJsonWith(Deserializer deserializer) {
      return this.json(deserializer);
   }

   public JwtParserBuilder json(Deserializer reader) {
      this.deserializer = (Deserializer)Assert.notNull(reader, "JSON Deserializer cannot be null.");
      return this;
   }

   public JwtParserBuilder base64UrlDecodeWith(Decoder decoder) {
      Assert.notNull(decoder, "decoder cannot be null.");
      return this.b64Url(new DelegateStringDecoder(decoder));
   }

   public JwtParserBuilder b64Url(Decoder decoder) {
      Assert.notNull(decoder, "decoder cannot be null.");
      this.decoder = decoder;
      return this;
   }

   public JwtParserBuilder requireIssuedAt(Date issuedAt) {
      this.expectedClaims.setIssuedAt(issuedAt);
      return this;
   }

   public JwtParserBuilder requireIssuer(String issuer) {
      this.expectedClaims.setIssuer(issuer);
      return this;
   }

   public JwtParserBuilder requireAudience(String audience) {
      ((NestedCollection)this.expectedClaims.audience().add(audience)).and();
      return this;
   }

   public JwtParserBuilder requireSubject(String subject) {
      this.expectedClaims.setSubject(subject);
      return this;
   }

   public JwtParserBuilder requireId(String id) {
      this.expectedClaims.setId(id);
      return this;
   }

   public JwtParserBuilder requireExpiration(Date expiration) {
      this.expectedClaims.setExpiration(expiration);
      return this;
   }

   public JwtParserBuilder requireNotBefore(Date notBefore) {
      this.expectedClaims.setNotBefore(notBefore);
      return this;
   }

   public JwtParserBuilder require(String claimName, Object value) {
      Assert.hasText(claimName, "claim name cannot be null or empty.");
      Assert.notNull(value, "The value cannot be null for claim name: " + claimName);
      this.expectedClaims.add(claimName, value);
      return this;
   }

   public JwtParserBuilder setClock(Clock clock) {
      return this.clock(clock);
   }

   public JwtParserBuilder clock(Clock clock) {
      Assert.notNull(clock, "Clock instance cannot be null.");
      this.clock = clock;
      return this;
   }

   public NestedCollection critical() {
      return new DefaultNestedCollection(this, this.critical) {
         protected void changed() {
            DefaultJwtParserBuilder.this.critical = Collections.asSet(this.getCollection());
         }
      };
   }

   public JwtParserBuilder setAllowedClockSkewSeconds(long seconds) throws IllegalArgumentException {
      return this.clockSkewSeconds(seconds);
   }

   public JwtParserBuilder clockSkewSeconds(long seconds) throws IllegalArgumentException {
      Assert.isTrue(seconds <= 9223372036854775L, "Illegal allowedClockSkewMillis value: multiplying this value by 1000 to obtain the number of milliseconds would cause a numeric overflow.");
      this.allowedClockSkewMillis = Math.max(0L, seconds * 1000L);
      return this;
   }

   public JwtParserBuilder setSigningKey(byte[] key) {
      Assert.notEmpty(key, "signature verification key cannot be null or empty.");
      return this.setSigningKey((Key)Keys.hmacShaKeyFor(key));
   }

   public JwtParserBuilder setSigningKey(String base64EncodedSecretKey) {
      Assert.hasText(base64EncodedSecretKey, "signature verification key cannot be null or empty.");
      byte[] bytes = (byte[])Decoders.BASE64.decode(base64EncodedSecretKey);
      return this.setSigningKey(bytes);
   }

   public JwtParserBuilder setSigningKey(Key key) {
      if (key instanceof SecretKey) {
         return this.verifyWith((SecretKey)key);
      } else if (key instanceof PublicKey) {
         return this.verifyWith((PublicKey)key);
      } else {
         String msg = "JWS verification key must be either a SecretKey (for MAC algorithms) or a PublicKey (for Signature algorithms).";
         throw new InvalidKeyException(msg);
      }
   }

   public JwtParserBuilder verifyWith(SecretKey key) {
      return this.verifyWith((Key)key);
   }

   public JwtParserBuilder verifyWith(PublicKey key) {
      return this.verifyWith((Key)key);
   }

   private JwtParserBuilder verifyWith(Key key) {
      if (key instanceof PrivateKey) {
         throw new IllegalArgumentException("PrivateKeys may not be used to verify digital signatures. PrivateKeys are used to sign, and PublicKeys are used to verify.");
      } else {
         this.signatureVerificationKey = (Key)Assert.notNull(key, "signature verification key cannot be null.");
         return this;
      }
   }

   public JwtParserBuilder decryptWith(SecretKey key) {
      return this.decryptWith((Key)key);
   }

   public JwtParserBuilder decryptWith(PrivateKey key) {
      return this.decryptWith((Key)key);
   }

   private JwtParserBuilder decryptWith(Key key) {
      if (key instanceof PublicKey) {
         throw new IllegalArgumentException("PublicKeys may not be used to decrypt data. PublicKeys are used to encrypt, and PrivateKeys are used to decrypt.");
      } else {
         this.decryptionKey = (Key)Assert.notNull(key, "decryption key cannot be null.");
         return this;
      }
   }

   public NestedCollection zip() {
      return new DefaultNestedCollection(this, this.zipAlgs.values()) {
         protected void changed() {
            DefaultJwtParserBuilder.this.zipAlgs = new IdRegistry("Compression Algorithm", this.getCollection());
         }
      };
   }

   public NestedCollection enc() {
      return new DefaultNestedCollection(this, this.encAlgs.values()) {
         public void changed() {
            DefaultJwtParserBuilder.this.encAlgs = new IdRegistry("JWE Encryption Algorithm", this.getCollection());
         }
      };
   }

   public NestedCollection sig() {
      return new DefaultNestedCollection(this, this.sigAlgs.values()) {
         public void changed() {
            DefaultJwtParserBuilder.this.sigAlgs = new IdRegistry("JWS Digital Signature or MAC", this.getCollection());
         }
      };
   }

   public NestedCollection key() {
      return new DefaultNestedCollection(this, this.keyAlgs.values()) {
         public void changed() {
            DefaultJwtParserBuilder.this.keyAlgs = new IdRegistry("JWE Key Management Algorithm", this.getCollection());
         }
      };
   }

   public JwtParserBuilder setSigningKeyResolver(SigningKeyResolver signingKeyResolver) {
      Assert.notNull(signingKeyResolver, "SigningKeyResolver cannot be null.");
      this.signingKeyResolver = signingKeyResolver;
      return this;
   }

   public JwtParserBuilder keyLocator(Locator keyLocator) {
      this.keyLocator = (Locator)Assert.notNull(keyLocator, "Key locator cannot be null.");
      return this;
   }

   public JwtParserBuilder setCompressionCodecResolver(CompressionCodecResolver resolver) {
      this.compressionCodecResolver = (CompressionCodecResolver)Assert.notNull(resolver, "CompressionCodecResolver cannot be null.");
      return this;
   }

   public JwtParser build() {
      if (this.deserializer == null) {
         this.json((Deserializer)Services.get(Deserializer.class));
      }

      if (this.signingKeyResolver != null && this.signatureVerificationKey != null) {
         String msg = "Both a 'signingKeyResolver and a 'verifyWith' key cannot be configured. Choose either, or prefer `keyLocator` when possible.";
         throw new IllegalStateException(msg);
      } else {
         if (this.keyLocator != null) {
            if (this.signatureVerificationKey != null) {
               String msg = "Both 'keyLocator' and a 'verifyWith' key cannot be configured. Prefer 'keyLocator' if possible.";
               throw new IllegalStateException(msg);
            }

            if (this.decryptionKey != null) {
               String msg = "Both 'keyLocator' and a 'decryptWith' key cannot be configured. Prefer 'keyLocator' if possible.";
               throw new IllegalStateException(msg);
            }
         }

         Locator<? extends Key> keyLocator = this.keyLocator;
         if (keyLocator == null) {
            keyLocator = new ConstantKeyLocator(this.signatureVerificationKey, this.decryptionKey);
         }

         if (!this.unsecured && this.unsecuredDecompression) {
            String msg = "'unsecuredDecompression' is only relevant if 'unsecured' is also configured. Please read the JavaDoc of both features before enabling either due to their security implications.";
            throw new IllegalStateException(msg);
         } else if (this.compressionCodecResolver != null && !ZIP.get().equals(this.zipAlgs)) {
            String msg = "Both 'zip()' and 'compressionCodecResolver' cannot be configured. Choose either.";
            throw new IllegalStateException(msg);
         } else {
            Assert.stateNotNull(keyLocator, "Key locator should never be null.");
            DefaultClaims expClaims = (DefaultClaims)this.expectedClaims.build();
            return new DefaultJwtParser(this.provider, this.signingKeyResolver, this.unsecured, this.unsecuredDecompression, keyLocator, this.clock, this.critical, this.allowedClockSkewMillis, expClaims, this.decoder, this.deserializer, this.compressionCodecResolver, this.zipAlgs, this.sigAlgs, this.keyAlgs, this.encAlgs);
         }
      }
   }
}
