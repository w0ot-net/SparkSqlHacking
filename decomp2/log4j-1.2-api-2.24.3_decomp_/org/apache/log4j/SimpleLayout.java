package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;

public class SimpleLayout extends Layout {
   public String format(final LoggingEvent theEvent) {
      return "";
   }

   public boolean ignoresThrowable() {
      return true;
   }
}
