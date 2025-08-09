package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.ViewVarCharWriter;
import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.holders.ViewVarCharHolder;
import org.apache.arrow.vector.util.Text;

public interface ViewVarCharReader extends BaseReader {
   void read(ViewVarCharHolder var1);

   void read(NullableViewVarCharHolder var1);

   Object readObject();

   Text readText();

   boolean isSet();

   void copyAsValue(ViewVarCharWriter var1);

   void copyAsField(String var1, ViewVarCharWriter var2);
}
