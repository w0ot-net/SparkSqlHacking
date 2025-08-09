package org.apache.parquet.hadoop.codec;

import io.airlift.compress.lz4.Lz4Decompressor;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.hadoop.io.compress.DirectDecompressor;

public class Lz4RawDecompressor extends NonBlockedDecompressor implements DirectDecompressor {
   private Lz4Decompressor decompressor = new Lz4Decompressor();

   protected int maxUncompressedLength(ByteBuffer compressed, int maxUncompressedLength) throws IOException {
      return maxUncompressedLength;
   }

   protected int uncompress(ByteBuffer compressed, ByteBuffer uncompressed) throws IOException {
      this.decompressor.decompress(compressed, uncompressed);
      int uncompressedSize = uncompressed.position();
      uncompressed.limit(uncompressedSize);
      uncompressed.rewind();
      return uncompressedSize;
   }

   public void decompress(ByteBuffer compressed, ByteBuffer uncompressed) throws IOException {
      this.uncompress(compressed, uncompressed);
   }
}
