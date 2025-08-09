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
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.Objects;

public final class AccountingDate extends AbstractDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -126140328940081914L;
   private static final int DAYS_IN_WEEK = 7;
   private static final int WEEKS_IN_YEAR = 52;
   private static final int DAYS_PER_LONG_CYCLE = 146097;
   private final AccountingChronology chronology;
   private final int prolepticYear;
   private final short month;
   private final short day;

   public static AccountingDate now(AccountingChronology chronology) {
      return now(chronology, Clock.systemDefaultZone());
   }

   public static AccountingDate now(AccountingChronology chronology, ZoneId zone) {
      return now(chronology, Clock.system(zone));
   }

   public static AccountingDate now(AccountingChronology chronology, Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(chronology, now.toEpochDay());
   }

   public static AccountingDate of(AccountingChronology chronology, int prolepticYear, int month, int dayOfMonth) {
      return create(chronology, prolepticYear, month, dayOfMonth);
   }

   public static AccountingDate from(AccountingChronology chronology, TemporalAccessor temporal) {
      return temporal instanceof AccountingDate && ((AccountingDate)temporal).getChronology().equals(chronology) ? (AccountingDate)temporal : ofEpochDay(chronology, temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static AccountingDate ofYearDay(AccountingChronology chronology, int prolepticYear, int dayOfYear) {
      Objects.requireNonNull(chronology, "A previously setup chronology is required.");
      ChronoField.YEAR.checkValidValue((long)prolepticYear);
      AccountingChronology.DAY_OF_YEAR_RANGE.checkValidValue((long)dayOfYear, ChronoField.DAY_OF_YEAR);
      boolean leap = chronology.isLeapYear((long)prolepticYear);
      if (dayOfYear > 364 && !leap) {
         throw new DateTimeException("Invalid date 'DayOfYear " + dayOfYear + "' as '" + prolepticYear + "' is not a leap year");
      } else {
         int month = leap ? chronology.getDivision().getMonthFromElapsedWeeks((dayOfYear - 1) / 7, chronology.getLeapWeekInMonth()) : chronology.getDivision().getMonthFromElapsedWeeks((dayOfYear - 1) / 7);
         int dayOfMonth = dayOfYear - (leap ? chronology.getDivision().getWeeksAtStartOfMonth(month, chronology.getLeapWeekInMonth()) : chronology.getDivision().getWeeksAtStartOfMonth(month)) * 7;
         return new AccountingDate(chronology, prolepticYear, month, dayOfMonth);
      }
   }

   static AccountingDate ofEpochDay(AccountingChronology chronology, long epochDay) {
      ChronoField.EPOCH_DAY.range().checkValidValue(epochDay, ChronoField.EPOCH_DAY);
      long accountingEpochDay = epochDay + (long)chronology.getDays0001ToIso1970();
      int longCycle = (int)Math.floorDiv(accountingEpochDay, 146097L);
      int daysInLongCycle = (int)Math.floorMod(accountingEpochDay, 146097L);
      int year = (daysInLongCycle - (daysInLongCycle / 365 + daysInLongCycle / 1461 - daysInLongCycle / '躬') / 7) / 364;
      int yearStart = (int)((long)(52 * (year - 1)) + chronology.previousLeapYears((long)year)) * 7;
      if (yearStart > daysInLongCycle) {
         --year;
         yearStart -= (52 + (chronology.isLeapYear((long)year) ? 1 : 0)) * 7;
      } else if (daysInLongCycle - yearStart >= (52 + (chronology.isLeapYear((long)year) ? 1 : 0)) * 7) {
         yearStart += (52 + (chronology.isLeapYear((long)year) ? 1 : 0)) * 7;
         ++year;
      }

      return ofYearDay(chronology, year + 400 * longCycle, daysInLongCycle - yearStart + 1);
   }

   private static AccountingDate resolvePreviousValid(AccountingChronology chronology, int prolepticYear, int month, int day) {
      day = Math.min(day, lengthOfMonth(chronology, prolepticYear, month));
      return new AccountingDate(chronology, prolepticYear, month, day);
   }

   private static int lengthOfMonth(AccountingChronology chronology, int prolepticYear, int month) {
      return (chronology.isLeapYear((long)prolepticYear) ? chronology.getDivision().getWeeksInMonth(month, chronology.getLeapWeekInMonth()) : chronology.getDivision().getWeeksInMonth(month)) * 7;
   }

   static AccountingDate create(AccountingChronology chronology, int prolepticYear, int month, int dayOfMonth) {
      Objects.requireNonNull(chronology, "A previously setup chronology is required.");
      ChronoField.YEAR.checkValidValue((long)prolepticYear);
      chronology.range(ChronoField.MONTH_OF_YEAR).checkValidValue((long)month, ChronoField.MONTH_OF_YEAR);
      if (dayOfMonth >= 1 && dayOfMonth <= lengthOfMonth(chronology, prolepticYear, month)) {
         return new AccountingDate(chronology, prolepticYear, month, dayOfMonth);
      } else if (month == chronology.getLeapWeekInMonth() && dayOfMonth < (chronology.getDivision().getWeeksInMonth(month) + 1) * 7 && !chronology.isLeapYear((long)prolepticYear)) {
         throw new DateTimeException("Invalid date '" + month + "/" + dayOfMonth + "' as '" + prolepticYear + "' is not a leap year");
      } else {
         throw new DateTimeException("Invalid date '" + month + "/" + dayOfMonth + "'");
      }
   }

   private AccountingDate(AccountingChronology chronology, int prolepticYear, int month, int dayOfMonth) {
      this.chronology = chronology;
      this.prolepticYear = prolepticYear;
      this.month = (short)month;
      this.day = (short)dayOfMonth;
   }

   private Object readResolve() {
      return create(this.chronology, this.prolepticYear, this.month, this.day);
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
      int weeksAtStartOfMonth = this.isLeapYear() ? this.chronology.getDivision().getWeeksAtStartOfMonth(this.month, this.chronology.getLeapWeekInMonth()) : this.chronology.getDivision().getWeeksAtStartOfMonth(this.month);
      return weeksAtStartOfMonth * 7 + this.day;
   }

   AbstractDate withDayOfYear(int value) {
      return this.plusDays((long)(value - this.getDayOfYear()));
   }

   int lengthOfYearInMonths() {
      return this.chronology.getDivision().lengthOfYearInMonths();
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return ValueRange.of(1L, (long)((this.lengthOfMonth() - 1) / 7 + 1));
   }

   AccountingDate resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(this.chronology, newYear, newMonth, dayOfMonth);
   }

   public AccountingChronology getChronology() {
      return this.chronology;
   }

   public int lengthOfMonth() {
      return lengthOfMonth(this.chronology, this.prolepticYear, this.month);
   }

   public int lengthOfYear() {
      return (52 + (this.isLeapYear() ? 1 : 0)) * 7;
   }

   public AccountingDate with(TemporalAdjuster adjuster) {
      return (AccountingDate)adjuster.adjustInto(this);
   }

   public AccountingDate with(TemporalField field, long newValue) {
      return (AccountingDate)super.with(field, newValue);
   }

   public AccountingDate plus(TemporalAmount amount) {
      return (AccountingDate)amount.addTo(this);
   }

   public AccountingDate plus(long amountToAdd, TemporalUnit unit) {
      return (AccountingDate)super.plus(amountToAdd, unit);
   }

   public AccountingDate minus(TemporalAmount amount) {
      return (AccountingDate)amount.subtractFrom(this);
   }

   public AccountingDate minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public ChronoLocalDateTime atTime(LocalTime localTime) {
      return super.atTime(localTime);
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      return super.until(from(this.chronology, endExclusive), unit);
   }

   public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
      return super.doUntil(from(this.chronology, endDateExclusive));
   }

   public long toEpochDay() {
      long year = (long)this.prolepticYear;
      long accountingEpochDay = ((year - 1L) * 52L + this.chronology.previousLeapYears(year)) * 7L + (long)(this.getDayOfYear() - 1);
      return accountingEpochDay - (long)this.chronology.getDays0001ToIso1970();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof AccountingDate)) {
         return false;
      } else {
         AccountingDate other = (AccountingDate)obj;
         return this.prolepticYear == other.prolepticYear && this.month == other.month && this.day == other.day && this.chronology.equals(other.chronology);
      }
   }

   public int hashCode() {
      return this.chronology.hashCode() ^ this.prolepticYear & -2048 ^ (this.prolepticYear << 11) + (this.month << 6) + this.day;
   }
}
