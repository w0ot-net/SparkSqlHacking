package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.FixedSizeBinaryHolder;

public interface FixedSizeBinaryWriter extends BaseWriter {
   void write(FixedSizeBinaryHolder var1);

   /** @deprecated */
   @Deprecated
   void writeFixedSizeBinary(ArrowBuf var1);
}
