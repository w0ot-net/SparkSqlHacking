package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampSecTZHolder;

public interface TimeStampSecTZWriter extends BaseWriter {
   void write(TimeStampSecTZHolder var1);

   /** @deprecated */
   @Deprecated
   void writeTimeStampSecTZ(long var1);
}
