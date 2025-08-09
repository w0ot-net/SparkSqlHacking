package org.apache.arrow.vector.complex.reader;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeStampSecWriter;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.TimeStampSecHolder;

public interface TimeStampSecReader extends BaseReader {
   void read(TimeStampSecHolder var1);

   void read(NullableTimeStampSecHolder var1);

   Object readObject();

   LocalDateTime readLocalDateTime();

   boolean isSet();

   void copyAsValue(TimeStampSecWriter var1);

   void copyAsField(String var1, TimeStampSecWriter var2);
}
