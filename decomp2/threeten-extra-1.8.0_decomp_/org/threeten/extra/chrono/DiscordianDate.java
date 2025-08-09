package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;

public final class DiscordianDate extends AbstractDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -4340508226506164852L;
   private static final int DISCORDIAN_1167_TO_ISO_1970 = 719162;
   private static final int DAYS_PER_SHORT_CYCLE = 1461;
   private static final int DAYS_PER_CYCLE = 36524;
   private static final int DAYS_PER_LONG_CYCLE = 146097;
   private static final int ST_TIBS_OFFSET = 60;
   private final int prolepticYear;
   private final short month;
   private final short day;

   public static DiscordianDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static DiscordianDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static DiscordianDate now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(now.toEpochDay());
   }

   public static DiscordianDate of(int prolepticYear, int month, int dayOfMonth) {
      return create(prolepticYear, month, dayOfMonth);
   }

   public static DiscordianDate from(TemporalAccessor temporal) {
      return temporal instanceof DiscordianDate ? (DiscordianDate)temporal : ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static DiscordianDate ofYearDay(int prolepticYear, int dayOfYear) {
      DiscordianChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      ChronoField.DAY_OF_YEAR.checkValidValue((long)dayOfYear);
      boolean leap = DiscordianChronology.INSTANCE.isLeapYear((long)prolepticYear);
      if (dayOfYear == 366 && !leap) {
         throw new DateTimeException("Invalid date 'DayOfYear 366' as '" + prolepticYear + "' is not a leap year");
      } else {
         if (leap) {
            if (dayOfYear == 60) {
               return new DiscordianDate(prolepticYear, 0, 0);
            }

            if (dayOfYear > 60) {
               --dayOfYear;
            }
         }

         int month = (dayOfYear - 1) / 73 + 1;
         int dayOfMonth = (dayOfYear - 1) % 73 + 1;
         return new DiscordianDate(prolepticYear, month, dayOfMonth);
      }
   }

   static DiscordianDate ofEpochDay(long epochDay) {
      DiscordianChronology.EPOCH_DAY_RANGE.checkValidValue(epochDay, ChronoField.EPOCH_DAY);
      long discordianEpochDay = epochDay + 719162L;
      long longCycle = Math.floorDiv(discordianEpochDay, 146097L);
      long daysInLongCycle = Math.floorMod(discordianEpochDay, 146097L);
      if (daysInLongCycle == 146096L) {
         int year = (int)(longCycle * 400L) + 400;
         return ofYearDay(year + 1166, 366);
      } else {
         int cycle = (int)daysInLongCycle / '躬';
         int dayInCycle = (int)daysInLongCycle % '躬';
         int shortCycle = dayInCycle / 1461;
         int dayInShortCycle = dayInCycle % 1461;
         if (dayInShortCycle == 1460) {
            int year = (int)(longCycle * 400L) + cycle * 100 + shortCycle * 4 + 4;
            return ofYearDay(year + 1166, 366);
         } else {
            int year = (int)(longCycle * 400L) + cycle * 100 + shortCycle * 4 + dayInShortCycle / 365 + 1;
            int dayOfYear = dayInShortCycle % 365 + 1;
            return ofYearDay(year + 1166, dayOfYear);
         }
      }
   }

   private static DiscordianDate resolvePreviousValid(int prolepticYear, int month, int day) {
      switch (month) {
         case 0:
            day = 0;
            if (DiscordianChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
               break;
            }

            month = 1;
         default:
            if (day == 0) {
               day = 60;
            }
      }

      return new DiscordianDate(prolepticYear, month, day);
   }

   private static long getLeapYearsBefore(long year) {
      long offsetYear = year - 1166L - 1L;
      return Math.floorDiv(offsetYear, 4L) - Math.floorDiv(offsetYear, 100L) + Math.floorDiv(offsetYear, 400L);
   }

   static DiscordianDate create(int prolepticYear, int month, int dayOfMonth) {
      DiscordianChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      DiscordianChronology.MONTH_OF_YEAR_RANGE.checkValidValue((long)month, ChronoField.MONTH_OF_YEAR);
      DiscordianChronology.DAY_OF_MONTH_RANGE.checkValidValue((long)dayOfMonth, ChronoField.DAY_OF_MONTH);
      if (month == 0 || dayOfMonth == 0) {
         if (month != 0 || dayOfMonth != 0) {
            throw new DateTimeException("Invalid date '" + month + " " + dayOfMonth + "' as St. Tib's Day is the only special day inserted in a non-existent month.");
         }

         if (!DiscordianChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
            throw new DateTimeException("Invalid date 'St. Tibs Day' as '" + prolepticYear + "' is not a leap year");
         }
      }

      return new DiscordianDate(prolepticYear, month, dayOfMonth);
   }

   private DiscordianDate(int prolepticYear, int month, int dayOfMonth) {
      this.prolepticYear = prolepticYear;
      this.month = (short)month;
      this.day = (short)dayOfMonth;
   }

   private Object readResolve() {
      return create(this.prolepticYear, this.month, this.day);
   }

   int getProlepticYear() {
      return this.prolepticYear;
   }

   int getMonth() {
      return this.month;
   }

   int getDayOfMonth() {
      return this.day;
   }

   int getDayOfYear() {
      if (this.month == 0 && this.day == 0) {
         return 60;
      } else {
         int dayOfYear = (this.month - 1) * 73 + this.day;
         return dayOfYear + (dayOfYear >= 60 && this.isLeapYear() ? 1 : 0);
      }
   }

   AbstractDate withDayOfYear(int value) {
      return this.plusDays((long)(value - this.getDayOfYear()));
   }

   int lengthOfWeek() {
      return 5;
   }

   int lengthOfYearInMonths() {
      return 5;
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return this.month == 0 ? ValueRange.of(0L, 0L) : ValueRange.of(1L, 15L);
   }

   DiscordianDate resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(newYear, newMonth, dayOfMonth);
   }

   public ValueRange range(TemporalField field) {
      if (field instanceof ChronoField && this.isSupported(field)) {
         ChronoField f = (ChronoField)field;
         switch (f) {
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
               return this.month == 0 ? ValueRange.of(0L, 0L) : ValueRange.of(1L, 5L);
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
               return ValueRange.of(this.isLeapYear() ? 0L : 1L, 5L);
            case ALIGNED_WEEK_OF_YEAR:
               return ValueRange.of(this.isLeapYear() ? 0L : 1L, 73L);
            case DAY_OF_MONTH:
               return this.month == 0 ? ValueRange.of(0L, 0L) : ValueRange.of(1L, 73L);
            case DAY_OF_WEEK:
               return this.month == 0 ? ValueRange.of(0L, 0L) : ValueRange.of(1L, 5L);
            case MONTH_OF_YEAR:
               return ValueRange.of(this.isLeapYear() ? 0L : 1L, 5L);
         }
      }

      return super.range(field);
   }

   public long getLong(TemporalField field) {
      if (field instanceof ChronoField) {
         switch ((ChronoField)field) {
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
               return this.month == 0 ? 0L : super.getLong(field);
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
               return (long)this.getDayOfWeek();
            case ALIGNED_WEEK_OF_YEAR:
               if (this.month == 0) {
                  return 0L;
               }

               return (long)((this.getDayOfYear() - (this.getDayOfYear() >= 60 && this.isLeapYear() ? 1 : 0) - 1) / 5 + 1);
            case DAY_OF_MONTH:
            case DAY_OF_WEEK:
            case MONTH_OF_YEAR:
            default:
               break;
            case ALIGNED_WEEK_OF_MONTH:
               return this.month == 0 ? 0L : super.getLong(field);
         }
      }

      return super.getLong(field);
   }

   int getDayOfWeek() {
      if (this.month == 0) {
         return 0;
      } else {
         int dayOfYear = this.getDayOfYear() - (this.getDayOfYear() >= 60 && this.isLeapYear() ? 1 : 0);
         return (dayOfYear - 1) % 5 + 1;
      }
   }

   long getProlepticMonth() {
      return (long)(this.prolepticYear * 5 + (this.month == 0 ? 1 : this.month) - 1);
   }

   long getProlepticWeek() {
      return (long)this.prolepticYear * 73L + (this.month == 0 ? 12L : this.getLong(ChronoField.ALIGNED_WEEK_OF_YEAR)) - 1L;
   }

   public DiscordianChronology getChronology() {
      return DiscordianChronology.INSTANCE;
   }

   public DiscordianEra getEra() {
      return DiscordianEra.YOLD;
   }

   public int lengthOfMonth() {
      return this.month == 0 ? 1 : 73;
   }

   public DiscordianDate with(TemporalAdjuster adjuster) {
      return (DiscordianDate)adjuster.adjustInto(this);
   }

   public DiscordianDate with(TemporalField field, long newValue) {
      if (field instanceof ChronoField) {
         ChronoField f = (ChronoField)field;
         DiscordianChronology.INSTANCE.range(f).checkValidValue(newValue, f);
         int nvalue = (int)newValue;
         if (nvalue == 0 && this.isLeapYear()) {
            switch (f) {
               case ALIGNED_DAY_OF_WEEK_IN_MONTH:
               case ALIGNED_DAY_OF_WEEK_IN_YEAR:
               case ALIGNED_WEEK_OF_YEAR:
               case DAY_OF_MONTH:
               case DAY_OF_WEEK:
               case MONTH_OF_YEAR:
               case ALIGNED_WEEK_OF_MONTH:
                  if (this.month == 0) {
                     return this;
                  }

                  return create(this.prolepticYear, 0, 0);
            }
         }

         if (this.month == 0) {
            switch (f) {
               case YEAR:
               case YEAR_OF_ERA:
                  if (DiscordianChronology.INSTANCE.isLeapYear((long)nvalue)) {
                     return create(nvalue, 0, 0);
                  }
               default:
                  return create(this.prolepticYear, 1, 60).with(field, newValue);
            }
         }

         this.range(f).checkValidValue(newValue, f);
         switch (f) {
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
            case DAY_OF_WEEK:
               if (this.month == 1 && this.day >= 56 && this.day < 61 && this.isLeapYear()) {
                  int currentDayOfWeek = this.getDayOfWeek();
                  if (currentDayOfWeek < 5 && nvalue == 5) {
                     return (DiscordianDate)this.plusDays((long)(nvalue - currentDayOfWeek + 1));
                  }

                  if (currentDayOfWeek == 5 && nvalue < 5) {
                     return (DiscordianDate)this.plusDays((long)(nvalue - currentDayOfWeek - 1));
                  }
               }
               break;
            case ALIGNED_WEEK_OF_YEAR:
            case ALIGNED_WEEK_OF_MONTH:
               if ((this.month == 1 || field == ChronoField.ALIGNED_WEEK_OF_YEAR) && this.isLeapYear()) {
                  int alignedWeek = (int)this.getLong(field);
                  int currentDayOfWeek = this.getDayOfWeek();
                  if ((alignedWeek > 12 || alignedWeek == 12 && currentDayOfWeek == 5) && (nvalue < 12 || nvalue == 12 && currentDayOfWeek < 5)) {
                     return (DiscordianDate)this.plusDays((newValue - (long)alignedWeek) * 5L - 1L);
                  }

                  if ((nvalue > 12 || nvalue == 12 && currentDayOfWeek == 5) && (alignedWeek < 12 || alignedWeek == 12 && currentDayOfWeek < 5)) {
                     return (DiscordianDate)this.plusDays((newValue - (long)alignedWeek) * (long)this.lengthOfWeek() + 1L);
                  }
               }
            case DAY_OF_MONTH:
            case MONTH_OF_YEAR:
         }
      }

      return (DiscordianDate)super.with(field, newValue);
   }

   public DiscordianDate plus(TemporalAmount amount) {
      return (DiscordianDate)amount.addTo(this);
   }

   public DiscordianDate plus(long amountToAdd, TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         ChronoUnit f = (ChronoUnit)unit;
         switch (f) {
            case WEEKS:
               return this.plusWeeks(amountToAdd);
            case MONTHS:
               return this.plusMonths(amountToAdd);
         }
      }

      return (DiscordianDate)super.plus(amountToAdd, unit);
   }

   DiscordianDate plusMonths(long months) {
      if (months == 0L) {
         return this;
      } else {
         long calcEm = Math.addExact(this.getProlepticMonth(), months);
         int newYear = Math.toIntExact(Math.floorDiv(calcEm, 5L));
         int newMonth = (int)(Math.floorMod(calcEm, 5L) + 1L);
         if (this.month == 0 && newMonth == 1) {
            newMonth = 0;
         }

         return this.resolvePrevious(newYear, newMonth, this.day);
      }
   }

   DiscordianDate plusWeeks(long weeks) {
      if (weeks == 0L) {
         return this;
      } else {
         long calcEm = Math.addExact(this.getProlepticWeek(), weeks);
         int newYear = Math.toIntExact(Math.floorDiv(calcEm, 73L));
         int newDayOfYear = (int)Math.floorMod(calcEm, 73L) * 5 + (this.month == 0 ? 5 : this.get(ChronoField.DAY_OF_WEEK));
         if (DiscordianChronology.INSTANCE.isLeapYear((long)newYear) && (newDayOfYear > 60 || newDayOfYear == 60 && this.month != 0)) {
            ++newDayOfYear;
         }

         return ofYearDay(newYear, newDayOfYear);
      }
   }

   public DiscordianDate minus(TemporalAmount amount) {
      return (DiscordianDate)amount.subtractFrom(this);
   }

   public DiscordianDate minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public ChronoLocalDateTime atTime(LocalTime localTime) {
      return super.atTime(localTime);
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      return this.until((AbstractDate)from(endExclusive), unit);
   }

   long until(AbstractDate end, TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case WEEKS:
               return this.weeksUntil(from(end));
         }
      }

      return super.until(end, unit);
   }

   long weeksUntil(DiscordianDate end) {
      long weekStart = this.getProlepticWeek() * 8L;
      long weekEnd = end.getProlepticWeek() * 8L;
      long packed1 = weekStart + (long)(this.month == 0 && end.month != 0 ? (weekEnd > weekStart ? 5 : 4) : this.getDayOfWeek());
      long packed2 = weekEnd + (long)(end.month == 0 && this.month != 0 ? (weekStart > weekEnd ? 5 : 4) : end.getDayOfWeek());
      return (packed2 - packed1) / 8L;
   }

   long monthsUntil(AbstractDate end) {
      DiscordianDate discordianEnd = from(end);
      long monthStart = this.getProlepticMonth() * 128L;
      long monthEnd = discordianEnd.getProlepticMonth() * 128L;
      long packed1 = monthStart + (long)(this.month == 0 && discordianEnd.month != 0 ? (monthEnd > monthStart ? 60 : 59) : this.getDayOfMonth());
      long packed2 = monthEnd + (long)(discordianEnd.month == 0 && this.month != 0 ? (monthStart > monthEnd ? 60 : 59) : end.getDayOfMonth());
      return (packed2 - packed1) / 128L;
   }

   public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
      long monthsUntil = this.monthsUntil(from(endDateExclusive));
      int years = Math.toIntExact(monthsUntil / 5L);
      int months = (int)(monthsUntil % 5L);
      int days = (int)this.plusMonths(monthsUntil).daysUntil(endDateExclusive);
      return DiscordianChronology.INSTANCE.period(years, months, days);
   }

   public long toEpochDay() {
      long year = (long)this.prolepticYear;
      long discordianEpochDay = (year - 1166L - 1L) * 365L + getLeapYearsBefore(year) + (long)(this.getDayOfYear() - 1);
      return discordianEpochDay - 719162L;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder(30);
      buf.append(DiscordianChronology.INSTANCE.toString()).append(" ").append(DiscordianEra.YOLD).append(" ").append(this.prolepticYear);
      if (this.month == 0) {
         buf.append(" St. Tib's Day");
      } else {
         buf.append("-").append(this.month).append(this.day < 10 ? "-0" : "-").append(this.day);
      }

      return buf.toString();
   }
}
