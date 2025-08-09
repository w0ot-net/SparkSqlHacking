package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.SPARC;

public final class SPARCOptions extends BCJOptions {
   private static final int ALIGNMENT = 4;

   public SPARCOptions() {
      super(4);
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new SimpleOutputStream(out, new SPARC(true, this.startOffset));
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new SimpleInputStream(in, new SPARC(false, this.startOffset));
   }

   FilterEncoder getFilterEncoder() {
      return new BCJEncoder(this, 9L);
   }
}
