package org.apache.commons.compress.compressors;

import java.io.InputStream;

public abstract class CompressorInputStream extends InputStream {
   private long bytesRead;

   protected void count(int read) {
      this.count((long)read);
   }

   protected void count(long read) {
      if (read != -1L) {
         this.bytesRead += read;
      }

   }

   public long getBytesRead() {
      return this.bytesRead;
   }

   /** @deprecated */
   @Deprecated
   public int getCount() {
      return (int)this.bytesRead;
   }

   public long getUncompressedCount() {
      return this.getBytesRead();
   }

   protected void pushedBackBytes(long pushedBack) {
      this.bytesRead -= pushedBack;
   }
}
