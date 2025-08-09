package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampSecHolder;

public interface TimeStampSecWriter extends BaseWriter {
   void write(TimeStampSecHolder var1);

   void writeTimeStampSec(long var1);
}
