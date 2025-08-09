package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;

public class NullInputStream extends AbstractInputStream {
   /** @deprecated */
   @Deprecated
   public static final NullInputStream INSTANCE = new NullInputStream();
   private final long size;
   private long position;
   private long mark;
   private long readLimit;
   private final boolean throwEofException;
   private final boolean markSupported;

   public NullInputStream() {
      this(0L, true, false);
   }

   public NullInputStream(long size) {
      this(size, true, false);
   }

   public NullInputStream(long size, boolean markSupported, boolean throwEofException) {
      this.mark = -1L;
      this.size = size;
      this.markSupported = markSupported;
      this.throwEofException = throwEofException;
   }

   public int available() {
      if (this.isClosed()) {
         return 0;
      } else {
         long avail = this.size - this.position;
         if (avail <= 0L) {
            return 0;
         } else {
            return avail > 2147483647L ? Integer.MAX_VALUE : (int)avail;
         }
      }
   }

   private void checkThrowEof(String message) throws EOFException {
      if (this.throwEofException) {
         throw new EOFException(message);
      }
   }

   public void close() throws IOException {
      super.close();
      this.mark = -1L;
   }

   public long getPosition() {
      return this.position;
   }

   public long getSize() {
      return this.size;
   }

   private int handleEof() throws IOException {
      this.checkThrowEof("handleEof()");
      return -1;
   }

   public NullInputStream init() {
      this.setClosed(false);
      this.position = 0L;
      this.mark = -1L;
      this.readLimit = 0L;
      return this;
   }

   public synchronized void mark(int readLimit) {
      if (!this.markSupported) {
         throw UnsupportedOperationExceptions.mark();
      } else {
         this.mark = this.position;
         this.readLimit = (long)readLimit;
      }
   }

   public boolean markSupported() {
      return this.markSupported;
   }

   protected int processByte() {
      return 0;
   }

   protected void processBytes(byte[] bytes, int offset, int length) {
   }

   public int read() throws IOException {
      this.checkOpen();
      if (this.position == this.size) {
         return this.handleEof();
      } else {
         ++this.position;
         return this.processByte();
      }
   }

   public int read(byte[] bytes) throws IOException {
      return this.read(bytes, 0, bytes.length);
   }

   public int read(byte[] bytes, int offset, int length) throws IOException {
      if (bytes.length != 0 && length != 0) {
         this.checkOpen();
         if (this.position == this.size) {
            return this.handleEof();
         } else {
            this.position += (long)length;
            int returnLength = length;
            if (this.position > this.size) {
               returnLength = length - (int)(this.position - this.size);
               this.position = this.size;
            }

            this.processBytes(bytes, offset, returnLength);
            return returnLength;
         }
      } else {
         return 0;
      }
   }

   public synchronized void reset() throws IOException {
      if (!this.markSupported) {
         throw UnsupportedOperationExceptions.reset();
      } else if (this.mark < 0L) {
         throw new IOException("No position has been marked");
      } else if (this.position > this.mark + this.readLimit) {
         throw new IOException("Marked position [" + this.mark + "] is no longer valid - passed the read limit [" + this.readLimit + "]");
      } else {
         this.position = this.mark;
         this.setClosed(false);
      }
   }

   public long skip(long numberOfBytes) throws IOException {
      if (this.isClosed()) {
         this.checkThrowEof("skip(long)");
         return -1L;
      } else if (this.position == this.size) {
         return (long)this.handleEof();
      } else {
         this.position += numberOfBytes;
         long returnLength = numberOfBytes;
         if (this.position > this.size) {
            returnLength = numberOfBytes - (this.position - this.size);
            this.position = this.size;
         }

         return returnLength;
      }
   }
}
