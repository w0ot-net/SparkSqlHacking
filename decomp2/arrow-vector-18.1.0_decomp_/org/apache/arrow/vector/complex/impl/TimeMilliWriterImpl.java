package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.TimeMilliVector;
import org.apache.arrow.vector.holders.NullableTimeMilliHolder;
import org.apache.arrow.vector.holders.TimeMilliHolder;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeMilliWriterImpl extends AbstractFieldWriter {
   final TimeMilliVector vector;

   public TimeMilliWriterImpl(TimeMilliVector vector) {
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

   public void write(TimeMilliHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableTimeMilliHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeTimeMilli(int value) {
      this.vector.setSafe(this.idx(), 1, value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }
}
