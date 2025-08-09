package org.apache.arrow.vector.complex.impl;

import java.time.Duration;
import java.time.Period;
import org.apache.arrow.vector.PeriodDuration;
import org.apache.arrow.vector.complex.writer.IntervalMonthDayNanoWriter;
import org.apache.arrow.vector.holders.IntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;
import org.apache.arrow.vector.types.Types;

public class IntervalMonthDayNanoHolderReaderImpl extends AbstractFieldReader {
   private IntervalMonthDayNanoHolder holder;

   public IntervalMonthDayNanoHolderReaderImpl(IntervalMonthDayNanoHolder holder) {
      this.holder = holder;
   }

   public int size() {
      throw new UnsupportedOperationException("You can't call size on a Holder value reader.");
   }

   public boolean next() {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public void setPosition(int index) {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.INTERVALMONTHDAYNANO;
   }

   public boolean isSet() {
      return true;
   }

   public void read(IntervalMonthDayNanoHolder h) {
      h.months = this.holder.months;
      h.days = this.holder.days;
      h.nanoseconds = this.holder.nanoseconds;
   }

   public void read(NullableIntervalMonthDayNanoHolder h) {
      h.months = this.holder.months;
      h.days = this.holder.days;
      h.nanoseconds = this.holder.nanoseconds;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public PeriodDuration readPeriodDuration() {
      return new PeriodDuration(Period.ofMonths(this.holder.months).plusDays((long)this.holder.days), Duration.ofNanos(this.holder.nanoseconds));
   }

   public Object readObject() {
      return this.readPeriodDuration();
   }

   public void copyAsValue(IntervalMonthDayNanoWriter writer) {
      writer.write(this.holder);
   }
}
