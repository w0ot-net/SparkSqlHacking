package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TinyIntWriter;
import org.apache.arrow.vector.holders.NullableTinyIntHolder;
import org.apache.arrow.vector.holders.TinyIntHolder;

public interface TinyIntReader extends BaseReader {
   void read(TinyIntHolder var1);

   void read(NullableTinyIntHolder var1);

   Object readObject();

   Byte readByte();

   boolean isSet();

   void copyAsValue(TinyIntWriter var1);

   void copyAsField(String var1, TinyIntWriter var2);
}
