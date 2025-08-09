package com.univocity.parsers.tsv;

import com.univocity.parsers.common.routine.AbstractRoutines;
import java.io.Writer;

public class TsvRoutines extends AbstractRoutines {
   public TsvRoutines() {
      this((TsvParserSettings)null, (TsvWriterSettings)null);
   }

   public TsvRoutines(TsvParserSettings parserSettings) {
      this(parserSettings, (TsvWriterSettings)null);
   }

   public TsvRoutines(TsvWriterSettings writerSettings) {
      this((TsvParserSettings)null, writerSettings);
   }

   public TsvRoutines(TsvParserSettings parserSettings, TsvWriterSettings writerSettings) {
      super("TSV parsing/writing routine", parserSettings, writerSettings);
   }

   protected TsvParser createParser(TsvParserSettings parserSettings) {
      return new TsvParser(parserSettings);
   }

   protected TsvWriter createWriter(Writer output, TsvWriterSettings writerSettings) {
      return new TsvWriter(output, writerSettings);
   }

   protected TsvParserSettings createDefaultParserSettings() {
      return new TsvParserSettings();
   }

   protected TsvWriterSettings createDefaultWriterSettings() {
      return new TsvWriterSettings();
   }
}
