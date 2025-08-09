package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.apache.arrow.vector.complex.writer.TimeStampSecWriter;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.TimeStampSecHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class TimeStampSecHolderReaderImpl extends AbstractFieldReader {
   private TimeStampSecHolder holder;

   public TimeStampSecHolderReaderImpl(TimeStampSecHolder holder) {
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
      return Types.MinorType.TIMESTAMPSEC;
   }

   public boolean isSet() {
      return true;
   }

   public void read(TimeStampSecHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeStampSecHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      long millis = TimeUnit.SECONDS.toMillis(this.holder.value);
      return DateUtility.getLocalDateTimeFromEpochMilli(millis);
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }

   public void copyAsValue(TimeStampSecWriter writer) {
      writer.write(this.holder);
   }
}
