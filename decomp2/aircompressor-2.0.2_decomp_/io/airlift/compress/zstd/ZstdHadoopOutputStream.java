package io.airlift.compress.zstd;

import io.airlift.compress.hadoop.HadoopOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

class ZstdHadoopOutputStream extends HadoopOutputStream {
   private final OutputStream out;
   private boolean initialized;
   private ZstdOutputStream zstdOutputStream;

   public ZstdHadoopOutputStream(OutputStream out) {
      this.out = (OutputStream)Objects.requireNonNull(out, "out is null");
   }

   public void write(int b) throws IOException {
      this.openStreamIfNecessary();
      this.zstdOutputStream.write(b);
   }

   public void write(byte[] buffer, int offset, int length) throws IOException {
      this.openStreamIfNecessary();
      this.zstdOutputStream.write(buffer, offset, length);
   }

   public void finish() throws IOException {
      if (this.zstdOutputStream != null) {
         this.zstdOutputStream.finishWithoutClosingSource();
         this.zstdOutputStream = null;
      }

   }

   public void flush() throws IOException {
      this.out.flush();
   }

   public void close() throws IOException {
      try {
         if (!this.initialized) {
            this.openStreamIfNecessary();
         }

         this.finish();
      } finally {
         this.out.close();
      }

   }

   private void openStreamIfNecessary() throws IOException {
      if (this.zstdOutputStream == null) {
         this.initialized = true;
         this.zstdOutputStream = new ZstdOutputStream(this.out);
      }

   }
}
