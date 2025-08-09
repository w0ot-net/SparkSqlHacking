package org.supercsv.io;

import java.io.IOException;
import org.supercsv.cellprocessor.ift.CellProcessor;

public interface ICsvBeanReader extends ICsvReader {
   Object read(Class var1, String... var2) throws IOException;

   Object read(Object var1, String... var2) throws IOException;

   Object read(Class var1, String[] var2, CellProcessor... var3) throws IOException;

   Object read(Object var1, String[] var2, CellProcessor... var3) throws IOException;
}
