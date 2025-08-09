package org.apache.arrow.vector.complex.reader;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeStampMilliWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.TimeStampMilliHolder;

public interface TimeStampMilliReader extends BaseReader {
   void read(TimeStampMilliHolder var1);

   void read(NullableTimeStampMilliHolder var1);

   Object readObject();

   LocalDateTime readLocalDateTime();

   boolean isSet();

   void copyAsValue(TimeStampMilliWriter var1);

   void copyAsField(String var1, TimeStampMilliWriter var2);
}
