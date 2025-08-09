package org.apache.logging.log4j.core.pattern;

import java.util.Objects;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.message.MapMessage.MapFormat;

@Plugin(
   name = "MapPatternConverter",
   category = "Converter"
)
@ConverterKeys({"K", "map", "MAP"})
public final class MapPatternConverter extends LogEventPatternConverter {
   private static final String JAVA_UNQUOTED;
   private final String key;
   private final String[] format;

   private MapPatternConverter(final String[] options, final String... format) {
      super(options != null && options.length > 0 ? "MAP{" + options[0] + '}' : "MAP", "map");
      this.key = options != null && options.length > 0 ? options[0] : null;
      this.format = format;
   }

   public static MapPatternConverter newInstance(final String[] options) {
      return new MapPatternConverter(options, new String[]{JAVA_UNQUOTED});
   }

   public static MapPatternConverter newInstance(final String[] options, final MapMessage.MapFormat format) {
      return new MapPatternConverter(options, new String[]{Objects.toString(format, JAVA_UNQUOTED)});
   }

   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      if (event.getMessage() instanceof MapMessage) {
         MapMessage msg = (MapMessage)event.getMessage();
         if (this.key == null) {
            msg.formatTo(this.format, toAppendTo);
         } else {
            String val = msg.get(this.key);
            if (val != null) {
               toAppendTo.append(val);
            }
         }

      }
   }

   static {
      JAVA_UNQUOTED = MapFormat.JAVA_UNQUOTED.name();
   }
}
