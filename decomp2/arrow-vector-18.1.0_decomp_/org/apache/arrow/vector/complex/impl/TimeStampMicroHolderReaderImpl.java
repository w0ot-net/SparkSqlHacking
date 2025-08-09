package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.TimeStampMicroWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMicroHolder;
import org.apache.arrow.vector.holders.TimeStampMicroHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class TimeStampMicroHolderReaderImpl extends AbstractFieldReader {
   private TimeStampMicroHolder holder;

   public TimeStampMicroHolderReaderImpl(TimeStampMicroHolder holder) {
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
      return Types.MinorType.TIMESTAMPMICRO;
   }

   public boolean isSet() {
      return true;
   }

   public void read(TimeStampMicroHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeStampMicroHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      return DateUtility.getLocalDateTimeFromEpochMicro(this.holder.value);
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }

   public void copyAsValue(TimeStampMicroWriter writer) {
      writer.write(this.holder);
   }
}
