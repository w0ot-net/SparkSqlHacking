package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.DateMilliHolder;

public interface DateMilliWriter extends BaseWriter {
   void write(DateMilliHolder var1);

   void writeDateMilli(long var1);
}
