package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.Float2Holder;

public interface Float2Writer extends BaseWriter {
   void write(Float2Holder var1);

   void writeFloat2(short var1);
}
