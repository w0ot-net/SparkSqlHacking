package org.apache.logging.log4j.core.pattern;

import java.util.List;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.util.EnglishEnums;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringBuilders;

@Plugin(
   name = "encode",
   category = "Converter"
)
@ConverterKeys({"enc", "encode"})
@PerformanceSensitive({"allocation"})
public final class EncodingPatternConverter extends LogEventPatternConverter {
   private final List formatters;
   private final EscapeFormat escapeFormat;

   private EncodingPatternConverter(final List formatters, final EscapeFormat escapeFormat) {
      super("encode", "encode");
      this.formatters = formatters;
      this.escapeFormat = escapeFormat;
   }

   public boolean handlesThrowable() {
      return this.formatters != null && this.formatters.stream().map(PatternFormatter::getConverter).anyMatch(LogEventPatternConverter::handlesThrowable);
   }

   public static EncodingPatternConverter newInstance(final Configuration config, final String[] options) {
      if (options.length <= 2 && options.length != 0) {
         if (options[0] == null) {
            LOGGER.error("No pattern supplied on escape");
            return null;
         } else {
            EscapeFormat escapeFormat = options.length < 2 ? EncodingPatternConverter.EscapeFormat.HTML : (EscapeFormat)EnglishEnums.valueOf(EscapeFormat.class, options[1], EncodingPatternConverter.EscapeFormat.HTML);
            PatternParser parser = PatternLayout.createPatternParser(config);
            List<PatternFormatter> formatters = parser.parse(options[0]);
            return new EncodingPatternConverter(formatters, escapeFormat);
         }
      } else {
         LOGGER.error("Incorrect number of options on escape. Expected 1 or 2, but received {}", options.length);
         return null;
      }
   }

   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      int start = toAppendTo.length();

      for(int i = 0; i < this.formatters.size(); ++i) {
         ((PatternFormatter)this.formatters.get(i)).format(event, toAppendTo);
      }

      this.escapeFormat.escape(toAppendTo, start);
   }

   private static enum EscapeFormat {
      HTML {
         void escape(final StringBuilder toAppendTo, final int start) {
            int origLength = toAppendTo.length();
            int firstSpecialChar = origLength;

            for(int i = origLength - 1; i >= start; --i) {
               char c = toAppendTo.charAt(i);
               String escaped = this.escapeChar(c);
               if (escaped != null) {
                  firstSpecialChar = i;

                  for(int j = 0; j < escaped.length() - 1; ++j) {
                     toAppendTo.append(' ');
                  }
               }
            }

            int i = origLength - 1;

            for(int j = toAppendTo.length(); i >= firstSpecialChar; --i) {
               char c = toAppendTo.charAt(i);
               String escaped = this.escapeChar(c);
               if (escaped == null) {
                  --j;
                  toAppendTo.setCharAt(j, c);
               } else {
                  toAppendTo.replace(j - escaped.length(), j, escaped);
                  j -= escaped.length();
               }
            }

         }

         private String escapeChar(final char c) {
            switch (c) {
               case '\n':
                  return "\\n";
               case '\r':
                  return "\\r";
               case '"':
                  return "&quot;";
               case '&':
                  return "&amp;";
               case '\'':
                  return "&apos;";
               case '/':
                  return "&#x2F;";
               case '<':
                  return "&lt;";
               case '>':
                  return "&gt;";
               default:
                  return null;
            }
         }
      },
      JSON {
         void escape(final StringBuilder toAppendTo, final int start) {
            StringBuilders.escapeJson(toAppendTo, start);
         }
      },
      CRLF {
         void escape(final StringBuilder toAppendTo, final int start) {
            int origLength = toAppendTo.length();
            int firstSpecialChar = origLength;

            for(int i = origLength - 1; i >= start; --i) {
               char c = toAppendTo.charAt(i);
               if (c == '\r' || c == '\n') {
                  firstSpecialChar = i;
                  toAppendTo.append(' ');
               }
            }

            int i = origLength - 1;

            for(int j = toAppendTo.length(); i >= firstSpecialChar; --i) {
               char c = toAppendTo.charAt(i);
               switch (c) {
                  case '\n':
                     --j;
                     toAppendTo.setCharAt(j, 'n');
                     --j;
                     toAppendTo.setCharAt(j, '\\');
                     break;
                  case '\r':
                     --j;
                     toAppendTo.setCharAt(j, 'r');
                     --j;
                     toAppendTo.setCharAt(j, '\\');
                     break;
                  default:
                     --j;
                     toAppendTo.setCharAt(j, c);
               }
            }

         }
      },
      XML {
         void escape(final StringBuilder toAppendTo, final int start) {
            StringBuilders.escapeXml(toAppendTo, start);
         }
      };

      private EscapeFormat() {
      }

      abstract void escape(final StringBuilder toAppendTo, final int start);

      // $FF: synthetic method
      private static EscapeFormat[] $values() {
         return new EscapeFormat[]{HTML, JSON, CRLF, XML};
      }
   }
}
