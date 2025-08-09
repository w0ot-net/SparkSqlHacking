package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.ReadWritableInterval;

public interface IntervalConverter extends Converter {
   boolean isReadableInterval(Object var1, Chronology var2);

   void setInto(ReadWritableInterval var1, Object var2, Chronology var3);
}
