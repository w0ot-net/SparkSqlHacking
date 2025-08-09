package org.apache.parquet.hadoop.codec;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.CompressorStream;

public class NonBlockedCompressorStream extends CompressorStream {
   public NonBlockedCompressorStream(OutputStream stream, Compressor compressor, int bufferSize) {
      super(stream, compressor, bufferSize);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (this.compressor.finished()) {
         throw new IOException("write beyond end of stream");
      } else if ((off | len | off + len | b.length - (off + len)) < 0) {
         throw new IndexOutOfBoundsException();
      } else if (len != 0) {
         this.compressor.setInput(b, off, len);
      }
   }
}
