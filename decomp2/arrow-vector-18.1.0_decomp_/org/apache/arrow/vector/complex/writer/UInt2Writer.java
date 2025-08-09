package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.UInt2Holder;

public interface UInt2Writer extends BaseWriter {
   void write(UInt2Holder var1);

   void writeUInt2(char var1);
}
