package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.holders.NullableTimeMilliHolder;
import org.apache.arrow.vector.holders.TimeMilliHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class NullableTimeMilliHolderReaderImpl extends AbstractFieldReader {
   private NullableTimeMilliHolder holder;

   public NullableTimeMilliHolderReaderImpl(NullableTimeMilliHolder holder) {
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
      return Types.MinorType.TIMEMILLI;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(TimeMilliHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableTimeMilliHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      return !this.isSet() ? null : DateUtility.getLocalDateTimeFromEpochMilli((long)this.holder.value);
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }
}
