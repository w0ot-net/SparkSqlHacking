package com.google.crypto.tink.prf.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.prf.AesCmacPrfKey;
import com.google.crypto.tink.prf.AesCmacPrfParameters;
import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
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
public final class AesCmacPrfProtoSerialization {
   private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesCmacPrfKey";
   private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.AesCmacPrfKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(AesCmacPrfProtoSerialization::serializeParameters, AesCmacPrfParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer KEY_SERIALIZER;
   private static final KeyParser KEY_PARSER;

   private static ProtoParametersSerialization serializeParameters(AesCmacPrfParameters parameters) {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.AesCmacPrfKey").setValue(AesCmacPrfKeyFormat.newBuilder().setKeySize(parameters.getKeySizeBytes()).build().toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build());
   }

   private static ProtoKeySerialization serializeKey(AesCmacPrfKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.AesCmacPrfKey", com.google.crypto.tink.proto.AesCmacPrfKey.newBuilder().setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build().toByteString(), KeyData.KeyMaterialType.SYMMETRIC, OutputPrefixType.RAW, key.getIdRequirementOrNull());
   }

   private static AesCmacPrfParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.AesCmacPrfKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to AesCmacPrfProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         AesCmacPrfKeyFormat format;
         try {
            format = AesCmacPrfKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing AesCmacPrfParameters failed: ", e);
         }

         if (format.getVersion() != 0) {
            throw new GeneralSecurityException("Parsing AesCmacPrfParameters failed: unknown Version " + format.getVersion());
         } else if (serialization.getKeyTemplate().getOutputPrefixType() != OutputPrefixType.RAW) {
            throw new GeneralSecurityException("Parsing AesCmacPrfParameters failed: only RAW output prefix type is accepted");
         } else {
            return AesCmacPrfParameters.create(format.getKeySize());
         }
      }
   }

   private static AesCmacPrfKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.AesCmacPrfKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to AesCmacPrfProtoSerialization.parseKey");
      } else {
         try {
            com.google.crypto.tink.proto.AesCmacPrfKey protoKey = com.google.crypto.tink.proto.AesCmacPrfKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else if (serialization.getIdRequirementOrNull() != null) {
               throw new GeneralSecurityException("ID requirement must be null.");
            } else {
               AesCmacPrfParameters parameters = AesCmacPrfParameters.create(protoKey.getKeyValue().size());
               return AesCmacPrfKey.create(parameters, SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access)));
            }
         } catch (InvalidProtocolBufferException var4) {
            throw new GeneralSecurityException("Parsing AesCmacPrfKey failed");
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

   private AesCmacPrfProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(AesCmacPrfProtoSerialization::parseParameters, TYPE_URL_BYTES, ProtoParametersSerialization.class);
      KEY_SERIALIZER = KeySerializer.create(AesCmacPrfProtoSerialization::serializeKey, AesCmacPrfKey.class, ProtoKeySerialization.class);
      KEY_PARSER = KeyParser.create(AesCmacPrfProtoSerialization::parseKey, TYPE_URL_BYTES, ProtoKeySerialization.class);
   }
}
