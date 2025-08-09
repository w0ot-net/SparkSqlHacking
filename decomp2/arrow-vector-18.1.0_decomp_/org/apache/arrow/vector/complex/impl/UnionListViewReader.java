package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.UnionHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class UnionListViewReader extends AbstractFieldReader {
   private final ListViewVector vector;
   private final ValueVector data;
   private int currentOffset;
   private int size;

   public UnionListViewReader(ListViewVector vector) {
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
         this.size = 0;
      } else {
         this.currentOffset = this.vector.getOffsetBuffer().getInt((long)index * 4L);
         this.size = this.vector.getSizeBuffer().getInt((long)index * 4L);
      }

   }

   public FieldReader reader() {
      return this.data.getReader();
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.LISTVIEW;
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
      return Math.max(this.size, 0);
   }

   public boolean next() {
      if (this.currentOffset < this.currentOffset + this.size) {
         this.data.getReader().setPosition(this.currentOffset++);
         return true;
      } else {
         return false;
      }
   }
}
