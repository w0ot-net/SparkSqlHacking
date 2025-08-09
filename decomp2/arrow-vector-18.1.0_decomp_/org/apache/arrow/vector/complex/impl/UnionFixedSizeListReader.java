package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.UnionHolder;
import org.apache.arrow.vector.types.Types;

public class UnionFixedSizeListReader extends AbstractFieldReader {
   private final FixedSizeListVector vector;
   private final ValueVector data;
   private final int listSize;
   private int currentOffset;

   public UnionFixedSizeListReader(FixedSizeListVector vector) {
      this.vector = vector;
      this.data = vector.getDataVector();
      this.listSize = vector.getListSize();
   }

   public boolean isSet() {
      return !this.vector.isNull(this.idx());
   }

   public FieldReader reader() {
      return this.data.getReader();
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }

   public Types.MinorType getMinorType() {
      return this.vector.getMinorType();
   }

   public void setPosition(int index) {
      super.setPosition(index);
      this.data.getReader().setPosition(index * this.listSize);
      this.currentOffset = 0;
   }

   public void read(int index, UnionHolder holder) {
      this.setPosition(this.idx());

      for(int i = -1; i < index; ++i) {
         if (!this.next()) {
            throw new IndexOutOfBoundsException("Requested " + index + ", size " + this.listSize);
         }
      }

      holder.reader = this.data.getReader();
      holder.isSet = this.vector.isNull(this.idx()) ? 0 : 1;
   }

   public int size() {
      return this.listSize;
   }

   public boolean next() {
      if (this.currentOffset < this.listSize) {
         this.data.getReader().setPosition(this.idx() * this.listSize + this.currentOffset++);
         return true;
      } else {
         return false;
      }
   }

   public void copyAsValue(BaseWriter.ListWriter writer) {
      ComplexCopier.copy(this, (FieldWriter)writer);
   }
}
