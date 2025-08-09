package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.RestrictedApi;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;

@Immutable
public final class JwtEcdsaPrivateKey extends JwtSignaturePrivateKey {
   public final JwtEcdsaPublicKey publicKey;
   public final SecretBigInteger privateKeyValue;

   private static void validatePrivateValue(BigInteger privateValue, ECPoint publicPoint, JwtEcdsaParameters.Algorithm algorithm) throws GeneralSecurityException {
      BigInteger order = algorithm.getECParameterSpec().getOrder();
      if (privateValue.signum() > 0 && privateValue.compareTo(order) < 0) {
         ECPoint p = EllipticCurvesUtil.multiplyByGenerator(privateValue, algorithm.getECParameterSpec());
         if (!p.equals(publicPoint)) {
            throw new GeneralSecurityException("Invalid private value");
         }
      } else {
         throw new GeneralSecurityException("Invalid private value");
      }
   }

   @RestrictedApi(
      explanation = "Accessing parts of keys can produce unexpected incompatibilities, annotate the function with @AccessesPartialKey",
      link = "https://developers.google.com/tink/design/access_control#accessing_partial_keys",
      allowedOnPath = ".*Test\\.java",
      allowlistAnnotations = {AccessesPartialKey.class}
   )
   @AccessesPartialKey
   public static JwtEcdsaPrivateKey create(JwtEcdsaPublicKey publicKey, SecretBigInteger privateValue) throws GeneralSecurityException {
      validatePrivateValue(privateValue.getBigInteger(InsecureSecretKeyAccess.get()), publicKey.getPublicPoint(), publicKey.getParameters().getAlgorithm());
      return new JwtEcdsaPrivateKey(publicKey, privateValue);
   }

   private JwtEcdsaPrivateKey(JwtEcdsaPublicKey publicKey, SecretBigInteger privateKeyValue) {
      this.publicKey = publicKey;
      this.privateKeyValue = privateKeyValue;
   }

   @RestrictedApi(
      explanation = "Accessing parts of keys can produce unexpected incompatibilities, annotate the function with @AccessesPartialKey",
      link = "https://developers.google.com/tink/design/access_control#accessing_partial_keys",
      allowedOnPath = ".*Test\\.java",
      allowlistAnnotations = {AccessesPartialKey.class}
   )
   public SecretBigInteger getPrivateValue() {
      return this.privateKeyValue;
   }

   public JwtEcdsaParameters getParameters() {
      return this.publicKey.getParameters();
   }

   public JwtEcdsaPublicKey getPublicKey() {
      return this.publicKey;
   }

   public boolean equalsKey(Key o) {
      if (!(o instanceof JwtEcdsaPrivateKey)) {
         return false;
      } else {
         JwtEcdsaPrivateKey that = (JwtEcdsaPrivateKey)o;
         return that.publicKey.equalsKey(this.publicKey) && this.privateKeyValue.equalsSecretBigInteger(that.privateKeyValue);
      }
   }
}
