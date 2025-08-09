package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.SmallIntHolder;

public interface SmallIntWriter extends BaseWriter {
   void write(SmallIntHolder var1);

   void writeSmallInt(short var1);
}
