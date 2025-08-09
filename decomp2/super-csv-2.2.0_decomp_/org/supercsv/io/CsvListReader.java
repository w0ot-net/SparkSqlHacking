package org.supercsv.io;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;

public class CsvListReader extends AbstractCsvReader implements ICsvListReader {
   public CsvListReader(Reader reader, CsvPreference preferences) {
      super(reader, preferences);
   }

   public CsvListReader(ITokenizer tokenizer, CsvPreference preferences) {
      super(tokenizer, preferences);
   }

   public List read() throws IOException {
      return this.readRow() ? new ArrayList(this.getColumns()) : null;
   }

   public List read(CellProcessor... processors) throws IOException {
      if (processors == null) {
         throw new NullPointerException("processors should not be null");
      } else {
         return this.readRow() ? this.executeProcessors(processors) : null;
      }
   }

   public List executeProcessors(CellProcessor... processors) {
      return super.executeProcessors(new ArrayList(this.getColumns().size()), processors);
   }
}
