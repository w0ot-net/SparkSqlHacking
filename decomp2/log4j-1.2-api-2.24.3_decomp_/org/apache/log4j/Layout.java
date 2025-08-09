package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.util.Strings;

public abstract class Layout {
   public static final String LINE_SEP;
   public static final int LINE_SEP_LEN;

   public abstract String format(LoggingEvent event);

   public String getContentType() {
      return "text/plain";
   }

   public String getHeader() {
      return null;
   }

   public String getFooter() {
      return null;
   }

   public abstract boolean ignoresThrowable();

   static {
      LINE_SEP = Strings.LINE_SEPARATOR;
      LINE_SEP_LEN = Strings.LINE_SEPARATOR.length();
   }
}
