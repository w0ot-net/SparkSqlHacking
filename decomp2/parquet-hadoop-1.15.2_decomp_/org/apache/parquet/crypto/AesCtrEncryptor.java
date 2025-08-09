package org.apache.parquet.crypto;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.format.BlockCipher;

public class AesCtrEncryptor extends AesCipher implements BlockCipher.Encryptor {
   private final byte[] ctrIV;
   private long operationCounter = 0L;

   AesCtrEncryptor(byte[] keyBytes) {
      super(AesMode.CTR, keyBytes);

      try {
         this.cipher = Cipher.getInstance(AesMode.CTR.getCipherName());
      } catch (GeneralSecurityException e) {
         throw new ParquetCryptoRuntimeException("Failed to create CTR cipher", e);
      }

      this.ctrIV = new byte[16];
      this.ctrIV[15] = 1;
   }

   public byte[] encrypt(byte[] plainText, byte[] AAD) {
      return this.encrypt(true, plainText, AAD);
   }

   public byte[] encrypt(boolean writeLength, byte[] plainText, byte[] AAD) {
      this.randomGenerator.nextBytes(this.localNonce);
      return this.encrypt(writeLength, plainText, this.localNonce, AAD);
   }

   public byte[] encrypt(boolean writeLength, byte[] plainText, byte[] nonce, byte[] AAD) {
      if (this.operationCounter > 4294967296L) {
         throw new ParquetCryptoRuntimeException("Exceeded limit of AES CTR encryption operations with same key and random IV");
      } else {
         ++this.operationCounter;
         if (nonce.length != 12) {
            throw new ParquetCryptoRuntimeException("Wrong nonce length " + nonce.length);
         } else {
            int plainTextLength = plainText.length;
            int cipherTextLength = 12 + plainTextLength;
            int lengthBufferLength = writeLength ? 4 : 0;
            byte[] cipherText = new byte[lengthBufferLength + cipherTextLength];
            int inputLength = plainTextLength;
            int inputOffset = 0;
            int outputOffset = lengthBufferLength + 12;

            try {
               System.arraycopy(nonce, 0, this.ctrIV, 0, 12);
               IvParameterSpec spec = new IvParameterSpec(this.ctrIV);
               this.cipher.init(1, this.aesKey, spec);

               while(inputLength > 4096) {
                  int written = this.cipher.update(plainText, inputOffset, 4096, cipherText, outputOffset);
                  inputOffset += 4096;
                  outputOffset += written;
                  inputLength -= 4096;
               }

               this.cipher.doFinal(plainText, inputOffset, inputLength, cipherText, outputOffset);
            } catch (GeneralSecurityException e) {
               throw new ParquetCryptoRuntimeException("Failed to encrypt", e);
            }

            if (writeLength) {
               System.arraycopy(BytesUtils.intToBytes(cipherTextLength), 0, cipherText, 0, lengthBufferLength);
            }

            System.arraycopy(nonce, 0, cipherText, lengthBufferLength, 12);
            return cipherText;
         }
      }
   }
}
