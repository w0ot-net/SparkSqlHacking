package io.jsonwebtoken;

import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.crypto.SecretKey;

/** @deprecated */
@Deprecated
public enum SignatureAlgorithm {
   NONE("none", "No digital signature or MAC performed", "None", (String)null, false, 0, 0),
   HS256("HS256", "HMAC using SHA-256", "HMAC", "HmacSHA256", true, 256, 256, "1.2.840.113549.2.9"),
   HS384("HS384", "HMAC using SHA-384", "HMAC", "HmacSHA384", true, 384, 384, "1.2.840.113549.2.10"),
   HS512("HS512", "HMAC using SHA-512", "HMAC", "HmacSHA512", true, 512, 512, "1.2.840.113549.2.11"),
   RS256("RS256", "RSASSA-PKCS-v1_5 using SHA-256", "RSA", "SHA256withRSA", true, 256, 2048),
   RS384("RS384", "RSASSA-PKCS-v1_5 using SHA-384", "RSA", "SHA384withRSA", true, 384, 2048),
   RS512("RS512", "RSASSA-PKCS-v1_5 using SHA-512", "RSA", "SHA512withRSA", true, 512, 2048),
   ES256("ES256", "ECDSA using P-256 and SHA-256", "ECDSA", "SHA256withECDSA", true, 256, 256),
   ES384("ES384", "ECDSA using P-384 and SHA-384", "ECDSA", "SHA384withECDSA", true, 384, 384),
   ES512("ES512", "ECDSA using P-521 and SHA-512", "ECDSA", "SHA512withECDSA", true, 512, 521),
   PS256("PS256", "RSASSA-PSS using SHA-256 and MGF1 with SHA-256", "RSA", "RSASSA-PSS", false, 256, 2048),
   PS384("PS384", "RSASSA-PSS using SHA-384 and MGF1 with SHA-384", "RSA", "RSASSA-PSS", false, 384, 2048),
   PS512("PS512", "RSASSA-PSS using SHA-512 and MGF1 with SHA-512", "RSA", "RSASSA-PSS", false, 512, 2048);

   private static final List PREFERRED_HMAC_ALGS = Collections.unmodifiableList(Arrays.asList(HS512, HS384, HS256));
   private static final List PREFERRED_EC_ALGS = Collections.unmodifiableList(Arrays.asList(ES512, ES384, ES256));
   private final String value;
   private final String description;
   private final String familyName;
   private final String jcaName;
   private final boolean jdkStandard;
   private final int digestLength;
   private final int minKeyLength;
   /** @deprecated */
   @Deprecated
   private final String pkcs12Name;

   private SignatureAlgorithm(String value, String description, String familyName, String jcaName, boolean jdkStandard, int digestLength, int minKeyLength) {
      this(value, description, familyName, jcaName, jdkStandard, digestLength, minKeyLength, jcaName);
   }

   private SignatureAlgorithm(String value, String description, String familyName, String jcaName, boolean jdkStandard, int digestLength, int minKeyLength, String pkcs12Name) {
      this.value = value;
      this.description = description;
      this.familyName = familyName;
      this.jcaName = jcaName;
      this.jdkStandard = jdkStandard;
      this.digestLength = digestLength;
      this.minKeyLength = minKeyLength;
      this.pkcs12Name = pkcs12Name;
   }

   public String getValue() {
      return this.value;
   }

   public String getDescription() {
      return this.description;
   }

   public String getFamilyName() {
      return this.familyName;
   }

   public String getJcaName() {
      return this.jcaName;
   }

   public boolean isJdkStandard() {
      return this.jdkStandard;
   }

   public boolean isHmac() {
      return this.familyName.equals("HMAC");
   }

   public boolean isRsa() {
      return this.familyName.equals("RSA");
   }

   public boolean isEllipticCurve() {
      return this.familyName.equals("ECDSA");
   }

   public int getMinKeyLength() {
      return this.minKeyLength;
   }

   public void assertValidSigningKey(Key key) throws InvalidKeyException {
      this.assertValid(key, true);
   }

   public void assertValidVerificationKey(Key key) throws InvalidKeyException {
      this.assertValid(key, false);
   }

   private static String keyType(boolean signing) {
      return signing ? "signing" : "verification";
   }

   private void assertValid(Key key, boolean signing) throws InvalidKeyException {
      if (this == NONE) {
         String msg = "The 'NONE' signature algorithm does not support cryptographic keys.";
         throw new InvalidKeyException(msg);
      } else {
         if (this.isHmac()) {
            if (!(key instanceof SecretKey)) {
               String msg = this.familyName + " " + keyType(signing) + " keys must be SecretKey instances.";
               throw new InvalidKeyException(msg);
            }

            SecretKey secretKey = (SecretKey)key;
            byte[] encoded = secretKey.getEncoded();
            if (encoded == null) {
               throw new InvalidKeyException("The " + keyType(signing) + " key's encoded bytes cannot be null.");
            }

            String alg = secretKey.getAlgorithm();
            if (alg == null) {
               throw new InvalidKeyException("The " + keyType(signing) + " key's algorithm cannot be null.");
            }

            if (!HS256.jcaName.equalsIgnoreCase(alg) && !HS384.jcaName.equalsIgnoreCase(alg) && !HS512.jcaName.equalsIgnoreCase(alg) && !HS256.pkcs12Name.equals(alg) && !HS384.pkcs12Name.equals(alg) && !HS512.pkcs12Name.equals(alg)) {
               throw new InvalidKeyException("The " + keyType(signing) + " key's algorithm '" + alg + "' does not equal a valid HmacSHA* algorithm name and cannot be used with " + this.name() + ".");
            }

            int size = encoded.length * 8;
            if (size < this.minKeyLength) {
               String msg = "The " + keyType(signing) + " key's size is " + size + " bits which " + "is not secure enough for the " + this.name() + " algorithm.  The JWT " + "JWA Specification (RFC 7518, Section 3.2) states that keys used with " + this.name() + " MUST have a " + "size >= " + this.minKeyLength + " bits (the key size must be greater than or equal to the hash " + "output size).  Consider using the " + Keys.class.getName() + " class's " + "'secretKeyFor(SignatureAlgorithm." + this.name() + ")' method to create a key guaranteed to be " + "secure enough for " + this.name() + ".  See " + "https://tools.ietf.org/html/rfc7518#section-3.2 for more information.";
               throw new WeakKeyException(msg);
            }
         } else {
            if (signing && !(key instanceof PrivateKey)) {
               String msg = this.familyName + " signing keys must be PrivateKey instances.";
               throw new InvalidKeyException(msg);
            }

            if (this.isEllipticCurve()) {
               if (!(key instanceof ECKey)) {
                  String msg = this.familyName + " " + keyType(signing) + " keys must be ECKey instances.";
                  throw new InvalidKeyException(msg);
               }

               ECKey ecKey = (ECKey)key;
               int size = ecKey.getParams().getOrder().bitLength();
               if (size < this.minKeyLength) {
                  String msg = "The " + keyType(signing) + " key's size (ECParameterSpec order) is " + size + " bits which is not secure enough for the " + this.name() + " algorithm.  The JWT " + "JWA Specification (RFC 7518, Section 3.4) states that keys used with " + this.name() + " MUST have a size >= " + this.minKeyLength + " bits.  Consider using the " + Keys.class.getName() + " class's " + "'keyPairFor(SignatureAlgorithm." + this.name() + ")' method to create a key pair guaranteed " + "to be secure enough for " + this.name() + ".  See " + "https://tools.ietf.org/html/rfc7518#section-3.4 for more information.";
                  throw new WeakKeyException(msg);
               }
            } else {
               if (!(key instanceof RSAKey)) {
                  String msg = this.familyName + " " + keyType(signing) + " keys must be RSAKey instances.";
                  throw new InvalidKeyException(msg);
               }

               RSAKey rsaKey = (RSAKey)key;
               int size = rsaKey.getModulus().bitLength();
               if (size < this.minKeyLength) {
                  String section = this.name().startsWith("P") ? "3.5" : "3.3";
                  String msg = "The " + keyType(signing) + " key's size is " + size + " bits which is not secure " + "enough for the " + this.name() + " algorithm.  The JWT JWA Specification (RFC 7518, Section " + section + ") states that keys used with " + this.name() + " MUST have a size >= " + this.minKeyLength + " bits.  Consider using the " + Keys.class.getName() + " class's " + "'keyPairFor(SignatureAlgorithm." + this.name() + ")' method to create a key pair guaranteed " + "to be secure enough for " + this.name() + ".  See " + "https://tools.ietf.org/html/rfc7518#section-" + section + " for more information.";
                  throw new WeakKeyException(msg);
               }
            }
         }

      }
   }

   public static SignatureAlgorithm forSigningKey(Key key) throws InvalidKeyException {
      if (key == null) {
         throw new InvalidKeyException("Key argument cannot be null.");
      } else if (key instanceof SecretKey || key instanceof PrivateKey && (key instanceof ECKey || key instanceof RSAKey)) {
         if (key instanceof SecretKey) {
            SecretKey secretKey = (SecretKey)key;
            int bitLength = io.jsonwebtoken.lang.Arrays.length(secretKey.getEncoded()) * 8;

            for(SignatureAlgorithm alg : PREFERRED_HMAC_ALGS) {
               if (bitLength >= alg.minKeyLength) {
                  return alg;
               }
            }

            String msg = "The specified SecretKey is not strong enough to be used with JWT HMAC signature algorithms.  The JWT specification requires HMAC keys to be >= 256 bits long.  The specified key is " + bitLength + " bits.  See https://tools.ietf.org/html/rfc7518#section-3.2 for more " + "information.";
            throw new WeakKeyException(msg);
         } else if (key instanceof RSAKey) {
            RSAKey rsaKey = (RSAKey)key;
            int bitLength = rsaKey.getModulus().bitLength();
            if (bitLength >= 4096) {
               RS512.assertValidSigningKey(key);
               return RS512;
            } else if (bitLength >= 3072) {
               RS384.assertValidSigningKey(key);
               return RS384;
            } else if (bitLength >= RS256.minKeyLength) {
               RS256.assertValidSigningKey(key);
               return RS256;
            } else {
               String msg = "The specified RSA signing key is not strong enough to be used with JWT RSA signature algorithms.  The JWT specification requires RSA keys to be >= 2048 bits long.  The specified RSA key is " + bitLength + " bits.  See https://tools.ietf.org/html/rfc7518#section-3.3 for more " + "information.";
               throw new WeakKeyException(msg);
            }
         } else {
            ECKey ecKey = (ECKey)key;
            int bitLength = ecKey.getParams().getOrder().bitLength();

            for(SignatureAlgorithm alg : PREFERRED_EC_ALGS) {
               if (bitLength >= alg.minKeyLength) {
                  alg.assertValidSigningKey(key);
                  return alg;
               }
            }

            String msg = "The specified Elliptic Curve signing key is not strong enough to be used with JWT ECDSA signature algorithms.  The JWT specification requires ECDSA keys to be >= 256 bits long.  The specified ECDSA key is " + bitLength + " bits.  See " + "https://tools.ietf.org/html/rfc7518#section-3.4 for more information.";
            throw new WeakKeyException(msg);
         }
      } else {
         String msg = "JWT standard signing algorithms require either 1) a SecretKey for HMAC-SHA algorithms or 2) a private RSAKey for RSA algorithms or 3) a private ECKey for Elliptic Curve algorithms.  The specified key is of type " + key.getClass().getName();
         throw new InvalidKeyException(msg);
      }
   }

   public static SignatureAlgorithm forName(String value) throws io.jsonwebtoken.security.SignatureException {
      for(SignatureAlgorithm alg : values()) {
         if (alg.getValue().equalsIgnoreCase(value)) {
            return alg;
         }
      }

      throw new io.jsonwebtoken.security.SignatureException("Unsupported signature algorithm '" + value + "'");
   }
}
