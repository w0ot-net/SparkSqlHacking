package org.apache.arrow.vector.complex.reader;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeStampMicroWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMicroHolder;
import org.apache.arrow.vector.holders.TimeStampMicroHolder;

public interface TimeStampMicroReader extends BaseReader {
   void read(TimeStampMicroHolder var1);

   void read(NullableTimeStampMicroHolder var1);

   Object readObject();

   LocalDateTime readLocalDateTime();

   boolean isSet();

   void copyAsValue(TimeStampMicroWriter var1);

   void copyAsField(String var1, TimeStampMicroWriter var2);
}
