package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.DateDayHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;
import org.apache.arrow.vector.types.Types;

public class NullableDateDayHolderReaderImpl extends AbstractFieldReader {
   private NullableDateDayHolder holder;

   public NullableDateDayHolderReaderImpl(NullableDateDayHolder holder) {
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
      return Types.MinorType.DATEDAY;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(DateDayHolder h) {
      h.value = this.holder.value;
   }

   public void read(NullableDateDayHolder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Integer readInteger() {
      if (!this.isSet()) {
         return null;
      } else {
         Integer value = new Integer(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readInteger();
   }
}
