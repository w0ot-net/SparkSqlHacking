package org.apache.parquet.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Path;

public class LocalInputFile implements InputFile {
   private final Path path;
   private long length = -1L;

   public LocalInputFile(Path file) {
      this.path = file;
   }

   public long getLength() throws IOException {
      if (this.length == -1L) {
         RandomAccessFile file = new RandomAccessFile(this.path.toFile(), "r");
         Throwable var2 = null;

         try {
            this.length = file.length();
         } catch (Throwable var11) {
            var2 = var11;
            throw var11;
         } finally {
            if (file != null) {
               if (var2 != null) {
                  try {
                     file.close();
                  } catch (Throwable var10) {
                     var2.addSuppressed(var10);
                  }
               } else {
                  file.close();
               }
            }

         }
      }

      return this.length;
   }

   public SeekableInputStream newStream() throws IOException {
      return new SeekableInputStream() {
         private final RandomAccessFile randomAccessFile;

         {
            this.randomAccessFile = new RandomAccessFile(LocalInputFile.this.path.toFile(), "r");
         }

         public int read() throws IOException {
            return this.randomAccessFile.read();
         }

         public long getPos() throws IOException {
            return this.randomAccessFile.getFilePointer();
         }

         public void seek(long newPos) throws IOException {
            this.randomAccessFile.seek(newPos);
         }

         public void readFully(byte[] bytes) throws IOException {
            this.randomAccessFile.readFully(bytes);
         }

         public void readFully(byte[] bytes, int start, int len) throws IOException {
            this.randomAccessFile.readFully(bytes, start, len);
         }

         public int read(ByteBuffer buf) throws IOException {
            byte[] buffer = new byte[buf.remaining()];
            int code = this.read(buffer);
            buf.put(buffer, buf.position() + buf.arrayOffset(), buf.remaining());
            return code;
         }

         public void readFully(ByteBuffer buf) throws IOException {
            byte[] buffer = new byte[buf.remaining()];
            this.readFully(buffer);
            buf.put(buffer, buf.position() + buf.arrayOffset(), buf.remaining());
         }

         public void close() throws IOException {
            this.randomAccessFile.close();
         }
      };
   }
}
