package com.google.crypto.tink.signature;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.PrivateKeyManager;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyManagerRegistry;
import com.google.crypto.tink.internal.LegacyKeyManagerImpl;
import com.google.crypto.tink.internal.MutableKeyCreationRegistry;
import com.google.crypto.tink.internal.MutableParametersRegistry;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.TinkBugException;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.signature.internal.RsaSsaPkcs1ProtoSerialization;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
import com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce;
import com.google.crypto.tink.util.SecretBigInteger;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class RsaSsaPkcs1SignKeyManager {
   private static final PrimitiveConstructor PUBLIC_KEY_SIGN_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(RsaSsaPkcs1SignJce::create, RsaSsaPkcs1PrivateKey.class, PublicKeySign.class);
   private static final PrimitiveConstructor PUBLIC_KEY_VERIFY_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(RsaSsaPkcs1VerifyJce::create, RsaSsaPkcs1PublicKey.class, PublicKeyVerify.class);
   private static final PrivateKeyManager legacyPrivateKeyManager = LegacyKeyManagerImpl.createPrivateKeyManager(getKeyType(), PublicKeySign.class, com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey.parser());
   private static final KeyManager legacyPublicKeyManager;
   private static final MutableKeyCreationRegistry.KeyCreator KEY_CREATOR;
   private static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;

   static String getKeyType() {
      return "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey";
   }

   @AccessesPartialKey
   private static RsaSsaPkcs1PrivateKey createKey(RsaSsaPkcs1Parameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      KeyPairGenerator keyGen = (KeyPairGenerator)EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
      RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(parameters.getModulusSizeBits(), new BigInteger(1, parameters.getPublicExponent().toByteArray()));
      keyGen.initialize(spec);
      KeyPair keyPair = keyGen.generateKeyPair();
      RSAPublicKey pubKey = (RSAPublicKey)keyPair.getPublic();
      RSAPrivateCrtKey privKey = (RSAPrivateCrtKey)keyPair.getPrivate();
      RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey = RsaSsaPkcs1PublicKey.builder().setParameters(parameters).setModulus(pubKey.getModulus()).setIdRequirement(idRequirement).build();
      return RsaSsaPkcs1PrivateKey.builder().setPublicKey(rsaSsaPkcs1PublicKey).setPrimes(SecretBigInteger.fromBigInteger(privKey.getPrimeP(), InsecureSecretKeyAccess.get()), SecretBigInteger.fromBigInteger(privKey.getPrimeQ(), InsecureSecretKeyAccess.get())).setPrivateExponent(SecretBigInteger.fromBigInteger(privKey.getPrivateExponent(), InsecureSecretKeyAccess.get())).setPrimeExponents(SecretBigInteger.fromBigInteger(privKey.getPrimeExponentP(), InsecureSecretKeyAccess.get()), SecretBigInteger.fromBigInteger(privKey.getPrimeExponentQ(), InsecureSecretKeyAccess.get())).setCrtCoefficient(SecretBigInteger.fromBigInteger(privKey.getCrtCoefficient(), InsecureSecretKeyAccess.get())).build();
   }

   private static Map namedParameters() throws GeneralSecurityException {
      Map<String, Parameters> result = new HashMap();
      result.put("RSA_SSA_PKCS1_3072_SHA256_F4", PredefinedSignatureParameters.RSA_SSA_PKCS1_3072_SHA256_F4);
      result.put("RSA_SSA_PKCS1_3072_SHA256_F4_RAW", RsaSsaPkcs1Parameters.builder().setHashType(RsaSsaPkcs1Parameters.HashType.SHA256).setModulusSizeBits(3072).setPublicExponent(RsaSsaPkcs1Parameters.F4).setVariant(RsaSsaPkcs1Parameters.Variant.NO_PREFIX).build());
      result.put("RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX", PredefinedSignatureParameters.RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX);
      result.put("RSA_SSA_PKCS1_4096_SHA512_F4", PredefinedSignatureParameters.RSA_SSA_PKCS1_4096_SHA512_F4);
      result.put("RSA_SSA_PKCS1_4096_SHA512_F4_RAW", RsaSsaPkcs1Parameters.builder().setHashType(RsaSsaPkcs1Parameters.HashType.SHA512).setModulusSizeBits(4096).setPublicExponent(RsaSsaPkcs1Parameters.F4).setVariant(RsaSsaPkcs1Parameters.Variant.NO_PREFIX).build());
      return result;
   }

   public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use RSA SSA PKCS1 in FIPS-mode, as BoringCrypto module is not available.");
      } else {
         RsaSsaPkcs1ProtoSerialization.register();
         MutableParametersRegistry.globalInstance().putAll(namedParameters());
         MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(PUBLIC_KEY_SIGN_PRIMITIVE_CONSTRUCTOR);
         MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(PUBLIC_KEY_VERIFY_PRIMITIVE_CONSTRUCTOR);
         MutableKeyCreationRegistry.globalInstance().add(KEY_CREATOR, RsaSsaPkcs1Parameters.class);
         KeyManagerRegistry.globalInstance().registerKeyManagerWithFipsCompatibility(legacyPrivateKeyManager, FIPS, newKeyAllowed);
         KeyManagerRegistry.globalInstance().registerKeyManagerWithFipsCompatibility(legacyPublicKeyManager, FIPS, false);
      }
   }

   public static final KeyTemplate rsa3072SsaPkcs1Sha256F4Template() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(RsaSsaPkcs1Parameters.builder().setModulusSizeBits(3072).setPublicExponent(RsaSsaPkcs1Parameters.F4).setHashType(RsaSsaPkcs1Parameters.HashType.SHA256).setVariant(RsaSsaPkcs1Parameters.Variant.TINK).build())));
   }

   public static final KeyTemplate rawRsa3072SsaPkcs1Sha256F4Template() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(RsaSsaPkcs1Parameters.builder().setModulusSizeBits(3072).setPublicExponent(RsaSsaPkcs1Parameters.F4).setHashType(RsaSsaPkcs1Parameters.HashType.SHA256).setVariant(RsaSsaPkcs1Parameters.Variant.NO_PREFIX).build())));
   }

   public static final KeyTemplate rsa4096SsaPkcs1Sha512F4Template() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(RsaSsaPkcs1Parameters.builder().setModulusSizeBits(4096).setPublicExponent(RsaSsaPkcs1Parameters.F4).setHashType(RsaSsaPkcs1Parameters.HashType.SHA512).setVariant(RsaSsaPkcs1Parameters.Variant.TINK).build())));
   }

   public static final KeyTemplate rawRsa4096SsaPkcs1Sha512F4Template() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(RsaSsaPkcs1Parameters.builder().setModulusSizeBits(4096).setPublicExponent(RsaSsaPkcs1Parameters.F4).setHashType(RsaSsaPkcs1Parameters.HashType.SHA512).setVariant(RsaSsaPkcs1Parameters.Variant.NO_PREFIX).build())));
   }

   private RsaSsaPkcs1SignKeyManager() {
   }

   static {
      legacyPublicKeyManager = LegacyKeyManagerImpl.create(RsaSsaPkcs1VerifyKeyManager.getKeyType(), PublicKeyVerify.class, KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey.parser());
      KEY_CREATOR = RsaSsaPkcs1SignKeyManager::createKey;
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
   }
}
