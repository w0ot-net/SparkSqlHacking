package com.google.crypto.tink.keyderivation.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.TinkProtoParametersFormat;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.keyderivation.PrfBasedKeyDerivationKey;
import com.google.crypto.tink.keyderivation.PrfBasedKeyDerivationParameters;
import com.google.crypto.tink.prf.PrfKey;
import com.google.crypto.tink.prf.PrfParameters;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.PrfBasedDeriverKey;
import com.google.crypto.tink.proto.PrfBasedDeriverKeyFormat;
import com.google.crypto.tink.proto.PrfBasedDeriverParams;
import com.google.crypto.tink.util.Bytes;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

final class PrfBasedKeyDerivationKeyProtoSerialization {
   private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.PrfBasedDeriverKey";
   private static final Bytes TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.PrfBasedDeriverKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(PrfBasedKeyDerivationKeyProtoSerialization::serializeParameters, PrfBasedKeyDerivationParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer KEY_SERIALIZER;
   private static final KeyParser KEY_PARSER;

   private static PrfBasedKeyDerivationParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.PrfBasedDeriverKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to PrfBasedKeyDerivationKeyProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         PrfBasedDeriverKeyFormat format;
         try {
            format = PrfBasedDeriverKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing PrfBasedDeriverKeyFormat failed: ", e);
         }

         Parameters derivedKeyParameters = TinkProtoParametersFormat.parse(format.getParams().getDerivedKeyTemplate().toByteArray());
         Parameters prfParameters = TinkProtoParametersFormat.parse(format.getPrfKeyTemplate().toByteArray());
         if (!(prfParameters instanceof PrfParameters)) {
            throw new GeneralSecurityException("Non-PRF parameters stored in the field prf_key_template");
         } else if (serialization.getKeyTemplate().getOutputPrefixType() != format.getParams().getDerivedKeyTemplate().getOutputPrefixType()) {
            throw new GeneralSecurityException("Output-Prefix mismatch in parameters while parsing " + format);
         } else {
            return PrfBasedKeyDerivationParameters.builder().setPrfParameters((PrfParameters)prfParameters).setDerivedKeyParameters(derivedKeyParameters).build();
         }
      }
   }

   private static ProtoParametersSerialization serializeParameters(PrfBasedKeyDerivationParameters parameters) throws GeneralSecurityException {
      try {
         byte[] serializedPrfParameters = TinkProtoParametersFormat.serialize(parameters.getPrfParameters());
         KeyTemplate prfKeyTemplate = KeyTemplate.parseFrom(serializedPrfParameters, ExtensionRegistryLite.getEmptyRegistry());
         byte[] serializedDerivedKeyParameters = TinkProtoParametersFormat.serialize(parameters.getDerivedKeyParameters());
         KeyTemplate derivedKeyTemplate = KeyTemplate.parseFrom(serializedDerivedKeyParameters, ExtensionRegistryLite.getEmptyRegistry());
         PrfBasedDeriverKeyFormat format = PrfBasedDeriverKeyFormat.newBuilder().setPrfKeyTemplate(prfKeyTemplate).setParams(PrfBasedDeriverParams.newBuilder().setDerivedKeyTemplate(derivedKeyTemplate)).build();
         return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.PrfBasedDeriverKey").setValue(format.toByteString()).setOutputPrefixType(derivedKeyTemplate.getOutputPrefixType()).build());
      } catch (InvalidProtocolBufferException e) {
         throw new GeneralSecurityException("Serializing PrfBasedKeyDerivationParameters failed: ", e);
      }
   }

   @AccessesPartialKey
   private static ProtoKeySerialization serializeKey(PrfBasedKeyDerivationKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      ProtoKeySerialization prfKeySerialization = (ProtoKeySerialization)MutableSerializationRegistry.globalInstance().serializeKey(key.getPrfKey(), ProtoKeySerialization.class, access);
      ProtoParametersSerialization derivedKeyParametersSerialization = (ProtoParametersSerialization)MutableSerializationRegistry.globalInstance().serializeParameters(key.getParameters().getDerivedKeyParameters(), ProtoParametersSerialization.class);
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.PrfBasedDeriverKey", PrfBasedDeriverKey.newBuilder().setPrfKey(KeyData.newBuilder().setValue(prfKeySerialization.getValue()).setTypeUrl(prfKeySerialization.getTypeUrl()).setKeyMaterialType(prfKeySerialization.getKeyMaterialType())).setParams(PrfBasedDeriverParams.newBuilder().setDerivedKeyTemplate(derivedKeyParametersSerialization.getKeyTemplate())).build().toByteString(), KeyData.KeyMaterialType.SYMMETRIC, derivedKeyParametersSerialization.getKeyTemplate().getOutputPrefixType(), key.getIdRequirementOrNull());
   }

   @AccessesPartialKey
   private static PrfBasedKeyDerivationKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.PrfBasedDeriverKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to PrfBasedKeyDerivationKey.parseKey");
      } else {
         try {
            PrfBasedDeriverKey protoKey = PrfBasedDeriverKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            ProtoKeySerialization prfKeySerialization = ProtoKeySerialization.create(protoKey.getPrfKey().getTypeUrl(), protoKey.getPrfKey().getValue(), protoKey.getPrfKey().getKeyMaterialType(), OutputPrefixType.RAW, (Integer)null);
            Key prfKeyUncast = MutableSerializationRegistry.globalInstance().parseKey(prfKeySerialization, access);
            if (!(prfKeyUncast instanceof PrfKey)) {
               throw new GeneralSecurityException("Non-PRF key stored in the field prf_key");
            } else {
               PrfKey prfKey = (PrfKey)prfKeyUncast;
               ProtoParametersSerialization derivedKeyParametersSerialization = ProtoParametersSerialization.checkedCreate(protoKey.getParams().getDerivedKeyTemplate());
               Parameters derivedKeyParameters = MutableSerializationRegistry.globalInstance().parseParameters(derivedKeyParametersSerialization);
               PrfBasedKeyDerivationParameters parameters = PrfBasedKeyDerivationParameters.builder().setDerivedKeyParameters(derivedKeyParameters).setPrfParameters(prfKey.getParameters()).build();
               if (serialization.getOutputPrefixType() != derivedKeyParametersSerialization.getKeyTemplate().getOutputPrefixType()) {
                  throw new GeneralSecurityException("Output-Prefix mismatch in parameters while parsing PrfBasedKeyDerivationKey with parameters " + parameters);
               } else {
                  return PrfBasedKeyDerivationKey.create(parameters, prfKey, serialization.getIdRequirementOrNull());
               }
            }
         } catch (InvalidProtocolBufferException var9) {
            throw new GeneralSecurityException("Parsing HmacKey failed");
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

   private PrfBasedKeyDerivationKeyProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(PrfBasedKeyDerivationKeyProtoSerialization::parseParameters, TYPE_URL_BYTES, ProtoParametersSerialization.class);
      KEY_SERIALIZER = KeySerializer.create(PrfBasedKeyDerivationKeyProtoSerialization::serializeKey, PrfBasedKeyDerivationKey.class, ProtoKeySerialization.class);
      KEY_PARSER = KeyParser.create(PrfBasedKeyDerivationKeyProtoSerialization::parseKey, TYPE_URL_BYTES, ProtoKeySerialization.class);
   }
}
