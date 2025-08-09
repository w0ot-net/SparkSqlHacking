package org.apache.arrow.vector.complex.reader;

import java.time.Duration;
import org.apache.arrow.vector.complex.writer.IntervalDayWriter;
import org.apache.arrow.vector.holders.IntervalDayHolder;
import org.apache.arrow.vector.holders.NullableIntervalDayHolder;

public interface IntervalDayReader extends BaseReader {
   void read(IntervalDayHolder var1);

   void read(NullableIntervalDayHolder var1);

   Object readObject();

   Duration readDuration();

   boolean isSet();

   void copyAsValue(IntervalDayWriter var1);

   void copyAsField(String var1, IntervalDayWriter var2);
}
