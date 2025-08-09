package org.apache.commons.compress.compressors.brotli;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.brotli.dec.BrotliInputStream;

public class BrotliCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
   private final BoundedInputStream countingInputStream;
   private final BrotliInputStream brotliInputStream;

   public BrotliCompressorInputStream(InputStream inputStream) throws IOException {
      this.brotliInputStream = new BrotliInputStream(this.countingInputStream = ((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(inputStream)).get());
   }

   public int available() throws IOException {
      return this.brotliInputStream.available();
   }

   public void close() throws IOException {
      this.brotliInputStream.close();
   }

   public long getCompressedCount() {
      return this.countingInputStream.getCount();
   }

   public synchronized void mark(int readLimit) {
      this.brotliInputStream.mark(readLimit);
   }

   public boolean markSupported() {
      return this.brotliInputStream.markSupported();
   }

   public int read() throws IOException {
      int ret = this.brotliInputStream.read();
      this.count(ret == -1 ? 0 : 1);
      return ret;
   }

   public int read(byte[] b) throws IOException {
      return this.brotliInputStream.read(b);
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      int ret = this.brotliInputStream.read(buf, off, len);
      this.count(ret);
      return ret;
   }

   public synchronized void reset() throws IOException {
      this.brotliInputStream.reset();
   }

   public long skip(long n) throws IOException {
      return IOUtils.skip(this.brotliInputStream, n);
   }

   public String toString() {
      return this.brotliInputStream.toString();
   }
}
