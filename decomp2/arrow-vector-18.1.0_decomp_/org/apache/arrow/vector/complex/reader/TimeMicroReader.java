package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.TimeMicroWriter;
import org.apache.arrow.vector.holders.NullableTimeMicroHolder;
import org.apache.arrow.vector.holders.TimeMicroHolder;

public interface TimeMicroReader extends BaseReader {
   void read(TimeMicroHolder var1);

   void read(NullableTimeMicroHolder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(TimeMicroWriter var1);

   void copyAsField(String var1, TimeMicroWriter var2);
}
