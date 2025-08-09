package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.UnionHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class UnionListReader extends AbstractFieldReader {
   private ListVector vector;
   private ValueVector data;
   private static final int OFFSET_WIDTH = 4;
   private int currentOffset;
   private int maxOffset;

   public UnionListReader(ListVector vector) {
      this.vector = vector;
      this.data = vector.getDataVector();
   }

   public Field getField() {
      return this.vector.getField();
   }

   public boolean isSet() {
      return !this.vector.isNull(this.idx());
   }

   public void setPosition(int index) {
      super.setPosition(index);
      if (this.vector.getOffsetBuffer().capacity() == 0L) {
         this.currentOffset = 0;
         this.maxOffset = 0;
      } else {
         this.currentOffset = this.vector.getOffsetBuffer().getInt((long)index * 4L) - 1;
         this.maxOffset = this.vector.getOffsetBuffer().getInt((long)(index + 1) * 4L);
      }

   }

   public FieldReader reader() {
      return this.data.getReader();
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.LIST;
   }

   public void read(int index, UnionHolder holder) {
      this.setPosition(this.idx());

      for(int i = -1; i < index; ++i) {
         this.next();
      }

      holder.reader = this.data.getReader();
      holder.isSet = this.data.getReader().isSet() ? 1 : 0;
   }

   public int size() {
      int size = this.maxOffset - this.currentOffset - 1;
      return size < 0 ? 0 : size;
   }

   public boolean next() {
      if (this.currentOffset + 1 < this.maxOffset) {
         this.data.getReader().setPosition(++this.currentOffset);
         return true;
      } else {
         return false;
      }
   }

   public void copyAsValue(BaseWriter.ListWriter writer) {
      ComplexCopier.copy(this, (FieldWriter)writer);
   }
}
