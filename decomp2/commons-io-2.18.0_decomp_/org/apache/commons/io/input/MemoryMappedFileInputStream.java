package org.apache.commons.io.input;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.apache.commons.io.build.AbstractStreamBuilder;

public final class MemoryMappedFileInputStream extends AbstractInputStream {
   private static final int DEFAULT_BUFFER_SIZE = 262144;
   private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.wrap(new byte[0]).asReadOnlyBuffer();
   private final int bufferSize;
   private final FileChannel channel;
   private ByteBuffer buffer;
   private long nextBufferPosition;

   public static Builder builder() {
      return new Builder();
   }

   private MemoryMappedFileInputStream(Path file, int bufferSize) throws IOException {
      this.buffer = EMPTY_BUFFER;
      this.bufferSize = bufferSize;
      this.channel = FileChannel.open(file, StandardOpenOption.READ);
   }

   public int available() throws IOException {
      return this.buffer.remaining();
   }

   private void cleanBuffer() {
      if (ByteBufferCleaner.isSupported() && this.buffer.isDirect()) {
         ByteBufferCleaner.clean(this.buffer);
      }

   }

   public void close() throws IOException {
      if (!this.isClosed()) {
         this.cleanBuffer();
         this.buffer = EMPTY_BUFFER;
         this.channel.close();
         super.close();
      }

   }

   int getBufferSize() {
      return this.bufferSize;
   }

   private void nextBuffer() throws IOException {
      long remainingInFile = this.channel.size() - this.nextBufferPosition;
      if (remainingInFile > 0L) {
         long amountToMap = Math.min(remainingInFile, (long)this.bufferSize);
         this.cleanBuffer();
         this.buffer = this.channel.map(MapMode.READ_ONLY, this.nextBufferPosition, amountToMap);
         this.nextBufferPosition += amountToMap;
      } else {
         this.buffer = EMPTY_BUFFER;
      }

   }

   public int read() throws IOException {
      this.checkOpen();
      if (!this.buffer.hasRemaining()) {
         this.nextBuffer();
         if (!this.buffer.hasRemaining()) {
            return -1;
         }
      }

      return Short.toUnsignedInt((short)this.buffer.get());
   }

   public int read(byte[] b, int off, int len) throws IOException {
      this.checkOpen();
      if (!this.buffer.hasRemaining()) {
         this.nextBuffer();
         if (!this.buffer.hasRemaining()) {
            return -1;
         }
      }

      int numBytes = Math.min(this.buffer.remaining(), len);
      this.buffer.get(b, off, numBytes);
      return numBytes;
   }

   public long skip(long n) throws IOException {
      this.checkOpen();
      if (n <= 0L) {
         return 0L;
      } else if (n <= (long)this.buffer.remaining()) {
         this.buffer.position((int)((long)this.buffer.position() + n));
         return n;
      } else {
         long remainingInFile = this.channel.size() - this.nextBufferPosition;
         long skipped = (long)this.buffer.remaining() + Math.min(remainingInFile, n - (long)this.buffer.remaining());
         this.nextBufferPosition += skipped - (long)this.buffer.remaining();
         this.nextBuffer();
         return skipped;
      }
   }

   public static class Builder extends AbstractStreamBuilder {
      public Builder() {
         this.setBufferSizeDefault(262144);
         this.setBufferSize(262144);
      }

      public MemoryMappedFileInputStream get() throws IOException {
         return new MemoryMappedFileInputStream(this.getPath(), this.getBufferSize());
      }
   }
}
