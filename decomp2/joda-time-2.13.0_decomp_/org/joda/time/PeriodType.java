package org.joda.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.field.FieldUtils;

public class PeriodType implements Serializable {
   private static final long serialVersionUID = 2274324892792009998L;
   private static final Map cTypes = new HashMap(32);
   static int YEAR_INDEX = 0;
   static int MONTH_INDEX = 1;
   static int WEEK_INDEX = 2;
   static int DAY_INDEX = 3;
   static int HOUR_INDEX = 4;
   static int MINUTE_INDEX = 5;
   static int SECOND_INDEX = 6;
   static int MILLI_INDEX = 7;
   private static PeriodType cStandard;
   private static PeriodType cYMDTime;
   private static PeriodType cYMD;
   private static PeriodType cYWDTime;
   private static PeriodType cYWD;
   private static PeriodType cYDTime;
   private static PeriodType cYD;
   private static PeriodType cDTime;
   private static PeriodType cTime;
   private static PeriodType cYears;
   private static PeriodType cMonths;
   private static PeriodType cWeeks;
   private static PeriodType cDays;
   private static PeriodType cHours;
   private static PeriodType cMinutes;
   private static PeriodType cSeconds;
   private static PeriodType cMillis;
   private final String iName;
   private final DurationFieldType[] iTypes;
   private final int[] iIndices;

   public static PeriodType standard() {
      PeriodType var0 = cStandard;
      if (var0 == null) {
         var0 = new PeriodType("Standard", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.months(), DurationFieldType.weeks(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
         cStandard = var0;
      }

      return var0;
   }

   public static PeriodType yearMonthDayTime() {
      PeriodType var0 = cYMDTime;
      if (var0 == null) {
         var0 = new PeriodType("YearMonthDayTime", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.months(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{0, 1, -1, 2, 3, 4, 5, 6});
         cYMDTime = var0;
      }

      return var0;
   }

   public static PeriodType yearMonthDay() {
      PeriodType var0 = cYMD;
      if (var0 == null) {
         var0 = new PeriodType("YearMonthDay", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.months(), DurationFieldType.days()}, new int[]{0, 1, -1, 2, -1, -1, -1, -1});
         cYMD = var0;
      }

      return var0;
   }

   public static PeriodType yearWeekDayTime() {
      PeriodType var0 = cYWDTime;
      if (var0 == null) {
         var0 = new PeriodType("YearWeekDayTime", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.weeks(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{0, -1, 1, 2, 3, 4, 5, 6});
         cYWDTime = var0;
      }

      return var0;
   }

   public static PeriodType yearWeekDay() {
      PeriodType var0 = cYWD;
      if (var0 == null) {
         var0 = new PeriodType("YearWeekDay", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.weeks(), DurationFieldType.days()}, new int[]{0, -1, 1, 2, -1, -1, -1, -1});
         cYWD = var0;
      }

      return var0;
   }

   public static PeriodType yearDayTime() {
      PeriodType var0 = cYDTime;
      if (var0 == null) {
         var0 = new PeriodType("YearDayTime", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{0, -1, -1, 1, 2, 3, 4, 5});
         cYDTime = var0;
      }

      return var0;
   }

   public static PeriodType yearDay() {
      PeriodType var0 = cYD;
      if (var0 == null) {
         var0 = new PeriodType("YearDay", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.days()}, new int[]{0, -1, -1, 1, -1, -1, -1, -1});
         cYD = var0;
      }

      return var0;
   }

   public static PeriodType dayTime() {
      PeriodType var0 = cDTime;
      if (var0 == null) {
         var0 = new PeriodType("DayTime", new DurationFieldType[]{DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{-1, -1, -1, 0, 1, 2, 3, 4});
         cDTime = var0;
      }

      return var0;
   }

   public static PeriodType time() {
      PeriodType var0 = cTime;
      if (var0 == null) {
         var0 = new PeriodType("Time", new DurationFieldType[]{DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{-1, -1, -1, -1, 0, 1, 2, 3});
         cTime = var0;
      }

      return var0;
   }

   public static PeriodType years() {
      PeriodType var0 = cYears;
      if (var0 == null) {
         var0 = new PeriodType("Years", new DurationFieldType[]{DurationFieldType.years()}, new int[]{0, -1, -1, -1, -1, -1, -1, -1});
         cYears = var0;
      }

      return var0;
   }

   public static PeriodType months() {
      PeriodType var0 = cMonths;
      if (var0 == null) {
         var0 = new PeriodType("Months", new DurationFieldType[]{DurationFieldType.months()}, new int[]{-1, 0, -1, -1, -1, -1, -1, -1});
         cMonths = var0;
      }

      return var0;
   }

   public static PeriodType weeks() {
      PeriodType var0 = cWeeks;
      if (var0 == null) {
         var0 = new PeriodType("Weeks", new DurationFieldType[]{DurationFieldType.weeks()}, new int[]{-1, -1, 0, -1, -1, -1, -1, -1});
         cWeeks = var0;
      }

      return var0;
   }

   public static PeriodType days() {
      PeriodType var0 = cDays;
      if (var0 == null) {
         var0 = new PeriodType("Days", new DurationFieldType[]{DurationFieldType.days()}, new int[]{-1, -1, -1, 0, -1, -1, -1, -1});
         cDays = var0;
      }

      return var0;
   }

   public static PeriodType hours() {
      PeriodType var0 = cHours;
      if (var0 == null) {
         var0 = new PeriodType("Hours", new DurationFieldType[]{DurationFieldType.hours()}, new int[]{-1, -1, -1, -1, 0, -1, -1, -1});
         cHours = var0;
      }

      return var0;
   }

   public static PeriodType minutes() {
      PeriodType var0 = cMinutes;
      if (var0 == null) {
         var0 = new PeriodType("Minutes", new DurationFieldType[]{DurationFieldType.minutes()}, new int[]{-1, -1, -1, -1, -1, 0, -1, -1});
         cMinutes = var0;
      }

      return var0;
   }

   public static PeriodType seconds() {
      PeriodType var0 = cSeconds;
      if (var0 == null) {
         var0 = new PeriodType("Seconds", new DurationFieldType[]{DurationFieldType.seconds()}, new int[]{-1, -1, -1, -1, -1, -1, 0, -1});
         cSeconds = var0;
      }

      return var0;
   }

   public static PeriodType millis() {
      PeriodType var0 = cMillis;
      if (var0 == null) {
         var0 = new PeriodType("Millis", new DurationFieldType[]{DurationFieldType.millis()}, new int[]{-1, -1, -1, -1, -1, -1, -1, 0});
         cMillis = var0;
      }

      return var0;
   }

   public static synchronized PeriodType forFields(DurationFieldType[] var0) {
      if (var0 != null && var0.length != 0) {
         for(int var1 = 0; var1 < var0.length; ++var1) {
            if (var0[var1] == null) {
               throw new IllegalArgumentException("Types array must not contain null");
            }
         }

         Map var8 = cTypes;
         if (var8.isEmpty()) {
            var8.put(standard(), standard());
            var8.put(yearMonthDayTime(), yearMonthDayTime());
            var8.put(yearMonthDay(), yearMonthDay());
            var8.put(yearWeekDayTime(), yearWeekDayTime());
            var8.put(yearWeekDay(), yearWeekDay());
            var8.put(yearDayTime(), yearDayTime());
            var8.put(yearDay(), yearDay());
            var8.put(dayTime(), dayTime());
            var8.put(time(), time());
            var8.put(years(), years());
            var8.put(months(), months());
            var8.put(weeks(), weeks());
            var8.put(days(), days());
            var8.put(hours(), hours());
            var8.put(minutes(), minutes());
            var8.put(seconds(), seconds());
            var8.put(millis(), millis());
         }

         PeriodType var2 = new PeriodType((String)null, var0, (int[])null);
         Object var3 = var8.get(var2);
         if (var3 instanceof PeriodType) {
            return (PeriodType)var3;
         } else if (var3 != null) {
            throw new IllegalArgumentException("PeriodType does not support fields: " + var3);
         } else {
            PeriodType var4 = standard();
            ArrayList var5 = new ArrayList(Arrays.asList(var0));
            if (!var5.remove(DurationFieldType.years())) {
               var4 = var4.withYearsRemoved();
            }

            if (!var5.remove(DurationFieldType.months())) {
               var4 = var4.withMonthsRemoved();
            }

            if (!var5.remove(DurationFieldType.weeks())) {
               var4 = var4.withWeeksRemoved();
            }

            if (!var5.remove(DurationFieldType.days())) {
               var4 = var4.withDaysRemoved();
            }

            if (!var5.remove(DurationFieldType.hours())) {
               var4 = var4.withHoursRemoved();
            }

            if (!var5.remove(DurationFieldType.minutes())) {
               var4 = var4.withMinutesRemoved();
            }

            if (!var5.remove(DurationFieldType.seconds())) {
               var4 = var4.withSecondsRemoved();
            }

            if (!var5.remove(DurationFieldType.millis())) {
               var4 = var4.withMillisRemoved();
            }

            if (var5.size() > 0) {
               var8.put(var2, var5);
               throw new IllegalArgumentException("PeriodType does not support fields: " + var5);
            } else {
               PeriodType var6 = new PeriodType((String)null, var4.iTypes, (int[])null);
               PeriodType var7 = (PeriodType)var8.get(var6);
               if (var7 != null) {
                  var8.put(var6, var7);
                  return var7;
               } else {
                  var8.put(var6, var4);
                  return var4;
               }
            }
         }
      } else {
         throw new IllegalArgumentException("Types array must not be null or empty");
      }
   }

   protected PeriodType(String var1, DurationFieldType[] var2, int[] var3) {
      this.iName = var1;
      this.iTypes = var2;
      this.iIndices = var3;
   }

   public String getName() {
      return this.iName;
   }

   public int size() {
      return this.iTypes.length;
   }

   public DurationFieldType getFieldType(int var1) {
      return this.iTypes[var1];
   }

   public boolean isSupported(DurationFieldType var1) {
      return this.indexOf(var1) >= 0;
   }

   public int indexOf(DurationFieldType var1) {
      int var2 = 0;

      for(int var3 = this.size(); var2 < var3; ++var2) {
         if (this.iTypes[var2].equals(var1)) {
            return var2;
         }
      }

      return -1;
   }

   public String toString() {
      return "PeriodType[" + this.getName() + "]";
   }

   int getIndexedField(ReadablePeriod var1, int var2) {
      int var3 = this.iIndices[var2];
      return var3 == -1 ? 0 : var1.getValue(var3);
   }

   boolean setIndexedField(ReadablePeriod var1, int var2, int[] var3, int var4) {
      int var5 = this.iIndices[var2];
      if (var5 == -1) {
         throw new UnsupportedOperationException("Field is not supported");
      } else {
         var3[var5] = var4;
         return true;
      }
   }

   boolean addIndexedField(ReadablePeriod var1, int var2, int[] var3, int var4) {
      if (var4 == 0) {
         return false;
      } else {
         int var5 = this.iIndices[var2];
         if (var5 == -1) {
            throw new UnsupportedOperationException("Field is not supported");
         } else {
            var3[var5] = FieldUtils.safeAdd(var3[var5], var4);
            return true;
         }
      }
   }

   public PeriodType withYearsRemoved() {
      return this.withFieldRemoved(0, "NoYears");
   }

   public PeriodType withMonthsRemoved() {
      return this.withFieldRemoved(1, "NoMonths");
   }

   public PeriodType withWeeksRemoved() {
      return this.withFieldRemoved(2, "NoWeeks");
   }

   public PeriodType withDaysRemoved() {
      return this.withFieldRemoved(3, "NoDays");
   }

   public PeriodType withHoursRemoved() {
      return this.withFieldRemoved(4, "NoHours");
   }

   public PeriodType withMinutesRemoved() {
      return this.withFieldRemoved(5, "NoMinutes");
   }

   public PeriodType withSecondsRemoved() {
      return this.withFieldRemoved(6, "NoSeconds");
   }

   public PeriodType withMillisRemoved() {
      return this.withFieldRemoved(7, "NoMillis");
   }

   private PeriodType withFieldRemoved(int var1, String var2) {
      int var3 = this.iIndices[var1];
      if (var3 == -1) {
         return this;
      } else {
         DurationFieldType[] var4 = new DurationFieldType[this.size() - 1];

         for(int var5 = 0; var5 < this.iTypes.length; ++var5) {
            if (var5 < var3) {
               var4[var5] = this.iTypes[var5];
            } else if (var5 > var3) {
               var4[var5 - 1] = this.iTypes[var5];
            }
         }

         int[] var7 = new int[8];

         for(int var6 = 0; var6 < var7.length; ++var6) {
            if (var6 < var1) {
               var7[var6] = this.iIndices[var6];
            } else if (var6 > var1) {
               var7[var6] = this.iIndices[var6] == -1 ? -1 : this.iIndices[var6] - 1;
            } else {
               var7[var6] = -1;
            }
         }

         return new PeriodType(this.getName() + var2, var4, var7);
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PeriodType)) {
         return false;
      } else {
         PeriodType var2 = (PeriodType)var1;
         return Arrays.equals(this.iTypes, var2.iTypes);
      }
   }

   public int hashCode() {
      int var1 = 0;

      for(int var2 = 0; var2 < this.iTypes.length; ++var2) {
         var1 += this.iTypes[var2].hashCode();
      }

      return var1;
   }
}
