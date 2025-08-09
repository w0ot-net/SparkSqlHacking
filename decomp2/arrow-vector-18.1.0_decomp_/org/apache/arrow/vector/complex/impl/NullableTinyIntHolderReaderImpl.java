package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.NullableTinyIntHolder;
import org.apache.arrow.vector.holders.TinyIntHolder;
import org.apache.arrow.vector.types.Types;

public class NullableTinyIntHolderReaderImpl extends AbstractFieldReader {
   private NullableTinyIntHolder holder;

   public NullableTinyIntHolderReaderImpl(NullableTinyIntHolder holder) {
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
      return Types.MinorType.TINYINT;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(TinyIntHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTinyIntHolder h) {
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
