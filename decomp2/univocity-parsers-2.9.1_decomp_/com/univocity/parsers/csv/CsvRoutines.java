package com.univocity.parsers.csv;

import com.univocity.parsers.common.routine.AbstractRoutines;
import java.io.Writer;

public class CsvRoutines extends AbstractRoutines {
   public CsvRoutines() {
      this((CsvParserSettings)null, (CsvWriterSettings)null);
   }

   public CsvRoutines(CsvParserSettings parserSettings) {
      this(parserSettings, (CsvWriterSettings)null);
   }

   public CsvRoutines(CsvWriterSettings writerSettings) {
      this((CsvParserSettings)null, writerSettings);
   }

   public CsvRoutines(CsvParserSettings parserSettings, CsvWriterSettings writerSettings) {
      super("CSV parsing/writing routine", parserSettings, writerSettings);
   }

   protected CsvParser createParser(CsvParserSettings parserSettings) {
      return new CsvParser(parserSettings);
   }

   protected CsvWriter createWriter(Writer output, CsvWriterSettings writerSettings) {
      return new CsvWriter(output, writerSettings);
   }

   protected CsvParserSettings createDefaultParserSettings() {
      return new CsvParserSettings();
   }

   protected CsvWriterSettings createDefaultWriterSettings() {
      return new CsvWriterSettings();
   }
}
