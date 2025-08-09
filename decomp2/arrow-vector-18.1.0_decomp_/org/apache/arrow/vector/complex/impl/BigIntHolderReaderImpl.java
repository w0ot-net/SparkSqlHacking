package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.holders.BigIntHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.types.Types;

public class BigIntHolderReaderImpl extends AbstractFieldReader {
   private BigIntHolder holder;

   public BigIntHolderReaderImpl(BigIntHolder holder) {
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
      return true;
   }

   public void read(BigIntHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableBigIntHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Long readLong() {
      Long value = new Long(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readLong();
   }

   public void copyAsValue(BigIntWriter writer) {
      writer.write(this.holder);
   }
}
