package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.TimeNanoWriter;
import org.apache.arrow.vector.holders.NullableTimeNanoHolder;
import org.apache.arrow.vector.holders.TimeNanoHolder;
import org.apache.arrow.vector.types.Types;

public class TimeNanoHolderReaderImpl extends AbstractFieldReader {
   private TimeNanoHolder holder;

   public TimeNanoHolderReaderImpl(TimeNanoHolder holder) {
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
      return Types.MinorType.TIMENANO;
   }

   public boolean isSet() {
      return true;
   }

   public void read(TimeNanoHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeNanoHolder h) {
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

   public void copyAsValue(TimeNanoWriter writer) {
      writer.write(this.holder);
   }
}
