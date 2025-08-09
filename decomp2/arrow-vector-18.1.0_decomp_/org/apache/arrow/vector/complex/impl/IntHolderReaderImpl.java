package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.IntWriter;
import org.apache.arrow.vector.holders.IntHolder;
import org.apache.arrow.vector.holders.NullableIntHolder;
import org.apache.arrow.vector.types.Types;

public class IntHolderReaderImpl extends AbstractFieldReader {
   private IntHolder holder;

   public IntHolderReaderImpl(IntHolder holder) {
      this.holder = holder;
   }

   public int size() {
      throw new UnsupportedOperationException("You can't call size on a Holder value reader.");
   }

   public boolean next() {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public void setPosition(int index) {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.INT;
   }

   public boolean isSet() {
      return true;
   }

   public void read(IntHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableIntHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Integer readInteger() {
      Integer value = new Integer(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readInteger();
   }

   public void copyAsValue(IntWriter writer) {
      writer.write(this.holder);
   }
}
