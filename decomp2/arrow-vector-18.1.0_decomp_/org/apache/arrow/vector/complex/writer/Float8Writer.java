package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.Float8Holder;

public interface Float8Writer extends BaseWriter {
   void write(Float8Holder var1);

   void writeFloat8(double var1);
}
