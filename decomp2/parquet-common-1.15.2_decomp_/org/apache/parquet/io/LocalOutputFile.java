package org.apache.parquet.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LocalOutputFile implements OutputFile {
   private static final int BUFFER_SIZE_DEFAULT = 4096;
   private final Path path;

   public LocalOutputFile(Path file) {
      this.path = file;
   }

   public PositionOutputStream create(long blockSize) throws IOException {
      return new LocalPositionOutputStream(4096, new StandardOpenOption[]{StandardOpenOption.CREATE_NEW});
   }

   public PositionOutputStream createOrOverwrite(long blockSize) throws IOException {
      return new LocalPositionOutputStream(4096, new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING});
   }

   public boolean supportsBlockSize() {
      return false;
   }

   public long defaultBlockSize() {
      return -1L;
   }

   public String getPath() {
      return this.path.toString();
   }

   private class LocalPositionOutputStream extends PositionOutputStream {
      private final BufferedOutputStream stream;
      private long pos = 0L;

      public LocalPositionOutputStream(int buffer, StandardOpenOption... openOption) throws IOException {
         this.stream = new BufferedOutputStream(Files.newOutputStream(LocalOutputFile.this.path, openOption), buffer);
      }

      public long getPos() {
         return this.pos;
      }

      public void write(int data) throws IOException {
         ++this.pos;
         this.stream.write(data);
      }

      public void write(byte[] data) throws IOException {
         this.pos += (long)data.length;
         this.stream.write(data);
      }

      public void write(byte[] data, int off, int len) throws IOException {
         this.pos += (long)len;
         this.stream.write(data, off, len);
      }

      public void flush() throws IOException {
         this.stream.flush();
      }

      public void close() throws IOException {
         try {
            OutputStream os = this.stream;
            Throwable var2 = null;

            try {
               os.flush();
            } catch (Throwable var12) {
               var2 = var12;
               throw var12;
            } finally {
               if (os != null) {
                  if (var2 != null) {
                     try {
                        os.close();
                     } catch (Throwable var11) {
                        var2.addSuppressed(var11);
                     }
                  } else {
                     os.close();
                  }
               }

            }

         } catch (Exception e) {
            throw new IOException(e);
         }
      }
   }
}
