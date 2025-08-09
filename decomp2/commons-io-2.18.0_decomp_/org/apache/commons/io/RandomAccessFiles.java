package org.apache.commons.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Objects;
import org.apache.commons.io.channels.FileChannels;

public class RandomAccessFiles {
   public static boolean contentEquals(RandomAccessFile raf1, RandomAccessFile raf2) throws IOException {
      if (Objects.equals(raf1, raf2)) {
         return true;
      } else {
         long length1 = length(raf1);
         long length2 = length(raf2);
         if (length1 != length2) {
            return false;
         } else if (length1 == 0L && length2 == 0L) {
            return true;
         } else {
            FileChannel channel1 = raf1.getChannel();
            FileChannel channel2 = raf2.getChannel();
            return FileChannels.contentEquals(channel1, channel2, 8192);
         }
      }
   }

   private static long length(RandomAccessFile raf) throws IOException {
      return raf != null ? raf.length() : 0L;
   }

   public static byte[] read(RandomAccessFile input, long position, int length) throws IOException {
      input.seek(position);
      Objects.requireNonNull(input);
      return IOUtils.toByteArray(input::read, length);
   }

   public static RandomAccessFile reset(RandomAccessFile raf) throws IOException {
      raf.seek(0L);
      return raf;
   }
}
