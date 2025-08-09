package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.FixedSizeBinaryWriter;
import org.apache.arrow.vector.holders.FixedSizeBinaryHolder;
import org.apache.arrow.vector.holders.NullableFixedSizeBinaryHolder;

public interface FixedSizeBinaryReader extends BaseReader {
   void read(FixedSizeBinaryHolder var1);

   void read(NullableFixedSizeBinaryHolder var1);

   Object readObject();

   byte[] readByteArray();

   boolean isSet();

   void copyAsValue(FixedSizeBinaryWriter var1);

   void copyAsField(String var1, FixedSizeBinaryWriter var2);
}
