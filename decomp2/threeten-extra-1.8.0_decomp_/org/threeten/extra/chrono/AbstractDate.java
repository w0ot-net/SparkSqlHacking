package org.threeten.extra.chrono;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

abstract class AbstractDate implements ChronoLocalDate {
   abstract int getProlepticYear();

   abstract int getMonth();

   abstract int getDayOfMonth();

   abstract int getDayOfYear();

   AbstractDate withDayOfYear(int value) {
      return this.plusDays((long)(value - this.getDayOfYear()));
   }

   int lengthOfWeek() {
      return 7;
   }

   int lengthOfYearInMonths() {
      return 12;
   }

   abstract ValueRange rangeAlignedWeekOfMonth();

   abstract AbstractDate resolvePrevious(int var1, int var2, int var3);

   AbstractDate resolveEpochDay(long epochDay) {
      return (AbstractDate)this.getChronology().dateEpochDay(epochDay);
   }

   public ValueRange range(TemporalField field) {
      if (field instanceof ChronoField) {
         if (this.isSupported(field)) {
            return this.rangeChrono((ChronoField)field);
         } else {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return field.rangeRefinedBy(this);
      }
   }

   ValueRange rangeChrono(ChronoField field) {
      switch (field) {
         case DAY_OF_MONTH:
            return ValueRange.of(1L, (long)this.lengthOfMonth());
         case DAY_OF_YEAR:
            return ValueRange.of(1L, (long)this.lengthOfYear());
         case ALIGNED_WEEK_OF_MONTH:
            return this.rangeAlignedWeekOfMonth();
         default:
            return this.getChronology().range(field);
      }
   }

   public long getLong(TemporalField field) {
      if (field instanceof ChronoField) {
         switch ((ChronoField)field) {
            case DAY_OF_MONTH:
               return (long)this.getDayOfMonth();
            case DAY_OF_YEAR:
               return (long)this.getDayOfYear();
            case ALIGNED_WEEK_OF_MONTH:
               return (long)this.getAlignedWeekOfMonth();
            case DAY_OF_WEEK:
               return (long)this.getDayOfWeek();
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
               return (long)this.getAlignedDayOfWeekInMonth();
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
               return (long)this.getAlignedDayOfWeekInYear();
            case EPOCH_DAY:
               return this.toEpochDay();
            case ALIGNED_WEEK_OF_YEAR:
               return (long)this.getAlignedWeekOfYear();
            case MONTH_OF_YEAR:
               return (long)this.getMonth();
            case PROLEPTIC_MONTH:
               return this.getProlepticMonth();
            case YEAR_OF_ERA:
               return (long)this.getYearOfEra();
            case YEAR:
               return (long)this.getProlepticYear();
            case ERA:
               return (long)(this.getProlepticYear() >= 1 ? 1 : 0);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return field.getFrom(this);
      }
   }

   int getAlignedDayOfWeekInMonth() {
      return (this.getDayOfMonth() - 1) % this.lengthOfWeek() + 1;
   }

   int getAlignedDayOfWeekInYear() {
      return (this.getDayOfYear() - 1) % this.lengthOfWeek() + 1;
   }

   int getAlignedWeekOfMonth() {
      return (this.getDayOfMonth() - 1) / this.lengthOfWeek() + 1;
   }

   int getAlignedWeekOfYear() {
      return (this.getDayOfYear() - 1) / this.lengthOfWeek() + 1;
   }

   int getDayOfWeek() {
      return (int)(Math.floorMod(this.toEpochDay() + 3L, 7L) + 1L);
   }

   long getProlepticMonth() {
      return (long)(this.getProlepticYear() * this.lengthOfYearInMonths() + this.getMonth() - 1);
   }

   int getYearOfEra() {
      return this.getProlepticYear() >= 1 ? this.getProlepticYear() : 1 - this.getProlepticYear();
   }

   public AbstractDate with(TemporalField field, long newValue) {
      if (field instanceof ChronoField) {
         ChronoField f = (ChronoField)field;
         this.getChronology().range(f).checkValidValue(newValue, f);
         int nvalue = (int)newValue;
         switch (f) {
            case DAY_OF_MONTH:
               return this.resolvePrevious(this.getProlepticYear(), this.getMonth(), nvalue);
            case DAY_OF_YEAR:
               return this.withDayOfYear(nvalue);
            case ALIGNED_WEEK_OF_MONTH:
               return this.plusDays((newValue - this.getLong(ChronoField.ALIGNED_WEEK_OF_MONTH)) * (long)this.lengthOfWeek());
            case DAY_OF_WEEK:
               return this.plusDays(newValue - (long)this.getDayOfWeek());
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
               return this.plusDays(newValue - this.getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
               return this.plusDays(newValue - this.getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case EPOCH_DAY:
               return this.resolveEpochDay(newValue);
            case ALIGNED_WEEK_OF_YEAR:
               return this.plusDays((newValue - this.getLong(ChronoField.ALIGNED_WEEK_OF_YEAR)) * (long)this.lengthOfWeek());
            case MONTH_OF_YEAR:
               return this.resolvePrevious(this.getProlepticYear(), nvalue, this.getDayOfMonth());
            case PROLEPTIC_MONTH:
               return this.plusMonths(newValue - this.getProlepticMonth());
            case YEAR_OF_ERA:
               return this.resolvePrevious(this.getProlepticYear() >= 1 ? nvalue : 1 - nvalue, this.getMonth(), this.getDayOfMonth());
            case YEAR:
               return this.resolvePrevious(nvalue, this.getMonth(), this.getDayOfMonth());
            case ERA:
               return newValue == this.getLong(ChronoField.ERA) ? this : this.resolvePrevious(1 - this.getProlepticYear(), this.getMonth(), this.getDayOfMonth());
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return (AbstractDate)field.adjustInto(this, newValue);
      }
   }

   public AbstractDate plus(long amountToAdd, TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         ChronoUnit f = (ChronoUnit)unit;
         switch (f) {
            case DAYS:
               return this.plusDays(amountToAdd);
            case WEEKS:
               return this.plusWeeks(amountToAdd);
            case MONTHS:
               return this.plusMonths(amountToAdd);
            case YEARS:
               return this.plusYears(amountToAdd);
            case DECADES:
               return this.plusYears(Math.multiplyExact(amountToAdd, 10L));
            case CENTURIES:
               return this.plusYears(Math.multiplyExact(amountToAdd, 100L));
            case MILLENNIA:
               return this.plusYears(Math.multiplyExact(amountToAdd, 1000L));
            case ERAS:
               return this.with(ChronoField.ERA, Math.addExact(this.getLong(ChronoField.ERA), amountToAdd));
            default:
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
         }
      } else {
         return (AbstractDate)unit.addTo(this, amountToAdd);
      }
   }

   AbstractDate plusYears(long yearsToAdd) {
      if (yearsToAdd == 0L) {
         return this;
      } else {
         int newYear = ChronoField.YEAR.checkValidIntValue(Math.addExact((long)this.getProlepticYear(), yearsToAdd));
         return this.resolvePrevious(newYear, this.getMonth(), this.getDayOfMonth());
      }
   }

   AbstractDate plusMonths(long months) {
      if (months == 0L) {
         return this;
      } else {
         long curEm = this.getProlepticMonth();
         long calcEm = Math.addExact(curEm, months);
         int newYear = Math.toIntExact(Math.floorDiv(calcEm, (long)this.lengthOfYearInMonths()));
         int newMonth = (int)(Math.floorMod(calcEm, (long)this.lengthOfYearInMonths()) + 1L);
         return this.resolvePrevious(newYear, newMonth, this.getDayOfMonth());
      }
   }

   AbstractDate plusWeeks(long amountToAdd) {
      return this.plusDays(Math.multiplyExact(amountToAdd, (long)this.lengthOfWeek()));
   }

   AbstractDate plusDays(long days) {
      return days == 0L ? this : this.resolveEpochDay(Math.addExact(this.toEpochDay(), days));
   }

   long until(AbstractDate end, TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case DAYS:
               return this.daysUntil(end);
            case WEEKS:
               return this.weeksUntil(end);
            case MONTHS:
               return this.monthsUntil(end);
            case YEARS:
               return this.monthsUntil(end) / (long)this.lengthOfYearInMonths();
            case DECADES:
               return this.monthsUntil(end) / (long)(this.lengthOfYearInMonths() * 10);
            case CENTURIES:
               return this.monthsUntil(end) / (long)(this.lengthOfYearInMonths() * 100);
            case MILLENNIA:
               return this.monthsUntil(end) / (long)(this.lengthOfYearInMonths() * 1000);
            case ERAS:
               return end.getLong(ChronoField.ERA) - this.getLong(ChronoField.ERA);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
         }
      } else {
         return unit.between(this, end);
      }
   }

   long daysUntil(ChronoLocalDate end) {
      return end.toEpochDay() - this.toEpochDay();
   }

   long weeksUntil(AbstractDate end) {
      return this.daysUntil(end) / (long)this.lengthOfWeek();
   }

   long monthsUntil(AbstractDate end) {
      long packed1 = this.getProlepticMonth() * 256L + (long)this.getDayOfMonth();
      long packed2 = end.getProlepticMonth() * 256L + (long)end.getDayOfMonth();
      return (packed2 - packed1) / 256L;
   }

   ChronoPeriod doUntil(AbstractDate end) {
      long totalMonths = end.getProlepticMonth() - this.getProlepticMonth();
      int days = end.getDayOfMonth() - this.getDayOfMonth();
      if (totalMonths > 0L && days < 0) {
         --totalMonths;
         AbstractDate calcDate = this.plusMonths(totalMonths);
         days = (int)(end.toEpochDay() - calcDate.toEpochDay());
      } else if (totalMonths < 0L && days > 0) {
         ++totalMonths;
         days -= end.lengthOfMonth();
      }

      long years = totalMonths / (long)this.lengthOfYearInMonths();
      int months = (int)(totalMonths % (long)this.lengthOfYearInMonths());
      return this.getChronology().period(Math.toIntExact(years), months, days);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         AbstractDate otherDate = (AbstractDate)obj;
         return this.getProlepticYear() == otherDate.getProlepticYear() && this.getMonth() == otherDate.getMonth() && this.getDayOfMonth() == otherDate.getDayOfMonth();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.getChronology().getId().hashCode() ^ this.getProlepticYear() & -2048 ^ (this.getProlepticYear() << 11) + (this.getMonth() << 6) + this.getDayOfMonth();
   }

   public String toString() {
      StringBuilder buf = new StringBuilder(30);
      buf.append(this.getChronology().toString()).append(" ").append(this.getEra()).append(" ").append(this.getYearOfEra()).append(this.getMonth() < 10 ? "-0" : "-").append(this.getMonth()).append(this.getDayOfMonth() < 10 ? "-0" : "-").append(this.getDayOfMonth());
      return buf.toString();
   }
}
