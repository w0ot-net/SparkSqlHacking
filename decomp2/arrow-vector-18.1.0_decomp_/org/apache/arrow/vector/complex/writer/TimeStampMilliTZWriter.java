package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampMilliTZHolder;

public interface TimeStampMilliTZWriter extends BaseWriter {
   void write(TimeStampMilliTZHolder var1);

   /** @deprecated */
   @Deprecated
   void writeTimeStampMilliTZ(long var1);
}
