package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

class ReadablePeriodConverter extends AbstractConverter implements PeriodConverter {
   static final ReadablePeriodConverter INSTANCE = new ReadablePeriodConverter();

   protected ReadablePeriodConverter() {
   }

   public void setInto(ReadWritablePeriod var1, Object var2, Chronology var3) {
      var1.setPeriod((ReadablePeriod)var2);
   }

   public PeriodType getPeriodType(Object var1) {
      ReadablePeriod var2 = (ReadablePeriod)var1;
      return var2.getPeriodType();
   }

   public Class getSupportedType() {
      return ReadablePeriod.class;
   }
}
