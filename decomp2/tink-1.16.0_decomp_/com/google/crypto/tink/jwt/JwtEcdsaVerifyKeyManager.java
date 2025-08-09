package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.signature.EcdsaParameters;
import com.google.crypto.tink.signature.EcdsaPublicKey;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

class JwtEcdsaVerifyKeyManager {
   static final PrimitiveConstructor PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(JwtEcdsaVerifyKeyManager::createFullPrimitive, JwtEcdsaPublicKey.class, JwtPublicKeyVerify.class);

   static EcdsaParameters.CurveType getCurveType(JwtEcdsaParameters parameters) throws GeneralSecurityException {
      if (parameters.getAlgorithm().equals(JwtEcdsaParameters.Algorithm.ES256)) {
         return EcdsaParameters.CurveType.NIST_P256;
      } else if (parameters.getAlgorithm().equals(JwtEcdsaParameters.Algorithm.ES384)) {
         return EcdsaParameters.CurveType.NIST_P384;
      } else if (parameters.getAlgorithm().equals(JwtEcdsaParameters.Algorithm.ES512)) {
         return EcdsaParameters.CurveType.NIST_P521;
      } else {
         throw new GeneralSecurityException("unknown algorithm in parameters: " + parameters);
      }
   }

   static EcdsaParameters.HashType getHash(JwtEcdsaParameters parameters) throws GeneralSecurityException {
      if (parameters.getAlgorithm().equals(JwtEcdsaParameters.Algorithm.ES256)) {
         return EcdsaParameters.HashType.SHA256;
      } else if (parameters.getAlgorithm().equals(JwtEcdsaParameters.Algorithm.ES384)) {
         return EcdsaParameters.HashType.SHA384;
      } else if (parameters.getAlgorithm().equals(JwtEcdsaParameters.Algorithm.ES512)) {
         return EcdsaParameters.HashType.SHA512;
      } else {
         throw new GeneralSecurityException("unknown algorithm in parameters: " + parameters);
      }
   }

   @AccessesPartialKey
   static EcdsaPublicKey toEcdsaPublicKey(JwtEcdsaPublicKey publicKey) throws GeneralSecurityException {
      EcdsaParameters ecdsaParameters = EcdsaParameters.builder().setSignatureEncoding(EcdsaParameters.SignatureEncoding.IEEE_P1363).setCurveType(getCurveType(publicKey.getParameters())).setHashType(getHash(publicKey.getParameters())).build();
      return EcdsaPublicKey.builder().setParameters(ecdsaParameters).setPublicPoint(publicKey.getPublicPoint()).build();
   }

   static JwtPublicKeyVerify createFullPrimitive(final JwtEcdsaPublicKey publicKey) throws GeneralSecurityException {
      EcdsaPublicKey ecdsaPublicKey = toEcdsaPublicKey(publicKey);
      final PublicKeyVerify verifier = EcdsaVerifyJce.create(ecdsaPublicKey);
      return new JwtPublicKeyVerify() {
         public VerifiedJwt verifyAndDecode(String compact, JwtValidator validator) throws GeneralSecurityException {
            JwtFormat.Parts parts = JwtFormat.splitSignedCompact(compact);
            verifier.verify(parts.signatureOrMac, parts.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
            JsonObject parsedHeader = JsonUtil.parseJson(parts.header);
            JwtFormat.validateHeader(parsedHeader, publicKey.getParameters().getAlgorithm().getStandardName(), publicKey.getKid(), publicKey.getParameters().allowKidAbsent());
            RawJwt token = RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(parsedHeader), parts.payload);
            return validator.validate(token);
         }
      };
   }

   static final EllipticCurves.CurveType getCurve(JwtEcdsaAlgorithm algorithm) throws GeneralSecurityException {
      switch (algorithm) {
         case ES256:
            return EllipticCurves.CurveType.NIST_P256;
         case ES384:
            return EllipticCurves.CurveType.NIST_P384;
         case ES512:
            return EllipticCurves.CurveType.NIST_P521;
         default:
            throw new GeneralSecurityException("unknown algorithm " + algorithm.name());
      }
   }

   public static Enums.HashType hashForEcdsaAlgorithm(JwtEcdsaAlgorithm algorithm) throws GeneralSecurityException {
      switch (algorithm) {
         case ES256:
            return Enums.HashType.SHA256;
         case ES384:
            return Enums.HashType.SHA384;
         case ES512:
            return Enums.HashType.SHA512;
         default:
            throw new GeneralSecurityException("unknown algorithm " + algorithm.name());
      }
   }

   static final void validateEcdsaAlgorithm(JwtEcdsaAlgorithm algorithm) throws GeneralSecurityException {
      Object unused = hashForEcdsaAlgorithm(algorithm);
   }

   private JwtEcdsaVerifyKeyManager() {
   }

   static String getKeyType() {
      return "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
   }
}
