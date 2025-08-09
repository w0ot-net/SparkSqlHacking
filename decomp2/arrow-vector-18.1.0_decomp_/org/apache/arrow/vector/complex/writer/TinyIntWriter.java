package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TinyIntHolder;

public interface TinyIntWriter extends BaseWriter {
   void write(TinyIntHolder var1);

   void writeTinyInt(byte var1);
}
