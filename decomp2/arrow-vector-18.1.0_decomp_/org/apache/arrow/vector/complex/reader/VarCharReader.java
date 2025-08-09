package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.VarCharWriter;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.VarCharHolder;
import org.apache.arrow.vector.util.Text;

public interface VarCharReader extends BaseReader {
   void read(VarCharHolder var1);

   void read(NullableVarCharHolder var1);

   Object readObject();

   Text readText();

   boolean isSet();

   void copyAsValue(VarCharWriter var1);

   void copyAsField(String var1, VarCharWriter var2);
}
