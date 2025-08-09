package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.chrono.AbstractChronology;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Era;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;

public final class AccountingChronology extends AbstractChronology implements Serializable {
   private static final long serialVersionUID = 7291205177830286973L;
   private static final ValueRange PROLEPTIC_MONTH_RANGE_12 = ValueRange.of(-11999988L, 11999999L);
   private static final ValueRange PROLEPTIC_MONTH_RANGE_13 = ValueRange.of(-12999987L, 12999999L);
   private static final ValueRange ALIGNED_WEEK_OF_YEAR_RANGE = ValueRange.of(1L, 52L, 53L);
   static final ValueRange DAY_OF_YEAR_RANGE = ValueRange.of(1L, 364L, 371L);
   private final DayOfWeek endsOn;
   private final boolean inLastWeek;
   private final Month end;
   private final AccountingYearDivision division;
   private final int leapWeekInMonth;
   private final int yearOffset;
   private final transient int yearZeroDifference;
   private final transient ValueRange alignedWeekOfMonthRange;
   private final transient ValueRange dayOfMonthRange;
   private final transient int days0001ToIso1970;

   static AccountingChronology create(DayOfWeek endsOn, Month end, boolean inLastWeek, AccountingYearDivision division, int leapWeekInMonth, int yearOffset) {
      if (endsOn != null && end != null && division != null && leapWeekInMonth != 0) {
         if (!division.getMonthsInYearRange().isValidValue((long)leapWeekInMonth)) {
            throw new IllegalStateException("Leap week cannot not be placed in non-existent month " + leapWeekInMonth + ", range is [" + division.getMonthsInYearRange() + "].");
         } else {
            LocalDate endingLimit = inLastWeek ? LocalDate.of(0 + yearOffset, end, 1).with(TemporalAdjusters.lastDayOfMonth()) : LocalDate.of(0 + yearOffset, end, 1).with(TemporalAdjusters.lastDayOfMonth()).plusDays(3L);
            LocalDate yearZeroEnd = endingLimit.with(TemporalAdjusters.previousOrSame(endsOn));
            int yearZeroDifference = (int)yearZeroEnd.until(endingLimit, ChronoUnit.DAYS);
            int longestMonthLength = 0;
            int shortestMonthLength = Integer.MAX_VALUE;

            for(int month = 1; (long)month <= division.getMonthsInYearRange().getMaximum(); ++month) {
               int monthLength = division.getWeeksInMonth(month);
               shortestMonthLength = Math.min(shortestMonthLength, monthLength);
               longestMonthLength = Math.max(longestMonthLength, monthLength + (month == leapWeekInMonth ? 1 : 0));
            }

            ValueRange alignedWeekOfMonthRange = ValueRange.of(1L, (long)shortestMonthLength, (long)longestMonthLength);
            ValueRange dayOfMonthRange = ValueRange.of(1L, (long)(shortestMonthLength * 7), (long)(longestMonthLength * 7));
            int daysToEpoch = Math.toIntExact(0L - yearZeroEnd.plusDays(1L).toEpochDay());
            return new AccountingChronology(endsOn, end, inLastWeek, division, leapWeekInMonth, yearZeroDifference, alignedWeekOfMonthRange, dayOfMonthRange, daysToEpoch, yearOffset);
         }
      } else {
         throw new IllegalStateException("AccountingCronology cannot be built: " + (endsOn == null ? "| ending day-of-week |" : "") + (end == null ? "| month ending in/nearest to |" : "") + (division == null ? "| how year divided |" : "") + (leapWeekInMonth == 0 ? "| leap-week month |" : "") + " not set.");
      }
   }

   private AccountingChronology(DayOfWeek endsOn, Month end, boolean inLastWeek, AccountingYearDivision division, int leapWeekInMonth, int yearZeroDifference, ValueRange alignedWeekOfMonthRange, ValueRange dayOfMonthRange, int daysToEpoch, int yearOffset) {
      this.endsOn = endsOn;
      this.end = end;
      this.inLastWeek = inLastWeek;
      this.division = division;
      this.leapWeekInMonth = leapWeekInMonth;
      this.yearZeroDifference = yearZeroDifference;
      this.alignedWeekOfMonthRange = alignedWeekOfMonthRange;
      this.dayOfMonthRange = dayOfMonthRange;
      this.days0001ToIso1970 = daysToEpoch;
      this.yearOffset = yearOffset;
   }

   private Object readResolve() {
      return create(this.endsOn, this.end, this.inLastWeek, this.getDivision(), this.leapWeekInMonth, this.yearOffset);
   }

   AccountingYearDivision getDivision() {
      return this.division;
   }

   int getLeapWeekInMonth() {
      return this.leapWeekInMonth;
   }

   int getDays0001ToIso1970() {
      return this.days0001ToIso1970;
   }

   public String getId() {
      return "Accounting";
   }

   public String getCalendarType() {
      return null;
   }

   public AccountingDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public AccountingDate date(int prolepticYear, int month, int dayOfMonth) {
      return AccountingDate.of(this, prolepticYear, month, dayOfMonth);
   }

   public AccountingDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public AccountingDate dateYearDay(int prolepticYear, int dayOfYear) {
      return AccountingDate.ofYearDay(this, prolepticYear, dayOfYear);
   }

   public AccountingDate dateEpochDay(long epochDay) {
      return AccountingDate.ofEpochDay(this, epochDay);
   }

   public AccountingDate dateNow() {
      return AccountingDate.now(this);
   }

   public AccountingDate dateNow(ZoneId zone) {
      return AccountingDate.now(this, zone);
   }

   public AccountingDate dateNow(Clock clock) {
      return AccountingDate.now(this, clock);
   }

   public AccountingDate date(TemporalAccessor temporal) {
      return AccountingDate.from(this, temporal);
   }

   public ChronoLocalDateTime localDateTime(TemporalAccessor temporal) {
      return super.localDateTime(temporal);
   }

   public ChronoZonedDateTime zonedDateTime(TemporalAccessor temporal) {
      return super.zonedDateTime(temporal);
   }

   public ChronoZonedDateTime zonedDateTime(Instant instant, ZoneId zone) {
      return super.zonedDateTime(instant, zone);
   }

   public boolean isLeapYear(long prolepticYear) {
      return Math.floorMod(prolepticYear + this.getISOLeapYearCount(prolepticYear) + (long)this.yearZeroDifference, 7L) == 0L || Math.floorMod(prolepticYear + this.getISOLeapYearCount(prolepticYear + 1L) + (long)this.yearZeroDifference, 7L) == 0L;
   }

   private long getISOLeapYearCount(long prolepticYear) {
      long offsetYear = prolepticYear - (long)(this.end == Month.JANUARY ? 1 : 0) - 1L + (long)this.yearOffset;
      return Math.floorDiv(offsetYear, 4L) - Math.floorDiv(offsetYear, 100L) + Math.floorDiv(offsetYear, 400L) + (long)(this.end == Month.JANUARY && this.yearOffset == 0 ? 1 : 0);
   }

   long previousLeapYears(long prolepticYear) {
      return Math.floorDiv(prolepticYear - 1L + this.getISOLeapYearCount(prolepticYear) + (long)this.yearZeroDifference, 7L);
   }

   public int prolepticYear(Era era, int yearOfEra) {
      if (!(era instanceof AccountingEra)) {
         throw new ClassCastException("Era must be AccountingEra");
      } else {
         return era == AccountingEra.CE ? yearOfEra : 1 - yearOfEra;
      }
   }

   public AccountingEra eraOf(int era) {
      return AccountingEra.of(era);
   }

   public List eras() {
      return Arrays.asList(AccountingEra.values());
   }

   public ValueRange range(ChronoField field) {
      switch (field) {
         case ALIGNED_WEEK_OF_MONTH:
            return this.alignedWeekOfMonthRange;
         case ALIGNED_WEEK_OF_YEAR:
            return ALIGNED_WEEK_OF_YEAR_RANGE;
         case DAY_OF_MONTH:
            return this.dayOfMonthRange;
         case DAY_OF_YEAR:
            return DAY_OF_YEAR_RANGE;
         case MONTH_OF_YEAR:
            return this.getDivision().getMonthsInYearRange();
         case PROLEPTIC_MONTH:
            return this.getDivision() == AccountingYearDivision.THIRTEEN_EVEN_MONTHS_OF_4_WEEKS ? PROLEPTIC_MONTH_RANGE_13 : PROLEPTIC_MONTH_RANGE_12;
         default:
            return field.range();
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof AccountingChronology)) {
         return false;
      } else {
         AccountingChronology other = (AccountingChronology)obj;
         return this.endsOn == other.endsOn && this.inLastWeek == other.inLastWeek && this.end == other.end && this.getDivision() == other.getDivision() && this.leapWeekInMonth == other.leapWeekInMonth && this.yearOffset == other.yearOffset;
      }
   }

   public int hashCode() {
      int prime = 31;
      int result = 0;
      result = 31 * result + this.endsOn.hashCode();
      result = 31 * result + (this.inLastWeek ? 1231 : 1237);
      result = 31 * result + this.end.hashCode();
      result = 31 * result + this.leapWeekInMonth;
      result = 31 * result + this.getDivision().hashCode();
      result = 31 * result + this.yearOffset;
      return result;
   }

   public String toString() {
      StringBuilder bld = new StringBuilder(30);
      bld.append(this.getId()).append(" calendar ends on ").append(this.endsOn).append(this.inLastWeek ? " in last week of " : " nearest end of ").append(this.end).append(", year divided in ").append(this.getDivision()).append(" with leap-week in month ").append(this.leapWeekInMonth).append(this.yearOffset == 0 ? " ending in the given ISO year" : " starting in the given ISO year");
      return bld.toString();
   }
}
