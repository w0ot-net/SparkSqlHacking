package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.holders.BitHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;
import org.apache.arrow.vector.types.Types;

public class BitHolderReaderImpl extends AbstractFieldReader {
   private BitHolder holder;

   public BitHolderReaderImpl(BitHolder holder) {
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
      return Types.MinorType.BIT;
   }

   public boolean isSet() {
      return true;
   }

   public void read(BitHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableBitHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Boolean readBoolean() {
      return new Boolean(this.holder.value != 0);
   }

   public Object readObject() {
      return this.readBoolean();
   }

   public void copyAsValue(BitWriter writer) {
      writer.write(this.holder);
   }
}
