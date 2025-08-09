package com.google.crypto.tink.signature.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.signature.EcdsaParameters;
import com.google.crypto.tink.signature.EcdsaPrivateKey;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

@AccessesPartialKey
public final class EcdsaProtoSerialization {
   private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
   private static final Bytes PRIVATE_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey");
   private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
   private static final Bytes PUBLIC_TYPE_URL_BYTES = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.EcdsaPublicKey");
   private static final ParametersSerializer PARAMETERS_SERIALIZER = ParametersSerializer.create(EcdsaProtoSerialization::serializeParameters, EcdsaParameters.class, ProtoParametersSerialization.class);
   private static final ParametersParser PARAMETERS_PARSER;
   private static final KeySerializer PUBLIC_KEY_SERIALIZER;
   private static final KeyParser PUBLIC_KEY_PARSER;
   private static final KeySerializer PRIVATE_KEY_SERIALIZER;
   private static final KeyParser PRIVATE_KEY_PARSER;

   private static OutputPrefixType toProtoOutputPrefixType(EcdsaParameters.Variant variant) throws GeneralSecurityException {
      if (EcdsaParameters.Variant.TINK.equals(variant)) {
         return OutputPrefixType.TINK;
      } else if (EcdsaParameters.Variant.CRUNCHY.equals(variant)) {
         return OutputPrefixType.CRUNCHY;
      } else if (EcdsaParameters.Variant.NO_PREFIX.equals(variant)) {
         return OutputPrefixType.RAW;
      } else if (EcdsaParameters.Variant.LEGACY.equals(variant)) {
         return OutputPrefixType.LEGACY;
      } else {
         throw new GeneralSecurityException("Unable to serialize variant: " + variant);
      }
   }

   private static HashType toProtoHashType(EcdsaParameters.HashType hashType) throws GeneralSecurityException {
      if (EcdsaParameters.HashType.SHA256.equals(hashType)) {
         return HashType.SHA256;
      } else if (EcdsaParameters.HashType.SHA384.equals(hashType)) {
         return HashType.SHA384;
      } else if (EcdsaParameters.HashType.SHA512.equals(hashType)) {
         return HashType.SHA512;
      } else {
         throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
      }
   }

   private static EcdsaParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
      switch (hashType) {
         case SHA256:
            return EcdsaParameters.HashType.SHA256;
         case SHA384:
            return EcdsaParameters.HashType.SHA384;
         case SHA512:
            return EcdsaParameters.HashType.SHA512;
         default:
            throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
      }
   }

   private static EcdsaParameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
      switch (outputPrefixType) {
         case TINK:
            return EcdsaParameters.Variant.TINK;
         case CRUNCHY:
            return EcdsaParameters.Variant.CRUNCHY;
         case LEGACY:
            return EcdsaParameters.Variant.LEGACY;
         case RAW:
            return EcdsaParameters.Variant.NO_PREFIX;
         default:
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
      }
   }

   private static EllipticCurveType toProtoCurveType(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
      if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
         return EllipticCurveType.NIST_P256;
      } else if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
         return EllipticCurveType.NIST_P384;
      } else if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
         return EllipticCurveType.NIST_P521;
      } else {
         throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
      }
   }

   private static int getEncodingLength(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
      if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
         return 33;
      } else if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
         return 49;
      } else if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
         return 67;
      } else {
         throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
      }
   }

   private static EcdsaParameters.CurveType toCurveType(EllipticCurveType protoCurveType) throws GeneralSecurityException {
      switch (protoCurveType) {
         case NIST_P256:
            return EcdsaParameters.CurveType.NIST_P256;
         case NIST_P384:
            return EcdsaParameters.CurveType.NIST_P384;
         case NIST_P521:
            return EcdsaParameters.CurveType.NIST_P521;
         default:
            throw new GeneralSecurityException("Unable to parse EllipticCurveType: " + protoCurveType.getNumber());
      }
   }

   private static EcdsaSignatureEncoding toProtoSignatureEncoding(EcdsaParameters.SignatureEncoding encoding) throws GeneralSecurityException {
      if (EcdsaParameters.SignatureEncoding.IEEE_P1363.equals(encoding)) {
         return EcdsaSignatureEncoding.IEEE_P1363;
      } else if (EcdsaParameters.SignatureEncoding.DER.equals(encoding)) {
         return EcdsaSignatureEncoding.DER;
      } else {
         throw new GeneralSecurityException("Unable to serialize SignatureEncoding " + encoding);
      }
   }

   private static EcdsaParameters.SignatureEncoding toSignatureEncoding(EcdsaSignatureEncoding encoding) throws GeneralSecurityException {
      switch (encoding) {
         case IEEE_P1363:
            return EcdsaParameters.SignatureEncoding.IEEE_P1363;
         case DER:
            return EcdsaParameters.SignatureEncoding.DER;
         default:
            throw new GeneralSecurityException("Unable to parse EcdsaSignatureEncoding: " + encoding.getNumber());
      }
   }

   private static EcdsaParams getProtoParams(EcdsaParameters parameters) throws GeneralSecurityException {
      return EcdsaParams.newBuilder().setHashType(toProtoHashType(parameters.getHashType())).setCurve(toProtoCurveType(parameters.getCurveType())).setEncoding(toProtoSignatureEncoding(parameters.getSignatureEncoding())).build();
   }

   private static EcdsaPublicKey getProtoPublicKey(com.google.crypto.tink.signature.EcdsaPublicKey key) throws GeneralSecurityException {
      int encLength = getEncodingLength(key.getParameters().getCurveType());
      ECPoint publicPoint = key.getPublicPoint();
      return EcdsaPublicKey.newBuilder().setParams(getProtoParams(key.getParameters())).setX(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineX(), encLength))).setY(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineY(), encLength))).build();
   }

   private static ProtoParametersSerialization serializeParameters(EcdsaParameters parameters) throws GeneralSecurityException {
      return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey").setValue(EcdsaKeyFormat.newBuilder().setParams(getProtoParams(parameters)).build().toByteString()).setOutputPrefixType(toProtoOutputPrefixType(parameters.getVariant())).build());
   }

   private static ProtoKeySerialization serializePublicKey(com.google.crypto.tink.signature.EcdsaPublicKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.EcdsaPublicKey", getProtoPublicKey(key).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static ProtoKeySerialization serializePrivateKey(EcdsaPrivateKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      int encLength = getEncodingLength(key.getParameters().getCurveType());
      return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey", com.google.crypto.tink.proto.EcdsaPrivateKey.newBuilder().setPublicKey(getProtoPublicKey(key.getPublicKey())).setKeyValue(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(key.getPrivateValue().getBigInteger(SecretKeyAccess.requireAccess(access)), encLength))).build().toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
   }

   private static EcdsaParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
      if (!serialization.getKeyTemplate().getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
      } else {
         EcdsaKeyFormat format;
         try {
            format = EcdsaKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
         } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing EcdsaParameters failed: ", e);
         }

         return EcdsaParameters.builder().setHashType(toHashType(format.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(format.getParams().getEncoding())).setCurveType(toCurveType(format.getParams().getCurve())).setVariant(toVariant(serialization.getKeyTemplate().getOutputPrefixType())).build();
      }
   }

   private static com.google.crypto.tink.signature.EcdsaPublicKey parsePublicKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EcdsaPublicKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePublicKey: " + serialization.getTypeUrl());
      } else {
         try {
            EcdsaPublicKey protoKey = EcdsaPublicKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               EcdsaParameters parameters = EcdsaParameters.builder().setHashType(toHashType(protoKey.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(protoKey.getParams().getEncoding())).setCurveType(toCurveType(protoKey.getParams().getCurve())).setVariant(toVariant(serialization.getOutputPrefixType())).build();
               return com.google.crypto.tink.signature.EcdsaPublicKey.builder().setParameters(parameters).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getY().toByteArray()))).setIdRequirement(serialization.getIdRequirementOrNull()).build();
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var4) {
            throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
         }
      }
   }

   private static EcdsaPrivateKey parsePrivateKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
      if (!serialization.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey")) {
         throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePrivateKey: " + serialization.getTypeUrl());
      } else {
         try {
            com.google.crypto.tink.proto.EcdsaPrivateKey protoKey = com.google.crypto.tink.proto.EcdsaPrivateKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (protoKey.getVersion() != 0) {
               throw new GeneralSecurityException("Only version 0 keys are accepted");
            } else {
               EcdsaPublicKey protoPublicKey = protoKey.getPublicKey();
               if (protoPublicKey.getVersion() != 0) {
                  throw new GeneralSecurityException("Only version 0 keys are accepted");
               } else {
                  EcdsaParameters parameters = EcdsaParameters.builder().setHashType(toHashType(protoPublicKey.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(protoPublicKey.getParams().getEncoding())).setCurveType(toCurveType(protoPublicKey.getParams().getCurve())).setVariant(toVariant(serialization.getOutputPrefixType())).build();
                  com.google.crypto.tink.signature.EcdsaPublicKey publicKey = com.google.crypto.tink.signature.EcdsaPublicKey.builder().setParameters(parameters).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoPublicKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(protoPublicKey.getY().toByteArray()))).setIdRequirement(serialization.getIdRequirementOrNull()).build();
                  return EcdsaPrivateKey.builder().setPublicKey(publicKey).setPrivateValue(SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(protoKey.getKeyValue().toByteArray()), SecretKeyAccess.requireAccess(access))).build();
               }
            }
         } catch (IllegalArgumentException | InvalidProtocolBufferException var6) {
            throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
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

   private EcdsaProtoSerialization() {
   }

   static {
      PARAMETERS_PARSER = ParametersParser.create(EcdsaProtoSerialization::parseParameters, PRIVATE_TYPE_URL_BYTES, ProtoParametersSerialization.class);
      PUBLIC_KEY_SERIALIZER = KeySerializer.create(EcdsaProtoSerialization::serializePublicKey, com.google.crypto.tink.signature.EcdsaPublicKey.class, ProtoKeySerialization.class);
      PUBLIC_KEY_PARSER = KeyParser.create(EcdsaProtoSerialization::parsePublicKey, PUBLIC_TYPE_URL_BYTES, ProtoKeySerialization.class);
      PRIVATE_KEY_SERIALIZER = KeySerializer.create(EcdsaProtoSerialization::serializePrivateKey, EcdsaPrivateKey.class, ProtoKeySerialization.class);
      PRIVATE_KEY_PARSER = KeyParser.create(EcdsaProtoSerialization::parsePrivateKey, PRIVATE_TYPE_URL_BYTES, ProtoKeySerialization.class);
   }
}
