package org.supercsv.io;

import java.io.IOException;
import org.supercsv.cellprocessor.ift.CellProcessor;

public interface ICsvBeanWriter extends ICsvWriter {
   void write(Object var1, String... var2) throws IOException;

   void write(Object var1, String[] var2, CellProcessor[] var3) throws IOException;
}
