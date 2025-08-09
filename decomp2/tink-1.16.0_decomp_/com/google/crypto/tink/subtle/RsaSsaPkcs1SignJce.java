package com.google.crypto.tink.subtle;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.signature.RsaSsaPkcs1Parameters;
import com.google.crypto.tink.signature.RsaSsaPkcs1PrivateKey;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;

@Immutable
public final class RsaSsaPkcs1SignJce implements PublicKeySign {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private static final byte[] EMPTY;
   private static final byte[] LEGACY_MESSAGE_SUFFIX;
   private static final byte[] TEST_DATA;
   private final RSAPrivateCrtKey privateKey;
   private final RSAPublicKey publicKey;
   private final String signatureAlgorithm;
   private final byte[] outputPrefix;
   private final byte[] messageSuffix;

   private RsaSsaPkcs1SignJce(final RSAPrivateCrtKey priv, Enums.HashType hash, byte[] outputPrefix, byte[] messageSuffix) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use RSA PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
      } else {
         Validators.validateSignatureHash(hash);
         Validators.validateRsaModulusSize(priv.getModulus().bitLength());
         Validators.validateRsaPublicExponent(priv.getPublicExponent());
         this.privateKey = priv;
         this.signatureAlgorithm = SubtleUtil.toRsaSsaPkcs1Algo(hash);
         KeyFactory kf = (KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA");
         this.publicKey = (RSAPublicKey)kf.generatePublic(new RSAPublicKeySpec(priv.getModulus(), priv.getPublicExponent()));
         this.outputPrefix = outputPrefix;
         this.messageSuffix = messageSuffix;
      }
   }

   public RsaSsaPkcs1SignJce(final RSAPrivateCrtKey priv, Enums.HashType hash) throws GeneralSecurityException {
      this(priv, hash, EMPTY, EMPTY);
   }

   @AccessesPartialKey
   public static PublicKeySign create(RsaSsaPkcs1PrivateKey key) throws GeneralSecurityException {
      KeyFactory kf = (KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA");
      RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey)kf.generatePrivate(new RSAPrivateCrtKeySpec(key.getPublicKey().getModulus(), key.getParameters().getPublicExponent(), key.getPrivateExponent().getBigInteger(InsecureSecretKeyAccess.get()), key.getPrimeP().getBigInteger(InsecureSecretKeyAccess.get()), key.getPrimeQ().getBigInteger(InsecureSecretKeyAccess.get()), key.getPrimeExponentP().getBigInteger(InsecureSecretKeyAccess.get()), key.getPrimeExponentQ().getBigInteger(InsecureSecretKeyAccess.get()), key.getCrtCoefficient().getBigInteger(InsecureSecretKeyAccess.get())));
      PublicKeySign signer = new RsaSsaPkcs1SignJce(privateKey, (Enums.HashType)RsaSsaPkcs1VerifyJce.HASH_TYPE_CONVERTER.toProtoEnum(key.getParameters().getHashType()), key.getOutputPrefix().toByteArray(), key.getParameters().getVariant().equals(RsaSsaPkcs1Parameters.Variant.LEGACY) ? LEGACY_MESSAGE_SUFFIX : EMPTY);
      PublicKeyVerify verify = RsaSsaPkcs1VerifyJce.create(key.getPublicKey());

      try {
         verify.verify(signer.sign(TEST_DATA), TEST_DATA);
         return signer;
      } catch (GeneralSecurityException e) {
         throw new GeneralSecurityException("RsaSsaPkcs1 signing with private key followed by verifying with public key failed. The key may be corrupted.", e);
      }
   }

   private byte[] noPrefixSign(final byte[] data) throws GeneralSecurityException {
      Signature signer = (Signature)EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
      signer.initSign(this.privateKey);
      signer.update(data);
      if (this.messageSuffix.length > 0) {
         signer.update(this.messageSuffix);
      }

      byte[] signature = signer.sign();
      Signature verifier = (Signature)EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
      verifier.initVerify(this.publicKey);
      verifier.update(data);
      if (this.messageSuffix.length > 0) {
         verifier.update(this.messageSuffix);
      }

      if (!verifier.verify(signature)) {
         throw new RuntimeException("Security bug: RSA signature computation error");
      } else {
         return signature;
      }
   }

   public byte[] sign(final byte[] data) throws GeneralSecurityException {
      byte[] signature = this.noPrefixSign(data);
      return this.outputPrefix.length == 0 ? signature : Bytes.concat(this.outputPrefix, signature);
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
      EMPTY = new byte[0];
      LEGACY_MESSAGE_SUFFIX = new byte[]{0};
      TEST_DATA = new byte[]{1, 2, 3};
   }
}
