package org.apache.log4j.pattern;

import org.apache.log4j.helpers.OptionConverter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

@Plugin(
   name = "Log4j1LevelPatternConverter",
   category = "Converter"
)
@ConverterKeys({"v1Level"})
public class Log4j1LevelPatternConverter extends LogEventPatternConverter {
   private static final Log4j1LevelPatternConverter INSTANCE = new Log4j1LevelPatternConverter();

   public static Log4j1LevelPatternConverter newInstance(final String[] options) {
      return INSTANCE;
   }

   private Log4j1LevelPatternConverter() {
      super("Log4j1Level", "v1Level");
   }

   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      toAppendTo.append(OptionConverter.convertLevel(event.getLevel()).toString());
   }
}
