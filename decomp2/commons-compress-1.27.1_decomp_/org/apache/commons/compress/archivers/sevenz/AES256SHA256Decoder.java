package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.compress.PasswordRequiredException;

final class AES256SHA256Decoder extends AbstractCoder {
   static byte[] sha256Password(byte[] password, int numCyclesPower, byte[] salt) {
      MessageDigest digest;
      try {
         digest = MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
         throw new IllegalStateException("SHA-256 is unsupported by your Java implementation", noSuchAlgorithmException);
      }

      byte[] extra = new byte[8];

      for(long j = 0L; j < 1L << numCyclesPower; ++j) {
         digest.update(salt);
         digest.update(password);
         digest.update(extra);

         for(int k = 0; k < extra.length; ++k) {
            ++extra[k];
            if (extra[k] != 0) {
               break;
            }
         }
      }

      return digest.digest();
   }

   static byte[] sha256Password(char[] password, int numCyclesPower, byte[] salt) {
      return sha256Password(utf16Decode(password), numCyclesPower, salt);
   }

   static byte[] utf16Decode(char[] chars) {
      if (chars == null) {
         return null;
      } else {
         ByteBuffer encoded = StandardCharsets.UTF_16LE.encode(CharBuffer.wrap(chars));
         if (encoded.hasArray()) {
            return encoded.array();
         } else {
            byte[] e = new byte[encoded.remaining()];
            encoded.get(e);
            return e;
         }
      }
   }

   AES256SHA256Decoder() {
      super(AES256Options.class);
   }

   InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] passwordBytes, int maxMemoryLimitInKb) {
      return new AES256SHA256DecoderInputStream(in, coder, archiveName, passwordBytes);
   }

   OutputStream encode(OutputStream out, Object options) throws IOException {
      return new AES256SHA256DecoderOutputStream((AES256Options)options, out);
   }

   byte[] getOptionsAsProperties(Object options) throws IOException {
      AES256Options opts = (AES256Options)options;
      byte[] props = new byte[2 + opts.getSalt().length + opts.getIv().length];
      props[0] = (byte)(opts.getNumCyclesPower() | (opts.getSalt().length == 0 ? 0 : 128) | (opts.getIv().length == 0 ? 0 : 64));
      if (opts.getSalt().length != 0 || opts.getIv().length != 0) {
         props[1] = (byte)((opts.getSalt().length == 0 ? 0 : opts.getSalt().length - 1) << 4 | (opts.getIv().length == 0 ? 0 : opts.getIv().length - 1));
         System.arraycopy(opts.getSalt(), 0, props, 2, opts.getSalt().length);
         System.arraycopy(opts.getIv(), 0, props, 2 + opts.getSalt().length, opts.getIv().length);
      }

      return props;
   }

   private static final class AES256SHA256DecoderInputStream extends InputStream {
      private final InputStream in;
      private final Coder coder;
      private final String archiveName;
      private final byte[] passwordBytes;
      private boolean isInitialized;
      private CipherInputStream cipherInputStream;

      private AES256SHA256DecoderInputStream(InputStream in, Coder coder, String archiveName, byte[] passwordBytes) {
         this.in = in;
         this.coder = coder;
         this.archiveName = archiveName;
         this.passwordBytes = passwordBytes;
      }

      public void close() throws IOException {
         if (this.cipherInputStream != null) {
            this.cipherInputStream.close();
         }

      }

      private CipherInputStream init() throws IOException {
         if (this.isInitialized) {
            return this.cipherInputStream;
         } else if (this.coder.properties == null) {
            throw new IOException("Missing AES256 properties in " + this.archiveName);
         } else if (this.coder.properties.length < 2) {
            throw new IOException("AES256 properties too short in " + this.archiveName);
         } else {
            int byte0 = 255 & this.coder.properties[0];
            int numCyclesPower = byte0 & 63;
            int byte1 = 255 & this.coder.properties[1];
            int ivSize = (byte0 >> 6 & 1) + (byte1 & 15);
            int saltSize = (byte0 >> 7 & 1) + (byte1 >> 4);
            if (2 + saltSize + ivSize > this.coder.properties.length) {
               throw new IOException("Salt size + IV size too long in " + this.archiveName);
            } else {
               byte[] salt = new byte[saltSize];
               System.arraycopy(this.coder.properties, 2, salt, 0, saltSize);
               byte[] iv = new byte[16];
               System.arraycopy(this.coder.properties, 2 + saltSize, iv, 0, ivSize);
               if (this.passwordBytes == null) {
                  throw new PasswordRequiredException(this.archiveName);
               } else {
                  byte[] aesKeyBytes;
                  if (numCyclesPower == 63) {
                     aesKeyBytes = new byte[32];
                     System.arraycopy(salt, 0, aesKeyBytes, 0, saltSize);
                     System.arraycopy(this.passwordBytes, 0, aesKeyBytes, saltSize, Math.min(this.passwordBytes.length, aesKeyBytes.length - saltSize));
                  } else {
                     aesKeyBytes = AES256SHA256Decoder.sha256Password(this.passwordBytes, numCyclesPower, salt);
                  }

                  SecretKey aesKey = AES256Options.newSecretKeySpec(aesKeyBytes);

                  try {
                     Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                     cipher.init(2, aesKey, new IvParameterSpec(iv));
                     this.cipherInputStream = new CipherInputStream(this.in, cipher);
                     this.isInitialized = true;
                     return this.cipherInputStream;
                  } catch (GeneralSecurityException generalSecurityException) {
                     throw new IllegalStateException("Decryption error (do you have the JCE Unlimited Strength Jurisdiction Policy Files installed?)", generalSecurityException);
                  }
               }
            }
         }
      }

      public int read() throws IOException {
         return this.init().read();
      }

      public int read(byte[] b, int off, int len) throws IOException {
         return this.init().read(b, off, len);
      }
   }

   private static final class AES256SHA256DecoderOutputStream extends OutputStream {
      private final CipherOutputStream cipherOutputStream;
      private final int cipherBlockSize;
      private final byte[] cipherBlockBuffer;
      private int count;

      private AES256SHA256DecoderOutputStream(AES256Options opts, OutputStream out) {
         this.cipherOutputStream = new CipherOutputStream(out, opts.getCipher());
         this.cipherBlockSize = opts.getCipher().getBlockSize();
         this.cipherBlockBuffer = new byte[this.cipherBlockSize];
      }

      public void close() throws IOException {
         if (this.count > 0) {
            this.cipherOutputStream.write(this.cipherBlockBuffer);
         }

         this.cipherOutputStream.close();
      }

      public void flush() throws IOException {
         this.cipherOutputStream.flush();
      }

      private void flushBuffer() throws IOException {
         this.cipherOutputStream.write(this.cipherBlockBuffer);
         this.count = 0;
         Arrays.fill(this.cipherBlockBuffer, (byte)0);
      }

      public void write(byte[] b, int off, int len) throws IOException {
         int gap = len + this.count > this.cipherBlockSize ? this.cipherBlockSize - this.count : len;
         System.arraycopy(b, off, this.cipherBlockBuffer, this.count, gap);
         this.count += gap;
         if (this.count == this.cipherBlockSize) {
            this.flushBuffer();
            if (len - gap >= this.cipherBlockSize) {
               int multipleCipherBlockSizeLen = (len - gap) / this.cipherBlockSize * this.cipherBlockSize;
               this.cipherOutputStream.write(b, off + gap, multipleCipherBlockSizeLen);
               gap += multipleCipherBlockSizeLen;
            }

            System.arraycopy(b, off + gap, this.cipherBlockBuffer, 0, len - gap);
            this.count = len - gap;
         }

      }

      public void write(int b) throws IOException {
         this.cipherBlockBuffer[this.count++] = (byte)b;
         if (this.count == this.cipherBlockSize) {
            this.flushBuffer();
         }

      }
   }
}
