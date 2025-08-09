package org.apache.arrow.vector.complex.reader;

import java.time.Period;
import org.apache.arrow.vector.complex.writer.IntervalYearWriter;
import org.apache.arrow.vector.holders.IntervalYearHolder;
import org.apache.arrow.vector.holders.NullableIntervalYearHolder;

public interface IntervalYearReader extends BaseReader {
   void read(IntervalYearHolder var1);

   void read(NullableIntervalYearHolder var1);

   Object readObject();

   Period readPeriod();

   boolean isSet();

   void copyAsValue(IntervalYearWriter var1);

   void copyAsField(String var1, IntervalYearWriter var2);
}
