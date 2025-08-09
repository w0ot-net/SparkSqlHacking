package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;

class ZipIoUtil {
   static void writeFully(SeekableByteChannel channel, ByteBuffer buf) throws IOException {
      while(true) {
         if (buf.hasRemaining()) {
            int remaining = buf.remaining();
            int written = channel.write(buf);
            if (written > 0) {
               continue;
            }

            throw new IOException("Failed to fully write: channel=" + channel + " length=" + remaining + " written=" + written);
         }

         return;
      }
   }

   static void writeFullyAt(FileChannel channel, ByteBuffer buf, long position) throws IOException {
      int written;
      for(long currentPosition = position; buf.hasRemaining(); currentPosition += (long)written) {
         int remaining = buf.remaining();
         written = channel.write(buf, currentPosition);
         if (written <= 0) {
            throw new IOException("Failed to fully write: channel=" + channel + " length=" + remaining + " written=" + written);
         }
      }

   }

   private ZipIoUtil() {
   }
}
