package io.vertx.ext.auth.impl.jose;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.NoSuchKeyIdException;
import io.vertx.ext.auth.impl.CertificateHelper;
import io.vertx.ext.auth.impl.Codec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class JWT {
   private static final Logger LOG = LoggerFactory.getLogger(JWT.class);
   private static final Random RND = new Random();
   private static final Charset UTF8;
   private boolean allowEmbeddedKey = false;
   private X509Certificate rootCA;
   private MessageDigest nonceDigest;
   private final Map SIGN = new ConcurrentHashMap();
   private final Map VERIFY = new ConcurrentHashMap();

   public JWT addJWK(JWK jwk) {
      if (jwk.use() != null && !"sig".equals(jwk.use())) {
         LOG.warn("JWK skipped: use: sig != " + jwk.use());
      } else {
         synchronized(this) {
            if (jwk.mac() != null || jwk.publicKey() != null) {
               List<JWS> current = (List)this.VERIFY.computeIfAbsent(jwk.getAlgorithm(), (k) -> new ArrayList());
               this.addJWK(current, jwk);
            }

            if (jwk.mac() != null || jwk.privateKey() != null) {
               List<JWS> current = (List)this.SIGN.computeIfAbsent(jwk.getAlgorithm(), (k) -> new ArrayList());
               this.addJWK(current, jwk);
            }
         }
      }

      return this;
   }

   public JWT allowEmbeddedKey(boolean allowEmbeddedKey) {
      this.allowEmbeddedKey = allowEmbeddedKey;
      return this;
   }

   public JWT embeddedKeyRootCA(String rootCA) throws CertificateException {
      this.rootCA = JWS.parseX5c(Codec.base64Decode(rootCA));
      this.allowEmbeddedKey = true;
      return this;
   }

   public JWT nonceAlgorithm(String alg) {
      if (alg == null) {
         this.nonceDigest = null;
      } else {
         try {
            this.nonceDigest = MessageDigest.getInstance(alg);
         } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
         }
      }

      return this;
   }

   private void addJWK(List current, JWK jwk) {
      boolean replaced = false;

      for(int i = 0; i < current.size(); ++i) {
         if (((JWS)current.get(i)).jwk().label().equals(jwk.label())) {
            LOG.info("replacing JWK with label " + jwk.label());
            current.set(i, new JWS(jwk));
            replaced = true;
            break;
         }
      }

      if (!replaced) {
         current.add(new JWS(jwk));
      }

   }

   public static JsonObject parse(byte[] token) {
      return parse(new String(token, UTF8));
   }

   public static JsonObject parse(String token) {
      String[] segments = token.split("\\.");
      if (segments.length >= 2 && segments.length <= 3) {
         String headerSeg = segments[0];
         String payloadSeg = segments[1];
         String signatureSeg = segments.length == 2 ? null : segments[2];
         JsonObject header = new JsonObject(new String(Codec.base64UrlDecode(headerSeg), UTF8));
         JsonObject payload = new JsonObject(new String(Codec.base64UrlDecode(payloadSeg), UTF8));
         return (new JsonObject()).put("header", header).put("payload", payload).put("signatureBase", headerSeg + "." + payloadSeg).put("signature", signatureSeg);
      } else {
         throw new RuntimeException("Not enough or too many segments [" + segments.length + "]");
      }
   }

   public JsonObject decode(String token) {
      return this.decode(token, false, (List)null);
   }

   public JsonObject decode(String token, List crls) {
      return this.decode(token, false, crls);
   }

   public JsonObject decode(String token, boolean full, List crls) {
      String[] segments = token.split("\\.");
      if (segments.length < 2) {
         throw new IllegalStateException("Invalid format for JWT");
      } else {
         String headerSeg = segments[0];
         String payloadSeg = segments[1];
         String signatureSeg = segments.length == 3 ? segments[2] : null;
         if ("".equals(signatureSeg)) {
            throw new IllegalStateException("Signature is required");
         } else {
            JsonObject header = new JsonObject(Buffer.buffer(Codec.base64UrlDecode(headerSeg)));
            boolean unsecure = this.isUnsecure();
            if (unsecure) {
               if (!this.allowEmbeddedKey && segments.length != 2) {
                  throw new IllegalStateException("JWT is in unsecured mode but token is signed.");
               }
            } else if (!this.allowEmbeddedKey && segments.length != 3) {
               throw new IllegalStateException("JWT is in secure mode but token is not signed.");
            }

            JsonObject payload = new JsonObject(Buffer.buffer(Codec.base64UrlDecode(payloadSeg)));
            String alg = header.getString("alg");
            if (!unsecure && "none".equals(alg)) {
               throw new IllegalStateException("Algorithm \"none\" not allowed");
            } else if (this.allowEmbeddedKey && header.containsKey("x5c")) {
               if (signatureSeg == null) {
                  throw new IllegalStateException("missing signature segment");
               } else {
                  try {
                     JsonArray chain = header.getJsonArray("x5c");
                     List<X509Certificate> certChain = new ArrayList();
                     if (chain != null && chain.size() != 0) {
                        for(int i = 0; i < chain.size(); ++i) {
                           certChain.add(JWS.parseX5c(Codec.base64Decode(chain.getString(i))));
                        }

                        if (this.rootCA != null) {
                           certChain.add(this.rootCA);
                           CertificateHelper.checkValidity(certChain, true, crls);
                        } else {
                           CertificateHelper.checkValidity(certChain, false, crls);
                        }

                        if (JWS.verifySignature(alg, (X509Certificate)certChain.get(0), Codec.base64UrlDecode(signatureSeg), (headerSeg + "." + payloadSeg).getBytes(UTF8))) {
                           return full ? (new JsonObject()).put("header", header).put("payload", payload) : payload;
                        } else {
                           throw new RuntimeException("Signature verification failed");
                        }
                     } else {
                        throw new IllegalStateException("x5c chain is null or empty");
                     }
                  } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | InvalidAlgorithmParameterException | NoSuchProviderException | CertificateException e) {
                     throw new RuntimeException("Signature verification failed", e);
                  }
               }
            } else if (unsecure) {
               return full ? (new JsonObject()).put("header", header).put("payload", payload) : payload;
            } else {
               List<JWS> signatures = (List)this.VERIFY.get(alg);
               if (signatures != null && signatures.size() != 0) {
                  if (signatureSeg == null) {
                     throw new IllegalStateException("missing signature segment");
                  } else {
                     byte[] payloadInput = Codec.base64UrlDecode(signatureSeg);
                     if (this.nonceDigest != null && header.containsKey("nonce")) {
                        synchronized(this) {
                           this.nonceDigest.reset();
                           header.put("nonce", Codec.base64UrlEncode(this.nonceDigest.digest(header.getString("nonce").getBytes(StandardCharsets.UTF_8))));
                           headerSeg = Codec.base64UrlEncode(header.encode().getBytes(StandardCharsets.UTF_8));
                        }
                     }

                     byte[] signingInput = (headerSeg + "." + payloadSeg).getBytes(UTF8);
                     String kid = header.getString("kid");
                     boolean hasKey = false;

                     for(JWS jws : signatures) {
                        if (kid == null || jws.jwk().getId() == null || kid.equals(jws.jwk().getId())) {
                           hasKey = true;
                           if (jws.verify(payloadInput, signingInput)) {
                              return full ? (new JsonObject()).put("header", header).put("payload", payload) : payload;
                           }
                        }
                     }

                     if (hasKey) {
                        throw new RuntimeException("Signature verification failed");
                     } else {
                        throw new NoSuchKeyIdException(alg, kid);
                     }
                  }
               } else {
                  throw new NoSuchKeyIdException(alg);
               }
            }
         }
      }
   }

   public String sign(JsonObject payload, JWTOptions options) {
      boolean unsecure = this.isUnsecure();
      String algorithm = options.getAlgorithm();
      if (!unsecure && "none".equals(algorithm)) {
         throw new IllegalStateException("Algorithm \"none\" not allowed");
      } else {
         JWS jws;
         String kid;
         if (!unsecure) {
            List<JWS> signatures = (List)this.SIGN.get(algorithm);
            if (signatures == null || signatures.size() == 0) {
               throw new RuntimeException("Algorithm not supported/allowed: " + algorithm);
            }

            jws = (JWS)signatures.get(signatures.size() == 1 ? 0 : RND.nextInt(signatures.size()));
            kid = jws.jwk().getId();
         } else {
            jws = null;
            kid = null;
         }

         JsonObject header = (new JsonObject()).mergeIn(options.getHeader()).put("typ", "JWT").put("alg", algorithm);
         if (kid != null) {
            header.put("kid", kid);
         }

         long timestamp = System.currentTimeMillis() / 1000L;
         if (!options.isNoTimestamp()) {
            payload.put("iat", payload.getValue("iat", timestamp));
         }

         if (options.getExpiresInSeconds() > 0) {
            payload.put("exp", timestamp + (long)options.getExpiresInSeconds());
         }

         if (options.getAudience() != null && options.getAudience().size() >= 1) {
            if (options.getAudience().size() > 1) {
               payload.put("aud", new JsonArray(options.getAudience()));
            } else {
               payload.put("aud", options.getAudience().get(0));
            }
         }

         if (options.getIssuer() != null) {
            payload.put("iss", options.getIssuer());
         }

         if (options.getSubject() != null) {
            payload.put("sub", options.getSubject());
         }

         String headerSegment = Codec.base64UrlEncode(header.encode().getBytes(StandardCharsets.UTF_8));
         String payloadSegment = Codec.base64UrlEncode(payload.encode().getBytes(StandardCharsets.UTF_8));
         if (!unsecure) {
            String signingInput = headerSegment + "." + payloadSegment;
            String signSegment = Codec.base64UrlEncode(jws.sign(signingInput.getBytes(UTF8)));
            return headerSegment + "." + payloadSegment + "." + signSegment;
         } else {
            return headerSegment + "." + payloadSegment;
         }
      }
   }

   public boolean isUnsecure() {
      return this.VERIFY.size() == 0 && this.SIGN.size() == 0;
   }

   public Collection availableAlgorithms() {
      Set<String> algorithms = new HashSet();
      algorithms.add("none");
      algorithms.addAll(this.VERIFY.keySet());
      algorithms.addAll(this.SIGN.keySet());
      return algorithms;
   }

   static {
      UTF8 = StandardCharsets.UTF_8;
   }
}
