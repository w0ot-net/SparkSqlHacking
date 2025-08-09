package io.jsonwebtoken.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts.KEY;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.io.Base64UrlStreamEncoder;
import io.jsonwebtoken.impl.io.ByteBase64UrlStreamEncoder;
import io.jsonwebtoken.impl.io.CountingInputStream;
import io.jsonwebtoken.impl.io.EncodingOutputStream;
import io.jsonwebtoken.impl.io.NamedSerializer;
import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.io.UncloseableInputStream;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.impl.lang.Functions;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Services;
import io.jsonwebtoken.impl.security.DefaultAeadRequest;
import io.jsonwebtoken.impl.security.DefaultAeadResult;
import io.jsonwebtoken.impl.security.DefaultKeyRequest;
import io.jsonwebtoken.impl.security.DefaultSecureRequest;
import io.jsonwebtoken.impl.security.Pbes2HsAkwAlgorithm;
import io.jsonwebtoken.impl.security.ProviderKey;
import io.jsonwebtoken.impl.security.StandardSecureDigestAlgorithms;
import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.NestedCollection;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.AeadRequest;
import io.jsonwebtoken.security.AeadResult;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.KeyResult;
import io.jsonwebtoken.security.Password;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DefaultJwtBuilder implements JwtBuilder {
   private static final String PUB_KEY_SIGN_MSG = "PublicKeys may not be used to create digital signatures. PrivateKeys are used to sign, and PublicKeys are used to verify.";
   private static final String PRIV_KEY_ENC_MSG = "PrivateKeys may not be used to encrypt data. PublicKeys are used to encrypt, and PrivateKeys are used to decrypt.";
   protected Provider provider;
   protected SecureRandom secureRandom;
   private final DefaultBuilderHeader headerBuilder;
   private final DefaultBuilderClaims claimsBuilder;
   private Payload payload;
   private SecureDigestAlgorithm sigAlg;
   private Function signFunction;
   private AeadAlgorithm enc;
   private KeyAlgorithm keyAlg;
   private Function keyAlgFunction;
   private Key key;
   private Serializer serializer;
   protected Encoder encoder;
   private boolean encodePayload;
   protected CompressionAlgorithm compressionAlgorithm;

   public DefaultJwtBuilder() {
      this.payload = Payload.EMPTY;
      this.sigAlg = SIG.NONE;
      this.encoder = Base64UrlStreamEncoder.INSTANCE;
      this.encodePayload = true;
      this.headerBuilder = new DefaultBuilderHeader(this);
      this.claimsBuilder = new DefaultBuilderClaims(this);
   }

   public JwtBuilder.BuilderHeader header() {
      return this.headerBuilder;
   }

   public JwtBuilder.BuilderClaims claims() {
      return this.claimsBuilder;
   }

   public JwtBuilder provider(Provider provider) {
      this.provider = provider;
      return this;
   }

   public JwtBuilder random(SecureRandom secureRandom) {
      this.secureRandom = secureRandom;
      return this;
   }

   public JwtBuilder serializeToJsonWith(Serializer serializer) {
      return this.json(serializer);
   }

   public JwtBuilder json(Serializer serializer) {
      this.serializer = (Serializer)Assert.notNull(serializer, "JSON Serializer cannot be null.");
      return this;
   }

   public JwtBuilder base64UrlEncodeWith(Encoder encoder) {
      return this.b64Url(new ByteBase64UrlStreamEncoder(encoder));
   }

   public JwtBuilder b64Url(Encoder encoder) {
      Assert.notNull(encoder, "encoder cannot be null.");
      this.encoder = encoder;
      return this;
   }

   public JwtBuilder encodePayload(boolean b64) {
      this.encodePayload = b64;
      String critParamId = DefaultProtectedHeader.CRIT.getId();
      String b64Id = DefaultJwsHeader.B64.getId();
      Set<String> crit = (Set)this.headerBuilder.get(DefaultProtectedHeader.CRIT);
      crit = new LinkedHashSet(Collections.nullSafe(crit));
      crit.remove(b64Id);
      return (JwtBuilder)((JwtBuilder.BuilderHeader)((JwtBuilder.BuilderHeader)this.header().delete(b64Id)).add(critParamId, crit)).and();
   }

   public JwtBuilder setHeader(Map map) {
      return (JwtBuilder)((JwtBuilder.BuilderHeader)((JwtBuilder.BuilderHeader)this.header().empty()).add(map)).and();
   }

   public JwtBuilder setHeaderParams(Map params) {
      return (JwtBuilder)((JwtBuilder.BuilderHeader)this.header().add(params)).and();
   }

   public JwtBuilder setHeaderParam(String name, Object value) {
      return (JwtBuilder)((JwtBuilder.BuilderHeader)this.header().add(name, value)).and();
   }

   protected static SecureDigestAlgorithm forSigningKey(Key key) {
      Assert.notNull(key, "Key cannot be null.");
      SecureDigestAlgorithm<K, ?> alg = StandardSecureDigestAlgorithms.findBySigningKey(key);
      if (alg == null) {
         String msg = "Unable to determine a suitable MAC or Signature algorithm for the specified key using available heuristics: either the key size is too weak be used with available algorithms, or the key size is unavailable (e.g. if using a PKCS11 or HSM (Hardware Security Module) key store). If you are using a PKCS11 or HSM keystore, consider using the JwtBuilder.signWith(Key, SecureDigestAlgorithm) method instead.";
         throw new UnsupportedKeyException(msg);
      } else {
         return alg;
      }
   }

   public JwtBuilder signWith(Key key) throws InvalidKeyException {
      Assert.notNull(key, "Key argument cannot be null.");
      SecureDigestAlgorithm<Key, ?> alg = forSigningKey(key);
      return this.signWith(key, alg);
   }

   public JwtBuilder signWith(Key key, SecureDigestAlgorithm alg) throws InvalidKeyException {
      Assert.notNull(key, "Key argument cannot be null.");
      if (key instanceof PublicKey) {
         throw new IllegalArgumentException("PublicKeys may not be used to create digital signatures. PrivateKeys are used to sign, and PublicKeys are used to verify.");
      } else {
         Assert.notNull(alg, "SignatureAlgorithm cannot be null.");
         String id = (String)Assert.hasText(alg.getId(), "SignatureAlgorithm id cannot be null or empty.");
         if (SIG.NONE.getId().equalsIgnoreCase(id)) {
            String msg = "The 'none' JWS algorithm cannot be used to sign JWTs.";
            throw new IllegalArgumentException(msg);
         } else {
            this.key = key;
            this.sigAlg = alg;
            this.signFunction = Functions.wrap(new Function() {
               public byte[] apply(SecureRequest request) {
                  return DefaultJwtBuilder.this.sigAlg.digest(request);
               }
            }, SignatureException.class, "Unable to compute %s signature.", id);
            return this;
         }
      }
   }

   public JwtBuilder signWith(Key key, SignatureAlgorithm alg) throws InvalidKeyException {
      Assert.notNull(alg, "SignatureAlgorithm cannot be null.");
      alg.assertValidSigningKey(key);
      return this.signWith(key, (SecureDigestAlgorithm)SIG.get().forKey(alg.getValue()));
   }

   public JwtBuilder signWith(SignatureAlgorithm alg, byte[] secretKeyBytes) throws InvalidKeyException {
      Assert.notNull(alg, "SignatureAlgorithm cannot be null.");
      Assert.notEmpty(secretKeyBytes, "secret key byte array cannot be null or empty.");
      Assert.isTrue(alg.isHmac(), "Key bytes may only be specified for HMAC signatures.  If using RSA or Elliptic Curve, use the signWith(SignatureAlgorithm, Key) method instead.");
      SecretKey key = new SecretKeySpec(secretKeyBytes, alg.getJcaName());
      return this.signWith((Key)key, (SignatureAlgorithm)alg);
   }

   public JwtBuilder signWith(SignatureAlgorithm alg, String base64EncodedSecretKey) throws InvalidKeyException {
      Assert.hasText(base64EncodedSecretKey, "base64-encoded secret key cannot be null or empty.");
      Assert.isTrue(alg.isHmac(), "Base64-encoded key bytes may only be specified for HMAC signatures.  If using RSA or Elliptic Curve, use the signWith(SignatureAlgorithm, Key) method instead.");
      byte[] bytes = (byte[])Decoders.BASE64.decode(base64EncodedSecretKey);
      return this.signWith(alg, bytes);
   }

   public JwtBuilder signWith(SignatureAlgorithm alg, Key key) {
      return this.signWith(key, alg);
   }

   public JwtBuilder encryptWith(SecretKey key, AeadAlgorithm enc) {
      return key instanceof Password ? this.encryptWith((Password)key, new Pbes2HsAkwAlgorithm(enc.getKeyBitLength()), enc) : this.encryptWith(key, KEY.DIRECT, enc);
   }

   public JwtBuilder encryptWith(Key key, KeyAlgorithm keyAlg, AeadAlgorithm enc) {
      this.enc = (AeadAlgorithm)Assert.notNull(enc, "Encryption algorithm cannot be null.");
      Assert.hasText(enc.getId(), "Encryption algorithm id cannot be null or empty.");
      Assert.notNull(key, "Encryption key cannot be null.");
      if (key instanceof PrivateKey) {
         throw new IllegalArgumentException("PrivateKeys may not be used to encrypt data. PublicKeys are used to encrypt, and PrivateKeys are used to decrypt.");
      } else {
         Assert.notNull(keyAlg, "KeyAlgorithm cannot be null.");
         String algId = (String)Assert.hasText(keyAlg.getId(), "KeyAlgorithm id cannot be null or empty.");
         this.key = key;
         this.keyAlg = keyAlg;
         final KeyAlgorithm<Key, ?> alg = this.keyAlg;
         String cekMsg = "Unable to obtain content encryption key from key management algorithm '%s'.";
         this.keyAlgFunction = Functions.wrap(new Function() {
            public KeyResult apply(KeyRequest request) {
               return alg.getEncryptionKey(request);
            }
         }, SecurityException.class, "Unable to obtain content encryption key from key management algorithm '%s'.", algId);
         return this;
      }
   }

   public JwtBuilder compressWith(CompressionAlgorithm alg) {
      Assert.notNull(alg, "CompressionAlgorithm cannot be null");
      Assert.hasText(alg.getId(), "CompressionAlgorithm id cannot be null or empty.");
      this.compressionAlgorithm = alg;
      return (JwtBuilder)((JwtBuilder.BuilderHeader)this.header().delete(DefaultHeader.COMPRESSION_ALGORITHM.getId())).and();
   }

   public JwtBuilder setPayload(String payload) {
      return this.content(payload);
   }

   public JwtBuilder content(String content) {
      if (Strings.hasText(content)) {
         this.payload = new Payload(content, (String)null);
      }

      return this;
   }

   public JwtBuilder content(byte[] content) {
      if (!Bytes.isEmpty(content)) {
         this.payload = new Payload(content, (String)null);
      }

      return this;
   }

   public JwtBuilder content(InputStream in) {
      if (in != null) {
         this.payload = new Payload(in, (String)null);
      }

      return this;
   }

   public JwtBuilder content(byte[] content, String cty) {
      Assert.notEmpty(content, "content byte array cannot be null or empty.");
      Assert.hasText(cty, "Content Type String cannot be null or empty.");
      this.payload = new Payload(content, cty);
      return (JwtBuilder)((JwtBuilder.BuilderHeader)this.header().delete(DefaultHeader.CONTENT_TYPE.getId())).and();
   }

   public JwtBuilder content(String content, String cty) throws IllegalArgumentException {
      Assert.hasText(content, "Content string cannot be null or empty.");
      Assert.hasText(cty, "ContentType string cannot be null or empty.");
      this.payload = new Payload(content, cty);
      return (JwtBuilder)((JwtBuilder.BuilderHeader)this.header().delete(DefaultHeader.CONTENT_TYPE.getId())).and();
   }

   public JwtBuilder content(InputStream in, String cty) throws IllegalArgumentException {
      Assert.notNull(in, "Payload InputStream cannot be null.");
      Assert.hasText(cty, "ContentType string cannot be null or empty.");
      this.payload = new Payload(in, cty);
      return (JwtBuilder)((JwtBuilder.BuilderHeader)this.header().delete(DefaultHeader.CONTENT_TYPE.getId())).and();
   }

   public JwtBuilder setClaims(Map claims) {
      Assert.notNull(claims, "Claims map cannot be null.");
      return (JwtBuilder)((JwtBuilder.BuilderClaims)((JwtBuilder.BuilderClaims)this.claims().empty()).add(claims)).and();
   }

   public JwtBuilder addClaims(Map claims) {
      return this.claims(claims);
   }

   public JwtBuilder claims(Map claims) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().add(claims)).and();
   }

   public JwtBuilder claim(String name, Object value) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().add(name, value)).and();
   }

   public JwtBuilder setIssuer(String iss) {
      return this.issuer(iss);
   }

   public JwtBuilder issuer(String iss) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().issuer(iss)).and();
   }

   public JwtBuilder setSubject(String sub) {
      return this.subject(sub);
   }

   public JwtBuilder subject(String sub) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().subject(sub)).and();
   }

   public JwtBuilder setAudience(String aud) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().setAudience(aud)).and();
   }

   public ClaimsMutator.AudienceCollection audience() {
      return new DelegateAudienceCollection(this, this.claims().audience());
   }

   public JwtBuilder setExpiration(Date exp) {
      return this.expiration(exp);
   }

   public JwtBuilder expiration(Date exp) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().expiration(exp)).and();
   }

   public JwtBuilder setNotBefore(Date nbf) {
      return this.notBefore(nbf);
   }

   public JwtBuilder notBefore(Date nbf) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().notBefore(nbf)).and();
   }

   public JwtBuilder setIssuedAt(Date iat) {
      return this.issuedAt(iat);
   }

   public JwtBuilder issuedAt(Date iat) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().issuedAt(iat)).and();
   }

   public JwtBuilder setId(String jti) {
      return this.id(jti);
   }

   public JwtBuilder id(String jti) {
      return (JwtBuilder)((JwtBuilder.BuilderClaims)this.claims().id(jti)).and();
   }

   private void assertPayloadEncoding(String type) {
      if (!this.encodePayload) {
         String msg = "Payload encoding may not be disabled for " + type + "s, only JWSs.";
         throw new IllegalArgumentException(msg);
      }
   }

   public String compact() {
      boolean jwe = this.enc != null;
      if (jwe && this.signFunction != null) {
         String msg = "Both 'signWith' and 'encryptWith' cannot be specified. Choose either one.";
         throw new IllegalStateException(msg);
      } else {
         Payload payload = (Payload)Assert.stateNotNull(this.payload, "Payload instance null, internal error");
         Claims claims = this.claimsBuilder.build();
         if (jwe && payload.isEmpty() && Collections.isEmpty(claims)) {
            String msg = "Encrypted JWTs must have either 'claims' or non-empty 'content'.";
            throw new IllegalStateException(msg);
         } else if (!payload.isEmpty() && !Collections.isEmpty(claims)) {
            throw new IllegalStateException("Both 'content' and 'claims' cannot be specified. Choose either one.");
         } else {
            if (this.serializer == null) {
               this.json((Serializer)Services.get(Serializer.class));
            }

            if (!Collections.isEmpty(claims)) {
               payload = new Payload(claims);
            }

            if (this.compressionAlgorithm != null && !payload.isEmpty()) {
               payload.setZip(this.compressionAlgorithm);
               this.headerBuilder.put(DefaultHeader.COMPRESSION_ALGORITHM.getId(), this.compressionAlgorithm.getId());
            }

            if (Strings.hasText(payload.getContentType())) {
               this.headerBuilder.contentType(payload.getContentType());
            }

            Provider keyProvider = ProviderKey.getProvider(this.key, this.provider);
            Key key = ProviderKey.getKey(this.key);
            if (jwe) {
               return this.encrypt(payload, key, keyProvider);
            } else {
               return key != null ? this.sign(payload, key, keyProvider) : this.unprotected(payload);
            }
         }
      }
   }

   private void writeAndClose(String name, Map map, OutputStream out) {
      try {
         Serializer<Map<String, ?>> named = new NamedSerializer(name, this.serializer);
         named.serialize(map, out);
      } finally {
         Objects.nullSafeClose(new Closeable[]{out});
      }

   }

   private void writeAndClose(String name, Payload payload, OutputStream out) {
      out = payload.compress(out);
      if (payload.isClaims()) {
         this.writeAndClose(name, (Map)payload.getRequiredClaims(), out);
      } else {
         try {
            InputStream in = payload.toInputStream();
            Streams.copy(in, out, new byte[4096], "Unable to copy payload.");
         } finally {
            Objects.nullSafeClose(new Closeable[]{out});
         }
      }

   }

   private String sign(Payload payload, Key key, Provider provider) {
      Assert.stateNotNull(key, "Key is required.");
      Assert.stateNotNull(this.sigAlg, "SignatureAlgorithm is required.");
      Assert.stateNotNull(this.signFunction, "Signature Algorithm function cannot be null.");
      Assert.stateNotNull(payload, "Payload argument cannot be null.");
      ByteArrayOutputStream jws = new ByteArrayOutputStream(4096);
      this.headerBuilder.add(DefaultHeader.ALGORITHM.getId(), this.sigAlg.getId());
      if (!this.encodePayload) {
         String id = DefaultJwsHeader.B64.getId();
         ((JwtBuilder.BuilderHeader)((NestedCollection)this.headerBuilder.critical().add(id)).and()).add(id, false);
      }

      JwsHeader header = (JwsHeader)Assert.isInstanceOf(JwsHeader.class, this.headerBuilder.build());
      this.encodeAndWrite("JWS Protected Header", (Map)header, jws);
      jws.write(46);
      InputStream payloadStream = null;
      InputStream signingInput;
      if (this.encodePayload) {
         this.encodeAndWrite("JWS Payload", (Payload)payload, jws);
         signingInput = Streams.of(jws.toByteArray());
      } else {
         InputStream prefixStream = Streams.of(jws.toByteArray());
         payloadStream = this.toInputStream("JWS Unencoded Payload", payload);
         if (!payload.isClaims()) {
            payloadStream = new CountingInputStream(payloadStream);
         }

         if (payloadStream.markSupported()) {
            payloadStream.mark(0);
         }

         signingInput = new SequenceInputStream(prefixStream, new UncloseableInputStream(payloadStream));
      }

      byte[] signature;
      try {
         SecureRequest<InputStream, Key> request = new DefaultSecureRequest(signingInput, provider, this.secureRandom, key);
         signature = (byte[])this.signFunction.apply(request);
         if (!this.encodePayload) {
            if (!payload.isCompressed() && (payload.isClaims() || payload.isString())) {
               Streams.copy(payloadStream, jws, new byte[8192], "Unable to copy attached Payload InputStream.");
            }

            if (payloadStream instanceof CountingInputStream && ((CountingInputStream)payloadStream).getCount() <= 0L) {
               String msg = "'b64' Unencoded payload option has been specified, but payload is empty.";
               throw new IllegalStateException(msg);
            }
         }
      } finally {
         Streams.reset(payloadStream);
      }

      jws.write(46);
      this.encodeAndWrite("JWS Signature", (byte[])signature, jws);
      return Strings.utf8(jws.toByteArray());
   }

   private String unprotected(Payload content) {
      Assert.stateNotNull(content, "Content argument cannot be null.");
      this.assertPayloadEncoding("unprotected JWT");
      this.headerBuilder.add(DefaultHeader.ALGORITHM.getId(), SIG.NONE.getId());
      ByteArrayOutputStream jwt = new ByteArrayOutputStream(512);
      Header header = this.headerBuilder.build();
      this.encodeAndWrite("JWT Header", (Map)header, jwt);
      jwt.write(46);
      this.encodeAndWrite("JWT Payload", (Payload)content, jwt);
      jwt.write(46);
      return Strings.ascii(jwt.toByteArray());
   }

   private void encrypt(final AeadRequest req, final AeadResult res) throws SecurityException {
      Function<Object, Object> fn = Functions.wrap(new Function() {
         public Object apply(Object o) {
            DefaultJwtBuilder.this.enc.encrypt(req, res);
            return null;
         }
      }, SecurityException.class, "%s encryption failed.", this.enc.getId());
      fn.apply((Object)null);
   }

   private String encrypt(Payload content, Key key, Provider keyProvider) {
      Assert.stateNotNull(content, "Payload argument cannot be null.");
      Assert.stateNotNull(key, "Key is required.");
      Assert.stateNotNull(this.enc, "Encryption algorithm is required.");
      Assert.stateNotNull(this.keyAlg, "KeyAlgorithm is required.");
      Assert.stateNotNull(this.keyAlgFunction, "KeyAlgorithm function cannot be null.");
      this.assertPayloadEncoding("JWE");
      InputStream plaintext = this.toInputStream("JWE Payload", content);
      JweHeader delegate = new DefaultMutableJweHeader(this.headerBuilder);
      KeyRequest<Key> keyRequest = new DefaultKeyRequest(key, keyProvider, this.secureRandom, delegate, this.enc);
      KeyResult keyResult = (KeyResult)this.keyAlgFunction.apply(keyRequest);
      Assert.stateNotNull(keyResult, "KeyAlgorithm must return a KeyResult.");
      SecretKey cek = (SecretKey)Assert.notNull(keyResult.getKey(), "KeyResult must return a content encryption key.");
      byte[] encryptedCek = (byte[])Assert.notNull(keyResult.getPayload(), "KeyResult must return an encrypted key byte array, even if empty.");
      this.headerBuilder.add(DefaultHeader.ALGORITHM.getId(), this.keyAlg.getId());
      this.headerBuilder.put(DefaultJweHeader.ENCRYPTION_ALGORITHM.getId(), this.enc.getId());
      JweHeader header = (JweHeader)Assert.isInstanceOf(JweHeader.class, this.headerBuilder.build(), "Invalid header created: ");
      ByteArrayOutputStream jwe = new ByteArrayOutputStream(8192);
      this.encodeAndWrite("JWE Protected Header", (Map)header, jwe);
      InputStream aad = Streams.of(jwe.toByteArray());
      ByteArrayOutputStream ciphertextOut = new ByteArrayOutputStream(8192);
      AeadRequest req = new DefaultAeadRequest(plaintext, (Provider)null, this.secureRandom, cek, aad);
      DefaultAeadResult res = new DefaultAeadResult(ciphertextOut);
      this.encrypt(req, res);
      byte[] iv = Assert.notEmpty(res.getIv(), "Encryption result must have a non-empty initialization vector.");
      byte[] tag = Assert.notEmpty(res.getDigest(), "Encryption result must have a non-empty authentication tag.");
      byte[] ciphertext = Assert.notEmpty(ciphertextOut.toByteArray(), "Encryption result must have non-empty ciphertext.");
      jwe.write(46);
      this.encodeAndWrite("JWE Encrypted CEK", (byte[])encryptedCek, jwe);
      jwe.write(46);
      this.encodeAndWrite("JWE Initialization Vector", (byte[])iv, jwe);
      jwe.write(46);
      this.encodeAndWrite("JWE Ciphertext", (byte[])ciphertext, jwe);
      jwe.write(46);
      this.encodeAndWrite("JWE AAD Tag", (byte[])tag, jwe);
      return Strings.utf8(jwe.toByteArray());
   }

   private OutputStream encode(OutputStream out, String name) {
      out = (OutputStream)this.encoder.encode(out);
      return new EncodingOutputStream(out, "base64url", name);
   }

   private void encodeAndWrite(String name, Map map, OutputStream out) {
      out = this.encode(out, name);
      this.writeAndClose(name, map, out);
   }

   private void encodeAndWrite(String name, Payload payload, OutputStream out) {
      out = this.encode(out, name);
      this.writeAndClose(name, payload, out);
   }

   private void encodeAndWrite(String name, byte[] data, OutputStream out) {
      out = this.encode(out, name);
      Streams.writeAndClose(out, data, "Unable to write bytes");
   }

   private InputStream toInputStream(String name, Payload payload) {
      if (!payload.isClaims() && !payload.isCompressed()) {
         return (InputStream)Assert.stateNotNull(payload.toInputStream(), "Payload InputStream cannot be null.");
      } else {
         ByteArrayOutputStream claimsOut = new ByteArrayOutputStream(8192);
         this.writeAndClose(name, (Payload)payload, claimsOut);
         return Streams.of(claimsOut.toByteArray());
      }
   }

   private static class DefaultBuilderClaims extends DelegatingClaimsMutator implements JwtBuilder.BuilderClaims {
      private final JwtBuilder builder;

      private DefaultBuilderClaims(JwtBuilder builder) {
         this.builder = builder;
      }

      public JwtBuilder and() {
         return this.builder;
      }

      private Claims build() {
         return new DefaultClaims((ParameterMap)this.DELEGATE);
      }
   }

   private static class DefaultBuilderHeader extends DefaultJweHeaderBuilder implements JwtBuilder.BuilderHeader {
      private final JwtBuilder builder;

      private DefaultBuilderHeader(JwtBuilder builder) {
         this.builder = (JwtBuilder)Assert.notNull(builder, "JwtBuilder cannot be null.");
      }

      public JwtBuilder and() {
         return this.builder;
      }

      private Object get(Parameter param) {
         return ((ParameterMap)this.DELEGATE).get(param);
      }

      private Header build() {
         return (new DefaultJwtHeaderBuilder(this)).build();
      }
   }
}
