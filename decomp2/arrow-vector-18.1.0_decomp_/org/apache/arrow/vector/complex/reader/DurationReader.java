package org.apache.arrow.vector.complex.reader;

import java.time.Duration;
import org.apache.arrow.vector.complex.writer.DurationWriter;
import org.apache.arrow.vector.holders.DurationHolder;
import org.apache.arrow.vector.holders.NullableDurationHolder;

public interface DurationReader extends BaseReader {
   void read(DurationHolder var1);

   void read(NullableDurationHolder var1);

   Object readObject();

   Duration readDuration();

   boolean isSet();

   void copyAsValue(DurationWriter var1);

   void copyAsField(String var1, DurationWriter var2);
}
