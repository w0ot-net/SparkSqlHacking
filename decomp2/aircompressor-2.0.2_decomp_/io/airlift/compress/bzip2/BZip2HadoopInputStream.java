package io.airlift.compress.bzip2;

import io.airlift.compress.hadoop.HadoopInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

class BZip2HadoopInputStream extends HadoopInputStream {
   private final BufferedInputStream bufferedIn;
   private CBZip2InputStream input;

   public BZip2HadoopInputStream(InputStream in) {
      this.bufferedIn = new BufferedInputStream(in);
   }

   public int read(byte[] buffer, int offset, int length) throws IOException {
      if (length == 0) {
         return 0;
      } else {
         if (this.input == null) {
            this.bufferedIn.mark(2);
            if (this.bufferedIn.read() != 66 || this.bufferedIn.read() != 90) {
               this.bufferedIn.reset();
            }

            this.input = new CBZip2InputStream(this.bufferedIn);
         }

         int result = this.input.read(buffer, offset, length);
         if (result == -2) {
            result = this.input.read(buffer, offset, 1);
         }

         return result;
      }
   }

   public int read() throws IOException {
      byte[] buffer = new byte[1];
      int result = this.read(buffer, 0, 1);
      return result < 0 ? result : buffer[0] & 255;
   }

   public void resetState() {
      this.input = null;
   }

   public void close() throws IOException {
      this.input = null;
      this.bufferedIn.close();
   }
}
