package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeStampMilliWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.TimeStampMilliHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class TimeStampMilliHolderReaderImpl extends AbstractFieldReader {
   private TimeStampMilliHolder holder;

   public TimeStampMilliHolderReaderImpl(TimeStampMilliHolder holder) {
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
      return Types.MinorType.TIMESTAMPMILLI;
   }

   public boolean isSet() {
      return true;
   }

   public void read(TimeStampMilliHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeStampMilliHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      return DateUtility.getLocalDateTimeFromEpochMilli(this.holder.value);
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }

   public void copyAsValue(TimeStampMilliWriter writer) {
      writer.write(this.holder);
   }
}
