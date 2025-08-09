package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.LZMAOutputStream;

public class LZMACompressorOutputStream extends CompressorOutputStream {
   public LZMACompressorOutputStream(OutputStream outputStream) throws IOException {
      super(new LZMAOutputStream(outputStream, new LZMA2Options(), -1L));
   }

   public void finish() throws IOException {
      ((LZMAOutputStream)this.out()).finish();
   }

   public void flush() throws IOException {
   }

   public void write(byte[] buf, int off, int len) throws IOException {
      this.out.write(buf, off, len);
   }
}
