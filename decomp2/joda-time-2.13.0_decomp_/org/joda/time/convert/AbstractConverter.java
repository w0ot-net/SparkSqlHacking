package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.PeriodType;
import org.joda.time.ReadablePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;

public abstract class AbstractConverter implements Converter {
   protected AbstractConverter() {
   }

   public long getInstantMillis(Object var1, Chronology var2) {
      return DateTimeUtils.currentTimeMillis();
   }

   public Chronology getChronology(Object var1, DateTimeZone var2) {
      return ISOChronology.getInstance(var2);
   }

   public Chronology getChronology(Object var1, Chronology var2) {
      return DateTimeUtils.getChronology(var2);
   }

   public int[] getPartialValues(ReadablePartial var1, Object var2, Chronology var3) {
      long var4 = this.getInstantMillis(var2, var3);
      return var3.get(var1, var4);
   }

   public int[] getPartialValues(ReadablePartial var1, Object var2, Chronology var3, DateTimeFormatter var4) {
      return this.getPartialValues(var1, var2, var3);
   }

   public PeriodType getPeriodType(Object var1) {
      return PeriodType.standard();
   }

   public boolean isReadableInterval(Object var1, Chronology var2) {
      return false;
   }

   public String toString() {
      return "Converter[" + (this.getSupportedType() == null ? "null" : this.getSupportedType().getName()) + "]";
   }
}
