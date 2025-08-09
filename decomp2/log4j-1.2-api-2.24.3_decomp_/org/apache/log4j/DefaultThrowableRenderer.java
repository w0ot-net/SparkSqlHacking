package org.apache.log4j;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import org.apache.log4j.spi.ThrowableRenderer;

public final class DefaultThrowableRenderer implements ThrowableRenderer {
   @SuppressFBWarnings(
      value = {"INFORMATION_EXPOSURE_THROUGH_AN_ERROR_MESSAGE"},
      justification = "The throwable is formatted into a log file, which should be private."
   )
   public static String[] render(final Throwable throwable) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);

      try {
         throwable.printStackTrace(pw);
      } catch (RuntimeException var6) {
      }

      pw.flush();
      LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
      ArrayList<String> lines = new ArrayList();

      try {
         for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            lines.add(line);
         }
      } catch (IOException ex) {
         if (ex instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         lines.add(ex.toString());
      }

      String[] tempRep = new String[lines.size()];
      lines.toArray(tempRep);
      return tempRep;
   }

   public String[] doRender(final Throwable throwable) {
      return render(throwable);
   }
}
