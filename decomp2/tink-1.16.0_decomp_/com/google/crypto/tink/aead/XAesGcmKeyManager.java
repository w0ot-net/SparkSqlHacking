package com.google.crypto.tink.aead;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.aead.internal.XAesGcm;
import com.google.crypto.tink.aead.internal.XAesGcmProtoSerialization;
import com.google.crypto.tink.internal.MutableKeyCreationRegistry;
import com.google.crypto.tink.internal.MutableParametersRegistry;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class XAesGcmKeyManager {
   private static final MutableKeyCreationRegistry.KeyCreator KEY_CREATOR = XAesGcmKeyManager::createXAesGcmKey;
   private static final PrimitiveConstructor X_AES_GCM_PRIMITVE_CONSTRUCTOR = PrimitiveConstructor.create(XAesGcm::create, XAesGcmKey.class, Aead.class);

   private static Map namedParameters() {
      Map<String, Parameters> result = new HashMap();
      result.put("XAES_256_GCM_192_BIT_NONCE", PredefinedAeadParameters.XAES_256_GCM_192_BIT_NONCE);
      result.put("XAES_256_GCM_192_BIT_NONCE_NO_PREFIX", PredefinedAeadParameters.XAES_256_GCM_192_BIT_NONCE_NO_PREFIX);
      result.put("XAES_256_GCM_160_BIT_NONCE_NO_PREFIX", PredefinedAeadParameters.XAES_256_GCM_160_BIT_NONCE_NO_PREFIX);
      result.put("X_AES_GCM_8_BYTE_SALT_NO_PREFIX", PredefinedAeadParameters.X_AES_GCM_8_BYTE_SALT_NO_PREFIX);
      return Collections.unmodifiableMap(result);
   }

   @AccessesPartialKey
   private static XAesGcmKey createXAesGcmKey(XAesGcmParameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      return XAesGcmKey.create(parameters, SecretBytes.randomBytes(32), idRequirement);
   }

   public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
      XAesGcmProtoSerialization.register();
      MutableParametersRegistry.globalInstance().putAll(namedParameters());
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(X_AES_GCM_PRIMITVE_CONSTRUCTOR);
      MutableKeyCreationRegistry.globalInstance().add(KEY_CREATOR, XAesGcmParameters.class);
   }

   private XAesGcmKeyManager() {
   }
}
