package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.aead.ChaCha20Poly1305Key;
import com.google.crypto.tink.aead.ChaCha20Poly1305Parameters;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.ChaCha20Poly1305KeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@AccessesPartialKey
public final class ChaCha20Poly1305ProtoSerialization {
   private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
   private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(ChaCha20Poly1305ProtoSerialization::serializeParameters, ChaCha20Poly1305Parameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer KEY_SERIALIZER;
   private static final KeyParser KEY_PARSER;

   private static OutputPrefixType toProtoOutputPrefixType(ChaCha20Poly1305Parameters.Variant variant) throws GeneralSecurityException {
      if (ChaCha20Poly1305Parameters.Variant.TINK.equals(variant)) {
         return OutputPrefixType.TINK;
      } else if (ChaCha20Poly1305Parameters.Variant.CRUNCHY.equals(variant)) {
         return OutputPrefixType.CRUNCHY;
      } else if (ChaCha20Poly1305Parameters.Variant.NO_PREFIX.equals(variant)) {
         return OutputPrefixType.RAW;
      } else {
         throw new GeneralSecurityException("Unable to serialize variant: " + variant);
      }
   }

   private static ChaCha20Poly1305Parameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
      switch (outputPrefixType) {
         case TINK:
            return ChaCha20Poly1305Parameters.Variant.TINK;
         case CRUNCHY:
         case LEGACY:
            return ChaCha20Poly1305Parameters.Variant.CRUNCHY;
         case RAW:
            return ChaCha20Poly1305Parameters.Variant.NO_PREFIX;
         default:
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
      }
   }

   private static ProtoParametersSerialization serializeParameters(ChaCha20Poly1305Parameters parameters) throws GeneralSecurityException {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").setValue(ChaCha20Poly1305KeyFormat.getDefaultInstance().toByteString()).setOutputPrefixType(toProtoOutputPrefixType(parameters.getVariant())).build());
   }

   private static ProtoKeySerialization serializeKey(ChaCha20Poly1305Key key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", com.google.crypto.tink.proto.ChaCha20Poly1305Key.newBuilder().setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build().toByteString(), KeyData.KeyMaterialType.SYMMETRIC, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static ChaCha20Poly1305Parameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
         throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         try {
            ChaCha20Poly1305KeyFormat var1 = ChaCha20Poly1305KeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing ChaCha20Poly1305Parameters failed: ", e);
         }

         return ChaCha20Poly1305Parameters.create(toVariant(serialization.getKeyTemplate().getOutputPrefixType()));
      }
   }

   private static ChaCha20Poly1305Key parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
         throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseKey");
      } else {
         try {
            com.google.crypto.tink.proto.ChaCha20Poly1305Key protoKey = com.google.crypto.tink.proto.ChaCha20Poly1305Key.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               return ChaCha20Poly1305Key.create(toVariant(serialization.getOutputPrefixType()), SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access)), serialization.getIdRequirementOrNull());
            }
         } catch (InvalidProtocolBufferException var3) {
            throw new GeneralSecurityException("Parsing ChaCha20Poly1305Key failed");
         }
      }
   }

   public static void register() throws GeneralSecurityException {
      register(MutableSerializationRegistry.globalInstance());
   }

   public static void register(MutableSerializationRegistry registry) throws GeneralSecurityException {
      registry.registerParametersSerializer(PARAMETERS_SERIALIZER);
      registry.registerParametersParser(PARAMETERS_PARSER);
      registry.registerKeySerializer(KEY_SERIALIZER);
      registry.registerKeyParser(KEY_PARSER);
   }

   private ChaCha20Poly1305ProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(ChaCha20Poly1305ProtoSerialization::parseParameters, TYPE_URL_BYTES, ProtoParametersSerialization.class);
      KEY_SERIALIZER = KeySerializer.create(ChaCha20Poly1305ProtoSerialization::serializeKey, ChaCha20Poly1305Key.class, ProtoKeySerialization.class);
      KEY_PARSER = KeyParser.create(ChaCha20Poly1305ProtoSerialization::parseKey, TYPE_URL_BYTES, ProtoKeySerialization.class);
   }
}
