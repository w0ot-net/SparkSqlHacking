package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;

public class ClosedWriter extends Writer {
   public static final ClosedWriter INSTANCE = new ClosedWriter();
   /** @deprecated */
   @Deprecated
   public static final ClosedWriter CLOSED_WRITER;

   public void close() throws IOException {
   }

   public void flush() throws IOException {
      throw new IOException("flush() failed: stream is closed");
   }

   public void write(char[] cbuf, int off, int len) throws IOException {
      throw new IOException("write(" + new String(cbuf) + ", " + off + ", " + len + ") failed: stream is closed");
   }

   static {
      CLOSED_WRITER = INSTANCE;
   }
}
