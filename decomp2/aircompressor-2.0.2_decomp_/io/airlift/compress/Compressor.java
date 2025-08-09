package io.airlift.compress;

import java.nio.ByteBuffer;

public interface Compressor {
   int maxCompressedLength(int uncompressedSize);

   int compress(byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset, int maxOutputLength);

   void compress(ByteBuffer input, ByteBuffer output);
}
