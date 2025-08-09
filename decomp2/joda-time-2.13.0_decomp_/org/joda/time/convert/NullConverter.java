package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.Period;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

class NullConverter extends AbstractConverter implements InstantConverter, PartialConverter, DurationConverter, PeriodConverter, IntervalConverter {
   static final NullConverter INSTANCE = new NullConverter();

   protected NullConverter() {
   }

   public long getDurationMillis(Object var1) {
      return 0L;
   }

   public void setInto(ReadWritablePeriod var1, Object var2, Chronology var3) {
      var1.setPeriod((ReadablePeriod)((Period)null));
   }

   public void setInto(ReadWritableInterval var1, Object var2, Chronology var3) {
      var1.setChronology(var3);
      long var4 = DateTimeUtils.currentTimeMillis();
      var1.setInterval(var4, var4);
   }

   public Class getSupportedType() {
      return null;
   }
}
