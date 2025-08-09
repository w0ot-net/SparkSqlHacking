package org.apache.derby.shared.common.stream;

import java.io.PrintWriter;

public interface HeaderPrintWriter {
   void printlnWithHeader(String var1);

   PrintWriterGetHeader getHeader();

   PrintWriter getPrintWriter();

   String getName();

   void print(String var1);

   void println(String var1);

   void println(Object var1);

   void flush();
}
