package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.LargeVarBinaryWriter;
import org.apache.arrow.vector.holders.LargeVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableLargeVarBinaryHolder;

public interface LargeVarBinaryReader extends BaseReader {
   void read(LargeVarBinaryHolder var1);

   void read(NullableLargeVarBinaryHolder var1);

   Object readObject();

   byte[] readByteArray();

   boolean isSet();

   void copyAsValue(LargeVarBinaryWriter var1);

   void copyAsField(String var1, LargeVarBinaryWriter var2);
}
