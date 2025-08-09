package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.UInt1Holder;

public interface UInt1Writer extends BaseWriter {
   void write(UInt1Holder var1);

   void writeUInt1(byte var1);
}
