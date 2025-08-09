package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import org.apache.arrow.memory.ArrowBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadChannel implements AutoCloseable {
   private static final Logger LOGGER = LoggerFactory.getLogger(ReadChannel.class);
   private ReadableByteChannel in;
   private long bytesRead = 0L;

   public ReadChannel(ReadableByteChannel in) {
      this.in = in;
   }

   public long bytesRead() {
      return this.bytesRead;
   }

   public int readFully(ByteBuffer buffer) throws IOException {
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("Reading buffer with size: {}", buffer.remaining());
      }

      int totalRead = 0;

      while(buffer.remaining() != 0) {
         int read = this.in.read(buffer);
         if (read == -1) {
            this.bytesRead += (long)totalRead;
            return totalRead;
         }

         totalRead += read;
         if (read == 0) {
            break;
         }
      }

      this.bytesRead += (long)totalRead;
      return totalRead;
   }

   public long readFully(ArrowBuf buffer, long length) throws IOException {
      boolean fullRead = true;

      long bytesLeft;
      int n;
      for(bytesLeft = length; fullRead && bytesLeft > 0L; bytesLeft -= (long)n) {
         int bytesToRead = (int)Math.min(bytesLeft, 2147483647L);
         n = this.readFully(buffer.nioBuffer(buffer.writerIndex(), bytesToRead));
         buffer.writerIndex(buffer.writerIndex() + (long)n);
         fullRead = n == bytesToRead;
      }

      return length - bytesLeft;
   }

   public void close() throws IOException {
      if (this.in != null) {
         this.in.close();
         this.in = null;
      }

   }
}
