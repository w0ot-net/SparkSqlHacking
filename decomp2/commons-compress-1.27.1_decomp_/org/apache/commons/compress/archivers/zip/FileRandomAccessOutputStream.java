package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

class FileRandomAccessOutputStream extends RandomAccessOutputStream {
   private final FileChannel channel;
   private long position;

   FileRandomAccessOutputStream(FileChannel channel) {
      this.channel = (FileChannel)Objects.requireNonNull(channel, "channel");
   }

   FileRandomAccessOutputStream(Path file) throws IOException {
      this(file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
   }

   FileRandomAccessOutputStream(Path file, OpenOption... options) throws IOException {
      this(FileChannel.open(file, options));
   }

   FileChannel channel() {
      return this.channel;
   }

   public void close() throws IOException {
      if (this.channel.isOpen()) {
         this.channel.close();
      }

   }

   public synchronized long position() {
      return this.position;
   }

   public synchronized void write(byte[] b, int off, int len) throws IOException {
      ZipIoUtil.writeFully(this.channel, ByteBuffer.wrap(b, off, len));
      this.position += (long)len;
   }

   public void writeFully(byte[] b, int off, int len, long atPosition) throws IOException {
      ByteBuffer buf = ByteBuffer.wrap(b, off, len);

      int written;
      for(long currentPos = atPosition; buf.hasRemaining(); currentPos += (long)written) {
         written = this.channel.write(buf, currentPos);
         if (written <= 0) {
            throw new IOException("Failed to fully write to file: written=" + written);
         }
      }

   }
}
