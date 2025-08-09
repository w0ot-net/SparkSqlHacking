package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TimeStampSecTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampSecTZHolder;
import org.apache.arrow.vector.holders.TimeStampSecTZHolder;

public interface TimeStampSecTZReader extends BaseReader {
   void read(TimeStampSecTZHolder var1);

   void read(NullableTimeStampSecTZHolder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(TimeStampSecTZWriter var1);

   void copyAsField(String var1, TimeStampSecTZWriter var2);
}
