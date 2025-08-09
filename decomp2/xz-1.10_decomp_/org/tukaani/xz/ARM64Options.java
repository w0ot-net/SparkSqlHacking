package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARM64;

public final class ARM64Options extends BCJOptions {
   private static final int ALIGNMENT = 4;

   public ARM64Options() {
      super(4);
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new SimpleOutputStream(out, new ARM64(true, this.startOffset));
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new SimpleInputStream(in, new ARM64(false, this.startOffset));
   }

   FilterEncoder getFilterEncoder() {
      return new BCJEncoder(this, 10L);
   }
}
