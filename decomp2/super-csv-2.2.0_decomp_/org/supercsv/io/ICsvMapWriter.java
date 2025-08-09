package org.supercsv.io;

import java.io.IOException;
import java.util.Map;
import org.supercsv.cellprocessor.ift.CellProcessor;

public interface ICsvMapWriter extends ICsvWriter {
   void write(Map var1, String... var2) throws IOException;

   void write(Map var1, String[] var2, CellProcessor[] var3) throws IOException;
}
