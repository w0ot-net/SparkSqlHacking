package org.apache.commons.io.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

public final class FileChannels {
   public static boolean contentEquals(FileChannel channel1, FileChannel channel2, int byteBufferSize) throws IOException {
      if (Objects.equals(channel1, channel2)) {
         return true;
      } else {
         long size1 = size(channel1);
         long size2 = size(channel2);
         if (size1 != size2) {
            return false;
         } else if (size1 == 0L && size2 == 0L) {
            return true;
         } else {
            ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(byteBufferSize);
            ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(byteBufferSize);

            do {
               int read1 = channel1.read(byteBuffer1);
               int read2 = channel2.read(byteBuffer2);
               byteBuffer1.clear();
               byteBuffer2.clear();
               if (read1 == -1 && read2 == -1) {
                  return byteBuffer1.equals(byteBuffer2);
               }

               if (read1 != read2) {
                  return false;
               }
            } while(byteBuffer1.equals(byteBuffer2));

            return false;
         }
      }
   }

   private static long size(FileChannel channel) throws IOException {
      return channel != null ? channel.size() : 0L;
   }

   private FileChannels() {
   }
}
