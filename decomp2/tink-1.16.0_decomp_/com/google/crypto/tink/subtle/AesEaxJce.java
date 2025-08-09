package com.google.crypto.tink.subtle;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.aead.AesEaxKey;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.Util;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesEaxJce implements Aead {
   public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS;
   private static final ThreadLocal localEcbCipher;
   private static final ThreadLocal localCtrCipher;
   static final int BLOCK_SIZE_IN_BYTES = 16;
   static final int TAG_SIZE_IN_BYTES = 16;
   private final byte[] b;
   private final byte[] p;
   private final byte[] outputPrefix;
   private final SecretKeySpec keySpec;
   private final int ivSizeInBytes;

   @AccessesPartialKey
   public static Aead create(AesEaxKey key) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
      } else if (key.getParameters().getTagSizeBytes() != 16) {
         throw new GeneralSecurityException("AesEaxJce only supports 16 byte tag size, not " + key.getParameters().getTagSizeBytes());
      } else {
         return new AesEaxJce(key.getKeyBytes().toByteArray(InsecureSecretKeyAccess.get()), key.getParameters().getIvSizeBytes(), key.getOutputPrefix().toByteArray());
      }
   }

   private AesEaxJce(final byte[] key, int ivSizeInBytes, byte[] outputPrefix) throws GeneralSecurityException {
      if (!FIPS.isCompatible()) {
         throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
      } else if (ivSizeInBytes != 12 && ivSizeInBytes != 16) {
         throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
      } else {
         this.ivSizeInBytes = ivSizeInBytes;
         Validators.validateAesKeySize(key.length);
         this.keySpec = new SecretKeySpec(key, "AES");
         Cipher ecb = (Cipher)localEcbCipher.get();
         ecb.init(1, this.keySpec);
         byte[] block = ecb.doFinal(new byte[16]);
         this.b = multiplyByX(block);
         this.p = multiplyByX(this.b);
         this.outputPrefix = outputPrefix;
      }
   }

   public AesEaxJce(final byte[] key, int ivSizeInBytes) throws GeneralSecurityException {
      this(key, ivSizeInBytes, new byte[0]);
   }

   private static void xor(final byte[] x, final byte[] y) {
      int len = x.length;

      for(int i = 0; i < len; ++i) {
         x[i] ^= y[i];
      }

   }

   private static byte[] multiplyByX(final byte[] block) {
      byte[] res = new byte[16];

      for(int i = 0; i < 15; ++i) {
         res[i] = (byte)((block[i] << 1 ^ (block[i + 1] & 255) >>> 7) & 255);
      }

      res[15] = (byte)(block[15] << 1 ^ block[0] >> 7 & 135);
      return res;
   }

   private byte[] pad(final byte[] data, int lastBlockFrom, int lastBlockTo) {
      byte[] lastBlock = Arrays.copyOfRange(data, lastBlockFrom, lastBlockTo);
      if (lastBlock.length == 16) {
         xor(lastBlock, this.b);
         return lastBlock;
      } else {
         byte[] res = Arrays.copyOf(this.p, 16);

         for(int i = 0; i < lastBlock.length; ++i) {
            res[i] ^= lastBlock[i];
         }

         res[lastBlock.length] = (byte)(res[lastBlock.length] ^ 128);
         return res;
      }
   }

   private byte[] omac(Cipher ecb, int tag, final byte[] data, int offset, int length) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
      assert length >= 0;

      assert 0 <= tag && tag <= 3;

      byte[] block = new byte[16];
      block[15] = (byte)tag;
      if (length == 0) {
         xor(block, this.b);
         return ecb.doFinal(block);
      } else {
         byte[] buffer = new byte[16];
         ecb.doFinal(block, 0, 16, buffer);
         byte[] var11 = buffer;
         buffer = block;

         int position;
         for(position = 0; length - position > 16; position += 16) {
            for(int i = 0; i < 16; ++i) {
               var11[i] ^= data[offset + position + i];
            }

            ecb.doFinal(var11, 0, 16, buffer);
            byte[] var13 = var11;
            var11 = buffer;
            buffer = var13;
         }

         byte[] padded = this.pad(data, offset + position, offset + length);
         xor(var11, padded);
         ecb.doFinal(var11, 0, 16, buffer);
         return buffer;
      }
   }

   public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
      if (plaintext.length > Integer.MAX_VALUE - this.outputPrefix.length - this.ivSizeInBytes - 16) {
         throw new GeneralSecurityException("plaintext too long");
      } else {
         byte[] ciphertext = Arrays.copyOf(this.outputPrefix, this.outputPrefix.length + this.ivSizeInBytes + plaintext.length + 16);
         byte[] iv = Random.randBytes(this.ivSizeInBytes);
         System.arraycopy(iv, 0, ciphertext, this.outputPrefix.length, this.ivSizeInBytes);
         Cipher ecb = (Cipher)localEcbCipher.get();
         ecb.init(1, this.keySpec);
         byte[] n = this.omac(ecb, 0, iv, 0, iv.length);
         byte[] aad = associatedData;
         if (associatedData == null) {
            aad = new byte[0];
         }

         byte[] h = this.omac(ecb, 1, aad, 0, aad.length);
         Cipher ctr = (Cipher)localCtrCipher.get();
         ctr.init(1, this.keySpec, new IvParameterSpec(n));
         ctr.doFinal(plaintext, 0, plaintext.length, ciphertext, this.outputPrefix.length + this.ivSizeInBytes);
         byte[] t = this.omac(ecb, 2, ciphertext, this.outputPrefix.length + this.ivSizeInBytes, plaintext.length);
         int offset = this.outputPrefix.length + plaintext.length + this.ivSizeInBytes;

         for(int i = 0; i < 16; ++i) {
            ciphertext[offset + i] = (byte)(h[i] ^ n[i] ^ t[i]);
         }

         return ciphertext;
      }
   }

   public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
      int plaintextLength = ciphertext.length - this.outputPrefix.length - this.ivSizeInBytes - 16;
      if (plaintextLength < 0) {
         throw new GeneralSecurityException("ciphertext too short");
      } else if (!Util.isPrefix(this.outputPrefix, ciphertext)) {
         throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
      } else {
         Cipher ecb = (Cipher)localEcbCipher.get();
         ecb.init(1, this.keySpec);
         byte[] n = this.omac(ecb, 0, ciphertext, this.outputPrefix.length, this.ivSizeInBytes);
         byte[] aad = associatedData;
         if (associatedData == null) {
            aad = new byte[0];
         }

         byte[] h = this.omac(ecb, 1, aad, 0, aad.length);
         byte[] t = this.omac(ecb, 2, ciphertext, this.outputPrefix.length + this.ivSizeInBytes, plaintextLength);
         byte res = 0;
         int offset = ciphertext.length - 16;

         for(int i = 0; i < 16; ++i) {
            res = (byte)(res | ciphertext[offset + i] ^ h[i] ^ n[i] ^ t[i]);
         }

         if (res != 0) {
            throw new AEADBadTagException("tag mismatch");
         } else {
            Cipher ctr = (Cipher)localCtrCipher.get();
            ctr.init(1, this.keySpec, new IvParameterSpec(n));
            return ctr.doFinal(ciphertext, this.outputPrefix.length + this.ivSizeInBytes, plaintextLength);
         }
      }
   }

   static {
      FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
      localEcbCipher = new ThreadLocal() {
         protected Cipher initialValue() {
            try {
               return (Cipher)EngineFactory.CIPHER.getInstance("AES/ECB/NOPADDING");
            } catch (GeneralSecurityException ex) {
               throw new IllegalStateException(ex);
            }
         }
      };
      localCtrCipher = new ThreadLocal() {
         protected Cipher initialValue() {
            try {
               return (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NOPADDING");
            } catch (GeneralSecurityException ex) {
               throw new IllegalStateException(ex);
            }
         }
      };
   }
}
