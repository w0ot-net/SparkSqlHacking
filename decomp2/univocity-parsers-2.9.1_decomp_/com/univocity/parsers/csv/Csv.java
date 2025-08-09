package com.univocity.parsers.csv;

public class Csv {
   public static CsvParserSettings parseExcel() {
      CsvParserSettings settings = new CsvParserSettings();
      ((CsvFormat)settings.getFormat()).setLineSeparator("\r\n");
      ((CsvFormat)settings.getFormat()).setComment('\u0000');
      settings.setParseUnescapedQuotes(false);
      settings.setSkipEmptyLines(false);
      settings.trimValues(false);
      return settings;
   }

   public static CsvParserSettings parseRfc4180() {
      CsvParserSettings settings = parseExcel();
      settings.setNormalizeLineEndingsWithinQuotes(false);
      return settings;
   }

   public static CsvWriterSettings writeExcel() {
      CsvWriterSettings settings = new CsvWriterSettings();
      ((CsvFormat)settings.getFormat()).setLineSeparator("\r\n");
      ((CsvFormat)settings.getFormat()).setComment('\u0000');
      settings.setEmptyValue((String)null);
      settings.setSkipEmptyLines(false);
      settings.trimValues(false);
      return settings;
   }

   public static CsvWriterSettings writeRfc4180() {
      CsvWriterSettings settings = writeExcel();
      settings.setNormalizeLineEndingsWithinQuotes(false);
      settings.setQuoteEscapingEnabled(true);
      return settings;
   }
}
