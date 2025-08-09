package org.apache.logging.log4j.core.pattern;

import [Ljava.lang.String;;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.core.util.Patterns;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.EnglishEnums;
import org.apache.logging.log4j.util.Strings;

public enum AnsiEscape {
   CSI("\u001b["),
   SUFFIX("m"),
   SEPARATOR(";"),
   NORMAL("0"),
   /** @deprecated */
   BRIGHT("1"),
   BOLD("1"),
   DIM("2"),
   UNDERLINE("3"),
   BLINK("5"),
   REVERSE("7"),
   HIDDEN("8"),
   BLACK("30"),
   FG_BLACK("30"),
   RED("31"),
   FG_RED("31"),
   GREEN("32"),
   FG_GREEN("32"),
   YELLOW("33"),
   FG_YELLOW("33"),
   BLUE("34"),
   FG_BLUE("34"),
   MAGENTA("35"),
   FG_MAGENTA("35"),
   CYAN("36"),
   FG_CYAN("36"),
   WHITE("37"),
   FG_WHITE("37"),
   DEFAULT("39"),
   FG_DEFAULT("39"),
   BG_BLACK("40"),
   BG_RED("41"),
   BG_GREEN("42"),
   BG_YELLOW("43"),
   BG_BLUE("44"),
   BG_MAGENTA("45"),
   BG_CYAN("46"),
   BG_WHITE("47"),
   BRIGHT_BLACK("90"),
   FG_BRIGHT_BLACK("90"),
   BRIGHT_RED("91"),
   FG_BRIGHT_RED("91"),
   BRIGHT_GREEN("92"),
   FG_BRIGHT_GREEN("92"),
   BRIGHT_YELLOW("93"),
   FG_BRIGHT_YELLOW("93"),
   BRIGHT_BLUE("94"),
   FG_BRIGHT_BLUE("94"),
   BRIGHT_MAGENTA("95"),
   FG_BRIGHT_MAGENTA("95"),
   BRIGHT_CYAN("96"),
   FG_BRIGHT_CYAN("96"),
   BRIGHT_WHITE("97"),
   FG_BRIGHT_WHITE("97"),
   BG_BRIGHT_BLACK("100"),
   BG_BRIGHT_RED("101"),
   BG_BRIGHT_GREEN("102"),
   BG_BRIGHT_YELLOW("103"),
   BG_BRIGHT_BLUE("104"),
   BG_BRIGHT_MAGENTA("105"),
   BG_BRIGHT_CYAN("106"),
   BG_BRIGHT_WHITE("107");

   private static final StatusLogger LOGGER = StatusLogger.getLogger();
   private static final String DEFAULT_STYLE = CSI.getCode() + SUFFIX.getCode();
   private final String code;

   private AnsiEscape(final String code) {
      this.code = code;
   }

   public static String getDefaultStyle() {
      return DEFAULT_STYLE;
   }

   public String getCode() {
      return this.code;
   }

   public static Map createMap(final String values, final String[] dontEscapeKeys) {
      return createMap(values.split(Patterns.COMMA_SEPARATOR), dontEscapeKeys);
   }

   public static Map createMap(final String[] values, final String[] dontEscapeKeys) {
      String[] sortedIgnoreKeys = dontEscapeKeys != null ? (String[])((String;)dontEscapeKeys).clone() : Strings.EMPTY_ARRAY;
      Arrays.sort(sortedIgnoreKeys);
      Map<String, String> map = new HashMap();

      for(String string : values) {
         String[] keyValue = string.split(Patterns.toWhitespaceSeparator("="));
         if (keyValue.length > 1) {
            String key = Strings.toRootUpperCase(keyValue[0]);
            String value = keyValue[1];
            boolean escape = Arrays.binarySearch(sortedIgnoreKeys, key) < 0;
            map.put(key, escape ? createSequence(value.split("\\s")) : value);
         } else {
            LOGGER.warn("Syntax error, missing '=': Expected \"{KEY1=VALUE, KEY2=VALUE, ...}");
         }
      }

      return map;
   }

   public static String createSequence(final String... names) {
      if (names == null) {
         return getDefaultStyle();
      } else {
         StringBuilder sb = new StringBuilder(CSI.getCode());
         boolean first = true;

         for(String name : names) {
            try {
               if (!name.startsWith("disableAnsi") && !name.startsWith("noConsoleNoAnsi")) {
                  if (!first) {
                     sb.append(SEPARATOR.getCode());
                  }

                  first = false;
                  String hexColor = null;
                  String trimmedName = Strings.toRootUpperCase(name.trim());
                  if (trimmedName.startsWith("#")) {
                     sb.append("38");
                     sb.append(SEPARATOR.getCode());
                     sb.append("2");
                     sb.append(SEPARATOR.getCode());
                     hexColor = trimmedName;
                  } else if (trimmedName.startsWith("FG_#")) {
                     sb.append("38");
                     sb.append(SEPARATOR.getCode());
                     sb.append("2");
                     sb.append(SEPARATOR.getCode());
                     hexColor = trimmedName.substring(3);
                  } else if (trimmedName.startsWith("BG_#")) {
                     sb.append("48");
                     sb.append(SEPARATOR.getCode());
                     sb.append("2");
                     sb.append(SEPARATOR.getCode());
                     hexColor = trimmedName.substring(3);
                  }

                  if (hexColor != null) {
                     sb.append(Integer.valueOf(hexColor.substring(1, 3), 16));
                     sb.append(SEPARATOR.getCode());
                     sb.append(Integer.valueOf(hexColor.substring(3, 5), 16));
                     sb.append(SEPARATOR.getCode());
                     sb.append(Integer.valueOf(hexColor.substring(5, 7), 16));
                  } else {
                     AnsiEscape escape = (AnsiEscape)EnglishEnums.valueOf(AnsiEscape.class, trimmedName);
                     sb.append(escape.getCode());
                  }
               }
            } catch (Exception ex) {
               StatusLogger.getLogger().warn("The style attribute {} is incorrect.", name, ex);
            }
         }

         sb.append(SUFFIX.getCode());
         return sb.toString();
      }
   }

   // $FF: synthetic method
   private static AnsiEscape[] $values() {
      return new AnsiEscape[]{CSI, SUFFIX, SEPARATOR, NORMAL, BRIGHT, BOLD, DIM, UNDERLINE, BLINK, REVERSE, HIDDEN, BLACK, FG_BLACK, RED, FG_RED, GREEN, FG_GREEN, YELLOW, FG_YELLOW, BLUE, FG_BLUE, MAGENTA, FG_MAGENTA, CYAN, FG_CYAN, WHITE, FG_WHITE, DEFAULT, FG_DEFAULT, BG_BLACK, BG_RED, BG_GREEN, BG_YELLOW, BG_BLUE, BG_MAGENTA, BG_CYAN, BG_WHITE, BRIGHT_BLACK, FG_BRIGHT_BLACK, BRIGHT_RED, FG_BRIGHT_RED, BRIGHT_GREEN, FG_BRIGHT_GREEN, BRIGHT_YELLOW, FG_BRIGHT_YELLOW, BRIGHT_BLUE, FG_BRIGHT_BLUE, BRIGHT_MAGENTA, FG_BRIGHT_MAGENTA, BRIGHT_CYAN, FG_BRIGHT_CYAN, BRIGHT_WHITE, FG_BRIGHT_WHITE, BG_BRIGHT_BLACK, BG_BRIGHT_RED, BG_BRIGHT_GREEN, BG_BRIGHT_YELLOW, BG_BRIGHT_BLUE, BG_BRIGHT_MAGENTA, BG_BRIGHT_CYAN, BG_BRIGHT_WHITE};
   }
}
