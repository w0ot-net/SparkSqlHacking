package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.IA64;

public final class IA64Options extends BCJOptions {
   private static final int ALIGNMENT = 16;

   public IA64Options() {
      super(16);
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new SimpleOutputStream(out, new IA64(true, this.startOffset));
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new SimpleInputStream(in, new IA64(false, this.startOffset));
   }

   FilterEncoder getFilterEncoder() {
      return new BCJEncoder(this, 6L);
   }
}
