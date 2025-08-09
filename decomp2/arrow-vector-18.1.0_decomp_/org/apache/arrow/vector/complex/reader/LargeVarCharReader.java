package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.LargeVarCharWriter;
import org.apache.arrow.vector.holders.LargeVarCharHolder;
import org.apache.arrow.vector.holders.NullableLargeVarCharHolder;
import org.apache.arrow.vector.util.Text;

public interface LargeVarCharReader extends BaseReader {
   void read(LargeVarCharHolder var1);

   void read(NullableLargeVarCharHolder var1);

   Object readObject();

   Text readText();

   boolean isSet();

   void copyAsValue(LargeVarCharWriter var1);

   void copyAsField(String var1, LargeVarCharWriter var2);
}
