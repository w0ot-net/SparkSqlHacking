package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.DateDayWriter;
import org.apache.arrow.vector.holders.DateDayHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;

public interface DateDayReader extends BaseReader {
   void read(DateDayHolder var1);

   void read(NullableDateDayHolder var1);

   Object readObject();

   Integer readInteger();

   boolean isSet();

   void copyAsValue(DateDayWriter var1);

   void copyAsField(String var1, DateDayWriter var2);
}
