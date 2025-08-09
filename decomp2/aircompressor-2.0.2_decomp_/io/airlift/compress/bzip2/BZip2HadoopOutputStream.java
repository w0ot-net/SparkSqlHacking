package io.airlift.compress.bzip2;

import io.airlift.compress.hadoop.HadoopOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

class BZip2HadoopOutputStream extends HadoopOutputStream {
   private final OutputStream rawOutput;
   private boolean initialized;
   private CBZip2OutputStream output;

   public BZip2HadoopOutputStream(OutputStream out) {
      this.rawOutput = (OutputStream)Objects.requireNonNull(out, "out is null");
   }

   public void write(int b) throws IOException {
      this.openStreamIfNecessary();
      this.output.write(b);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      this.openStreamIfNecessary();
      this.output.write(b, off, len);
   }

   public void finish() throws IOException {
      if (this.output != null) {
         this.output.finish();
         this.output = null;
      }

   }

   public void flush() throws IOException {
      this.rawOutput.flush();
   }

   public void close() throws IOException {
      try {
         if (!this.initialized) {
            this.openStreamIfNecessary();
         }

         this.finish();
      } finally {
         this.rawOutput.close();
      }

   }

   private void openStreamIfNecessary() throws IOException {
      if (this.output == null) {
         this.initialized = true;
         this.rawOutput.write(new byte[]{66, 90});
         this.output = new CBZip2OutputStream(this.rawOutput);
      }

   }
}
