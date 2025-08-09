package org.apache.arrow.vector.complex.reader;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeMilliWriter;
import org.apache.arrow.vector.holders.NullableTimeMilliHolder;
import org.apache.arrow.vector.holders.TimeMilliHolder;

public interface TimeMilliReader extends BaseReader {
   void read(TimeMilliHolder var1);

   void read(NullableTimeMilliHolder var1);

   Object readObject();

   LocalDateTime readLocalDateTime();

   boolean isSet();

   void copyAsValue(TimeMilliWriter var1);

   void copyAsField(String var1, TimeMilliWriter var2);
}
