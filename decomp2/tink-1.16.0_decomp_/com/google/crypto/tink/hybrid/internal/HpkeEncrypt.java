package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.hybrid.HpkeParameters;
import com.google.crypto.tink.hybrid.HpkePublicKey;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public final class HpkeEncrypt implements HybridEncrypt {
   private static final byte[] EMPTY_ASSOCIATED_DATA = new byte[0];
   private final byte[] recipientPublicKey;
   private final HpkeKem kem;
   private final HpkeKdf kdf;
   private final HpkeAead aead;
   private final byte[] outputPrefix;

   private HpkeEncrypt(Bytes recipientPublicKey, HpkeKem kem, HpkeKdf kdf, HpkeAead aead, Bytes outputPrefix) {
      this.recipientPublicKey = recipientPublicKey.toByteArray();
      this.kem = kem;
      this.kdf = kdf;
      this.aead = aead;
      this.outputPrefix = outputPrefix.toByteArray();
   }

   @AccessesPartialKey
   public static HybridEncrypt create(HpkePublicKey key) throws GeneralSecurityException {
      HpkeParameters parameters = key.getParameters();
      return new HpkeEncrypt(key.getPublicKeyBytes(), createKem(parameters.getKemId()), createKdf(parameters.getKdfId()), createAead(parameters.getAeadId()), key.getOutputPrefix());
   }

   static HpkeKem createKem(HpkeParameters.KemId kemId) throws GeneralSecurityException {
      if (kemId.equals(HpkeParameters.KemId.DHKEM_X25519_HKDF_SHA256)) {
         return new X25519HpkeKem(new HkdfHpkeKdf("HmacSha256"));
      } else if (kemId.equals(HpkeParameters.KemId.DHKEM_P256_HKDF_SHA256)) {
         return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P256);
      } else if (kemId.equals(HpkeParameters.KemId.DHKEM_P384_HKDF_SHA384)) {
         return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P384);
      } else if (kemId.equals(HpkeParameters.KemId.DHKEM_P521_HKDF_SHA512)) {
         return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P521);
      } else {
         throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
      }
   }

   static HpkeKdf createKdf(HpkeParameters.KdfId kdfId) throws GeneralSecurityException {
      if (kdfId.equals(HpkeParameters.KdfId.HKDF_SHA256)) {
         return new HkdfHpkeKdf("HmacSha256");
      } else if (kdfId.equals(HpkeParameters.KdfId.HKDF_SHA384)) {
         return new HkdfHpkeKdf("HmacSha384");
      } else if (kdfId.equals(HpkeParameters.KdfId.HKDF_SHA512)) {
         return new HkdfHpkeKdf("HmacSha512");
      } else {
         throw new GeneralSecurityException("Unrecognized HPKE KDF identifier");
      }
   }

   static HpkeAead createAead(HpkeParameters.AeadId aeadId) throws GeneralSecurityException {
      if (aeadId.equals(HpkeParameters.AeadId.AES_128_GCM)) {
         return new AesGcmHpkeAead(16);
      } else if (aeadId.equals(HpkeParameters.AeadId.AES_256_GCM)) {
         return new AesGcmHpkeAead(32);
      } else if (aeadId.equals(HpkeParameters.AeadId.CHACHA20_POLY1305)) {
         return new ChaCha20Poly1305HpkeAead();
      } else {
         throw new GeneralSecurityException("Unrecognized HPKE AEAD identifier");
      }
   }

   public byte[] encrypt(final byte[] plaintext, final byte[] contextInfo) throws GeneralSecurityException {
      byte[] info = contextInfo;
      if (contextInfo == null) {
         info = new byte[0];
      }

      HpkeContext context = HpkeContext.createSenderContext(this.recipientPublicKey, this.kem, this.kdf, this.aead, info);
      byte[] encapsulatedKey = context.getEncapsulatedKey();
      int ciphertextOffset = this.outputPrefix.length + encapsulatedKey.length;
      byte[] ciphertextWithPrefix = context.seal(plaintext, ciphertextOffset, EMPTY_ASSOCIATED_DATA);
      System.arraycopy(this.outputPrefix, 0, ciphertextWithPrefix, 0, this.outputPrefix.length);
      System.arraycopy(encapsulatedKey, 0, ciphertextWithPrefix, this.outputPrefix.length, encapsulatedKey.length);
      return ciphertextWithPrefix;
   }
}
