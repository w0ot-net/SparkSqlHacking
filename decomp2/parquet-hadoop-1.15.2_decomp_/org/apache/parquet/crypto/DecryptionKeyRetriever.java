package org.apache.parquet.crypto;

public interface DecryptionKeyRetriever {
   byte[] getKey(byte[] var1) throws KeyAccessDeniedException, ParquetCryptoRuntimeException;
}
