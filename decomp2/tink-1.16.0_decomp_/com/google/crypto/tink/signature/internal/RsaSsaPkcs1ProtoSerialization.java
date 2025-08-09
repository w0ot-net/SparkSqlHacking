package com.google.crypto.tink.signature.internal;

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
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.signature.RsaSsaPkcs1Parameters;
import com.google.crypto.tink.signature.RsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@AccessesPartialKey
public final class RsaSsaPkcs1ProtoSerialization {
   private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey";
   private static final Bytes PRIVATE_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey");
   private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey";
   private static final Bytes PUBLIC_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(RsaSsaPkcs1ProtoSerialization::serializeParameters, RsaSsaPkcs1Parameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer PUBLIC_KEY_SERIALIZER;
   private static final KeyParser PUBLIC_KEY_PARSER;
   private static final KeySerializer PRIVATE_KEY_SERIALIZER;
   private static final KeyParser PRIVATE_KEY_PARSER;
   private static final EnumTypeProtoConverter VARIANT_CONVERTER;
   private static final EnumTypeProtoConverter HASH_TYPE_CONVERTER;

   private static RsaSsaPkcs1Params getProtoParams(RsaSsaPkcs1Parameters parameters) throws GeneralSecurityException {
      return RsaSsaPkcs1Params.newBuilder().setHashType((HashType)HASH_TYPE_CONVERTER.toProtoEnum(parameters.getHashType())).build();
   }

   private static ByteString encodeBigInteger(BigInteger i) {
      byte[] encoded = BigIntegerEncoding.toBigEndianBytes(i);
      return ByteString.copyFrom(encoded);
   }

   private static RsaSsaPkcs1PublicKey getProtoPublicKey(com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey key) throws GeneralSecurityException {
      return RsaSsaPkcs1PublicKey.newBuilder().setParams(getProtoParams(key.getParameters())).setN(encodeBigInteger(key.getModulus())).setE(encodeBigInteger(key.getParameters().getPublicExponent())).build();
   }

   private static ProtoParametersSerialization serializeParameters(RsaSsaPkcs1Parameters parameters) throws GeneralSecurityException {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey").setValue(RsaSsaPkcs1KeyFormat.newBuilder().setParams(getProtoParams(parameters)).setModulusSizeInBits(parameters.getModulusSizeBits()).setPublicExponent(encodeBigInteger(parameters.getPublicExponent())).build().toByteString()).setOutputPrefixType((OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(parameters.getVariant())).build());
   }

   private static ProtoKeySerialization serializePublicKey(com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey", getProtoPublicKey(key).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, (OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static ByteString encodeSecretBigInteger(SecretBigInteger i, SecretKeyAccess access) {
      return encodeBigInteger(i.getBigInteger(access));
   }

   private static ProtoKeySerialization serializePrivateKey(RsaSsaPkcs1PrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
      com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey protoPrivateKey = com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey.newBuilder().setVersion(0).setPublicKey(getProtoPublicKey(key.getPublicKey())).setD(encodeSecretBigInteger(key.getPrivateExponent(), a)).setP(encodeSecretBigInteger(key.getPrimeP(), a)).setQ(encodeSecretBigInteger(key.getPrimeQ(), a)).setDp(encodeSecretBigInteger(key.getPrimeExponentP(), a)).setDq(encodeSecretBigInteger(key.getPrimeExponentQ(), a)).setCrt(encodeSecretBigInteger(key.getCrtCoefficient(), a)).build();
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey", protoPrivateKey.toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, (OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static BigInteger decodeBigInteger(ByteString data) {
      return BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray());
   }

   private static RsaSsaPkcs1Parameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to RsaSsaPkcs1ProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         RsaSsaPkcs1KeyFormat format;
         try {
            format = RsaSsaPkcs1KeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing RsaSsaPkcs1Parameters failed: ", e);
         }

         return RsaSsaPkcs1Parameters.builder().setHashType((RsaSsaPkcs1Parameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(format.getParams().getHashType())).setPublicExponent(decodeBigInteger(format.getPublicExponent())).setModulusSizeBits(format.getModulusSizeInBits()).setVariant((RsaSsaPkcs1Parameters.Variant)VARIANT_CONVERTER.fromProtoEnum(serialization.getKeyTemplate().getOutputPrefixType())).build();
      }
   }

   private static com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey parsePublicKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to RsaSsaPkcs1ProtoSerialization.parsePublicKey: " + serialization.getTypeUrl());
      } else {
         try {
            RsaSsaPkcs1PublicKey protoKey = RsaSsaPkcs1PublicKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               BigInteger modulus = decodeBigInteger(protoKey.getN());
               int modulusSizeInBits = modulus.bitLength();
               RsaSsaPkcs1Parameters parameters = RsaSsaPkcs1Parameters.builder().setHashType((RsaSsaPkcs1Parameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(protoKey.getParams().getHashType())).setPublicExponent(decodeBigInteger(protoKey.getE())).setModulusSizeBits(modulusSizeInBits).setVariant((RsaSsaPkcs1Parameters.Variant)VARIANT_CONVERTER.fromProtoEnum(serialization.getOutputPrefixType())).build();
               return com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey.builder().setParameters(parameters).setModulus(modulus).setIdRequirement(serialization.getIdRequirementOrNull()).build();
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var6) {
            throw new GeneralSecurityException("Parsing RsaSsaPkcs1PublicKey failed");
         }
      }
   }

   private static SecretBigInteger decodeSecretBigInteger(ByteString data, SecretKeyAccess access) {
      return SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray()), access);
   }

   private static RsaSsaPkcs1PrivateKey parsePrivateKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to RsaSsaPkcs1ProtoSerialization.parsePrivateKey: " + serialization.getTypeUrl());
      } else {
         try {
            com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey protoKey = com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               RsaSsaPkcs1PublicKey protoPublicKey = protoKey.getPublicKey();
               if (protoPublicKey.getVersion() != 0) {
                  throw new GeneralSecurityException("Only version 0 keys are accepted");
               } else {
                  BigInteger modulus = decodeBigInteger(protoPublicKey.getN());
                  int modulusSizeInBits = modulus.bitLength();
                  BigInteger publicExponent = decodeBigInteger(protoPublicKey.getE());
                  RsaSsaPkcs1Parameters parameters = RsaSsaPkcs1Parameters.builder().setHashType((RsaSsaPkcs1Parameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(protoPublicKey.getParams().getHashType())).setPublicExponent(publicExponent).setModulusSizeBits(modulusSizeInBits).setVariant((RsaSsaPkcs1Parameters.Variant)VARIANT_CONVERTER.fromProtoEnum(serialization.getOutputPrefixType())).build();
                  com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey publicKey = com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey.builder().setParameters(parameters).setModulus(modulus).setIdRequirement(serialization.getIdRequirementOrNull()).build();
                  SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
                  return RsaSsaPkcs1PrivateKey.builder().setPublicKey(publicKey).setPrimes(decodeSecretBigInteger(protoKey.getP(), a), decodeSecretBigInteger(protoKey.getQ(), a)).setPrivateExponent(decodeSecretBigInteger(protoKey.getD(), a)).setPrimeExponents(decodeSecretBigInteger(protoKey.getDp(), a), decodeSecretBigInteger(protoKey.getDq(), a)).setCrtCoefficient(decodeSecretBigInteger(protoKey.getCrt(), a)).build();
               }
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var10) {
            throw new GeneralSecurityException("Parsing RsaSsaPkcs1PrivateKey failed");
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

   private RsaSsaPkcs1ProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(RsaSsaPkcs1ProtoSerialization::parseParameters, PRIVATE_TYPE_URL_BYTES, ProtoParametersSerialization.class);
      PUBLIC_KEY_SERIALIZER = KeySerializer.create(RsaSsaPkcs1ProtoSerialization::serializePublicKey, com.google.crypto.tink.signature.RsaSsaPkcs1PublicKey.class, ProtoKeySerialization.class);
      PUBLIC_KEY_PARSER = KeyParser.create(RsaSsaPkcs1ProtoSerialization::parsePublicKey, PUBLIC_TYPE_URL_BYTES, ProtoKeySerialization.class);
      PRIVATE_KEY_SERIALIZER = KeySerializer.create(RsaSsaPkcs1ProtoSerialization::serializePrivateKey, RsaSsaPkcs1PrivateKey.class, ProtoKeySerialization.class);
      PRIVATE_KEY_PARSER = KeyParser.create(RsaSsaPkcs1ProtoSerialization::parsePrivateKey, PRIVATE_TYPE_URL_BYTES, ProtoKeySerialization.class);
      VARIANT_CONVERTER = EnumTypeProtoConverter.builder().add(OutputPrefixType.RAW, RsaSsaPkcs1Parameters.Variant.NO_PREFIX).add(OutputPrefixType.TINK, RsaSsaPkcs1Parameters.Variant.TINK).add(OutputPrefixType.CRUNCHY, RsaSsaPkcs1Parameters.Variant.CRUNCHY).add(OutputPrefixType.LEGACY, RsaSsaPkcs1Parameters.Variant.LEGACY).build();
      HASH_TYPE_CONVERTER = EnumTypeProtoConverter.builder().add(HashType.SHA256, RsaSsaPkcs1Parameters.HashType.SHA256).add(HashType.SHA384, RsaSsaPkcs1Parameters.HashType.SHA384).add(HashType.SHA512, RsaSsaPkcs1Parameters.HashType.SHA512).build();
   }
}
