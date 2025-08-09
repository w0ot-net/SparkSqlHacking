package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.Float2Vector;
import org.apache.arrow.vector.holders.Float2Holder;
import org.apache.arrow.vector.holders.NullableFloat2Holder;
import org.apache.arrow.vector.types.pojo.Field;

public class Float2WriterImpl extends AbstractFieldWriter {
   final Float2Vector vector;

   public Float2WriterImpl(Float2Vector vector) {
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

   public void write(Float2Holder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableFloat2Holder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeFloat2(short value) {
      this.vector.setSafe(this.idx(), 1, value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }
}
