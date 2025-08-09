package org.apache.zookeeper.server.persistence;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePadding {
   private static final Logger LOG = LoggerFactory.getLogger(FileTxnLog.class);
   private static long preAllocSize = 67108864L;
   private static final ByteBuffer fill = ByteBuffer.allocateDirect(1);
   private long currentSize;

   public static long getPreAllocSize() {
      return preAllocSize;
   }

   public static void setPreallocSize(long size) {
      preAllocSize = size;
   }

   public void setCurrentSize(long currentSize) {
      this.currentSize = currentSize;
   }

   long padFile(FileChannel fileChannel) throws IOException {
      return this.padFile(fileChannel, fileChannel.position());
   }

   long padFile(FileChannel fileChannel, long position) throws IOException {
      long newFileSize = calculateFileSizeWithPadding(position, this.currentSize, preAllocSize);
      if (this.currentSize != newFileSize) {
         fileChannel.write((ByteBuffer)fill.position(0), newFileSize - (long)fill.remaining());
         this.currentSize = newFileSize;
      }

      return this.currentSize;
   }

   public static long calculateFileSizeWithPadding(long position, long fileSize, long preAllocSize) {
      if (preAllocSize > 0L && position + 4096L >= fileSize) {
         if (position > fileSize) {
            fileSize = position + preAllocSize;
            fileSize -= fileSize % preAllocSize;
         } else {
            fileSize += preAllocSize;
         }
      }

      return fileSize;
   }

   static {
      String size = System.getProperty("zookeeper.preAllocSize");
      if (size != null) {
         try {
            preAllocSize = Long.parseLong(size) * 1024L;
         } catch (NumberFormatException var2) {
            LOG.warn("{} is not a valid value for preAllocSize", size);
         }
      }

   }
}
