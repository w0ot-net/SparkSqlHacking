package io.airlift.compress.gzip;

import io.airlift.compress.hadoop.HadoopOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

class JdkGzipHadoopOutputStream extends HadoopOutputStream {
   private final byte[] oneByte = new byte[1];
   private final GZIPOutputStreamWrapper output;

   public JdkGzipHadoopOutputStream(OutputStream output, int bufferSize) throws IOException {
      this.output = new GZIPOutputStreamWrapper((OutputStream)Objects.requireNonNull(output, "output is null"), bufferSize);
   }

   public void write(int b) throws IOException {
      this.oneByte[0] = (byte)b;
      this.write(this.oneByte, 0, 1);
   }

   public void write(byte[] buffer, int offset, int length) throws IOException {
      this.output.write(buffer, offset, length);
   }

   public void finish() throws IOException {
      try {
         this.output.finish();
      } finally {
         this.output.end();
      }

   }

   public void flush() throws IOException {
      this.output.flush();
   }

   public void close() throws IOException {
      try {
         this.finish();
      } finally {
         this.output.close();
      }

   }

   private static class GZIPOutputStreamWrapper extends GZIPOutputStream {
      GZIPOutputStreamWrapper(OutputStream output, int bufferSize) throws IOException {
         super(output, bufferSize);
      }

      public void end() throws IOException {
         this.def.end();
      }
   }
}
