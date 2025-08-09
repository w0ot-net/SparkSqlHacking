package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.complex.writer.DateMilliWriter;
import org.apache.arrow.vector.holders.DateMilliHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.DateUtility;

public class DateMilliHolderReaderImpl extends AbstractFieldReader {
   private DateMilliHolder holder;

   public DateMilliHolderReaderImpl(DateMilliHolder holder) {
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
      return Types.MinorType.DATEMILLI;
   }

   public boolean isSet() {
      return true;
   }

   public void read(DateMilliHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableDateMilliHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public LocalDateTime readLocalDateTime() {
      return DateUtility.getLocalDateTimeFromEpochMilli(this.holder.value);
   }

   public Object readObject() {
      return this.readLocalDateTime();
   }

   public void copyAsValue(DateMilliWriter writer) {
      writer.write(this.holder);
   }
}
