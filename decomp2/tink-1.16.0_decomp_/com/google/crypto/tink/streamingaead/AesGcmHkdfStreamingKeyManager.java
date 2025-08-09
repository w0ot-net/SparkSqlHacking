package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.StreamingAead;
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
import com.google.crypto.tink.streamingaead.internal.AesGcmHkdfStreamingProtoSerialization;
import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.util.SecretBytes;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class AesGcmHkdfStreamingKeyManager {
   private static final PrimitiveConstructor AES_GCM_HKDF_STREAMING_AEAD_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(AesGcmHkdfStreaming::create, AesGcmHkdfStreamingKey.class, StreamingAead.class);
   private static final KeyManager legacyKeyManager;
   private static final MutableKeyCreationRegistry.KeyCreator KEY_CREATOR;
   private static final MutableKeyDerivationRegistry.InsecureKeyCreator KEY_DERIVER;

   static String getKeyType() {
      return "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
   }

   @AccessesPartialKey
   private static AesGcmHkdfStreamingKey creatAesGcmHkdfStreamingKey(AesGcmHkdfStreamingParameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      return AesGcmHkdfStreamingKey.create(parameters, SecretBytes.randomBytes(parameters.getKeySizeBytes()));
   }

   @AccessesPartialKey
   static AesGcmHkdfStreamingKey createAesGcmHkdfStreamingKeyFromRandomness(AesGcmHkdfStreamingParameters parameters, InputStream stream, @Nullable Integer idRequirement, SecretKeyAccess access) throws GeneralSecurityException {
      return AesGcmHkdfStreamingKey.create(parameters, Util.readIntoSecretBytes(stream, parameters.getKeySizeBytes(), access));
   }

   private static Map namedParameters() throws GeneralSecurityException {
      Map<String, Parameters> result = new HashMap();
      result.put("AES128_GCM_HKDF_4KB", PredefinedStreamingAeadParameters.AES128_GCM_HKDF_4KB);
      result.put("AES128_GCM_HKDF_1MB", PredefinedStreamingAeadParameters.AES128_GCM_HKDF_1MB);
      result.put("AES256_GCM_HKDF_4KB", PredefinedStreamingAeadParameters.AES256_GCM_HKDF_4KB);
      result.put("AES256_GCM_HKDF_1MB", PredefinedStreamingAeadParameters.AES256_GCM_HKDF_1MB);
      return Collections.unmodifiableMap(result);
   }

   public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
      if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
         throw new GeneralSecurityException("Registering AES-GCM HKDF Streaming AEAD is not supported in FIPS mode");
      } else {
         AesGcmHkdfStreamingProtoSerialization.register();
         MutableParametersRegistry.globalInstance().putAll(namedParameters());
         MutableKeyDerivationRegistry.globalInstance().add(KEY_DERIVER, AesGcmHkdfStreamingParameters.class);
         MutableKeyCreationRegistry.globalInstance().add(KEY_CREATOR, AesGcmHkdfStreamingParameters.class);
         MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(AES_GCM_HKDF_STREAMING_AEAD_PRIMITIVE_CONSTRUCTOR);
         KeyManagerRegistry.globalInstance().registerKeyManager(legacyKeyManager, newKeyAllowed);
      }
   }

   public static final KeyTemplate aes128GcmHkdf4KBTemplate() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(AesGcmHkdfStreamingParameters.builder().setKeySizeBytes(16).setDerivedAesGcmKeySizeBytes(16).setCiphertextSegmentSizeBytes(4096).setHkdfHashType(AesGcmHkdfStreamingParameters.HashType.SHA256).build())));
   }

   public static final KeyTemplate aes128GcmHkdf1MBTemplate() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(AesGcmHkdfStreamingParameters.builder().setKeySizeBytes(16).setDerivedAesGcmKeySizeBytes(16).setCiphertextSegmentSizeBytes(1048576).setHkdfHashType(AesGcmHkdfStreamingParameters.HashType.SHA256).build())));
   }

   public static final KeyTemplate aes256GcmHkdf4KBTemplate() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(AesGcmHkdfStreamingParameters.builder().setKeySizeBytes(32).setDerivedAesGcmKeySizeBytes(32).setCiphertextSegmentSizeBytes(4096).setHkdfHashType(AesGcmHkdfStreamingParameters.HashType.SHA256).build())));
   }

   public static final KeyTemplate aes256GcmHkdf1MBTemplate() {
      return (KeyTemplate)TinkBugException.exceptionIsBug((TinkBugException.ThrowingSupplier)(() -> KeyTemplate.createFrom(AesGcmHkdfStreamingParameters.builder().setKeySizeBytes(32).setDerivedAesGcmKeySizeBytes(32).setCiphertextSegmentSizeBytes(1048576).setHkdfHashType(AesGcmHkdfStreamingParameters.HashType.SHA256).build())));
   }

   private AesGcmHkdfStreamingKeyManager() {
   }

   static {
      legacyKeyManager = LegacyKeyManagerImpl.create(getKeyType(), StreamingAead.class, KeyData.KeyMaterialType.SYMMETRIC, com.google.crypto.tink.proto.AesGcmHkdfStreamingKey.parser());
      KEY_CREATOR = AesGcmHkdfStreamingKeyManager::creatAesGcmHkdfStreamingKey;
      KEY_DERIVER = AesGcmHkdfStreamingKeyManager::createAesGcmHkdfStreamingKeyFromRandomness;
   }
}
