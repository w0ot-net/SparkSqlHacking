package org.apache.parquet.hadoop.codec;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.xerial.snappy.Snappy;

public class SnappyDecompressor extends NonBlockedDecompressor {
   protected int uncompress(ByteBuffer compressed, ByteBuffer uncompressed) throws IOException {
      return Snappy.uncompress(compressed, uncompressed);
   }

   protected int maxUncompressedLength(ByteBuffer compressed, int maxUncompressedLength) throws IOException {
      return Snappy.uncompressedLength(compressed);
   }
}
