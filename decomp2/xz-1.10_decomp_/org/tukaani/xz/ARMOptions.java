package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARM;

public final class ARMOptions extends BCJOptions {
   private static final int ALIGNMENT = 4;

   public ARMOptions() {
      super(4);
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new SimpleOutputStream(out, new ARM(true, this.startOffset));
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new SimpleInputStream(in, new ARM(false, this.startOffset));
   }

   FilterEncoder getFilterEncoder() {
      return new BCJEncoder(this, 7L);
   }
}
