package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.UInt1Holder;
import org.apache.arrow.vector.types.Types;

public class NullableUInt1HolderReaderImpl extends AbstractFieldReader {
   private NullableUInt1Holder holder;

   public NullableUInt1HolderReaderImpl(NullableUInt1Holder holder) {
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
      return Types.MinorType.UINT1;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(UInt1Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableUInt1Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Byte readByte() {
      if (!this.isSet()) {
         return null;
      } else {
         Byte value = new Byte(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readByte();
   }
}
