package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;

public interface PeriodConverter extends Converter {
   void setInto(ReadWritablePeriod var1, Object var2, Chronology var3);

   PeriodType getPeriodType(Object var1);
}
