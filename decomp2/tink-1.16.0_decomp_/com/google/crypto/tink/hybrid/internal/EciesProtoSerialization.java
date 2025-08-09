package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.TinkProtoParametersFormat;
import com.google.crypto.tink.hybrid.EciesParameters;
import com.google.crypto.tink.hybrid.EciesPrivateKey;
import com.google.crypto.tink.hybrid.EciesPublicKey;
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
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesAeadHkdfPrivateKey;
import com.google.crypto.tink.proto.EciesAeadHkdfPublicKey;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.crypto.tink.util.SecretBytes;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

@AccessesPartialKey
public final class EciesProtoSerialization {
   private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
   private static final Bytes PRIVATE_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
   private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
   private static final Bytes PUBLIC_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(EciesProtoSerialization::serializeParameters, EciesParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer PUBLIC_KEY_SERIALIZER;
   private static final KeyParser PUBLIC_KEY_PARSER;
   private static final KeySerializer PRIVATE_KEY_SERIALIZER;
   private static final KeyParser PRIVATE_KEY_PARSER;
   private static final EnumTypeProtoConverter VARIANT_CONVERTER;
   private static final EnumTypeProtoConverter HASH_TYPE_CONVERTER;
   private static final EnumTypeProtoConverter CURVE_TYPE_CONVERTER;
   private static final EnumTypeProtoConverter POINT_FORMAT_CONVERTER;

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

   private static EciesAeadHkdfParams toProtoParameters(EciesParameters parameters) throws GeneralSecurityException {
      EciesHkdfKemParams.Builder kemProtoParamsBuilder = EciesHkdfKemParams.newBuilder().setCurveType((EllipticCurveType)CURVE_TYPE_CONVERTER.toProtoEnum(parameters.getCurveType())).setHkdfHashType((HashType)HASH_TYPE_CONVERTER.toProtoEnum(parameters.getHashType()));
      if (parameters.getSalt() != null && parameters.getSalt().size() > 0) {
         kemProtoParamsBuilder.setHkdfSalt(ByteString.copyFrom(parameters.getSalt().toByteArray()));
      }

      EciesHkdfKemParams kemProtoParams = kemProtoParamsBuilder.build();

      EciesAeadDemParams demProtoParams;
      try {
         KeyTemplate demKeyTemplate = KeyTemplate.parseFrom(TinkProtoParametersFormat.serialize(parameters.getDemParameters()), ExtensionRegistryLite.getEmptyRegistry());
         demProtoParams = EciesAeadDemParams.newBuilder().setAeadDem(KeyTemplate.newBuilder().setTypeUrl(demKeyTemplate.getTypeUrl()).setOutputPrefixType(OutputPrefixType.TINK).setValue(demKeyTemplate.getValue()).build()).build();
      } catch (InvalidProtocolBufferException e) {
         throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
      }

      EciesParameters.PointFormat pointFormat = parameters.getNistCurvePointFormat();
      if (pointFormat == null) {
         pointFormat = EciesParameters.PointFormat.COMPRESSED;
      }

      return EciesAeadHkdfParams.newBuilder().setKemParams(kemProtoParams).setDemParams(demProtoParams).setEcPointFormat((EcPointFormat)POINT_FORMAT_CONVERTER.toProtoEnum(pointFormat)).build();
   }

   private static EciesParameters fromProtoParameters(OutputPrefixType outputPrefixType, EciesAeadHkdfParams protoParams) throws GeneralSecurityException {
      KeyTemplate aeadKeyTemplate = KeyTemplate.newBuilder().setTypeUrl(protoParams.getDemParams().getAeadDem().getTypeUrl()).setOutputPrefixType(OutputPrefixType.RAW).setValue(protoParams.getDemParams().getAeadDem().getValue()).build();
      EciesParameters.Builder builder = EciesParameters.builder().setVariant((EciesParameters.Variant)VARIANT_CONVERTER.fromProtoEnum(outputPrefixType)).setCurveType((EciesParameters.CurveType)CURVE_TYPE_CONVERTER.fromProtoEnum(protoParams.getKemParams().getCurveType())).setHashType((EciesParameters.HashType)HASH_TYPE_CONVERTER.fromProtoEnum(protoParams.getKemParams().getHkdfHashType())).setDemParameters(TinkProtoParametersFormat.parse(aeadKeyTemplate.toByteArray())).setSalt(Bytes.copyFrom(protoParams.getKemParams().getHkdfSalt().toByteArray()));
      if (!protoParams.getKemParams().getCurveType().equals(EllipticCurveType.CURVE25519)) {
         builder.setNistCurvePointFormat((EciesParameters.PointFormat)POINT_FORMAT_CONVERTER.fromProtoEnum(protoParams.getEcPointFormat()));
      } else if (!protoParams.getEcPointFormat().equals(EcPointFormat.COMPRESSED)) {
         throw new GeneralSecurityException("For CURVE25519 EcPointFormat must be compressed");
      }

      return builder.build();
   }

   private static int getEncodingLength(EciesParameters.CurveType curveType) throws GeneralSecurityException {
      if (EciesParameters.CurveType.NIST_P256.equals(curveType)) {
         return 33;
      } else if (EciesParameters.CurveType.NIST_P384.equals(curveType)) {
         return 49;
      } else if (EciesParameters.CurveType.NIST_P521.equals(curveType)) {
         return 67;
      } else {
         throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
      }
   }

   private static EciesAeadHkdfPublicKey toProtoPublicKey(EciesPublicKey key) throws GeneralSecurityException {
      if (key.getParameters().getCurveType().equals(EciesParameters.CurveType.X25519)) {
         return EciesAeadHkdfPublicKey.newBuilder().setVersion(0).setParams(toProtoParameters(key.getParameters())).setX(ByteString.copyFrom(key.getX25519CurvePointBytes().toByteArray())).setY(ByteString.EMPTY).build();
      } else {
         int encLength = getEncodingLength(key.getParameters().getCurveType());
         ECPoint publicPoint = key.getNistCurvePoint();
         if (publicPoint == null) {
            throw new GeneralSecurityException("NistCurvePoint was null for NIST curve");
         } else {
            return EciesAeadHkdfPublicKey.newBuilder().setVersion(0).setParams(toProtoParameters(key.getParameters())).setX(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineX(), encLength))).setY(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineY(), encLength))).build();
         }
      }
   }

   private static EciesAeadHkdfPrivateKey toProtoPrivateKey(EciesPrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      EciesAeadHkdfPrivateKey.Builder builder = EciesAeadHkdfPrivateKey.newBuilder().setVersion(0).setPublicKey(toProtoPublicKey(key.getPublicKey()));
      if (key.getParameters().getCurveType().equals(EciesParameters.CurveType.X25519)) {
         builder.setKeyValue(ByteString.copyFrom(key.getX25519PrivateKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access))));
      } else {
         int encLength = getEncodingLength(key.getParameters().getCurveType());
         builder.setKeyValue(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(key.getNistPrivateKeyValue().getBigInteger(SecretKeyAccess.requireAccess(access)), encLength)));
      }

      return builder.build();
   }

   private static ProtoParametersSerialization serializeParameters(EciesParameters parameters) throws GeneralSecurityException {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").setValue(EciesAeadHkdfKeyFormat.newBuilder().setParams(toProtoParameters(parameters)).build().toByteString()).setOutputPrefixType((OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(parameters.getVariant())).build());
   }

   private static ProtoKeySerialization serializePublicKey(EciesPublicKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", toProtoPublicKey(key).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, (OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static ProtoKeySerialization serializePrivateKey(EciesPrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", toProtoPrivateKey(key, access).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, (OutputPrefixType)VARIANT_CONVERTER.toProtoEnum(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static EciesParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         EciesAeadHkdfKeyFormat format;
         try {
            format = EciesAeadHkdfKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
         }

         return fromProtoParameters(serialization.getKeyTemplate().getOutputPrefixType(), format.getParams());
      }
   }

   private static EciesPublicKey parsePublicKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePublicKey: " + serialization.getTypeUrl());
      } else {
         try {
            EciesAeadHkdfPublicKey protoKey = EciesAeadHkdfPublicKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               EciesParameters parameters = fromProtoParameters(serialization.getOutputPrefixType(), protoKey.getParams());
               if (parameters.getCurveType().equals(EciesParameters.CurveType.X25519)) {
                  if (!protoKey.getY().isEmpty()) {
                     throw new GeneralSecurityException("Y must be empty for X25519 points");
                  } else {
                     return EciesPublicKey.createForCurveX25519(parameters, Bytes.copyFrom(protoKey.getX().toByteArray()), serialization.getIdRequirementOrNull());
                  }
               } else {
                  ECPoint point = new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getY().toByteArray()));
                  return EciesPublicKey.createForNistCurve(parameters, point, serialization.getIdRequirementOrNull());
               }
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var5) {
            throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
         }
      }
   }

   private static EciesPrivateKey parsePrivateKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePrivateKey: " + serialization.getTypeUrl());
      } else {
         try {
            EciesAeadHkdfPrivateKey protoKey = EciesAeadHkdfPrivateKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               EciesAeadHkdfPublicKey protoPublicKey = protoKey.getPublicKey();
               if (protoPublicKey.getVersion() != 0) {
                  throw new GeneralSecurityException("Only version 0 keys are accepted");
               } else {
                  EciesParameters parameters = fromProtoParameters(serialization.getOutputPrefixType(), protoPublicKey.getParams());
                  if (parameters.getCurveType().equals(EciesParameters.CurveType.X25519)) {
                     EciesPublicKey publicKey = EciesPublicKey.createForCurveX25519(parameters, Bytes.copyFrom(protoPublicKey.getX().toByteArray()), serialization.getIdRequirementOrNull());
                     return EciesPrivateKey.createForCurveX25519(publicKey, SecretBytes.copyFrom(protoKey.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access)));
                  } else {
                     ECPoint point = new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoPublicKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(protoPublicKey.getY().toByteArray()));
                     EciesPublicKey publicKey = EciesPublicKey.createForNistCurve(parameters, point, serialization.getIdRequirementOrNull());
                     return EciesPrivateKey.createForNistCurve(publicKey, SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getKeyValue().toByteArray()), SecretKeyAccess.requireAccess(access)));
                  }
               }
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var7) {
            throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
         }
      }
   }

   private EciesProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(EciesProtoSerialization::parseParameters, PRIVATE_TYPE_URL_BYTES, ProtoParametersSerialization.class);
      PUBLIC_KEY_SERIALIZER = KeySerializer.create(EciesProtoSerialization::serializePublicKey, EciesPublicKey.class, ProtoKeySerialization.class);
      PUBLIC_KEY_PARSER = KeyParser.create(EciesProtoSerialization::parsePublicKey, PUBLIC_TYPE_URL_BYTES, ProtoKeySerialization.class);
      PRIVATE_KEY_SERIALIZER = KeySerializer.create(EciesProtoSerialization::serializePrivateKey, EciesPrivateKey.class, ProtoKeySerialization.class);
      PRIVATE_KEY_PARSER = KeyParser.create(EciesProtoSerialization::parsePrivateKey, PRIVATE_TYPE_URL_BYTES, ProtoKeySerialization.class);
      VARIANT_CONVERTER = EnumTypeProtoConverter.builder().add(OutputPrefixType.RAW, EciesParameters.Variant.NO_PREFIX).add(OutputPrefixType.TINK, EciesParameters.Variant.TINK).add(OutputPrefixType.LEGACY, EciesParameters.Variant.CRUNCHY).add(OutputPrefixType.CRUNCHY, EciesParameters.Variant.CRUNCHY).build();
      HASH_TYPE_CONVERTER = EnumTypeProtoConverter.builder().add(HashType.SHA1, EciesParameters.HashType.SHA1).add(HashType.SHA224, EciesParameters.HashType.SHA224).add(HashType.SHA256, EciesParameters.HashType.SHA256).add(HashType.SHA384, EciesParameters.HashType.SHA384).add(HashType.SHA512, EciesParameters.HashType.SHA512).build();
      CURVE_TYPE_CONVERTER = EnumTypeProtoConverter.builder().add(EllipticCurveType.NIST_P256, EciesParameters.CurveType.NIST_P256).add(EllipticCurveType.NIST_P384, EciesParameters.CurveType.NIST_P384).add(EllipticCurveType.NIST_P521, EciesParameters.CurveType.NIST_P521).add(EllipticCurveType.CURVE25519, EciesParameters.CurveType.X25519).build();
      POINT_FORMAT_CONVERTER = EnumTypeProtoConverter.builder().add(EcPointFormat.UNCOMPRESSED, EciesParameters.PointFormat.UNCOMPRESSED).add(EcPointFormat.COMPRESSED, EciesParameters.PointFormat.COMPRESSED).add(EcPointFormat.DO_NOT_USE_CRUNCHY_UNCOMPRESSED, EciesParameters.PointFormat.LEGACY_UNCOMPRESSED).build();
   }
}
