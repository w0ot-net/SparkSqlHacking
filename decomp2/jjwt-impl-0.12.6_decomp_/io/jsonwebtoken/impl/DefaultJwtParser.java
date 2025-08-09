package io.jsonwebtoken.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.CompressionCodecResolver;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtHandler;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.ProtectedHeader;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.io.AbstractParser;
import io.jsonwebtoken.impl.io.BytesInputStream;
import io.jsonwebtoken.impl.io.CharSequenceReader;
import io.jsonwebtoken.impl.io.JsonObjectDeserializer;
import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.io.UncloseableInputStream;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.impl.security.DefaultDecryptAeadRequest;
import io.jsonwebtoken.impl.security.DefaultDecryptionKeyRequest;
import io.jsonwebtoken.impl.security.DefaultVerifySecureDigestRequest;
import io.jsonwebtoken.impl.security.LocatingKeyResolver;
import io.jsonwebtoken.impl.security.ProviderKey;
import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.DateFormats;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.DecryptAeadRequest;
import io.jsonwebtoken.security.DecryptionKeyRequest;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import io.jsonwebtoken.security.WeakKeyException;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;

public class DefaultJwtParser extends AbstractParser implements JwtParser {
   static final char SEPARATOR_CHAR = '.';
   private static final JwtTokenizer jwtTokenizer = new JwtTokenizer();
   static final String PRIV_KEY_VERIFY_MSG = "PrivateKeys may not be used to verify digital signatures. PrivateKeys are used to sign, and PublicKeys are used to verify.";
   static final String PUB_KEY_DECRYPT_MSG = "PublicKeys may not be used to decrypt data. PublicKeys are used to encrypt, and PrivateKeys are used to decrypt.";
   public static final String INCORRECT_EXPECTED_CLAIM_MESSAGE_TEMPLATE = "Expected %s claim to be: %s, but was: %s.";
   public static final String MISSING_EXPECTED_CLAIM_VALUE_MESSAGE_TEMPLATE = "Missing expected '%s' value in '%s' claim %s.";
   public static final String MISSING_JWS_ALG_MSG = "JWS header does not contain a required 'alg' (Algorithm) header parameter.  This header parameter is mandatory per the JWS Specification, Section 4.1.1. See https://www.rfc-editor.org/rfc/rfc7515.html#section-4.1.1 for more information.";
   public static final String MISSING_JWE_ALG_MSG = "JWE header does not contain a required 'alg' (Algorithm) header parameter.  This header parameter is mandatory per the JWE Specification, Section 4.1.1. See https://www.rfc-editor.org/rfc/rfc7516.html#section-4.1.1 for more information.";
   public static final String MISSING_JWS_DIGEST_MSG_FMT = "The JWS header references signature algorithm '%s' but the compact JWE string is missing the required signature.";
   public static final String MISSING_JWE_DIGEST_MSG_FMT = "The JWE header references key management algorithm '%s' but the compact JWE string is missing the required AAD authentication tag.";
   private static final String MISSING_ENC_MSG = "JWE header does not contain a required 'enc' (Encryption Algorithm) header parameter.  This header parameter is mandatory per the JWE Specification, Section 4.1.2. See https://www.rfc-editor.org/rfc/rfc7516.html#section-4.1.2 for more information.";
   private static final String UNSECURED_DISABLED_MSG_PREFIX;
   private static final String CRIT_UNSECURED_MSG;
   private static final String CRIT_MISSING_MSG;
   private static final String CRIT_UNSUPPORTED_MSG;
   private static final String JWE_NONE_MSG;
   private static final String JWS_NONE_SIG_MISMATCH_MSG;
   private static final String B64_MISSING_PAYLOAD = "Unable to verify JWS signature: the parser has encountered an Unencoded Payload JWS with detached payload, but the detached payload value required for signature verification has not been provided. If you expect to receive and parse Unencoded Payload JWSs in your application, the overloaded JwtParser.parseSignedContent or JwtParser.parseSignedClaims methods that accept a byte[] or InputStream must be used for these kinds of JWSs. Header: %s";
   private static final String B64_DECOMPRESSION_MSG;
   private static final String UNPROTECTED_DECOMPRESSION_MSG;
   private final Provider provider;
   private final SigningKeyResolver signingKeyResolver;
   private final boolean unsecured;
   private final boolean unsecuredDecompression;
   private final Function sigAlgs;
   private final Function encAlgs;
   private final Function keyAlgs;
   private final Function zipAlgs;
   private final Locator keyLocator;
   private final Decoder decoder;
   private final Deserializer deserializer;
   private final ClaimsBuilder expectedClaims;
   private final Clock clock;
   private final Set critical;
   private final long allowedClockSkewMillis;

   DefaultJwtParser(Provider provider, SigningKeyResolver signingKeyResolver, boolean unsecured, boolean unsecuredDecompression, Locator keyLocator, Clock clock, Set critical, long allowedClockSkewMillis, DefaultClaims expectedClaims, Decoder base64UrlDecoder, Deserializer deserializer, CompressionCodecResolver compressionCodecResolver, Registry zipAlgs, Registry sigAlgs, Registry keyAlgs, Registry encAlgs) {
      this.provider = provider;
      this.unsecured = unsecured;
      this.unsecuredDecompression = unsecuredDecompression;
      this.signingKeyResolver = signingKeyResolver;
      this.keyLocator = (Locator)Assert.notNull(keyLocator, "Key Locator cannot be null.");
      this.clock = (Clock)Assert.notNull(clock, "Clock cannot be null.");
      this.critical = Collections.nullSafe(critical);
      this.allowedClockSkewMillis = allowedClockSkewMillis;
      this.expectedClaims = (ClaimsBuilder)Jwts.claims().add(expectedClaims);
      this.decoder = (Decoder)Assert.notNull(base64UrlDecoder, "base64UrlDecoder cannot be null.");
      this.deserializer = (Deserializer)Assert.notNull(deserializer, "JSON Deserializer cannot be null.");
      this.sigAlgs = new IdLocator(DefaultHeader.ALGORITHM, sigAlgs, "JWS header does not contain a required 'alg' (Algorithm) header parameter.  This header parameter is mandatory per the JWS Specification, Section 4.1.1. See https://www.rfc-editor.org/rfc/rfc7515.html#section-4.1.1 for more information.");
      this.keyAlgs = new IdLocator(DefaultHeader.ALGORITHM, keyAlgs, "JWE header does not contain a required 'alg' (Algorithm) header parameter.  This header parameter is mandatory per the JWE Specification, Section 4.1.1. See https://www.rfc-editor.org/rfc/rfc7516.html#section-4.1.1 for more information.");
      this.encAlgs = new IdLocator(DefaultJweHeader.ENCRYPTION_ALGORITHM, encAlgs, "JWE header does not contain a required 'enc' (Encryption Algorithm) header parameter.  This header parameter is mandatory per the JWE Specification, Section 4.1.2. See https://www.rfc-editor.org/rfc/rfc7516.html#section-4.1.2 for more information.");
      this.zipAlgs = (Function)(compressionCodecResolver != null ? new CompressionCodecLocator(compressionCodecResolver) : new IdLocator(DefaultHeader.COMPRESSION_ALGORITHM, zipAlgs, (String)null));
   }

   public boolean isSigned(CharSequence compact) {
      if (!Strings.hasText(compact)) {
         return false;
      } else {
         try {
            TokenizedJwt tokenized = jwtTokenizer.tokenize(new CharSequenceReader(compact));
            return !(tokenized instanceof TokenizedJwe) && Strings.hasText(tokenized.getDigest());
         } catch (MalformedJwtException var3) {
            return false;
         }
      }
   }

   private static boolean hasContentType(Header header) {
      return header != null && Strings.hasText(header.getContentType());
   }

   private byte[] verifySignature(TokenizedJwt tokenized, JwsHeader jwsHeader, String alg, SigningKeyResolver resolver, Claims claims, Payload payload) {
      Assert.notNull(resolver, "SigningKeyResolver instance cannot be null.");

      SecureDigestAlgorithm<?, Key> algorithm;
      try {
         algorithm = (SecureDigestAlgorithm)this.sigAlgs.apply(jwsHeader);
      } catch (UnsupportedJwtException e) {
         String msg = "Unsupported signature algorithm '" + alg + "'";
         throw new SignatureException(msg, e);
      }

      Assert.stateNotNull(algorithm, "JWS Signature Algorithm cannot be null.");
      Key key;
      if (claims != null) {
         key = resolver.resolveSigningKey(jwsHeader, claims);
      } else {
         key = resolver.resolveSigningKey(jwsHeader, payload.getBytes());
      }

      if (key == null) {
         String msg = "Cannot verify JWS signature: unable to locate signature verification key for JWS with header: " + jwsHeader;
         throw new UnsupportedJwtException(msg);
      } else {
         Provider provider = ProviderKey.getProvider(key, this.provider);
         key = ProviderKey.getKey(key);
         Assert.stateNotNull(key, "ProviderKey cannot be null.");
         if (key instanceof PrivateKey) {
            throw new InvalidKeyException("PrivateKeys may not be used to verify digital signatures. PrivateKeys are used to sign, and PublicKeys are used to verify.");
         } else {
            byte[] signature = this.decode(tokenized.getDigest(), "JWS signature");
            InputStream payloadStream = null;
            InputStream verificationInput;
            if (jwsHeader.isPayloadEncoded()) {
               int len = tokenized.getProtected().length() + 1 + tokenized.getPayload().length();
               CharBuffer cb = CharBuffer.allocate(len);
               cb.put(Strings.wrap(tokenized.getProtected()));
               cb.put('.');
               cb.put(Strings.wrap(tokenized.getPayload()));
               cb.rewind();
               ByteBuffer bb = StandardCharsets.US_ASCII.encode(cb);
               bb.rewind();
               byte[] data = new byte[bb.remaining()];
               bb.get(data);
               verificationInput = Streams.of(data);
            } else {
               ByteBuffer headerBuf = StandardCharsets.US_ASCII.encode(Strings.wrap(tokenized.getProtected()));
               headerBuf.rewind();
               ByteBuffer buf = ByteBuffer.allocate(headerBuf.remaining() + 1);
               buf.put(headerBuf);
               buf.put((byte)46);
               buf.rewind();
               byte[] data = new byte[buf.remaining()];
               buf.get(data);
               InputStream prefixStream = Streams.of(data);
               payloadStream = payload.toInputStream();
               verificationInput = new SequenceInputStream(prefixStream, new UncloseableInputStream(payloadStream));
            }

            try {
               VerifySecureDigestRequest<Key> request = new DefaultVerifySecureDigestRequest(verificationInput, provider, (SecureRandom)null, key, signature);
               if (!algorithm.verify(request)) {
                  String msg = "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.";
                  throw new SignatureException(msg);
               }
            } catch (WeakKeyException e) {
               throw e;
            } catch (IllegalArgumentException | InvalidKeyException e) {
               String algId = algorithm.getId();
               String msg = "The parsed JWT indicates it was signed with the '" + algId + "' signature " + "algorithm, but the provided " + key.getClass().getName() + " key may " + "not be used to verify " + algId + " signatures.  Because the specified " + "key reflects a specific and expected algorithm, and the JWT does not reflect " + "this algorithm, it is likely that the JWT was not expected and therefore should not be " + "trusted.  Another possibility is that the parser was provided the incorrect " + "signature verification key, but this cannot be assumed for security reasons.";
               throw new UnsupportedJwtException(msg, e);
            } finally {
               Streams.reset(payloadStream);
            }

            return signature;
         }
      }
   }

   public Jwt parse(Reader reader) {
      Assert.notNull(reader, "Reader cannot be null.");
      return this.parse(reader, Payload.EMPTY);
   }

   private Jwt parse(Reader compact, Payload unencodedPayload) {
      Assert.notNull(compact, "Compact reader cannot be null.");
      Assert.stateNotNull(unencodedPayload, "internal error: unencodedPayload is null.");
      TokenizedJwt tokenized = jwtTokenizer.tokenize(compact);
      CharSequence base64UrlHeader = tokenized.getProtected();
      if (!Strings.hasText(base64UrlHeader)) {
         String msg = "Compact JWT strings MUST always have a Base64Url protected header per https://tools.ietf.org/html/rfc7519#section-7.2 (steps 2-4).";
         throw new MalformedJwtException(msg);
      } else {
         byte[] headerBytes = this.decode(base64UrlHeader, "protected header");
         Map<String, ?> m = this.deserialize(Streams.of(headerBytes), "protected header");

         Header header;
         try {
            header = tokenized.createHeader(m);
         } catch (Exception e) {
            String msg = "Invalid protected header: " + e.getMessage();
            throw new MalformedJwtException(msg, e);
         }

         String alg = Strings.clean(header.getAlgorithm());
         if (!Strings.hasText(alg)) {
            String msg = tokenized instanceof TokenizedJwe ? "JWE header does not contain a required 'alg' (Algorithm) header parameter.  This header parameter is mandatory per the JWE Specification, Section 4.1.1. See https://www.rfc-editor.org/rfc/rfc7516.html#section-4.1.1 for more information." : "JWS header does not contain a required 'alg' (Algorithm) header parameter.  This header parameter is mandatory per the JWS Specification, Section 4.1.1. See https://www.rfc-editor.org/rfc/rfc7515.html#section-4.1.1 for more information.";
            throw new MalformedJwtException(msg);
         } else {
            boolean unsecured = SIG.NONE.getId().equalsIgnoreCase(alg);
            CharSequence base64UrlDigest = tokenized.getDigest();
            boolean hasDigest = Strings.hasText(base64UrlDigest);
            if (unsecured) {
               if (tokenized instanceof TokenizedJwe) {
                  throw new MalformedJwtException(JWE_NONE_MSG);
               }

               if (!this.unsecured) {
                  String msg = UNSECURED_DISABLED_MSG_PREFIX + header;
                  throw new UnsupportedJwtException(msg);
               }

               if (hasDigest) {
                  throw new MalformedJwtException(JWS_NONE_SIG_MISMATCH_MSG);
               }

               if (header.containsKey(DefaultProtectedHeader.CRIT.getId())) {
                  String msg = String.format(CRIT_UNSECURED_MSG, header);
                  throw new MalformedJwtException(msg);
               }
            } else if (!hasDigest) {
               String fmt = tokenized instanceof TokenizedJwe ? "The JWE header references key management algorithm '%s' but the compact JWE string is missing the required AAD authentication tag." : "The JWS header references signature algorithm '%s' but the compact JWE string is missing the required signature.";
               String msg = String.format(fmt, alg);
               throw new MalformedJwtException(msg);
            }

            if (header instanceof ProtectedHeader) {
               Set<String> crit = Collections.nullSafe(((ProtectedHeader)header).getCritical());
               Set<String> supportedCrit = this.critical;
               String b64Id = DefaultJwsHeader.B64.getId();
               if (!unencodedPayload.isEmpty() && !this.critical.contains(b64Id)) {
                  supportedCrit = new LinkedHashSet(Collections.size(this.critical) + 1);
                  supportedCrit.add(DefaultJwsHeader.B64.getId());
                  supportedCrit.addAll(this.critical);
               }

               for(String name : crit) {
                  if (!header.containsKey(name)) {
                     String msg = String.format(CRIT_MISSING_MSG, name, name, header);
                     throw new MalformedJwtException(msg);
                  }

                  if (!supportedCrit.contains(name)) {
                     String msg = String.format(CRIT_UNSUPPORTED_MSG, name, name, header);
                     throw new UnsupportedJwtException(msg);
                  }
               }
            }

            CharSequence payloadToken = tokenized.getPayload();
            boolean integrityVerified = false;
            boolean payloadBase64UrlEncoded = !(header instanceof JwsHeader) || ((JwsHeader)header).isPayloadEncoded();
            Payload payload;
            if (payloadBase64UrlEncoded) {
               byte[] data = this.decode(tokenized.getPayload(), "payload");
               payload = new Payload(data, header.getContentType());
            } else if (Strings.hasText(payloadToken)) {
               payload = new Payload(payloadToken, header.getContentType());
            } else {
               if (unencodedPayload.isEmpty()) {
                  String msg = String.format("Unable to verify JWS signature: the parser has encountered an Unencoded Payload JWS with detached payload, but the detached payload value required for signature verification has not been provided. If you expect to receive and parse Unencoded Payload JWSs in your application, the overloaded JwtParser.parseSignedContent or JwtParser.parseSignedClaims methods that accept a byte[] or InputStream must be used for these kinds of JWSs. Header: %s", header);
                  throw new SignatureException(msg);
               }

               payload = unencodedPayload;
            }

            if (tokenized instanceof TokenizedJwe && payload.isEmpty()) {
               String msg = "Compact JWE strings MUST always contain a payload (ciphertext).";
               throw new MalformedJwtException(msg);
            } else {
               byte[] iv = null;
               byte[] digest = null;
               if (tokenized instanceof TokenizedJwe) {
                  TokenizedJwe tokenizedJwe = (TokenizedJwe)tokenized;
                  JweHeader jweHeader = (JweHeader)Assert.stateIsInstance(JweHeader.class, header, "Not a JweHeader. ");
                  byte[] cekBytes = Bytes.EMPTY;
                  CharSequence base64Url = tokenizedJwe.getEncryptedKey();
                  if (Strings.hasText(base64Url)) {
                     cekBytes = this.decode(base64Url, "JWE encrypted key");
                     if (Bytes.isEmpty(cekBytes)) {
                        String msg = "Compact JWE string represents an encrypted key, but the key is empty.";
                        throw new MalformedJwtException(msg);
                     }
                  }

                  base64Url = tokenizedJwe.getIv();
                  if (Strings.hasText(base64Url)) {
                     iv = this.decode(base64Url, "JWE Initialization Vector");
                  }

                  if (Bytes.isEmpty(iv)) {
                     String msg = "Compact JWE strings must always contain an Initialization Vector.";
                     throw new MalformedJwtException(msg);
                  }

                  ByteBuffer buf = StandardCharsets.US_ASCII.encode(Strings.wrap(base64UrlHeader));
                  byte[] aadBytes = new byte[buf.remaining()];
                  buf.get(aadBytes);
                  InputStream aad = Streams.of(aadBytes);
                  Assert.hasText(base64UrlDigest, "JWE AAD Authentication Tag cannot be null or empty.");
                  digest = this.decode(base64UrlDigest, "JWE AAD Authentication Tag");
                  if (Bytes.isEmpty(digest)) {
                     String msg = "Compact JWE strings must always contain an AAD Authentication Tag.";
                     throw new MalformedJwtException(msg);
                  }

                  String enc = jweHeader.getEncryptionAlgorithm();
                  if (!Strings.hasText(enc)) {
                     throw new MalformedJwtException("JWE header does not contain a required 'enc' (Encryption Algorithm) header parameter.  This header parameter is mandatory per the JWE Specification, Section 4.1.2. See https://www.rfc-editor.org/rfc/rfc7516.html#section-4.1.2 for more information.");
                  }

                  AeadAlgorithm encAlg = (AeadAlgorithm)this.encAlgs.apply(jweHeader);
                  Assert.stateNotNull(encAlg, "JWE Encryption Algorithm cannot be null.");
                  KeyAlgorithm keyAlg = (KeyAlgorithm)this.keyAlgs.apply(jweHeader);
                  Assert.stateNotNull(keyAlg, "JWE Key Algorithm cannot be null.");
                  Key key = (Key)this.keyLocator.locate(jweHeader);
                  if (key == null) {
                     String msg = "Cannot decrypt JWE payload: unable to locate key for JWE with header: " + jweHeader;
                     throw new UnsupportedJwtException(msg);
                  }

                  if (key instanceof PublicKey) {
                     throw new InvalidKeyException("PublicKeys may not be used to decrypt data. PublicKeys are used to encrypt, and PrivateKeys are used to decrypt.");
                  }

                  Provider provider = ProviderKey.getProvider(key, this.provider);
                  key = ProviderKey.getKey(key);
                  DecryptionKeyRequest<Key> request = new DefaultDecryptionKeyRequest(cekBytes, provider, (SecureRandom)null, jweHeader, encAlg, key);
                  SecretKey cek = keyAlg.getDecryptionKey(request);
                  if (cek == null) {
                     String msg = "The '" + keyAlg.getId() + "' JWE key algorithm did not return a decryption key. " + "Unable to perform '" + encAlg.getId() + "' decryption.";
                     throw new IllegalStateException(msg);
                  }

                  InputStream ciphertext = payload.toInputStream();
                  ByteArrayOutputStream plaintext = new ByteArrayOutputStream(8192);
                  DecryptAeadRequest dreq = new DefaultDecryptAeadRequest(ciphertext, cek, aad, iv, digest);
                  encAlg.decrypt(dreq, plaintext);
                  payload = new Payload(plaintext.toByteArray(), header.getContentType());
                  integrityVerified = true;
               } else if (hasDigest && this.signingKeyResolver == null) {
                  JwsHeader jwsHeader = (JwsHeader)Assert.stateIsInstance(JwsHeader.class, header, "Not a JwsHeader. ");
                  digest = this.verifySignature(tokenized, jwsHeader, alg, new LocatingKeyResolver(this.keyLocator), (Claims)null, payload);
                  integrityVerified = true;
               }

               CompressionAlgorithm compressionAlgorithm = (CompressionAlgorithm)this.zipAlgs.apply(header);
               if (compressionAlgorithm != null) {
                  if (!integrityVerified) {
                     if (!payloadBase64UrlEncoded) {
                        String msg = String.format(B64_DECOMPRESSION_MSG, compressionAlgorithm.getId());
                        throw new UnsupportedJwtException(msg);
                     }

                     if (!this.unsecuredDecompression) {
                        String msg = String.format(UNPROTECTED_DECOMPRESSION_MSG, compressionAlgorithm.getId());
                        throw new UnsupportedJwtException(msg);
                     }
                  }

                  payload = payload.decompress(compressionAlgorithm);
               }

               Claims claims = null;
               byte[] payloadBytes = payload.getBytes();
               if (payload.isConsumable()) {
                  InputStream in = null;

                  try {
                     in = payload.toInputStream();
                     if (!hasContentType(header)) {
                        Map<String, ?> claimsMap = null;

                        try {
                           if (!in.markSupported()) {
                              in = new BufferedInputStream(in);
                              in.mark(0);
                           }

                           claimsMap = this.deserialize(new UncloseableInputStream(in), "claims");
                        } catch (MalformedJwtException | DeserializationException var48) {
                        } finally {
                           Streams.reset(in);
                        }

                        if (claimsMap != null) {
                           try {
                              claims = new DefaultClaims(claimsMap);
                           } catch (Throwable t) {
                              String msg = "Invalid claims: " + t.getMessage();
                              throw new MalformedJwtException(msg);
                           }
                        }
                     }

                     if (claims == null) {
                        payloadBytes = Streams.bytes(in, "Unable to convert payload to byte array.");
                     }
                  } finally {
                     Objects.nullSafeClose(new Closeable[]{in});
                  }
               }

               if (hasDigest && this.signingKeyResolver != null) {
                  JwsHeader jwsHeader = (JwsHeader)Assert.stateIsInstance(JwsHeader.class, header, "Not a JwsHeader. ");
                  digest = this.verifySignature(tokenized, jwsHeader, alg, this.signingKeyResolver, claims, payload);
                  integrityVerified = true;
               }

               Object body = claims != null ? claims : payloadBytes;
               Jwt<?, ?> jwt;
               if (header instanceof JweHeader) {
                  jwt = new DefaultJwe((JweHeader)header, body, iv, digest);
               } else if (hasDigest) {
                  JwsHeader jwsHeader = (JwsHeader)Assert.isInstanceOf(JwsHeader.class, header, "JwsHeader required.");
                  jwt = new DefaultJws(jwsHeader, body, digest, base64UrlDigest.toString());
               } else {
                  jwt = new DefaultJwt(header, body);
               }

               boolean allowSkew = this.allowedClockSkewMillis > 0L;
               if (claims != null) {
                  Date now = this.clock.now();
                  long nowTime = now.getTime();
                  Date exp = claims.getExpiration();
                  if (exp != null) {
                     long maxTime = nowTime - this.allowedClockSkewMillis;
                     Date max = allowSkew ? new Date(maxTime) : now;
                     if (max.after(exp)) {
                        String expVal = DateFormats.formatIso8601(exp, true);
                        String nowVal = DateFormats.formatIso8601(now, true);
                        long differenceMillis = nowTime - exp.getTime();
                        String msg = "JWT expired " + differenceMillis + " milliseconds ago at " + expVal + ". " + "Current time: " + nowVal + ". Allowed clock skew: " + this.allowedClockSkewMillis + " milliseconds.";
                        throw new ExpiredJwtException(header, claims, msg);
                     }
                  }

                  Date nbf = claims.getNotBefore();
                  if (nbf != null) {
                     long minTime = nowTime + this.allowedClockSkewMillis;
                     Date min = allowSkew ? new Date(minTime) : now;
                     if (min.before(nbf)) {
                        String nbfVal = DateFormats.formatIso8601(nbf, true);
                        String nowVal = DateFormats.formatIso8601(now, true);
                        long differenceMillis = nbf.getTime() - nowTime;
                        String msg = "JWT early by " + differenceMillis + " milliseconds before " + nbfVal + ". Current time: " + nowVal + ". Allowed clock skew: " + this.allowedClockSkewMillis + " milliseconds.";
                        throw new PrematureJwtException(header, claims, msg);
                     }
                  }

                  this.validateExpectedClaims(header, claims);
               }

               return jwt;
            }
         }
      }
   }

   private static Object normalize(Object o) {
      if (o instanceof Integer) {
         o = ((Integer)o).longValue();
      }

      return o;
   }

   private void validateExpectedClaims(Header header, Claims claims) {
      Claims expected = (Claims)this.expectedClaims.build();

      for(String expectedClaimName : expected.keySet()) {
         Object expectedClaimValue = normalize(expected.get(expectedClaimName));
         Object actualClaimValue = normalize(claims.get(expectedClaimName));
         if (expectedClaimValue instanceof Date) {
            try {
               actualClaimValue = claims.get(expectedClaimName, Date.class);
            } catch (Exception var13) {
               String msg = "JWT Claim '" + expectedClaimName + "' was expected to be a Date, but its value " + "cannot be converted to a Date using current heuristics.  Value: " + actualClaimValue;
               throw new IncorrectClaimException(header, claims, expectedClaimName, expectedClaimValue, msg);
            }
         }

         if (actualClaimValue == null) {
            boolean collection = expectedClaimValue instanceof Collection;
            String msg = "Missing '" + expectedClaimName + "' claim. Expected value";
            if (collection) {
               msg = msg + "s: " + expectedClaimValue;
            } else {
               msg = msg + ": " + expectedClaimValue;
            }

            throw new MissingClaimException(header, claims, expectedClaimName, expectedClaimValue, msg);
         }

         if (expectedClaimValue instanceof Collection) {
            Collection<?> expectedValues = (Collection)expectedClaimValue;
            Collection<?> actualValues = (Collection<?>)(actualClaimValue instanceof Collection ? (Collection)actualClaimValue : Collections.setOf(new Object[]{actualClaimValue}));

            for(Object expectedValue : expectedValues) {
               if (!Collections.contains(actualValues.iterator(), expectedValue)) {
                  String msg = String.format("Missing expected '%s' value in '%s' claim %s.", expectedValue, expectedClaimName, actualValues);
                  throw new IncorrectClaimException(header, claims, expectedClaimName, expectedClaimValue, msg);
               }
            }
         } else if (!expectedClaimValue.equals(actualClaimValue)) {
            String msg = String.format("Expected %s claim to be: %s, but was: %s.", expectedClaimName, expectedClaimValue, actualClaimValue);
            throw new IncorrectClaimException(header, claims, expectedClaimName, expectedClaimValue, msg);
         }
      }

   }

   public Object parse(CharSequence compact, JwtHandler handler) {
      return this.parse(compact, Payload.EMPTY).accept(handler);
   }

   private Jwt parse(CharSequence compact, Payload unencodedPayload) {
      Assert.hasText(compact, "JWT String argument cannot be null or empty.");
      return this.parse((Reader)(new CharSequenceReader(compact)), (Payload)unencodedPayload);
   }

   public Jwt parseContentJwt(CharSequence jwt) {
      return (Jwt)((Jwt)this.parse((CharSequence)jwt)).accept(Jwt.UNSECURED_CONTENT);
   }

   public Jwt parseClaimsJwt(CharSequence jwt) {
      return (Jwt)((Jwt)this.parse((CharSequence)jwt)).accept(Jwt.UNSECURED_CLAIMS);
   }

   public Jws parseContentJws(CharSequence jws) {
      return this.parseSignedContent(jws);
   }

   public Jws parseClaimsJws(CharSequence jws) {
      return this.parseSignedClaims(jws);
   }

   public Jwt parseUnsecuredContent(CharSequence jwt) throws JwtException, IllegalArgumentException {
      return (Jwt)((Jwt)this.parse((CharSequence)jwt)).accept(Jwt.UNSECURED_CONTENT);
   }

   public Jwt parseUnsecuredClaims(CharSequence jwt) throws JwtException, IllegalArgumentException {
      return (Jwt)((Jwt)this.parse((CharSequence)jwt)).accept(Jwt.UNSECURED_CLAIMS);
   }

   public Jws parseSignedContent(CharSequence compact) {
      return (Jws)((Jwt)this.parse((CharSequence)compact)).accept(Jws.CONTENT);
   }

   private Jws parseSignedContent(CharSequence jws, Payload unencodedPayload) {
      return (Jws)this.parse(jws, unencodedPayload).accept(Jws.CONTENT);
   }

   public Jws parseSignedClaims(CharSequence compact) {
      return (Jws)((Jwt)this.parse((CharSequence)compact)).accept(Jws.CLAIMS);
   }

   private Jws parseSignedClaims(CharSequence jws, Payload unencodedPayload) {
      unencodedPayload.setClaimsExpected(true);
      return (Jws)this.parse(jws, unencodedPayload).accept(Jws.CLAIMS);
   }

   public Jws parseSignedContent(CharSequence jws, byte[] unencodedPayload) {
      Assert.notEmpty(unencodedPayload, "unencodedPayload argument cannot be null or empty.");
      return this.parseSignedContent(jws, new Payload(unencodedPayload, (String)null));
   }

   private static Payload payloadFor(InputStream in) {
      if (in instanceof BytesInputStream) {
         byte[] data = Streams.bytes(in, "Unable to obtain payload InputStream bytes.");
         return new Payload(data, (String)null);
      } else {
         return new Payload(in, (String)null);
      }
   }

   public Jws parseSignedContent(CharSequence jws, InputStream unencodedPayload) {
      Assert.notNull(unencodedPayload, "unencodedPayload InputStream cannot be null.");
      return this.parseSignedContent(jws, payloadFor(unencodedPayload));
   }

   public Jws parseSignedClaims(CharSequence jws, byte[] unencodedPayload) {
      Assert.notEmpty(unencodedPayload, "unencodedPayload argument cannot be null or empty.");
      return this.parseSignedClaims(jws, new Payload(unencodedPayload, (String)null));
   }

   public Jws parseSignedClaims(CharSequence jws, InputStream unencodedPayload) {
      Assert.notNull(unencodedPayload, "unencodedPayload InputStream cannot be null.");
      byte[] bytes = Streams.bytes(unencodedPayload, "Unable to obtain Claims bytes from unencodedPayload InputStream");
      return this.parseSignedClaims(jws, new Payload(bytes, (String)null));
   }

   public Jwe parseEncryptedContent(CharSequence compact) throws JwtException {
      return (Jwe)((Jwt)this.parse((CharSequence)compact)).accept(Jwe.CONTENT);
   }

   public Jwe parseEncryptedClaims(CharSequence compact) throws JwtException {
      return (Jwe)((Jwt)this.parse((CharSequence)compact)).accept(Jwe.CLAIMS);
   }

   protected byte[] decode(CharSequence base64UrlEncoded, String name) {
      try {
         InputStream decoding = (InputStream)this.decoder.decode(Streams.of(Strings.utf8(base64UrlEncoded)));
         return Streams.bytes(decoding, "Unable to Base64Url-decode input.");
      } catch (Throwable t) {
         String value = "payload".equals(name) ? "<redacted>" : base64UrlEncoded.toString();
         String msg = "Invalid Base64Url " + name + ": " + value;
         throw new MalformedJwtException(msg, t);
      }
   }

   protected Map deserialize(InputStream in, String name) {
      Map var5;
      try {
         Reader reader = Streams.reader(in);
         JsonObjectDeserializer deserializer = new JsonObjectDeserializer(this.deserializer, name);
         var5 = deserializer.apply(reader);
      } finally {
         Objects.nullSafeClose(new Closeable[]{in});
      }

      return var5;
   }

   static {
      UNSECURED_DISABLED_MSG_PREFIX = "Unsecured JWSs (those with an " + DefaultHeader.ALGORITHM + " header value of '" + SIG.NONE.getId() + "') are disallowed by " + "default as mandated by https://www.rfc-editor.org/rfc/rfc7518.html#section-3.6. If you wish to " + "allow them to be parsed, call the JwtParserBuilder.unsecured() method, but please read the " + "security considerations covered in that method's JavaDoc before doing so. Header: ";
      CRIT_UNSECURED_MSG = "Unsecured JWSs (those with an " + DefaultHeader.ALGORITHM + " header value of '" + SIG.NONE.getId() + "') may not use the " + DefaultProtectedHeader.CRIT + " header parameter per https://www.rfc-editor.org/rfc/rfc7515.html#section-4.1.11 (\"the [crit] Header " + "Parameter MUST be integrity protected; therefore, it MUST occur only within [a] JWS Protected Header)\"." + " Header: %s";
      CRIT_MISSING_MSG = "Protected Header " + DefaultProtectedHeader.CRIT + " set references header name '%s', but the header does not contain an " + "associated '%s' header parameter as required by " + "https://www.rfc-editor.org/rfc/rfc7515.html#section-4.1.11. Header: %s";
      CRIT_UNSUPPORTED_MSG = "Protected Header " + DefaultProtectedHeader.CRIT + " set references unsupported header name '%s'. Application developers expecting to support a JWT " + "extension using header '%s' in their application code must indicate it " + "is supported by using the JwtParserBuilder.critical method. Header: %s";
      JWE_NONE_MSG = "JWEs do not support key management " + DefaultHeader.ALGORITHM + " header value '" + SIG.NONE.getId() + "' per " + "https://www.rfc-editor.org/rfc/rfc7518.html#section-4.1";
      JWS_NONE_SIG_MISMATCH_MSG = "The JWS header references signature algorithm '" + SIG.NONE.getId() + "' yet the compact JWS string contains a signature. This is not permitted " + "per https://tools.ietf.org/html/rfc7518#section-3.6.";
      B64_DECOMPRESSION_MSG = "The JWT header references compression algorithm '%s', but payload decompression for Unencoded JWSs (those with an " + DefaultJwsHeader.B64 + " header value of false) that rely on a SigningKeyResolver are disallowed " + "by default to protect against [Denial of Service attacks](" + "https://www.usenix.org/system/files/conference/usenixsecurity15/sec15-paper-pellegrino.pdf).  If you " + "wish to enable Unencoded JWS payload decompression, configure the JwtParserBuilder." + "keyLocator(Locator) and do not configure a SigningKeyResolver.";
      UNPROTECTED_DECOMPRESSION_MSG = "The JWT header references compression algorithm '%s', but payload decompression for Unprotected JWTs (those with an " + DefaultHeader.ALGORITHM + " header value of '" + SIG.NONE.getId() + "') or Unencoded JWSs (those with a " + DefaultJwsHeader.B64 + " header value of false) that also rely on a SigningKeyResolver are disallowed " + "by default to protect against [Denial of Service attacks](" + "https://www.usenix.org/system/files/conference/usenixsecurity15/sec15-paper-pellegrino.pdf).  If you " + "wish to enable Unsecure JWS or Unencoded JWS payload decompression, call the JwtParserBuilder." + "unsecuredDecompression() method, but please read the security considerations covered in that " + "method's JavaDoc before doing so.";
   }
}
