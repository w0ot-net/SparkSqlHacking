package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

public final class GregorianChronology extends BasicGJChronology {
   private static final long serialVersionUID = -861407383323710522L;
   private static final long MILLIS_PER_YEAR = 31556952000L;
   private static final long MILLIS_PER_MONTH = 2629746000L;
   private static final int DAYS_0000_TO_1970 = 719527;
   private static final int MIN_YEAR = -292275054;
   private static final int MAX_YEAR = 292278993;
   private static final GregorianChronology INSTANCE_UTC;
   private static final ConcurrentHashMap cCache = new ConcurrentHashMap();

   public static GregorianChronology getInstanceUTC() {
      return INSTANCE_UTC;
   }

   public static GregorianChronology getInstance() {
      return getInstance(DateTimeZone.getDefault(), 4);
   }

   public static GregorianChronology getInstance(DateTimeZone var0) {
      return getInstance(var0, 4);
   }

   public static GregorianChronology getInstance(DateTimeZone var0, int var1) {
      if (var0 == null) {
         var0 = DateTimeZone.getDefault();
      }

      GregorianChronology[] var3 = (GregorianChronology[])cCache.get(var0);
      if (var3 == null) {
         var3 = new GregorianChronology[7];
         GregorianChronology[] var4 = (GregorianChronology[])cCache.putIfAbsent(var0, var3);
         if (var4 != null) {
            var3 = var4;
         }
      }

      GregorianChronology var2;
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
                  var2 = new GregorianChronology((Chronology)null, (Object)null, var1);
               } else {
                  var2 = getInstance(DateTimeZone.UTC, var1);
                  var2 = new GregorianChronology(ZonedChronology.getInstance(var2, var0), (Object)null, var1);
               }

               var3[var1 - 1] = var2;
            }
         }
      }

      return var2;
   }

   private GregorianChronology(Chronology var1, Object var2, int var3) {
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

   protected void assemble(AssembledChronology.Fields var1) {
      if (this.getBase() == null) {
         super.assemble(var1);
      }

   }

   boolean isLeapYear(int var1) {
      return (var1 & 3) == 0 && (var1 % 100 != 0 || var1 % 400 == 0);
   }

   long calculateFirstDayOfYearMillis(int var1) {
      int var2 = var1 / 100;
      if (var1 < 0) {
         var2 = (var1 + 3 >> 2) - var2 + (var2 + 3 >> 2) - 1;
      } else {
         var2 = (var1 >> 2) - var2 + (var2 >> 2);
         if (this.isLeapYear(var1)) {
            --var2;
         }
      }

      return ((long)var1 * 365L + (long)(var2 - 719527)) * 86400000L;
   }

   int getMinYear() {
      return -292275054;
   }

   int getMaxYear() {
      return 292278993;
   }

   long getAverageMillisPerYear() {
      return 31556952000L;
   }

   long getAverageMillisPerYearDividedByTwo() {
      return 15778476000L;
   }

   long getAverageMillisPerMonth() {
      return 2629746000L;
   }

   long getApproxMillisAtEpochDividedByTwo() {
      return 31083597720000L;
   }

   static {
      INSTANCE_UTC = getInstance(DateTimeZone.UTC);
   }
}
