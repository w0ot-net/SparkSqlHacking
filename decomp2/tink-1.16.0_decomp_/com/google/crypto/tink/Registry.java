package com.google.crypto.tink;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyManagerRegistry;
import com.google.crypto.tink.internal.MutableParametersRegistry;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveSet;
import com.google.crypto.tink.prf.Prf;
import com.google.crypto.tink.proto.KeyData;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class Registry {
   private static final Logger logger = Logger.getLogger(Registry.class.getName());
   private static final ConcurrentMap catalogueMap = new ConcurrentHashMap();
   private static final Set ALLOWED_PRIMITIVES = Collections.unmodifiableSet(createAllowedPrimitives());

   static synchronized void reset() {
      KeyManagerRegistry.resetGlobalInstanceTestOnly();
      MutablePrimitiveRegistry.resetGlobalInstanceTestOnly();
      catalogueMap.clear();
   }

   /** @deprecated */
   @Deprecated
   public static synchronized void addCatalogue(String catalogueName, Catalogue catalogue) throws GeneralSecurityException {
      if (catalogueName == null) {
         throw new IllegalArgumentException("catalogueName must be non-null.");
      } else if (catalogue == null) {
         throw new IllegalArgumentException("catalogue must be non-null.");
      } else {
         if (catalogueMap.containsKey(catalogueName.toLowerCase(Locale.US))) {
            Catalogue<?> existing = (Catalogue)catalogueMap.get(catalogueName.toLowerCase(Locale.US));
            if (!catalogue.getClass().getName().equals(existing.getClass().getName())) {
               logger.warning("Attempted overwrite of a catalogueName catalogue for name " + catalogueName);
               throw new GeneralSecurityException("catalogue for name " + catalogueName + " has been already registered");
            }
         }

         catalogueMap.put(catalogueName.toLowerCase(Locale.US), catalogue);
      }
   }

   /** @deprecated */
   @Deprecated
   public static Catalogue getCatalogue(String catalogueName) throws GeneralSecurityException {
      if (catalogueName == null) {
         throw new IllegalArgumentException("catalogueName must be non-null.");
      } else {
         Catalogue<?> catalogue = (Catalogue)catalogueMap.get(catalogueName.toLowerCase(Locale.US));
         if (catalogue == null) {
            String error = String.format("no catalogue found for %s. ", catalogueName);
            if (catalogueName.toLowerCase(Locale.US).startsWith("tinkaead")) {
               error = error + "Maybe call AeadConfig.register().";
            }

            if (catalogueName.toLowerCase(Locale.US).startsWith("tinkdeterministicaead")) {
               error = error + "Maybe call DeterministicAeadConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkstreamingaead")) {
               error = error + "Maybe call StreamingAeadConfig.register().";
            } else if (!catalogueName.toLowerCase(Locale.US).startsWith("tinkhybriddecrypt") && !catalogueName.toLowerCase(Locale.US).startsWith("tinkhybridencrypt")) {
               if (catalogueName.toLowerCase(Locale.US).startsWith("tinkmac")) {
                  error = error + "Maybe call MacConfig.register().";
               } else if (!catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeysign") && !catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeyverify")) {
                  if (catalogueName.toLowerCase(Locale.US).startsWith("tink")) {
                     error = error + "Maybe call TinkConfig.register().";
                  }
               } else {
                  error = error + "Maybe call SignatureConfig.register().";
               }
            } else {
               error = error + "Maybe call HybridConfig.register().";
            }

            throw new GeneralSecurityException(error);
         } else {
            return catalogue;
         }
      }
   }

   public static synchronized void registerKeyManager(final KeyManager manager) throws GeneralSecurityException {
      registerKeyManager(manager, true);
   }

   private static Set createAllowedPrimitives() {
      HashSet<Class<?>> result = new HashSet();
      result.add(Aead.class);
      result.add(DeterministicAead.class);
      result.add(StreamingAead.class);
      result.add(HybridEncrypt.class);
      result.add(HybridDecrypt.class);
      result.add(Mac.class);
      result.add(Prf.class);
      result.add(PublicKeySign.class);
      result.add(PublicKeyVerify.class);
      return result;
   }

   public static synchronized void registerKeyManager(final KeyManager manager, boolean newKeyAllowed) throws GeneralSecurityException {
      if (manager == null) {
         throw new IllegalArgumentException("key manager must be non-null.");
      } else if (!ALLOWED_PRIMITIVES.contains(manager.getPrimitiveClass())) {
         throw new GeneralSecurityException("Registration of key managers for class " + manager.getPrimitiveClass() + " has been disabled. Please file an issue on https://github.com/tink-crypto/tink-java");
      } else if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
         throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
      } else {
         KeyManagerRegistry.globalInstance().registerKeyManager(manager, newKeyAllowed);
      }
   }

   /** @deprecated */
   @Deprecated
   public static synchronized void registerKeyManager(String typeUrl, final KeyManager manager) throws GeneralSecurityException {
      registerKeyManager(typeUrl, manager, true);
   }

   /** @deprecated */
   @Deprecated
   public static synchronized void registerKeyManager(String typeUrl, final KeyManager manager, boolean newKeyAllowed) throws GeneralSecurityException {
      if (manager == null) {
         throw new IllegalArgumentException("key manager must be non-null.");
      } else if (!typeUrl.equals(manager.getKeyType())) {
         throw new GeneralSecurityException("Manager does not support key type " + typeUrl + ".");
      } else {
         registerKeyManager(manager, newKeyAllowed);
      }
   }

   /** @deprecated */
   @Deprecated
   public static KeyManager getKeyManager(String typeUrl, Class primitiveClass) throws GeneralSecurityException {
      return KeyManagerRegistry.globalInstance().getKeyManager(typeUrl, primitiveClass);
   }

   /** @deprecated */
   @Deprecated
   public static KeyManager getUntypedKeyManager(String typeUrl) throws GeneralSecurityException {
      return KeyManagerRegistry.globalInstance().getUntypedKeyManager(typeUrl);
   }

   /** @deprecated */
   @Deprecated
   public static synchronized KeyData newKeyData(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
      KeyManager<?> manager = KeyManagerRegistry.globalInstance().getUntypedKeyManager(keyTemplate.getTypeUrl());
      if (KeyManagerRegistry.globalInstance().isNewKeyAllowed(keyTemplate.getTypeUrl())) {
         return manager.newKeyData(keyTemplate.getValue());
      } else {
         throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
      }
   }

   /** @deprecated */
   @Deprecated
   public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) throws GeneralSecurityException {
      byte[] serializedKeyTemplate = TinkProtoParametersFormat.serialize(keyTemplate.toParameters());

      try {
         return newKeyData(com.google.crypto.tink.proto.KeyTemplate.parseFrom(serializedKeyTemplate, ExtensionRegistryLite.getEmptyRegistry()));
      } catch (InvalidProtocolBufferException e) {
         throw new GeneralSecurityException("Failed to parse serialized parameters", e);
      }
   }

   /** @deprecated */
   @Deprecated
   public static synchronized MessageLite newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
      KeyManager<?> manager = getUntypedKeyManager(keyTemplate.getTypeUrl());
      if (KeyManagerRegistry.globalInstance().isNewKeyAllowed(keyTemplate.getTypeUrl())) {
         return manager.newKey(keyTemplate.getValue());
      } else {
         throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
      }
   }

   /** @deprecated */
   @Deprecated
   public static synchronized MessageLite newKey(String typeUrl, MessageLite format) throws GeneralSecurityException {
      KeyManager<?> manager = getUntypedKeyManager(typeUrl);
      if (KeyManagerRegistry.globalInstance().isNewKeyAllowed(typeUrl)) {
         return manager.newKey(format);
      } else {
         throw new GeneralSecurityException("newKey-operation not permitted for key type " + typeUrl);
      }
   }

   /** @deprecated */
   @Deprecated
   public static KeyData getPublicKeyData(String typeUrl, ByteString serializedPrivateKey) throws GeneralSecurityException {
      KeyManager<?> manager = getUntypedKeyManager(typeUrl);
      if (!(manager instanceof PrivateKeyManager)) {
         throw new GeneralSecurityException("manager for key type " + typeUrl + " is not a PrivateKeyManager");
      } else {
         return ((PrivateKeyManager)manager).getPublicKeyData(serializedPrivateKey);
      }
   }

   /** @deprecated */
   @Deprecated
   public static Object getPrimitive(String typeUrl, MessageLite key, Class primitiveClass) throws GeneralSecurityException {
      KeyManager<P> manager = KeyManagerRegistry.globalInstance().getKeyManager(typeUrl, primitiveClass);
      return manager.getPrimitive(key.toByteString());
   }

   public static Object getPrimitive(String typeUrl, ByteString serializedKey, Class primitiveClass) throws GeneralSecurityException {
      KeyManager<P> manager = KeyManagerRegistry.globalInstance().getKeyManager(typeUrl, primitiveClass);
      return manager.getPrimitive(serializedKey);
   }

   public static Object getPrimitive(String typeUrl, byte[] serializedKey, Class primitiveClass) throws GeneralSecurityException {
      return getPrimitive(typeUrl, ByteString.copyFrom(serializedKey), primitiveClass);
   }

   public static Object getPrimitive(KeyData keyData, Class primitiveClass) throws GeneralSecurityException {
      return getPrimitive(keyData.getTypeUrl(), keyData.getValue(), primitiveClass);
   }

   static Object getFullPrimitive(Key key, Class primitiveClass) throws GeneralSecurityException {
      return MutablePrimitiveRegistry.globalInstance().getPrimitive(key, primitiveClass);
   }

   public static Object wrap(PrimitiveSet primitiveSet, Class clazz) throws GeneralSecurityException {
      return MutablePrimitiveRegistry.globalInstance().wrap(primitiveSet, clazz);
   }

   public static Object wrap(PrimitiveSet primitiveSet) throws GeneralSecurityException {
      return wrap(primitiveSet, primitiveSet.getPrimitiveClass());
   }

   public static synchronized List keyTemplates() {
      return MutableParametersRegistry.globalInstance().getNames();
   }

   @Nullable
   public static Class getInputPrimitive(Class wrappedPrimitive) {
      try {
         return MutablePrimitiveRegistry.globalInstance().getInputPrimitiveClass(wrappedPrimitive);
      } catch (GeneralSecurityException var2) {
         return null;
      }
   }

   public static synchronized void restrictToFipsIfEmpty() throws GeneralSecurityException {
      KeyManagerRegistry.globalInstance().restrictToFipsIfEmptyAndGlobalInstance();
   }

   private Registry() {
   }
}
