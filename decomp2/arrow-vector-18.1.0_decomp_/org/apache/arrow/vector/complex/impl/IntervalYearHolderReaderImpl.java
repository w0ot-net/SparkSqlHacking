package org.apache.arrow.vector.complex.impl;

import java.time.Period;
import org.apache.arrow.vector.complex.writer.IntervalYearWriter;
import org.apache.arrow.vector.holders.IntervalYearHolder;
import org.apache.arrow.vector.holders.NullableIntervalYearHolder;
import org.apache.arrow.vector.types.Types;

public class IntervalYearHolderReaderImpl extends AbstractFieldReader {
   private IntervalYearHolder holder;

   public IntervalYearHolderReaderImpl(IntervalYearHolder holder) {
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
      return Types.MinorType.INTERVALYEAR;
   }

   public boolean isSet() {
      return true;
   }

   public void read(IntervalYearHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableIntervalYearHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Period readPeriod() {
      return Period.ofMonths(this.holder.value);
   }

   public Object readObject() {
      return this.readPeriod();
   }

   public void copyAsValue(IntervalYearWriter writer) {
      writer.write(this.holder);
   }
}
