package org.supercsv.io;

import java.io.IOException;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;

public interface ICsvListWriter extends ICsvWriter {
   void write(List var1) throws IOException;

   void write(List var1, CellProcessor[] var2) throws IOException;

   void write(Object... var1) throws IOException;

   void write(String... var1) throws IOException;
}
