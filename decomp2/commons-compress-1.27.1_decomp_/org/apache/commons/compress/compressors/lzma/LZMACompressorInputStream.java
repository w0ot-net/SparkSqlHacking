package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.tukaani.xz.LZMAInputStream;
import org.tukaani.xz.MemoryLimitException;

public class LZMACompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
   private final BoundedInputStream countingStream;
   private final InputStream in;

   public static boolean matches(byte[] signature, int length) {
      return signature != null && length >= 3 && signature[0] == 93 && signature[1] == 0 && signature[2] == 0;
   }

   public LZMACompressorInputStream(InputStream inputStream) throws IOException {
      this.in = new LZMAInputStream(this.countingStream = ((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(inputStream)).get(), -1);
   }

   public LZMACompressorInputStream(InputStream inputStream, int memoryLimitInKb) throws IOException {
      try {
         this.in = new LZMAInputStream(this.countingStream = ((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(inputStream)).get(), memoryLimitInKb);
      } catch (MemoryLimitException e) {
         throw new org.apache.commons.compress.MemoryLimitException((long)e.getMemoryNeeded(), e.getMemoryLimit(), e);
      }
   }

   public int available() throws IOException {
      return this.in.available();
   }

   public void close() throws IOException {
      this.in.close();
   }

   public long getCompressedCount() {
      return this.countingStream.getCount();
   }

   public int read() throws IOException {
      int ret = this.in.read();
      this.count(ret == -1 ? 0 : 1);
      return ret;
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      int ret = this.in.read(buf, off, len);
      this.count(ret);
      return ret;
   }

   public long skip(long n) throws IOException {
      return IOUtils.skip(this.in, n);
   }
}
