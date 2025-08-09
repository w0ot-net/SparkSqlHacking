package org.apache.commons.io.input;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;
import org.apache.commons.io.build.AbstractStreamBuilder;

public class RandomAccessFileInputStream extends AbstractInputStream {
   private final boolean propagateClose;
   private final RandomAccessFile randomAccessFile;

   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public RandomAccessFileInputStream(RandomAccessFile file) {
      this(file, false);
   }

   /** @deprecated */
   @Deprecated
   public RandomAccessFileInputStream(RandomAccessFile file, boolean propagateClose) {
      this.randomAccessFile = (RandomAccessFile)Objects.requireNonNull(file, "file");
      this.propagateClose = propagateClose;
   }

   public int available() throws IOException {
      long avail = this.availableLong();
      return avail > 2147483647L ? Integer.MAX_VALUE : (int)avail;
   }

   public long availableLong() throws IOException {
      return this.isClosed() ? 0L : this.randomAccessFile.length() - this.randomAccessFile.getFilePointer();
   }

   public void close() throws IOException {
      super.close();
      if (this.propagateClose) {
         this.randomAccessFile.close();
      }

   }

   public RandomAccessFile getRandomAccessFile() {
      return this.randomAccessFile;
   }

   public boolean isCloseOnClose() {
      return this.propagateClose;
   }

   public int read() throws IOException {
      return this.randomAccessFile.read();
   }

   public int read(byte[] bytes) throws IOException {
      return this.randomAccessFile.read(bytes);
   }

   public int read(byte[] bytes, int offset, int length) throws IOException {
      return this.randomAccessFile.read(bytes, offset, length);
   }

   public long skip(long skipCount) throws IOException {
      if (skipCount <= 0L) {
         return 0L;
      } else {
         long filePointer = this.randomAccessFile.getFilePointer();
         long fileLength = this.randomAccessFile.length();
         if (filePointer >= fileLength) {
            return 0L;
         } else {
            long targetPos = filePointer + skipCount;
            long newPos = targetPos > fileLength ? fileLength - 1L : targetPos;
            if (newPos > 0L) {
               this.randomAccessFile.seek(newPos);
            }

            return this.randomAccessFile.getFilePointer() - filePointer;
         }
      }
   }

   public static class Builder extends AbstractStreamBuilder {
      private boolean propagateClose;

      public RandomAccessFileInputStream get() throws IOException {
         return new RandomAccessFileInputStream(this.getRandomAccessFile(), this.propagateClose);
      }

      public Builder setCloseOnClose(boolean propagateClose) {
         this.propagateClose = propagateClose;
         return this;
      }

      public Builder setRandomAccessFile(RandomAccessFile randomAccessFile) {
         return (Builder)super.setRandomAccessFile(randomAccessFile);
      }
   }
}
