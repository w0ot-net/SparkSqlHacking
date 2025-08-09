package org.tukaani.xz;

import java.io.FilterInputStream;
import java.io.InputStream;

public class CloseIgnoringInputStream extends FilterInputStream {
   public CloseIgnoringInputStream(InputStream in) {
      super(in);
   }

   public void close() {
   }
}
