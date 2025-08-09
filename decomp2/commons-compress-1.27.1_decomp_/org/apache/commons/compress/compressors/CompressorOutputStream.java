package org.apache.commons.compress.compressors;

import java.io.FilterOutputStream;
import java.io.OutputStream;

public abstract class CompressorOutputStream extends FilterOutputStream {
   public CompressorOutputStream() {
      super((OutputStream)null);
   }

   public CompressorOutputStream(OutputStream out) {
      super(out);
   }

   protected OutputStream out() {
      return this.out;
   }
}
