package org.glassfish.jersey.internal.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ExceptionUtils {
   private ExceptionUtils() {
   }

   public static String exceptionStackTraceAsString(Throwable t) {
      StringWriter sw = new StringWriter();
      t.printStackTrace(new PrintWriter(sw));
      return sw.toString();
   }

   public static void conditionallyReThrow(Exception e, boolean rethrow, Logger logger, String m, Level level) throws Exception {
      if (rethrow) {
         throw e;
      } else {
         logger.log(level, m, e);
      }
   }
}
