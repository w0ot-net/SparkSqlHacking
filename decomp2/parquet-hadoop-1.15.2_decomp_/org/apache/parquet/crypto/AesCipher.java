package org.apache.parquet.crypto;

import java.security.SecureRandom;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesCipher {
   public static final int NONCE_LENGTH = 12;
   public static final int GCM_TAG_LENGTH = 16;
   static final int AAD_FILE_UNIQUE_LENGTH = 8;
   protected static final int CTR_IV_LENGTH = 16;
   protected static final int GCM_TAG_LENGTH_BITS = 128;
   protected static final int CHUNK_LENGTH = 4096;
   protected static final int SIZE_LENGTH = 4;
   protected static final long GCM_RANDOM_IV_SAME_KEY_MAX_OPS = 4294967296L;
   protected static final long CTR_RANDOM_IV_SAME_KEY_MAX_OPS = 4294967296L;
   protected SecretKeySpec aesKey;
   protected final SecureRandom randomGenerator;
   protected Cipher cipher;
   protected final byte[] localNonce;

   AesCipher(AesMode mode, byte[] keyBytes) {
      if (null == keyBytes) {
         throw new IllegalArgumentException("Null key bytes");
      } else {
         boolean allZeroKey = true;

         for(byte kb : keyBytes) {
            if (kb != 0) {
               allZeroKey = false;
               break;
            }
         }

         if (allZeroKey) {
            throw new IllegalArgumentException("All key bytes are zero");
         } else {
            this.aesKey = new SecretKeySpec(keyBytes, "AES");
            this.randomGenerator = new SecureRandom();
            this.localNonce = new byte[12];
         }
      }
   }

   public static byte[] createModuleAAD(byte[] fileAAD, ModuleCipherFactory.ModuleType moduleType, int rowGroupOrdinal, int columnOrdinal, int pageOrdinal) {
      byte[] typeOrdinalBytes = new byte[1];
      typeOrdinalBytes[0] = moduleType.getValue();
      if (ModuleCipherFactory.ModuleType.Footer == moduleType) {
         return concatByteArrays(fileAAD, typeOrdinalBytes);
      } else if (rowGroupOrdinal < 0) {
         throw new IllegalArgumentException("Wrong row group ordinal: " + rowGroupOrdinal);
      } else {
         short shortRGOrdinal = (short)rowGroupOrdinal;
         if (shortRGOrdinal != rowGroupOrdinal) {
            throw new ParquetCryptoRuntimeException("Encrypted parquet files can't have more than 32767 row groups: " + rowGroupOrdinal);
         } else {
            byte[] rowGroupOrdinalBytes = shortToBytesLE(shortRGOrdinal);
            if (columnOrdinal < 0) {
               throw new IllegalArgumentException("Wrong column ordinal: " + columnOrdinal);
            } else {
               short shortColumOrdinal = (short)columnOrdinal;
               if (shortColumOrdinal != columnOrdinal) {
                  throw new ParquetCryptoRuntimeException("Encrypted parquet files can't have more than 32767 columns: " + columnOrdinal);
               } else {
                  byte[] columnOrdinalBytes = shortToBytesLE(shortColumOrdinal);
                  if (ModuleCipherFactory.ModuleType.DataPage != moduleType && ModuleCipherFactory.ModuleType.DataPageHeader != moduleType) {
                     return concatByteArrays(fileAAD, typeOrdinalBytes, rowGroupOrdinalBytes, columnOrdinalBytes);
                  } else if (pageOrdinal < 0) {
                     throw new IllegalArgumentException("Wrong page ordinal: " + pageOrdinal);
                  } else {
                     short shortPageOrdinal = (short)pageOrdinal;
                     if (shortPageOrdinal != pageOrdinal) {
                        throw new ParquetCryptoRuntimeException("Encrypted parquet files can't have more than 32767 pages per chunk: " + pageOrdinal);
                     } else {
                        byte[] pageOrdinalBytes = shortToBytesLE(shortPageOrdinal);
                        return concatByteArrays(fileAAD, typeOrdinalBytes, rowGroupOrdinalBytes, columnOrdinalBytes, pageOrdinalBytes);
                     }
                  }
               }
            }
         }
      }
   }

   public static byte[] createFooterAAD(byte[] aadPrefixBytes) {
      return createModuleAAD(aadPrefixBytes, ModuleCipherFactory.ModuleType.Footer, -1, -1, -1);
   }

   public static void quickUpdatePageAAD(byte[] pageAAD, int newPageOrdinal) {
      Objects.requireNonNull(pageAAD);
      if (newPageOrdinal < 0) {
         throw new IllegalArgumentException("Wrong page ordinal: " + newPageOrdinal);
      } else {
         short shortPageOrdinal = (short)newPageOrdinal;
         if (shortPageOrdinal != newPageOrdinal) {
            throw new ParquetCryptoRuntimeException("Encrypted parquet files can't have more than 32767 pages per chunk: " + newPageOrdinal);
         } else {
            byte[] pageOrdinalBytes = shortToBytesLE(shortPageOrdinal);
            System.arraycopy(pageOrdinalBytes, 0, pageAAD, pageAAD.length - 2, 2);
         }
      }
   }

   static byte[] concatByteArrays(byte[]... arrays) {
      int totalLength = 0;

      for(byte[] array : arrays) {
         totalLength += array.length;
      }

      byte[] output = new byte[totalLength];
      int offset = 0;

      for(byte[] array : arrays) {
         System.arraycopy(array, 0, output, offset, array.length);
         offset += array.length;
      }

      return output;
   }

   private static byte[] shortToBytesLE(short input) {
      byte[] output = new byte[2];
      output[1] = (byte)(255 & input >> 8);
      output[0] = (byte)(255 & input);
      return output;
   }
}
