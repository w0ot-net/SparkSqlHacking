package org.apache.logging.log4j.core.pattern;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Plugin(
   name = "ThreadIdPatternConverter",
   category = "Converter"
)
@ConverterKeys({"T", "tid", "threadId"})
@PerformanceSensitive({"allocation"})
public final class ThreadIdPatternConverter extends LogEventPatternConverter {
   private static final ThreadIdPatternConverter INSTANCE = new ThreadIdPatternConverter();

   private ThreadIdPatternConverter() {
      super("ThreadId", "threadId");
   }

   public static ThreadIdPatternConverter newInstance(final String[] options) {
      return INSTANCE;
   }

   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      toAppendTo.append(event.getThreadId());
   }
}
