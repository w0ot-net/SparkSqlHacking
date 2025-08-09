package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.UInt8Holder;

public interface UInt8Writer extends BaseWriter {
   void write(UInt8Holder var1);

   void writeUInt8(long var1);
}
