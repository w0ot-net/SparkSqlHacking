package org.joda.time.convert;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

final class CalendarConverter extends AbstractConverter implements InstantConverter, PartialConverter {
   static final CalendarConverter INSTANCE = new CalendarConverter();

   protected CalendarConverter() {
   }

   public Chronology getChronology(Object var1, Chronology var2) {
      if (var2 != null) {
         return var2;
      } else {
         Calendar var3 = (Calendar)var1;
         Object var4 = null;

         try {
            var7 = DateTimeZone.forTimeZone(var3.getTimeZone());
         } catch (IllegalArgumentException var6) {
            var7 = DateTimeZone.getDefault();
         }

         return this.getChronology(var3, (DateTimeZone)var7);
      }
   }

   public Chronology getChronology(Object var1, DateTimeZone var2) {
      if (var1.getClass().getName().endsWith(".BuddhistCalendar")) {
         return BuddhistChronology.getInstance(var2);
      } else if (var1 instanceof GregorianCalendar) {
         GregorianCalendar var3 = (GregorianCalendar)var1;
         long var4 = var3.getGregorianChange().getTime();
         if (var4 == Long.MIN_VALUE) {
            return GregorianChronology.getInstance(var2);
         } else {
            return (Chronology)(var4 == Long.MAX_VALUE ? JulianChronology.getInstance(var2) : GJChronology.getInstance(var2, var4, 4));
         }
      } else {
         return ISOChronology.getInstance(var2);
      }
   }

   public long getInstantMillis(Object var1, Chronology var2) {
      Calendar var3 = (Calendar)var1;
      return var3.getTime().getTime();
   }

   public Class getSupportedType() {
      return Calendar.class;
   }
}
