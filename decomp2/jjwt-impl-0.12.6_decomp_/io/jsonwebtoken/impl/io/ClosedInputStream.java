package io.jsonwebtoken.impl.io;

import java.io.IOException;
import java.io.InputStream;

public final class ClosedInputStream extends InputStream {
   public static final ClosedInputStream INSTANCE = new ClosedInputStream();

   private ClosedInputStream() {
   }

   public int read() throws IOException {
      return -1;
   }
}
