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
import com.google.crypto.tink.prf.HkdfPrfKey;
import com.google.crypto.tink.prf.HkdfPrfParameters;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
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
public final class HkdfPrfProtoSerialization {
   private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
   private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.HkdfPrfKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(HkdfPrfProtoSerialization::serializeParameters, HkdfPrfParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer KEY_SERIALIZER;
   private static final KeyParser KEY_PARSER;

   private static HashType toProtoHashType(HkdfPrfParameters.HashType hashType) throws GeneralSecurityException {
      if (HkdfPrfParameters.HashType.SHA1.equals(hashType)) {
         return HashType.SHA1;
      } else if (HkdfPrfParameters.HashType.SHA224.equals(hashType)) {
         return HashType.SHA224;
      } else if (HkdfPrfParameters.HashType.SHA256.equals(hashType)) {
         return HashType.SHA256;
      } else if (HkdfPrfParameters.HashType.SHA384.equals(hashType)) {
         return HashType.SHA384;
      } else if (HkdfPrfParameters.HashType.SHA512.equals(hashType)) {
         return HashType.SHA512;
      } else {
         throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
      }
   }

   private static HkdfPrfParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
      switch (hashType) {
         case SHA1:
            return HkdfPrfParameters.HashType.SHA1;
         case SHA224:
            return HkdfPrfParameters.HashType.SHA224;
         case SHA256:
            return HkdfPrfParameters.HashType.SHA256;
         case SHA384:
            return HkdfPrfParameters.HashType.SHA384;
         case SHA512:
            return HkdfPrfParameters.HashType.SHA512;
         default:
            throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
      }
   }

   private static HkdfPrfParams getProtoParams(HkdfPrfParameters parameters) throws GeneralSecurityException {
      HkdfPrfParams.Builder builder = HkdfPrfParams.newBuilder().setHash(toProtoHashType(parameters.getHashType()));
      if (parameters.getSalt() != null && parameters.getSalt().size() > 0) {
         builder.setSalt(ByteString.copyFrom(parameters.getSalt().toByteArray()));
      }

      return builder.build();
   }

   private static ProtoParametersSerialization serializeParameters(HkdfPrfParameters parameters) throws GeneralSecurityException {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.HkdfPrfKey").setValue(HkdfPrfKeyFormat.newBuilder().setParams(getProtoParams(parameters)).setKeySize(parameters.getKeySizeBytes()).build().toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build());
   }

   private static ProtoKeySerialization serializeKey(HkdfPrfKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.HkdfPrfKey", com.google.crypto.tink.proto.HkdfPrfKey.newBuilder().setParams(getProtoParams(key.getParameters())).setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build().toByteString(), KeyData.KeyMaterialType.SYMMETRIC, OutputPrefixType.RAW, key.getIdRequirementOrNull());
   }

   private static HkdfPrfParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.HkdfPrfKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         HkdfPrfKeyFormat format;
         try {
            format = HkdfPrfKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: ", e);
         }

         if (format.getVersion() != 0) {
            throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: unknown Version " + format.getVersion());
         } else if (serialization.getKeyTemplate().getOutputPrefixType() != OutputPrefixType.RAW) {
            throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: only RAW output prefix type is accepted");
         } else {
            return HkdfPrfParameters.builder().setKeySizeBytes(format.getKeySize()).setHashType(toHashType(format.getParams().getHash())).setSalt(Bytes.copyFrom(format.getParams().getSalt().toByteArray())).build();
         }
      }
   }

   private static HkdfPrfKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.HkdfPrfKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseKey");
      } else {
         try {
            com.google.crypto.tink.proto.HkdfPrfKey protoKey = com.google.crypto.tink.proto.HkdfPrfKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else if (serialization.getIdRequirementOrNull() != null) {
               throw new GeneralSecurityException("ID requirement must be null.");
            } else {
               HkdfPrfParameters parameters = HkdfPrfParameters.builder().setKeySizeBytes(protoKey.getKeyValue().size()).setHashType(toHashType(protoKey.getParams().getHash())).setSalt(Bytes.copyFrom(protoKey.getParams().getSalt().toByteArray())).build();
               return HkdfPrfKey.builder().setParameters(parameters).setKeyBytes(SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access))).build();
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var4) {
            throw new GeneralSecurityException("Parsing HkdfPrfKey failed");
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

   private HkdfPrfProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(HkdfPrfProtoSerialization::parseParameters, TYPE_URL_BYTES, ProtoParametersSerialization.class);
      KEY_SERIALIZER = KeySerializer.create(HkdfPrfProtoSerialization::serializeKey, HkdfPrfKey.class, ProtoKeySerialization.class);
      KEY_PARSER = KeyParser.create(HkdfPrfProtoSerialization::parseKey, TYPE_URL_BYTES, ProtoKeySerialization.class);
   }
}
