package com.google.crypto.tink.signature.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.signature.Ed25519Parameters;
import com.google.crypto.tink.signature.Ed25519PrivateKey;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

@Immutable
public final class Ed25519SignJce implements PublicKeySign {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   public static final int SECRET_KEY_LEN = 32;
   public static final int SIGNATURE_LEN = 64;
   private static final String ALGORITHM_NAME = "Ed25519";
   private static final byte[] ED25519_PKCS8_PREFIX;
   private final byte[] outputPrefix;
   private final byte[] messageSuffix;
   private final PrivateKey privateKey;

   static byte[] pkcs8EncodePrivateKey(byte[] privateKey) throws GeneralSecurityException {
      if (privateKey.length != 32) {
         throw new IllegalArgumentException(String.format("Given private key's length is not %s", 32));
      } else {
         return Bytes.concat(ED25519_PKCS8_PREFIX, privateKey);
      }
   }

   @AccessesPartialKey
   public static PublicKeySign create(Ed25519PrivateKey key) throws GeneralSecurityException {
      return new Ed25519SignJce(key.getPrivateKeyBytes().toByteArray(InsecureSecretKeyAccess.get()), key.getOutputPrefix().toByteArray(), key.getParameters().getVariant().equals(Ed25519Parameters.Variant.LEGACY) ? new byte[]{0} : new byte[0]);
   }

   private Ed25519SignJce(final byte[] privateKey, final byte[] outputPrefix, final byte[] messageSuffix) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use Ed25519 in FIPS-mode.");
      } else {
         this.outputPrefix = outputPrefix;
         this.messageSuffix = messageSuffix;
         KeySpec spec = new PKCS8EncodedKeySpec(pkcs8EncodePrivateKey(privateKey));
         KeyFactory keyFactory = (KeyFactory)EngineFactory.KEY_FACTORY.getInstance("Ed25519");
         this.privateKey = keyFactory.generatePrivate(spec);
      }
   }

   public Ed25519SignJce(final byte[] privateKey) throws GeneralSecurityException {
      this(privateKey, new byte[0], new byte[0]);
   }

   public static boolean isSupported() {
      try {
         KeyFactory unusedKeyFactory = (KeyFactory)EngineFactory.KEY_FACTORY.getInstance("Ed25519");
         Signature unusedSignature = (Signature)EngineFactory.SIGNATURE.getInstance("Ed25519");
         return true;
      } catch (GeneralSecurityException var2) {
         return false;
      }
   }

   public byte[] sign(final byte[] data) throws GeneralSecurityException {
      Signature signer = (Signature)EngineFactory.SIGNATURE.getInstance("Ed25519");
      signer.initSign(this.privateKey);
      signer.update(data);
      signer.update(this.messageSuffix);
      byte[] signature = signer.sign();
      return this.outputPrefix.length == 0 ? signature : Bytes.concat(this.outputPrefix, signature);
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
      ED25519_PKCS8_PREFIX = new byte[]{48, 46, 2, 1, 0, 48, 5, 6, 3, 43, 101, 112, 4, 34, 4, 32};
   }
}
