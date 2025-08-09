package org.apache.avro.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ReusableByteBufferInputStream extends InputStream {
   private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);
   private ByteBuffer byteBuffer;
   private Buffer buffer;
   private int mark;

   public ReusableByteBufferInputStream() {
      this.byteBuffer = EMPTY_BUFFER;
      this.buffer = this.byteBuffer;
      this.mark = 0;
   }

   public void setByteBuffer(ByteBuffer buf) {
      this.byteBuffer = buf.duplicate();
      this.buffer = this.byteBuffer;
      this.mark = buf.position();
   }

   public int read() throws IOException {
      return this.buffer.hasRemaining() ? this.byteBuffer.get() & 255 : -1;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (this.buffer.remaining() <= 0) {
         return -1;
      } else {
         int bytesToRead = Math.min(len, this.buffer.remaining());
         this.byteBuffer.get(b, off, bytesToRead);
         return bytesToRead;
      }
   }

   public long skip(long n) throws IOException {
      if (n <= 0L) {
         return 0L;
      } else {
         int bytesToSkip = n > (long)this.buffer.remaining() ? this.buffer.remaining() : (int)n;
         this.buffer.position(this.buffer.position() + bytesToSkip);
         return (long)bytesToSkip;
      }
   }

   public synchronized void mark(int readLimit) {
      this.mark = this.buffer.position();
   }

   public synchronized void reset() throws IOException {
      this.buffer.position(this.mark);
   }

   public boolean markSupported() {
      return true;
   }
}
