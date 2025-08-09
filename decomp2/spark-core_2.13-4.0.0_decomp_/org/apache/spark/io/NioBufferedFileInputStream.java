package org.apache.spark.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import org.apache.spark.storage.StorageUtils;
import org.apache.spark.unsafe.Platform;

public final class NioBufferedFileInputStream extends InputStream {
   private static final Cleaner CLEANER = Cleaner.create();
   private static final int DEFAULT_BUFFER_SIZE_BYTES = 8192;
   private final Cleaner.Cleanable cleanable;
   private final ByteBuffer byteBuffer;
   private final FileChannel fileChannel;

   public NioBufferedFileInputStream(File file, int bufferSizeInBytes) throws IOException {
      this.byteBuffer = Platform.allocateDirectBuffer(bufferSizeInBytes);
      this.fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ);
      this.byteBuffer.flip();
      this.cleanable = CLEANER.register(this, new ResourceCleaner(this.fileChannel, this.byteBuffer));
   }

   public NioBufferedFileInputStream(File file) throws IOException {
      this(file, 8192);
   }

   private boolean refill() throws IOException {
      if (!this.byteBuffer.hasRemaining()) {
         this.byteBuffer.clear();

         int nRead;
         for(nRead = 0; nRead == 0; nRead = this.fileChannel.read(this.byteBuffer)) {
         }

         this.byteBuffer.flip();
         if (nRead < 0) {
            return false;
         }
      }

      return true;
   }

   public synchronized int read() throws IOException {
      return !this.refill() ? -1 : this.byteBuffer.get() & 255;
   }

   public synchronized int read(byte[] b, int offset, int len) throws IOException {
      if (offset >= 0 && len >= 0 && offset + len >= 0 && offset + len <= b.length) {
         if (!this.refill()) {
            return -1;
         } else {
            len = Math.min(len, this.byteBuffer.remaining());
            this.byteBuffer.get(b, offset, len);
            return len;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public synchronized int available() throws IOException {
      return this.byteBuffer.remaining();
   }

   public synchronized long skip(long n) throws IOException {
      if (n <= 0L) {
         return 0L;
      } else if ((long)this.byteBuffer.remaining() >= n) {
         this.byteBuffer.position(this.byteBuffer.position() + (int)n);
         return n;
      } else {
         long skippedFromBuffer = (long)this.byteBuffer.remaining();
         long toSkipFromFileChannel = n - skippedFromBuffer;
         this.byteBuffer.position(0);
         this.byteBuffer.flip();
         return skippedFromBuffer + this.skipFromFileChannel(toSkipFromFileChannel);
      }
   }

   private long skipFromFileChannel(long n) throws IOException {
      long currentFilePosition = this.fileChannel.position();
      long size = this.fileChannel.size();
      if (n > size - currentFilePosition) {
         this.fileChannel.position(size);
         return size - currentFilePosition;
      } else {
         this.fileChannel.position(currentFilePosition + n);
         return n;
      }
   }

   public synchronized void close() throws IOException {
      try {
         this.cleanable.clean();
      } catch (UncheckedIOException re) {
         if (re.getCause() != null) {
            throw re.getCause();
         } else {
            throw re;
         }
      }
   }

   private static record ResourceCleaner(FileChannel fileChannel, ByteBuffer byteBuffer) implements Runnable {
      public void run() {
         try {
            this.fileChannel.close();
         } catch (IOException e) {
            throw new UncheckedIOException(e);
         } finally {
            StorageUtils.dispose(this.byteBuffer);
         }

      }
   }
}
