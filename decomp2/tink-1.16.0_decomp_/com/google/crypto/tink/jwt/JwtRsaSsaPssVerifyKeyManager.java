package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.signature.RsaSsaPssParameters;
import com.google.crypto.tink.signature.RsaSsaPssPublicKey;
import com.google.crypto.tink.subtle.RsaSsaPssVerifyJce;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

final class JwtRsaSsaPssVerifyKeyManager {
   static final PrimitiveConstructor PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(JwtRsaSsaPssVerifyKeyManager::createFullPrimitive, JwtRsaSsaPssPublicKey.class, JwtPublicKeyVerify.class);

   private static RsaSsaPssParameters.HashType hashTypeForAlgorithm(JwtRsaSsaPssParameters.Algorithm algorithm) throws GeneralSecurityException {
      if (algorithm.equals(JwtRsaSsaPssParameters.Algorithm.PS256)) {
         return RsaSsaPssParameters.HashType.SHA256;
      } else if (algorithm.equals(JwtRsaSsaPssParameters.Algorithm.PS384)) {
         return RsaSsaPssParameters.HashType.SHA384;
      } else if (algorithm.equals(JwtRsaSsaPssParameters.Algorithm.PS512)) {
         return RsaSsaPssParameters.HashType.SHA512;
      } else {
         throw new GeneralSecurityException("unknown algorithm " + algorithm);
      }
   }

   static final int saltLengthForPssAlgorithm(JwtRsaSsaPssParameters.Algorithm algorithm) throws GeneralSecurityException {
      if (algorithm.equals(JwtRsaSsaPssParameters.Algorithm.PS256)) {
         return 32;
      } else if (algorithm.equals(JwtRsaSsaPssParameters.Algorithm.PS384)) {
         return 48;
      } else if (algorithm.equals(JwtRsaSsaPssParameters.Algorithm.PS512)) {
         return 64;
      } else {
         throw new GeneralSecurityException("unknown algorithm " + algorithm);
      }
   }

   @AccessesPartialKey
   static RsaSsaPssPublicKey toRsaSsaPssPublicKey(JwtRsaSsaPssPublicKey publicKey) throws GeneralSecurityException {
      RsaSsaPssParameters rsaSsaPssParameters = RsaSsaPssParameters.builder().setModulusSizeBits(publicKey.getParameters().getModulusSizeBits()).setPublicExponent(publicKey.getParameters().getPublicExponent()).setSigHashType(hashTypeForAlgorithm(publicKey.getParameters().getAlgorithm())).setMgf1HashType(hashTypeForAlgorithm(publicKey.getParameters().getAlgorithm())).setSaltLengthBytes(saltLengthForPssAlgorithm(publicKey.getParameters().getAlgorithm())).setVariant(RsaSsaPssParameters.Variant.NO_PREFIX).build();
      return RsaSsaPssPublicKey.builder().setParameters(rsaSsaPssParameters).setModulus(publicKey.getModulus()).build();
   }

   static JwtPublicKeyVerify createFullPrimitive(final JwtRsaSsaPssPublicKey publicKey) throws GeneralSecurityException {
      RsaSsaPssPublicKey rsaSsaPssPublicKey = toRsaSsaPssPublicKey(publicKey);
      final PublicKeyVerify verifier = RsaSsaPssVerifyJce.create(rsaSsaPssPublicKey);
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
      return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";
   }

   private JwtRsaSsaPssVerifyKeyManager() {
   }
}
