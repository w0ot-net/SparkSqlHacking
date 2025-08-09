package com.google.crypto.tink.signature.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.signature.Ed25519Parameters;
import com.google.crypto.tink.signature.Ed25519PublicKey;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

@Immutable
public final class Ed25519VerifyJce implements PublicKeyVerify {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private static final int PUBLIC_KEY_LEN = 32;
   private static final int SIGNATURE_LEN = 64;
   private static final String ALGORITHM_NAME = "Ed25519";
   private static final byte[] ED25519_X509_PREFIX;
   private final PublicKey publicKey;
   private final byte[] outputPrefix;
   private final byte[] messageSuffix;

   static byte[] x509EncodePublicKey(byte[] publicKey) throws GeneralSecurityException {
      if (publicKey.length != 32) {
         throw new IllegalArgumentException(String.format("Given public key's length is not %s.", 32));
      } else {
         return Bytes.concat(ED25519_X509_PREFIX, publicKey);
      }
   }

   @AccessesPartialKey
   public static PublicKeyVerify create(Ed25519PublicKey key) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use Ed25519 in FIPS-mode.");
      } else {
         return new Ed25519VerifyJce(key.getPublicKeyBytes().toByteArray(), key.getOutputPrefix().toByteArray(), key.getParameters().getVariant().equals(Ed25519Parameters.Variant.LEGACY) ? new byte[]{0} : new byte[0]);
      }
   }

   Ed25519VerifyJce(final byte[] publicKey) throws GeneralSecurityException {
      this(publicKey, new byte[0], new byte[0]);
   }

   private Ed25519VerifyJce(final byte[] publicKey, final byte[] outputPrefix, final byte[] messageSuffix) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use Ed25519 in FIPS-mode.");
      } else {
         KeySpec spec = new X509EncodedKeySpec(x509EncodePublicKey(publicKey));
         KeyFactory keyFactory = (KeyFactory)EngineFactory.KEY_FACTORY.getInstance("Ed25519");
         this.publicKey = keyFactory.generatePublic(spec);
         this.outputPrefix = outputPrefix;
         this.messageSuffix = messageSuffix;
      }
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

   public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
      if (signature.length != this.outputPrefix.length + 64) {
         throw new GeneralSecurityException(String.format("Invalid signature length: %s", 64));
      } else if (!Util.isPrefix(this.outputPrefix, signature)) {
         throw new GeneralSecurityException("Invalid signature (output prefix mismatch)");
      } else {
         Signature verifier = (Signature)EngineFactory.SIGNATURE.getInstance("Ed25519");
         verifier.initVerify(this.publicKey);
         verifier.update(data);
         verifier.update(this.messageSuffix);

         boolean verified;
         try {
            verified = verifier.verify(signature, this.outputPrefix.length, 64);
         } catch (RuntimeException var6) {
            verified = false;
         }

         if (!verified) {
            throw new GeneralSecurityException("Signature check failed.");
         }
      }
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
      ED25519_X509_PREFIX = new byte[]{48, 42, 48, 5, 6, 3, 43, 101, 112, 3, 33, 0};
   }
}
