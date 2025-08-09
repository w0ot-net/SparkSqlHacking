package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TimeSecWriter;
import org.apache.arrow.vector.holders.NullableTimeSecHolder;
import org.apache.arrow.vector.holders.TimeSecHolder;

public interface TimeSecReader extends BaseReader {
   void read(TimeSecHolder var1);

   void read(NullableTimeSecHolder var1);

   Object readObject();

   Integer readInteger();

   boolean isSet();

   void copyAsValue(TimeSecWriter var1);

   void copyAsField(String var1, TimeSecWriter var2);
}
