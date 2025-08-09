package com.univocity.parsers.fixed;

import com.univocity.parsers.common.routine.AbstractRoutines;
import java.io.Writer;

public class FixedWidthRoutines extends AbstractRoutines {
   public FixedWidthRoutines() {
      this((FixedWidthParserSettings)null, (FixedWidthWriterSettings)null);
   }

   public FixedWidthRoutines(FixedWidthParserSettings parserSettings) {
      this(parserSettings, (FixedWidthWriterSettings)null);
   }

   public FixedWidthRoutines(FixedWidthWriterSettings writerSettings) {
      this((FixedWidthParserSettings)null, writerSettings);
   }

   public FixedWidthRoutines(FixedWidthParserSettings parserSettings, FixedWidthWriterSettings writerSettings) {
      super("Fixed-width parsing/writing routine", parserSettings, writerSettings);
   }

   protected void adjustColumnLengths(String[] headers, int[] lengths) {
      if (((FixedWidthWriterSettings)this.getWriterSettings()).getFieldLengths() == null) {
         ((FixedWidthWriterSettings)this.getWriterSettings()).setFieldLengths(new FixedWidthFields(headers, lengths));
      }

   }

   protected FixedWidthParser createParser(FixedWidthParserSettings parserSettings) {
      return new FixedWidthParser(parserSettings);
   }

   protected FixedWidthWriter createWriter(Writer output, FixedWidthWriterSettings writerSettings) {
      return new FixedWidthWriter(output, writerSettings);
   }

   protected FixedWidthParserSettings createDefaultParserSettings() {
      return new FixedWidthParserSettings();
   }

   protected FixedWidthWriterSettings createDefaultWriterSettings() {
      return new FixedWidthWriterSettings();
   }
}
