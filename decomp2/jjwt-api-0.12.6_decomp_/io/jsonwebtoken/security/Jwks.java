package io.jsonwebtoken.security;

import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.lang.Registry;

public final class Jwks {
   private static final String JWKS_BRIDGE_FQCN = "io.jsonwebtoken.impl.security.JwksBridge";
   private static final String BUILDER_FQCN = "io.jsonwebtoken.impl.security.DefaultDynamicJwkBuilder";
   private static final String PARSER_BUILDER_FQCN = "io.jsonwebtoken.impl.security.DefaultJwkParserBuilder";
   private static final String SET_BUILDER_FQCN = "io.jsonwebtoken.impl.security.DefaultJwkSetBuilder";
   private static final String SET_PARSER_BUILDER_FQCN = "io.jsonwebtoken.impl.security.DefaultJwkSetParserBuilder";

   private Jwks() {
   }

   public static DynamicJwkBuilder builder() {
      return (DynamicJwkBuilder)Classes.newInstance("io.jsonwebtoken.impl.security.DefaultDynamicJwkBuilder");
   }

   public static JwkParserBuilder parser() {
      return (JwkParserBuilder)Classes.newInstance("io.jsonwebtoken.impl.security.DefaultJwkParserBuilder");
   }

   public static JwkSetBuilder set() {
      return (JwkSetBuilder)Classes.newInstance("io.jsonwebtoken.impl.security.DefaultJwkSetBuilder");
   }

   public static JwkSetParserBuilder setParser() {
      return (JwkSetParserBuilder)Classes.newInstance("io.jsonwebtoken.impl.security.DefaultJwkSetParserBuilder");
   }

   public static String json(PublicJwk publicJwk) {
      return UNSAFE_JSON(publicJwk);
   }

   public static String UNSAFE_JSON(Jwk jwk) {
      return (String)Classes.invokeStatic("io.jsonwebtoken.impl.security.JwksBridge", "UNSAFE_JSON", new Class[]{Jwk.class}, jwk);
   }

   public static final class CRV {
      private static final String IMPL_CLASSNAME = "io.jsonwebtoken.impl.security.StandardCurves";
      private static final Registry REGISTRY = (Registry)Classes.newInstance("io.jsonwebtoken.impl.security.StandardCurves");
      public static final Curve P256 = (Curve)get().forKey("P-256");
      public static final Curve P384 = (Curve)get().forKey("P-384");
      public static final Curve P521 = (Curve)get().forKey("P-521");
      public static final Curve Ed25519 = (Curve)get().forKey("Ed25519");
      public static final Curve Ed448 = (Curve)get().forKey("Ed448");
      public static final Curve X25519 = (Curve)get().forKey("X25519");
      public static final Curve X448 = (Curve)get().forKey("X448");

      public static Registry get() {
         return REGISTRY;
      }

      private CRV() {
      }
   }

   public static final class HASH {
      private static final String IMPL_CLASSNAME = "io.jsonwebtoken.impl.security.StandardHashAlgorithms";
      private static final Registry REGISTRY = (Registry)Classes.newInstance("io.jsonwebtoken.impl.security.StandardHashAlgorithms");
      public static final HashAlgorithm SHA256 = (HashAlgorithm)get().forKey("sha-256");
      public static final HashAlgorithm SHA384 = (HashAlgorithm)get().forKey("sha-384");
      public static final HashAlgorithm SHA512 = (HashAlgorithm)get().forKey("sha-512");
      public static final HashAlgorithm SHA3_256 = (HashAlgorithm)get().forKey("sha3-256");
      public static final HashAlgorithm SHA3_384 = (HashAlgorithm)get().forKey("sha3-384");
      public static final HashAlgorithm SHA3_512 = (HashAlgorithm)get().forKey("sha3-512");

      public static Registry get() {
         return REGISTRY;
      }

      private HASH() {
      }
   }

   public static final class OP {
      private static final String IMPL_CLASSNAME = "io.jsonwebtoken.impl.security.StandardKeyOperations";
      private static final Registry REGISTRY = (Registry)Classes.newInstance("io.jsonwebtoken.impl.security.StandardKeyOperations");
      private static final String BUILDER_CLASSNAME = "io.jsonwebtoken.impl.security.DefaultKeyOperationBuilder";
      private static final String POLICY_BUILDER_CLASSNAME = "io.jsonwebtoken.impl.security.DefaultKeyOperationPolicyBuilder";
      public static final KeyOperation SIGN = (KeyOperation)get().forKey("sign");
      public static final KeyOperation VERIFY = (KeyOperation)get().forKey("verify");
      public static final KeyOperation ENCRYPT = (KeyOperation)get().forKey("encrypt");
      public static final KeyOperation DECRYPT = (KeyOperation)get().forKey("decrypt");
      public static final KeyOperation WRAP_KEY = (KeyOperation)get().forKey("wrapKey");
      public static final KeyOperation UNWRAP_KEY = (KeyOperation)get().forKey("unwrapKey");
      public static final KeyOperation DERIVE_KEY = (KeyOperation)get().forKey("deriveKey");
      public static final KeyOperation DERIVE_BITS = (KeyOperation)get().forKey("deriveBits");

      public static KeyOperationBuilder builder() {
         return (KeyOperationBuilder)Classes.newInstance("io.jsonwebtoken.impl.security.DefaultKeyOperationBuilder");
      }

      public static KeyOperationPolicyBuilder policy() {
         return (KeyOperationPolicyBuilder)Classes.newInstance("io.jsonwebtoken.impl.security.DefaultKeyOperationPolicyBuilder");
      }

      public static Registry get() {
         return REGISTRY;
      }

      private OP() {
      }
   }
}
