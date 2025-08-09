package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.ViewVarBinaryWriter;
import org.apache.arrow.vector.holders.NullableViewVarBinaryHolder;
import org.apache.arrow.vector.holders.ViewVarBinaryHolder;

public interface ViewVarBinaryReader extends BaseReader {
   void read(ViewVarBinaryHolder var1);

   void read(NullableViewVarBinaryHolder var1);

   Object readObject();

   byte[] readByteArray();

   boolean isSet();

   void copyAsValue(ViewVarBinaryWriter var1);

   void copyAsField(String var1, ViewVarBinaryWriter var2);
}
