package io.airlift.compress.zstd;

interface BlockCompressor {
   BlockCompressor UNSUPPORTED = (inputBase, inputAddress, inputSize, sequenceStore, blockCompressionState, offsets, parameters) -> {
      throw new UnsupportedOperationException();
   };

   int compressBlock(Object inputBase, long inputAddress, int inputSize, SequenceStore output, BlockCompressionState state, RepeatedOffsets offsets, CompressionParameters parameters);
}
