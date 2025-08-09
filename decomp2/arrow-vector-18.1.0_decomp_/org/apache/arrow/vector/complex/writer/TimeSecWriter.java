package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeSecHolder;

public interface TimeSecWriter extends BaseWriter {
   void write(TimeSecHolder var1);

   void writeTimeSec(int var1);
}
