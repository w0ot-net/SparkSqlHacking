package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.ViewVarCharHolder;
import org.apache.arrow.vector.util.Text;

public interface ViewVarCharWriter extends BaseWriter {
   void write(ViewVarCharHolder var1);

   void writeViewVarChar(int var1, int var2, ArrowBuf var3);

   void writeViewVarChar(Text var1);

   void writeViewVarChar(String var1);
}
