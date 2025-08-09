package io.vertx.core.logging;

import io.vertx.core.impl.Utils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class VertxLoggerFormatter extends Formatter {
   public String format(LogRecord record) {
      OffsetDateTime date = fromMillis(record.getMillis());
      StringBuilder sb = new StringBuilder();
      sb.append("[").append(Thread.currentThread().getName()).append("] ");
      sb.append(date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)).append(" ");
      sb.append(record.getLevel()).append(" [");
      sb.append(record.getLoggerName()).append("]").append("  ");
      sb.append(record.getMessage());
      sb.append(Utils.LINE_SEPARATOR);
      if (record.getThrown() != null) {
         try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            record.getThrown().printStackTrace(pw);
            pw.close();
            sb.append(sw.toString());
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }

      return sb.toString();
   }

   private static OffsetDateTime fromMillis(long epochMillis) {
      return OffsetDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
   }
}
