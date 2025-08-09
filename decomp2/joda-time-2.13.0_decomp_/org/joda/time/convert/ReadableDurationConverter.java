package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadablePeriod;

class ReadableDurationConverter extends AbstractConverter implements DurationConverter, PeriodConverter {
   static final ReadableDurationConverter INSTANCE = new ReadableDurationConverter();

   protected ReadableDurationConverter() {
   }

   public long getDurationMillis(Object var1) {
      return ((ReadableDuration)var1).getMillis();
   }

   public void setInto(ReadWritablePeriod var1, Object var2, Chronology var3) {
      ReadableDuration var4 = (ReadableDuration)var2;
      var3 = DateTimeUtils.getChronology(var3);
      long var5 = var4.getMillis();
      int[] var7 = var3.get((ReadablePeriod)var1, var5);

      for(int var8 = 0; var8 < var7.length; ++var8) {
         var1.setValue(var8, var7[var8]);
      }

   }

   public Class getSupportedType() {
      return ReadableDuration.class;
   }
}
