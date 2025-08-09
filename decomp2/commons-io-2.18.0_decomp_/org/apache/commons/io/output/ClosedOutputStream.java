package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class ClosedOutputStream extends OutputStream {
   public static final ClosedOutputStream INSTANCE = new ClosedOutputStream();
   /** @deprecated */
   @Deprecated
   public static final ClosedOutputStream CLOSED_OUTPUT_STREAM;

   public void flush() throws IOException {
      throw new IOException("flush() failed: stream is closed");
   }

   public void write(byte[] b, int off, int len) throws IOException {
      throw new IOException("write(byte[], int, int) failed: stream is closed");
   }

   public void write(int b) throws IOException {
      throw new IOException("write(int) failed: stream is closed");
   }

   static {
      CLOSED_OUTPUT_STREAM = INSTANCE;
   }
}
