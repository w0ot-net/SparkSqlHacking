package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.PeriodDuration;
import org.apache.arrow.vector.complex.writer.IntervalMonthDayNanoWriter;
import org.apache.arrow.vector.holders.IntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;

public interface IntervalMonthDayNanoReader extends BaseReader {
   void read(IntervalMonthDayNanoHolder var1);

   void read(NullableIntervalMonthDayNanoHolder var1);

   Object readObject();

   PeriodDuration readPeriodDuration();

   boolean isSet();

   void copyAsValue(IntervalMonthDayNanoWriter var1);

   void copyAsField(String var1, IntervalMonthDayNanoWriter var2);
}
