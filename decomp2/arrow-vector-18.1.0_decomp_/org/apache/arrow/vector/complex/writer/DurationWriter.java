package org.apache.arrow.vector.complex.writer;

import org.apache.arrow.vector.holders.DurationHolder;

public interface DurationWriter extends BaseWriter {
   void write(DurationHolder var1);

   /** @deprecated */
   @Deprecated
   void writeDuration(long var1);
}
