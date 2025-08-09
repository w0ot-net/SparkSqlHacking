package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.holders.NullableTimeStampMicroHolder;
import org.apache.arrow.vector.holders.TimeStampMicroHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class NullableTimeStampMicroHolderReaderImpl extends AbstractFieldReader {
   private NullableTimeStampMicroHolder holder;

   public NullableTimeStampMicroHolderReaderImpl(NullableTimeStampMicroHolder holder) {
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
      return this.holder.isSet == 1;
   }

   public void read(TimeStampMicroHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeStampMicroHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      return !this.isSet() ? null : DateUtility.getLocalDateTimeFromEpochMicro(this.holder.value);
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }
}
