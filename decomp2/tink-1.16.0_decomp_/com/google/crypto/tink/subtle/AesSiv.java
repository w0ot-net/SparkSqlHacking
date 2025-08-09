package com.google.crypto.tink.subtle;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.daead.AesSivKey;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.mac.internal.AesUtil;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesSiv implements DeterministicAead {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private static final Collection KEY_SIZES;
   private static final byte[] BLOCK_ZERO;
   private static final byte[] BLOCK_ONE;
   private final PrfAesCmac cmacForS2V;
   private final byte[] aesCtrKey;
   private final byte[] outputPrefix;
   private static final ThreadLocal localAesCtrCipher;

   @AccessesPartialKey
   public static DeterministicAead create(AesSivKey key) throws GeneralSecurityException {
      return new AesSiv(key.getKeyBytes().toByteArray(InsecureSecretKeyAccess.get()), key.getOutputPrefix());
   }

   private AesSiv(final byte[] key, com.google.crypto.tink.util.Bytes outputPrefix) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
      } else if (!KEY_SIZES.contains(key.length)) {
         throw new InvalidKeyException("invalid key size: " + key.length + " bytes; key must have 64 bytes");
      } else {
         byte[] k1 = Arrays.copyOfRange(key, 0, key.length / 2);
         this.aesCtrKey = Arrays.copyOfRange(key, key.length / 2, key.length);
         this.cmacForS2V = new PrfAesCmac(k1);
         this.outputPrefix = outputPrefix.toByteArray();
      }
   }

   public AesSiv(final byte[] key) throws GeneralSecurityException {
      this(key, com.google.crypto.tink.util.Bytes.copyFrom(new byte[0]));
   }

   private byte[] s2v(final byte[]... s) throws GeneralSecurityException {
      if (s.length == 0) {
         return this.cmacForS2V.compute(BLOCK_ONE, 16);
      } else {
         byte[] result = this.cmacForS2V.compute(BLOCK_ZERO, 16);

         for(int i = 0; i < s.length - 1; ++i) {
            byte[] currBlock;
            if (s[i] == null) {
               currBlock = new byte[0];
            } else {
               currBlock = s[i];
            }

            result = Bytes.xor(AesUtil.dbl(result), this.cmacForS2V.compute(currBlock, 16));
         }

         byte[] lastBlock = s[s.length - 1];
         if (lastBlock.length >= 16) {
            result = Bytes.xorEnd(lastBlock, result);
         } else {
            result = Bytes.xor(AesUtil.cmacPad(lastBlock), AesUtil.dbl(result));
         }

         return this.cmacForS2V.compute(result, 16);
      }
   }

   public byte[] encryptDeterministically(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
      if (plaintext.length > Integer.MAX_VALUE - this.outputPrefix.length - 16) {
         throw new GeneralSecurityException("plaintext too long");
      } else {
         Cipher aesCtr = (Cipher)localAesCtrCipher.get();
         byte[] computedIv = this.s2v(associatedData, plaintext);
         byte[] ivForJavaCrypto = (byte[])(([B)computedIv).clone();
         ivForJavaCrypto[8] = (byte)(ivForJavaCrypto[8] & 127);
         ivForJavaCrypto[12] = (byte)(ivForJavaCrypto[12] & 127);
         aesCtr.init(1, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(ivForJavaCrypto));
         int outputSize = this.outputPrefix.length + computedIv.length + plaintext.length;
         byte[] output = Arrays.copyOf(this.outputPrefix, outputSize);
         System.arraycopy(computedIv, 0, output, this.outputPrefix.length, computedIv.length);
         int written = aesCtr.doFinal(plaintext, 0, plaintext.length, output, this.outputPrefix.length + computedIv.length);
         if (written != plaintext.length) {
            throw new GeneralSecurityException("not enough data written");
         } else {
            return output;
         }
      }
   }

   public byte[] decryptDeterministically(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
      if (ciphertext.length < 16 + this.outputPrefix.length) {
         throw new GeneralSecurityException("Ciphertext too short.");
      } else if (!Util.isPrefix(this.outputPrefix, ciphertext)) {
         throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
      } else {
         Cipher aesCtr = (Cipher)localAesCtrCipher.get();
         byte[] expectedIv = Arrays.copyOfRange(ciphertext, this.outputPrefix.length, 16 + this.outputPrefix.length);
         byte[] ivForJavaCrypto = (byte[])(([B)expectedIv).clone();
         ivForJavaCrypto[8] = (byte)(ivForJavaCrypto[8] & 127);
         ivForJavaCrypto[12] = (byte)(ivForJavaCrypto[12] & 127);
         aesCtr.init(2, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(ivForJavaCrypto));
         int offset = 16 + this.outputPrefix.length;
         int ctrCiphertextLen = ciphertext.length - offset;
         byte[] decryptedPt = aesCtr.doFinal(ciphertext, offset, ctrCiphertextLen);
         if (ctrCiphertextLen == 0 && decryptedPt == null && SubtleUtil.isAndroid()) {
            decryptedPt = new byte[0];
         }

         byte[] computedIv = this.s2v(associatedData, decryptedPt);
         if (Bytes.equal(expectedIv, computedIv)) {
            return decryptedPt;
         } else {
            throw new AEADBadTagException("Integrity check failed.");
         }
      }
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
      KEY_SIZES = Arrays.asList(64);
      BLOCK_ZERO = new byte[16];
      BLOCK_ONE = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
      localAesCtrCipher = new ThreadLocal() {
         protected Cipher initialValue() {
            try {
               return (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
            } catch (GeneralSecurityException ex) {
               throw new IllegalStateException(ex);
            }
         }
      };
   }
}
