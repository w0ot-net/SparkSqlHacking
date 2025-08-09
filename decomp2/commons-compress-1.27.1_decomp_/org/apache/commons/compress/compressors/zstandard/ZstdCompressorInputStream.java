package org.apache.commons.compress.compressors.zstandard;

import com.github.luben.zstd.BufferPool;
import com.github.luben.zstd.ZstdInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;

public class ZstdCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
   private final BoundedInputStream countingStream;
   private final ZstdInputStream decIS;

   public ZstdCompressorInputStream(InputStream in) throws IOException {
      this.decIS = new ZstdInputStream(this.countingStream = ((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(in)).get());
   }

   public ZstdCompressorInputStream(InputStream in, BufferPool bufferPool) throws IOException {
      this.decIS = new ZstdInputStream(this.countingStream = ((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(in)).get(), bufferPool);
   }

   public int available() throws IOException {
      return this.decIS.available();
   }

   public void close() throws IOException {
      this.decIS.close();
   }

   public long getCompressedCount() {
      return this.countingStream.getCount();
   }

   public synchronized void mark(int readLimit) {
      this.decIS.mark(readLimit);
   }

   public boolean markSupported() {
      return this.decIS.markSupported();
   }

   public int read() throws IOException {
      int ret = this.decIS.read();
      this.count(ret == -1 ? 0 : 1);
      return ret;
   }

   public int read(byte[] b) throws IOException {
      return this.read(b, 0, b.length);
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      if (len == 0) {
         return 0;
      } else {
         int ret = this.decIS.read(buf, off, len);
         this.count(ret);
         return ret;
      }
   }

   public synchronized void reset() throws IOException {
      this.decIS.reset();
   }

   public long skip(long n) throws IOException {
      return IOUtils.skip(this.decIS, n);
   }

   public String toString() {
      return this.decIS.toString();
   }
}
