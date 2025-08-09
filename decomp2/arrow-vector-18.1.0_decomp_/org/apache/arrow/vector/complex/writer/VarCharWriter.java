package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.VarCharHolder;
import org.apache.arrow.vector.util.Text;

public interface VarCharWriter extends BaseWriter {
   void write(VarCharHolder var1);

   void writeVarChar(int var1, int var2, ArrowBuf var3);

   void writeVarChar(Text var1);

   void writeVarChar(String var1);
}
