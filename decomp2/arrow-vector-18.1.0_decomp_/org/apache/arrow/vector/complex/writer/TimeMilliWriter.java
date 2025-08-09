package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeMilliHolder;

public interface TimeMilliWriter extends BaseWriter {
   void write(TimeMilliHolder var1);

   void writeTimeMilli(int var1);
}
