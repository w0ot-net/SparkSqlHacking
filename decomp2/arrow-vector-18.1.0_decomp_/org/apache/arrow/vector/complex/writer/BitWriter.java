package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.BitHolder;

public interface BitWriter extends BaseWriter {
   void write(BitHolder var1);

   void writeBit(int var1);
}
