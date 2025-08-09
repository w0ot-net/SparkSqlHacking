package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.Float4Holder;

public interface Float4Writer extends BaseWriter {
   void write(Float4Holder var1);

   void writeFloat4(float var1);
}
