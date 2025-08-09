package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampMilliHolder;

public interface TimeStampMilliWriter extends BaseWriter {
   void write(TimeStampMilliHolder var1);

   void writeTimeStampMilli(long var1);
}
