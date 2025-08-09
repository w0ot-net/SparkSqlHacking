package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.IntervalDayHolder;

public interface IntervalDayWriter extends BaseWriter {
   void write(IntervalDayHolder var1);

   void writeIntervalDay(int var1, int var2);
}
