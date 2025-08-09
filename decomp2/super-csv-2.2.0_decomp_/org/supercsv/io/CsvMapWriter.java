package org.supercsv.io;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.Util;

public class CsvMapWriter extends AbstractCsvWriter implements ICsvMapWriter {
   private final List processedColumns = new ArrayList();

   public CsvMapWriter(Writer writer, CsvPreference preference) {
      super(writer, preference);
   }

   public void write(Map values, String... nameMapping) throws IOException {
      super.incrementRowAndLineNo();
      super.writeRow(Util.filterMapToObjectArray(values, nameMapping));
   }

   public void write(Map values, String[] nameMapping, CellProcessor[] processors) throws IOException {
      super.incrementRowAndLineNo();
      Util.executeCellProcessors(this.processedColumns, Util.filterMapToList(values, nameMapping), processors, this.getLineNumber(), this.getRowNumber());
      super.writeRow(this.processedColumns);
   }
}
