package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.IntHolder;

public interface IntWriter extends BaseWriter {
   void write(IntHolder var1);

   void writeInt(int var1);
}
