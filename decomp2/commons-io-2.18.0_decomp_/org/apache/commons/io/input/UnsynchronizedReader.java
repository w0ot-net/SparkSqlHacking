package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;

public abstract class UnsynchronizedReader extends Reader {
   private static final int MAX_SKIP_BUFFER_SIZE = 8192;
   private boolean closed;
   private char[] skipBuffer;

   void checkOpen() throws IOException {
      Input.checkOpen(!this.isClosed());
   }

   public void close() throws IOException {
      this.closed = true;
   }

   public boolean isClosed() {
      return this.closed;
   }

   public void setClosed(boolean closed) {
      this.closed = closed;
   }

   public long skip(long n) throws IOException {
      if (n < 0L) {
         throw new IllegalArgumentException("skip value < 0");
      } else {
         int bufSize = (int)Math.min(n, 8192L);
         if (this.skipBuffer == null || this.skipBuffer.length < bufSize) {
            this.skipBuffer = new char[bufSize];
         }

         long remaining;
         int countOrEof;
         for(remaining = n; remaining > 0L; remaining -= (long)countOrEof) {
            countOrEof = this.read(this.skipBuffer, 0, (int)Math.min(remaining, (long)bufSize));
            if (countOrEof == -1) {
               break;
            }
         }

         return n - remaining;
      }
   }
}
