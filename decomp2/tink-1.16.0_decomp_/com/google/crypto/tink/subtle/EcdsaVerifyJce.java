package com.google.crypto.tink.subtle;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.ConscryptUtil;
import com.google.crypto.tink.internal.EnumTypeProtoConverter;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.signature.EcdsaParameters;
import com.google.crypto.tink.signature.EcdsaPublicKey;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

@Immutable
public final class EcdsaVerifyJce implements PublicKeyVerify {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private static final byte[] EMPTY;
   private static final byte[] LEGACY_MESSAGE_SUFFIX;
   private final ECPublicKey publicKey;
   private final String signatureAlgorithm;
   private final EllipticCurves.EcdsaEncoding encoding;
   private final byte[] outputPrefix;
   private final byte[] messageSuffix;
   private final Provider provider;
   static final EnumTypeProtoConverter HASH_TYPE_CONVERTER;
   static final EnumTypeProtoConverter ENCODING_CONVERTER;
   static final EnumTypeProtoConverter CURVE_TYPE_CONVERTER;

   @AccessesPartialKey
   public static PublicKeyVerify create(EcdsaPublicKey key) throws GeneralSecurityException {
      ECPublicKey publicKey = EllipticCurves.getEcPublicKey((EllipticCurves.CurveType)CURVE_TYPE_CONVERTER.toProtoEnum(key.getParameters().getCurveType()), key.getPublicPoint().getAffineX().toByteArray(), key.getPublicPoint().getAffineY().toByteArray());
      return new EcdsaVerifyJce(publicKey, (Enums.HashType)HASH_TYPE_CONVERTER.toProtoEnum(key.getParameters().getHashType()), (EllipticCurves.EcdsaEncoding)ENCODING_CONVERTER.toProtoEnum(key.getParameters().getSignatureEncoding()), key.getOutputPrefix().toByteArray(), key.getParameters().getVariant().equals(EcdsaParameters.Variant.LEGACY) ? LEGACY_MESSAGE_SUFFIX : EMPTY);
   }

   private EcdsaVerifyJce(final ECPublicKey pubKey, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding, byte[] outputPrefix, byte[] messageSuffix) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
      } else {
         EllipticCurves.checkPublicKey(pubKey);
         this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hash);
         this.publicKey = pubKey;
         this.encoding = encoding;
         this.outputPrefix = outputPrefix;
         this.messageSuffix = messageSuffix;
         this.provider = ConscryptUtil.providerOrNull();
      }
   }

   public EcdsaVerifyJce(final ECPublicKey pubKey, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding) throws GeneralSecurityException {
      this(pubKey, hash, encoding, EMPTY, EMPTY);
   }

   private Signature getInstance(String signatureAlgorithm) throws GeneralSecurityException {
      return this.provider != null ? Signature.getInstance(signatureAlgorithm, this.provider) : (Signature)EngineFactory.SIGNATURE.getInstance(signatureAlgorithm);
   }

   private void noPrefixVerify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
      byte[] derSignature = signature;
      if (this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363) {
         EllipticCurve curve = this.publicKey.getParams().getCurve();
         if (signature.length != 2 * EllipticCurves.fieldSizeInBytes(curve)) {
            throw new GeneralSecurityException("Invalid signature");
         }

         derSignature = EllipticCurves.ecdsaIeee2Der(signature);
      }

      if (!EllipticCurves.isValidDerEncoding(derSignature)) {
         throw new GeneralSecurityException("Invalid signature");
      } else {
         Signature verifier = this.getInstance(this.signatureAlgorithm);
         verifier.initVerify(this.publicKey);
         verifier.update(data);
         if (this.messageSuffix.length > 0) {
            verifier.update(this.messageSuffix);
         }

         boolean verified = false;

         try {
            verified = verifier.verify(derSignature);
         } catch (RuntimeException var7) {
            verified = false;
         }

         if (!verified) {
            throw new GeneralSecurityException("Invalid signature");
         }
      }
   }

   public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
      if (this.outputPrefix.length == 0) {
         this.noPrefixVerify(signature, data);
      } else if (!Util.isPrefix(this.outputPrefix, signature)) {
         throw new GeneralSecurityException("Invalid signature (output prefix mismatch)");
      } else {
         byte[] signatureNoPrefix = Arrays.copyOfRange(signature, this.outputPrefix.length, signature.length);
         this.noPrefixVerify(signatureNoPrefix, data);
      }
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
      EMPTY = new byte[0];
      LEGACY_MESSAGE_SUFFIX = new byte[]{0};
      HASH_TYPE_CONVERTER = EnumTypeProtoConverter.builder().add(Enums.HashType.SHA256, EcdsaParameters.HashType.SHA256).add(Enums.HashType.SHA384, EcdsaParameters.HashType.SHA384).add(Enums.HashType.SHA512, EcdsaParameters.HashType.SHA512).build();
      ENCODING_CONVERTER = EnumTypeProtoConverter.builder().add(EllipticCurves.EcdsaEncoding.IEEE_P1363, EcdsaParameters.SignatureEncoding.IEEE_P1363).add(EllipticCurves.EcdsaEncoding.DER, EcdsaParameters.SignatureEncoding.DER).build();
      CURVE_TYPE_CONVERTER = EnumTypeProtoConverter.builder().add(EllipticCurves.CurveType.NIST_P256, EcdsaParameters.CurveType.NIST_P256).add(EllipticCurves.CurveType.NIST_P384, EcdsaParameters.CurveType.NIST_P384).add(EllipticCurves.CurveType.NIST_P521, EcdsaParameters.CurveType.NIST_P521).build();
   }
}
