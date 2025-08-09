package com.google.crypto.tink.signature;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.PrivateKeyManager;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyManagerRegistry;
import com.google.crypto.tink.internal.LegacyKeyManagerImpl;
import com.google.crypto.tink.internal.MutableKeyCreationRegistry;
import com.google.crypto.tink.internal.MutableKeyDerivationRegistry;
import com.google.crypto.tink.internal.MutableParametersRegistry;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.TinkBugException;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.signature.internal.Ed25519ProtoSerialization;
import com.google.crypto.tink.subtle.Ed25519Sign;
import com.google.crypto.tink.subtle.Ed25519Verify;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class Ed25519PrivateKeyManager {
   private static final PrimitiveConstructor PUBLIC_KEY_SIGN_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(Ed25519Sign::create, Ed25519PrivateKey.class, PublicKeySign.class);
   private static final PrimitiveConstructor PUBLIC_KEY_VERIFY_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(Ed25519Verify::create, Ed25519PublicKey.class, PublicKeyVerify.class);
   private static final PrivateKeyManager legacyPrivateKeyManager = LegacyKeyManagerImpl.createPrivateKeyManager(getKeyType(), PublicKeySign.class, com.google.crypto.tink.proto.Ed25519PrivateKey.parser());
   private static final KeyManager legacyPublicKeyManager;
   private static final MutableKeyDerivationRegistry.InsecureKeyCreator KEY_DERIVER;
   private static final MutableKeyCreationRegistry.KeyCreator KEY_CREATOR;

   static String getKeyType() {
      return "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey";
   }

   @AccessesPartialKey
   static Ed25519PrivateKey createEd25519KeyFromRandomness(Ed25519Parameters parameters, InputStream stream, @Nullable Integer idRequirement, SecretKeyAccess access) throws GeneralSecurityException {
      SecretBytes pseudorandomness = Util.readIntoSecretBytes(stream, 32, access);
      Ed25519Sign.KeyPair keyPair = Ed25519Sign.KeyPair.newKeyPairFromSeed(pseudorandomness.toByteArray(access));
      Ed25519PublicKey publicKey = Ed25519PublicKey.create(parameters.getVariant(), Bytes.copyFrom(keyPair.getPublicKey()), idRequirement);
      return Ed25519PrivateKey.create(publicKey, SecretBytes.copyFrom(keyPair.getPrivateKey(), access));
   }

   @AccessesPartialKey
   static Ed25519PrivateKey createEd25519Key(Ed25519Parameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      Ed25519Sign.KeyPair keyPair = Ed25519Sign.KeyPair.newKeyPair();
      Ed25519PublicKey publicKey = Ed25519PublicKey.create(parameters.getVariant(), Bytes.copyFrom(keyPair.getPublicKey()), idRequirement);
      return Ed25519PrivateKey.create(publicKey, SecretBytes.copyFrom(keyPair.getPrivateKey(), InsecureSecretKeyAccess.get()));
   }

   private static Map namedParameters() throws GeneralSecurityException {
      Map<String, Parameters> result = new HashMap();
      result.put("ED25519", Ed25519Parameters.create(Ed25519Parameters.Variant.TINK));
      result.put("ED25519_RAW", Ed25519Parameters.create(Ed25519Parameters.Variant.NO_PREFIX));
      result.put("ED25519WithRawOutput", Ed25519Parameters.create(Ed25519Parameters.Variant.NO_PREFIX));
      return Collections.unmodifiableMap(result);
   }

   public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
      if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
         throw new GeneralSecurityException("Registering AES GCM SIV is not supported in FIPS mode");
      } else {
         Ed25519ProtoSerialization.register();
         MutableParametersRegistry.globalInstance().putAll(namedParameters());
         MutableKeyCreationRegistry.globalInstance().add(KEY_CREATOR, Ed25519Parameters.class);
         MutableKeyDerivationRegistry.globalInstance().add(KEY_DERIVER, Ed25519Parameters.class);
         MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(PUBLIC_KEY_SIGN_PRIMITIVE_CONSTRUCTOR);
         MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(PUBLIC_KEY_VERIFY_PRIMITIVE_CONSTRUCTOR);
         KeyManagerRegistry.globalInstance().registerKeyManager(legacyPrivateKeyManager, newKeyAllowed);
         KeyManagerRegistry.globalInstance().registerKeyManager(legacyPublicKeyManager, false);
      }
   }

   public static final KeyTemplate ed25519Template() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(Ed25519Parameters.create(Ed25519Parameters.Variant.TINK))));
   }

   public static final KeyTemplate rawEd25519Template() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(Ed25519Parameters.create(Ed25519Parameters.Variant.NO_PREFIX))));
   }

   private Ed25519PrivateKeyManager() {
   }

   static {
      legacyPublicKeyManager = LegacyKeyManagerImpl.create(Ed25519PublicKeyManager.getKeyType(), PublicKeyVerify.class, KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, com.google.crypto.tink.proto.Ed25519PublicKey.parser());
      KEY_DERIVER = Ed25519PrivateKeyManager::createEd25519KeyFromRandomness;
      KEY_CREATOR = Ed25519PrivateKeyManager::createEd25519Key;
   }
}
