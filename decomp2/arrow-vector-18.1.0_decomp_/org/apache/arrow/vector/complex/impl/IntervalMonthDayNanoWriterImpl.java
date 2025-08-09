package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.IntervalMonthDayNanoVector;
import org.apache.arrow.vector.holders.IntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;
import org.apache.arrow.vector.types.pojo.Field;

public class IntervalMonthDayNanoWriterImpl extends AbstractFieldWriter {
   final IntervalMonthDayNanoVector vector;

   public IntervalMonthDayNanoWriterImpl(IntervalMonthDayNanoVector vector) {
      this.vector = vector;
   }

   public Field getField() {
      return this.vector.getField();
   }

   public int getValueCapacity() {
      return this.vector.getValueCapacity();
   }

   public void allocate() {
      this.vector.allocateNew();
   }

   public void close() {
      this.vector.close();
   }

   public void clear() {
      this.vector.clear();
   }

   protected int idx() {
      return super.idx();
   }

   public void write(IntervalMonthDayNanoHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableIntervalMonthDayNanoHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeIntervalMonthDayNano(int months, int days, long nanoseconds) {
      this.vector.setSafe(this.idx(), 1, months, days, nanoseconds);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }
}
