package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.DateDayHolder;

public interface DateDayWriter extends BaseWriter {
   void write(DateDayHolder var1);

   void writeDateDay(int var1);
}
