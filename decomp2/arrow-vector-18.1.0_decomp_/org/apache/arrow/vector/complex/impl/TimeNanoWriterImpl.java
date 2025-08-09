package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.TimeNanoVector;
import org.apache.arrow.vector.holders.NullableTimeNanoHolder;
import org.apache.arrow.vector.holders.TimeNanoHolder;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeNanoWriterImpl extends AbstractFieldWriter {
   final TimeNanoVector vector;

   public TimeNanoWriterImpl(TimeNanoVector vector) {
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

   public void write(TimeNanoHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableTimeNanoHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeTimeNano(long value) {
      this.vector.setSafe(this.idx(), 1, value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }
}
