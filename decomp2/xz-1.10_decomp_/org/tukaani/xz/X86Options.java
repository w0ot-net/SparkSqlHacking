package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.X86;

public final class X86Options extends BCJOptions {
   private static final int ALIGNMENT = 1;

   public X86Options() {
      super(1);
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new SimpleOutputStream(out, new X86(true, this.startOffset));
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new SimpleInputStream(in, new X86(false, this.startOffset));
   }

   FilterEncoder getFilterEncoder() {
      return new BCJEncoder(this, 4L);
   }
}
