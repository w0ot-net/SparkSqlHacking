package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.SkipDateTimeField;

public final class JulianChronology extends BasicGJChronology {
   private static final long serialVersionUID = -8731039522547897247L;
   private static final long MILLIS_PER_YEAR = 31557600000L;
   private static final long MILLIS_PER_MONTH = 2629800000L;
   private static final int MIN_YEAR = -292269054;
   private static final int MAX_YEAR = 292272992;
   private static final JulianChronology INSTANCE_UTC;
   private static final ConcurrentHashMap cCache = new ConcurrentHashMap();

   static int adjustYearForSet(int var0) {
      if (var0 <= 0) {
         if (var0 == 0) {
            throw new IllegalFieldValueException(DateTimeFieldType.year(), var0, (Number)null, (Number)null);
         }

         ++var0;
      }

      return var0;
   }

   public static JulianChronology getInstanceUTC() {
      return INSTANCE_UTC;
   }

   public static JulianChronology getInstance() {
      return getInstance(DateTimeZone.getDefault(), 4);
   }

   public static JulianChronology getInstance(DateTimeZone var0) {
      return getInstance(var0, 4);
   }

   public static JulianChronology getInstance(DateTimeZone var0, int var1) {
      if (var0 == null) {
         var0 = DateTimeZone.getDefault();
      }

      JulianChronology[] var3 = (JulianChronology[])cCache.get(var0);
      if (var3 == null) {
         var3 = new JulianChronology[7];
         JulianChronology[] var4 = (JulianChronology[])cCache.putIfAbsent(var0, var3);
         if (var4 != null) {
            var3 = var4;
         }
      }

      JulianChronology var2;
      try {
         var2 = var3[var1 - 1];
      } catch (ArrayIndexOutOfBoundsException var7) {
         throw new IllegalArgumentException("Invalid min days in first week: " + var1);
      }

      if (var2 == null) {
         synchronized(var3) {
            var2 = var3[var1 - 1];
            if (var2 == null) {
               if (var0 == DateTimeZone.UTC) {
                  var2 = new JulianChronology((Chronology)null, (Object)null, var1);
               } else {
                  var2 = getInstance(DateTimeZone.UTC, var1);
                  var2 = new JulianChronology(ZonedChronology.getInstance(var2, var0), (Object)null, var1);
               }

               var3[var1 - 1] = var2;
            }
         }
      }

      return var2;
   }

   JulianChronology(Chronology var1, Object var2, int var3) {
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

   long getDateMidnightMillis(int var1, int var2, int var3) throws IllegalArgumentException {
      return super.getDateMidnightMillis(adjustYearForSet(var1), var2, var3);
   }

   boolean isLeapYear(int var1) {
      return (var1 & 3) == 0;
   }

   long calculateFirstDayOfYearMillis(int var1) {
      int var2 = var1 - 1968;
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
      return var4 - 62035200000L;
   }

   int getMinYear() {
      return -292269054;
   }

   int getMaxYear() {
      return 292272992;
   }

   long getAverageMillisPerYear() {
      return 31557600000L;
   }

   long getAverageMillisPerYearDividedByTwo() {
      return 15778800000L;
   }

   long getAverageMillisPerMonth() {
      return 2629800000L;
   }

   long getApproxMillisAtEpochDividedByTwo() {
      return 31083663600000L;
   }

   protected void assemble(AssembledChronology.Fields var1) {
      if (this.getBase() == null) {
         super.assemble(var1);
         var1.year = new SkipDateTimeField(this, var1.year);
         var1.weekyear = new SkipDateTimeField(this, var1.weekyear);
      }

   }

   static {
      INSTANCE_UTC = getInstance(DateTimeZone.UTC);
   }
}
