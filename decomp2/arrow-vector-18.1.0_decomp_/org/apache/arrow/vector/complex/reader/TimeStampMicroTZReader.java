package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TimeStampMicroTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMicroTZHolder;
import org.apache.arrow.vector.holders.TimeStampMicroTZHolder;

public interface TimeStampMicroTZReader extends BaseReader {
   void read(TimeStampMicroTZHolder var1);

   void read(NullableTimeStampMicroTZHolder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(TimeStampMicroTZWriter var1);

   void copyAsField(String var1, TimeStampMicroTZWriter var2);
}
