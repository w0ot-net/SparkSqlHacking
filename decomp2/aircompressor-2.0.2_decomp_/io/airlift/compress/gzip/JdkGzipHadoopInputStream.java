package io.airlift.compress.gzip;

import io.airlift.compress.hadoop.HadoopInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

class JdkGzipHadoopInputStream extends HadoopInputStream {
   private final byte[] oneByte = new byte[1];
   private final GZIPInputStream input;

   public JdkGzipHadoopInputStream(InputStream input, int bufferSize) throws IOException {
      this.input = new GZIPInputStream(new GzipBufferedInputStream(input, bufferSize), bufferSize);
   }

   public int read() throws IOException {
      int length = this.input.read(this.oneByte, 0, 1);
      return length < 0 ? length : this.oneByte[0] & 255;
   }

   public int read(byte[] output, int offset, int length) throws IOException {
      return this.input.read(output, offset, length);
   }

   public void resetState() {
      throw new UnsupportedOperationException("resetState not supported for gzip");
   }

   public void close() throws IOException {
      this.input.close();
   }

   private static class GzipBufferedInputStream extends BufferedInputStream {
      public GzipBufferedInputStream(InputStream input, int bufferSize) {
         super((InputStream)Objects.requireNonNull(input, "input is null"), bufferSize);
      }

      public int available() throws IOException {
         return Math.max(1, super.available());
      }
   }
}
