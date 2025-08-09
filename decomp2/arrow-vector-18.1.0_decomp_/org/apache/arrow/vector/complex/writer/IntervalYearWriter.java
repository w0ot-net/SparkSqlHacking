package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.IntervalYearHolder;

public interface IntervalYearWriter extends BaseWriter {
   void write(IntervalYearHolder var1);

   void writeIntervalYear(int var1);
}
