package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TimeStampNanoTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampNanoTZHolder;
import org.apache.arrow.vector.holders.TimeStampNanoTZHolder;

public interface TimeStampNanoTZReader extends BaseReader {
   void read(TimeStampNanoTZHolder var1);

   void read(NullableTimeStampNanoTZHolder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(TimeStampNanoTZWriter var1);

   void copyAsField(String var1, TimeStampNanoTZWriter var2);
}
