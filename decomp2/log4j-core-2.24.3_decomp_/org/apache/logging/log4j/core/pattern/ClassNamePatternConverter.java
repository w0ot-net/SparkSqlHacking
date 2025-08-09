package org.apache.logging.log4j.core.pattern;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.impl.LocationAware;

@Plugin(
   name = "ClassNamePatternConverter",
   category = "Converter"
)
@ConverterKeys({"C", "class"})
public final class ClassNamePatternConverter extends NamePatternConverter implements LocationAware {
   private static final String NA = "?";

   private ClassNamePatternConverter(final String[] options) {
      super("Class Name", "class name", options);
   }

   public static ClassNamePatternConverter newInstance(final String[] options) {
      return new ClassNamePatternConverter(options);
   }

   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      StackTraceElement element = event.getSource();
      if (element == null) {
         toAppendTo.append("?");
      } else {
         this.abbreviate(element.getClassName(), toAppendTo);
      }

   }

   public boolean requiresLocation() {
      return true;
   }
}
