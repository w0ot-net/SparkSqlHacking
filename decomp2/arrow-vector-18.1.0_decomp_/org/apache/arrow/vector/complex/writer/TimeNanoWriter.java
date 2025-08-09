package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeNanoHolder;

public interface TimeNanoWriter extends BaseWriter {
   void write(TimeNanoHolder var1);

   void writeTimeNano(long var1);
}
