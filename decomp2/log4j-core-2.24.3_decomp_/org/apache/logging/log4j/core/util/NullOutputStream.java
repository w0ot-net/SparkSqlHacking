package org.apache.logging.log4j.core.util;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream extends OutputStream {
   private static final NullOutputStream INSTANCE = new NullOutputStream();
   /** @deprecated */
   @Deprecated
   public static final NullOutputStream NULL_OUTPUT_STREAM;

   public static NullOutputStream getInstance() {
      return INSTANCE;
   }

   private NullOutputStream() {
   }

   public void write(final byte[] b, final int off, final int len) {
   }

   public void write(final int b) {
   }

   public void write(final byte[] b) throws IOException {
   }

   static {
      NULL_OUTPUT_STREAM = INSTANCE;
   }
}
