package org.apache.logging.log4j.core.pattern;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "highlight",
   category = "Converter"
)
@ConverterKeys({"highlight"})
@PerformanceSensitive({"allocation"})
public final class HighlightConverter extends LogEventPatternConverter implements AnsiConverter {
   private static final Map DEFAULT_STYLES = new HashMap();
   private static final Map LOGBACK_STYLES = new HashMap();
   private static final String STYLE_KEY = "STYLE";
   private static final String DISABLE_ANSI_KEY = "DISABLEANSI";
   private static final String NO_CONSOLE_NO_ANSI_KEY = "NOCONSOLENOANSI";
   private static final String STYLE_KEY_DEFAULT = "DEFAULT";
   private static final String STYLE_KEY_LOGBACK = "LOGBACK";
   private static final Map STYLES = new HashMap();
   private final Map levelStyles;
   private final List patternFormatters;
   private final boolean noAnsi;
   private final String defaultStyle;

   private static Map createLevelStyleMap(final String[] options) {
      if (options.length < 2) {
         return DEFAULT_STYLES;
      } else {
         Map<String, String> styles = AnsiEscape.createMap(options[1], new String[]{"STYLE", "DISABLEANSI", "NOCONSOLENOANSI"});
         Map<String, String> levelStyles = new HashMap(DEFAULT_STYLES);

         for(Map.Entry entry : styles.entrySet()) {
            String key = Strings.toRootUpperCase((String)entry.getKey());
            String value = (String)entry.getValue();
            if ("STYLE".equalsIgnoreCase(key)) {
               Map<String, String> enumMap = (Map)STYLES.get(Strings.toRootUpperCase(value));
               if (enumMap == null) {
                  LOGGER.error("Unknown level style: " + value + ". Use one of " + Arrays.toString(STYLES.keySet().toArray()));
               } else {
                  levelStyles.putAll(enumMap);
               }
            } else if (!"DISABLEANSI".equalsIgnoreCase(key) && !"NOCONSOLENOANSI".equalsIgnoreCase(key)) {
               Level level = Level.toLevel(key, (Level)null);
               if (level == null) {
                  LOGGER.warn("Setting style for yet unknown level name {}", key);
                  levelStyles.put(key, value);
               } else {
                  levelStyles.put(level.name(), value);
               }
            }
         }

         return levelStyles;
      }
   }

   public static HighlightConverter newInstance(final Configuration config, final String[] options) {
      if (options.length < 1) {
         LOGGER.error("Incorrect number of options on style. Expected at least 1, received " + options.length);
         return null;
      } else if (options[0] == null) {
         LOGGER.error("No pattern supplied on style");
         return null;
      } else {
         PatternParser parser = PatternLayout.createPatternParser(config);
         List<PatternFormatter> formatters = parser.parse(options[0]);
         boolean disableAnsi = Arrays.toString(options).contains("disableAnsi=true");
         boolean noConsoleNoAnsi = Arrays.toString(options).contains("noConsoleNoAnsi=true");
         boolean hideAnsi = disableAnsi || noConsoleNoAnsi && System.console() == null;
         return new HighlightConverter(formatters, createLevelStyleMap(options), hideAnsi);
      }
   }

   private HighlightConverter(final List patternFormatters, final Map levelStyles, final boolean noAnsi) {
      super("style", "style");
      this.patternFormatters = patternFormatters;
      this.levelStyles = levelStyles;
      this.defaultStyle = AnsiEscape.getDefaultStyle();
      this.noAnsi = noAnsi;
   }

   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      int start = 0;
      int end = 0;
      String levelStyle = (String)this.levelStyles.get(event.getLevel().name());
      if (!this.noAnsi) {
         start = toAppendTo.length();
         if (levelStyle != null) {
            toAppendTo.append(levelStyle);
         }

         end = toAppendTo.length();
      }

      int i = 0;

      for(int size = this.patternFormatters.size(); i < size; ++i) {
         ((PatternFormatter)this.patternFormatters.get(i)).format(event, toAppendTo);
      }

      i = toAppendTo.length() == end;
      if (!this.noAnsi) {
         if (i) {
            toAppendTo.setLength(start);
         } else if (levelStyle != null) {
            toAppendTo.append(this.defaultStyle);
         }
      }

   }

   String getLevelStyle(final Level level) {
      return (String)this.levelStyles.get(level.name());
   }

   public boolean handlesThrowable() {
      for(PatternFormatter formatter : this.patternFormatters) {
         if (formatter.handlesThrowable()) {
            return true;
         }
      }

      return false;
   }

   static {
      DEFAULT_STYLES.put(Level.FATAL.name(), AnsiEscape.createSequence("BRIGHT", "RED"));
      DEFAULT_STYLES.put(Level.ERROR.name(), AnsiEscape.createSequence("BRIGHT", "RED"));
      DEFAULT_STYLES.put(Level.WARN.name(), AnsiEscape.createSequence("YELLOW"));
      DEFAULT_STYLES.put(Level.INFO.name(), AnsiEscape.createSequence("GREEN"));
      DEFAULT_STYLES.put(Level.DEBUG.name(), AnsiEscape.createSequence("CYAN"));
      DEFAULT_STYLES.put(Level.TRACE.name(), AnsiEscape.createSequence("BLACK"));
      LOGBACK_STYLES.put(Level.FATAL.name(), AnsiEscape.createSequence("BLINK", "BRIGHT", "RED"));
      LOGBACK_STYLES.put(Level.ERROR.name(), AnsiEscape.createSequence("BRIGHT", "RED"));
      LOGBACK_STYLES.put(Level.WARN.name(), AnsiEscape.createSequence("RED"));
      LOGBACK_STYLES.put(Level.INFO.name(), AnsiEscape.createSequence("BLUE"));
      LOGBACK_STYLES.put(Level.DEBUG.name(), AnsiEscape.createSequence((String[])null));
      LOGBACK_STYLES.put(Level.TRACE.name(), AnsiEscape.createSequence((String[])null));
      STYLES.put("DEFAULT", DEFAULT_STYLES);
      STYLES.put("LOGBACK", LOGBACK_STYLES);
   }
}
