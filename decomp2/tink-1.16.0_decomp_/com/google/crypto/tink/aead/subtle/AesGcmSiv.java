package com.google.crypto.tink.aead.subtle;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.aead.AesGcmSivKey;
import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Hex;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Alpha
public final class AesGcmSiv implements Aead {
   private static final byte[] TEST_PLAINTEXT = Hex.decode("7a806c");
   private static final byte[] TEST_AAD = Hex.decode("46bb91c3c5");
   private static final byte[] TEST_KEY = Hex.decode("36864200e0eaf5284d884a0e77d31646");
   private static final byte[] TEST_NOUNCE = Hex.decode("bae8e37fc83441b16034566b");
   private static final byte[] TEST_RESULT = Hex.decode("af60eb711bd85bc1e4d3e0a462e074eea428a8");
   private static final ThreadLocal localAesGcmSivCipher = new ThreadLocal() {
      protected Cipher initialValue() {
         try {
            Cipher cipher = (Cipher)EngineFactory.CIPHER.getInstance("AES/GCM-SIV/NoPadding");
            return !AesGcmSiv.isAesGcmSivCipher(cipher) ? null : cipher;
         } catch (GeneralSecurityException ex) {
            throw new IllegalStateException(ex);
         }
      }
   };
   private static final int IV_SIZE_IN_BYTES = 12;
   private static final int TAG_SIZE_IN_BYTES = 16;
   private final SecretKey keySpec;
   private final byte[] outputPrefix;

   private static boolean isAesGcmSivCipher(Cipher cipher) {
      try {
         AlgorithmParameterSpec params = getParams(TEST_NOUNCE);
         cipher.init(2, new SecretKeySpec(TEST_KEY, "AES"), params);
         cipher.updateAAD(TEST_AAD);
         byte[] output = cipher.doFinal(TEST_RESULT, 0, TEST_RESULT.length);
         return Bytes.equal(output, TEST_PLAINTEXT);
      } catch (GeneralSecurityException var3) {
         return false;
      }
   }

   @AccessesPartialKey
   public static Aead create(AesGcmSivKey key) throws GeneralSecurityException {
      return new AesGcmSiv(key.getKeyBytes().toByteArray(InsecureSecretKeyAccess.get()), key.getOutputPrefix().toByteArray());
   }

   private AesGcmSiv(byte[] key, byte[] outputPrefix) throws GeneralSecurityException {
      this.outputPrefix = outputPrefix;
      Validators.validateAesKeySize(key.length);
      this.keySpec = new SecretKeySpec(key, "AES");
   }

   public AesGcmSiv(final byte[] key) throws GeneralSecurityException {
      this(key, new byte[0]);
   }

   private Cipher getAesGcmSivCipher() throws GeneralSecurityException {
      Cipher cipher = (Cipher)localAesGcmSivCipher.get();
      if (cipher == null) {
         throw new GeneralSecurityException("AES GCM SIV cipher is not available or is invalid.");
      } else {
         return cipher;
      }
   }

   private byte[] rawEncrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
      Cipher cipher = this.getAesGcmSivCipher();
      if (plaintext.length > 2147483619) {
         throw new GeneralSecurityException("plaintext too long");
      } else {
         byte[] ciphertext = new byte[12 + plaintext.length + 16];
         byte[] iv = Random.randBytes(12);
         System.arraycopy(iv, 0, ciphertext, 0, 12);
         AlgorithmParameterSpec params = getParams(iv);
         cipher.init(1, this.keySpec, params);
         if (associatedData != null && associatedData.length != 0) {
            cipher.updateAAD(associatedData);
         }

         int written = cipher.doFinal(plaintext, 0, plaintext.length, ciphertext, 12);
         if (written != plaintext.length + 16) {
            int actualTagSize = written - plaintext.length;
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, actualTagSize));
         } else {
            return ciphertext;
         }
      }
   }

   public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
      byte[] ciphertext = this.rawEncrypt(plaintext, associatedData);
      return this.outputPrefix.length == 0 ? ciphertext : Bytes.concat(this.outputPrefix, ciphertext);
   }

   private byte[] rawDecrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
      Cipher cipher = this.getAesGcmSivCipher();
      if (ciphertext.length < 28) {
         throw new GeneralSecurityException("ciphertext too short");
      } else {
         AlgorithmParameterSpec params = getParams(ciphertext, 0, 12);
         cipher.init(2, this.keySpec, params);
         if (associatedData != null && associatedData.length != 0) {
            cipher.updateAAD(associatedData);
         }

         return cipher.doFinal(ciphertext, 12, ciphertext.length - 12);
      }
   }

   public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
      if (this.outputPrefix.length == 0) {
         return this.rawDecrypt(ciphertext, associatedData);
      } else if (!Util.isPrefix(this.outputPrefix, ciphertext)) {
         throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
      } else {
         byte[] copiedCiphertext = Arrays.copyOfRange(ciphertext, this.outputPrefix.length, ciphertext.length);
         return this.rawDecrypt(copiedCiphertext, associatedData);
      }
   }

   private static AlgorithmParameterSpec getParams(final byte[] iv) throws GeneralSecurityException {
      return getParams(iv, 0, iv.length);
   }

   private static AlgorithmParameterSpec getParams(final byte[] buf, int offset, int len) throws GeneralSecurityException {
      return new GCMParameterSpec(128, buf, offset, len);
   }
}
