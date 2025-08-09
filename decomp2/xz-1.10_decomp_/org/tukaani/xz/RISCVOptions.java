package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.RISCVDecoder;
import org.tukaani.xz.simple.RISCVEncoder;

public final class RISCVOptions extends BCJOptions {
   private static final int ALIGNMENT = 2;

   public RISCVOptions() {
      super(2);
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new SimpleOutputStream(out, new RISCVEncoder(this.startOffset));
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new SimpleInputStream(in, new RISCVDecoder(this.startOffset));
   }

   FilterEncoder getFilterEncoder() {
      return new BCJEncoder(this, 11L);
   }
}
