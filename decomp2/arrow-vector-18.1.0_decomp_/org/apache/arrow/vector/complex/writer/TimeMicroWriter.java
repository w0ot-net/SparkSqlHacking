package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeMicroHolder;

public interface TimeMicroWriter extends BaseWriter {
   void write(TimeMicroHolder var1);

   void writeTimeMicro(long var1);
}
