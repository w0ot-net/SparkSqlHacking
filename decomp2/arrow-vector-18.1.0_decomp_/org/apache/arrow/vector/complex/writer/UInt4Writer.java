package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.UInt4Holder;

public interface UInt4Writer extends BaseWriter {
   void write(UInt4Holder var1);

   void writeUInt4(int var1);
}
