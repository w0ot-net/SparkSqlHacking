package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.TimeStampMilliTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMilliTZHolder;
import org.apache.arrow.vector.holders.TimeStampMilliTZHolder;
import org.apache.arrow.vector.types.Types;

public class TimeStampMilliTZHolderReaderImpl extends AbstractFieldReader {
   private TimeStampMilliTZHolder holder;

   public TimeStampMilliTZHolderReaderImpl(TimeStampMilliTZHolder holder) {
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
      return Types.MinorType.TIMESTAMPMILLITZ;
   }

   public boolean isSet() {
      return true;
   }

   public void read(TimeStampMilliTZHolder h) {
      h.value = this.holder.value;
      h.timezone = this.holder.timezone;
   }

   public void read(NullableTimeStampMilliTZHolder h) {
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

   public void copyAsValue(TimeStampMilliTZWriter writer) {
      writer.write(this.holder);
   }
}
