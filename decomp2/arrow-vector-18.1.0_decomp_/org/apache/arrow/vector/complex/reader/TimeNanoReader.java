package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TimeNanoWriter;
import org.apache.arrow.vector.holders.NullableTimeNanoHolder;
import org.apache.arrow.vector.holders.TimeNanoHolder;

public interface TimeNanoReader extends BaseReader {
   void read(TimeNanoHolder var1);

   void read(NullableTimeNanoHolder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(TimeNanoWriter var1);

   void copyAsField(String var1, TimeNanoWriter var2);
}
