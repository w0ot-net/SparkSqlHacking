package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampMicroTZHolder;

public interface TimeStampMicroTZWriter extends BaseWriter {
   void write(TimeStampMicroTZHolder var1);

   /** @deprecated */
   @Deprecated
   void writeTimeStampMicroTZ(long var1);
}
