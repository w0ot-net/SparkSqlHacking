package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class ClosedInputStream extends InputStream {
   public static final ClosedInputStream INSTANCE = new ClosedInputStream();
   /** @deprecated */
   @Deprecated
   public static final ClosedInputStream CLOSED_INPUT_STREAM;

   static InputStream ifNull(InputStream in) {
      return (InputStream)(in != null ? in : INSTANCE);
   }

   public int read() {
      return -1;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return -1;
   }

   static {
      CLOSED_INPUT_STREAM = INSTANCE;
   }
}
