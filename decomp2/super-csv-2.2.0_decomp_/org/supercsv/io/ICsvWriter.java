package org.supercsv.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public interface ICsvWriter extends Closeable, Flushable {
   int getLineNumber();

   int getRowNumber();

   void writeComment(String var1) throws IOException;

   void writeHeader(String... var1) throws IOException;
}
