package org.apache.arrow.vector.complex.impl;

import java.time.Duration;
import org.apache.arrow.vector.holders.IntervalDayHolder;
import org.apache.arrow.vector.holders.NullableIntervalDayHolder;
import org.apache.arrow.vector.types.Types;

public class NullableIntervalDayHolderReaderImpl extends AbstractFieldReader {
   private NullableIntervalDayHolder holder;

   public NullableIntervalDayHolderReaderImpl(NullableIntervalDayHolder holder) {
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
      return Types.MinorType.INTERVALDAY;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(IntervalDayHolder h) {
      h.days = this.holder.days;
      h.milliseconds = this.holder.milliseconds;
   }

   public void read(NullableIntervalDayHolder h) {
      h.days = this.holder.days;
      h.milliseconds = this.holder.milliseconds;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Duration readDuration() {
      return !this.isSet() ? null : Duration.ofDays((long)this.holder.days).plusMillis((long)this.holder.milliseconds);
   }

   public Object readObject() {
      return this.readDuration();
   }
}
