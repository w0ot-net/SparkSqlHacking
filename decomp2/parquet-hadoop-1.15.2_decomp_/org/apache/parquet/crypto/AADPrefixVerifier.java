package org.apache.parquet.crypto;

public interface AADPrefixVerifier {
   void verify(byte[] var1) throws ParquetCryptoRuntimeException;
}
