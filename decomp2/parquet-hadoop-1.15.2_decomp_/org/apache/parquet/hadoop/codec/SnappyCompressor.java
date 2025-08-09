package org.apache.parquet.hadoop.codec;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.xerial.snappy.Snappy;

public class SnappyCompressor extends NonBlockedCompressor {
   protected int maxCompressedLength(int byteSize) {
      return Snappy.maxCompressedLength(byteSize);
   }

   protected int compress(ByteBuffer uncompressed, ByteBuffer compressed) throws IOException {
      return Snappy.compress(uncompressed, compressed);
   }
}
