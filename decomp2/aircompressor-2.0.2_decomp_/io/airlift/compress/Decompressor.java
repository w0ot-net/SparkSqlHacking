package io.airlift.compress;

import java.nio.ByteBuffer;

public interface Decompressor {
   int decompress(byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset, int maxOutputLength) throws MalformedInputException;

   void decompress(ByteBuffer input, ByteBuffer output) throws MalformedInputException;
}
