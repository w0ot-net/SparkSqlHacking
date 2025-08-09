package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableInterval;

class ReadableIntervalConverter extends AbstractConverter implements IntervalConverter, DurationConverter, PeriodConverter {
   static final ReadableIntervalConverter INSTANCE = new ReadableIntervalConverter();

   protected ReadableIntervalConverter() {
   }

   public long getDurationMillis(Object var1) {
      return ((ReadableInterval)var1).toDurationMillis();
   }

   public void setInto(ReadWritablePeriod var1, Object var2, Chronology var3) {
      ReadableInterval var4 = (ReadableInterval)var2;
      var3 = var3 != null ? var3 : DateTimeUtils.getIntervalChronology(var4);
      long var5 = var4.getStartMillis();
      long var7 = var4.getEndMillis();
      int[] var9 = var3.get(var1, var5, var7);

      for(int var10 = 0; var10 < var9.length; ++var10) {
         var1.setValue(var10, var9[var10]);
      }

   }

   public boolean isReadableInterval(Object var1, Chronology var2) {
      return true;
   }

   public void setInto(ReadWritableInterval var1, Object var2, Chronology var3) {
      ReadableInterval var4 = (ReadableInterval)var2;
      var1.setInterval(var4);
      if (var3 != null) {
         var1.setChronology(var3);
      } else {
         var1.setChronology(var4.getChronology());
      }

   }

   public Class getSupportedType() {
      return ReadableInterval.class;
   }
}
