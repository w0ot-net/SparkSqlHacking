package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.field.SkipDateTimeField;

public final class CopticChronology extends BasicFixedMonthChronology {
   private static final long serialVersionUID = -5972804258688333942L;
   public static final int AM = 1;
   private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AM");
   private static final int MIN_YEAR = -292269337;
   private static final int MAX_YEAR = 292272708;
   private static final ConcurrentHashMap cCache = new ConcurrentHashMap();
   private static final CopticChronology INSTANCE_UTC;

   public static CopticChronology getInstanceUTC() {
      return INSTANCE_UTC;
   }

   public static CopticChronology getInstance() {
      return getInstance(DateTimeZone.getDefault(), 4);
   }

   public static CopticChronology getInstance(DateTimeZone var0) {
      return getInstance(var0, 4);
   }

   public static CopticChronology getInstance(DateTimeZone var0, int var1) {
      if (var0 == null) {
         var0 = DateTimeZone.getDefault();
      }

      CopticChronology[] var3 = (CopticChronology[])cCache.get(var0);
      if (var3 == null) {
         var3 = new CopticChronology[7];
         CopticChronology[] var4 = (CopticChronology[])cCache.putIfAbsent(var0, var3);
         if (var4 != null) {
            var3 = var4;
         }
      }

      CopticChronology var2;
      try {
         var2 = var3[var1 - 1];
      } catch (ArrayIndexOutOfBoundsException var8) {
         throw new IllegalArgumentException("Invalid min days in first week: " + var1);
      }

      if (var2 == null) {
         synchronized(var3) {
            var2 = var3[var1 - 1];
            if (var2 == null) {
               if (var0 == DateTimeZone.UTC) {
                  var2 = new CopticChronology((Chronology)null, (Object)null, var1);
                  DateTime var5 = new DateTime(1, 1, 1, 0, 0, 0, 0, var2);
                  var2 = new CopticChronology(LimitChronology.getInstance(var2, var5, (ReadableDateTime)null), (Object)null, var1);
               } else {
                  var2 = getInstance(DateTimeZone.UTC, var1);
                  var2 = new CopticChronology(ZonedChronology.getInstance(var2, var0), (Object)null, var1);
               }

               var3[var1 - 1] = var2;
            }
         }
      }

      return var2;
   }

   CopticChronology(Chronology var1, Object var2, int var3) {
      super(var1, var2, var3);
   }

   private Object readResolve() {
      Chronology var1 = this.getBase();
      int var2 = this.getMinimumDaysInFirstWeek();
      var2 = var2 == 0 ? 4 : var2;
      return var1 == null ? getInstance(DateTimeZone.UTC, var2) : getInstance(var1.getZone(), var2);
   }

   public Chronology withUTC() {
      return INSTANCE_UTC;
   }

   public Chronology withZone(DateTimeZone var1) {
      if (var1 == null) {
         var1 = DateTimeZone.getDefault();
      }

      return var1 == this.getZone() ? this : getInstance(var1);
   }

   boolean isLeapDay(long var1) {
      return this.dayOfMonth().get(var1) == 6 && this.monthOfYear().isLeap(var1);
   }

   long calculateFirstDayOfYearMillis(int var1) {
      int var2 = var1 - 1687;
      int var3;
      if (var2 <= 0) {
         var3 = var2 + 3 >> 2;
      } else {
         var3 = var2 >> 2;
         if (!this.isLeapYear(var1)) {
            ++var3;
         }
      }

      long var4 = ((long)var2 * 365L + (long)var3) * 86400000L;
      return var4 + 21859200000L;
   }

   int getMinYear() {
      return -292269337;
   }

   int getMaxYear() {
      return 292272708;
   }

   long getApproxMillisAtEpochDividedByTwo() {
      return 26607895200000L;
   }

   protected void assemble(AssembledChronology.Fields var1) {
      if (this.getBase() == null) {
         super.assemble(var1);
         var1.year = new SkipDateTimeField(this, var1.year);
         var1.weekyear = new SkipDateTimeField(this, var1.weekyear);
         var1.era = ERA_FIELD;
         var1.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
         var1.months = var1.monthOfYear.getDurationField();
      }

   }

   static {
      INSTANCE_UTC = getInstance(DateTimeZone.UTC);
   }
}
