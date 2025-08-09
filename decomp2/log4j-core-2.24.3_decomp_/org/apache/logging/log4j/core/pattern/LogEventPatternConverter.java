package org.apache.logging.log4j.core.pattern;

import org.apache.logging.log4j.core.LogEvent;

public abstract class LogEventPatternConverter extends AbstractPatternConverter {
   protected LogEventPatternConverter(final String name, final String style) {
      super(name, style);
   }

   public abstract void format(final LogEvent event, final StringBuilder toAppendTo);

   public void format(final Object obj, final StringBuilder output) {
      if (obj instanceof LogEvent) {
         this.format((LogEvent)obj, output);
      }

   }

   public boolean handlesThrowable() {
      return false;
   }

   public boolean isVariable() {
      return true;
   }

   public String emptyVariableOutput() {
      return "";
   }
}
