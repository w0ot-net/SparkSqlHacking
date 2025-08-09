package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.holders.UInt2Holder;
import org.apache.arrow.vector.types.Types;

public class NullableUInt2HolderReaderImpl extends AbstractFieldReader {
   private NullableUInt2Holder holder;

   public NullableUInt2HolderReaderImpl(NullableUInt2Holder holder) {
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
      return Types.MinorType.UINT2;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(UInt2Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableUInt2Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Character readCharacter() {
      if (!this.isSet()) {
         return null;
      } else {
         Character value = new Character(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readCharacter();
   }
}
