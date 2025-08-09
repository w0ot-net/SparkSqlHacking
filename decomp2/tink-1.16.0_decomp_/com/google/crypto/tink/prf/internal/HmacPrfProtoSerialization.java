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
import com.google.crypto.tink.prf.HmacPrfKey;
import com.google.crypto.tink.prf.HmacPrfParameters;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
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
public final class HmacPrfProtoSerialization {
   private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HmacPrfKey";
   private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.HmacPrfKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(HmacPrfProtoSerialization::serializeParameters, HmacPrfParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer KEY_SERIALIZER;
   private static final KeyParser KEY_PARSER;

   private static HashType toProtoHashType(HmacPrfParameters.HashType hashType) throws GeneralSecurityException {
      if (HmacPrfParameters.HashType.SHA1.equals(hashType)) {
         return HashType.SHA1;
      } else if (HmacPrfParameters.HashType.SHA224.equals(hashType)) {
         return HashType.SHA224;
      } else if (HmacPrfParameters.HashType.SHA256.equals(hashType)) {
         return HashType.SHA256;
      } else if (HmacPrfParameters.HashType.SHA384.equals(hashType)) {
         return HashType.SHA384;
      } else if (HmacPrfParameters.HashType.SHA512.equals(hashType)) {
         return HashType.SHA512;
      } else {
         throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
      }
   }

   private static HmacPrfParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
      switch (hashType) {
         case SHA1:
            return HmacPrfParameters.HashType.SHA1;
         case SHA224:
            return HmacPrfParameters.HashType.SHA224;
         case SHA256:
            return HmacPrfParameters.HashType.SHA256;
         case SHA384:
            return HmacPrfParameters.HashType.SHA384;
         case SHA512:
            return HmacPrfParameters.HashType.SHA512;
         default:
            throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
      }
   }

   private static HmacPrfParams getProtoParams(HmacPrfParameters parameters) throws GeneralSecurityException {
      return HmacPrfParams.newBuilder().setHash(toProtoHashType(parameters.getHashType())).build();
   }

   private static ProtoParametersSerialization serializeParameters(HmacPrfParameters parameters) throws GeneralSecurityException {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.HmacPrfKey").setValue(HmacPrfKeyFormat.newBuilder().setParams(getProtoParams(parameters)).setKeySize(parameters.getKeySizeBytes()).build().toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build());
   }

   private static ProtoKeySerialization serializeKey(HmacPrfKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.HmacPrfKey", com.google.crypto.tink.proto.HmacPrfKey.newBuilder().setParams(getProtoParams(key.getParameters())).setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build().toByteString(), KeyData.KeyMaterialType.SYMMETRIC, OutputPrefixType.RAW, key.getIdRequirementOrNull());
   }

   private static HmacPrfParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.HmacPrfKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to HmacPrfProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         HmacPrfKeyFormat format;
         try {
            format = HmacPrfKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing HmacPrfParameters failed: ", e);
         }

         if (format.getVersion() != 0) {
            throw new GeneralSecurityException("Parsing HmacPrfParameters failed: unknown Version " + format.getVersion());
         } else if (serialization.getKeyTemplate().getOutputPrefixType() != OutputPrefixType.RAW) {
            throw new GeneralSecurityException("Parsing HmacPrfParameters failed: only RAW output prefix type is accepted");
         } else {
            return HmacPrfParameters.builder().setKeySizeBytes(format.getKeySize()).setHashType(toHashType(format.getParams().getHash())).build();
         }
      }
   }

   private static HmacPrfKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.HmacPrfKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to HmacPrfProtoSerialization.parseKey");
      } else {
         try {
            com.google.crypto.tink.proto.HmacPrfKey protoKey = com.google.crypto.tink.proto.HmacPrfKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else if (serialization.getIdRequirementOrNull() != null) {
               throw new GeneralSecurityException("ID requirement must be null.");
            } else {
               HmacPrfParameters parameters = HmacPrfParameters.builder().setKeySizeBytes(protoKey.getKeyValue().size()).setHashType(toHashType(protoKey.getParams().getHash())).build();
               return HmacPrfKey.builder().setParameters(parameters).setKeyBytes(SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access))).build();
            }
         } catch (InvalidProtocolBufferException var4) {
            throw new GeneralSecurityException("Parsing HmacPrfKey failed");
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

   private HmacPrfProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(HmacPrfProtoSerialization::parseParameters, TYPE_URL_BYTES, ProtoParametersSerialization.class);
      KEY_SERIALIZER = KeySerializer.create(HmacPrfProtoSerialization::serializeKey, HmacPrfKey.class, ProtoKeySerialization.class);
      KEY_PARSER = KeyParser.create(HmacPrfProtoSerialization::parseKey, TYPE_URL_BYTES, ProtoKeySerialization.class);
   }
}
