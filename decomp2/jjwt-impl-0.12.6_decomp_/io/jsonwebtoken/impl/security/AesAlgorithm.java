package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.IvSupplier;
import io.jsonwebtoken.security.KeyBuilderSupplier;
import io.jsonwebtoken.security.KeyLengthSupplier;
import io.jsonwebtoken.security.Request;
import io.jsonwebtoken.security.SecretKeyBuilder;
import io.jsonwebtoken.security.WeakKeyException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

abstract class AesAlgorithm extends CryptoAlgorithm implements KeyBuilderSupplier, KeyLengthSupplier {
   protected static final String KEY_ALG_NAME = "AES";
   protected static final int BLOCK_SIZE = 128;
   protected static final int BLOCK_BYTE_SIZE = 16;
   protected static final int GCM_IV_SIZE = 96;
   protected static final String DECRYPT_NO_IV = "This algorithm implementation rejects decryption requests that do not include initialization vectors. AES ciphertext without an IV is weak and susceptible to attack.";
   protected final int keyBitLength;
   protected final int ivBitLength;
   protected final int tagBitLength;
   protected final boolean gcm;

   static void assertKeyBitLength(int keyBitLength) {
      if (keyBitLength != 128 && keyBitLength != 192 && keyBitLength != 256) {
         String msg = "Invalid AES key length: " + Bytes.bitsMsg((long)keyBitLength) + ". AES only supports " + "128, 192, or 256 bit keys.";
         throw new IllegalArgumentException(msg);
      }
   }

   static SecretKey keyFor(byte[] bytes) {
      int bitlen = (int)Bytes.bitLength(bytes);
      assertKeyBitLength(bitlen);
      return new SecretKeySpec(bytes, "AES");
   }

   AesAlgorithm(String id, String jcaTransformation, int keyBitLength) {
      super(id, jcaTransformation);
      assertKeyBitLength(keyBitLength);
      this.keyBitLength = keyBitLength;
      this.gcm = jcaTransformation.startsWith("AES/GCM");
      this.ivBitLength = jcaTransformation.equals("AESWrap") ? 0 : (this.gcm ? 96 : 128);
      this.tagBitLength = this.gcm ? 128 : this.keyBitLength;
   }

   public int getKeyBitLength() {
      return this.keyBitLength;
   }

   public SecretKeyBuilder key() {
      return new DefaultSecretKeyBuilder("AES", this.getKeyBitLength());
   }

   protected SecretKey assertKey(SecretKey key) {
      Assert.notNull(key, "Request key cannot be null.");
      this.validateLengthIfPossible(key);
      return key;
   }

   private void validateLengthIfPossible(SecretKey key) {
      this.validateLength(key, this.keyBitLength, false);
   }

   protected static String lengthMsg(String id, String type, int requiredLengthInBits, long actualLengthInBits) {
      return "The '" + id + "' algorithm requires " + type + " with a length of " + Bytes.bitsMsg((long)requiredLengthInBits) + ".  The provided key has a length of " + Bytes.bitsMsg(actualLengthInBits) + ".";
   }

   protected byte[] validateLength(SecretKey key, int requiredBitLength, boolean propagate) {
      byte[] keyBytes;
      try {
         keyBytes = key.getEncoded();
      } catch (RuntimeException re) {
         if (propagate) {
            throw re;
         }

         return null;
      }

      long keyBitLength = Bytes.bitLength(keyBytes);
      if (keyBitLength < (long)requiredBitLength) {
         throw new WeakKeyException(lengthMsg(this.getId(), "keys", requiredBitLength, keyBitLength));
      } else {
         return keyBytes;
      }
   }

   protected byte[] assertBytes(byte[] bytes, String type, int requiredBitLen) {
      long bitLen = Bytes.bitLength(bytes);
      if ((long)requiredBitLen != bitLen) {
         String msg = lengthMsg(this.getId(), type, requiredBitLen, bitLen);
         throw new IllegalArgumentException(msg);
      } else {
         return bytes;
      }
   }

   byte[] assertIvLength(byte[] iv) {
      return this.assertBytes(iv, "initialization vectors", this.ivBitLength);
   }

   byte[] assertTag(byte[] tag) {
      return this.assertBytes(tag, "authentication tags", this.tagBitLength);
   }

   byte[] assertDecryptionIv(IvSupplier src) throws IllegalArgumentException {
      byte[] iv = src.getIv();
      Assert.notEmpty(iv, "This algorithm implementation rejects decryption requests that do not include initialization vectors. AES ciphertext without an IV is weak and susceptible to attack.");
      return this.assertIvLength(iv);
   }

   protected byte[] ensureInitializationVector(Request request) {
      byte[] iv = null;
      if (request instanceof IvSupplier) {
         iv = Arrays.clean(((IvSupplier)request).getIv());
      }

      int ivByteLength = this.ivBitLength / 8;
      if (iv != null && iv.length != 0) {
         this.assertIvLength(iv);
      } else {
         iv = new byte[ivByteLength];
         SecureRandom random = ensureSecureRandom(request);
         random.nextBytes(iv);
      }

      return iv;
   }

   protected AlgorithmParameterSpec getIvSpec(byte[] iv) {
      Assert.notEmpty(iv, "Initialization Vector byte array cannot be null or empty.");
      return (AlgorithmParameterSpec)(this.gcm ? new GCMParameterSpec(128, iv) : new IvParameterSpec(iv));
   }

   protected void withCipher(Cipher cipher, InputStream in, OutputStream out) throws Exception {
      byte[] last = this.withCipher(cipher, in, (InputStream)null, out);
      out.write(last);
   }

   private void updateAAD(Cipher cipher, InputStream aad) throws Exception {
      if (aad != null) {
         byte[] buf = new byte[2048];
         int len = 0;

         while(len != -1) {
            len = aad.read(buf);
            if (len > 0) {
               cipher.updateAAD(buf, 0, len);
            }
         }

      }
   }

   protected byte[] withCipher(Cipher cipher, InputStream in, InputStream aad, OutputStream out) throws Exception {
      this.updateAAD(cipher, aad);
      byte[] buf = new byte[2048];

      byte[] enc;
      try {
         int len = 0;

         while(len != -1) {
            len = in.read(buf);
            if (len > 0) {
               enc = cipher.update(buf, 0, len);
               Streams.write(out, enc, "Unable to write Cipher output to OutputStream");
            }
         }

         enc = cipher.doFinal();
      } finally {
         Bytes.clear(buf);
      }

      return enc;
   }
}
