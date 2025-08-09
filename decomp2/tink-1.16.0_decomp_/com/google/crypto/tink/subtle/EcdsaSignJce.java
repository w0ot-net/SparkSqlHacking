package com.google.crypto.tink.subtle;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.ConscryptUtil;
import com.google.crypto.tink.signature.EcdsaParameters;
import com.google.crypto.tink.signature.EcdsaPrivateKey;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;

@Immutable
public final class EcdsaSignJce implements PublicKeySign {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private static final byte[] EMPTY;
   private static final byte[] LEGACY_MESSAGE_SUFFIX;
   private static final byte[] TEST_DATA;
   private final ECPrivateKey privateKey;
   private final String signatureAlgorithm;
   private final EllipticCurves.EcdsaEncoding encoding;
   private final byte[] outputPrefix;
   private final byte[] messageSuffix;
   private final Provider provider;

   private EcdsaSignJce(final ECPrivateKey priv, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding, byte[] outputPrefix, byte[] messageSuffix) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
      } else {
         this.privateKey = priv;
         this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hash);
         this.encoding = encoding;
         this.outputPrefix = outputPrefix;
         this.messageSuffix = messageSuffix;
         this.provider = ConscryptUtil.providerOrNull();
      }
   }

   public EcdsaSignJce(final ECPrivateKey priv, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding) throws GeneralSecurityException {
      this(priv, hash, encoding, EMPTY, EMPTY);
   }

   @AccessesPartialKey
   public static PublicKeySign create(EcdsaPrivateKey key) throws GeneralSecurityException {
      Enums.HashType hashType = (Enums.HashType)EcdsaVerifyJce.HASH_TYPE_CONVERTER.toProtoEnum(key.getParameters().getHashType());
      EllipticCurves.EcdsaEncoding ecdsaEncoding = (EllipticCurves.EcdsaEncoding)EcdsaVerifyJce.ENCODING_CONVERTER.toProtoEnum(key.getParameters().getSignatureEncoding());
      EllipticCurves.CurveType curveType = (EllipticCurves.CurveType)EcdsaVerifyJce.CURVE_TYPE_CONVERTER.toProtoEnum(key.getParameters().getCurveType());
      ECPrivateKey privateKey = EllipticCurves.getEcPrivateKey(curveType, key.getPrivateValue().getBigInteger(InsecureSecretKeyAccess.get()).toByteArray());
      PublicKeySign signer = new EcdsaSignJce(privateKey, hashType, ecdsaEncoding, key.getOutputPrefix().toByteArray(), key.getParameters().getVariant().equals(EcdsaParameters.Variant.LEGACY) ? LEGACY_MESSAGE_SUFFIX : EMPTY);
      PublicKeyVerify verify = EcdsaVerifyJce.create(key.getPublicKey());

      try {
         verify.verify(signer.sign(TEST_DATA), TEST_DATA);
         return signer;
      } catch (GeneralSecurityException e) {
         throw new GeneralSecurityException("ECDSA signing with private key followed by verifying with public key failed. The key may be corrupted.", e);
      }
   }

   private Signature getInstance(String signatureAlgorithm) throws GeneralSecurityException {
      return this.provider != null ? Signature.getInstance(signatureAlgorithm, this.provider) : (Signature)EngineFactory.SIGNATURE.getInstance(signatureAlgorithm);
   }

   public byte[] sign(final byte[] data) throws GeneralSecurityException {
      Signature signer = this.getInstance(this.signatureAlgorithm);
      signer.initSign(this.privateKey);
      signer.update(data);
      if (this.messageSuffix.length > 0) {
         signer.update(this.messageSuffix);
      }

      byte[] signature = signer.sign();
      if (this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363) {
         EllipticCurve curve = this.privateKey.getParams().getCurve();
         signature = EllipticCurves.ecdsaDer2Ieee(signature, 2 * EllipticCurves.fieldSizeInBytes(curve));
      }

      return this.outputPrefix.length == 0 ? signature : Bytes.concat(this.outputPrefix, signature);
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
      EMPTY = new byte[0];
      LEGACY_MESSAGE_SUFFIX = new byte[]{0};
      TEST_DATA = new byte[]{1, 2, 3};
   }
}
