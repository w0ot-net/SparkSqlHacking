package org.apache.parquet.hadoop.codec;

import io.airlift.compress.lz4.Lz4Compressor;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Lz4RawCompressor extends NonBlockedCompressor {
   private Lz4Compressor compressor = new Lz4Compressor();

   protected int maxCompressedLength(int byteSize) {
      return io.airlift.compress.lz4.Lz4RawCompressor.maxCompressedLength(byteSize);
   }

   protected int compress(ByteBuffer uncompressed, ByteBuffer compressed) throws IOException {
      this.compressor.compress(uncompressed, compressed);
      int compressedSize = compressed.position();
      compressed.limit(compressedSize);
      compressed.rewind();
      return compressedSize;
   }
}
