package org.apache.commons.compress.compressors.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

public class XZCompressorOutputStream extends CompressorOutputStream {
   public XZCompressorOutputStream(OutputStream outputStream) throws IOException {
      super(new XZOutputStream(outputStream, new LZMA2Options()));
   }

   public XZCompressorOutputStream(OutputStream outputStream, int preset) throws IOException {
      super(new XZOutputStream(outputStream, new LZMA2Options(preset)));
   }

   public void finish() throws IOException {
      ((XZOutputStream)this.out()).finish();
   }

   public void write(byte[] buf, int off, int len) throws IOException {
      this.out.write(buf, off, len);
   }
}
