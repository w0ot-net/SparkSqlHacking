package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.internal.InternalConfiguration;
import com.google.crypto.tink.internal.MonitoringAnnotations;
import com.google.crypto.tink.internal.MutableKeyCreationRegistry;
import com.google.crypto.tink.internal.MutableParametersRegistry;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.PrimitiveSet;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.internal.InternalKeyHandle;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.InlineMe;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public final class KeysetHandle {
   private final Keyset keyset;
   private final List entries;
   private final MonitoringAnnotations annotations;

   private static KeyStatus parseStatus(KeyStatusType in) throws GeneralSecurityException {
      switch (in) {
         case ENABLED:
            return KeyStatus.ENABLED;
         case DISABLED:
            return KeyStatus.DISABLED;
         case DESTROYED:
            return KeyStatus.DESTROYED;
         default:
            throw new GeneralSecurityException("Unknown key status");
      }
   }

   private static KeyStatusType serializeStatus(KeyStatus in) {
      if (KeyStatus.ENABLED.equals(in)) {
         return KeyStatusType.ENABLED;
      } else if (KeyStatus.DISABLED.equals(in)) {
         return KeyStatusType.DISABLED;
      } else if (KeyStatus.DESTROYED.equals(in)) {
         return KeyStatusType.DESTROYED;
      } else {
         throw new IllegalStateException("Unknown key status");
      }
   }

   private static List getEntriesFromKeyset(Keyset keyset) {
      List<Entry> result = new ArrayList(keyset.getKeyCount());

      for(Keyset.Key protoKey : keyset.getKeyList()) {
         int id = protoKey.getKeyId();

         try {
            Key key = toKey(protoKey);
            result.add(new Entry(key, parseStatus(protoKey.getStatus()), id, id == keyset.getPrimaryKeyId()));
         } catch (GeneralSecurityException var6) {
            result.add((Object)null);
         }
      }

      return Collections.unmodifiableList(result);
   }

   private Entry entryByIndex(int i) {
      if (this.entries.get(i) == null) {
         throw new IllegalStateException("Keyset-Entry at position " + i + " has wrong status or key parsing failed");
      } else {
         return (Entry)this.entries.get(i);
      }
   }

   public static Builder.Entry importKey(Key key) {
      Builder.Entry importedEntry = new Builder.Entry(key);
      Integer requirement = key.getIdRequirementOrNull();
      if (requirement != null) {
         importedEntry.withFixedId(requirement);
      }

      return importedEntry;
   }

   public static Builder.Entry generateEntryFromParametersName(String parametersName) throws GeneralSecurityException {
      Parameters parameters = MutableParametersRegistry.globalInstance().get(parametersName);
      return new Builder.Entry(parameters);
   }

   public static Builder.Entry generateEntryFromParameters(Parameters parameters) {
      return new Builder.Entry(parameters);
   }

   private KeysetHandle(Keyset keyset, List entries) {
      this.keyset = keyset;
      this.entries = entries;
      this.annotations = MonitoringAnnotations.EMPTY;
   }

   private KeysetHandle(Keyset keyset, List entries, MonitoringAnnotations annotations) {
      this.keyset = keyset;
      this.entries = entries;
      this.annotations = annotations;
   }

   static final KeysetHandle fromKeyset(Keyset keyset) throws GeneralSecurityException {
      assertEnoughKeyMaterial(keyset);
      List<Entry> entries = getEntriesFromKeyset(keyset);
      return new KeysetHandle(keyset, entries);
   }

   static final KeysetHandle fromKeysetAndAnnotations(Keyset keyset, MonitoringAnnotations annotations) throws GeneralSecurityException {
      assertEnoughKeyMaterial(keyset);
      List<Entry> entries = getEntriesFromKeyset(keyset);
      return new KeysetHandle(keyset, entries, annotations);
   }

   Keyset getKeyset() {
      return this.keyset;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public static Builder newBuilder(KeysetHandle handle) {
      Builder builder = new Builder();

      for(int i = 0; i < handle.size(); ++i) {
         Entry entry = (Entry)handle.entries.get(i);
         if (entry == null) {
            builder.setErrorToThrow(new GeneralSecurityException("Keyset-Entry in original keyset at position " + i + " has wrong status or key parsing failed"));
            break;
         }

         Builder.Entry builderEntry = importKey(entry.getKey()).withFixedId(entry.getId());
         builderEntry.setStatus(entry.getStatus());
         if (entry.isPrimary()) {
            builderEntry.makePrimary();
         }

         builder.addEntry(builderEntry);
      }

      return builder;
   }

   public Entry getPrimary() {
      for(Entry entry : this.entries) {
         if (entry != null && entry.isPrimary()) {
            if (entry.getStatus() != KeyStatus.ENABLED) {
               throw new IllegalStateException("Keyset has primary which isn't enabled");
            }

            return entry;
         }
      }

      throw new IllegalStateException("Keyset has no valid primary");
   }

   public int size() {
      return this.entries.size();
   }

   public Entry getAt(int i) {
      if (i >= 0 && i < this.size()) {
         return this.entryByIndex(i);
      } else {
         throw new IndexOutOfBoundsException("Invalid index " + i + " for keyset of size " + this.size());
      }
   }

   /** @deprecated */
   @Deprecated
   public List getKeys() {
      ArrayList<KeyHandle> result = new ArrayList();

      for(Keyset.Key key : this.keyset.getKeyList()) {
         KeyData keyData = key.getKeyData();
         result.add(new InternalKeyHandle(new ProtoKey(keyData, KeyTemplate.fromProto(key.getOutputPrefixType())), key.getStatus(), key.getKeyId()));
      }

      return Collections.unmodifiableList(result);
   }

   /** @deprecated */
   @Deprecated
   public KeysetInfo getKeysetInfo() {
      return Util.getKeysetInfo(this.keyset);
   }

   public static final KeysetHandle generateNew(Parameters parameters) throws GeneralSecurityException {
      return newBuilder().addEntry(generateEntryFromParameters(parameters).withRandomId().makePrimary()).build();
   }

   /** @deprecated */
   @Deprecated
   public static final KeysetHandle generateNew(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
      return generateNew(TinkProtoParametersFormat.parse(keyTemplate.toByteArray()));
   }

   public static final KeysetHandle generateNew(KeyTemplate keyTemplate) throws GeneralSecurityException {
      return generateNew(keyTemplate.toParameters());
   }

   /** @deprecated */
   @Deprecated
   public static final KeysetHandle createFromKey(KeyHandle keyHandle, KeyAccess access) throws GeneralSecurityException {
      KeysetManager km = KeysetManager.withEmptyKeyset().add(keyHandle);
      km.setPrimary(km.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
      return km.getKeysetHandle();
   }

   /** @deprecated */
   @Deprecated
   public static final KeysetHandle read(KeysetReader reader, Aead masterKey) throws GeneralSecurityException, IOException {
      return readWithAssociatedData(reader, masterKey, new byte[0]);
   }

   /** @deprecated */
   @Deprecated
   public static final KeysetHandle readWithAssociatedData(KeysetReader reader, Aead masterKey, byte[] associatedData) throws GeneralSecurityException, IOException {
      EncryptedKeyset encryptedKeyset = reader.readEncrypted();
      assertEnoughEncryptedKeyMaterial(encryptedKeyset);
      return fromKeyset(decrypt(encryptedKeyset, masterKey, associatedData));
   }

   /** @deprecated */
   @Deprecated
   public static final KeysetHandle readNoSecret(KeysetReader reader) throws GeneralSecurityException, IOException {
      byte[] serializedKeyset;
      try {
         serializedKeyset = reader.read().toByteArray();
      } catch (InvalidProtocolBufferException var3) {
         throw new GeneralSecurityException("invalid keyset");
      }

      return readNoSecret(serializedKeyset);
   }

   /** @deprecated */
   @Deprecated
   public static final KeysetHandle readNoSecret(final byte[] serialized) throws GeneralSecurityException {
      try {
         Keyset keyset = Keyset.parseFrom(serialized, ExtensionRegistryLite.getEmptyRegistry());
         assertNoSecretKeyMaterial(keyset);
         return fromKeyset(keyset);
      } catch (InvalidProtocolBufferException var2) {
         throw new GeneralSecurityException("invalid keyset");
      }
   }

   /** @deprecated */
   @Deprecated
   public void write(KeysetWriter keysetWriter, Aead masterKey) throws GeneralSecurityException, IOException {
      this.writeWithAssociatedData(keysetWriter, masterKey, new byte[0]);
   }

   /** @deprecated */
   @Deprecated
   public void writeWithAssociatedData(KeysetWriter keysetWriter, Aead masterKey, byte[] associatedData) throws GeneralSecurityException, IOException {
      EncryptedKeyset encryptedKeyset = encrypt(this.keyset, masterKey, associatedData);
      keysetWriter.write(encryptedKeyset);
   }

   /** @deprecated */
   @Deprecated
   public void writeNoSecret(KeysetWriter writer) throws GeneralSecurityException, IOException {
      assertNoSecretKeyMaterial(this.keyset);
      writer.write(this.keyset);
   }

   private static EncryptedKeyset encrypt(Keyset keyset, Aead masterKey, byte[] associatedData) throws GeneralSecurityException {
      byte[] encryptedKeyset = masterKey.encrypt(keyset.toByteArray(), associatedData);
      return EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(encryptedKeyset)).setKeysetInfo(Util.getKeysetInfo(keyset)).build();
   }

   private static Keyset decrypt(EncryptedKeyset encryptedKeyset, Aead masterKey, byte[] associatedData) throws GeneralSecurityException {
      try {
         Keyset keyset = Keyset.parseFrom(masterKey.decrypt(encryptedKeyset.getEncryptedKeyset().toByteArray(), associatedData), ExtensionRegistryLite.getEmptyRegistry());
         assertEnoughKeyMaterial(keyset);
         return keyset;
      } catch (InvalidProtocolBufferException var4) {
         throw new GeneralSecurityException("invalid keyset, corrupted key material");
      }
   }

   public KeysetHandle getPublicKeysetHandle() throws GeneralSecurityException {
      if (this.keyset == null) {
         throw new GeneralSecurityException("cleartext keyset is not available");
      } else {
         Keyset.Builder publicKeysetBuilder = Keyset.newBuilder();
         List<Entry> publicEntries = new ArrayList(this.entries.size());
         int i = 0;

         for(Entry entry : this.entries) {
            Entry publicEntry;
            Keyset.Key publicProtoKey;
            if (entry != null && entry.getKey() instanceof PrivateKey) {
               Key publicKey = ((PrivateKey)entry.getKey()).getPublicKey();
               publicEntry = new Entry(publicKey, entry.getStatus(), entry.getId(), entry.isPrimary());
               publicProtoKey = createKeysetKey(publicKey, entry.getStatus(), entry.getId());
            } else {
               Keyset.Key protoKey = this.keyset.getKey(i);
               KeyData keyData = getPublicKeyDataFromRegistry(protoKey.getKeyData());
               publicProtoKey = protoKey.toBuilder().setKeyData(keyData).build();

               try {
                  Key publicKey = toKey(publicProtoKey);
                  int id = publicProtoKey.getKeyId();
                  publicEntry = new Entry(publicKey, parseStatus(publicProtoKey.getStatus()), id, id == this.keyset.getPrimaryKeyId());
               } catch (GeneralSecurityException var12) {
                  publicEntry = null;
               }
            }

            publicKeysetBuilder.addKey(publicProtoKey);
            publicEntries.add(publicEntry);
            ++i;
         }

         publicKeysetBuilder.setPrimaryKeyId(this.keyset.getPrimaryKeyId());
         return new KeysetHandle(publicKeysetBuilder.build(), publicEntries, this.annotations);
      }
   }

   private static KeyData getPublicKeyDataFromRegistry(KeyData privateKeyData) throws GeneralSecurityException {
      if (privateKeyData.getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
         throw new GeneralSecurityException("The keyset contains a non-private key");
      } else {
         KeyData publicKeyData = Registry.getPublicKeyData(privateKeyData.getTypeUrl(), privateKeyData.getValue());
         return publicKeyData;
      }
   }

   public String toString() {
      return this.getKeysetInfo().toString();
   }

   private static void assertNoSecretKeyMaterial(Keyset keyset) throws GeneralSecurityException {
      for(Keyset.Key key : keyset.getKeyList()) {
         if (key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
            throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", key.getKeyData().getKeyMaterialType().name(), key.getKeyData().getTypeUrl()));
         }
      }

   }

   private static void assertEnoughKeyMaterial(Keyset keyset) throws GeneralSecurityException {
      if (keyset == null || keyset.getKeyCount() <= 0) {
         throw new GeneralSecurityException("empty keyset");
      }
   }

   private static void assertEnoughEncryptedKeyMaterial(EncryptedKeyset keyset) throws GeneralSecurityException {
      if (keyset == null || keyset.getEncryptedKeyset().size() == 0) {
         throw new GeneralSecurityException("empty keyset");
      }
   }

   private Object getPrimitiveWithKnownInputPrimitive(InternalConfiguration config, Class classObject, Class inputPrimitiveClassObject) throws GeneralSecurityException {
      Util.validateKeyset(this.keyset);
      PrimitiveSet.Builder<B> builder = PrimitiveSet.newBuilder(inputPrimitiveClassObject);
      builder.setAnnotations(this.annotations);

      for(int i = 0; i < this.size(); ++i) {
         Keyset.Key protoKey = this.keyset.getKey(i);
         if (protoKey.getStatus().equals(KeyStatusType.ENABLED)) {
            Entry entry = (Entry)this.entries.get(i);
            if (entry == null) {
               throw new GeneralSecurityException("Key parsing of key with index " + i + " and type_url " + protoKey.getKeyData().getTypeUrl() + " failed, unable to get primitive");
            }

            Key key = entry.getKey();

            B fullPrimitive;
            try {
               fullPrimitive = (B)config.getPrimitive(key, inputPrimitiveClassObject);
            } catch (GeneralSecurityException e) {
               throw new GeneralSecurityException("Unable to get primitive " + inputPrimitiveClassObject + " for key of type " + protoKey.getKeyData().getTypeUrl() + ", see https://developers.google.com/tink/faq/registration_errors", e);
            }

            if (protoKey.getKeyId() == this.keyset.getPrimaryKeyId()) {
               builder.addPrimaryFullPrimitive(fullPrimitive, key, protoKey);
            } else {
               builder.addFullPrimitive(fullPrimitive, key, protoKey);
            }
         }
      }

      return config.wrap(builder.build(), classObject);
   }

   public Object getPrimitive(Configuration configuration, Class targetClassObject) throws GeneralSecurityException {
      if (!(configuration instanceof InternalConfiguration)) {
         throw new GeneralSecurityException("Currently only subclasses of InternalConfiguration are accepted");
      } else {
         InternalConfiguration internalConfig = (InternalConfiguration)configuration;
         Class<?> inputPrimitiveClassObject = internalConfig.getInputPrimitiveClass(targetClassObject);
         if (inputPrimitiveClassObject == null) {
            throw new GeneralSecurityException("No wrapper found for " + targetClassObject.getName());
         } else {
            return this.getPrimitiveWithKnownInputPrimitive(internalConfig, targetClassObject, inputPrimitiveClassObject);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this.getPrimitive(RegistryConfiguration.get(), targetClassObject)",
      imports = {"com.google.crypto.tink.RegistryConfiguration"}
   )
   public Object getPrimitive(Class targetClassObject) throws GeneralSecurityException {
      return this.getPrimitive(RegistryConfiguration.get(), targetClassObject);
   }

   /** @deprecated */
   @Deprecated
   public KeyHandle primaryKey() throws GeneralSecurityException {
      int primaryKeyId = this.keyset.getPrimaryKeyId();

      for(Keyset.Key key : this.keyset.getKeyList()) {
         if (key.getKeyId() == primaryKeyId) {
            return new InternalKeyHandle(new ProtoKey(key.getKeyData(), KeyTemplate.fromProto(key.getOutputPrefixType())), key.getStatus(), key.getKeyId());
         }
      }

      throw new GeneralSecurityException("No primary key found in keyset.");
   }

   public boolean equalsKeyset(KeysetHandle other) {
      if (this.size() != other.size()) {
         return false;
      } else {
         boolean primaryFound = false;

         for(int i = 0; i < this.size(); ++i) {
            Entry thisEntry = (Entry)this.entries.get(i);
            Entry otherEntry = (Entry)other.entries.get(i);
            if (thisEntry == null) {
               return false;
            }

            if (otherEntry == null) {
               return false;
            }

            if (!thisEntry.equalsEntry(otherEntry)) {
               return false;
            }

            primaryFound |= thisEntry.isPrimary;
         }

         if (!primaryFound) {
            return false;
         } else {
            return true;
         }
      }
   }

   private static ProtoKeySerialization toProtoKeySerialization(Keyset.Key protoKey) throws GeneralSecurityException {
      int id = protoKey.getKeyId();
      Integer idRequirement = protoKey.getOutputPrefixType() == OutputPrefixType.RAW ? null : id;
      return ProtoKeySerialization.create(protoKey.getKeyData().getTypeUrl(), protoKey.getKeyData().getValue(), protoKey.getKeyData().getKeyMaterialType(), protoKey.getOutputPrefixType(), idRequirement);
   }

   private static Key toKey(Keyset.Key protoKey) throws GeneralSecurityException {
      ProtoKeySerialization protoKeySerialization = toProtoKeySerialization(protoKey);
      return MutableSerializationRegistry.globalInstance().parseKeyWithLegacyFallback(protoKeySerialization, InsecureSecretKeyAccess.get());
   }

   private static Keyset.Key toKeysetKey(int id, KeyStatusType status, ProtoKeySerialization protoKeySerialization) {
      return Keyset.Key.newBuilder().setKeyData(KeyData.newBuilder().setTypeUrl(protoKeySerialization.getTypeUrl()).setValue(protoKeySerialization.getValue()).setKeyMaterialType(protoKeySerialization.getKeyMaterialType())).setStatus(status).setKeyId(id).setOutputPrefixType(protoKeySerialization.getOutputPrefixType()).build();
   }

   private static Keyset.Key createKeysetKey(Key key, KeyStatus keyStatus, int id) throws GeneralSecurityException {
      ProtoKeySerialization serializedKey = (ProtoKeySerialization)MutableSerializationRegistry.globalInstance().serializeKey(key, ProtoKeySerialization.class, InsecureSecretKeyAccess.get());
      Integer idRequirement = serializedKey.getIdRequirementOrNull();
      if (idRequirement != null && idRequirement != id) {
         throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
      } else {
         return toKeysetKey(id, serializeStatus(keyStatus), serializedKey);
      }
   }

   public static final class Builder {
      private final List entries = new ArrayList();
      @Nullable
      private GeneralSecurityException errorToThrow = null;
      private MonitoringAnnotations annotations;
      private boolean buildCalled;

      public Builder() {
         this.annotations = MonitoringAnnotations.EMPTY;
         this.buildCalled = false;
      }

      private void clearPrimary() {
         for(Entry entry : this.entries) {
            entry.isPrimary = false;
         }

      }

      @CanIgnoreReturnValue
      public Builder addEntry(Entry entry) {
         if (entry.builder != null) {
            throw new IllegalStateException("Entry has already been added to a KeysetHandle.Builder");
         } else {
            if (entry.isPrimary) {
               this.clearPrimary();
            }

            entry.builder = this;
            this.entries.add(entry);
            return this;
         }
      }

      @CanIgnoreReturnValue
      @Alpha
      public Builder setMonitoringAnnotations(MonitoringAnnotations annotations) {
         this.annotations = annotations;
         return this;
      }

      public int size() {
         return this.entries.size();
      }

      public Entry getAt(int i) {
         return (Entry)this.entries.get(i);
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      public Entry removeAt(int i) {
         return (Entry)this.entries.remove(i);
      }

      @CanIgnoreReturnValue
      public Builder deleteAt(int i) {
         this.entries.remove(i);
         return this;
      }

      private static void checkIdAssignments(List entries) throws GeneralSecurityException {
         for(int i = 0; i < entries.size() - 1; ++i) {
            if (((Entry)entries.get(i)).strategy == KeysetHandle.Builder.KeyIdStrategy.RANDOM_ID && ((Entry)entries.get(i + 1)).strategy != KeysetHandle.Builder.KeyIdStrategy.RANDOM_ID) {
               throw new GeneralSecurityException("Entries with 'withRandomId()' may only be followed by other entries with 'withRandomId()'.");
            }
         }

      }

      private void setErrorToThrow(GeneralSecurityException errorToThrow) {
         this.errorToThrow = errorToThrow;
      }

      private static int randomIdNotInSet(Set ids) {
         int id;
         for(id = 0; id == 0 || ids.contains(id); id = com.google.crypto.tink.internal.Util.randKeyId()) {
         }

         return id;
      }

      private static int getNextIdFromBuilderEntry(Entry builderEntry, Set idsSoFar) throws GeneralSecurityException {
         int id = 0;
         if (builderEntry.strategy == null) {
            throw new GeneralSecurityException("No ID was set (with withFixedId or withRandomId)");
         } else {
            if (builderEntry.strategy == KeysetHandle.Builder.KeyIdStrategy.RANDOM_ID) {
               id = randomIdNotInSet(idsSoFar);
            } else {
               id = builderEntry.strategy.getFixedId();
            }

            return id;
         }
      }

      public KeysetHandle build() throws GeneralSecurityException {
         if (this.errorToThrow != null) {
            throw new GeneralSecurityException("Cannot build keyset due to error in original", this.errorToThrow);
         } else if (this.buildCalled) {
            throw new GeneralSecurityException("KeysetHandle.Builder#build must only be called once");
         } else {
            this.buildCalled = true;
            Keyset.Builder keysetBuilder = Keyset.newBuilder();
            List<Entry> handleEntries = new ArrayList(this.entries.size());
            Integer primaryId = null;
            checkIdAssignments(this.entries);
            Set<Integer> idsSoFar = new HashSet();

            for(Entry builderEntry : this.entries) {
               if (builderEntry.keyStatus == null) {
                  throw new GeneralSecurityException("Key Status not set.");
               }

               int id = getNextIdFromBuilderEntry(builderEntry, idsSoFar);
               if (idsSoFar.contains(id)) {
                  throw new GeneralSecurityException("Id " + id + " is used twice in the keyset");
               }

               idsSoFar.add(id);
               Keyset.Key keysetKey;
               Entry handleEntry;
               if (builderEntry.key != null) {
                  handleEntry = new Entry(builderEntry.key, builderEntry.keyStatus, id, builderEntry.isPrimary);
                  keysetKey = KeysetHandle.createKeysetKey(builderEntry.key, builderEntry.keyStatus, id);
               } else {
                  Integer idRequirement = builderEntry.parameters.hasIdRequirement() ? id : null;
                  Key key = MutableKeyCreationRegistry.globalInstance().createKey(builderEntry.parameters, idRequirement);
                  handleEntry = new Entry(key, builderEntry.keyStatus, id, builderEntry.isPrimary);
                  keysetKey = KeysetHandle.createKeysetKey(key, builderEntry.keyStatus, id);
               }

               keysetBuilder.addKey(keysetKey);
               if (builderEntry.isPrimary) {
                  if (primaryId != null) {
                     throw new GeneralSecurityException("Two primaries were set");
                  }

                  primaryId = id;
                  if (builderEntry.keyStatus != KeyStatus.ENABLED) {
                     throw new GeneralSecurityException("Primary key is not enabled");
                  }
               }

               handleEntries.add(handleEntry);
            }

            if (primaryId == null) {
               throw new GeneralSecurityException("No primary was set");
            } else {
               keysetBuilder.setPrimaryKeyId(primaryId);
               Keyset keyset = keysetBuilder.build();
               KeysetHandle.assertEnoughKeyMaterial(keyset);
               return new KeysetHandle(keyset, handleEntries, this.annotations);
            }
         }
      }

      private static class KeyIdStrategy {
         private static final KeyIdStrategy RANDOM_ID = new KeyIdStrategy();
         private final int fixedId;

         private KeyIdStrategy() {
            this.fixedId = 0;
         }

         private KeyIdStrategy(int id) {
            this.fixedId = id;
         }

         private static KeyIdStrategy randomId() {
            return RANDOM_ID;
         }

         private static KeyIdStrategy fixedId(int id) {
            return new KeyIdStrategy(id);
         }

         private int getFixedId() {
            return this.fixedId;
         }
      }

      public static final class Entry {
         private boolean isPrimary;
         private KeyStatus keyStatus;
         @Nullable
         private final Key key;
         @Nullable
         private final Parameters parameters;
         private KeyIdStrategy strategy;
         @Nullable
         private Builder builder;

         private Entry(Key key) {
            this.keyStatus = KeyStatus.ENABLED;
            this.strategy = null;
            this.builder = null;
            this.key = key;
            this.parameters = null;
         }

         private Entry(Parameters parameters) {
            this.keyStatus = KeyStatus.ENABLED;
            this.strategy = null;
            this.builder = null;
            this.key = null;
            this.parameters = parameters;
         }

         @CanIgnoreReturnValue
         public Entry makePrimary() {
            if (this.builder != null) {
               this.builder.clearPrimary();
            }

            this.isPrimary = true;
            return this;
         }

         public boolean isPrimary() {
            return this.isPrimary;
         }

         @CanIgnoreReturnValue
         public Entry setStatus(KeyStatus status) {
            this.keyStatus = status;
            return this;
         }

         public KeyStatus getStatus() {
            return this.keyStatus;
         }

         @CanIgnoreReturnValue
         public Entry withFixedId(int id) {
            this.strategy = KeysetHandle.Builder.KeyIdStrategy.fixedId(id);
            return this;
         }

         @CanIgnoreReturnValue
         public Entry withRandomId() {
            this.strategy = KeysetHandle.Builder.KeyIdStrategy.randomId();
            return this;
         }
      }
   }

   @Immutable
   public static final class Entry {
      private final Key key;
      private final KeyStatus keyStatus;
      private final int id;
      private final boolean isPrimary;

      private Entry(Key key, KeyStatus keyStatus, int id, boolean isPrimary) {
         this.key = key;
         this.keyStatus = keyStatus;
         this.id = id;
         this.isPrimary = isPrimary;
      }

      public Key getKey() {
         return this.key;
      }

      public KeyStatus getStatus() {
         return this.keyStatus;
      }

      public int getId() {
         return this.id;
      }

      public boolean isPrimary() {
         return this.isPrimary;
      }

      private boolean equalsEntry(Entry other) {
         if (other.isPrimary != this.isPrimary) {
            return false;
         } else if (!other.keyStatus.equals(this.keyStatus)) {
            return false;
         } else if (other.id != this.id) {
            return false;
         } else {
            return other.key.equalsKey(this.key);
         }
      }
   }
}
