package org.apache.arrow.vector.complex.reader;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeStampNanoWriter;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.holders.TimeStampNanoHolder;

public interface TimeStampNanoReader extends BaseReader {
   void read(TimeStampNanoHolder var1);

   void read(NullableTimeStampNanoHolder var1);

   Object readObject();

   LocalDateTime readLocalDateTime();

   boolean isSet();

   void copyAsValue(TimeStampNanoWriter var1);

   void copyAsField(String var1, TimeStampNanoWriter var2);
}
