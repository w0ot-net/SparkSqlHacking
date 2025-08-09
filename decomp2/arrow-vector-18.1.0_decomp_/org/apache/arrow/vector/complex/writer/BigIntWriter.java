package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.BigIntHolder;

public interface BigIntWriter extends BaseWriter {
   void write(BigIntHolder var1);

   void writeBigInt(long var1);
}
