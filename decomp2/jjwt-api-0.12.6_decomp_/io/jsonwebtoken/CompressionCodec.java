package io.jsonwebtoken;

import io.jsonwebtoken.io.CompressionAlgorithm;

/** @deprecated */
@Deprecated
public interface CompressionCodec extends CompressionAlgorithm {
   /** @deprecated */
   @Deprecated
   String getAlgorithmName();

   /** @deprecated */
   @Deprecated
   byte[] compress(byte[] var1) throws CompressionException;

   /** @deprecated */
   @Deprecated
   byte[] decompress(byte[] var1) throws CompressionException;
}
