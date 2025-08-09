package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.LargeVarCharHolder;
import org.apache.arrow.vector.util.Text;

public interface LargeVarCharWriter extends BaseWriter {
   void write(LargeVarCharHolder var1);

   void writeLargeVarChar(long var1, long var3, ArrowBuf var5);

   void writeLargeVarChar(Text var1);

   void writeLargeVarChar(String var1);
}
