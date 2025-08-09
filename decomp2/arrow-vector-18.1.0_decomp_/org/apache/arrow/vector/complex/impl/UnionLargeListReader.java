package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.UnionHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class UnionLargeListReader extends AbstractFieldReader {
   private LargeListVector vector;
   private ValueVector data;
   private static final long OFFSET_WIDTH = 8L;
   private long currentOffset;
   private long maxOffset;

   public UnionLargeListReader(LargeListVector vector) {
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
      this.currentOffset = this.vector.getOffsetBuffer().getLong((long)index * 8L) - 1L;
      this.maxOffset = this.vector.getOffsetBuffer().getLong(((long)index + 1L) * 8L);
   }

   public FieldReader reader() {
      return this.data.getReader();
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.LARGELIST;
   }

   public void read(int index, UnionHolder holder) {
      this.setPosition(index);

      for(int i = -1; i < index; ++i) {
         this.next();
      }

      holder.reader = this.data.getReader();
      holder.isSet = this.data.getReader().isSet() ? 1 : 0;
   }

   public int size() {
      int size = LargeMemoryUtil.checkedCastToInt(this.maxOffset - this.currentOffset - 1L);
      return size < 0 ? 0 : size;
   }

   public boolean next() {
      if (this.currentOffset + 1L < this.maxOffset) {
         this.data.getReader().setPosition(LargeMemoryUtil.checkedCastToInt(++this.currentOffset));
         return true;
      } else {
         return false;
      }
   }

   public void copyAsValue(UnionLargeListWriter writer) {
      ComplexCopier.copy(this, writer);
   }
}
