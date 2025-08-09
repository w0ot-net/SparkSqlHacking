package org.apache.commons.crypto.stream.input;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class ChannelInput implements Input {
   private static final int SKIP_BUFFER_SIZE = 2048;
   private ByteBuffer buf;
   private final ReadableByteChannel channel;

   public ChannelInput(ReadableByteChannel channel) {
      this.channel = channel;
   }

   public int read(ByteBuffer dst) throws IOException {
      return this.channel.read(dst);
   }

   public long skip(long n) throws IOException {
      long remaining = n;
      if (n <= 0L) {
         return 0L;
      } else {
         int size = (int)Math.min(2048L, n);

         int nr;
         for(ByteBuffer skipBuffer = this.getSkipBuf(); remaining > 0L; remaining -= (long)nr) {
            skipBuffer.clear();
            skipBuffer.limit((int)Math.min((long)size, remaining));
            nr = this.read(skipBuffer);
            if (nr < 0) {
               break;
            }
         }

         return n - remaining;
      }
   }

   public int available() throws IOException {
      return 0;
   }

   public int read(long position, byte[] buffer, int offset, int length) throws IOException {
      throw new UnsupportedOperationException("Positioned read is not supported by this implementation");
   }

   public void seek(long position) throws IOException {
      throw new UnsupportedOperationException("Seek is not supported by this implementation");
   }

   public void close() throws IOException {
      this.channel.close();
   }

   private ByteBuffer getSkipBuf() {
      if (this.buf == null) {
         this.buf = ByteBuffer.allocate(2048);
      }

      return this.buf;
   }
}
