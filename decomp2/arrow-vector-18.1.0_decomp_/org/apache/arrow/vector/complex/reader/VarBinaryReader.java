package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.VarBinaryWriter;
import org.apache.arrow.vector.holders.NullableVarBinaryHolder;
import org.apache.arrow.vector.holders.VarBinaryHolder;

public interface VarBinaryReader extends BaseReader {
   void read(VarBinaryHolder var1);

   void read(NullableVarBinaryHolder var1);

   Object readObject();

   byte[] readByteArray();

   boolean isSet();

   void copyAsValue(VarBinaryWriter var1);

   void copyAsField(String var1, VarBinaryWriter var2);
}
