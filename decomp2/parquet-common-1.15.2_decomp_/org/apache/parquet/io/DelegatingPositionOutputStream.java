package org.apache.parquet.io;

import java.io.IOException;
import java.io.OutputStream;

public abstract class DelegatingPositionOutputStream extends PositionOutputStream {
   private final OutputStream stream;

   public DelegatingPositionOutputStream(OutputStream stream) {
      this.stream = stream;
   }

   public OutputStream getStream() {
      return this.stream;
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

   public void flush() throws IOException {
      this.stream.flush();
   }

   public abstract long getPos() throws IOException;

   public void write(int b) throws IOException {
      this.stream.write(b);
   }

   public void write(byte[] b) throws IOException {
      this.stream.write(b);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.stream.write(b, off, len);
   }
}
