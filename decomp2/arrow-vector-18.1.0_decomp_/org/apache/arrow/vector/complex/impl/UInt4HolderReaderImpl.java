package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.UInt4Writer;
import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.UInt4Holder;
import org.apache.arrow.vector.types.Types;

public class UInt4HolderReaderImpl extends AbstractFieldReader {
   private UInt4Holder holder;

   public UInt4HolderReaderImpl(UInt4Holder holder) {
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
      return Types.MinorType.UINT4;
   }

   public boolean isSet() {
      return true;
   }

   public void read(UInt4Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableUInt4Holder h) {
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

   public void copyAsValue(UInt4Writer writer) {
      writer.write(this.holder);
   }
}
