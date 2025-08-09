package org.apache.commons.io.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import org.apache.commons.io.build.AbstractStreamBuilder;

public final class BufferedFileChannelInputStream extends InputStream {
   private final ByteBuffer byteBuffer;
   private final FileChannel fileChannel;

   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public BufferedFileChannelInputStream(File file) throws IOException {
      this((File)file, 8192);
   }

   /** @deprecated */
   @Deprecated
   public BufferedFileChannelInputStream(File file, int bufferSize) throws IOException {
      this(file.toPath(), bufferSize);
   }

   private BufferedFileChannelInputStream(FileChannel fileChannel, int bufferSize) {
      this.fileChannel = (FileChannel)Objects.requireNonNull(fileChannel, "path");
      this.byteBuffer = ByteBuffer.allocateDirect(bufferSize);
      this.byteBuffer.flip();
   }

   /** @deprecated */
   @Deprecated
   public BufferedFileChannelInputStream(Path path) throws IOException {
      this((Path)path, 8192);
   }

   /** @deprecated */
   @Deprecated
   public BufferedFileChannelInputStream(Path path, int bufferSize) throws IOException {
      this(FileChannel.open(path, StandardOpenOption.READ), bufferSize);
   }

   public synchronized int available() throws IOException {
      if (!this.fileChannel.isOpen()) {
         return 0;
      } else {
         return !this.refill() ? 0 : this.byteBuffer.remaining();
      }
   }

   private void clean(ByteBuffer buffer) {
      if (buffer.isDirect()) {
         this.cleanDirectBuffer(buffer);
      }

   }

   private void cleanDirectBuffer(ByteBuffer buffer) {
      if (ByteBufferCleaner.isSupported()) {
         ByteBufferCleaner.clean(buffer);
      }

   }

   public synchronized void close() throws IOException {
      try {
         this.fileChannel.close();
      } finally {
         this.clean(this.byteBuffer);
      }

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

   private boolean refill() throws IOException {
      Input.checkOpen(this.fileChannel.isOpen());
      if (this.byteBuffer.hasRemaining()) {
         return true;
      } else {
         this.byteBuffer.clear();

         int nRead;
         for(nRead = 0; nRead == 0; nRead = this.fileChannel.read(this.byteBuffer)) {
         }

         this.byteBuffer.flip();
         return nRead >= 0;
      }
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

   public static class Builder extends AbstractStreamBuilder {
      private FileChannel fileChannel;

      public BufferedFileChannelInputStream get() throws IOException {
         return this.fileChannel != null ? new BufferedFileChannelInputStream(this.fileChannel, this.getBufferSize()) : new BufferedFileChannelInputStream(this.getPath(), this.getBufferSize());
      }

      public Builder setFileChannel(FileChannel fileChannel) {
         this.fileChannel = fileChannel;
         return this;
      }
   }
}
