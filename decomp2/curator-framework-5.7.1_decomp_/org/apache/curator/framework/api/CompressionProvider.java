package org.apache.curator.framework.api;

public interface CompressionProvider {
   byte[] compress(String var1, byte[] var2) throws Exception;

   byte[] decompress(String var1, byte[] var2) throws Exception;
}
