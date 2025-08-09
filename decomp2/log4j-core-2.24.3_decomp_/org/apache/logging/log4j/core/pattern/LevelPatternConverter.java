package org.apache.logging.log4j.core.pattern;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.core.util.Patterns;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "LevelPatternConverter",
   category = "Converter"
)
@ConverterKeys({"p", "level"})
@PerformanceSensitive({"allocation"})
public class LevelPatternConverter extends LogEventPatternConverter {
   private static final String OPTION_LENGTH = "length";
   private static final String OPTION_LOWER = "lowerCase";
   private static final LevelPatternConverter INSTANCE = new SimpleLevelPatternConverter();

   private LevelPatternConverter() {
      super("Level", "level");
   }

   public static LevelPatternConverter newInstance(final String[] options) {
      if (options != null && options.length != 0) {
         Map<Level, String> levelMap = new HashMap();
         int length = Integer.MAX_VALUE;
         boolean lowerCase = false;
         String[] definitions = options[0].split(Patterns.COMMA_SEPARATOR);

         for(String def : definitions) {
            String[] pair = def.split("=");
            if (pair != null && pair.length == 2) {
               String key = pair[0].trim();
               String value = pair[1].trim();
               if ("length".equalsIgnoreCase(key)) {
                  length = Integers.parseInt(value);
               } else if ("lowerCase".equalsIgnoreCase(key)) {
                  lowerCase = Boolean.parseBoolean(value);
               } else {
                  Level level = Level.toLevel(key, (Level)null);
                  if (level == null) {
                     LOGGER.error("Invalid Level {}", key);
                  } else {
                     levelMap.put(level, value);
                  }
               }
            } else {
               LOGGER.error("Invalid option {}", def);
            }
         }

         if (levelMap.isEmpty() && length == Integer.MAX_VALUE && !lowerCase) {
            return INSTANCE;
         } else {
            for(Level level : Level.values()) {
               if (!levelMap.containsKey(level)) {
                  String left = left(level, length);
                  levelMap.put(level, lowerCase ? Strings.toRootLowerCase(left) : left);
               }
            }

            return new LevelMapLevelPatternConverter(levelMap);
         }
      } else {
         return INSTANCE;
      }
   }

   private static String left(final Level level, final int length) {
      String string = level.toString();
      return length >= string.length() ? string : string.substring(0, length);
   }

   public void format(final LogEvent event, final StringBuilder output) {
      throw new UnsupportedOperationException("Overridden by subclasses");
   }

   public String getStyleClass(final Object e) {
      return e instanceof LogEvent ? "level " + Strings.toRootLowerCase(((LogEvent)e).getLevel().name()) : "level";
   }

   private static final class SimpleLevelPatternConverter extends LevelPatternConverter {
      private SimpleLevelPatternConverter() {
      }

      public void format(final LogEvent event, final StringBuilder output) {
         output.append(event.getLevel());
      }
   }

   private static final class LevelMapLevelPatternConverter extends LevelPatternConverter {
      private final Map levelMap;

      private LevelMapLevelPatternConverter(final Map levelMap) {
         this.levelMap = levelMap;
      }

      public void format(final LogEvent event, final StringBuilder output) {
         output.append((String)this.levelMap.get(event.getLevel()));
      }
   }
}
