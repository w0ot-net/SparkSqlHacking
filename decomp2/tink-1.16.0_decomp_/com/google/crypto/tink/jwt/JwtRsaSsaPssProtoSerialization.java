package com.google.crypto.tink.jwt;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.EnumTypeProtoConverter;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@AccessesPartialKey
final class JwtRsaSsaPssProtoSerialization {
   private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey";
   private static final Bytes PRIVATE_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey");
   private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";
   private static final Bytes PUBLIC_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(JwtRsaSsaPssProtoSerialization::serializeParameters, JwtRsaSsaPssParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer PUBLIC_KEY_SERIALIZER;
   private static final KeyParser PUBLIC_KEY_PARSER;
   private static final KeySerializer PRIVATE_KEY_SERIALIZER;
   private static final KeyParser PRIVATE_KEY_PARSER;
   private static final EnumTypeProtoConverter ALGORITHM_CONVERTER;

   private static OutputPrefixType toProtoOutputPrefixType(JwtRsaSsaPssParameters parameters) {
      return parameters.getKidStrategy().equals(JwtRsaSsaPssParameters.KidStrategy.BASE64_ENCODED_KEY_ID) ? OutputPrefixType.TINK : OutputPrefixType.RAW;
   }

   private static ByteString encodeBigInteger(BigInteger i) {
      byte[] encoded = BigIntegerEncoding.toBigEndianBytes(i);
      return ByteString.copyFrom(encoded);
   }

   private static JwtRsaSsaPssKeyFormat getProtoKeyFormat(JwtRsaSsaPssParameters parameters) throws GeneralSecurityException {
      if (!parameters.getKidStrategy().equals(JwtRsaSsaPssParameters.KidStrategy.IGNORED) && !parameters.getKidStrategy().equals(JwtRsaSsaPssParameters.KidStrategy.BASE64_ENCODED_KEY_ID)) {
         throw new GeneralSecurityException("Unable to serialize Parameters object with KidStrategy " + parameters.getKidStrategy());
      } else {
         return JwtRsaSsaPssKeyFormat.newBuilder().setVersion(0).setAlgorithm((JwtRsaSsaPssAlgorithm)ALGORITHM_CONVERTER.toProtoEnum(parameters.getAlgorithm())).setModulusSizeInBits(parameters.getModulusSizeBits()).setPublicExponent(encodeBigInteger(parameters.getPublicExponent())).build();
      }
   }

   private static ProtoParametersSerialization serializeParameters(JwtRsaSsaPssParameters parameters) throws GeneralSecurityException {
      OutputPrefixType outputPrefixType = toProtoOutputPrefixType(parameters);
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey").setValue(getProtoKeyFormat(parameters).toByteString()).setOutputPrefixType(outputPrefixType).build());
   }

   private static com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey getProtoPublicKey(JwtRsaSsaPssPublicKey key) throws GeneralSecurityException {
      com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey.Builder builder = com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey.newBuilder().setVersion(0).setAlgorithm((JwtRsaSsaPssAlgorithm)ALGORITHM_CONVERTER.toProtoEnum(key.getParameters().getAlgorithm())).setN(encodeBigInteger(key.getModulus())).setE(encodeBigInteger(key.getParameters().getPublicExponent()));
      if (key.getParameters().getKidStrategy().equals(JwtRsaSsaPssParameters.KidStrategy.CUSTOM)) {
         builder.setCustomKid(com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey.CustomKid.newBuilder().setValue((String)key.getKid().get()).build());
      }

      return builder.build();
   }

   private static ProtoKeySerialization serializePublicKey(JwtRsaSsaPssPublicKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey", getProtoPublicKey(key).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, toProtoOutputPrefixType(key.getParameters()), key.getIdRequirementOrNull());
   }

   private static ByteString encodeSecretBigInteger(SecretBigInteger i, SecretKeyAccess access) {
      return encodeBigInteger(i.getBigInteger(access));
   }

   private static ProtoKeySerialization serializePrivateKey(JwtRsaSsaPssPrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
      com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey protoPrivateKey = com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey.newBuilder().setVersion(0).setPublicKey(getProtoPublicKey(key.getPublicKey())).setD(encodeSecretBigInteger(key.getPrivateExponent(), a)).setP(encodeSecretBigInteger(key.getPrimeP(), a)).setQ(encodeSecretBigInteger(key.getPrimeQ(), a)).setDp(encodeSecretBigInteger(key.getPrimeExponentP(), a)).setDq(encodeSecretBigInteger(key.getPrimeExponentQ(), a)).setCrt(encodeSecretBigInteger(key.getCrtCoefficient(), a)).build();
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey", protoPrivateKey.toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, toProtoOutputPrefixType(key.getParameters()), key.getIdRequirementOrNull());
   }

   private static BigInteger decodeBigInteger(ByteString data) {
      return BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray());
   }

   private static void validateVersion(int version) throws GeneralSecurityException {
      if (version != 0) {
         throw new GeneralSecurityException("Parsing failed: unknown version " + version);
      }
   }

   private static JwtRsaSsaPssParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to JwtRsaSsaPssProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         JwtRsaSsaPssKeyFormat format;
         try {
            format = JwtRsaSsaPssKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing JwtRsaSsaPssParameters failed: ", e);
         }

         validateVersion(format.getVersion());
         JwtRsaSsaPssParameters.KidStrategy kidStrategy = null;
         if (serialization.getKeyTemplate().getOutputPrefixType().equals(OutputPrefixType.TINK)) {
            kidStrategy = JwtRsaSsaPssParameters.KidStrategy.BASE64_ENCODED_KEY_ID;
         }

         if (serialization.getKeyTemplate().getOutputPrefixType().equals(OutputPrefixType.RAW)) {
            kidStrategy = JwtRsaSsaPssParameters.KidStrategy.IGNORED;
         }

         if (kidStrategy == null) {
            throw new GeneralSecurityException("Invalid OutputPrefixType for JwtHmacKeyFormat");
         } else {
            return JwtRsaSsaPssParameters.builder().setKidStrategy(kidStrategy).setAlgorithm((JwtRsaSsaPssParameters.Algorithm)ALGORITHM_CONVERTER.fromProtoEnum(format.getAlgorithm())).setPublicExponent(decodeBigInteger(format.getPublicExponent())).setModulusSizeBits(format.getModulusSizeInBits()).build();
         }
      }
   }

   private static JwtRsaSsaPssPublicKey getPublicKeyFromProto(com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey protoKey, OutputPrefixType outputPrefixType, @Nullable Integer idRequirement) throws GeneralSecurityException {
      validateVersion(protoKey.getVersion());
      JwtRsaSsaPssParameters.Builder parametersBuilder = JwtRsaSsaPssParameters.builder();
      JwtRsaSsaPssPublicKey.Builder keyBuilder = JwtRsaSsaPssPublicKey.builder();
      if (outputPrefixType.equals(OutputPrefixType.TINK)) {
         if (protoKey.hasCustomKid()) {
            throw new GeneralSecurityException("Keys serialized with OutputPrefixType TINK should not have a custom kid");
         }

         if (idRequirement == null) {
            throw new GeneralSecurityException("Keys serialized with OutputPrefixType TINK need an ID Requirement");
         }

         parametersBuilder.setKidStrategy(JwtRsaSsaPssParameters.KidStrategy.BASE64_ENCODED_KEY_ID);
         keyBuilder.setIdRequirement(idRequirement);
      } else if (outputPrefixType.equals(OutputPrefixType.RAW)) {
         if (protoKey.hasCustomKid()) {
            parametersBuilder.setKidStrategy(JwtRsaSsaPssParameters.KidStrategy.CUSTOM);
            keyBuilder.setCustomKid(protoKey.getCustomKid().getValue());
         } else {
            parametersBuilder.setKidStrategy(JwtRsaSsaPssParameters.KidStrategy.IGNORED);
         }
      }

      BigInteger modulus = decodeBigInteger(protoKey.getN());
      int modulusSizeInBits = modulus.bitLength();
      parametersBuilder.setAlgorithm((JwtRsaSsaPssParameters.Algorithm)ALGORITHM_CONVERTER.fromProtoEnum(protoKey.getAlgorithm())).setPublicExponent(decodeBigInteger(protoKey.getE())).setModulusSizeBits(modulusSizeInBits);
      keyBuilder.setModulus(modulus).setParameters(parametersBuilder.build());
      return keyBuilder.build();
   }

   private static JwtRsaSsaPssPublicKey parsePublicKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to JwtRsaSsaPssProtoSerialization.parsePublicKey: " + serialization.getTypeUrl());
      } else {
         try {
            com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey protoKey = com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return getPublicKeyFromProto(protoKey, serialization.getOutputPrefixType(), serialization.getIdRequirementOrNull());
         } catch (InvalidProtocolBufferException var3) {
            throw new GeneralSecurityException("Parsing JwtRsaSsaPssPublicKey failed");
         }
      }
   }

   private static SecretBigInteger decodeSecretBigInteger(ByteString data, SecretKeyAccess access) {
      return SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray()), access);
   }

   private static JwtRsaSsaPssPrivateKey parsePrivateKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to JwtRsaSsaPssProtoSerialization.parsePrivateKey: " + serialization.getTypeUrl());
      } else {
         try {
            com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey protoKey = com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            validateVersion(protoKey.getVersion());
            JwtRsaSsaPssPublicKey publicKey = getPublicKeyFromProto(protoKey.getPublicKey(), serialization.getOutputPrefixType(), serialization.getIdRequirementOrNull());
            SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
            return JwtRsaSsaPssPrivateKey.builder().setPublicKey(publicKey).setPrimes(decodeSecretBigInteger(protoKey.getP(), a), decodeSecretBigInteger(protoKey.getQ(), a)).setPrivateExponent(decodeSecretBigInteger(protoKey.getD(), a)).setPrimeExponents(decodeSecretBigInteger(protoKey.getDp(), a), decodeSecretBigInteger(protoKey.getDq(), a)).setCrtCoefficient(decodeSecretBigInteger(protoKey.getCrt(), a)).build();
         } catch (InvalidProtocolBufferException var5) {
            throw new GeneralSecurityException("Parsing JwtRsaSsaPssPrivateKey failed");
         }
      }
   }

   public static void register() throws GeneralSecurityException {
      register(MutableSerializationRegistry.globalInstance());
   }

   public static void register(MutableSerializationRegistry registry) throws GeneralSecurityException {
      registry.registerParametersSerializer(PARAMETERS_SERIALIZER);
      registry.registerParametersParser(PARAMETERS_PARSER);
      registry.registerKeySerializer(PUBLIC_KEY_SERIALIZER);
      registry.registerKeyParser(PUBLIC_KEY_PARSER);
      registry.registerKeySerializer(PRIVATE_KEY_SERIALIZER);
      registry.registerKeyParser(PRIVATE_KEY_PARSER);
   }

   private JwtRsaSsaPssProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(JwtRsaSsaPssProtoSerialization::parseParameters, PRIVATE_TYPE_URL_BYTES, ProtoParametersSerialization.class);
      PUBLIC_KEY_SERIALIZER = KeySerializer.create(JwtRsaSsaPssProtoSerialization::serializePublicKey, JwtRsaSsaPssPublicKey.class, ProtoKeySerialization.class);
      PUBLIC_KEY_PARSER = KeyParser.create(JwtRsaSsaPssProtoSerialization::parsePublicKey, PUBLIC_TYPE_URL_BYTES, ProtoKeySerialization.class);
      PRIVATE_KEY_SERIALIZER = KeySerializer.create(JwtRsaSsaPssProtoSerialization::serializePrivateKey, JwtRsaSsaPssPrivateKey.class, ProtoKeySerialization.class);
      PRIVATE_KEY_PARSER = KeyParser.create(JwtRsaSsaPssProtoSerialization::parsePrivateKey, PRIVATE_TYPE_URL_BYTES, ProtoKeySerialization.class);
      ALGORITHM_CONVERTER = EnumTypeProtoConverter.builder().add(JwtRsaSsaPssAlgorithm.PS256, JwtRsaSsaPssParameters.Algorithm.PS256).add(JwtRsaSsaPssAlgorithm.PS384, JwtRsaSsaPssParameters.Algorithm.PS384).add(JwtRsaSsaPssAlgorithm.PS512, JwtRsaSsaPssParameters.Algorithm.PS512).build();
   }
}
