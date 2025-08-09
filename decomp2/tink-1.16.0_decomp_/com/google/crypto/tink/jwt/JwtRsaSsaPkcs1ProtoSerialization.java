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
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormat;
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
final class JwtRsaSsaPkcs1ProtoSerialization {
   private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey";
   private static final Bytes PRIVATE_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey");
   private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
   private static final Bytes PUBLIC_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(JwtRsaSsaPkcs1ProtoSerialization::serializeParameters, JwtRsaSsaPkcs1Parameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer PUBLIC_KEY_SERIALIZER;
   private static final KeyParser PUBLIC_KEY_PARSER;
   private static final KeySerializer PRIVATE_KEY_SERIALIZER;
   private static final KeyParser PRIVATE_KEY_PARSER;
   private static final EnumTypeProtoConverter ALGORITHM_CONVERTER;

   private static OutputPrefixType toProtoOutputPrefixType(JwtRsaSsaPkcs1Parameters parameters) {
      return parameters.getKidStrategy().equals(JwtRsaSsaPkcs1Parameters.KidStrategy.BASE64_ENCODED_KEY_ID) ? OutputPrefixType.TINK : OutputPrefixType.RAW;
   }

   private static ByteString encodeBigInteger(BigInteger i) {
      byte[] encoded = BigIntegerEncoding.toBigEndianBytes(i);
      return ByteString.copyFrom(encoded);
   }

   private static JwtRsaSsaPkcs1KeyFormat getProtoKeyFormat(JwtRsaSsaPkcs1Parameters parameters) throws GeneralSecurityException {
      if (!parameters.getKidStrategy().equals(JwtRsaSsaPkcs1Parameters.KidStrategy.IGNORED) && !parameters.getKidStrategy().equals(JwtRsaSsaPkcs1Parameters.KidStrategy.BASE64_ENCODED_KEY_ID)) {
         throw new GeneralSecurityException("Unable to serialize Parameters object with KidStrategy " + parameters.getKidStrategy());
      } else {
         return JwtRsaSsaPkcs1KeyFormat.newBuilder().setVersion(0).setAlgorithm((JwtRsaSsaPkcs1Algorithm)ALGORITHM_CONVERTER.toProtoEnum(parameters.getAlgorithm())).setModulusSizeInBits(parameters.getModulusSizeBits()).setPublicExponent(encodeBigInteger(parameters.getPublicExponent())).build();
      }
   }

   private static ProtoParametersSerialization serializeParameters(JwtRsaSsaPkcs1Parameters parameters) throws GeneralSecurityException {
      OutputPrefixType outputPrefixType = toProtoOutputPrefixType(parameters);
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey").setValue(getProtoKeyFormat(parameters).toByteString()).setOutputPrefixType(outputPrefixType).build());
   }

   private static com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey getProtoPublicKey(JwtRsaSsaPkcs1PublicKey key) throws GeneralSecurityException {
      com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey.Builder builder = com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setAlgorithm((JwtRsaSsaPkcs1Algorithm)ALGORITHM_CONVERTER.toProtoEnum(key.getParameters().getAlgorithm())).setN(encodeBigInteger(key.getModulus())).setE(encodeBigInteger(key.getParameters().getPublicExponent()));
      if (key.getParameters().getKidStrategy().equals(JwtRsaSsaPkcs1Parameters.KidStrategy.CUSTOM)) {
         builder.setCustomKid(com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey.CustomKid.newBuilder().setValue((String)key.getKid().get()).build());
      }

      return builder.build();
   }

   private static ProtoKeySerialization serializePublicKey(JwtRsaSsaPkcs1PublicKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey", getProtoPublicKey(key).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, toProtoOutputPrefixType(key.getParameters()), key.getIdRequirementOrNull());
   }

   private static ByteString encodeSecretBigInteger(SecretBigInteger i, SecretKeyAccess access) {
      return encodeBigInteger(i.getBigInteger(access));
   }

   private static ProtoKeySerialization serializePrivateKey(JwtRsaSsaPkcs1PrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
      com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey protoPrivateKey = com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey.newBuilder().setVersion(0).setPublicKey(getProtoPublicKey(key.getPublicKey())).setD(encodeSecretBigInteger(key.getPrivateExponent(), a)).setP(encodeSecretBigInteger(key.getPrimeP(), a)).setQ(encodeSecretBigInteger(key.getPrimeQ(), a)).setDp(encodeSecretBigInteger(key.getPrimeExponentP(), a)).setDq(encodeSecretBigInteger(key.getPrimeExponentQ(), a)).setCrt(encodeSecretBigInteger(key.getCrtCoefficient(), a)).build();
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey", protoPrivateKey.toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, toProtoOutputPrefixType(key.getParameters()), key.getIdRequirementOrNull());
   }

   private static BigInteger decodeBigInteger(ByteString data) {
      return BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray());
   }

   private static void validateVersion(int version) throws GeneralSecurityException {
      if (version != 0) {
         throw new GeneralSecurityException("Parsing failed: unknown version " + version);
      }
   }

   private static JwtRsaSsaPkcs1Parameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to JwtRsaSsaPkcs1ProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         JwtRsaSsaPkcs1KeyFormat format;
         try {
            format = JwtRsaSsaPkcs1KeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing JwtRsaSsaPkcs1Parameters failed: ", e);
         }

         validateVersion(format.getVersion());
         JwtRsaSsaPkcs1Parameters.KidStrategy kidStrategy = null;
         if (serialization.getKeyTemplate().getOutputPrefixType().equals(OutputPrefixType.TINK)) {
            kidStrategy = JwtRsaSsaPkcs1Parameters.KidStrategy.BASE64_ENCODED_KEY_ID;
         }

         if (serialization.getKeyTemplate().getOutputPrefixType().equals(OutputPrefixType.RAW)) {
            kidStrategy = JwtRsaSsaPkcs1Parameters.KidStrategy.IGNORED;
         }

         if (kidStrategy == null) {
            throw new GeneralSecurityException("Invalid OutputPrefixType for JwtHmacKeyFormat");
         } else {
            return JwtRsaSsaPkcs1Parameters.builder().setKidStrategy(kidStrategy).setAlgorithm((JwtRsaSsaPkcs1Parameters.Algorithm)ALGORITHM_CONVERTER.fromProtoEnum(format.getAlgorithm())).setPublicExponent(decodeBigInteger(format.getPublicExponent())).setModulusSizeBits(format.getModulusSizeInBits()).build();
         }
      }
   }

   private static JwtRsaSsaPkcs1PublicKey getPublicKeyFromProto(com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey protoKey, OutputPrefixType outputPrefixType, @Nullable Integer idRequirement) throws GeneralSecurityException {
      validateVersion(protoKey.getVersion());
      JwtRsaSsaPkcs1Parameters.Builder parametersBuilder = JwtRsaSsaPkcs1Parameters.builder();
      JwtRsaSsaPkcs1PublicKey.Builder keyBuilder = JwtRsaSsaPkcs1PublicKey.builder();
      if (outputPrefixType.equals(OutputPrefixType.TINK)) {
         if (protoKey.hasCustomKid()) {
            throw new GeneralSecurityException("Keys serialized with OutputPrefixType TINK should not have a custom kid");
         }

         if (idRequirement == null) {
            throw new GeneralSecurityException("Keys serialized with OutputPrefixType TINK need an ID Requirement");
         }

         parametersBuilder.setKidStrategy(JwtRsaSsaPkcs1Parameters.KidStrategy.BASE64_ENCODED_KEY_ID);
         keyBuilder.setIdRequirement(idRequirement);
      } else if (outputPrefixType.equals(OutputPrefixType.RAW)) {
         if (protoKey.hasCustomKid()) {
            parametersBuilder.setKidStrategy(JwtRsaSsaPkcs1Parameters.KidStrategy.CUSTOM);
            keyBuilder.setCustomKid(protoKey.getCustomKid().getValue());
         } else {
            parametersBuilder.setKidStrategy(JwtRsaSsaPkcs1Parameters.KidStrategy.IGNORED);
         }
      }

      BigInteger modulus = decodeBigInteger(protoKey.getN());
      int modulusSizeInBits = modulus.bitLength();
      parametersBuilder.setAlgorithm((JwtRsaSsaPkcs1Parameters.Algorithm)ALGORITHM_CONVERTER.fromProtoEnum(protoKey.getAlgorithm())).setPublicExponent(decodeBigInteger(protoKey.getE())).setModulusSizeBits(modulusSizeInBits);
      keyBuilder.setModulus(modulus).setParameters(parametersBuilder.build());
      return keyBuilder.build();
   }

   private static JwtRsaSsaPkcs1PublicKey parsePublicKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to JwtRsaSsaPkcs1ProtoSerialization.parsePublicKey: " + serialization.getTypeUrl());
      } else {
         try {
            com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey protoKey = com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return getPublicKeyFromProto(protoKey, serialization.getOutputPrefixType(), serialization.getIdRequirementOrNull());
         } catch (InvalidProtocolBufferException var3) {
            throw new GeneralSecurityException("Parsing JwtRsaSsaPkcs1PublicKey failed");
         }
      }
   }

   private static SecretBigInteger decodeSecretBigInteger(ByteString data, SecretKeyAccess access) {
      return SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray()), access);
   }

   private static JwtRsaSsaPkcs1PrivateKey parsePrivateKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to JwtRsaSsaPkcs1ProtoSerialization.parsePrivateKey: " + serialization.getTypeUrl());
      } else {
         try {
            com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey protoKey = com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            validateVersion(protoKey.getVersion());
            JwtRsaSsaPkcs1PublicKey publicKey = getPublicKeyFromProto(protoKey.getPublicKey(), serialization.getOutputPrefixType(), serialization.getIdRequirementOrNull());
            SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
            return JwtRsaSsaPkcs1PrivateKey.builder().setPublicKey(publicKey).setPrimes(decodeSecretBigInteger(protoKey.getP(), a), decodeSecretBigInteger(protoKey.getQ(), a)).setPrivateExponent(decodeSecretBigInteger(protoKey.getD(), a)).setPrimeExponents(decodeSecretBigInteger(protoKey.getDp(), a), decodeSecretBigInteger(protoKey.getDq(), a)).setCrtCoefficient(decodeSecretBigInteger(protoKey.getCrt(), a)).build();
         } catch (InvalidProtocolBufferException var5) {
            throw new GeneralSecurityException("Parsing JwtRsaSsaPkcs1PrivateKey failed");
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

   private JwtRsaSsaPkcs1ProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(JwtRsaSsaPkcs1ProtoSerialization::parseParameters, PRIVATE_TYPE_URL_BYTES, ProtoParametersSerialization.class);
      PUBLIC_KEY_SERIALIZER = KeySerializer.create(JwtRsaSsaPkcs1ProtoSerialization::serializePublicKey, JwtRsaSsaPkcs1PublicKey.class, ProtoKeySerialization.class);
      PUBLIC_KEY_PARSER = KeyParser.create(JwtRsaSsaPkcs1ProtoSerialization::parsePublicKey, PUBLIC_TYPE_URL_BYTES, ProtoKeySerialization.class);
      PRIVATE_KEY_SERIALIZER = KeySerializer.create(JwtRsaSsaPkcs1ProtoSerialization::serializePrivateKey, JwtRsaSsaPkcs1PrivateKey.class, ProtoKeySerialization.class);
      PRIVATE_KEY_PARSER = KeyParser.create(JwtRsaSsaPkcs1ProtoSerialization::parsePrivateKey, PRIVATE_TYPE_URL_BYTES, ProtoKeySerialization.class);
      ALGORITHM_CONVERTER = EnumTypeProtoConverter.builder().add(JwtRsaSsaPkcs1Algorithm.RS256, JwtRsaSsaPkcs1Parameters.Algorithm.RS256).add(JwtRsaSsaPkcs1Algorithm.RS384, JwtRsaSsaPkcs1Parameters.Algorithm.RS384).add(JwtRsaSsaPkcs1Algorithm.RS512, JwtRsaSsaPkcs1Parameters.Algorithm.RS512).build();
   }
}
