package org.apache.parquet.hadoop.util;

import java.io.IOException;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.parquet.io.PositionOutputStream;

public class HadoopPositionOutputStream extends PositionOutputStream {
   private final FSDataOutputStream wrapped;

   HadoopPositionOutputStream(FSDataOutputStream wrapped) {
      this.wrapped = wrapped;
   }

   public long getPos() throws IOException {
      return this.wrapped.getPos();
   }

   public void write(int b) throws IOException {
      this.wrapped.write(b);
   }

   public void write(byte[] b) throws IOException {
      this.wrapped.write(b);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.wrapped.write(b, off, len);
   }

   public void sync() throws IOException {
      this.wrapped.hsync();
   }

   public void flush() throws IOException {
      this.wrapped.flush();
   }

   public void close() throws IOException {
      FSDataOutputStream fdos = this.wrapped;
      Throwable var2 = null;

      try {
         fdos.hflush();
      } catch (Throwable var11) {
         var2 = var11;
         throw var11;
      } finally {
         if (fdos != null) {
            if (var2 != null) {
               try {
                  fdos.close();
               } catch (Throwable var10) {
                  var2.addSuppressed(var10);
               }
            } else {
               fdos.close();
            }
         }

      }

   }
}
