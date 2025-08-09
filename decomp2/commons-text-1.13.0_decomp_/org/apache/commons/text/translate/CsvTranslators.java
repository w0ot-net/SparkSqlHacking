package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.StringUtils;

public final class CsvTranslators {
   private static final char CSV_DELIMITER = ',';
   private static final char CSV_QUOTE = '"';
   private static final String CSV_QUOTE_STR = String.valueOf('"');
   private static final String CSV_ESCAPED_QUOTE_STR;
   private static final char[] CSV_SEARCH_CHARS;

   private CsvTranslators() {
   }

   static {
      CSV_ESCAPED_QUOTE_STR = CSV_QUOTE_STR + CSV_QUOTE_STR;
      CSV_SEARCH_CHARS = new char[]{',', '"', '\r', '\n'};
   }

   public static class CsvEscaper extends SinglePassTranslator {
      void translateWhole(CharSequence input, Writer writer) throws IOException {
         String inputSting = input.toString();
         if (StringUtils.containsNone(inputSting, CsvTranslators.CSV_SEARCH_CHARS)) {
            writer.write(inputSting);
         } else {
            writer.write(34);
            writer.write(StringUtils.replace(inputSting, CsvTranslators.CSV_QUOTE_STR, CsvTranslators.CSV_ESCAPED_QUOTE_STR));
            writer.write(34);
         }

      }
   }

   public static class CsvUnescaper extends SinglePassTranslator {
      void translateWhole(CharSequence input, Writer writer) throws IOException {
         if (input.charAt(0) == '"' && input.charAt(input.length() - 1) == '"') {
            String quoteless = input.subSequence(1, input.length() - 1).toString();
            if (StringUtils.containsAny(quoteless, CsvTranslators.CSV_SEARCH_CHARS)) {
               writer.write(StringUtils.replace(quoteless, CsvTranslators.CSV_ESCAPED_QUOTE_STR, CsvTranslators.CSV_QUOTE_STR));
            } else {
               writer.write(quoteless);
            }

         } else {
            writer.write(input.toString());
         }
      }
   }
}
