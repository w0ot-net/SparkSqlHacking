package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeStampNanoWriter;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.holders.TimeStampNanoHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class TimeStampNanoHolderReaderImpl extends AbstractFieldReader {
   private TimeStampNanoHolder holder;

   public TimeStampNanoHolderReaderImpl(TimeStampNanoHolder holder) {
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
      return Types.MinorType.TIMESTAMPNANO;
   }

   public boolean isSet() {
      return true;
   }

   public void read(TimeStampNanoHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeStampNanoHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      return DateUtility.getLocalDateTimeFromEpochNano(this.holder.value);
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }

   public void copyAsValue(TimeStampNanoWriter writer) {
      writer.write(this.holder);
   }
}
