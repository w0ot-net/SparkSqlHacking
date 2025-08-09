package org.joda.time.chrono;

import java.io.IOException;
import java.io.ObjectInputStream;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;

public abstract class AssembledChronology extends BaseChronology {
   private static final long serialVersionUID = -6728465968995518215L;
   private final Chronology iBase;
   private final Object iParam;
   private transient DurationField iMillis;
   private transient DurationField iSeconds;
   private transient DurationField iMinutes;
   private transient DurationField iHours;
   private transient DurationField iHalfdays;
   private transient DurationField iDays;
   private transient DurationField iWeeks;
   private transient DurationField iWeekyears;
   private transient DurationField iMonths;
   private transient DurationField iYears;
   private transient DurationField iCenturies;
   private transient DurationField iEras;
   private transient DateTimeField iMillisOfSecond;
   private transient DateTimeField iMillisOfDay;
   private transient DateTimeField iSecondOfMinute;
   private transient DateTimeField iSecondOfDay;
   private transient DateTimeField iMinuteOfHour;
   private transient DateTimeField iMinuteOfDay;
   private transient DateTimeField iHourOfDay;
   private transient DateTimeField iClockhourOfDay;
   private transient DateTimeField iHourOfHalfday;
   private transient DateTimeField iClockhourOfHalfday;
   private transient DateTimeField iHalfdayOfDay;
   private transient DateTimeField iDayOfWeek;
   private transient DateTimeField iDayOfMonth;
   private transient DateTimeField iDayOfYear;
   private transient DateTimeField iWeekOfWeekyear;
   private transient DateTimeField iWeekyear;
   private transient DateTimeField iWeekyearOfCentury;
   private transient DateTimeField iMonthOfYear;
   private transient DateTimeField iYear;
   private transient DateTimeField iYearOfEra;
   private transient DateTimeField iYearOfCentury;
   private transient DateTimeField iCenturyOfEra;
   private transient DateTimeField iEra;
   private transient int iBaseFlags;

   protected AssembledChronology(Chronology var1, Object var2) {
      this.iBase = var1;
      this.iParam = var2;
      this.setFields();
   }

   public DateTimeZone getZone() {
      Chronology var1;
      return (var1 = this.iBase) != null ? var1.getZone() : null;
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      Chronology var5;
      return (var5 = this.iBase) != null && (this.iBaseFlags & 6) == 6 ? var5.getDateTimeMillis(var1, var2, var3, var4) : super.getDateTimeMillis(var1, var2, var3, var4);
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4, int var5, int var6, int var7) throws IllegalArgumentException {
      Chronology var8;
      return (var8 = this.iBase) != null && (this.iBaseFlags & 5) == 5 ? var8.getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7) : super.getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
   }

   public long getDateTimeMillis(long var1, int var3, int var4, int var5, int var6) throws IllegalArgumentException {
      Chronology var7;
      return (var7 = this.iBase) != null && (this.iBaseFlags & 1) == 1 ? var7.getDateTimeMillis(var1, var3, var4, var5, var6) : super.getDateTimeMillis(var1, var3, var4, var5, var6);
   }

   public final DurationField millis() {
      return this.iMillis;
   }

   public final DateTimeField millisOfSecond() {
      return this.iMillisOfSecond;
   }

   public final DateTimeField millisOfDay() {
      return this.iMillisOfDay;
   }

   public final DurationField seconds() {
      return this.iSeconds;
   }

   public final DateTimeField secondOfMinute() {
      return this.iSecondOfMinute;
   }

   public final DateTimeField secondOfDay() {
      return this.iSecondOfDay;
   }

   public final DurationField minutes() {
      return this.iMinutes;
   }

   public final DateTimeField minuteOfHour() {
      return this.iMinuteOfHour;
   }

   public final DateTimeField minuteOfDay() {
      return this.iMinuteOfDay;
   }

   public final DurationField hours() {
      return this.iHours;
   }

   public final DateTimeField hourOfDay() {
      return this.iHourOfDay;
   }

   public final DateTimeField clockhourOfDay() {
      return this.iClockhourOfDay;
   }

   public final DurationField halfdays() {
      return this.iHalfdays;
   }

   public final DateTimeField hourOfHalfday() {
      return this.iHourOfHalfday;
   }

   public final DateTimeField clockhourOfHalfday() {
      return this.iClockhourOfHalfday;
   }

   public final DateTimeField halfdayOfDay() {
      return this.iHalfdayOfDay;
   }

   public final DurationField days() {
      return this.iDays;
   }

   public final DateTimeField dayOfWeek() {
      return this.iDayOfWeek;
   }

   public final DateTimeField dayOfMonth() {
      return this.iDayOfMonth;
   }

   public final DateTimeField dayOfYear() {
      return this.iDayOfYear;
   }

   public final DurationField weeks() {
      return this.iWeeks;
   }

   public final DateTimeField weekOfWeekyear() {
      return this.iWeekOfWeekyear;
   }

   public final DurationField weekyears() {
      return this.iWeekyears;
   }

   public final DateTimeField weekyear() {
      return this.iWeekyear;
   }

   public final DateTimeField weekyearOfCentury() {
      return this.iWeekyearOfCentury;
   }

   public final DurationField months() {
      return this.iMonths;
   }

   public final DateTimeField monthOfYear() {
      return this.iMonthOfYear;
   }

   public final DurationField years() {
      return this.iYears;
   }

   public final DateTimeField year() {
      return this.iYear;
   }

   public final DateTimeField yearOfEra() {
      return this.iYearOfEra;
   }

   public final DateTimeField yearOfCentury() {
      return this.iYearOfCentury;
   }

   public final DurationField centuries() {
      return this.iCenturies;
   }

   public final DateTimeField centuryOfEra() {
      return this.iCenturyOfEra;
   }

   public final DurationField eras() {
      return this.iEras;
   }

   public final DateTimeField era() {
      return this.iEra;
   }

   protected abstract void assemble(Fields var1);

   protected final Chronology getBase() {
      return this.iBase;
   }

   protected final Object getParam() {
      return this.iParam;
   }

   private void setFields() {
      Fields var1 = new Fields();
      if (this.iBase != null) {
         var1.copyFieldsFrom(this.iBase);
      }

      this.assemble(var1);
      DurationField var2;
      this.iMillis = (var2 = var1.millis) != null ? var2 : super.millis();
      this.iSeconds = (var2 = var1.seconds) != null ? var2 : super.seconds();
      this.iMinutes = (var2 = var1.minutes) != null ? var2 : super.minutes();
      this.iHours = (var2 = var1.hours) != null ? var2 : super.hours();
      this.iHalfdays = (var2 = var1.halfdays) != null ? var2 : super.halfdays();
      this.iDays = (var2 = var1.days) != null ? var2 : super.days();
      this.iWeeks = (var2 = var1.weeks) != null ? var2 : super.weeks();
      this.iWeekyears = (var2 = var1.weekyears) != null ? var2 : super.weekyears();
      this.iMonths = (var2 = var1.months) != null ? var2 : super.months();
      this.iYears = (var2 = var1.years) != null ? var2 : super.years();
      this.iCenturies = (var2 = var1.centuries) != null ? var2 : super.centuries();
      this.iEras = (var2 = var1.eras) != null ? var2 : super.eras();
      DateTimeField var14;
      this.iMillisOfSecond = (var14 = var1.millisOfSecond) != null ? var14 : super.millisOfSecond();
      this.iMillisOfDay = (var14 = var1.millisOfDay) != null ? var14 : super.millisOfDay();
      this.iSecondOfMinute = (var14 = var1.secondOfMinute) != null ? var14 : super.secondOfMinute();
      this.iSecondOfDay = (var14 = var1.secondOfDay) != null ? var14 : super.secondOfDay();
      this.iMinuteOfHour = (var14 = var1.minuteOfHour) != null ? var14 : super.minuteOfHour();
      this.iMinuteOfDay = (var14 = var1.minuteOfDay) != null ? var14 : super.minuteOfDay();
      this.iHourOfDay = (var14 = var1.hourOfDay) != null ? var14 : super.hourOfDay();
      this.iClockhourOfDay = (var14 = var1.clockhourOfDay) != null ? var14 : super.clockhourOfDay();
      this.iHourOfHalfday = (var14 = var1.hourOfHalfday) != null ? var14 : super.hourOfHalfday();
      this.iClockhourOfHalfday = (var14 = var1.clockhourOfHalfday) != null ? var14 : super.clockhourOfHalfday();
      this.iHalfdayOfDay = (var14 = var1.halfdayOfDay) != null ? var14 : super.halfdayOfDay();
      this.iDayOfWeek = (var14 = var1.dayOfWeek) != null ? var14 : super.dayOfWeek();
      this.iDayOfMonth = (var14 = var1.dayOfMonth) != null ? var14 : super.dayOfMonth();
      this.iDayOfYear = (var14 = var1.dayOfYear) != null ? var14 : super.dayOfYear();
      this.iWeekOfWeekyear = (var14 = var1.weekOfWeekyear) != null ? var14 : super.weekOfWeekyear();
      this.iWeekyear = (var14 = var1.weekyear) != null ? var14 : super.weekyear();
      this.iWeekyearOfCentury = (var14 = var1.weekyearOfCentury) != null ? var14 : super.weekyearOfCentury();
      this.iMonthOfYear = (var14 = var1.monthOfYear) != null ? var14 : super.monthOfYear();
      this.iYear = (var14 = var1.year) != null ? var14 : super.year();
      this.iYearOfEra = (var14 = var1.yearOfEra) != null ? var14 : super.yearOfEra();
      this.iYearOfCentury = (var14 = var1.yearOfCentury) != null ? var14 : super.yearOfCentury();
      this.iCenturyOfEra = (var14 = var1.centuryOfEra) != null ? var14 : super.centuryOfEra();
      this.iEra = (var14 = var1.era) != null ? var14 : super.era();
      int var37;
      if (this.iBase == null) {
         var37 = 0;
      } else {
         var37 = (this.iHourOfDay == this.iBase.hourOfDay() && this.iMinuteOfHour == this.iBase.minuteOfHour() && this.iSecondOfMinute == this.iBase.secondOfMinute() && this.iMillisOfSecond == this.iBase.millisOfSecond() ? 1 : 0) | (this.iMillisOfDay == this.iBase.millisOfDay() ? 2 : 0) | (this.iYear == this.iBase.year() && this.iMonthOfYear == this.iBase.monthOfYear() && this.iDayOfMonth == this.iBase.dayOfMonth() ? 4 : 0);
      }

      this.iBaseFlags = var37;
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      this.setFields();
   }

   public static final class Fields {
      public DurationField millis;
      public DurationField seconds;
      public DurationField minutes;
      public DurationField hours;
      public DurationField halfdays;
      public DurationField days;
      public DurationField weeks;
      public DurationField weekyears;
      public DurationField months;
      public DurationField years;
      public DurationField centuries;
      public DurationField eras;
      public DateTimeField millisOfSecond;
      public DateTimeField millisOfDay;
      public DateTimeField secondOfMinute;
      public DateTimeField secondOfDay;
      public DateTimeField minuteOfHour;
      public DateTimeField minuteOfDay;
      public DateTimeField hourOfDay;
      public DateTimeField clockhourOfDay;
      public DateTimeField hourOfHalfday;
      public DateTimeField clockhourOfHalfday;
      public DateTimeField halfdayOfDay;
      public DateTimeField dayOfWeek;
      public DateTimeField dayOfMonth;
      public DateTimeField dayOfYear;
      public DateTimeField weekOfWeekyear;
      public DateTimeField weekyear;
      public DateTimeField weekyearOfCentury;
      public DateTimeField monthOfYear;
      public DateTimeField year;
      public DateTimeField yearOfEra;
      public DateTimeField yearOfCentury;
      public DateTimeField centuryOfEra;
      public DateTimeField era;

      Fields() {
      }

      public void copyFieldsFrom(Chronology var1) {
         DurationField var2;
         if (isSupported(var2 = var1.millis())) {
            this.millis = var2;
         }

         if (isSupported(var2 = var1.seconds())) {
            this.seconds = var2;
         }

         if (isSupported(var2 = var1.minutes())) {
            this.minutes = var2;
         }

         if (isSupported(var2 = var1.hours())) {
            this.hours = var2;
         }

         if (isSupported(var2 = var1.halfdays())) {
            this.halfdays = var2;
         }

         if (isSupported(var2 = var1.days())) {
            this.days = var2;
         }

         if (isSupported(var2 = var1.weeks())) {
            this.weeks = var2;
         }

         if (isSupported(var2 = var1.weekyears())) {
            this.weekyears = var2;
         }

         if (isSupported(var2 = var1.months())) {
            this.months = var2;
         }

         if (isSupported(var2 = var1.years())) {
            this.years = var2;
         }

         if (isSupported(var2 = var1.centuries())) {
            this.centuries = var2;
         }

         if (isSupported(var2 = var1.eras())) {
            this.eras = var2;
         }

         DateTimeField var14;
         if (isSupported(var14 = var1.millisOfSecond())) {
            this.millisOfSecond = var14;
         }

         if (isSupported(var14 = var1.millisOfDay())) {
            this.millisOfDay = var14;
         }

         if (isSupported(var14 = var1.secondOfMinute())) {
            this.secondOfMinute = var14;
         }

         if (isSupported(var14 = var1.secondOfDay())) {
            this.secondOfDay = var14;
         }

         if (isSupported(var14 = var1.minuteOfHour())) {
            this.minuteOfHour = var14;
         }

         if (isSupported(var14 = var1.minuteOfDay())) {
            this.minuteOfDay = var14;
         }

         if (isSupported(var14 = var1.hourOfDay())) {
            this.hourOfDay = var14;
         }

         if (isSupported(var14 = var1.clockhourOfDay())) {
            this.clockhourOfDay = var14;
         }

         if (isSupported(var14 = var1.hourOfHalfday())) {
            this.hourOfHalfday = var14;
         }

         if (isSupported(var14 = var1.clockhourOfHalfday())) {
            this.clockhourOfHalfday = var14;
         }

         if (isSupported(var14 = var1.halfdayOfDay())) {
            this.halfdayOfDay = var14;
         }

         if (isSupported(var14 = var1.dayOfWeek())) {
            this.dayOfWeek = var14;
         }

         if (isSupported(var14 = var1.dayOfMonth())) {
            this.dayOfMonth = var14;
         }

         if (isSupported(var14 = var1.dayOfYear())) {
            this.dayOfYear = var14;
         }

         if (isSupported(var14 = var1.weekOfWeekyear())) {
            this.weekOfWeekyear = var14;
         }

         if (isSupported(var14 = var1.weekyear())) {
            this.weekyear = var14;
         }

         if (isSupported(var14 = var1.weekyearOfCentury())) {
            this.weekyearOfCentury = var14;
         }

         if (isSupported(var14 = var1.monthOfYear())) {
            this.monthOfYear = var14;
         }

         if (isSupported(var14 = var1.year())) {
            this.year = var14;
         }

         if (isSupported(var14 = var1.yearOfEra())) {
            this.yearOfEra = var14;
         }

         if (isSupported(var14 = var1.yearOfCentury())) {
            this.yearOfCentury = var14;
         }

         if (isSupported(var14 = var1.centuryOfEra())) {
            this.centuryOfEra = var14;
         }

         if (isSupported(var14 = var1.era())) {
            this.era = var14;
         }

      }

      private static boolean isSupported(DurationField var0) {
         return var0 == null ? false : var0.isSupported();
      }

      private static boolean isSupported(DateTimeField var0) {
         return var0 == null ? false : var0.isSupported();
      }
   }
}
