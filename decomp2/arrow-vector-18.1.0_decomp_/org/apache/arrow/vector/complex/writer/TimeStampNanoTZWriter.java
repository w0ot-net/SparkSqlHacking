package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.TimeStampNanoTZHolder;

public interface TimeStampNanoTZWriter extends BaseWriter {
   void write(TimeStampNanoTZHolder var1);

   /** @deprecated */
   @Deprecated
   void writeTimeStampNanoTZ(long var1);
}
