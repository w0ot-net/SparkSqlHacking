package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.signature.RsaSsaPkcs1Parameters;
import com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

final class JwtRsaSsaPkcs1VerifyKeyManager {
   static final PrimitiveConstructor PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(JwtRsaSsaPkcs1VerifyKeyManager::createFullPrimitive, JwtRsaSsaPkcs1PublicKey.class, JwtPublicKeyVerify.class);

   private static RsaSsaPkcs1Parameters.HashType hashTypeForAlgorithm(JwtRsaSsaPkcs1Parameters.Algorithm algorithm) throws GeneralSecurityException {
      if (algorithm.equals(JwtRsaSsaPkcs1Parameters.Algorithm.RS256)) {
         return RsaSsaPkcs1Parameters.HashType.SHA256;
      } else if (algorithm.equals(JwtRsaSsaPkcs1Parameters.Algorithm.RS384)) {
         return RsaSsaPkcs1Parameters.HashType.SHA384;
      } else if (algorithm.equals(JwtRsaSsaPkcs1Parameters.Algorithm.RS512)) {
         return RsaSsaPkcs1Parameters.HashType.SHA512;
      } else {
         throw new GeneralSecurityException("unknown algorithm " + algorithm);
      }
   }

   @AccessesPartialKey
   static RsaSsaPkcs1PublicKey toRsaSsaPkcs1PublicKey(JwtRsaSsaPkcs1PublicKey publicKey) throws GeneralSecurityException {
      RsaSsaPkcs1Parameters rsaSsaPkcs1Parameters = RsaSsaPkcs1Parameters.builder().setModulusSizeBits(publicKey.getParameters().getModulusSizeBits()).setPublicExponent(publicKey.getParameters().getPublicExponent()).setHashType(hashTypeForAlgorithm(publicKey.getParameters().getAlgorithm())).setVariant(RsaSsaPkcs1Parameters.Variant.NO_PREFIX).build();
      return RsaSsaPkcs1PublicKey.builder().setParameters(rsaSsaPkcs1Parameters).setModulus(publicKey.getModulus()).build();
   }

   static JwtPublicKeyVerify createFullPrimitive(final JwtRsaSsaPkcs1PublicKey publicKey) throws GeneralSecurityException {
      RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey = toRsaSsaPkcs1PublicKey(publicKey);
      final PublicKeyVerify verifier = RsaSsaPkcs1VerifyJce.create(rsaSsaPkcs1PublicKey);
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

   static String getKeyType() {
      return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
   }

   private JwtRsaSsaPkcs1VerifyKeyManager() {
   }
}
