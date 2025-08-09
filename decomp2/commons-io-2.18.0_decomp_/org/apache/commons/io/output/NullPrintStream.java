package org.apache.commons.io.output;

import java.io.PrintStream;

public class NullPrintStream extends PrintStream {
   public static final NullPrintStream INSTANCE = new NullPrintStream();
   /** @deprecated */
   @Deprecated
   public static final NullPrintStream NULL_PRINT_STREAM;

   /** @deprecated */
   @Deprecated
   public NullPrintStream() {
      super(NullOutputStream.INSTANCE);
   }

   static {
      NULL_PRINT_STREAM = INSTANCE;
   }
}
