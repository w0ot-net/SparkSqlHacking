package org.apache.logging.log4j.core.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public final class Throwables {
   private Throwables() {
   }

   public static Throwable getRootCause(final Throwable throwable) {
      Throwable slowPointer = throwable;
      boolean advanceSlowPointer = false;

      Throwable parent;
      Throwable cause;
      for(parent = throwable; (cause = parent.getCause()) != null; advanceSlowPointer = !advanceSlowPointer) {
         parent = cause;
         if (cause == slowPointer) {
            throw new IllegalArgumentException("loop in causal chain");
         }

         if (advanceSlowPointer) {
            slowPointer = slowPointer.getCause();
         }
      }

      return parent;
   }

   @SuppressFBWarnings(
      value = {"INFORMATION_EXPOSURE_THROUGH_AN_ERROR_MESSAGE"},
      justification = "Log4j prints stacktraces only to logs, which should be private."
   )
   public static List toStringList(final Throwable throwable) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);

      try {
         throwable.printStackTrace(pw);
      } catch (RuntimeException var10) {
      }

      pw.flush();
      List<String> lines = new ArrayList();
      LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));

      try {
         for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            lines.add(line);
         }
      } catch (IOException ex) {
         if (ex instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         lines.add(ex.toString());
      } finally {
         Closer.closeSilently(reader);
      }

      return lines;
   }

   public static void rethrow(final Throwable t) {
      rethrow0(t);
   }

   private static void rethrow0(final Throwable t) throws Throwable {
      throw t;
   }
}
