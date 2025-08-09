package org.apache.parquet.hadoop.codec;

import com.github.luben.zstd.BufferPool;
import com.github.luben.zstd.ZstdInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.hadoop.io.compress.CompressionInputStream;

public class ZstdDecompressorStream extends CompressionInputStream {
   private ZstdInputStream zstdInputStream;

   public ZstdDecompressorStream(InputStream stream) throws IOException {
      super(stream);
      this.zstdInputStream = new ZstdInputStream(stream);
   }

   public ZstdDecompressorStream(InputStream stream, BufferPool pool) throws IOException {
      super(stream);
      this.zstdInputStream = new ZstdInputStream(stream, pool);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.zstdInputStream.read(b, off, len);
   }

   public int read() throws IOException {
      return this.zstdInputStream.read();
   }

   public void resetState() throws IOException {
   }

   public void close() throws IOException {
      try {
         this.zstdInputStream.close();
      } finally {
         super.close();
      }

   }
}
