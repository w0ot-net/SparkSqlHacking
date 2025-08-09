package org.apache.commons.compress.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/** @deprecated */
@Deprecated
public class CountingOutputStream extends FilterOutputStream {
   private long bytesWritten;

   public CountingOutputStream(OutputStream out) {
      super(out);
   }

   protected void count(long written) {
      if (written != -1L) {
         this.bytesWritten += written;
      }

   }

   public long getBytesWritten() {
      return this.bytesWritten;
   }

   public void write(byte[] b) throws IOException {
      this.write(b, 0, b.length);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.out.write(b, off, len);
      this.count((long)len);
   }

   public void write(int b) throws IOException {
      this.out.write(b);
      this.count(1L);
   }
}
