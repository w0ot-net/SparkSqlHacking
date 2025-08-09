package io.airlift.compress.zstd;

import io.airlift.compress.hadoop.HadoopInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class ZstdHadoopInputStream extends HadoopInputStream {
   private final InputStream in;
   private ZstdInputStream zstdInputStream;

   public ZstdHadoopInputStream(InputStream in) {
      this.in = (InputStream)Objects.requireNonNull(in, "in is null");
      this.zstdInputStream = new ZstdInputStream(in);
   }

   public int read() throws IOException {
      return this.zstdInputStream.read();
   }

   public int read(byte[] b) throws IOException {
      return this.zstdInputStream.read(b);
   }

   public int read(byte[] outputBuffer, int outputOffset, int outputLength) throws IOException {
      return this.zstdInputStream.read(outputBuffer, outputOffset, outputLength);
   }

   public void resetState() {
      this.zstdInputStream = new ZstdInputStream(this.in);
   }

   public void close() throws IOException {
      this.zstdInputStream.close();
   }
}
