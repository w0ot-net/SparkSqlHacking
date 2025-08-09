package org.supercsv.io;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.Util;

public class CsvMapReader extends AbstractCsvReader implements ICsvMapReader {
   public CsvMapReader(Reader reader, CsvPreference preferences) {
      super(reader, preferences);
   }

   public CsvMapReader(ITokenizer tokenizer, CsvPreference preferences) {
      super(tokenizer, preferences);
   }

   public Map read(String... nameMapping) throws IOException {
      if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else if (this.readRow()) {
         Map<String, String> destination = new HashMap();
         Util.filterListToMap(destination, nameMapping, this.getColumns());
         return destination;
      } else {
         return null;
      }
   }

   public Map read(String[] nameMapping, CellProcessor[] processors) throws IOException {
      if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else if (processors == null) {
         throw new NullPointerException("processors should not be null");
      } else if (this.readRow()) {
         List<Object> processedColumns = this.executeProcessors(new ArrayList(this.getColumns().size()), processors);
         Map<String, Object> destination = new HashMap(processedColumns.size());
         Util.filterListToMap(destination, nameMapping, processedColumns);
         return destination;
      } else {
         return null;
      }
   }
}
