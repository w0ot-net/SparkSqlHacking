package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.TimeStampSecHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class NullableTimeStampSecHolderReaderImpl extends AbstractFieldReader {
   private NullableTimeStampSecHolder holder;

   public NullableTimeStampSecHolderReaderImpl(NullableTimeStampSecHolder holder) {
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
      return this.holder.isSet == 1;
   }

   public void read(TimeStampSecHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeStampSecHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      if (!this.isSet()) {
         return null;
      } else {
         long millis = TimeUnit.SECONDS.toMillis(this.holder.value);
         return DateUtility.getLocalDateTimeFromEpochMilli(millis);
      }
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }
}
