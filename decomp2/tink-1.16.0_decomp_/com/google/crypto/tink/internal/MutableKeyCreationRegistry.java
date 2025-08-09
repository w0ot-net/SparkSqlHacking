package com.google.crypto.tink.internal;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class MutableKeyCreationRegistry {
   private final Map creators = new HashMap();
   private static final KeyCreator LEGACY_PROTO_KEY_CREATOR = MutableKeyCreationRegistry::createProtoKeyFromProtoParameters;
   private static final MutableKeyCreationRegistry globalInstance = newRegistryWithLegacyFallback();

   private static LegacyProtoKey createProtoKeyFromProtoParameters(LegacyProtoParameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      KeyTemplate keyTemplate = parameters.getSerialization().getKeyTemplate();
      KeyManager<?> manager = KeyManagerRegistry.globalInstance().getUntypedKeyManager(keyTemplate.getTypeUrl());
      if (!KeyManagerRegistry.globalInstance().isNewKeyAllowed(keyTemplate.getTypeUrl())) {
         throw new GeneralSecurityException("Creating new keys is not allowed.");
      } else {
         KeyData keyData = manager.newKeyData(keyTemplate.getValue());
         ProtoKeySerialization protoSerialization = ProtoKeySerialization.create(keyData.getTypeUrl(), keyData.getValue(), keyData.getKeyMaterialType(), keyTemplate.getOutputPrefixType(), idRequirement);
         return new LegacyProtoKey(protoSerialization, InsecureSecretKeyAccess.get());
      }
   }

   private static MutableKeyCreationRegistry newRegistryWithLegacyFallback() {
      MutableKeyCreationRegistry registry = new MutableKeyCreationRegistry();

      try {
         registry.add(LEGACY_PROTO_KEY_CREATOR, LegacyProtoParameters.class);
         return registry;
      } catch (GeneralSecurityException e) {
         throw new IllegalStateException("unexpected error.", e);
      }
   }

   public static MutableKeyCreationRegistry globalInstance() {
      return globalInstance;
   }

   public synchronized void add(KeyCreator creator, Class parametersClass) throws GeneralSecurityException {
      KeyCreator<?> existingCreator = (KeyCreator)this.creators.get(parametersClass);
      if (existingCreator != null && !existingCreator.equals(creator)) {
         throw new GeneralSecurityException("Different key creator for parameters class " + parametersClass + " already inserted");
      } else {
         this.creators.put(parametersClass, creator);
      }
   }

   public Key createKey(Parameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      return this.createKeyTyped(parameters, idRequirement);
   }

   private synchronized Key createKeyTyped(Parameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException {
      Class<?> parametersClass = parameters.getClass();
      KeyCreator<?> creator = (KeyCreator)this.creators.get(parametersClass);
      if (creator == null) {
         throw new GeneralSecurityException("Cannot create a new key for parameters " + parameters + ": no key creator for this class was registered.");
      } else {
         return creator.createKey(parameters, idRequirement);
      }
   }

   public interface KeyCreator {
      Key createKey(Parameters parameters, @Nullable Integer idRequirement) throws GeneralSecurityException;
   }
}
