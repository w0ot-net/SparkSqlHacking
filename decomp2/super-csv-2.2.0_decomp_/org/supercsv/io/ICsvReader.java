package org.supercsv.io;

import java.io.Closeable;
import java.io.IOException;

public interface ICsvReader extends Closeable {
   String get(int var1);

   String[] getHeader(boolean var1) throws IOException;

   int getLineNumber();

   String getUntokenizedRow();

   int getRowNumber();

   int length();
}
