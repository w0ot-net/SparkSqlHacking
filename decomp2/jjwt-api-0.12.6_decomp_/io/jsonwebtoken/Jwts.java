package io.jsonwebtoken;

import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.lang.Builder;
import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.SecretKeyAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.X509Builder;
import java.util.Map;

public final class Jwts {
   private static Object get(Registry registry, String id) {
      return registry.forKey(id);
   }

   public static HeaderBuilder header() {
      return (HeaderBuilder)Classes.newInstance("io.jsonwebtoken.impl.DefaultJwtHeaderBuilder");
   }

   public static ClaimsBuilder claims() {
      return (ClaimsBuilder)Classes.newInstance("io.jsonwebtoken.impl.DefaultClaimsBuilder");
   }

   /** @deprecated */
   @Deprecated
   public static Claims claims(Map claims) {
      return (Claims)((ClaimsBuilder)claims().add(claims)).build();
   }

   public static JwtBuilder builder() {
      return (JwtBuilder)Classes.newInstance("io.jsonwebtoken.impl.DefaultJwtBuilder");
   }

   public static JwtParserBuilder parser() {
      return (JwtParserBuilder)Classes.newInstance("io.jsonwebtoken.impl.DefaultJwtParserBuilder");
   }

   private Jwts() {
   }

   public static final class ENC {
      private static final String IMPL_CLASSNAME = "io.jsonwebtoken.impl.security.StandardEncryptionAlgorithms";
      private static final Registry REGISTRY = (Registry)Classes.newInstance("io.jsonwebtoken.impl.security.StandardEncryptionAlgorithms");
      public static final AeadAlgorithm A128CBC_HS256 = (AeadAlgorithm)get().forKey("A128CBC-HS256");
      public static final AeadAlgorithm A192CBC_HS384 = (AeadAlgorithm)get().forKey("A192CBC-HS384");
      public static final AeadAlgorithm A256CBC_HS512 = (AeadAlgorithm)get().forKey("A256CBC-HS512");
      public static final AeadAlgorithm A128GCM = (AeadAlgorithm)get().forKey("A128GCM");
      public static final AeadAlgorithm A192GCM = (AeadAlgorithm)get().forKey("A192GCM");
      public static final AeadAlgorithm A256GCM = (AeadAlgorithm)get().forKey("A256GCM");

      public static Registry get() {
         return REGISTRY;
      }

      private ENC() {
      }
   }

   public static final class SIG {
      private static final String IMPL_CLASSNAME = "io.jsonwebtoken.impl.security.StandardSecureDigestAlgorithms";
      private static final Registry REGISTRY = (Registry)Classes.newInstance("io.jsonwebtoken.impl.security.StandardSecureDigestAlgorithms");
      public static final SecureDigestAlgorithm NONE;
      public static final MacAlgorithm HS256;
      public static final MacAlgorithm HS384;
      public static final MacAlgorithm HS512;
      public static final io.jsonwebtoken.security.SignatureAlgorithm RS256;
      public static final io.jsonwebtoken.security.SignatureAlgorithm RS384;
      public static final io.jsonwebtoken.security.SignatureAlgorithm RS512;
      public static final io.jsonwebtoken.security.SignatureAlgorithm PS256;
      public static final io.jsonwebtoken.security.SignatureAlgorithm PS384;
      public static final io.jsonwebtoken.security.SignatureAlgorithm PS512;
      public static final io.jsonwebtoken.security.SignatureAlgorithm ES256;
      public static final io.jsonwebtoken.security.SignatureAlgorithm ES384;
      public static final io.jsonwebtoken.security.SignatureAlgorithm ES512;
      public static final io.jsonwebtoken.security.SignatureAlgorithm EdDSA;

      private SIG() {
      }

      public static Registry get() {
         return REGISTRY;
      }

      static {
         NONE = (SecureDigestAlgorithm)Jwts.get(REGISTRY, "none");
         HS256 = (MacAlgorithm)Jwts.get(REGISTRY, "HS256");
         HS384 = (MacAlgorithm)Jwts.get(REGISTRY, "HS384");
         HS512 = (MacAlgorithm)Jwts.get(REGISTRY, "HS512");
         RS256 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "RS256");
         RS384 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "RS384");
         RS512 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "RS512");
         PS256 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "PS256");
         PS384 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "PS384");
         PS512 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "PS512");
         ES256 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "ES256");
         ES384 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "ES384");
         ES512 = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "ES512");
         EdDSA = (io.jsonwebtoken.security.SignatureAlgorithm)Jwts.get(REGISTRY, "EdDSA");
      }
   }

   public static final class KEY {
      private static final String IMPL_CLASSNAME = "io.jsonwebtoken.impl.security.StandardKeyAlgorithms";
      private static final Registry REGISTRY = (Registry)Classes.newInstance("io.jsonwebtoken.impl.security.StandardKeyAlgorithms");
      public static final KeyAlgorithm DIRECT;
      public static final SecretKeyAlgorithm A128KW;
      public static final SecretKeyAlgorithm A192KW;
      public static final SecretKeyAlgorithm A256KW;
      public static final SecretKeyAlgorithm A128GCMKW;
      public static final SecretKeyAlgorithm A192GCMKW;
      public static final SecretKeyAlgorithm A256GCMKW;
      public static final KeyAlgorithm PBES2_HS256_A128KW;
      public static final KeyAlgorithm PBES2_HS384_A192KW;
      public static final KeyAlgorithm PBES2_HS512_A256KW;
      public static final KeyAlgorithm RSA1_5;
      public static final KeyAlgorithm RSA_OAEP;
      public static final KeyAlgorithm RSA_OAEP_256;
      public static final KeyAlgorithm ECDH_ES;
      public static final KeyAlgorithm ECDH_ES_A128KW;
      public static final KeyAlgorithm ECDH_ES_A192KW;
      public static final KeyAlgorithm ECDH_ES_A256KW;

      public static Registry get() {
         return REGISTRY;
      }

      private KEY() {
      }

      static {
         DIRECT = (KeyAlgorithm)Jwts.get(REGISTRY, "dir");
         A128KW = (SecretKeyAlgorithm)Jwts.get(REGISTRY, "A128KW");
         A192KW = (SecretKeyAlgorithm)Jwts.get(REGISTRY, "A192KW");
         A256KW = (SecretKeyAlgorithm)Jwts.get(REGISTRY, "A256KW");
         A128GCMKW = (SecretKeyAlgorithm)Jwts.get(REGISTRY, "A128GCMKW");
         A192GCMKW = (SecretKeyAlgorithm)Jwts.get(REGISTRY, "A192GCMKW");
         A256GCMKW = (SecretKeyAlgorithm)Jwts.get(REGISTRY, "A256GCMKW");
         PBES2_HS256_A128KW = (KeyAlgorithm)Jwts.get(REGISTRY, "PBES2-HS256+A128KW");
         PBES2_HS384_A192KW = (KeyAlgorithm)Jwts.get(REGISTRY, "PBES2-HS384+A192KW");
         PBES2_HS512_A256KW = (KeyAlgorithm)Jwts.get(REGISTRY, "PBES2-HS512+A256KW");
         RSA1_5 = (KeyAlgorithm)Jwts.get(REGISTRY, "RSA1_5");
         RSA_OAEP = (KeyAlgorithm)Jwts.get(REGISTRY, "RSA-OAEP");
         RSA_OAEP_256 = (KeyAlgorithm)Jwts.get(REGISTRY, "RSA-OAEP-256");
         ECDH_ES = (KeyAlgorithm)Jwts.get(REGISTRY, "ECDH-ES");
         ECDH_ES_A128KW = (KeyAlgorithm)Jwts.get(REGISTRY, "ECDH-ES+A128KW");
         ECDH_ES_A192KW = (KeyAlgorithm)Jwts.get(REGISTRY, "ECDH-ES+A192KW");
         ECDH_ES_A256KW = (KeyAlgorithm)Jwts.get(REGISTRY, "ECDH-ES+A256KW");
      }
   }

   public static final class ZIP {
      private static final String IMPL_CLASSNAME = "io.jsonwebtoken.impl.io.StandardCompressionAlgorithms";
      private static final Registry REGISTRY = (Registry)Classes.newInstance("io.jsonwebtoken.impl.io.StandardCompressionAlgorithms");
      public static final CompressionAlgorithm DEF = (CompressionAlgorithm)get().forKey("DEF");
      public static final CompressionAlgorithm GZIP = (CompressionAlgorithm)get().forKey("GZIP");

      public static Registry get() {
         return REGISTRY;
      }

      private ZIP() {
      }
   }

   public interface HeaderBuilder extends JweHeaderMutator, X509Builder, Builder {
   }
}
