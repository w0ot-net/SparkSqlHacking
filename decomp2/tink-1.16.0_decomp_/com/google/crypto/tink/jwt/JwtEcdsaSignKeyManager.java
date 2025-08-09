package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.PrivateKeyManager;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyManagerRegistry;
import com.google.crypto.tink.internal.LegacyKeyManagerImpl;
import com.google.crypto.tink.internal.MutableKeyCreationRegistry;
import com.google.crypto.tink.internal.MutableParametersRegistry;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.signature.EcdsaPrivateKey;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.util.SecretBigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class JwtEcdsaSignKeyManager {
   private static final PrivateKeyManager legacyPrivateKeyManager = LegacyKeyManagerImpl.createPrivateKeyManager(getKeyType(), Void.class, com.google.crypto.tink.proto.JwtEcdsaPrivateKey.parser());
   private static final KeyManager legacyPublicKeyManager;
   private static final PrimitiveConstructor PRIMITIVE_CONSTRUCTOR;
   private static final MutableKeyCreationRegistry.KeyCreator KEY_CREATOR;
   private static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;

   @AccessesPartialKey
   private static EcdsaPrivateKey toEcdsaPrivateKey(JwtEcdsaPrivateKey privateKey) throws GeneralSecurityException {
      return EcdsaPrivateKey.builder().setPublicKey(JwtEcdsaVerifyKeyManager.toEcdsaPublicKey(privateKey.getPublicKey())).setPrivateValue(privateKey.getPrivateValue()).build();
   }

   static JwtPublicKeySign createFullPrimitive(final JwtEcdsaPrivateKey privateKey) throws GeneralSecurityException {
      EcdsaPrivateKey ecdsaPrivateKey = toEcdsaPrivateKey(privateKey);
      final PublicKeySign signer = EcdsaSignJce.create(ecdsaPrivateKey);
      final String algorithm = privateKey.getParameters().getAlgorithm().getStandardName();
      return new JwtPublicKeySign() {
         public String signAndEncode(RawJwt rawJwt) throws GeneralSecurityException {
            String unsignedCompact = JwtFormat.createUnsignedCompact(algorithm, privateKey.getPublicKey().getKid(), rawJwt);
            return JwtFormat.createSignedCompact(unsignedCompact, signer.sign(unsignedCompact.getBytes(StandardCharsets.US_ASCII)));
         }
      };
   }

   @AccessesPartialKey
   private static JwtEcdsaPrivateKey createKey(JwtEcdsaParameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      KeyPair keyPair = EllipticCurves.generateKeyPair(parameters.getAlgorithm().getECParameterSpec());
      ECPublicKey pubKey = (ECPublicKey)keyPair.getPublic();
      ECPrivateKey privKey = (ECPrivateKey)keyPair.getPrivate();
      JwtEcdsaPublicKey.Builder publicKeyBuilder = JwtEcdsaPublicKey.builder().setParameters(parameters).setPublicPoint(pubKey.getW());
      if (idRequirement != null) {
         publicKeyBuilder.setIdRequirement(idRequirement);
      }

      return JwtEcdsaPrivateKey.create(publicKeyBuilder.build(), SecretBigInteger.fromBigInteger(privKey.getS(), InsecureSecretKeyAccess.get()));
   }

   private JwtEcdsaSignKeyManager() {
   }

   static String getKeyType() {
      return "type.googleapis.com/google.crypto.tink.JwtEcdsaPrivateKey";
   }

   private static Map namedParameters() throws GeneralSecurityException {
      Map<String, Parameters> result = new HashMap();
      result.put("JWT_ES256_RAW", JwtEcdsaParameters.builder().setAlgorithm(JwtEcdsaParameters.Algorithm.ES256).setKidStrategy(JwtEcdsaParameters.KidStrategy.IGNORED).build());
      result.put("JWT_ES256", JwtEcdsaParameters.builder().setAlgorithm(JwtEcdsaParameters.Algorithm.ES256).setKidStrategy(JwtEcdsaParameters.KidStrategy.BASE64_ENCODED_KEY_ID).build());
      result.put("JWT_ES384_RAW", JwtEcdsaParameters.builder().setAlgorithm(JwtEcdsaParameters.Algorithm.ES384).setKidStrategy(JwtEcdsaParameters.KidStrategy.IGNORED).build());
      result.put("JWT_ES384", JwtEcdsaParameters.builder().setAlgorithm(JwtEcdsaParameters.Algorithm.ES384).setKidStrategy(JwtEcdsaParameters.KidStrategy.BASE64_ENCODED_KEY_ID).build());
      result.put("JWT_ES512_RAW", JwtEcdsaParameters.builder().setAlgorithm(JwtEcdsaParameters.Algorithm.ES512).setKidStrategy(JwtEcdsaParameters.KidStrategy.IGNORED).build());
      result.put("JWT_ES512", JwtEcdsaParameters.builder().setAlgorithm(JwtEcdsaParameters.Algorithm.ES512).setKidStrategy(JwtEcdsaParameters.KidStrategy.BASE64_ENCODED_KEY_ID).build());
      return Collections.unmodifiableMap(result);
   }

   public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto module is not available.");
      } else {
         KeyManagerRegistry.globalInstance().registerKeyManagerWithFipsCompatibility(legacyPrivateKeyManager, FIPS, newKeyAllowed);
         KeyManagerRegistry.globalInstance().registerKeyManagerWithFipsCompatibility(legacyPublicKeyManager, FIPS, false);
         MutableKeyCreationRegistry.globalInstance().add(KEY_CREATOR, JwtEcdsaParameters.class);
         JwtEcdsaProtoSerialization.register();
         MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(JwtEcdsaVerifyKeyManager.PRIMITIVE_CONSTRUCTOR);
         MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(PRIMITIVE_CONSTRUCTOR);
         MutableParametersRegistry.globalInstance().putAll(namedParameters());
      }
   }

   static {
      legacyPublicKeyManager = LegacyKeyManagerImpl.create(JwtEcdsaVerifyKeyManager.getKeyType(), Void.class, KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, com.google.crypto.tink.proto.JwtEcdsaPublicKey.parser());
      PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(JwtEcdsaSignKeyManager::createFullPrimitive, JwtEcdsaPrivateKey.class, JwtPublicKeySign.class);
      KEY_CREATOR = JwtEcdsaSignKeyManager::createKey;
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
   }
}
