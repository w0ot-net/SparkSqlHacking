package org.apache.parquet.hadoop.codec;

import java.io.IOException;
import java.io.InputStream;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.hadoop.io.compress.DecompressorStream;

public class NonBlockedDecompressorStream extends DecompressorStream {
   private boolean inputHandled;

   public NonBlockedDecompressorStream(InputStream stream, Decompressor decompressor, int bufferSize) throws IOException {
      super(stream, decompressor, bufferSize);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (!this.inputHandled) {
         while(true) {
            int compressedBytes = this.getCompressedData();
            if (compressedBytes == -1) {
               this.inputHandled = true;
               break;
            }

            this.decompressor.setInput(this.buffer, 0, compressedBytes);
         }
      }

      int decompressedBytes = this.decompressor.decompress(b, off, len);
      if (this.decompressor.finished()) {
         this.decompressor.reset();
      }

      if (decompressedBytes == 0) {
         throw new IOException("Corrupt file: Zero bytes read during decompression.");
      } else {
         return decompressedBytes;
      }
   }
}
