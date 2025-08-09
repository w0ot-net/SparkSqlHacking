package com.google.crypto.tink.internal;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.PrivateKey;
import com.google.crypto.tink.PrivateKeyManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.security.GeneralSecurityException;

public class LegacyKeyManagerImpl implements KeyManager {
   final String typeUrl;
   final Class primitiveClass;
   final KeyData.KeyMaterialType keyMaterialType;
   final Parser protobufKeyParser;

   public static KeyManager create(String typeUrl, Class primitiveClass, KeyData.KeyMaterialType keyMaterialType, Parser protobufKeyParser) {
      return new LegacyKeyManagerImpl(typeUrl, primitiveClass, keyMaterialType, protobufKeyParser);
   }

   LegacyKeyManagerImpl(String typeUrl, Class primitiveClass, KeyData.KeyMaterialType keyMaterialType, Parser protobufKeyParser) {
      this.protobufKeyParser = protobufKeyParser;
      this.typeUrl = typeUrl;
      this.primitiveClass = primitiveClass;
      this.keyMaterialType = keyMaterialType;
   }

   public Object getPrimitive(ByteString serializedKey) throws GeneralSecurityException {
      ProtoKeySerialization serialization = ProtoKeySerialization.create(this.typeUrl, serializedKey, this.keyMaterialType, OutputPrefixType.RAW, (Integer)null);
      Key key = MutableSerializationRegistry.globalInstance().parseKey(serialization, InsecureSecretKeyAccess.get());
      return MutablePrimitiveRegistry.globalInstance().getPrimitive(key, this.primitiveClass);
   }

   public final Object getPrimitive(MessageLite key) throws GeneralSecurityException {
      return this.getPrimitive(key.toByteString());
   }

   public final MessageLite newKey(ByteString serializedKeyFormat) throws GeneralSecurityException {
      KeyData keyData = this.newKeyData(serializedKeyFormat);

      try {
         return (MessageLite)this.protobufKeyParser.parseFrom(keyData.getValue(), ExtensionRegistryLite.getEmptyRegistry());
      } catch (InvalidProtocolBufferException var4) {
         throw new GeneralSecurityException("Unexpectedly failed to parse key");
      }
   }

   public final MessageLite newKey(MessageLite keyFormat) throws GeneralSecurityException {
      return this.newKey(keyFormat.toByteString());
   }

   public final boolean doesSupport(String typeUrl) {
      return typeUrl.equals(this.getKeyType());
   }

   public final String getKeyType() {
      return this.typeUrl;
   }

   public int getVersion() {
      return 0;
   }

   public final KeyData newKeyData(ByteString serializedKeyFormat) throws GeneralSecurityException {
      ProtoParametersSerialization parametersSerialization = ProtoParametersSerialization.checkedCreate(KeyTemplate.newBuilder().setTypeUrl(this.typeUrl).setValue(serializedKeyFormat).setOutputPrefixType(OutputPrefixType.RAW).build());
      Parameters parameters = MutableSerializationRegistry.globalInstance().parseParameters(parametersSerialization);
      Key key = MutableKeyCreationRegistry.globalInstance().createKey(parameters, (Integer)null);
      ProtoKeySerialization keySerialization = (ProtoKeySerialization)MutableSerializationRegistry.globalInstance().serializeKey(key, ProtoKeySerialization.class, InsecureSecretKeyAccess.get());
      return KeyData.newBuilder().setTypeUrl(keySerialization.getTypeUrl()).setValue(keySerialization.getValue()).setKeyMaterialType(keySerialization.getKeyMaterialType()).build();
   }

   public final Class getPrimitiveClass() {
      return this.primitiveClass;
   }

   public static PrivateKeyManager createPrivateKeyManager(String typeUrl, Class primitiveClass, Parser protobufKeyParser) {
      return new LegacyPrivateKeyManagerImpl(typeUrl, primitiveClass, protobufKeyParser);
   }

   private static class LegacyPrivateKeyManagerImpl extends LegacyKeyManagerImpl implements PrivateKeyManager {
      protected LegacyPrivateKeyManagerImpl(String typeUrl, Class primitiveClass, Parser protobufKeyParser) {
         super(typeUrl, primitiveClass, KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, protobufKeyParser);
      }

      public KeyData getPublicKeyData(ByteString serializedKey) throws GeneralSecurityException {
         ProtoKeySerialization serialization = ProtoKeySerialization.create(this.typeUrl, serializedKey, this.keyMaterialType, OutputPrefixType.RAW, (Integer)null);
         Key key = MutableSerializationRegistry.globalInstance().parseKey(serialization, InsecureSecretKeyAccess.get());
         if (!(key instanceof PrivateKey)) {
            throw new GeneralSecurityException("Key not private key");
         } else {
            Key publicKey = ((PrivateKey)key).getPublicKey();
            ProtoKeySerialization publicKeySerialization = (ProtoKeySerialization)MutableSerializationRegistry.globalInstance().serializeKey(publicKey, ProtoKeySerialization.class, InsecureSecretKeyAccess.get());
            return KeyData.newBuilder().setTypeUrl(publicKeySerialization.getTypeUrl()).setValue(publicKeySerialization.getValue()).setKeyMaterialType(publicKeySerialization.getKeyMaterialType()).build();
         }
      }
   }
}
