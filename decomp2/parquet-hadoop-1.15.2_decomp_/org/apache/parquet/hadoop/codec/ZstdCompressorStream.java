package org.apache.parquet.hadoop.codec;

import com.github.luben.zstd.BufferPool;
import com.github.luben.zstd.ZstdOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;

public class ZstdCompressorStream extends CompressionOutputStream {
   private ZstdOutputStream zstdOutputStream;

   public ZstdCompressorStream(OutputStream stream, int level, int workers) throws IOException {
      super(stream);
      this.zstdOutputStream = new ZstdOutputStream(stream, level);
      this.zstdOutputStream.setWorkers(workers);
   }

   public ZstdCompressorStream(OutputStream stream, BufferPool pool, int level, int workers) throws IOException {
      super(stream);
      this.zstdOutputStream = new ZstdOutputStream(stream, pool);
      this.zstdOutputStream.setLevel(level);
      this.zstdOutputStream.setWorkers(workers);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.zstdOutputStream.write(b, off, len);
   }

   public void write(int b) throws IOException {
      this.zstdOutputStream.write(b);
   }

   public void finish() throws IOException {
   }

   public void resetState() throws IOException {
   }

   public void flush() throws IOException {
      this.zstdOutputStream.flush();
   }

   public void close() throws IOException {
      ZstdOutputStream zos = this.zstdOutputStream;
      Throwable var2 = null;

      try {
         zos.flush();
      } catch (Throwable var11) {
         var2 = var11;
         throw var11;
      } finally {
         if (zos != null) {
            if (var2 != null) {
               try {
                  zos.close();
               } catch (Throwable var10) {
                  var2.addSuppressed(var10);
               }
            } else {
               zos.close();
            }
         }

      }

   }
}
