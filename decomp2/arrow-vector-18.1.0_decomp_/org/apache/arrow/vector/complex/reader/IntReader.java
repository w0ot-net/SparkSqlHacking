package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.IntWriter;
import org.apache.arrow.vector.holders.IntHolder;
import org.apache.arrow.vector.holders.NullableIntHolder;

public interface IntReader extends BaseReader {
   void read(IntHolder var1);

   void read(NullableIntHolder var1);

   Object readObject();

   Integer readInteger();

   boolean isSet();

   void copyAsValue(IntWriter var1);

   void copyAsField(String var1, IntWriter var2);
}
