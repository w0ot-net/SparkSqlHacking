package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.SmallIntWriter;
import org.apache.arrow.vector.holders.NullableSmallIntHolder;
import org.apache.arrow.vector.holders.SmallIntHolder;

public interface SmallIntReader extends BaseReader {
   void read(SmallIntHolder var1);

   void read(NullableSmallIntHolder var1);

   Object readObject();

   Short readShort();

   boolean isSet();

   void copyAsValue(SmallIntWriter var1);

   void copyAsField(String var1, SmallIntWriter var2);
}
