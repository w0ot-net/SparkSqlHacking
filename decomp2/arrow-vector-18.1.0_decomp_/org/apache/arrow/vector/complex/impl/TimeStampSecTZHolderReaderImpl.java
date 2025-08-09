package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.TimeStampSecTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampSecTZHolder;
import org.apache.arrow.vector.holders.TimeStampSecTZHolder;
import org.apache.arrow.vector.types.Types;

public class TimeStampSecTZHolderReaderImpl extends AbstractFieldReader {
   private TimeStampSecTZHolder holder;

   public TimeStampSecTZHolderReaderImpl(TimeStampSecTZHolder holder) {
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
      return Types.MinorType.TIMESTAMPSECTZ;
   }

   public boolean isSet() {
      return true;
   }

   public void read(TimeStampSecTZHolder h) {
      h.value = this.holder.value;
      h.timezone = this.holder.timezone;
   }

   public void read(NullableTimeStampSecTZHolder h) {
      h.value = this.holder.value;
      h.timezone = this.holder.timezone;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Long readLong() {
      Long value = new Long(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readLong();
   }

   public void copyAsValue(TimeStampSecTZWriter writer) {
      writer.write(this.holder);
   }
}
