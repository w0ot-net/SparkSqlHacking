package org.apache.logging.log4j.core.pattern;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Plugin(
   name = "LoggerPatternConverter",
   category = "Converter"
)
@ConverterKeys({"c", "logger"})
@PerformanceSensitive({"allocation"})
public final class LoggerPatternConverter extends NamePatternConverter {
   private static final LoggerPatternConverter INSTANCE = new LoggerPatternConverter((String[])null);

   private LoggerPatternConverter(final String[] options) {
      super("Logger", "logger", options);
   }

   public static LoggerPatternConverter newInstance(final String[] options) {
      return options != null && options.length != 0 ? new LoggerPatternConverter(options) : INSTANCE;
   }

   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      this.abbreviate(event.getLoggerName(), toAppendTo);
   }
}
