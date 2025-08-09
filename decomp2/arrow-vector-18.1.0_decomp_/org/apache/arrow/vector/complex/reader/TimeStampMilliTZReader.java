package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TimeStampMilliTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMilliTZHolder;
import org.apache.arrow.vector.holders.TimeStampMilliTZHolder;

public interface TimeStampMilliTZReader extends BaseReader {
   void read(TimeStampMilliTZHolder var1);

   void read(NullableTimeStampMilliTZHolder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(TimeStampMilliTZWriter var1);

   void copyAsField(String var1, TimeStampMilliTZWriter var2);
}
