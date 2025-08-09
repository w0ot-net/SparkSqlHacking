package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.IntervalMonthDayNanoHolder;

public interface IntervalMonthDayNanoWriter extends BaseWriter {
   void write(IntervalMonthDayNanoHolder var1);

   void writeIntervalMonthDayNano(int var1, int var2, long var3);
}
