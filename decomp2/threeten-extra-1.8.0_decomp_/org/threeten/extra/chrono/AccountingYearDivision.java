package org.threeten.extra.chrono;

import java.time.DateTimeException;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;
import java.util.Arrays;

public enum AccountingYearDivision {
   QUARTERS_OF_PATTERN_4_4_5_WEEKS(new int[]{4, 4, 5, 4, 4, 5, 4, 4, 5, 4, 4, 5}),
   QUARTERS_OF_PATTERN_4_5_4_WEEKS(new int[]{4, 5, 4, 4, 5, 4, 4, 5, 4, 4, 5, 4}),
   QUARTERS_OF_PATTERN_5_4_4_WEEKS(new int[]{5, 4, 4, 5, 4, 4, 5, 4, 4, 5, 4, 4}),
   THIRTEEN_EVEN_MONTHS_OF_4_WEEKS(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4});

   private final int[] weeksInMonths;
   private final ValueRange monthsInYearRange;
   private final int[] elapsedWeeks;

   private AccountingYearDivision(int[] weeksInMonths) {
      this.weeksInMonths = weeksInMonths;
      this.monthsInYearRange = ValueRange.of(1L, (long)weeksInMonths.length);
      this.elapsedWeeks = new int[weeksInMonths.length];

      for(int i = 1; i < weeksInMonths.length; ++i) {
         this.elapsedWeeks[i] = this.elapsedWeeks[i - 1] + weeksInMonths[i - 1];
      }

   }

   ValueRange getMonthsInYearRange() {
      return this.monthsInYearRange;
   }

   int lengthOfYearInMonths() {
      return this.weeksInMonths.length;
   }

   int getWeeksInMonth(int month) {
      return this.getWeeksInMonth(month, 0);
   }

   int getWeeksInMonth(int month, int leapWeekInMonth) {
      month = this.monthsInYearRange.checkValidIntValue((long)month, ChronoField.MONTH_OF_YEAR);
      leapWeekInMonth = leapWeekInMonth == 0 ? 0 : this.monthsInYearRange.checkValidIntValue((long)leapWeekInMonth, ChronoField.MONTH_OF_YEAR);
      return this.weeksInMonths[month - 1] + (month == leapWeekInMonth ? 1 : 0);
   }

   int getWeeksAtStartOfMonth(int month) {
      return this.getWeeksAtStartOfMonth(month, 0);
   }

   int getWeeksAtStartOfMonth(int month, int leapWeekInMonth) {
      month = this.monthsInYearRange.checkValidIntValue((long)month, ChronoField.MONTH_OF_YEAR);
      leapWeekInMonth = leapWeekInMonth == 0 ? 0 : this.monthsInYearRange.checkValidIntValue((long)leapWeekInMonth, ChronoField.MONTH_OF_YEAR);
      return this.elapsedWeeks[month - 1] + (leapWeekInMonth != 0 && month > leapWeekInMonth ? 1 : 0);
   }

   int getMonthFromElapsedWeeks(int weeksElapsed) {
      return this.getMonthFromElapsedWeeks(weeksElapsed, 0);
   }

   int getMonthFromElapsedWeeks(int weeksElapsed, int leapWeekInMonth) {
      if (weeksElapsed >= 0 && weeksElapsed < (leapWeekInMonth == 0 ? 52 : 53)) {
         leapWeekInMonth = leapWeekInMonth == 0 ? 0 : this.monthsInYearRange.checkValidIntValue((long)leapWeekInMonth, ChronoField.MONTH_OF_YEAR);
         int month = Arrays.binarySearch(this.elapsedWeeks, weeksElapsed);
         month = month >= 0 ? month + 1 : 0 - month - 1;
         return leapWeekInMonth != 0 && month > leapWeekInMonth && weeksElapsed <= this.elapsedWeeks[month - 1] ? month - 1 : month;
      } else {
         throw new DateTimeException("Count of '" + this.elapsedWeeks.length + "' elapsed weeks not valid, should be in the range [0, " + (leapWeekInMonth == 0 ? 52 : 53) + ")");
      }
   }
}
