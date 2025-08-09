package org.apache.parquet.crypto;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.format.BlockCipher;

public class AesGcmEncryptor extends AesCipher implements BlockCipher.Encryptor {
   private long operationCounter = 0L;

   AesGcmEncryptor(byte[] keyBytes) {
      super(AesMode.GCM, keyBytes);

      try {
         this.cipher = Cipher.getInstance(AesMode.GCM.getCipherName());
      } catch (GeneralSecurityException e) {
         throw new ParquetCryptoRuntimeException("Failed to create GCM cipher", e);
      }
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
         throw new ParquetCryptoRuntimeException("Exceeded limit of AES GCM encryption operations with same key and random IV");
      } else {
         ++this.operationCounter;
         if (nonce.length != 12) {
            throw new ParquetCryptoRuntimeException("Wrong nonce length " + nonce.length);
         } else {
            int plainTextLength = plainText.length;
            int cipherTextLength = 12 + plainTextLength + 16;
            int lengthBufferLength = writeLength ? 4 : 0;
            byte[] cipherText = new byte[lengthBufferLength + cipherTextLength];
            int inputLength = plainTextLength;
            int inputOffset = 0;
            int outputOffset = lengthBufferLength + 12;

            try {
               GCMParameterSpec spec = new GCMParameterSpec(128, nonce);
               this.cipher.init(1, this.aesKey, spec);
               if (null != AAD) {
                  this.cipher.updateAAD(AAD);
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
