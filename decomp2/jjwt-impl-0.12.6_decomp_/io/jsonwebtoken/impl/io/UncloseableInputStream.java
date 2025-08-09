package io.jsonwebtoken.impl.io;

import java.io.FilterInputStream;
import java.io.InputStream;

public final class UncloseableInputStream extends FilterInputStream {
   public UncloseableInputStream(InputStream in) {
      super(in);
   }

   public void close() {
      this.in = ClosedInputStream.INSTANCE;
   }
}
