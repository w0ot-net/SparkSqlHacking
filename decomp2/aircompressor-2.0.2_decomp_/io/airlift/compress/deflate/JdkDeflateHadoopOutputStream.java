package io.airlift.compress.deflate;

import io.airlift.compress.hadoop.HadoopOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.Deflater;

class JdkDeflateHadoopOutputStream extends HadoopOutputStream {
   private final byte[] oneByte = new byte[1];
   private final OutputStream output;
   private final Deflater deflater;
   private final byte[] outputBuffer;
   protected boolean closed;

   public JdkDeflateHadoopOutputStream(OutputStream output, int bufferSize) {
      this.output = (OutputStream)Objects.requireNonNull(output, "output is null");
      this.deflater = new Deflater();
      this.outputBuffer = new byte[bufferSize];
   }

   public void write(int b) throws IOException {
      this.oneByte[0] = (byte)b;
      this.write(this.oneByte, 0, 1);
   }

   public void write(byte[] buffer, int offset, int length) throws IOException {
      this.deflater.setInput(buffer, offset, length);

      while(!this.deflater.needsInput()) {
         this.compress();
      }

   }

   public void finish() throws IOException {
      if (!this.deflater.finished()) {
         this.deflater.finish();

         while(!this.deflater.finished()) {
            this.compress();
         }
      }

      this.deflater.reset();
   }

   private void compress() throws IOException {
      int compressedSize = this.deflater.deflate(this.outputBuffer, 0, this.outputBuffer.length);
      this.output.write(this.outputBuffer, 0, compressedSize);
   }

   public void flush() throws IOException {
      this.output.flush();
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.closed = true;

         try {
            this.finish();
         } finally {
            this.output.close();
         }
      }

   }
}
