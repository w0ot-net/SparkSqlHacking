package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.UInt4Holder;
import org.apache.arrow.vector.types.Types;

public class NullableUInt4HolderReaderImpl extends AbstractFieldReader {
   private NullableUInt4Holder holder;

   public NullableUInt4HolderReaderImpl(NullableUInt4Holder holder) {
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
      return this.holder.isSet == 1;
   }

   public void read(UInt4Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableUInt4Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Integer readInteger() {
      if (!this.isSet()) {
         return null;
      } else {
         Integer value = new Integer(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readInteger();
   }
}
