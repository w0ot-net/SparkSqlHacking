package org.apache.arrow.vector.complex.reader;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.DateMilliWriter;
import org.apache.arrow.vector.holders.DateMilliHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;

public interface DateMilliReader extends BaseReader {
   void read(DateMilliHolder var1);

   void read(NullableDateMilliHolder var1);

   Object readObject();

   LocalDateTime readLocalDateTime();

   boolean isSet();

   void copyAsValue(DateMilliWriter var1);

   void copyAsField(String var1, DateMilliWriter var2);
}
