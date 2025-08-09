package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampMicroHolder;

public interface TimeStampMicroWriter extends BaseWriter {
   void write(TimeStampMicroHolder var1);

   void writeTimeStampMicro(long var1);
}
