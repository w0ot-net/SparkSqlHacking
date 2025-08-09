package io.vertx.ext.auth.impl.jose;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.impl.CertificateHelper;
import io.vertx.ext.auth.impl.Codec;
import io.vertx.ext.auth.impl.asn.ASN1;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class JWK {
   private static final Logger LOG = LoggerFactory.getLogger(JWK.class);
   private static final Map ALG_ALIAS = new HashMap() {
      {
         this.put("HS256", Arrays.asList("HMacSHA256", "1.2.840.113549.2.9"));
         this.put("HS384", Arrays.asList("HMacSHA384", "1.2.840.113549.2.10"));
         this.put("HS512", Arrays.asList("HMacSHA512", "1.2.840.113549.2.11"));
         this.put("RS256", Arrays.asList("SHA256withRSA", "1.2.840.113549.1.1.11"));
         this.put("RS384", Arrays.asList("SHA384withRSA", "1.2.840.113549.1.1.12"));
         this.put("RS512", Arrays.asList("SHA512withRSA", "1.2.840.113549.1.1.13"));
         this.put("ES256K", Collections.singletonList("SHA256withECDSA"));
         this.put("ES256", Arrays.asList("SHA256withECDSA", "1.2.840.10045.4.3.2"));
         this.put("ES384", Arrays.asList("SHA384withECDSA", "1.2.840.10045.4.3.3"));
         this.put("ES512", Arrays.asList("SHA512withECDSA", "1.2.840.10045.4.3.4"));
      }
   };
   private final String kid;
   private final String alg;
   private final String kty;
   private final String use;
   private final String label;
   private PrivateKey privateKey;
   private PublicKey publicKey;
   private Mac mac;

   private static boolean invalidAlgAlias(String alg, String alias) {
      for(String expected : (List)ALG_ALIAS.get(alias)) {
         if (alg.equalsIgnoreCase(expected)) {
            return false;
         }
      }

      return true;
   }

   public static List load(KeyStore keyStore, String keyStorePassword, Map passwordProtection) {
      List<JWK> keys = new ArrayList();

      for(String alias : Arrays.asList("HS256", "HS384", "HS512")) {
         try {
            char[] password = password(keyStorePassword, passwordProtection, alias);
            Key secretKey = keyStore.getKey(alias, password);
            if (secretKey != null) {
               String alg = secretKey.getAlgorithm();
               if (invalidAlgAlias(alg, alias)) {
                  LOG.warn("The key algorithm does not match: {" + alias + ": " + alg + "}");
               } else {
                  Mac mac = Mac.getInstance(alg);
                  mac.init(secretKey);
                  keys.add(new JWK(alias, mac));
               }
            }
         } catch (NoSuchAlgorithmException | UnrecoverableKeyException | InvalidKeyException | KeyStoreException e) {
            LOG.warn("Failed to load key for algorithm: " + alias, e);
         }
      }

      for(String alias : Arrays.asList("RS256", "RS384", "RS512", "ES256K", "ES256", "ES384", "ES512")) {
         try {
            X509Certificate certificate = (X509Certificate)keyStore.getCertificate(alias);
            if (certificate != null) {
               certificate.checkValidity();
               String alg = certificate.getSigAlgName();
               if (invalidAlgAlias(alg, alias)) {
                  LOG.warn("The key algorithm does not match: {" + alias + ": " + alg + "}");
               } else {
                  char[] password = password(keyStorePassword, passwordProtection, alias);
                  PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias, password);
                  keys.add(new JWK(alias, certificate, privateKey));
               }
            }
         } catch (KeyStoreException | CertificateExpiredException | CertificateNotYetValidException | NoSuchAlgorithmException | UnrecoverableKeyException | ClassCastException e) {
            LOG.warn("Failed to load key for algorithm: " + alias, e);
         }
      }

      return keys;
   }

   private static char[] password(String keyStorePassword, Map passwordProtection, String alias) {
      String password;
      if (passwordProtection == null || (password = (String)passwordProtection.get(alias)) == null) {
         password = keyStorePassword;
      }

      return password.toCharArray();
   }

   public JWK(PubSecKeyOptions options) {
      this.alg = options.getAlgorithm();
      this.kid = options.getId();
      this.use = null;
      Buffer buffer = (Buffer)Objects.requireNonNull(options.getBuffer());
      this.label = this.kid == null ? this.alg + "#" + buffer.hashCode() : this.kid;
      switch (this.alg) {
         case "HS256":
            try {
               this.mac = Mac.getInstance("HMacSHA256");
               this.mac.init(new SecretKeySpec(buffer.getBytes(), "HMacSHA256"));
            } catch (InvalidKeyException | NoSuchAlgorithmException e) {
               throw new RuntimeException(e);
            }

            this.kty = "oct";
            return;
         case "HS384":
            try {
               this.mac = Mac.getInstance("HMacSHA384");
               this.mac.init(new SecretKeySpec(buffer.getBytes(), "HMacSHA384"));
            } catch (InvalidKeyException | NoSuchAlgorithmException e) {
               throw new RuntimeException(e);
            }

            this.kty = "oct";
            return;
         case "HS512":
            try {
               this.mac = Mac.getInstance("HMacSHA512");
               this.mac.init(new SecretKeySpec(buffer.getBytes(), "HMacSHA512"));
            } catch (InvalidKeyException | NoSuchAlgorithmException e) {
               throw new RuntimeException(e);
            }

            this.kty = "oct";
            return;
         default:
            try {
               switch (this.alg) {
                  case "RS256":
                  case "RS384":
                  case "RS512":
                     this.kty = "RSA";
                     this.parsePEM(KeyFactory.getInstance("RSA"), buffer.toString(StandardCharsets.US_ASCII));
                     break;
                  case "PS256":
                  case "PS384":
                  case "PS512":
                     this.kty = "RSASSA";
                     this.parsePEM(KeyFactory.getInstance("RSA"), buffer.toString(StandardCharsets.US_ASCII));
                     break;
                  case "ES256":
                  case "ES384":
                  case "ES512":
                  case "ES256K":
                     this.kty = "EC";
                     this.parsePEM(KeyFactory.getInstance("EC"), buffer.toString(StandardCharsets.US_ASCII));
                     break;
                  case "EdDSA":
                     this.kty = "EdDSA";
                     this.parsePEM(KeyFactory.getInstance("EdDSA"), buffer.toString(StandardCharsets.US_ASCII));
                     break;
                  default:
                     throw new IllegalArgumentException("Unknown algorithm: " + this.alg);
               }

            } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException e) {
               throw new RuntimeException(e);
            }
      }
   }

   private void parsePEM(KeyFactory kf, String pem) throws CertificateException, InvalidKeySpecException {
      String[] lines = pem.split("\r?\n");
      if (lines.length <= 2) {
         throw new IllegalArgumentException("PEM contains not enough lines");
      } else {
         Pattern begin = Pattern.compile("-----BEGIN (.+?)-----");
         Pattern end = Pattern.compile("-----END (.+?)-----");
         Matcher beginMatcher = begin.matcher(lines[0]);
         if (!beginMatcher.matches()) {
            throw new IllegalArgumentException("PEM first line does not match a BEGIN line");
         } else {
            String kind = beginMatcher.group(1);
            Buffer buffer = Buffer.buffer();
            boolean endSeen = false;

            for(int i = 1; i < lines.length; ++i) {
               if (!"".equals(lines[i])) {
                  Matcher endMatcher = end.matcher(lines[i]);
                  if (endMatcher.matches()) {
                     endSeen = true;
                     if (!kind.equals(endMatcher.group(1))) {
                        throw new IllegalArgumentException("PEM END line does not match start");
                     }
                     break;
                  }

                  buffer.appendString(lines[i]);
               }
            }

            if (!endSeen) {
               throw new IllegalArgumentException("PEM END line not found");
            } else {
               switch (kind) {
                  case "CERTIFICATE":
                     CertificateFactory cf = CertificateFactory.getInstance("X.509");
                     this.publicKey = cf.generateCertificate(new ByteArrayInputStream(pem.getBytes(StandardCharsets.US_ASCII))).getPublicKey();
                     return;
                  case "PUBLIC KEY":
                  case "PUBLIC RSA KEY":
                  case "RSA PUBLIC KEY":
                     this.publicKey = kf.generatePublic(new X509EncodedKeySpec(Codec.base64MimeDecode(buffer.getBytes())));
                     return;
                  case "PRIVATE KEY":
                  case "PRIVATE RSA KEY":
                  case "RSA PRIVATE KEY":
                     this.privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(Codec.base64MimeDecode(buffer.getBytes())));
                     return;
                  default:
                     throw new IllegalStateException("Invalid PEM content: " + kind);
               }
            }
         }
      }
   }

   private JWK(String algorithm, Mac mac) throws NoSuchAlgorithmException {
      this.alg = algorithm;
      this.kid = null;
      this.label = this.alg + "#" + mac.hashCode();
      this.use = null;
      switch (this.alg) {
         case "HS256":
         case "HS384":
         case "HS512":
            this.kty = "oct";
            this.mac = mac;
            return;
         default:
            throw new NoSuchAlgorithmException("Unknown algorithm: " + algorithm);
      }
   }

   private JWK(String algorithm, X509Certificate certificate, PrivateKey privateKey) throws NoSuchAlgorithmException {
      this.alg = algorithm;
      this.kid = null;
      this.label = privateKey != null ? algorithm + '#' + certificate.hashCode() + "-" + privateKey.hashCode() : algorithm + '#' + certificate.hashCode();
      this.use = null;
      this.publicKey = certificate.getPublicKey();
      this.privateKey = privateKey;
      switch (algorithm) {
         case "RS256":
         case "RS384":
         case "RS512":
            this.kty = "RSA";
            break;
         case "PS256":
         case "PS384":
         case "PS512":
            this.kty = "RSASSA";
            break;
         case "ES256":
         case "ES384":
         case "ES512":
         case "ES256K":
            this.kty = "EC";
            break;
         default:
            throw new NoSuchAlgorithmException("Unknown algorithm: " + algorithm);
      }

   }

   public JWK(JsonObject json) {
      this.kid = json.getString("kid");
      this.use = json.getString("use");

      try {
         label104:
         switch (json.getString("kty")) {
            case "RSA":
            case "RSASSA":
               this.kty = json.getString("kty");
               this.alg = json.getString("alg", "RS256");
               switch (this.alg) {
                  case "RS1":
                  case "RS256":
                  case "RS384":
                  case "RS512":
                  case "PS256":
                  case "PS384":
                  case "PS512":
                     this.createRSA(json);
                     break label104;
                  default:
                     throw new NoSuchAlgorithmException(this.alg);
               }
            case "EC":
               this.kty = json.getString("kty");
               this.alg = json.getString("alg", "ES256");
               switch (this.alg) {
                  case "ES256":
                  case "ES256K":
                  case "ES512":
                  case "ES384":
                     this.createEC(json);
                     break label104;
                  default:
                     throw new NoSuchAlgorithmException(this.alg);
               }
            case "OKP":
               this.kty = json.getString("kty");
               this.alg = json.getString("alg", "EdDSA");
               this.createOKP(json);
               break;
            case "oct":
               this.kty = json.getString("kty");
               this.alg = json.getString("alg", "HS256");
               switch (this.alg) {
                  case "HS256":
                     this.createOCT("HMacSHA256", json);
                     break label104;
                  case "HS384":
                     this.createOCT("HMacSHA384", json);
                     break label104;
                  case "HS512":
                     this.createOCT("HMacSHA512", json);
                     break label104;
                  default:
                     throw new NoSuchAlgorithmException(this.alg);
               }
            default:
               throw new RuntimeException("Unsupported key type: " + json.getString("kty"));
         }

         this.label = this.kid != null ? this.kid : this.alg + "#" + json.hashCode();
      } catch (InvalidKeyException | InvalidKeySpecException | InvalidParameterSpecException | CertificateException | NoSuchProviderException | SignatureException | NoSuchAlgorithmException e) {
         throw new RuntimeException(e);
      }
   }

   private void createRSA(JsonObject json) throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, InvalidKeyException, NoSuchProviderException, SignatureException {
      if (jsonHasProperties(json, "n", "e")) {
         BigInteger n = new BigInteger(1, Codec.base64UrlDecode(json.getString("n")));
         BigInteger e = new BigInteger(1, Codec.base64UrlDecode(json.getString("e")));
         this.publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(n, e));
         if (jsonHasProperties(json, "d", "p", "q", "dp", "dq", "qi")) {
            BigInteger d = new BigInteger(1, Codec.base64UrlDecode(json.getString("d")));
            BigInteger p = new BigInteger(1, Codec.base64UrlDecode(json.getString("p")));
            BigInteger q = new BigInteger(1, Codec.base64UrlDecode(json.getString("q")));
            BigInteger dp = new BigInteger(1, Codec.base64UrlDecode(json.getString("dp")));
            BigInteger dq = new BigInteger(1, Codec.base64UrlDecode(json.getString("dq")));
            BigInteger qi = new BigInteger(1, Codec.base64UrlDecode(json.getString("qi")));
            this.privateKey = KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(n, e, d, p, q, dp, dq, qi));
         }
      }

      if (json.containsKey("x5c")) {
         JsonArray x5c = json.getJsonArray("x5c");
         List<X509Certificate> certChain = new ArrayList();

         for(int i = 0; i < x5c.size(); ++i) {
            certChain.add(JWS.parseX5c(x5c.getString(i)));
         }

         CertificateHelper.checkValidity(certChain, false, (List)null);
         X509Certificate certificate = (X509Certificate)certChain.get(0);
         this.publicKey = certificate.getPublicKey();
      }

   }

   private void createEC(JsonObject json) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidParameterSpecException {
      AlgorithmParameters parameters = AlgorithmParameters.getInstance("EC");
      parameters.init(new ECGenParameterSpec(translateECCrv(json.getString("crv"))));
      if (jsonHasProperties(json, "x", "y")) {
         BigInteger x = new BigInteger(1, Codec.base64UrlDecode(json.getString("x")));
         BigInteger y = new BigInteger(1, Codec.base64UrlDecode(json.getString("y")));
         this.publicKey = KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(new ECPoint(x, y), (ECParameterSpec)parameters.getParameterSpec(ECParameterSpec.class)));
      }

      if (jsonHasProperties(json, "d")) {
         BigInteger d = new BigInteger(1, Codec.base64UrlDecode(json.getString("d")));
         this.privateKey = KeyFactory.getInstance("EC").generatePrivate(new ECPrivateKeySpec(d, (ECParameterSpec)parameters.getParameterSpec(ECParameterSpec.class)));
      }

   }

   private void createOKP(JsonObject json) throws NoSuchAlgorithmException, InvalidKeySpecException {
      if (jsonHasProperties(json, "x")) {
         byte[] key = Codec.base64UrlDecode(json.getString("x"));
         byte bitStringTag = 3;
         byte[] spki = ASN1.sequence(Buffer.buffer().appendBytes(ASN1.sequence(oidCrv(json.getString("crv")))).appendByte((byte)3).appendBytes(ASN1.length(key.length + 1)).appendByte((byte)0).appendBytes(key).getBytes());
         this.publicKey = KeyFactory.getInstance("EdDSA").generatePublic(new X509EncodedKeySpec(spki));
      }

      if (jsonHasProperties(json, "d")) {
         byte[] key = Codec.base64UrlDecode(json.getString("d"));
         byte octetStringTag = 4;
         byte[] asnKey = Buffer.buffer().appendByte((byte)4).appendBytes(ASN1.length(key.length)).appendBytes(key).getBytes();
         byte[] pkcs8 = ASN1.sequence(Buffer.buffer().appendBytes(new byte[]{2, 1, 0}).appendBytes(ASN1.sequence(oidCrv(json.getString("crv")))).appendByte((byte)4).appendBytes(ASN1.length(asnKey.length)).appendBytes(asnKey).getBytes());
         this.privateKey = KeyFactory.getInstance("EdDSA").generatePrivate(new PKCS8EncodedKeySpec(pkcs8));
      }

   }

   private void createOCT(String alias, JsonObject json) throws NoSuchAlgorithmException, InvalidKeyException {
      this.mac = Mac.getInstance(alias);
      this.mac.init(new SecretKeySpec(Codec.base64UrlDecode(json.getString("k")), alias));
   }

   public String getAlgorithm() {
      return this.alg;
   }

   public String getId() {
      return this.kid;
   }

   private static String translateECCrv(String crv) {
      switch (crv) {
         case "P-256":
            return "secp256r1";
         case "P-384":
            return "secp384r1";
         case "P-521":
            return "secp521r1";
         case "secp256k1":
            return "secp256k1";
         default:
            throw new IllegalArgumentException("Unsupported {crv}: " + crv);
      }
   }

   private static byte[] oidCrv(String crv) {
      switch (crv) {
         case "Ed25519":
            return new byte[]{6, 3, 43, 101, 112};
         case "Ed448":
            return new byte[]{6, 3, 43, 101, 113};
         case "X25519":
            return new byte[]{6, 3, 43, 101, 110};
         case "X448":
            return new byte[]{6, 3, 43, 101, 111};
         default:
            throw new IllegalArgumentException("Unsupported {crv}: " + crv);
      }
   }

   private static boolean jsonHasProperties(JsonObject json, String... properties) {
      for(String property : properties) {
         if (!json.containsKey(property) || json.getValue(property) == null) {
            return false;
         }
      }

      return true;
   }

   public String use() {
      return this.use;
   }

   public String label() {
      return this.label;
   }

   public String kty() {
      return this.kty;
   }

   public Mac mac() {
      return this.mac;
   }

   public PublicKey publicKey() {
      return this.publicKey;
   }

   public PrivateKey privateKey() {
      return this.privateKey;
   }
}
