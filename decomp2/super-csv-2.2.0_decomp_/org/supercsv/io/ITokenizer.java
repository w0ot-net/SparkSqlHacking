package org.supercsv.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface ITokenizer extends Closeable {
   int getLineNumber();

   String getUntokenizedRow();

   boolean readColumns(List var1) throws IOException;
}
