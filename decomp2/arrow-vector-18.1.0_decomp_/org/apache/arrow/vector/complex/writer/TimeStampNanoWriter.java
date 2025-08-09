package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampNanoHolder;

public interface TimeStampNanoWriter extends BaseWriter {
   void write(TimeStampNanoHolder var1);

   void writeTimeStampNano(long var1);
}
