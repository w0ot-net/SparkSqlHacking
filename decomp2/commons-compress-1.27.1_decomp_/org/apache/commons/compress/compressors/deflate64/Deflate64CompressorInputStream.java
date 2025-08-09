package org.apache.commons.compress.compressors.deflate64;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;

public class Deflate64CompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
   private InputStream originalStream;
   private HuffmanDecoder decoder;
   private long compressedBytesRead;
   private final byte[] oneByte;

   Deflate64CompressorInputStream(HuffmanDecoder decoder) {
      this.oneByte = new byte[1];
      this.decoder = decoder;
   }

   public Deflate64CompressorInputStream(InputStream in) {
      this(new HuffmanDecoder(in));
      this.originalStream = in;
   }

   public int available() throws IOException {
      return this.decoder != null ? this.decoder.available() : 0;
   }

   public void close() throws IOException {
      try {
         this.closeDecoder();
      } finally {
         if (this.originalStream != null) {
            this.originalStream.close();
            this.originalStream = null;
         }

      }

   }

   private void closeDecoder() {
      Closeable c = this.decoder;
      IOUtils.closeQuietly(c);
      this.decoder = null;
   }

   public long getCompressedCount() {
      return this.compressedBytesRead;
   }

   public int read() throws IOException {
      while(true) {
         int r = this.read(this.oneByte);
         switch (r) {
            case -1:
               return -1;
            case 0:
               break;
            case 1:
               return this.oneByte[0] & 255;
            default:
               throw new IllegalStateException("Invalid return value from read: " + r);
         }
      }
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (len == 0) {
         return 0;
      } else {
         int read = -1;
         if (this.decoder != null) {
            try {
               read = this.decoder.decode(b, off, len);
            } catch (RuntimeException ex) {
               throw new IOException("Invalid Deflate64 input", ex);
            }

            this.compressedBytesRead = this.decoder.getBytesRead();
            this.count(read);
            if (read == -1) {
               this.closeDecoder();
            }
         }

         return read;
      }
   }
}
