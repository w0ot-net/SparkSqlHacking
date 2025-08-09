package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.SmallIntWriter;
import org.apache.arrow.vector.holders.NullableSmallIntHolder;
import org.apache.arrow.vector.holders.SmallIntHolder;
import org.apache.arrow.vector.types.Types;

public class SmallIntHolderReaderImpl extends AbstractFieldReader {
   private SmallIntHolder holder;

   public SmallIntHolderReaderImpl(SmallIntHolder holder) {
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
      return Types.MinorType.SMALLINT;
   }

   public boolean isSet() {
      return true;
   }

   public void read(SmallIntHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableSmallIntHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Short readShort() {
      Short value = new Short(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readShort();
   }

   public void copyAsValue(SmallIntWriter writer) {
      writer.write(this.holder);
   }
}
