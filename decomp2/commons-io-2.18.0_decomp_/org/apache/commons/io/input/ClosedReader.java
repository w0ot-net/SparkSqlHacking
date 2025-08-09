package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;

public class ClosedReader extends Reader {
   public static final ClosedReader INSTANCE = new ClosedReader();
   /** @deprecated */
   @Deprecated
   public static final ClosedReader CLOSED_READER;

   public void close() throws IOException {
   }

   public int read(char[] cbuf, int off, int len) {
      return -1;
   }

   static {
      CLOSED_READER = INSTANCE;
   }
}
