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
import com.google.crypto.tink.proto.RsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.signature.RsaSsaPssParameters;
import com.google.crypto.tink.signature.RsaSsaPssPrivateKey;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@AccessesPartialKey
public final class RsaSsaPssProtoSerialization {
   private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey";
   private static final Bytes PRIVATE_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey");
   private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey";
   private static final Bytes PUBLIC_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(RsaSsaPssProtoSerialization::serializeParameters, RsaSsaPssParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer PUBLIC_KEY_SERIALIZER;
   private static final KeyParser PUBLIC_KEY_PARSER;
   private static final KeySerializer PRIVATE_KEY_SERIALIZER;
   private static final KeyParser PRIVATE_KEY_PARSER;
   private static final EnumTypeProtoConverter VARIANT_CONVERTER;
   private static final EnumTypeProtoConverter HASH_TYPE_CONVERTER;

   private static RsaSsaPssParams getProtoParams(RsaSsaPssParameters parameters) throws GeneralSecurityException {
      return RsaSsaPssParams.newBuilder().setSigHash((HashType)HASH_TYPE_CONVERTER.toProtoEnum(parameters.getSigHashType())).setMgf1Hash((HashType)HASH_TYPE_CONVERTER.toProtoEnum(parameters.getMgf1HashType())).setSaltLength(parameters.getSaltLengthBytes()).build();
   }

   private static ByteString encodeBigInteger(BigInteger i) {
      byte[] encoded = BigIntegerEncoding.toBigEndianBytes(i);
      return ByteString.copyFrom(encoded);
   }

   private static RsaSsaPssPublicKey getProtoPublicKey(com.google.crypto.tink.signature.RsaSsaPssPublicKey key) throws GeneralSecurityException {
      return RsaSsaPssPublicKey.newBuilder().setParams(getProtoParams(key.getParameters())).setN(encodeBigInteger(key.getModulus())).setE(encodeBigInteger(key.getParameters().getPublicExponent())).setVersion(0).build();
   }

   private static ProtoParametersSerialization serializeParameters(RsaSsaPssParameters parameters) throws GeneralSecurityException {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey").setValue(RsaSsaPssKeyFormat.newBuilder().setParams(getProtoParams(parameters)).setModulusSizeInBits(parameters.getModulusSizeBits()).setPublicExponent(encodeBigInteger(parameters.getPublicExponent())).build().toByteString()).setOutputPrefixType((OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(parameters.getVariant())).build());
   }

   private static ProtoKeySerialization serializePublicKey(com.google.crypto.tink.signature.RsaSsaPssPublicKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey", getProtoPublicKey(key).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, (OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static ByteString encodeSecretBigInteger(SecretBigInteger i, SecretKeyAccess access) {
      return encodeBigInteger(i.getBigInteger(access));
   }

   private static ProtoKeySerialization serializePrivateKey(RsaSsaPssPrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
      com.google.crypto.tink.proto.RsaSsaPssPrivateKey protoPrivateKey = com.google.crypto.tink.proto.RsaSsaPssPrivateKey.newBuilder().setVersion(0).setPublicKey(getProtoPublicKey(key.getPublicKey())).setD(encodeSecretBigInteger(key.getPrivateExponent(), a)).setP(encodeSecretBigInteger(key.getPrimeP(), a)).setQ(encodeSecretBigInteger(key.getPrimeQ(), a)).setDp(encodeSecretBigInteger(key.getPrimeExponentP(), a)).setDq(encodeSecretBigInteger(key.getPrimeExponentQ(), a)).setCrt(encodeSecretBigInteger(key.getCrtCoefficient(), a)).build();
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey", protoPrivateKey.toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, (OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static BigInteger decodeBigInteger(ByteString data) {
      return BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray());
   }

   private static RsaSsaPssParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to RsaSsaPssProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         RsaSsaPssKeyFormat format;
         try {
            format = RsaSsaPssKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing RsaSsaPssParameters failed: ", e);
         }

         return RsaSsaPssParameters.builder().setSigHashType((RsaSsaPssParameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(format.getParams().getSigHash())).setMgf1HashType((RsaSsaPssParameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(format.getParams().getMgf1Hash())).setPublicExponent(decodeBigInteger(format.getPublicExponent())).setModulusSizeBits(format.getModulusSizeInBits()).setSaltLengthBytes(format.getParams().getSaltLength()).setVariant((RsaSsaPssParameters.Variant)VARIANT_CONVERTER.fromProtoEnum(serialization.getKeyTemplate().getOutputPrefixType())).build();
      }
   }

   private static com.google.crypto.tink.signature.RsaSsaPssPublicKey parsePublicKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to RsaSsaPssProtoSerialization.parsePublicKey: " + serialization.getTypeUrl());
      } else {
         try {
            RsaSsaPssPublicKey protoKey = RsaSsaPssPublicKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               BigInteger modulus = decodeBigInteger(protoKey.getN());
               int modulusSizeInBits = modulus.bitLength();
               RsaSsaPssParameters parameters = RsaSsaPssParameters.builder().setSigHashType((RsaSsaPssParameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(protoKey.getParams().getSigHash())).setMgf1HashType((RsaSsaPssParameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(protoKey.getParams().getMgf1Hash())).setPublicExponent(decodeBigInteger(protoKey.getE())).setModulusSizeBits(modulusSizeInBits).setSaltLengthBytes(protoKey.getParams().getSaltLength()).setVariant((RsaSsaPssParameters.Variant)VARIANT_CONVERTER.fromProtoEnum(serialization.getOutputPrefixType())).build();
               return com.google.crypto.tink.signature.RsaSsaPssPublicKey.builder().setParameters(parameters).setModulus(modulus).setIdRequirement(serialization.getIdRequirementOrNull()).build();
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var6) {
            throw new GeneralSecurityException("Parsing RsaSsaPssPublicKey failed");
         }
      }
   }

   private static SecretBigInteger decodeSecretBigInteger(ByteString data, SecretKeyAccess access) {
      return SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(data.toByteArray()), access);
   }

   private static RsaSsaPssPrivateKey parsePrivateKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to RsaSsaPssProtoSerialization.parsePrivateKey: " + serialization.getTypeUrl());
      } else {
         try {
            com.google.crypto.tink.proto.RsaSsaPssPrivateKey protoKey = com.google.crypto.tink.proto.RsaSsaPssPrivateKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               RsaSsaPssPublicKey protoPublicKey = protoKey.getPublicKey();
               if (protoPublicKey.getVersion() != 0) {
                  throw new GeneralSecurityException("Only version 0 keys are accepted");
               } else {
                  BigInteger modulus = decodeBigInteger(protoPublicKey.getN());
                  int modulusSizeInBits = modulus.bitLength();
                  BigInteger publicExponent = decodeBigInteger(protoPublicKey.getE());
                  RsaSsaPssParameters parameters = RsaSsaPssParameters.builder().setSigHashType((RsaSsaPssParameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(protoPublicKey.getParams().getSigHash())).setMgf1HashType((RsaSsaPssParameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(protoPublicKey.getParams().getMgf1Hash())).setPublicExponent(publicExponent).setModulusSizeBits(modulusSizeInBits).setSaltLengthBytes(protoPublicKey.getParams().getSaltLength()).setVariant((RsaSsaPssParameters.Variant)VARIANT_CONVERTER.fromProtoEnum(serialization.getOutputPrefixType())).build();
                  com.google.crypto.tink.signature.RsaSsaPssPublicKey publicKey = com.google.crypto.tink.signature.RsaSsaPssPublicKey.builder().setParameters(parameters).setModulus(modulus).setIdRequirement(serialization.getIdRequirementOrNull()).build();
                  SecretKeyAccess a = SecretKeyAccess.requireAccess(access);
                  return RsaSsaPssPrivateKey.builder().setPublicKey(publicKey).setPrimes(decodeSecretBigInteger(protoKey.getP(), a), decodeSecretBigInteger(protoKey.getQ(), a)).setPrivateExponent(decodeSecretBigInteger(protoKey.getD(), a)).setPrimeExponents(decodeSecretBigInteger(protoKey.getDp(), a), decodeSecretBigInteger(protoKey.getDq(), a)).setCrtCoefficient(decodeSecretBigInteger(protoKey.getCrt(), a)).build();
               }
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var10) {
            throw new GeneralSecurityException("Parsing RsaSsaPssPrivateKey failed");
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

   private RsaSsaPssProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(RsaSsaPssProtoSerialization::parseParameters, PRIVATE_TYPE_URL_BYTES, ProtoParametersSerialization.class);
      PUBLIC_KEY_SERIALIZER = KeySerializer.create(RsaSsaPssProtoSerialization::serializePublicKey, com.google.crypto.tink.signature.RsaSsaPssPublicKey.class, ProtoKeySerialization.class);
      PUBLIC_KEY_PARSER = KeyParser.create(RsaSsaPssProtoSerialization::parsePublicKey, PUBLIC_TYPE_URL_BYTES, ProtoKeySerialization.class);
      PRIVATE_KEY_SERIALIZER = KeySerializer.create(RsaSsaPssProtoSerialization::serializePrivateKey, RsaSsaPssPrivateKey.class, ProtoKeySerialization.class);
      PRIVATE_KEY_PARSER = KeyParser.create(RsaSsaPssProtoSerialization::parsePrivateKey, PRIVATE_TYPE_URL_BYTES, ProtoKeySerialization.class);
      VARIANT_CONVERTER = EnumTypeProtoConverter.builder().add(OutputPrefixType.RAW, RsaSsaPssParameters.Variant.NO_PREFIX).add(OutputPrefixType.TINK, RsaSsaPssParameters.Variant.TINK).add(OutputPrefixType.CRUNCHY, RsaSsaPssParameters.Variant.CRUNCHY).add(OutputPrefixType.LEGACY, RsaSsaPssParameters.Variant.LEGACY).build();
      HASH_TYPE_CONVERTER = EnumTypeProtoConverter.builder().add(HashType.SHA256, RsaSsaPssParameters.HashType.SHA256).add(HashType.SHA384, RsaSsaPssParameters.HashType.SHA384).add(HashType.SHA512, RsaSsaPssParameters.HashType.SHA512).build();
   }
}
