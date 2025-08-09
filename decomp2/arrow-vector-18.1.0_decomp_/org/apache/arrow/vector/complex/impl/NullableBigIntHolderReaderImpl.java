package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.BigIntHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.types.Types;

public class NullableBigIntHolderReaderImpl extends AbstractFieldReader {
   private NullableBigIntHolder holder;

   public NullableBigIntHolderReaderImpl(NullableBigIntHolder holder) {
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
      return Types.MinorType.BIGINT;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(BigIntHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableBigIntHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Long readLong() {
      if (!this.isSet()) {
         return null;
      } else {
         Long value = new Long(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readLong();
   }
}
