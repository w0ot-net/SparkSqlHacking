package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.simple.SimpleFilter;

class SimpleInputStream extends InputStream {
   private static final int FILTER_BUF_SIZE = 4096;
   private InputStream in;
   private final SimpleFilter simpleFilter;
   private final byte[] filterBuf = new byte[4096];
   private int pos = 0;
   private int filtered = 0;
   private int unfiltered = 0;
   private boolean endReached = false;
   private IOException exception = null;
   private final byte[] tempBuf = new byte[1];

   static int getMemoryUsage() {
      return 5;
   }

   SimpleInputStream(InputStream in, SimpleFilter simpleFilter) {
      if (in == null) {
         throw new NullPointerException();
      } else {
         assert simpleFilter != null;

         this.in = in;
         this.simpleFilter = simpleFilter;
      }
   }

   public int read() throws IOException {
      return this.read(this.tempBuf, 0, 1) == -1 ? -1 : this.tempBuf[0] & 255;
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len >= 0 && off + len <= buf.length) {
         if (len == 0) {
            return 0;
         } else if (this.in == null) {
            throw new XZIOException("Stream closed");
         } else if (this.exception != null) {
            throw this.exception;
         } else {
            try {
               int size = 0;

               while(true) {
                  int copySize = Math.min(this.filtered, len);
                  System.arraycopy(this.filterBuf, this.pos, buf, off, copySize);
                  this.pos += copySize;
                  this.filtered -= copySize;
                  off += copySize;
                  len -= copySize;
                  size += copySize;
                  if (this.pos + this.filtered + this.unfiltered == 4096) {
                     System.arraycopy(this.filterBuf, this.pos, this.filterBuf, 0, this.filtered + this.unfiltered);
                     this.pos = 0;
                  }

                  if (len == 0 || this.endReached) {
                     return size > 0 ? size : -1;
                  }

                  assert this.filtered == 0;

                  int inSize = 4096 - (this.pos + this.filtered + this.unfiltered);
                  inSize = this.in.read(this.filterBuf, this.pos + this.filtered + this.unfiltered, inSize);
                  if (inSize == -1) {
                     this.endReached = true;
                     this.filtered = this.unfiltered;
                     this.unfiltered = 0;
                  } else {
                     this.unfiltered += inSize;
                     this.filtered = this.simpleFilter.code(this.filterBuf, this.pos, this.unfiltered);

                     assert this.filtered <= this.unfiltered;

                     this.unfiltered -= this.filtered;
                  }
               }
            } catch (IOException e) {
               this.exception = e;
               throw e;
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public int available() throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (this.exception != null) {
         throw this.exception;
      } else {
         return this.filtered;
      }
   }

   public void close() throws IOException {
      if (this.in != null) {
         try {
            this.in.close();
         } finally {
            this.in = null;
         }
      }

   }
}
