package org.apache.parquet.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import org.apache.parquet.format.BlockCipher;

public class AesCtrDecryptor extends AesCipher implements BlockCipher.Decryptor {
   private final byte[] ctrIV;

   AesCtrDecryptor(byte[] keyBytes) {
      super(AesMode.CTR, keyBytes);

      try {
         this.cipher = Cipher.getInstance(AesMode.CTR.getCipherName());
      } catch (GeneralSecurityException e) {
         throw new ParquetCryptoRuntimeException("Failed to create CTR cipher", e);
      }

      this.ctrIV = new byte[16];
      this.ctrIV[15] = 1;
   }

   public byte[] decrypt(byte[] lengthAndCiphertext, byte[] AAD) {
      int cipherTextOffset = 4;
      int cipherTextLength = lengthAndCiphertext.length - 4;
      return this.decrypt(lengthAndCiphertext, cipherTextOffset, cipherTextLength, AAD);
   }

   public byte[] decrypt(byte[] ciphertext, int cipherTextOffset, int cipherTextLength, byte[] AAD) {
      int plainTextLength = cipherTextLength - 12;
      if (plainTextLength < 1) {
         throw new ParquetCryptoRuntimeException("Wrong input length " + plainTextLength);
      } else {
         System.arraycopy(ciphertext, cipherTextOffset, this.ctrIV, 0, 12);
         byte[] plainText = new byte[plainTextLength];
         int inputLength = cipherTextLength - 12;
         int inputOffset = cipherTextOffset + 12;
         int outputOffset = 0;

         try {
            IvParameterSpec spec = new IvParameterSpec(this.ctrIV);
            this.cipher.init(2, this.aesKey, spec);

            while(inputLength > 4096) {
               int written = this.cipher.update(ciphertext, inputOffset, 4096, plainText, outputOffset);
               inputOffset += 4096;
               outputOffset += written;
               inputLength -= 4096;
            }

            this.cipher.doFinal(ciphertext, inputOffset, inputLength, plainText, outputOffset);
            return plainText;
         } catch (GeneralSecurityException e) {
            throw new ParquetCryptoRuntimeException("Failed to decrypt", e);
         }
      }
   }

   public ByteBuffer decrypt(ByteBuffer ciphertext, byte[] AAD) {
      int cipherTextOffset = 4;
      int cipherTextLength = ciphertext.limit() - ciphertext.position() - 4;
      int plainTextLength = cipherTextLength - 12;
      if (plainTextLength < 1) {
         throw new ParquetCryptoRuntimeException("Wrong input length " + plainTextLength);
      } else {
         ciphertext.position(ciphertext.position() + cipherTextOffset);
         ciphertext.get(this.ctrIV, 0, 12);
         ByteBuffer plainText = ciphertext.slice();
         plainText.limit(plainTextLength);
         int inputLength = cipherTextLength - 12;
         int inputOffset = cipherTextOffset + 12;

         try {
            IvParameterSpec spec = new IvParameterSpec(this.ctrIV);
            this.cipher.init(2, this.aesKey, spec);

            while(inputLength > 4096) {
               ciphertext.position(inputOffset);
               ciphertext.limit(inputOffset + 4096);
               this.cipher.update(ciphertext, plainText);
               inputOffset += 4096;
               inputLength -= 4096;
            }

            ciphertext.position(inputOffset);
            ciphertext.limit(inputOffset + inputLength);
            this.cipher.doFinal(ciphertext, plainText);
            plainText.flip();
            return plainText;
         } catch (GeneralSecurityException e) {
            throw new ParquetCryptoRuntimeException("Failed to decrypt", e);
         }
      }
   }

   public byte[] decrypt(InputStream from, byte[] AAD) throws IOException {
      byte[] lengthBuffer = new byte[4];

      int n;
      for(int gotBytes = 0; gotBytes < 4; gotBytes += n) {
         n = from.read(lengthBuffer, gotBytes, 4 - gotBytes);
         if (n <= 0) {
            throw new IOException("Tried to read int (4 bytes), but only got " + gotBytes + " bytes.");
         }
      }

      n = (lengthBuffer[3] & 255) << 24 | (lengthBuffer[2] & 255) << 16 | (lengthBuffer[1] & 255) << 8 | lengthBuffer[0] & 255;
      if (n < 1) {
         throw new IOException("Wrong length of encrypted metadata: " + n);
      } else {
         byte[] ciphertextBuffer = new byte[n];

         int n;
         for(int var8 = 0; var8 < n; var8 += n) {
            n = from.read(ciphertextBuffer, var8, n - var8);
            if (n <= 0) {
               throw new IOException("Tried to read " + n + " bytes, but only got " + var8 + " bytes.");
            }
         }

         return this.decrypt(ciphertextBuffer, 0, n, AAD);
      }
   }
}
