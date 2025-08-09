package org.tukaani.xz.rangecoder;

import java.io.IOException;
import java.io.OutputStream;

public final class RangeEncoderToStream extends RangeEncoder {
   private final OutputStream out;

   public RangeEncoderToStream(OutputStream out) {
      this.out = out;
      this.reset();
   }

   void writeByte(int b) throws IOException {
      this.out.write(b);
   }
}
