package org.threeten.extra.chrono;

import java.time.DayOfWeek;
import java.time.Month;

public final class AccountingChronologyBuilder {
   private DayOfWeek endsOn;
   private boolean inLastWeek;
   private Month end;
   private AccountingYearDivision division;
   private int leapWeekInMonth;
   private int yearOffset;

   public AccountingChronologyBuilder endsOn(DayOfWeek endsOn) {
      this.endsOn = endsOn;
      return this;
   }

   public AccountingChronologyBuilder nearestEndOf(Month end) {
      this.inLastWeek = false;
      this.end = end;
      return this;
   }

   public AccountingChronologyBuilder inLastWeekOf(Month end) {
      this.inLastWeek = true;
      this.end = end;
      return this;
   }

   public AccountingChronologyBuilder withDivision(AccountingYearDivision division) {
      this.division = division;
      return this;
   }

   public AccountingChronologyBuilder leapWeekInMonth(int leapWeekInMonth) {
      this.leapWeekInMonth = leapWeekInMonth;
      return this;
   }

   public AccountingChronologyBuilder accountingYearEndsInIsoYear() {
      this.yearOffset = 0;
      return this;
   }

   public AccountingChronologyBuilder accountingYearStartsInIsoYear() {
      this.yearOffset = 1;
      return this;
   }

   public AccountingChronology toChronology() {
      return AccountingChronology.create(this.endsOn, this.end, this.inLastWeek, this.division, this.leapWeekInMonth, this.yearOffset);
   }
}
