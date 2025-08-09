package org.supercsv.io;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.Util;

public class CsvListWriter extends AbstractCsvWriter implements ICsvListWriter {
   private final List processedColumns = new ArrayList();

   public CsvListWriter(Writer writer, CsvPreference preference) {
      super(writer, preference);
   }

   public void write(List columns, CellProcessor[] processors) throws IOException {
      super.incrementRowAndLineNo();
      Util.executeCellProcessors(this.processedColumns, columns, processors, this.getLineNumber(), this.getRowNumber());
      super.writeRow(this.processedColumns);
   }

   public void write(List columns) throws IOException {
      super.incrementRowAndLineNo();
      super.writeRow(columns);
   }

   public void write(Object... columns) throws IOException {
      super.incrementRowAndLineNo();
      super.writeRow(columns);
   }

   public void write(String... columns) throws IOException {
      super.incrementRowAndLineNo();
      super.writeRow(columns);
   }
}
