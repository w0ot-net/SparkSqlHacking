package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.VarCharHolder;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.Text;

public class VarCharWriterImpl extends AbstractFieldWriter {
   final VarCharVector vector;
   private final Text textBuffer = new Text();

   public VarCharWriterImpl(VarCharVector vector) {
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

   public void write(VarCharHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableVarCharHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeVarChar(int start, int end, ArrowBuf buffer) {
      this.vector.setSafe(this.idx(), 1, start, end, buffer);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeVarChar(Text value) {
      this.vector.setSafe(this.idx(), value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeVarChar(String value) {
      this.textBuffer.set(value);
      this.vector.setSafe(this.idx(), this.textBuffer);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }
}
