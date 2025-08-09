package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.TimeStampMilliVector;
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.TimeStampMilliHolder;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeStampMilliWriterImpl extends AbstractFieldWriter {
   final TimeStampMilliVector vector;

   public TimeStampMilliWriterImpl(TimeStampMilliVector vector) {
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

   public void write(TimeStampMilliHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableTimeStampMilliHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeTimeStampMilli(long value) {
      this.vector.setSafe(this.idx(), 1, value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }
}
