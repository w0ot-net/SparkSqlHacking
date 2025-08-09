package org.supercsv.io;

import java.io.IOException;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;

public interface ICsvListReader extends ICsvReader {
   List read() throws IOException;

   List read(CellProcessor... var1) throws IOException;

   List executeProcessors(CellProcessor... var1);
}
