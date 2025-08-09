package org.supercsv.io;

import java.io.IOException;
import java.util.Map;
import org.supercsv.cellprocessor.ift.CellProcessor;

public interface ICsvMapReader extends ICsvReader {
   Map read(String... var1) throws IOException;

   Map read(String[] var1, CellProcessor[] var2) throws IOException;
}
