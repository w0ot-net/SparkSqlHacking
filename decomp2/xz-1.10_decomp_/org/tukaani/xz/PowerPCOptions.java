package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.PowerPC;

public final class PowerPCOptions extends BCJOptions {
   private static final int ALIGNMENT = 4;

   public PowerPCOptions() {
      super(4);
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new SimpleOutputStream(out, new PowerPC(true, this.startOffset));
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new SimpleInputStream(in, new PowerPC(false, this.startOffset));
   }

   FilterEncoder getFilterEncoder() {
      return new BCJEncoder(this, 5L);
   }
}
